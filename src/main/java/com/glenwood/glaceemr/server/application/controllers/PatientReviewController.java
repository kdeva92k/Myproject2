package com.glenwood.glaceemr.server.application.controllers;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.PatientReviewedDetails;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.patientreview.PatientReviewService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@RequestMapping(value="/user/PatientReview")
public class PatientReviewController {
	
	@Autowired
	PatientReviewService patientReviewService; 

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(PatientReviewController.class);

	/**
	 * Get reviewed information
	 * @param userId
	 * @param chartId
	 * @return
	 */
	@RequestMapping(value="/getReviewInfo", method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean getReviewInfo(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Getting review information");
			emrResponseBean.setData(patientReviewService.getReviewInfo(chartId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.HISTORY, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Review information viewed @chartId="+chartId, sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.HISTORY, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Review information viewed @chartId="+chartId, sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}

	/**
	 * Save reviewed information
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/saveReviewInfo", method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean getReviewInfo(
			@RequestParam(value="userId", required=false, defaultValue="-1") Integer userId,
			@RequestParam(value="patientId", required=false, defaultValue="-1") Integer patientId,
			@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId,
			@RequestParam(value="encounterId", required=false, defaultValue="-1") Integer encounterId){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Saving review information");

			PatientReviewedDetails patientReviewed = new PatientReviewedDetails();
			patientReviewed.setPatientReviewedDetailsBy(userId);
			patientReviewed.setPatientReviewedDetailsOn(new Timestamp(new Date().getTime()));
			patientReviewed.setPatientReviewedDetailsChartid(chartId);
			patientReviewed.setPatientReviewedDetailsPatientid(patientId);
			patientReviewed.setPatientReviewedDetailsEncounterid(encounterId);
			patientReviewService.saveReviewInfo(patientReviewed);

			emrResponseBean.setData(patientReviewService.getReviewInfo(chartId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.HISTORY, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Review information viewed @encounterId="+encounterId, sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.HISTORY, LogActionType.CREATE, 1, Log_Outcome.EXCEPTION, "Review information viewed @encounterId="+encounterId, sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean; 
	}
}