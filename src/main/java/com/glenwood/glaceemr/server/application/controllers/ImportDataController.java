package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

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
import com.glenwood.glaceemr.server.application.services.chart.ImportData.ImportDataService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;

@RestController
@Transactional
@RequestMapping(value="/user/ImportData.Action")
public class ImportDataController {
	
	@Autowired
	ImportDataService importDataService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	
	/**
	 * Getting patient encounter list
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ImportEncounterList",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getImportEncList(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="encounterId") Integer encounterId) throws Exception{
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
			emrResponseBean.setData(importDataService.getImportEncList(patientId,encounterId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Encounter list viewed to import", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Encounter list viewed to import", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}

	/**
	 * Importing data from previous encounter
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param templateId
	 * @param tabId
	 * @param prevEncounterId
	 * @throws Exception
	 */
	@RequestMapping(value="/ImportData",method=RequestMethod.GET)
	@ResponseBody
	public void getImportData(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="templateId") Integer templateId,
			@RequestParam(value="tabId") Integer tabId,
			@RequestParam(value="prevEncounterId") Integer prevEncounterId,
			@RequestParam(value="dxSpecific", required=false, defaultValue="f") String dxSpecific,
			@RequestParam(value="dxCode", required=false, defaultValue="") String dxCode,
			@RequestParam(value="userId", required=false, defaultValue="-1") Integer userId) throws Exception{

		try{
			patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
			chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
			templateId=Integer.parseInt(Optional.fromNullable(templateId+"").or("-1"));
			tabId=Integer.parseInt(Optional.fromNullable(tabId+"").or("-1"));
			prevEncounterId=Integer.parseInt(Optional.fromNullable(prevEncounterId+"").or("-1"));
			userId=Integer.parseInt(Optional.fromNullable(userId+"").or("-1"));
			importDataService.importData(patientId,encounterId,templateId,chartId,tabId,prevEncounterId,dxSpecific,dxCode,userId);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Imported data from previous encounter", userId, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId+"|prevEncounterId="+prevEncounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.CREATE, 1, Log_Outcome.EXCEPTION, "Imported data from previous encounter", userId, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId+"|prevEncounterId="+prevEncounterId, LogUserType.USER_LOGIN, "", "");
		}
	}


}