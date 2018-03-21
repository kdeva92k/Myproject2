package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.FocalShortcuts.FocalShortcutsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;

/**
 * Controller for FocalShortcuts
 * @author Bhagya Lakshmi
 *
 */
@RestController
@Transactional
@RequestMapping(value="/user/FocalShortcuts.Action")
public class FocalShortcutsController {
	
	@Autowired
	FocalShortcutsService FocalShortcutsService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;

	/**
	 * Method to get focal shortcuts Available
	 * @param tabId
	 * @return
	 */
	@RequestMapping(value="/GetFocalShortcutsAvailable",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean GetFocalshortcutsAvailable(@RequestParam(value="tabId") String tabId)
	{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		emrResponseBean.setData(FocalShortcutsService.getFocalshortcutsAvailable(tabId).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully retrieved focal shortcuts", sessionMap.getUserID(), request.getRemoteAddr(), -1, "tabId="+tabId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to delete focal elements
	 * @param shortcutId
	 */
	@RequestMapping(value="/DeleteFocalElements",method=RequestMethod.POST)
	@ResponseBody
	public void deleteSymptomDetails(@RequestParam(value="shortcutId") String shortcutId){
		String FocalShortcutId=HUtil.Nz(shortcutId,"-1");
		FocalShortcutsService.deleteElementsInFocalShortcut(FocalShortcutId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.DELETE, 1, Log_Outcome.SUCCESS, "Successfully deleted focal shortcut", sessionMap.getUserID(), request.getRemoteAddr(), -1, "shortcutId="+shortcutId, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to update focal shortcut
	 * @param description
	 * @param isActive
	 * @param focalId
	 */
	@RequestMapping(value="/UpdateFocalShortcut",method=RequestMethod.POST)
	@ResponseBody
	public void updateFocalShortcut(@RequestParam(value="description") String description,
			@RequestParam(value="isActive") Boolean isActive,
			@RequestParam(value="focalId") String focalId){
		String descriptionShortcut=HUtil.Nz(description,"-1");
		focalId=HUtil.Nz(focalId,"-1");
		FocalShortcutsService.updateFocalShortcut(descriptionShortcut,isActive,focalId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Successfully updated focal shortcut", sessionMap.getUserID(), request.getRemoteAddr(), -1, "focalId="+focalId, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to get focal shortcut data
	 * @param focalIndex
	 * @param tabId
	 * @param focalId
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param templateId
	 * @return 
	 */
	@RequestMapping(value="/GetFocalShortcutData",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getFocalShortcutData(@RequestParam(value="focalIndex") String focalIndex,
			@RequestParam(value="tabId") Integer tabId,
			@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="templateId") Integer templateId){
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		focalIndex=Optional.fromNullable(focalIndex).or("-1");
		tabId=Integer.parseInt(Optional.fromNullable(tabId+"").or("-1"));
		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		emrResponseBean.setData(FocalShortcutsService.getFocalShortcutData(focalIndex,tabId,patientId,chartId,encounterId,templateId).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully retrieved focal shortcut details", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "focalIndex="+focalIndex+"|chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to add new focal shortcut
	 * @param tabId
	 * @param patientId
	 * @param encounterId
	 * @param symptomIds
	 * @return
	 */
	@RequestMapping(value="/AddNewFocalShorctut",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean AddNewFocalShorctut(@RequestParam(value="tabId") Integer tabId,
			@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="symptomIds") String symptomIds){
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		tabId=Integer.parseInt(Optional.fromNullable(tabId+"").or("-1"));
		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		symptomIds=Optional.fromNullable(symptomIds+"").or("-1");
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		emrResponseBean.setData(FocalShortcutsService.addNewFocalShorctut(tabId,patientId,encounterId,symptomIds).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully created focal shortcut", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "symptomIds="+symptomIds+"|encounterId="+encounterId+"|tabId="+tabId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to save focal data
	 * @param tabid
	 * @param shortcutName
	 * @param focalDescription
	 * @param xmlData
	 * @return 
	 */
	@RequestMapping(value="/SaveFocalData",method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean SaveFocalData(@RequestParam(value="tabid") String tabid,
			@RequestParam(value="shortcutName") String shortcutName,
			@RequestParam(value="focalDescription") String focalDescription,
			@RequestParam(value="xmlData") String xmlData){
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		tabid=Optional.fromNullable(tabid+"").or("-1");
		shortcutName=Optional.fromNullable(shortcutName+"").or("-1");
		focalDescription=Optional.fromNullable(focalDescription+"").or("-1");
		xmlData=Optional.fromNullable(xmlData+"").or("-1");
		emrResponseBean.setData(FocalShortcutsService.saveFocalData(tabid,shortcutName,focalDescription,xmlData).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Successfully saved focal shortcut details", sessionMap.getUserID(), request.getRemoteAddr(), -1, "tabId="+tabid+"|shortcutName"+shortcutName, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to save focal data
	 * @param tabid
	 * @param shortcutName
	 * @param focalDescription
	 * @param xmlData
	 * @return 
	 */
	@RequestMapping(value="/SearchFocalShortcuts",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean SearchFocalShortcuts(@RequestParam(value="tabId") Integer tabId,
			@RequestParam(value="focalsearch") String focalsearch){
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		tabId=Optional.fromNullable(tabId).or(-1);
		focalsearch=Optional.fromNullable(focalsearch+"").or("-1");
		emrResponseBean.setData(FocalShortcutsService.searchFocalShortcuts(tabId,focalsearch).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully retrieved search results for focal shortcuts", sessionMap.getUserID(), request.getRemoteAddr(), -1, "tabId="+tabId+"|key="+focalsearch, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}

	/**
	 * Method to search focal shortcuts
	 * @param key
	 * @param tabId
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/SearchFocalShortcut", method= RequestMethod.GET)
	public EMRResponseBean searchFocalShortcut(
		@RequestParam(value="key", defaultValue="", required=true) String key,
		@RequestParam(value="tabId", defaultValue="-1", required=true) Integer tabId) throws JSONException{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		emrResponseBean.setData(FocalShortcutsService.searchFocalShortcut(key, tabId).toString());	
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully retrieved search results for focal shortcuts", sessionMap.getUserID(), request.getRemoteAddr(), -1, "tabId="+tabId+"|key="+key, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method view selected focal shortcut
	 * @param focalId
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value="/FetchFocalShortcut", method= RequestMethod.GET)
	public EMRResponseBean fetchFocalShortcut(
		@RequestParam(value="focalId", defaultValue="-1", required=true) Integer focalId) throws JSONException{		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		emrResponseBean.setData(FocalShortcutsService.fetchFocalShortcut(focalId).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully retrieved focal shortcut details", sessionMap.getUserID(), request.getRemoteAddr(), -1, "focalId="+focalId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to get patient encounter data for adding shortcut
	 * @param patientId
	 * @param encounterId
	 * @param gwPattern
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value="/FetchPatientData", method= RequestMethod.GET)
	public EMRResponseBean fetchPatientData(
		@RequestParam(value="patientId", defaultValue="-1", required=true) Integer patientId,
		@RequestParam(value="encounterId", defaultValue="-1", required=true) Integer encounterId,
		@RequestParam(value="gwPattern", defaultValue="", required=true) String gwPattern) throws JSONException{		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		emrResponseBean.setData(FocalShortcutsService.fetchPatientData(patientId, encounterId, gwPattern).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.FOCALSHORTCUTS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully retrieved patient encounter data for adding focal shortcut", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|gwpattern="+gwPattern, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
}