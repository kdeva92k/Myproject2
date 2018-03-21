<%@page import="java.util.Properties"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.lang.management.ThreadInfo"%>
<%@page import="java.lang.management.ManagementFactory"%>
<%@page import="java.lang.management.GarbageCollectorMXBean"%>
<%@page import="java.lang.management.ManagementFactory"%>
<%@page import="java.lang.management.MemoryPoolMXBean"%>
<%@page import="java.lang.management.MemoryUsage"%>
<%@page import="java.util.List"%>


<%!


public static String Nz(String xValue1, String xValue2){
	if ( checkNull(xValue1) )
	    return xValue2;
	else
	    return xValue1;
   }
public static boolean checkNull(String xParam){
	xParam = (xParam != null) ? xParam.trim() : xParam  ;
	if (xParam == null || xParam.length() == 0 || xParam == "")
	    return true;
	else
	    return false;
   }
%>

<%
	//this part is to call gc from crontab in central server in regular interval.
	if (request.getParameter("FROM") != null && request.getParameter("FROM").equals("CRON")) {
		Runtime.getRuntime().gc();
		return;
	}
%>
<html>
<head>
<title>MEMORY STATUS OF THE GLACE JAVA VIRTUAL MACHINE</title>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<style type="text/css">
th {
	border: 1px solid black;
	padding: 0 3px;
}

td.main {
	border: 1px solid black;
}

span.click {
	cursor: pointer;
	color: blue;
}


#loadingmsg {
	color: black;
	background: #fff;
	padding: 10px;
	position: fixed;
	top: 37.5%;
	left: 0%;
	z-index: 100;
	margin-right: 0%;
	margin-bottom: 0%;
	width: 100%;
	text-align: center;
}

#loadingover {
	background: black;
	z-index: 99;
	width: 100%;
	height: 100%;
	position: fixed;
	top: 0;
	left: 0;
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=80)";
	filter: alpha(opacity = 80);
	-moz-opacity: 0.8;
	-khtml-opacity: 0.8;
	opacity: 0.8;
}
</style>
<script type="text/javascript">
	var killThread = function(xtid) {
		if (confirm("This action will kill the thread")) {
			document.monitorForm.mode.value = 1;
			document.monitorForm.tid.value = xtid;
			document.monitorForm.submit();
		}
	}

	var expandCollapse = function(xThis, xId) {
		if (typeof (xThis) == "undefined")
			return;

		if (xThis.innerHTML == "&nbsp;(+)&nbsp;") {
			document.getElementById("div_" + xId).style.display = "block";
			xThis.innerHTML = "&nbsp;(-)&nbsp;";
		} else {
			document.getElementById("div_" + xId).style.display = "none";
			xThis.innerHTML = "&nbsp;(+)&nbsp;";
		}
	}
	function heapDumpConfirmation() {
		var answer = confirm('This is a huge process and consumes wast amount of time.\n\nAre you sure want to Continue ?\n(Click OK to continue & CANCEL to Abort)');
		if (answer == true) {
			document.getElementById('loadingmsg').style.display = 'block';
			document.getElementById('loadingover').style.display = 'block';
			heapDump();
		} else
			alert("Cancelled");
	}
	function heapDump() {
		//	Runtime.getRuntime().gc();
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				var answer = confirm('Heap Dump process completed.\nDo you want to Download the file ?\n\nClick OK to Download & CANCEL to Abort');
				if (answer == true) {
					var downloadURL = "../jsp/utilities/DownloadDumpFile.jsp?fileName="
							+ xhttp.responseText;
					window.open(downloadURL);
				} else
					alert("Download Cancelled.");
				document.getElementById('loadingmsg').style.display = 'none';
				document.getElementById('loadingover').style.display = 'none';
			}
		};
		xhttp.open("GET", "heapDump.Action", true);
		xhttp.send();
	}
</script>


