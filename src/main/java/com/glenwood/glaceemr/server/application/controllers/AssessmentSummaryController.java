package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.assessment.AssessmentSummaryService;
import com.glenwood.glaceemr.server.application.services.chart.problemlist.ProblemListService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;
 

/**
 * Controller for Assessments module
 * @author software
 *
 */
@RestController
@RequestMapping(value="/user/Assessment.Action")
public class AssessmentSummaryController {
	
	@Autowired
	AssessmentSummaryService assessmentService;
	
	@Autowired
	ProblemListService problemListService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(AssessmentSummaryController.class);
	
	/**
	 * Method to retrieve current visit problems
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/CurrentDiagnosis",method = RequestMethod.GET)
	public EMRResponseBean getCurrentDiagnosis(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId,
			@RequestParam(value="encounterId", required=false, defaultValue="") Integer encounterId) throws Exception {
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("getting current diagnosis");
			logger.error("getting current diagnosis");
			List<PatientAssessments> currentProblems = assessmentService.getCurrentDiagnosis(patientId,encounterId);
			emrResponseBean.setData(currentProblems);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Assessments viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Assessments viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Method to save current diagnosis data
	 * @param assessListobj
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/SaveCodes",method = RequestMethod.GET)
	public EMRResponseBean saveCurrentDiagnosis(
			@RequestParam(value="assessListobj", required=false, defaultValue="") JSONObject assessListobj) throws Exception {
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
		logger.debug("getting current diagnosis");
		logger.error("getting current diagnosis");
		Boolean success = assessmentService.saveDiagnosis(assessListobj);
		emrResponseBean.setData(success);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Assessments saved", sessionMap.getUserID(), request.getRemoteAddr(), -1, "-1", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.CREATEORUPDATE, 1, Log_Outcome.EXCEPTION, "Assessments saved", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;		
	}
	
	
	/**
	 * Method to fetch active problems data
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ActiveProblems",method = RequestMethod.GET)
	public EMRResponseBean activeProblems(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId) throws Exception {
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Fetching active problems");
			logger.error("Fetching active problems");		
			List<ProblemList> currentProblems = problemListService.getActiveProblems(patientId);
			emrResponseBean.setData(currentProblems);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Patient active problems viewed in Assessments", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");			
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Patient active problems viewed in Assessments", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Method to fetch edit page data
	 * @param patientId
	 * @param encounterId
	 * @param dxCode
	 * @param problemId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/EditFetch",method = RequestMethod.GET)
	public EMRResponseBean loadEditData(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId,
			@RequestParam(value="encounterId", required=false, defaultValue="") Integer encounterId,
			@RequestParam(value="dxCode", required=false, defaultValue="") String dxCode,
			@RequestParam(value="problemId", required=false, defaultValue="") Integer problemId) throws Exception {
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Fetching data corresponds to edit dx");
			logger.error("Fetching data corresponds to edit dx");

			List<PatientAssessments> editData = assessmentService.getEditData(patientId,encounterId,dxCode,problemId);
			emrResponseBean.setData(editData);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Patient Dx '"+dxCode+"' details viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|problemId="+problemId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Patient Dx '"+dxCode+"' details viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|problemId="+problemId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}

	/**
	 * Method to send current encounter assessments to problem list 
	 * @param patientId
	 * @param encounterId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ProblemlistData",method = RequestMethod.GET)
	public EMRResponseBean loadProblemlist(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId,
			@RequestParam(value="encounterId", required=false, defaultValue="") Integer encounterId,
			@RequestParam(value="userId", required=false, defaultValue="") Integer userId) throws Exception {
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("load data corresponds to current dx");		
			String message = assessmentService.moveToProblemList(patientId,encounterId,userId);
			emrResponseBean.setData(message);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "All Patient Assessments moved to his problem list", userId, request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "All Patient Assessments moved to his problem list", userId, request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
			
		}
		return emrResponseBean;
	}

}
