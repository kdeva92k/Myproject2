package com.glenwood.glaceemr.server.application.controllers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.AuditTrail;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailFetchService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.HUtil;

@RestController
@RequestMapping(value = "/user/auditTrail.Action")
public class AuditTrailController {

	@Autowired
	EMRResponseBean auditResponseBean;
	@Autowired
	AuditTrailFetchService auditTrailFetchService;

	@Resource
	EmployeeProfileRepository empProfileRepo;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public EMRResponseBean searchEventLog(@RequestParam(value = "data") String data,
			@RequestParam(value = "currentpage") int currentPage, @RequestParam(value = "pagesize") int pageSize)
			throws Exception {
		auditResponseBean = new EMRResponseBean();
		JSONObject dataObj = new JSONObject(data);
		int userId = Integer.parseInt((String) HUtil.nz(dataObj.get("userId"), "-1"));
		int patientid = (int) HUtil.nz(dataObj.get("patientid"), -1);
		String parentModule = (String) HUtil.nz(dataObj.get("parentModule"), "-1");
		String subModule = (String) HUtil.nz(dataObj.get("subModule"), "-1");
		String outcome = (String) HUtil.nz(dataObj.get("outcome"), "-1");
		String desc = (String) HUtil.nz(dataObj.get("desc"), "-1");
		String startDate = (String) HUtil.nz(dataObj.get("startDate"), "-1");
		String action = (String) HUtil.nz(dataObj.get("action"), "-1");
		int parentEvent = (int) HUtil.nz(dataObj.get("parentEvent"), -1);
		String sessionId = (String) HUtil.nz(dataObj.get("sessionId"), "-1");
		String clientIp = (String) HUtil.nz(dataObj.get("clientIp"), "-1");
		String sortProperty = (String) HUtil.nz(dataObj.get("sortProperty"), "");
		String order = (String) HUtil.nz(dataObj.get("order"), "");
		int logId = (int) HUtil.nz(dataObj.get("logId"), -1);
		if (!startDate.equals("-1")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date sDate = format.parse(startDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startDate = newFormat.format(sDate);
		}
		String endDate = (String) HUtil.nz(dataObj.get("endDate"), "-1");
		if (!endDate.equals("-1")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date eDate = format.parse(endDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endDate = newFormat.format(eDate);
		}
		Iterable<AuditTrail> auditLogList = auditTrailFetchService.getSearchResult(userId, parentModule, subModule, outcome, desc,
				startDate, endDate, action, parentEvent, patientid, sessionId, clientIp, logId, sortProperty, order, currentPage, pageSize);
		auditResponseBean.setData(auditLogList);
		auditResponseBean.setSuccess(true);
		return auditResponseBean;
	}

	@RequestMapping(value = "/exportCsvCount", method = RequestMethod.GET)
	public @ResponseBody String exportCsvCount(@RequestParam(value = "data") String data, HttpServletRequest request,
			HttpServletResponse newResponse) throws Exception {
		JSONObject dataObj = new JSONObject(data);
		int userId = Integer.parseInt((String) HUtil.nz(dataObj.get("userId"), "-1"));
		int patientid = (int) HUtil.nz(dataObj.get("patientid"), -1);
		String parentModule = (String) HUtil.nz(dataObj.get("parentModule"), "-1");
		String subModule = (String) HUtil.nz(dataObj.get("subModule"), "-1");
		String outcome = (String) HUtil.nz(dataObj.get("outcome"), "-1");
		String desc = (String) HUtil.nz(dataObj.get("desc"), "-1");
		String startDate = (String) HUtil.nz(dataObj.get("startDate"), "-1");
		String action = (String) HUtil.nz(dataObj.get("action"), "-1");
		int parentEvent = (int) HUtil.nz(dataObj.get("parentEvent"), "-1");
		String sessionId = (String) HUtil.nz(dataObj.get("sessionId"), "-1");
		String clientIp = (String) HUtil.nz(dataObj.get("clientIp"), "-1");
		int logId = (int) HUtil.nz(dataObj.get("logId"), "-1");

		if (!startDate.equals("-1")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date sDate = format.parse(startDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startDate = newFormat.format(sDate);
		}
		String endDate = (String) HUtil.nz(dataObj.get("endDate"), "-1");
		if (!endDate.equals("-1")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date eDate = format.parse(endDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endDate = newFormat.format(eDate);
		}

		String auditLogCount = auditTrailFetchService.generateCsvCount(userId, parentModule, subModule, outcome, desc, startDate,
				endDate, action, parentEvent, patientid, sessionId, clientIp, logId, newResponse);
		return auditLogCount;
	}

	@RequestMapping(value = "/exportCsv", method = RequestMethod.GET)
	public @ResponseBody void exportCsv1(@RequestParam(value = "data") String data, HttpServletRequest request,
			HttpServletResponse newResponse) throws Exception {
		JSONObject dataObj = new JSONObject(data);
		int userId = Integer.parseInt((String) HUtil.nz(dataObj.get("userId"), "-1"));
		int patientid = (int) HUtil.nz(dataObj.get("patientid"), -1);
		String parentModule = (String) HUtil.nz(dataObj.get("parentModule"), "-1");
		String subModule = (String) HUtil.nz(dataObj.get("subModule"), "-1");
		String outcome = (String) HUtil.nz(dataObj.get("outcome"), "-1");
		String desc = (String) HUtil.nz(dataObj.get("desc"), "-1");
		String startDate = (String) HUtil.nz(dataObj.get("startDate"), "-1");
		String endDate = (String) HUtil.nz(dataObj.get("endDate"), "-1");
		String action = (String) HUtil.nz(dataObj.get("action"), "-1");
		int parentEvent = (int) HUtil.nz(dataObj.get("parentEvent"), "-1");
		String sessionId = (String) HUtil.nz(dataObj.get("sessionId"), "-1");
		String clientIp = (String) HUtil.nz(dataObj.get("clientIp"), "-1");
		int logId = (int) HUtil.nz(dataObj.get("logId"), "-1");
		String sortProperty = (String) HUtil.nz(dataObj.get("sortProperty"), "");
		String order = (String) HUtil.nz(dataObj.get("order"), "");
		int coSign = Integer.parseInt((String) HUtil.nz(dataObj.get("coSign"), "-1"));

		if (!startDate.equals("-1")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date sDate = format.parse(startDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startDate = newFormat.format(sDate);
		}
		if (!endDate.equals("-1")) {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date eDate = format.parse(endDate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endDate = newFormat.format(eDate);
		}

		Iterable<AuditTrail> auditLogList = auditTrailFetchService.generateCsv(userId, parentModule, subModule, outcome, desc, startDate,
				endDate, action, parentEvent, patientid, sessionId, clientIp, logId, sortProperty, order, newResponse);

		SimpleDateFormat monthDayYearformatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		BufferedWriter writer = new BufferedWriter(newResponse.getWriter());

		try {

			writer.write("ID");
			writer.write("	");
			writer.write("Parent ID");
			writer.write("	");
			writer.write("Log On");
			writer.write("	");
			writer.write("User Name");
			writer.write("	");
			writer.write("Account No");
			writer.write("	");
			writer.write("Module Name");
			writer.write("	");
			writer.write("Action");
			writer.write("	");
			writer.write("Outcome");
			writer.write("	");
			writer.write("Client IP");
			writer.write("	");
			writer.write("Description");
			writer.write("	");
			writer.write("Session ID");
			writer.write("	");
			writer.write("Server IP");
			writer.write("	");
			writer.write("Server Host Name");
			writer.write("	");
			writer.write("Request URL");
			writer.write("	");
			writer.write("Reference URL");
			if (coSign != -1 && coSign == 1) {
				writer.write("	");
				writer.write("Patient Information");
				writer.write("	");
				writer.write("Relevant IDs");
				writer.write("	");
				writer.write("Raw Data");
				writer.write("	");
				writer.write("Backup Data");
				writer.write("	");
				writer.write("PHI Description");
			}
			writer.newLine();

			Iterator<AuditTrail> list = auditLogList.iterator();

			while (list.hasNext()) {
				AuditTrail eventLog = (AuditTrail) list.next();

				writer.write(String.valueOf(eventLog.getLogId()));
				writer.write("	");
				String parentId = "";
				try {
					parentId = String.valueOf(eventLog.getParentID());
					if (parentId.equals("") || parentId.equals("0") || parentId.equals("-1") || parentId == null) {
						parentId = "N/A";
					}
				} catch (Exception e) {
					parentId = "N/A";
				}
				writer.write(parentId);
				writer.write("	");
				writer.write(monthDayYearformatter.format(eventLog.getLogOn()));
				writer.write("	");
				String userName = "";
				try {
					userName = empProfileRepo.findOne(eventLog.getUserId()).getEmpProfileFullname();
				} catch (Exception e) {
					userName = "N/A";
				}
				writer.write(userName);
				writer.write("	");
				String patientId = "";
				try {
					patientId = String.valueOf(eventLog.getPatientId());
					if (patientId.equals("") || patientId.equals("0") || patientId.equals("-1") || patientId == null) {
						patientId = "N/A";
					}
				} catch (Exception e) {
					patientId = "N/A";
				}
				writer.write(patientId);
				writer.write("	");
				writer.write(LogModuleType.nameOf(eventLog.getModule()));
				writer.write("	");
				writer.write(LogActionType.nameOf(eventLog.getAction()));
				writer.write("	");
				writer.write(Log_Outcome.nameOf(eventLog.getOutcome()));
				writer.write("	");
				String clientIP = "";
				try {
					clientIP = eventLog.getClientIp();
					if (clientIP.equals("") || clientIP == null) {
						clientIP = "N/A";
					}
				} catch (Exception e) {
					clientIP = "N/A";
				}
				writer.write(clientIP);
				writer.write("	");
				String Desc = "";
				try {
					Desc = eventLog.getDesc();
					if (Desc.equals("") || Desc == null) {
						Desc = "N/A";
					}
				} catch (Exception e) {
					Desc = "N/A";
				}
				writer.write(Desc);
				writer.write("	");
				String sessionID = "";
				try {
					sessionID = eventLog.getSessionId();
					if (sessionID.equals("") || sessionID == null) {
						sessionID = "N/A";
					}
				} catch (Exception e) {
					sessionID = "N/A";
				}
				writer.write(sessionID);
				writer.write("	");
				String serverIp = "";
				try {
					serverIp = eventLog.getServerIp();
					if (serverIp.equals("") || serverIp == null) {
						serverIp = "N/A";
					}
				} catch (Exception e) {
					serverIp = "N/A";
				}
				writer.write(serverIp);
				writer.write("	");
				String serverHostname = "";
				try {
					serverHostname = eventLog.getServerHostname();
					if (serverHostname.equals("") || serverHostname == null) {
						serverHostname = "N/A";
					}
				} catch (Exception e) {
					serverHostname = "N/A";
				}
				writer.write(serverHostname);
				writer.write("	");
				String reqUrl = "";
				try {
					reqUrl = eventLog.getRequestedUrl();
					if (reqUrl.equals("") || reqUrl == null) {
						reqUrl = "N/A";
					}
				} catch (Exception e) {
					reqUrl = "N/A";
				}
				writer.write(reqUrl);
				writer.write("	");
				String refUrl = "";
				try {
					refUrl = eventLog.getReferenceUrl();
					if (refUrl.equals("") || refUrl == null) {
						refUrl = "N/A";
					}
				} catch (Exception e) {
					refUrl = "N/A";
				}
				writer.write(refUrl);
				if (coSign != -1 && coSign == 1) {
					writer.write("	");
					String patientName = "";
					String patientDOB;
					try {
						patientName = eventLog.getPatientName();
						if (patientName.equals("") || patientName == null) {
							patientName = "N/A";
						}
					} catch (Exception e) {
						patientName = "N/A";
					}
					try {
						patientDOB = eventLog.getDob().toString();
						if (patientDOB.equals("") || patientDOB == null) {
							patientDOB = "N/A";
						}
					} catch (Exception e) {
						patientDOB = "N/A";
					}
					writer.write("Patient Name : " + patientName + ", Patient DOB : " + patientDOB);
					writer.write("	");
					String revIDs = "";
					try {
						revIDs = eventLog.getRelevantIds();
						if (revIDs.equals("") || revIDs == null) {
							revIDs = "N/A";
						}
					} catch (Exception e) {
						revIDs = "N/A";
					}
					writer.write(revIDs);
					writer.write("	");
					String raw = "";
					try {
						raw = eventLog.getRawData();
						if (raw.equals("") || raw == null) {
							raw = "N/A";
						}
					} catch (Exception e) {
						raw = "N/A";
					}
					writer.write(raw);
					writer.write("	");
					String back = "";
					try {
						back = eventLog.getBackUpData();
						if (back.equals("") || back == null) {
							back = "N/A";
						}
					} catch (Exception e) {
						back = "N/A";
					}
					writer.write(back);
					writer.write("	");
					String phi = "";
					try {
						phi = eventLog.getPhiDescription();
						if (phi.equals("") || phi == null) {
							phi = "N/A";
						}
					} catch (Exception e) {
						phi = "N/A";
					}
					writer.write(phi);
				}
				writer.newLine();

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			writer.flush();
			writer.close();
		}

	}

}
