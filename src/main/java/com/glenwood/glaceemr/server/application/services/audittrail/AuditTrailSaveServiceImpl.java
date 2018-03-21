package com.glenwood.glaceemr.server.application.services.audittrail;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.AuditTrail;
import com.glenwood.glaceemr.server.application.repositories.AuditTrailRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.SessionMap;

@Service
public class AuditTrailSaveServiceImpl implements AuditTrailSaveService {

	@Resource
	AuditTrailRepository auditTrailRepository;
	@Autowired
	SessionMap sessionMap;
	@Autowired
	HttpServletRequest httpServletRequest;

	@PersistenceContext
	private EntityManager entityManager;

	public Timestamp findCurrentTimeStamp() {

		Query query = entityManager.createNativeQuery(" select current_timestamp from audittrail_log limit 1");
		return (Timestamp) query.getSingleResult();
	}

	public int LogEvent(LogType LogType, LogModuleType LogModuleType, LogActionType LogActionType, int Parent_id, Log_Outcome Log_Outcome, String Description, int User_Id, String ClientIP, int patientId, String relevantIds, LogUserType LogUserType,
			String PHI_Description, String Raw_Data) {
		AuditTrail eventLog = new AuditTrail();
		eventLog.setLogType(LogType.getValue());
		eventLog.setModule(LogModuleType.getValue());
		eventLog.setDesc(Description);
		eventLog.setAction(LogActionType.getValue());
		eventLog.setClientIp(ClientIP);
		eventLog.setUserId(User_Id);
		eventLog.setPatientId(patientId);
		eventLog.setOutcome(Log_Outcome.getValue());
		eventLog.setParentID(Parent_id);

		if (relevantIds.length() == 0) {
			relevantIds = "N/A";
		}
		eventLog.setRelevantIds(relevantIds);

		eventLog.setLoginType(LogUserType.getValue());
		eventLog.setPhiDescription(PHI_Description);
		String userAgentStatic = httpServletRequest.getHeader("User-Agent");
		if (Raw_Data.equalsIgnoreCase("")) {
			Raw_Data = userAgentStatic;
			if (userAgentStatic.equalsIgnoreCase(""))
				Raw_Data = "N/A";
		}

		eventLog.setRawData(Raw_Data);
		if(httpServletRequest.getSession()!=null)
			eventLog.setSessionId(httpServletRequest.getSession(false).getId());
		else
			eventLog.setSessionId("");
		eventLog.setServerIp(getServerIPAddress());
		String Server_Hostname = httpServletRequest.getRemoteHost();
		eventLog.setServerHostname(Server_Hostname);
		Timestamp logOn = findCurrentTimeStamp();
		eventLog.setLogOn(logOn);
		eventLog.setBackUpData("");
		String dataCheckSum = calculateChecksum(User_Id, Parent_id, logOn, LogModuleType.getValue(), LogActionType.getValue(), Log_Outcome.getValue(), ClientIP, httpServletRequest.getSession(false).getId(), relevantIds, Description);

		eventLog.setCheckSum(SHA1ForString(dataCheckSum));
		eventLog.setReferenceUrl(httpServletRequest.getHeader("Referer"));
		eventLog.setRequestedUrl(httpServletRequest.getRequestURL().toString());
		auditTrailRepository.saveAndFlush(eventLog);
		return eventLog.getLogId();
	}

	@Override
	public int logDataBackup(int logId, ArrayList<Object> data) {
		AuditTrail eventLog = null;

		if (logId == -1) {
			eventLog = new AuditTrail();
		} else {
			eventLog = auditTrailRepository.findOne(logId);
		}

		eventLog.setBackUpData(data.toString());

		eventLog = auditTrailRepository.saveAndFlush(eventLog);
		return eventLog.getLogId();
	}

