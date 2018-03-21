package com.glenwood.glaceemr.server.application.controllers;

import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet.CoumadinFlowSheetService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@Transactional
@RequestMapping(value = "/CoumadinFlowSheet")
public class CoumadinFlowSheetController {

	@Autowired
	CoumadinFlowSheetService coumadinflowsheetService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	/**
	 * Method to retrieve Episode details
	 * 
	 * @param patientId
	 * @param episodeId
	 * @param type
	 * @return episodedata
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEpisodes", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getEpisodes(
			@RequestParam(value = "patientId", required = true) Integer patientId,
			@RequestParam(value = "episodeId", required = false, defaultValue = "-1") Integer episodeId,
			@RequestParam(value = "type", required = false, defaultValue = "-1") Integer type)
			throws Exception {
//		List<PatientEpisode> episodedata = coumadinflowsheetService.getEpisodes(patientId, episodeId, type);
		
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(coumadinflowsheetService.getEpisodes(patientId, episodeId, type));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CoumadinFlowSheet, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded episodes", sessionMap.getUserID(), request.getRemoteAddr(), patientId,"-1", LogUserType.USER_LOGIN, "", "");
		//auditTrailSaveService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.CoumadinFlowSheet,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded episodes",sessionMap.getUserID(),"127.0.0.1",request.getRemoteAddr(),patientId,-1,-1,AuditLogConstants.CoumadinFlowSheet,request);
		return emrResponseBean;
		
	}

	/**
	 * Method to save Episode details
	 * 
	 * @param coumadin_xml
	 * @param patientId
	 * @param userId
	 * @param timestamp
	 * @param episodeId
	 * @param episodeEndDate
	 * @param episodeStartDate
	 * @param isEpisodeDirty
	 * @param INRGoalRangeStart
	 * @param INRGoalRangeEnd
	 * save episode details
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveEpisodes", method = RequestMethod.GET)
	@ResponseBody
	public void saveEpisodes(
			@RequestParam(value = "patientId", required = true) Integer patientId,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "episodeId", required = false, defaultValue = "-1") Integer episodeId,
			@RequestParam(value = "episodeStartDate", required = true) String episodeStartDate,
			@RequestParam(value = "chartId", required = true) Integer chartId,
			@RequestParam(value = "episodeEndDate", required = false, defaultValue = "") String episodeEndDate,
			@RequestParam(value = "episodeStatus", required = true) Integer episodeStatus,
			@RequestParam(value = "isEpisodeDirty", required = false, defaultValue = "-1") String isEpisodeDirty,
			@RequestParam(value = "INRGoalRangeStart", required = false, defaultValue = "") String INRGoalRangeStart,
			@RequestParam(value = "INRGoalRangeEnd", required = false, defaultValue = "") String INRGoalRangeEnd,
			@RequestParam(value = "coumadin_xml", required = false, defaultValue = "-1") String coumadin_xml)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
				now.getTime());
		if (!episodeStartDate.equals("") && episodeEndDate.equals("")) {
			SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date date1 = originalFormat.parse(episodeStartDate);
			coumadinflowsheetService.saveEpisodes(patientId, userId, episodeId,
					date1, episodeStatus, currentTimestamp, isEpisodeDirty);
		}

		else if (!episodeStartDate.equals("") && (!episodeEndDate.equals(""))) {
			SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date date2 = originalFormat.parse(episodeEndDate);
			java.util.Date date1 = originalFormat.parse(episodeStartDate);
			coumadinflowsheetService.saveEpisodeswithEndDate(patientId, userId,
					episodeId, date1, date2, episodeStatus, currentTimestamp,
					isEpisodeDirty);

		}
		if (episodeId != -1) {
			coumadinflowsheetService.updateINRGoal(patientId, episodeId,
					INRGoalRangeStart, INRGoalRangeEnd);
		}
		if (!coumadin_xml.equals("-1")) {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentFactory
					.newDocumentBuilder();
			String xml = URLDecoder.decode(coumadin_xml, "UTF-8");
			InputSource source = new InputSource(new ByteArrayInputStream(
					xml.getBytes()));
			Document document = (Document) documentBuilder.parse(source);
			coumadinflowsheetService.saveIndication(document, episodeId,
					userId, currentTimestamp, patientId);
			coumadinflowsheetService.saveLog(document, chartId, patientId,
					userId, episodeId, currentTimestamp);
		}
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CoumadinFlowSheet, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Successfully saved episode details", userId, request.getRemoteAddr(), patientId,"-1", LogUserType.USER_LOGIN, "", "");
		//auditTrailSaveService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.CoumadinFlowSheet,AuditLogConstants.CREATEORUPDATE,1,AuditLogConstants.SUCCESS,"Successfully saved episode details",userId,"127.0.0.1",request.getRemoteAddr(),patientId,chartId,-1,AuditLogConstants.CoumadinFlowSheet,request);
	}
	/**
	 * Method to get Log informations details
	 * @param chartId
	 * @param episodeId
	 * returns logInfo
	 */
	@RequestMapping(value = "/getLogInfo", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getLogInfo(
			@RequestParam(value = "chartId", required = true) Integer chartId,
			@RequestParam(value = "episodeId", required = true) Integer episodeId)
			throws Exception {
//		List<LogInfoBean> logInfo = coumadinflowsheetService.getLogInfo(chartId, episodeId);
		
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(coumadinflowsheetService.getLogInfo(chartId, episodeId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CoumadinFlowSheet, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded log details", sessionMap.getUserID(), request.getRemoteAddr(), -1,"-1", LogUserType.USER_LOGIN, "", "");
		//auditTrailSaveService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.CoumadinFlowSheet,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded log details",sessionMap.getUserID(),"127.0.0.1",request.getRemoteAddr(),-1,chartId,-1,AuditLogConstants.CoumadinFlowSheet,request);

        return emrResponseBean;
	}
	/**
	 * Method to get PTINR values
	 * @param chartId
	 * @param date
	 * returns ptINR
	 */
	@RequestMapping(value = "/getPTINR", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPTINR(
			@RequestParam(value = "chartId", required = false,defaultValue = "-1") Integer chartId,
			@RequestParam(value = "date", required = false,defaultValue = "-1") String date)
			throws Exception {
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date1 = originalFormat.parse(date);
//		List<LabDetailsBean> ptINR = coumadinflowsheetService.getPTINR(chartId,date1);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(coumadinflowsheetService.getPTINR(chartId,date1));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CoumadinFlowSheet, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded PTINR details", sessionMap.getUserID(), request.getRemoteAddr(), -1,"-1", LogUserType.USER_LOGIN, "", "");
		//auditTrailSaveService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.CoumadinFlowSheet,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded PTINR details",sessionMap.getUserID(),"127.0.0.1",request.getRemoteAddr(),-1,chartId,-1,AuditLogConstants.CoumadinFlowSheet,request);

        return emrResponseBean;
	}
	/**
	 * Method to get RecentINR details
	 * @param episodeId
	 * @param chartId
	 * @param patientId
	 * returns recentINR
	 */
	
	@RequestMapping(value = "/getRecentINR", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getRecentINR(
			@RequestParam(value = "episodeId", required = false, defaultValue = "-1") Integer episodeId,
			@RequestParam(value = "chartId", required = false,defaultValue = "-1") Integer chartId,
			@RequestParam(value = "patientId", required = false,defaultValue = "-1") Integer patientId)
			throws Exception {
//		List<RecentINRBean> recentINR = coumadinflowsheetService.getRecentINR(episodeId, chartId, patientId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(coumadinflowsheetService.getRecentINR(episodeId, chartId, patientId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CoumadinFlowSheet, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded Recent INR details", sessionMap.getUserID(), request.getRemoteAddr(), -1,"-1", LogUserType.USER_LOGIN, "", "");
		//auditTrailSaveService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.CoumadinFlowSheet,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded Recent INR details",sessionMap.getUserID(),"127.0.0.1",request.getRemoteAddr(),patientId,chartId,-1,AuditLogConstants.CoumadinFlowSheet,request);

        return emrResponseBean;
	}
	/**
	 * Method to get indication details
	 * @param episodeId
	 * returns indications
	 */
	@RequestMapping(value = "/getIndication", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getIndication(
			@RequestParam(value = "episodeId", required = true) Integer episodeId)
			throws Exception {
//		List<WarfarinIndication> indications = coumadinflowsheetService.getIndication(episodeId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(coumadinflowsheetService.getIndication(episodeId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CoumadinFlowSheet, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded Indication details", sessionMap.getUserID(), request.getRemoteAddr(), -1,"-1", LogUserType.USER_LOGIN, "", "");
	//	auditTrailSaveService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.CoumadinFlowSheet,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded Indication details",sessionMap.getUserID(),"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.CoumadinFlowSheet,request);
        return emrResponseBean;
	}
	/**
	 * Method to check for configurations
	 * return true/false
	 */
	@RequestMapping(value = "/checkConfiguration", method = RequestMethod.GET)
	@ResponseBody
	public Boolean checkConfiguration() throws Exception {
		Boolean configure = coumadinflowsheetService.checkConfiguration();
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CoumadinFlowSheet, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully Configured Coumadin", sessionMap.getUserID(), request.getRemoteAddr(), -1,"-1", LogUserType.USER_LOGIN, "", "");
		return configure;
	}
	/**
	 * Method to create Coumadin reminders
	 * @param patientId
	 * @param episodeId
	 * @param userId
	 * @param date
	 * save reminder details
	 */
	@RequestMapping(value = "/createCoumadinReminders", method = RequestMethod.GET)
	@ResponseBody
	public void createCoumadinReminders(
			@RequestParam(value = "patientId", required = false,defaultValue = "-1") Integer patientId,
			@RequestParam(value = "episodeId", required = false,defaultValue = "-1") Integer episodeId,
			@RequestParam(value = "userId", required = false,defaultValue = "-1") Integer userId,
			@RequestParam(value = "date", required = true) String date)
			throws Exception {
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date createdDate = originalFormat.parse(date);
		coumadinflowsheetService.createCoumadinReminders(patientId, episodeId,
				userId, createdDate);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CoumadinFlowSheet, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully created coumadin remainders", sessionMap.getUserID(), request.getRemoteAddr(), patientId,"-1", LogUserType.USER_LOGIN, "", "");
		//auditTrailSaveService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.CoumadinFlowSheet,AuditLogConstants.CREATED,1,AuditLogConstants.SUCCESS,"Successfully created coumadin remainders",userId,"127.0.0.1",request.getRemoteAddr(),patientId,-1,-1,AuditLogConstants.CoumadinFlowSheet,request);
	}

}