</head>
<body>
	<form method="GET" id="monitorForm" name="monitorForm"
		action="JVMfreespace.jsp">
		<input type="hidden" name="mode" id="mode" /> <input type="hidden"
			name="tid" id="tid" />
		<h2 style='color: blue; font-size: 20px;'>Memory status of GLACE
			JVM</h2>
		<%
			long maxmemory = Runtime.getRuntime().maxMemory();
			long totalmemory = Runtime.getRuntime().totalMemory();
			long freememory = Runtime.getRuntime().freeMemory();
			long usedmemory = totalmemory - freememory;
			long availablememory = maxmemory - usedmemory;
		%>
		<br />
		<font color=red><b>MAXIMUM ALLOTED SPACE FOR JVM</b></font>&nbsp;=&nbsp;<%=maxmemory%>&nbsp;Bytes.&nbsp;&nbsp;(a)&nbsp;&nbsp;<%=maxmemory / 1024%>&nbsp;KB.&nbsp;&nbsp;(a)&nbsp;&nbsp;<%=(maxmemory / (1024 * 1024))%>&nbsp;MB.
		<br />
		<font color=red><b>SPACE USED BY JVM (FOR CURRENT AND
				FUTURE OBJECT)</b></font>&nbsp;=&nbsp;<%=totalmemory%>&nbsp;Bytes.&nbsp;&nbsp;(a)&nbsp;&nbsp;<%=totalmemory / 1024%>&nbsp;KB.&nbsp;&nbsp;(a)&nbsp;&nbsp;<%=totalmemory / (1024 * 1024)%>&nbsp;MB.
		<br />
		<font color=red><b>SPACE USED BY CURRENT OBJECT</b></font>&nbsp;=&nbsp;<%=usedmemory%>&nbsp;Bytes.&nbsp;&nbsp;(a)&nbsp;&nbsp;<%=usedmemory / 1024%>&nbsp;KB.&nbsp;&nbsp;(a)&nbsp;&nbsp;<%=usedmemory / (1024 * 1024)%>&nbsp;MB.
		<br />
		<font color=red><b>AVAILABLE SPACE FOR FUTURE OBJECTS</b></font>&nbsp;=&nbsp;<%=availablememory%>&nbsp;Bytes.&nbsp;&nbsp;(a)&nbsp;&nbsp;<%=availablememory / 1024%>&nbsp;KB.&nbsp;&nbsp;(a)&nbsp;&nbsp;<%=availablememory / (1024 * 1024)%>&nbsp;MB.
		<br />
		<br />


		<%
			String mode = Nz(request.getParameter("mode"), "0");
			long tid = Long.parseLong(Nz(request.getParameter("tid"), "-1"));
		%>

		<%-- <%
			Properties prop = new Properties();
			FileInputStream pro_ip = null;
			String ip_addr = "";
			try {
				ServletContext context;
				context = config.getServletContext();
				pro_ip = new FileInputStream(context.getRealPath("/jsp/jvmmanager_trusted_ip.properties"));
				prop.load(pro_ip);
				ip_addr = prop.get("ip_addr").toString();
				pro_ip.close();
			} catch (Exception e) {
				GlaceOutLoggingStream.console(e);
			}
			//String ipAddress = request.getRemoteAddr();

			String ipAddress = Nz(request.getHeader("X-FORWARDED-FOR"), "-1");
			if (ipAddress.equalsIgnoreCase("-1")) {
				ipAddress = request.getRemoteAddr();
			}
			if (ipAddress.equals(ip_addr)) {
				//	 out.println("<div><input type=\"button\" size=\"30\"  value=\"Heap Dump\"  id=\"dump\" onclick=\"javascript:heapDumpConfirmation();\" style=\"float:right;position:absolute;right:5%;top:15%;font-size:17px\" /></div>");
			}
		%> --%>

		<!-- <div>
			<input type="button" size="30" value="Heap Dump" id="dump"
				onclick="javascript:heapDumpConfirmation();"
				style="float: right; position: absolute; right: 5%; top: 15%; font-size: 17px" />
		</div> -->
		<table cellpadding="0" cellspacing="0" border="0"
			style="table-layout: fixed; width: 100%;">
			<tr>
				<th>Thread ID</th>
				<th>Thread Name</th>
				<th>Status</th>
				<th>CPU Time</th>
				<th>User Time</th>
				<th style="border: 0px solid black;">&nbsp;</th>
			</tr>
			<%
				if (mode.equals("1") && tid != -1) {
					Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();
					Set<Thread> threadSet = threadMap.keySet();
					Iterator<Thread> threadIt = threadSet.iterator();
					while (threadIt.hasNext()) {
						Thread currThread = threadIt.next();
						if (currThread.getId() == tid) {
							currThread.stop();
						}
					}
				}
				ThreadInfo[] infoThreads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
				for (int i = infoThreads.length - 1; i > 0; i--) {
					if (infoThreads[i].getThreadName().contains("http")) {
						out.println(
								"<tr><td class=\"main\">" + infoThreads[i].getThreadId() + "</td><td class=\"main\">"
										+ infoThreads[i].getThreadName() + "</td><td class=\"main\">"
										+ infoThreads[i].getThreadState() + "</td><td class=\"main\">"
										+ (ManagementFactory.getThreadMXBean()
												.getThreadCpuTime(infoThreads[i].getThreadId()) / 100000000)
										+ "</td><td class=\"main\">"
										+ (ManagementFactory.getThreadMXBean()
												.getThreadUserTime(infoThreads[i].getThreadId()) / 100000000)
										+ "</td><td><span class=\"click\" title=\"Expand/Collapse\" onclick=\"expandCollapse(this,"
										+ infoThreads[i].getThreadId()
										+ ")\">&nbsp;(+)&nbsp;</span><span class=\"click\" title=\"Kill Thread\" onclick=\"killThread("
										+ infoThreads[i].getThreadId() + ")\">&nbsp;(x)&nbsp;<span></td></tr>");

						StackTraceElement[] trace = infoThreads[i].getStackTrace();
						out.println("<tr><td colspan=\"5\"><div id=\"div_" + infoThreads[i].getThreadId()
								+ "\" style=\"display:none;\">");
						for (int traceCnt = 0; traceCnt < trace.length; traceCnt++) {
							out.println("<span style=\"color:#A52A2A;\">Class Name: </span><span>"
									+ trace[traceCnt].getClassName()
									+ ",&nbsp;</span><span style=\"color:#A52A2A;\">&nbsp;Method Name: </span><span>"
									+ trace[traceCnt].getMethodName() + "</span><br />");
						}
						out.println("</div></td></tr>");
					}
				}
			%>
		</table>
	</form>
	<div
		style="display: flex; justify-content: center; align-items: center; height: 20%; width: 100%">
		<div id='loadingmsg' style='display: none'>
			<img src="../images/ellipsis.gif"><br />Processing, please
			wait...<br /> do not refresh or close the page ...
		</div>
		<div id='loadingover' style='display: none'></div>
		<!-- <div style="display:none" id="loadingDiv" ><span style="font-size:30px">LOADING ...  </span><img src="ellipsis.gif"></div> -->
	</div>

	<%
		List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
		out.println(
				"<table cellpadding='10' cellspacing='0' border='1' style='table-layout:fixed;width:100%'><caption><font size=5>Garbage Collector Info</font></caption><tr><th bgcolor='skyblue'>Name</th><th bgcolor='skyblue'>Collection Count</th><th bgcolor='skyblue'>Collection Time</th><th bgcolor='skyblue'>Memory Pools</th></tr>");
		for (GarbageCollectorMXBean tmpGC : gcList) {

			String[] memoryPoolNames = tmpGC.getMemoryPoolNames();
			out.print("<tr><td>" + tmpGC.getName() + "</td>");
			out.print("<td>" + tmpGC.getCollectionCount() + "</td>");
			out.print("<td>" + tmpGC.getCollectionTime() + "</td>");
			out.print("<td><table>");
			for (String mpnTmp : memoryPoolNames) {
				out.println("<tr><td>" + mpnTmp + "</td></tr>");
			}
			out.print("</table></td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<br><br><br>");
		out.println(
				"<table border='1' cellpadding='10' cellspacing='0' style='table-layout:fixed;width:100%'><caption><font size=5>Memory Pools Info</font></caption>");
		out.println(
				"<tr><th bgcolor='skyblue'>Name</th><th bgcolor='skyblue'>Usage</th><th bgcolor='skyblue'>Collection Useage</th><th bgcolor='skyblue'>Peak Useage</th><th bgcolor='skyblue'>Type</th><th bgcolor='skyblue'>Memory Manager Names</th></tr>");
		List<MemoryPoolMXBean> memoryList = ManagementFactory.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean tmpMem : memoryList) {
			String[] memManagerNames = tmpMem.getMemoryManagerNames();

			out.println("<tr><td>" + tmpMem.getName() + "</td>");

			String usage = tmpMem.getUsage().toString();
			String[] usageSplit = usage.split("[\\)]");
			out.println("<td><table>");
			for (String usageValue : usageSplit)
				out.println("<tr><td>" + usageValue + ")</td></tr>");
			out.print("</table></td>");

			if (tmpMem.getCollectionUsage() != null) {
				String collectionUsage = tmpMem.getCollectionUsage().toString();
				String[] collectionUsageSplit = collectionUsage.split("[\\)]");
				out.println("<td><table>");
				for (String usageValue : collectionUsageSplit)
					out.println("<tr><td>" + usageValue + ")</td></tr>");
				out.print("</table></td>");
			} else {
				out.println("<td>" + tmpMem.getCollectionUsage() + "</td>");
			}

			String peakUsage = tmpMem.getPeakUsage().toString();
			String[] peakUsageSplit = peakUsage.split("[\\)]");
			out.println("<td><table>");
			for (String usageValue : peakUsageSplit)
				out.println("<tr><td>" + usageValue + ")</td></tr>");
			out.print("</table></td>");

			out.println("<td>" + tmpMem.getType() + "</td>");

			out.println("<td><table>");
			for (String mmnTmp : memManagerNames) {
				out.println("<tr><td>" + mmnTmp + "</td></tr>");
			}
			out.print("</table></td>");
			out.println("</tr>");
		}
		out.println("</table>\n");
		out.println("<br><br><br>");

		MemoryUsage mu = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		MemoryUsage muNH = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();

		out.println(
				"<table border='1' cellpadding='10' cellspacing='0' style='table-layout:fixed;width:100%'><caption><font size=5>Total Memory Info</font></caption><th bgcolor='skyblue'>Name</th><th bgcolor='skyblue'>Init</th><th bgcolor='skyblue'>Max</th><th bgcolor='skyblue'>Used</th><th bgcolor='skyblue'>Commited</th>");
		out.println("<tr><td>Heap Memory</td><td>" + mu.getInit() + "</td><td>" + mu.getMax() + "</td><td>"
				+ mu.getUsed() + "</td><td>" + mu.getCommitted() + "</td></tr>");
		out.println("<tr><td>Non Heap Memory</td><td>" + muNH.getInit() + "</td><td>" + muNH.getMax() + "</td><td>"
				+ muNH.getUsed() + "</td><td>" + muNH.getCommitted() + "</td></tr></table>");
		out.println("<br><br><br>");
	%>

</body>
</html>