	public int LogEvent(int logId, LogType LogType, LogModuleType LogModuleType, LogActionType LogActionType, int Parent_id, Log_Outcome Log_Outcome, String Description, int User_Id, String ClientIP, int patientId, String relevantIds,
			LogUserType LogUserType, String PHI_Description, String Raw_Data) {
		AuditTrail eventLog = null;

		if (logId == -1) {
			eventLog = new AuditTrail();
		} else {
			eventLog = auditTrailRepository.findOne(logId);
		}

		eventLog.setLogType(LogType.getValue());
		eventLog.setModule(LogModuleType.getValue());
		eventLog.setDesc(Description);
		eventLog.setAction(LogActionType.getValue());
		eventLog.setClientIp(ClientIP);
		eventLog.setUserId(User_Id);
		eventLog.setPatientId(patientId);
		eventLog.setOutcome(Log_Outcome.getValue());
		eventLog.setParentID(Parent_id);

		if (relevantIds.length() == 0) {
			relevantIds = "N/A";
		}
		eventLog.setRelevantIds(relevantIds);

		eventLog.setLoginType(LogUserType.getValue());
		eventLog.setPhiDescription(PHI_Description);
		String userAgentStatic = httpServletRequest.getHeader("User-Agent");
		if (Raw_Data.equalsIgnoreCase("")) {
			Raw_Data = userAgentStatic;
			if (userAgentStatic.equalsIgnoreCase(""))
				Raw_Data = "N/A";
		}

		eventLog.setRawData(Raw_Data);
		eventLog.setSessionId(httpServletRequest.getSession(false).getId());
		eventLog.setServerIp(getServerIPAddress());

		String Server_Hostname = httpServletRequest.getRemoteHost();
		eventLog.setServerHostname(Server_Hostname);
		Timestamp logOn = findCurrentTimeStamp();
		eventLog.setLogOn(logOn);
		eventLog.setBackUpData("");
		String dataCheckSum = calculateChecksum(User_Id, Parent_id, logOn, LogModuleType.getValue(), LogActionType.getValue(), Log_Outcome.getValue(), ClientIP, httpServletRequest.getSession(false).getId(), relevantIds, Description);

		eventLog.setCheckSum(SHA1ForString(dataCheckSum));
		eventLog.setReferenceUrl(httpServletRequest.getHeader("Referer"));
		eventLog.setRequestedUrl(httpServletRequest.getRequestURL().toString());
		eventLog = auditTrailRepository.saveAndFlush(eventLog);
		return eventLog.getLogId();
	}

	public int logException(int logId, LogType LogType, LogModuleType LogModuleType, LogActionType LogActionType, int Parent_id, Log_Outcome Log_Outcome, String Description, int User_Id, LogUserType LogUserType, Exception e) {

		AuditTrail eventLog = null;

		if (logId == -1) {
			eventLog = new AuditTrail();
		} else {
			eventLog = auditTrailRepository.findOne(logId);
		}

		eventLog.setLogType(LogType.getValue());
		eventLog.setModule(LogModuleType.getValue());
		eventLog.setDesc(Description);
		eventLog.setAction(LogActionType.getValue());
		String ClientIP;
		ClientIP = httpServletRequest.getRemoteAddr();
		eventLog.setClientIp(ClientIP);
		eventLog.setUserId(User_Id);
		eventLog.setPatientId(-1);
		eventLog.setOutcome(Log_Outcome.getValue());// default exception
		eventLog.setParentID(Parent_id);
		String relevantIds = "";
		if (relevantIds.length() == 0) {
			relevantIds = "N/A";
		}
		eventLog.setRelevantIds(relevantIds);
		eventLog.setLoginType(LogUserType.getValue());
		eventLog.setRawData(getException(e));
		eventLog.setSessionId(httpServletRequest.getSession(false).getId());
		eventLog.setServerIp(getServerIPAddress());
		String Server_Hostname = httpServletRequest.getRemoteHost();
		eventLog.setServerHostname(Server_Hostname);
		Timestamp logOn = findCurrentTimeStamp();
		eventLog.setLogOn(logOn);
		eventLog.setBackUpData("");
		String dataCheckSum = calculateChecksum(User_Id, Parent_id, logOn, LogModuleType.getValue(), LogActionType.getValue(), Log_Outcome.getValue(), ClientIP, httpServletRequest.getSession(false).getId(), relevantIds, Description);

		eventLog.setCheckSum(SHA1ForString(dataCheckSum));
		eventLog.setReferenceUrl(httpServletRequest.getHeader("Referer"));
		eventLog.setRequestedUrl(httpServletRequest.getRequestURL().toString());
		eventLog = auditTrailRepository.saveAndFlush(eventLog);
		return eventLog.getLogId();

	}

