<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html>
<script language = "javascript" src="../../../includes/GlaceAjax/GlaceAjaxAction.js"></script>
<script language = "javascript" src="../../../includes/chart/ClinicalIntake/PatientClinicalIntake.js"></script>
<script type="text/javascript">
<![CDATA[
function submitForm(){
pageNo=document.getElementById('pageNo').value;
    var patientId=document.getElementById('patientid').value;
	var requestId=document.getElementById('requestId').value;  
	var groupId=document.getElementById('groupId').value;
	var xml=collectXml(pageNo,patientId,requestId,groupId,3);
	document.getElementById('hiddenxml').value=xml;
		document.clinicalIntake.action="PatientClinicalIntake.Action?mode=3&patientId="+patientId+"&requestId="+requestId+"&groupId="+groupId;
		document.clinicalIntake.submit();
 }
function executeToggle(obj){
	var currId=obj.getAttribute("toggleId");
	if(obj.checked){
		var groupName=obj.attributes.name.value;
		var groupElements=document.getElementsByName(groupName);
		for(var elementsId=0;elementsId<groupElements.length;elementsId++){
			if(currId!=groupElements[elementsId].getAttribute("toggleId")){
				 groupElements[elementsId].checked = false;
			}
		}
	}
}
function changePage(pageNo,form){
	var patientId=document.getElementById('patientid').value;
	var requestId=document.getElementById('requestId').value;
	var groupId=document.getElementById('groupId').value;
	var mode=document.getElementById('mode').value;
    	var xml=collectXml(pageNo,patientId,requestId,groupId,2);
	    document.getElementById('hiddenxml').value=xml;
		document.clinicalIntake.action="PatientClinicalIntake.Action?&pageNo="+((pageNo) ? pageNo : 0)+"&patientId="+patientId+"&requestId="+requestId+"&chartId="+requestId+"&mode=2&groupId="+groupId;
		document.clinicalIntake.submit();
 }

function makeDirty(obj)
{
try{
var id=obj.getAttribute("id");
  var lab=obj.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("label");
  obj.setAttribute("value",obj.value);
 	for(var i=0;i<lab.length;i++){
 		var labelid=lab[i].getAttribute("id");
 		if(id==labelid){
 			lab[i].setAttribute("parentdirty","true");
	 	}
	}
	if(obj){
    	obj.setAttribute("isdirty","true");
		}
	}catch(e){
		alert(e.message);
	}		
}
function collectXml(pageNo,patientId,requestId,groupId,mode){
	var clen=document.getElementsByTagName('label');
	var inputelem=document.getElementsByTagName('INPUT');
	var optelem=document.getElementsByTagName('OPTION');
	var totalNoOfQuestions=document.getElementById('totalNoOfQuestions').value;
	var totalNoofPages=document.getElementById('totalNoofPages').value;
	var currentPage=document.getElementById('pageNo').value;
	var	xml="<question>";
			xml+="<header><patientid value=\""+patientId+"\">"+patientId+"</patientid>\n<requestid value=\""+requestId+"\">"+requestId+"</requestid>\n<mode value=\""+mode+"\">"+mode+"</mode><groupId value=\""+groupId+"\">"+groupId+"</groupId>\n</header>";
			xml+="<tnoofquestions value=\""+totalNoOfQuestions+"\">"+totalNoOfQuestions+"</tnoofquestions>";
			xml+="<noofpages value=\""+totalNoofPages+"\">"+totalNoofPages+"</noofpages>";
			xml+="<pageno value=\""+pageNo+"\">"+pageNo+"</pageno>";
				for(var i=0; i < clen.length; i++){
					var v=clen[i].getAttribute("qcaption");
					xml+="<caption displayname=\""+v+"\" clinicalquestionid=\""+clen[i].getAttribute("id")+"\" groupid=\""+clen[i].getAttribute("groupid")+"\" questionno=\""+clen[i].getAttribute("questionNo")+"\" gwid=\""+clen[i].getAttribute("gwid")+"\" parentdirty=\""+clen[i].getAttribute("parentdirty")+"\"><choices>";
					var cnt=0;
						if(clen[i].getAttribute("selectbox")>0){
							for(var k=0; k < clen[i].getAttribute("selectbox"); k++){
								for(var j=0; j < optelem.length; j++){
								var l=optelem[j].getAttribute("value");
								if(optelem[j].getAttribute("isdirty")=="true"){
									cnt++;
										xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+clen[i].getAttribute("id")+"\" clinicalelementtype=\""+optelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+optelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+optelem[j].getAttribute("optionvalue")+"\" isactive=\""+optelem[j].checked+"\" gwid=\""+optelem[j].getAttribute("gwid")+"\" isdirty=\"true\" clinicaldatatype=\""+optelem[j].getAttribute("clinicaldatatype")+"\" count=\""+cnt+"\" isboolean=\""+optelem[j].getAttribute("isboolean")+"\"></choice>";
									}else{
										xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+clen[i].getAttribute("id")+"\" clinicalelementtype=\""+optelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+optelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+optelem[j].getAttribute("optionvalue")+"\" isactive=\""+optelem[j].checked+"\" gwid=\""+optelem[j].getAttribute("gwid")+"\" isdirty=\"true\" clinicaldatatype=\""+optelem[j].getAttribute("clinicaldatatype")+"\" count=\"1\" isboolean=\""+optelem[j].getAttribute("isboolean")+"\"></choice>";
									}
								}
							}
						}else{
							for(var j=0; j < inputelem.length; j++){
								if(clen[i].getAttribute("id")==inputelem[j].getAttribute("id")){
									var type=0;
										if(inputelem[j].type=='checkbox'){
 											type=1;
 										}else if(inputelem[j].type=='text'){
 											type=2;
 										}else{
 											type=0;
 										}
 										
						switch(parseInt(type)){
						case 1:{
  								var l=inputelem[j].getAttribute("caption");
  								if(inputelem[j].getAttribute("isdirty")=="true"){
									cnt++;
									if(inputelem[j].getAttribute("selectbox")==-1){
										xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+inputelem[j].getAttribute("id")+"\" clinicalelementtype=\""+inputelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+inputelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+inputelem[j].getAttribute("value")+"\" isactive=\""+inputelem[j].checked+"\" gwid=\""+inputelem[j].getAttribute("gwid")+"\" isdirty=\""+inputelem[j].getAttribute("isdirty")+"\" clinicaldatatype=\""+inputelem[j].getAttribute("clinicaldatatype")+"\" count=\""+cnt+"\" type=\""+type+"\" isboolean=\""+inputelem[j].getAttribute("isboolean")+"\"></choice>";
									}else{
										xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+inputelem[j].getAttribute("id")+"\" clinicalelementtype=\""+inputelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+inputelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+inputelem[j].getAttribute("optionvalue")+"\" isactive=\""+inputelem[j].checked+"\" gwid=\""+inputelem[j].getAttribute("gwid")+"\" isdirty=\""+inputelem[j].getAttribute("isdirty")+"\" clinicaldatatype=\""+inputelem[j].getAttribute("clinicaldatatype")+"\" count=\""+cnt+"\" type=\""+type+"\" isboolean=\""+inputelem[j].getAttribute("isboolean")+"\"></choice>";
									}
								}else{
									if(inputelem[j].getAttribute("selectbox")==-1){
										xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+inputelem[j].getAttribute("id")+"\" clinicalelementtype=\""+inputelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+inputelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+inputelem[j].getAttribute("value")+"\" isactive=\""+inputelem[j].checked+"\" gwid=\""+inputelem[j].getAttribute("gwid")+"\" isdirty=\""+inputelem[j].getAttribute("isdirty")+"\" clinicaldatatype=\""+inputelem[j].getAttribute("clinicaldatatype")+"\" count=\"1\" type=\""+type+"\" isboolean=\""+inputelem[j].getAttribute("isboolean")+"\"></choice>";
									}else{
										xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+inputelem[j].getAttribute("id")+"\" clinicalelementtype=\""+inputelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+inputelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+inputelem[j].getAttribute("optionvalue")+"\" isactive=\""+inputelem[j].checked+"\" gwid=\""+inputelem[j].getAttribute("gwid")+"\" isdirty=\""+inputelem[j].getAttribute("isdirty")+"\" clinicaldatatype=\""+inputelem[j].getAttribute("clinicaldatatype")+"\" count=\"1\" type=\""+type+"\" isboolean=\""+inputelem[j].getAttribute("isboolean")+"\"></choice>";
									}
								}
								break;
								}
						case 2:{
								var l=inputelem[j].getAttribute("caption");
								if(inputelem[j].getAttribute("isdirty")=="true"){
									cnt++;
									xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+inputelem[j].getAttribute("id")+"\" clinicalelementtype=\""+inputelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+inputelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+inputelem[j].getAttribute("value")+"\" isactive=\"\" gwid=\""+inputelem[j].getAttribute("gwid")+"\" isdirty=\""+inputelem[j].getAttribute("isdirty")+"\" clinicaldatatype=\""+inputelem[j].getAttribute("clinicaldatatype")+"\" count=\""+cnt+"\" type=\""+type+"\" isboolean=\""+inputelem[j].getAttribute("isboolean")+"\"></choice>";
								}else{
									xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+inputelem[j].getAttribute("id")+"\" clinicalelementtype=\""+inputelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+inputelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+inputelem[j].getAttribute("value")+"\" isactive=\"\" gwid=\""+inputelem[j].getAttribute("gwid")+"\" isdirty=\""+inputelem[j].getAttribute("isdirty")+"\" clinicaldatatype=\""+inputelem[j].getAttribute("clinicaldatatype")+"\" count=\"1\" type=\""+type+"\" isboolean=\""+inputelem[j].getAttribute("isboolean")+"\"></choice>";
								}
								break;
								}
						default:{
								var l=inputelem[j].getAttribute("caption");
								if(inputelem[j].getAttribute("isdirty")=="true"){
									cnt++;
									xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+inputelem[j].getAttribute("id")+"\" clinicalelementtype=\""+inputelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+inputelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+inputelem[j].getAttribute("value")+"\" isactive=\"\" gwid=\""+inputelem[j].getAttribute("gwid")+"\" isdirty=\""+inputelem[j].getAttribute("isdirty")+"\" clinicaldatatype=\""+inputelem[j].getAttribute("clinicaldatatype")+"\" count=\""+cnt+"\" type=\""+type+"\" isboolean=\""+inputelem[j].getAttribute("isboolean")+"\"></choice>";
								}else{
									xml+="<choice displayname=\""+l+"\" clinicaloptionid=\""+inputelem[j].getAttribute("id")+"\" clinicalelementtype=\""+inputelem[j].getAttribute("clinicalelementtype")+"\" selectbox=\""+inputelem[j].getAttribute("selectbox")+"\" clinicaloptionvalue=\""+inputelem[j].getAttribute("value")+"\" isactive=\"\" gwid=\""+inputelem[j].getAttribute("gwid")+"\" isdirty=\""+inputelem[j].getAttribute("isdirty")+"\" clinicaldatatype=\""+inputelem[j].getAttribute("clinicaldatatype")+"\" count=\"1\" type=\""+type+"\" isboolean=\""+inputelem[j].getAttribute("isboolean")+"\"></choice>";
								}
								break;
								}
							}
					   }
					}
				}
			xml+="</choices>";
			xml+="</caption>";
		}
			xml+="</question>";
			return xml;
}
]]>
</script>
<body style="background:white;">
<!-- target="_self" -->
<form method="post" align="left" id="clinicalIntake" name="clinicalIntake" action="" target="_self"> 
<script language = "text/javascript"  src="myjs.js"></script>
		<table cellspacing="0" cellpadding="2" border="0"  style="width:100%;solid rgb(227, 233, 255); margin-top: 0px;">
			<tr>
					<table width="100%" cellspacing="0" cellpadding="0" border="0" style="margin-top: 6px;">
					<tr>
					<td class="formlabel1" style="position:absolute;left:10px;top:10px;">
					<font size="4" face="arial" color="blue" ><xsl:value-of select="questionnaire/question/header/groupName"></xsl:value-of></font>
					</td>
					<xsl:variable name="totalpages"><xsl:value-of select="questionnaire/question/noofpages"></xsl:value-of></xsl:variable>
					<xsl:choose>
					<xsl:when test="$totalpages!=1">
					<td align="right" style="border-left: 0px none;"><font face="arial">
											<xsl:call-template name="pagenavigation">
												<xsl:with-param name="direction">first</xsl:with-param>
												<xsl:with-param name="pageno" select="questionnaire/question/pageno"/>
												<xsl:with-param name="totalnoofpages" select="questionnaire/question/noofpages"/>
												<xsl:with-param name="xml" select="xml"/>
											</xsl:call-template>
										&#160;&#160;
										
											<xsl:call-template name="pagenavigation">
												<xsl:with-param name="direction">prev</xsl:with-param>
												<xsl:with-param name="pageno" select="questionnaire/question/pageno"/>
												<xsl:with-param name="totalnoofpages" select="questionnaire/question/noofpages"/>
												<xsl:with-param name="xml" select="xml"/>
											</xsl:call-template>
										&#160;&#160;
										
											<xsl:variable name="pageNo"><xsl:value-of select="questionnaire/question/pageno"/></xsl:variable>
											&#160;&#160;
											<xsl:value-of select="$pageNo + 1"/>&#160;of&#160;<xsl:value-of select="questionnaire/question/noofpages"/>
											&#160;&#160;
										
										&#160;&#160;
											<xsl:call-template name="pagenavigation">
												<xsl:with-param name="direction">next</xsl:with-param>
												<xsl:with-param name="pageno" select="questionnaire/question/pageno"/>
												<xsl:with-param name="totalnoofpages" select="questionnaire/question/noofpages"/>
												<xsl:with-param name="xml" select="xml"/>
											</xsl:call-template>
										&#160;&#160;
											<xsl:call-template name="pagenavigation">
												<xsl:with-param name="direction">last</xsl:with-param>
												<xsl:with-param name="pageno" select="questionnaire/question/pageno"/>
												<xsl:with-param name="totalnoofpages" select="questionnaire/question/noofpages"/>
												<xsl:with-param name="xml" select="xml"/>
											</xsl:call-template>
										&#160;&#160;
										</font>
						</td>
					</xsl:when>
					<xsl:otherwise>
					<td align="right" style="border-left: 0px none;">
					&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
					&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;		
					</td>
					</xsl:otherwise>
					</xsl:choose>
						<td>
					<xsl:variable name="totalpages"><xsl:value-of select="questionnaire/question/noofpages"></xsl:value-of></xsl:variable>
					<xsl:choose>
					<xsl:when test="($totalpages) &gt; 0">
					<xsl:element name="input">
					<xsl:attribute name="type">submit</xsl:attribute>
					<xsl:attribute name="value">Submit</xsl:attribute>
					<xsl:attribute name="style">
                      <xsl:value-of select="'color:#272727;background:#D4D5D5;border-radius:2px;width:75px;height:30px;cursor:pointer;border:1px solid #878787;'"/>
                    </xsl:attribute>
					<xsl:attribute name="onclick">submitForm();</xsl:attribute>
					</xsl:element>
					</xsl:when>
					<xsl:otherwise>
					<xsl:element name="input">
					<xsl:attribute name="type">submit</xsl:attribute>
					<xsl:attribute name="style">
                      <xsl:value-of select="'color:#272727;background:#D4D5D5;border-radius:2px;width:75px;height:30px;cursor:pointer;border:1px solid #878787;'"/>
                    </xsl:attribute>
					<xsl:attribute name="value">Submit</xsl:attribute>
					<xsl:attribute name="onclick">submitForm();changePage('<xsl:value-of select="$totalpages"/>');</xsl:attribute>
					</xsl:element>
					</xsl:otherwise>
					</xsl:choose>
					</td>
			        </tr>
					</table>
			</tr>	
		<xsl:choose>
		<xsl:when test="questionnaire/question/tnoofquestions &gt; 0">
		<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0" style="font-family: Arial;font-size: 11pt;">
	          <xsl:for-each select="questionnaire/question/caption">
						<xsl:variable name="caption"><xsl:value-of select="questionnaire/question/caption"/></xsl:variable>
						<xsl:variable name="quesId"><xsl:value-of select="@clinicalintakeid"/></xsl:variable>
							<tr height="50px">
								<td colspan="4" width="100%">
								<xsl:element name="label">
								<xsl:attribute name="type">label</xsl:attribute>
								<xsl:attribute name="name">captionname</xsl:attribute>
								<xsl:attribute name="id"><xsl:value-of select="@clinicalintakeid"/></xsl:attribute>
								<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
								<xsl:attribute name="qcaption"><xsl:value-of select="@displayname"/></xsl:attribute>
								<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>
								<xsl:attribute name="groupid"><xsl:value-of select="@groupid"/></xsl:attribute>
								<xsl:attribute name="questionNo"><xsl:value-of select="@questionNo"/></xsl:attribute>
								<xsl:attribute name="parentdirty"><xsl:text>false</xsl:text></xsl:attribute>							
								<font face="arial"><b><xsl:value-of select="@questionNo"/>&#160;<xsl:value-of select="@displayname"/></b></font>
								</xsl:element>
								</td>
							</tr>
							<div id="did" style="display:none;background:LightSteelBlue;width:80%;height:80%;left:150px;top:150px;">
							<xsl:attribute name="parentdirty"><xsl:text>false</xsl:text></xsl:attribute>				
							</div>
							<tr>
							<xsl:if test="@selectbox='1'">
							<td width="25%" nowrap="nowrap"><font face="arial">
							<select>
								<xsl:for-each select="choices/choice">							
									<xsl:element name="option">
										<xsl:attribute name="value"><xsl:value-of select="@displayname"/></xsl:attribute>
										<xsl:attribute name="id"><xsl:value-of select="@clinicaloptionid"/></xsl:attribute>
										<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
										<xsl:attribute name="clinicalelementtype"><xsl:value-of select="@clinicalelementtype"/></xsl:attribute>
										<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>		
										<xsl:attribute name="selected" />	
										<xsl:attribute name="optionvalue"><xsl:value-of select="@clinicaloptionvalue"/></xsl:attribute>
										<xsl:attribute name="clinicaldatatype"><xsl:value-of select="@clinicaldatatype"/></xsl:attribute>	
										<xsl:attribute name="count"><xsl:value-of select="@count"/></xsl:attribute>
										<xsl:attribute name="parentdirty"><xsl:text>false</xsl:text></xsl:attribute>				
										<xsl:value-of select="text()"/>
										<xsl:value-of select="@displayname"/>
									</xsl:element>
							</xsl:for-each>
							</select>
							</font></td>
							</xsl:if>
								<xsl:for-each select="choices/choice">
									<xsl:if test="@clinicalelementtype='5' and @selectbox=''">
										<td width="25%" nowrap="nowrap"><font face="arial">
											<xsl:variable name="dataselect">
												<xsl:value-of select="normalize-space(@isactive)"/>
											</xsl:variable>
											<xsl:choose>
											<xsl:when test="(contains($dataselect,'true') or contains($dataselect,'t'))">										
											<xsl:element name="input">
												<xsl:attribute name="type">checkbox</xsl:attribute>
												<xsl:attribute name="name"><xsl:value-of select="$quesId"/></xsl:attribute>
												<xsl:attribute name="id"><xsl:value-of select="@clinicaloptionid"/></xsl:attribute>
												<xsl:attribute name="toggleId"><xsl:value-of select="@clinicaloptionid"/>_<xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
												<xsl:attribute name="optionsize"><xsl:value-of select="@optionsize"/></xsl:attribute>
												<xsl:attribute name="checked"></xsl:attribute>
												<xsl:attribute name="clinicalelementtype"><xsl:value-of select="@clinicalelementtype"/></xsl:attribute>
												<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>	
												<xsl:attribute name="optionvalue"><xsl:value-of select="@clinicaloptionvalue"/></xsl:attribute>
												<xsl:attribute name="isactive"><xsl:value-of select="@isactive"/></xsl:attribute>										
												<xsl:attribute name="caption"><xsl:value-of select="@displayname"/></xsl:attribute>
												<xsl:attribute name="count"><xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="clinicaldatatype"><xsl:value-of select="@clinicaldatatype"/></xsl:attribute>
												<xsl:attribute name="isboolean"><xsl:value-of select="@isboolean"/></xsl:attribute>
												<xsl:attribute name="isdirty"><xsl:text>false</xsl:text></xsl:attribute>
												<xsl:attribute name="onclick">makeDirty(this);</xsl:attribute>
												<xsl:value-of select="@displayname"/>
											</xsl:element>
											</xsl:when>
											<xsl:otherwise>
												<xsl:element name="input">
												<xsl:attribute name="type">checkbox</xsl:attribute>
												<xsl:attribute name="name"><xsl:value-of select="$quesId"/></xsl:attribute>
												<xsl:attribute name="id"><xsl:value-of select="@clinicaloptionid"/></xsl:attribute>
												<xsl:attribute name="toggleId"><xsl:value-of select="@clinicaloptionid"/>_<xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
												<xsl:attribute name="optionsize"><xsl:value-of select="@optionsize"/></xsl:attribute>
												<xsl:attribute name="clinicalelementtype"><xsl:value-of select="@clinicalelementtype"/></xsl:attribute>
												<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>		
												<xsl:attribute name="optionvalue"><xsl:value-of select="@clinicaloptionvalue"/></xsl:attribute>
												<xsl:attribute name="clinicaldatatype"><xsl:value-of select="@clinicaldatatype"/></xsl:attribute>
												<xsl:attribute name="isactive">f</xsl:attribute>				
												<xsl:attribute name="count"><xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="caption"><xsl:value-of select="@displayname"/></xsl:attribute>
												<xsl:attribute name="isboolean"><xsl:value-of select="@isboolean"/></xsl:attribute>
												<xsl:attribute name="isdirty"><xsl:text>false</xsl:text></xsl:attribute>
												<xsl:attribute name="onclick">makeDirty(this);</xsl:attribute>
												<xsl:value-of select="@displayname"/>
											</xsl:element>
											</xsl:otherwise>
											</xsl:choose>
										</font></td>								
										
										<xsl:variable name="count">
											<xsl:if test="@count!=''">
												<xsl:value-of select="@count"/>
											</xsl:if>
										</xsl:variable>
										<xsl:if test="$count!='' and ($count mod 4) = '0'"><tr height="8px"><td></td></tr></xsl:if>
									</xsl:if>
									<xsl:if test="@clinicalelementtype='4' and @selectbox=''">
										<td width="25%" ><font face="arial">
										<xsl:variable name="dataselect">
												<xsl:value-of select="normalize-space(@isactive)"/>
											</xsl:variable>
											<xsl:choose>
											<xsl:when test="(contains($dataselect,'true') or contains($dataselect,'t'))">			
											<xsl:element name="input">
												<xsl:attribute name="type">checkbox</xsl:attribute>
												<xsl:attribute name="name"><xsl:value-of select="$quesId"/></xsl:attribute>
												<xsl:attribute name="id"><xsl:value-of select="@clinicaloptionid"/></xsl:attribute>
												<xsl:attribute name="toggleId"><xsl:value-of select="@clinicaloptionid"/>_<xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
												<xsl:attribute name="optionsize"><xsl:value-of select="@optionsize"/></xsl:attribute>
												<xsl:attribute name="checked"></xsl:attribute>
												<xsl:attribute name="clinicalelementtype"><xsl:value-of select="@clinicalelementtype"/></xsl:attribute>
												<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>
												<xsl:attribute name="optionvalue"><xsl:value-of select="@clinicaloptionvalue"/></xsl:attribute>
												<xsl:attribute name="clinicaldatatype"><xsl:value-of select="@clinicaldatatype"/></xsl:attribute>
												<xsl:attribute name="isactive"><xsl:value-of select="@isactive"/></xsl:attribute>	
												<xsl:attribute name="count"><xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="caption"><xsl:value-of select="@displayname"/></xsl:attribute>
												<xsl:attribute name="isboolean"><xsl:value-of select="@isboolean"/></xsl:attribute>
												<xsl:attribute name="isdirty"><xsl:text>false</xsl:text></xsl:attribute>
												<xsl:attribute name="onclick">makeDirty(this);executeToggle(this);</xsl:attribute>
												<xsl:value-of select="@displayname"/>
											</xsl:element>
											</xsl:when>
											<xsl:otherwise>
											<xsl:element name="input">
												<xsl:attribute name="type">checkbox</xsl:attribute>
												<xsl:attribute name="name"><xsl:value-of select="$quesId"/></xsl:attribute>
												<xsl:attribute name="id"><xsl:value-of select="@clinicaloptionid"/></xsl:attribute>
												<xsl:attribute name="toggleId"><xsl:value-of select="@clinicaloptionid"/>_<xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
												<xsl:attribute name="optionsize"><xsl:value-of select="@optionsize"/></xsl:attribute>
												<xsl:attribute name="clinicalelementtype"><xsl:value-of select="@clinicalelementtype"/></xsl:attribute>
												<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>
												<xsl:attribute name="optionvalue"><xsl:value-of select="@clinicaloptionvalue"/></xsl:attribute>
												<xsl:attribute name="clinicaldatatype"><xsl:value-of select="@clinicaldatatype"/></xsl:attribute>
												<xsl:attribute name="isactive">f</xsl:attribute>	
												<xsl:attribute name="count"><xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="caption"><xsl:value-of select="@displayname"/></xsl:attribute>
												<xsl:attribute name="isboolean"><xsl:value-of select="@isboolean"/></xsl:attribute>
												<xsl:attribute name="isdirty"><xsl:text>false</xsl:text></xsl:attribute>
												<xsl:attribute name="onclick">makeDirty(this);executeToggle(this);</xsl:attribute>
												<xsl:value-of select="@displayname"/>
											</xsl:element>
											</xsl:otherwise>
											</xsl:choose>
										</font></td>
										<xsl:variable name="count">
											<xsl:if test="@count!=''">
												<xsl:value-of select="@count"/>
											</xsl:if>
										</xsl:variable>
										<xsl:if test="$count!='' and ($count mod 4) = '0'"><tr height="8px"><td></td></tr></xsl:if>
									</xsl:if>
									<xsl:if test="@clinicalelementtype='3' and @selectbox=''">
										<td width="25%" nowrap="nowrap"><font face="arial">
										<xsl:variable name="dataselect">
												<xsl:value-of select="normalize-space(@isactive)"/>
											</xsl:variable>
											<xsl:choose>
											<xsl:when test="(contains($dataselect,'true') or contains($dataselect,'t'))">			
											<xsl:element name="input">
												<xsl:attribute name="type">checkbox</xsl:attribute>
												<xsl:attribute name="name"><xsl:value-of select="$quesId"/></xsl:attribute>
												<xsl:attribute name="id"><xsl:value-of select="@clinicaloptionid"/></xsl:attribute>
												<xsl:attribute name="toggleId"><xsl:value-of select="@clinicaloptionid"/>_<xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
												<xsl:attribute name="optionsize"><xsl:value-of select="@optionsize"/></xsl:attribute>
												<xsl:attribute name="checked"></xsl:attribute>
												<xsl:attribute name="clinicalelementtype"><xsl:value-of select="@clinicalelementtype"/></xsl:attribute>
												<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>
												<xsl:attribute name="optionvalue"><xsl:value-of select="@clinicaloptionvalue"/></xsl:attribute>
												<xsl:attribute name="isactive"><xsl:value-of select="@isactive"/></xsl:attribute>	
												<xsl:attribute name="clinicaldatatype"><xsl:value-of select="@clinicaldatatype"/></xsl:attribute>
												<xsl:attribute name="count"><xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="caption"><xsl:value-of select="@displayname"/></xsl:attribute>
												<xsl:attribute name="isboolean"><xsl:value-of select="@isboolean"/></xsl:attribute>
												<xsl:attribute name="isdirty"><xsl:text>false</xsl:text></xsl:attribute>
												<xsl:attribute name="onclick">makeDirty(this);executeToggle(this);</xsl:attribute>
												<xsl:value-of select="@displayname"/>
											</xsl:element>
											</xsl:when>
											<xsl:otherwise>
											<xsl:element name="input">
												<xsl:attribute name="type">checkbox</xsl:attribute>
												<xsl:attribute name="name"><xsl:value-of select="$quesId"/></xsl:attribute>
												<xsl:attribute name="id"><xsl:value-of select="@clinicaloptionid"/></xsl:attribute>
												<xsl:attribute name="toggleId"><xsl:value-of select="@clinicaloptionid"/>_<xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
												<xsl:attribute name="optionsize"><xsl:value-of select="@optionsize"/></xsl:attribute>
												<xsl:attribute name="clinicalelementtype"><xsl:value-of select="@clinicalelementtype"/></xsl:attribute>
												<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>
												<xsl:attribute name="optionvalue"><xsl:value-of select="@clinicaloptionvalue"/></xsl:attribute>
												<xsl:attribute name="clinicaldatatype"><xsl:value-of select="@clinicaldatatype"/></xsl:attribute>
												<xsl:attribute name="isactive">f</xsl:attribute>	
												<xsl:attribute name="count"><xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="caption"><xsl:value-of select="@displayname"/></xsl:attribute>
												<xsl:attribute name="isboolean"><xsl:value-of select="@isboolean"/></xsl:attribute>
												<xsl:attribute name="isdirty"><xsl:text>false</xsl:text></xsl:attribute>
												<xsl:attribute name="onclick">makeDirty(this);executeToggle(this);</xsl:attribute>
												<xsl:value-of select="@displayname"/>
											</xsl:element>
											</xsl:otherwise>
											</xsl:choose>
										</font></td>
										<xsl:variable name="count">
											<xsl:if test="@count!=''">
												<xsl:value-of select="@count"/>
											</xsl:if>
										</xsl:variable>
										<xsl:if test="$count!='' and ($count mod 4) = '0'"><tr height="8px"><td></td></tr></xsl:if>
									</xsl:if>
									<xsl:if test="@selectbox='-1'">
										<td width="25%" nowrap="nowrap"><font face="arial">
										   <xsl:value-of select="@displayname"/>&#160;
											<xsl:element name="input">
												<xsl:attribute name="type">text</xsl:attribute>
												<xsl:attribute name="style">width:100px;height:23px;</xsl:attribute>
												<xsl:attribute name="name">optionname</xsl:attribute>
												<xsl:attribute name="id"><xsl:value-of select="@clinicaloptionid"/></xsl:attribute>
												<xsl:attribute name="gwid"><xsl:value-of select="@gwid"/></xsl:attribute>
												<xsl:attribute name="optionsize"><xsl:value-of select="@optionsize"/></xsl:attribute>
												<xsl:attribute name="clinicalelementtype"><xsl:value-of select="@clinicalelementtype"/></xsl:attribute>
												<xsl:attribute name="selectbox"><xsl:value-of select="@selectbox"/></xsl:attribute>
												<xsl:attribute name="value"><xsl:value-of select="@clinicaloptionvalue"/></xsl:attribute>
												<xsl:attribute name="textvalue"><xsl:value-of select="text()"/></xsl:attribute>
												<xsl:attribute name="clinicaldatatype"><xsl:value-of select="@clinicaldatatype"/></xsl:attribute>
												<xsl:attribute name="count"><xsl:value-of select="@count"/></xsl:attribute>
												<xsl:attribute name="caption"><xsl:value-of select="@displayname"/></xsl:attribute>
												<xsl:attribute name="isboolean"><xsl:value-of select="@isboolean"/></xsl:attribute>
												<xsl:attribute name="isdirty"><xsl:text>false</xsl:text></xsl:attribute>
												<xsl:attribute name="onblur">makeDirty(this);</xsl:attribute>
										</xsl:element>
										</font></td>
										<xsl:variable name="count">
											<xsl:if test="@count!=''">
												<xsl:value-of select="@count"/>
											</xsl:if>
										</xsl:variable>
										<xsl:if test="$count!='' and ($count mod 4) = '0'"><tr height="8px"><td></td></tr></xsl:if>
									</xsl:if>
								</xsl:for-each>
							
							</tr>
					</xsl:for-each>	
				</table>
		</xsl:when>
		<xsl:otherwise>
		<tbody>
			<tr style="font-family: Arial;font-size: 12pt;">
				<td colspan="5" align="center">
					No records found.
				</td>
			</tr>
		</tbody>
		</xsl:otherwise>
		</xsl:choose>
		<tr>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" style="margin-top: 6px;">
				<tr>
				<td></td>
				<xsl:variable name="totalpages"><xsl:value-of select="questionnaire/question/noofpages"></xsl:value-of></xsl:variable>
					<xsl:choose>
					<xsl:when test="$totalpages!=1">
					<td align="right" style="border-left: 0px none;">
									  
											<xsl:call-template name="pagenavigation">
												<xsl:with-param name="direction">first</xsl:with-param>
												<xsl:with-param name="pageno" select="questionnaire/question/pageno"/>
												<xsl:with-param name="totalnoofpages" select="questionnaire/question/noofpages"/>
												<xsl:with-param name="xml" select="xml"/>
											</xsl:call-template>
										&#160;&#160;
										
											<xsl:call-template name="pagenavigation">
												<xsl:with-param name="direction">prev</xsl:with-param>
												<xsl:with-param name="pageno" select="questionnaire/question/pageno"/>
												<xsl:with-param name="totalnoofpages" select="questionnaire/question/noofpages"/>
												<xsl:with-param name="xml" select="xml"/>
											</xsl:call-template>
										&#160;&#160;
										
											<xsl:variable name="pageNo"><xsl:value-of select="questionnaire/question/pageno"/></xsl:variable>
									
											&#160;&#160;
											<xsl:value-of select="$pageNo + 1"/>&#160;of&#160;<xsl:value-of select="questionnaire/question/noofpages"/>
											&#160;&#160;
										
										&#160;&#160;
											<xsl:call-template name="pagenavigation">
												<xsl:with-param name="direction">next</xsl:with-param>
												<xsl:with-param name="pageno" select="questionnaire/question/pageno"/>
												<xsl:with-param name="totalnoofpages" select="questionnaire/question/noofpages"/>
												<xsl:with-param name="xml" select="xml"/>
											</xsl:call-template>
										&#160;&#160;
										
											<xsl:call-template name="pagenavigation">
												<xsl:with-param name="direction">last</xsl:with-param>
												<xsl:with-param name="pageno" select="questionnaire/question/pageno"/>
												<xsl:with-param name="totalnoofpages" select="questionnaire/question/noofpages"/>
												<xsl:with-param name="xml" select="xml"/>
											</xsl:call-template>
										&#160;&#160;
							</td>
					</xsl:when>
					<xsl:otherwise>
					<td align="right" style="border-left: 0px none;">
					&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
					&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
					</td>
					</xsl:otherwise>
					</xsl:choose>
					<td>
					<xsl:choose>
					<xsl:when test="($totalpages) &gt; 0">
					<xsl:element name="input">
					<xsl:attribute name="type">submit</xsl:attribute>
					<xsl:attribute name="style">
                      <xsl:value-of select="'color:#272727;background:#D4D5D5;border-radius:2px;width:75px;height:30px;cursor:pointer;border:1px solid #878787;'"/>
                    </xsl:attribute>
					<xsl:attribute name="value">Submit</xsl:attribute>
					<xsl:attribute name="onclick">submitForm();</xsl:attribute>
					</xsl:element>
					</xsl:when>
					<xsl:otherwise>
					<xsl:element name="input">
					<xsl:attribute name="type">submit</xsl:attribute>
					<xsl:attribute name="style">
                      <xsl:value-of select="'color:#272727;background:#D4D5D5;border-radius:2px;width:75px;height:30px;cursor:pointer;border:1px solid #878787;'"/>
                    </xsl:attribute>
					<xsl:attribute name="value">Submit</xsl:attribute>
					<xsl:attribute name="onclick">submitForm();changePage('<xsl:value-of select="$totalpages"/>');</xsl:attribute>
					</xsl:element>
					</xsl:otherwise>
					</xsl:choose>
			<!--  	<input type="submit" value="Submit" name="fld_prim_scriptDisable" id="fld_prim_scriptDisable" class="gl_btns" onclick="submitForm()"></input>-->	
					</td>
					</tr>
					</table>
					</tr>	
	</table>
	<table> 
 		<tr> 
			<td><div id="dirtydiv"></div></td> 
		</tr> 
	</table>
	<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">hiddenxml</xsl:attribute>
			<xsl:attribute name="id">hiddenxml</xsl:attribute>
			<xsl:attribute name="value"></xsl:attribute>
		</xsl:element>
		<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">patientid</xsl:attribute>
			<xsl:attribute name="id">patientid</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="questionnaire/question/header/patientid"></xsl:value-of></xsl:attribute>
		</xsl:element>
		<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">requestId</xsl:attribute>
			<xsl:attribute name="id">requestId</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="questionnaire/question/header/requestid"></xsl:value-of></xsl:attribute>
		</xsl:element>
		<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">mode</xsl:attribute>
			<xsl:attribute name="id">mode</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="questionnaire/question/header/mode"></xsl:value-of></xsl:attribute>
		</xsl:element>
		<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">groupId</xsl:attribute>
			<xsl:attribute name="id">groupId</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="questionnaire/question/header/groupId"></xsl:value-of></xsl:attribute>
		</xsl:element>
		
		<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">totalNoOfQuestions</xsl:attribute>
			<xsl:attribute name="id">totalNoOfQuestions</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="questionnaire/question/tnoofquestions"></xsl:value-of></xsl:attribute>
		</xsl:element>
	
		<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">totalNoofPages</xsl:attribute>
			<xsl:attribute name="id">totalNoofPages</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="questionnaire/question/noofpages"></xsl:value-of></xsl:attribute>
		</xsl:element>
		<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">pageNo</xsl:attribute>
			<xsl:attribute name="id">pageNo</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="questionnaire/question/pageno"></xsl:value-of></xsl:attribute>
		</xsl:element>
	</form>
		<xsl:element name="input">
			<xsl:attribute name="type">hidden</xsl:attribute>
			<xsl:attribute name="name">totalrecordcount</xsl:attribute>
			<xsl:attribute name="id">totalrecordcount</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="questionnaire/question/tnoofquestions"/></xsl:attribute>
		</xsl:element>
		</body>
		</html>
