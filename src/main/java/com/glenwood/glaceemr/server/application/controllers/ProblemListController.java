package com.glenwood.glaceemr.server.application.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.problemlist.ProblemListService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for Problem list module
 * @author software
 *
 */
@RestController
@RequestMapping(value="/user/ProblemList")
public class ProblemListController {
	
	@Autowired
	ProblemListService problemListService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	
	private Logger logger = Logger.getLogger(ProblemListController.class);
	
	/**
	 * Method to get list of active problems
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
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Patient active problems viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
		catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Patient active problems viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Method to retrieve list of inactive and resolved problems
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/InactiveProblems",method = RequestMethod.GET)
	public EMRResponseBean inactiveProblems(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId) throws Exception {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Fetching inactive and resolved problems");
			logger.error("Fetching inactive and resolved problems");		
			List<ProblemList> currentProblems = problemListService.getInactiveProblems(patientId);
			emrResponseBean.setData(currentProblems);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Patient Inactive problems viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Patient Inactive problems viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Method to fetch edit page data
	 * @param patientId
	 * @param problemId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/EditFetch",method = RequestMethod.GET)
	public EMRResponseBean loadEditData(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId,
			@RequestParam(value="problemId", required=false, defaultValue="") Integer problemId) throws Exception {
		logger.debug("Fetching data corresponds to edit dx");
		logger.error("Fetching data corresponds to edit dx");
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			List<ProblemList> editData = problemListService.getEditData(patientId,problemId);
			emrResponseBean.setData(editData);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Patient problem details viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "problemId="+problemId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Patient problem details viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "problemId="+problemId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Method to edit a problem
	 * @param patientId
	 * @param userId
	 * @param saveData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/EditSave",method = RequestMethod.GET)
	public EMRResponseBean saveEditData(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId,
			@RequestParam(value="userId", required=false, defaultValue="") Integer userId,
			@RequestParam(value="saveData", required=false, defaultValue="") String saveData) throws Exception {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Updating problem list data");
			logger.error("Updating problem list data");		
			problemListService.editDataSave(patientId,userId,saveData);
			emrResponseBean.setData("edited");
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Patient problem list updated", userId, request.getRemoteAddr(), patientId, "saveData="+saveData, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Patient problem list updated", userId, request.getRemoteAddr(), patientId, "saveData="+saveData, LogUserType.USER_LOGIN, "", "");
		}
	    return emrResponseBean;
	}
	
	/**
	 * Method to delete a problem from problem list
	 * @param patientId
	 * @param deleteData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DeleteSave",method = RequestMethod.GET)
	public EMRResponseBean deleteDxData(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId,
			@RequestParam(value="deleteData", required=false, defaultValue="") String deleteData) throws Exception {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{ 
			logger.debug("Problem list delete");
			logger.error("Problem list delete");
			problemListService.deleteDataSave(patientId,deleteData);
			emrResponseBean.setData("deleted");
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Patient problems deleted", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "deleteData="+deleteData, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Patient problems deleted", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "deleteData="+deleteData, LogUserType.USER_LOGIN, "", "");
		}
	    return emrResponseBean;
	}
	
	
	/**
	 * Method to delete a problem from problem list while importing in Assessment
	 * @param patientId
	 * @param deleteData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DeleteSaveFetch",method = RequestMethod.GET)
	public EMRResponseBean deleteDxDataAndFetch(
			@RequestParam(value="patientId", required=false, defaultValue="") Integer patientId,
			@RequestParam(value="deleteData", required=false, defaultValue="") String deleteData) throws Exception {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Problem list delete");
			logger.error("Problem list delete");
			emrResponseBean.setData(problemListService.deleteDataSaveFetch(patientId,deleteData));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Patient problem deleted and viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "deleteData="+deleteData, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ASSESSMENT, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Patient problem deleted and viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "deleteData="+deleteData, LogUserType.USER_LOGIN, "", "");
		}
	    return emrResponseBean;
	}
	
}