	public String getServerIPAddress() {
		String ipAddress = "", netWorkAddress = null;
		try {
			InetAddress localMachine = InetAddress.getLocalHost();
			ipAddress = HUtil.Nz(localMachine.getHostAddress(), "127.0.0.1");
			InetAddress obj = null;
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();

				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					obj = enumIpAddr.nextElement();
					netWorkAddress = obj.getHostAddress();
				}
			}
			ipAddress = HUtil.Nz(netWorkAddress, ipAddress);
		} catch (Exception uhe) {
			uhe.printStackTrace();
		}
		return ipAddress;
	}

	public String SHA1ForString(String message) {
		String out = "";
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-1"); // SHA-512

			md.update(message.getBytes());
			byte[] mb = md.digest();
			for (int i = 0; i < mb.length; i++) {
				byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				out += s;
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return out;
	}

	public String getException(Exception e) {
		JSONObject data = null;

		try {
			data = new JSONObject();
			data.put("Message", e.getMessage().toString());

			try {
				data.put("Stack Trace", ExceptionUtils.getStackTrace(e));
			} catch (Exception ex) {
				throw ex;
			}

			data.put("Session Variables", getSessiontVariables());
			data.put("Request Variables", getRequestVariables());
			data.put("Server Variables", getServerVariables());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return data.toString();
	}

	public JSONArray getServerVariables() {
		String[][] variables = { { "AUTH_TYPE", httpServletRequest.getAuthType() }, { "CONTENT_LENGTH", String.valueOf(httpServletRequest.getContentLength()) }, { "CONTENT_TYPE", httpServletRequest.getContentType() },
				{ "DOCUMENT_ROOT", httpServletRequest.getSession().getServletContext().getRealPath("/") }, { "PATH_INFO", httpServletRequest.getPathInfo() }, { "PATH_TRANSLATED", httpServletRequest.getPathTranslated() },
				{ "QUERY_STRING", httpServletRequest.getQueryString() }, { "REMOTE_ADDR", httpServletRequest.getRemoteAddr() }, { "REMOTE_HOST", httpServletRequest.getRemoteHost() }, { "REMOTE_USER", httpServletRequest.getRemoteUser() },
				{ "REQUEST_METHOD", httpServletRequest.getMethod() }, { "SCRIPT_NAME", httpServletRequest.getServletPath() }, { "SERVER_NAME", httpServletRequest.getServerName() },
				{ "SERVER_PORT", String.valueOf(httpServletRequest.getServerPort()) }, { "SERVER_PROTOCOL", httpServletRequest.getProtocol() }, { "SERVER_SOFTWARE", httpServletRequest.getSession().getServletContext().getServerInfo() } };

		JSONObject serVariables = new JSONObject();
		JSONArray serVariablesarr = new JSONArray();
		try {
			for (int i = 0; i < variables.length; i++) {
				String varName = variables[i][0];
				String varValue = variables[i][1];
				if (varValue == null)
					varValue = "Not specified";

				serVariables.put(varName, varValue);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		serVariablesarr.put(serVariables);

		return serVariablesarr;
	}

	public JSONArray getRequestVariables() {
		JSONObject reqVariables = new JSONObject();
		JSONArray reqVariablesarr = new JSONArray();
		Enumeration<?> enames;
		try {
			enames = httpServletRequest.getHeaderNames();
			while (enames.hasMoreElements()) {
				String name = (String) enames.nextElement();
				String value = httpServletRequest.getHeader(name);
				reqVariables.put(name, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		reqVariablesarr.put(reqVariables);

		return reqVariablesarr;
	}

	public JSONArray getSessiontVariables() {
		JSONObject sessVariables = new JSONObject();
		JSONArray sessVariablesarr = new JSONArray();
		Enumeration<?> enames;
		try {
			enames = httpServletRequest.getSession(true).getAttributeNames();
			while (enames.hasMoreElements()) {
				String name = (String) enames.nextElement();
				String value = "" + httpServletRequest.getSession(true).getAttribute(name);
				sessVariables.put(name, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		sessVariablesarr.put(sessVariables);

		return sessVariablesarr;
	}

	private static String calculateChecksum(int user_Id, int patient_Id, Timestamp strDate, int LogModuleType, int LogActionType, String out_Come, String client_IP, String session_Id, String relavant_Id, String description) {
		String message;
		message = new StringBuilder(400).append(user_Id).append(",").append(patient_Id).append(",").append(strDate).append(",").append(LogModuleType).append(",").append(LogActionType).append(",").append(out_Come).append(",").append(client_IP)
				.append(",").append(session_Id).append(",").append(relavant_Id).append(",").append(description).toString();
		String out = "";

		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-1");

			md.update(message.getBytes());
			byte[] mb = md.digest();
			for (byte temp : mb) {
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2)
					s = "0" + s;
				s = s.substring(s.length() - 2);
				out += s;
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return out;
	}

}