</xsl:template>
	<xsl:template name="pagenavigation">
		<xsl:param name="direction"/>
		<xsl:param name="pageno"/>
		<xsl:param name="totalnoofpages"/>
		<xsl:param name="xml"/>
		<xsl:element name="img">
		<xsl:attribute name="border">0</xsl:attribute>
			<xsl:if test="$direction = 'first'">
				<xsl:attribute name="src">../../../images/btn_first.gif</xsl:attribute>
				<xsl:attribute name="alt">First</xsl:attribute>
				<xsl:choose>
					<xsl:when test="$pageno &gt; 0">
						<xsl:attribute name="onclick">changePage(0,document.clinicalIntake)</xsl:attribute>
						<xsl:attribute name="style">cursor:pointer;</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">cursor:pointer;opacity: 0.5;</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
			<xsl:if test="$direction = 'prev'">
				<xsl:attribute name="src">../../../images/btn_prev.gif</xsl:attribute>
				<xsl:attribute name="alt">Previous</xsl:attribute>
				<xsl:choose>
					<xsl:when test="$pageno &gt; 0">
						<xsl:attribute name="onclick">changePage(<xsl:value-of select="$pageno -1"></xsl:value-of>,document.clinicalIntake)</xsl:attribute>
						<xsl:attribute name="style">cursor:pointer;</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">cursor:pointer;opacity: 0.5;</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
			<xsl:if test="$direction = 'next'">
				<xsl:attribute name="src">../../../images/btn_next.gif</xsl:attribute>
				<xsl:attribute name="alt">Next</xsl:attribute>
				<xsl:choose>
					<xsl:when test="(( ($pageno + 1) &lt; $totalnoofpages))">
						<xsl:attribute name="onclick">changePage(<xsl:value-of select="$pageno + 1"></xsl:value-of>,document.clinicalIntake)</xsl:attribute>
						<xsl:attribute name="style">cursor:pointer;</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">cursor:pointer;opacity: 0.5;</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
			<xsl:if test="$direction = 'last'">
				<xsl:attribute name="src">../../../images/btn_last.gif</xsl:attribute>
				<xsl:attribute name="alt">Last</xsl:attribute>
				<xsl:choose>
					<xsl:when test="(( ($pageno + 1) &lt; $totalnoofpages))">
						<xsl:attribute name="onclick">changePage(<xsl:value-of select="$totalnoofpages - 1"></xsl:value-of>,document.clinicalIntake)</xsl:attribute>
						<xsl:attribute name="style">cursor:pointer;</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">cursor:pointer;opacity: 0.5;</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
		</xsl:element>
	</xsl:template>
	
	
		<xsl:template name="pagenavigation1">
	
		<xsl:param name="direction"/>
		<xsl:param name="pageno"/>
		<xsl:param name="totalnoofpages"/>
		<xsl:param name="xml"/>
		
		<xsl:element name="img">
		<xsl:attribute name="border">0</xsl:attribute>
			<xsl:if test="$direction = 'first'">
				<xsl:attribute name="src"></xsl:attribute>
				<xsl:attribute name="alt">First</xsl:attribute>
				<xsl:choose>
					<xsl:when test="$pageno &gt; 0">
						<xsl:attribute name="onclick">changePage(0,document.clinicalIntake)</xsl:attribute>
						<xsl:attribute name="style">cursor:pointer;</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">cursor:pointer;opacity: 0.5;</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
			<xsl:if test="$direction = 'prev'">
				<xsl:attribute name="type">button</xsl:attribute>
				<xsl:attribute name="alt">Previous</xsl:attribute>
				<xsl:choose>
					<xsl:when test="$pageno &gt; 0">
						<xsl:attribute name="onclick">changePage(<xsl:value-of select="$pageno -1"></xsl:value-of>,document.clinicalIntake)</xsl:attribute>
						<xsl:attribute name="style">cursor:pointer;</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">cursor:pointer;opacity: 0.5;</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
			<xsl:if test="$direction = 'next'">
				<xsl:attribute name="src"></xsl:attribute>
				<xsl:attribute name="alt">Next</xsl:attribute>
				<xsl:choose>
					<xsl:when test="(( ($pageno + 1) &lt; $totalnoofpages))">
						<xsl:attribute name="onclick">changePage(<xsl:value-of select="$pageno + 1"></xsl:value-of>,document.clinicalIntake)</xsl:attribute>
						<xsl:attribute name="style">cursor:pointer;</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">cursor:pointer;opacity: 0.5;</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
			<xsl:if test="$direction = 'last'">
				<xsl:attribute name="src"></xsl:attribute>
				<xsl:attribute name="alt">Last</xsl:attribute>
				<xsl:choose>
					<xsl:when test="(( ($pageno + 1) &lt; $totalnoofpages))">
						<xsl:attribute name="onclick">changePage(<xsl:value-of select="$totalnoofpages - 1"></xsl:value-of>,document.clinicalIntake)</xsl:attribute>
						<xsl:attribute name="style">cursor:pointer;</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="style">cursor:pointer;opacity: 0.5;</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:if>
		</xsl:element>
	</xsl:template>
	
</xsl:stylesheet>
