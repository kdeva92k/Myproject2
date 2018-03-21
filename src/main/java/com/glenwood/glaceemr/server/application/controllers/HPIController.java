package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Relavant_Id;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.HPI.HPIService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.google.common.base.Optional;
/**
 * Controller for HPI
 * @author Bhagya Lakshmi
 *
 */

@RestController
@Transactional
@RequestMapping(value="/user/HPIElements.Action")
public class HPIController {
	
	@Autowired
	HPIService HPIService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;

	private Logger logger = Logger.getLogger(HPIController.class);
	
	
	/**
	 * TO get System Details
	 * @param patientId
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/HPISymptomData",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getHPISystemDetails(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="templateId") Integer templateId,
			@RequestParam(value="symptomGWId") String symptomGWId,
			@RequestParam(value="symptomId") String symptomId,
			@RequestParam(value="isFollowUp") String isFollowUp,
			@RequestParam(value="tabId") String tabId,
			@RequestParam(value="clientId") String clientId) throws Exception{

		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		templateId=Integer.parseInt(Optional.fromNullable(templateId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(HPIService.getSymptomData(clientId,patientId,chartId,encounterId,templateId,symptomGWId,symptomId,isFollowUp,tabId).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved HPI System Details", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+templateId+"|symptomId="+symptomId+"|tabId="+tabId+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To delete Symptom details
	 * @param symptomToRemoveGWID
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param isFollowUp
	 * @param templateId
	 * @param isCompleted
	 */
	@RequestMapping(value="/DeleteSymptomDetails",method=RequestMethod.POST)
	@ResponseBody
	public void deleteSymptomDetails(@RequestParam(value="symptomToRemoveGWID") String symptomToRemoveGWID,
			@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="isFollowUp") Integer isFollowUp,
			@RequestParam(value="templateId") Integer templateId,
			@RequestParam(value="isCompleted") Boolean isCompleted){
		String symptomGWId=HUtil.Nz(symptomToRemoveGWID,"-1");
		symptomGWId=symptomGWId.substring(0, 14)+"%";
		HPIService.deleteSymptomDetails(symptomGWId,patientId,encounterId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.DELETE, 0, Log_Outcome.SUCCESS, "successful deletion of HPI Symptom details", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+templateId, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	
	/**
	 * TO get Chief Complaints Data
	 * @param patientId
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/FetchCC",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getHPICC(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId){
		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(HPIService.fetchCCData(patientId,chartId,encounterId).toString().replaceAll("\\\\", ""));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successful retrieval of HPI Chief Complaints Data", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+Log_Relavant_Id.TEMPLATE_ID, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}

	/**
	 * TO get Patient HPI Notes
	 * @param patientId
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/FetchPatientHPINotesData",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientHPINotesData(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId){
		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(HPIService.fetchPatientHPINotesData(patientId,chartId,encounterId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successful retrieval of Patient HPI Notes", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+Log_Relavant_Id.TEMPLATE_ID, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}

	/**
	 * TO fetch default shortcut
	 * @param patientId
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="/FetchDefaultShortcut",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchDefaultShortcutData(@RequestParam(value="mode") Integer mode){
		mode=Integer.parseInt(Optional.fromNullable(mode+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(HPIService.fetchDefaultShortcutData(mode).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successful retrieval of HPI default shortcut", -1, request.getRemoteAddr(), -1, "encounterId="+Log_Relavant_Id.ENCOUNTER_ID+"|templateId="+Log_Relavant_Id.TEMPLATE_ID, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}

	/**
	 * To Fetch Shortcut Data
	 * @param shortcutId
	 * @return
	 */
	//check
	@RequestMapping(value="/FetchShortcutData",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchShortcutData(@RequestParam(value="shortcutId") String shortcutId)
	{
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(HPIService.fetchShortcutData(shortcutId).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successful retrieval of HPI Shortcut Data", -1, request.getRemoteAddr(), -1, "encounterId="+Log_Relavant_Id.ENCOUNTER_ID+"|templateId="+Log_Relavant_Id.TEMPLATE_ID+"|shortcutId="+shortcutId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to Fetch Shortcut list based on search string
	 * @param mode
	 * @param searchStr
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value="/FetchShortCutListBasedOnSearchString",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean FetchShortCutListBasedOnSearchString(@RequestParam(value="mode") Integer mode,
			@RequestParam(value="searchStr") String searchStr,
			@RequestParam(value="limit") Integer limit,
			@RequestParam(value="offset") Integer offset)
	{
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(HPIService.fetchShortCutListBasedOnSearchString(mode,searchStr,limit,offset).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successful retrieval of HPI Shortcut list based on search string", -1, request.getRemoteAddr(), -1, "encounterId="+Log_Relavant_Id.ENCOUNTER_ID+"|templateId="+Log_Relavant_Id.TEMPLATE_ID+"|searchString="+searchStr, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	
	/**
	 * Method to add shortcut in chief complaints
	 * @param complaint
	 */
	@RequestMapping(value="/AddShorctutInCC",method=RequestMethod.POST)
	@ResponseBody
	public void AddShorctutInCCAndNote(@RequestParam(value="complaint") String complaint){
		complaint=Optional.fromNullable(complaint+"").or("-1");
		HPIService.addShorctutInCC(complaint);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of shortcut in HPI chief complaints", -1, request.getRemoteAddr(), -1, "encounterId="+Log_Relavant_Id.ENCOUNTER_ID+"|templateId="+Log_Relavant_Id.TEMPLATE_ID+"|complaint="+complaint, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to add shortcut in HPI Notes
	 * @param Id
	 * @param ShortCutCode
	 * @param Description
	 * @param categoryId
	 */
	@RequestMapping(value="/AddShorctutInNotes",method=RequestMethod.POST)
	@ResponseBody
	public void AddShorctutInNotes(@RequestParam(value="Id") String Id,
			@RequestParam(value="ShortCutCode") String ShortCutCode,
			@RequestParam(value="Description") String Description,
			@RequestParam(value="categoryId") String categoryId){
		Id=Optional.fromNullable(Id+"").or("-1");
		ShortCutCode=Optional.fromNullable(ShortCutCode+"").or("-1");
		Description=Optional.fromNullable(Description+"").or("-1");
		categoryId=Optional.fromNullable(categoryId+"").or("-1");
		HPIService.addShorctutInNotes(Id,ShortCutCode,Description,categoryId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of shortcut in HPI Notes", -1, request.getRemoteAddr(), -1, "encounterId="+Log_Relavant_Id.ENCOUNTER_ID+"|templateId="+Log_Relavant_Id.TEMPLATE_ID+"|ShortCutCode="+ShortCutCode+"categoryId"+categoryId, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	
	/**
	 * Method to fetch All Symptoms and Data
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param templateId
	 * @param isCompleted
	 * @param symptomTypeList
	 * @param isFollowUp
	 * @param clientId
	 * @return
	 */
	@RequestMapping(value="/FetchSymptom",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean FetchSymptom(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="templateId") Integer templateId,
			@RequestParam(value="isCompleted") String isCompleted,
			@RequestParam(value="symptomTypeList") String symptomTypeList,
			@RequestParam(value="isFollowUp") String isFollowUp,
			@RequestParam(value="clientId") String clientId){
		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		templateId=Integer.parseInt(Optional.fromNullable(templateId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(HPIService.fetchSymptom(patientId,chartId,encounterId,templateId,isCompleted,symptomTypeList,isFollowUp,clientId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successful retrieval of HPI All Symptoms and Data", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+templateId+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
}