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
import com.glenwood.glaceemr.server.application.services.pastencounters.PastEncountersService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller Past Encounters
 * @author Chandrahas
 *
 */
@RestController
@Transactional
@RequestMapping(value="/user/PastEncounters")
public class PastEncountersController {


	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	PastEncountersService pastEncountersService;
	
	/**
	 * To Get open admission details
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPastEncounters",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAdmission(@RequestParam(value="patientId",required=false, defaultValue="-1") Integer patientId,
			@RequestParam(value="chartId",required=false, defaultValue="-1") Integer chartId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{			
			emrResponseBean.setData(pastEncountersService.getPastEncounters(patientId, chartId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PASTENCOUNTERS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Past Encounters viewed @chartId="+chartId, sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PASTENCOUNTERS, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Past Encounters viewed @chartId="+chartId, sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
	    return emrResponseBean;
	}
	
	/**
	 * To Get visit summary details
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @return
	 */
	@RequestMapping(value="/getVisitSummary", method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getVisitSummary(
			@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{			
			emrResponseBean.setData(pastEncountersService.getVisitSummary(patientId, chartId, encounterId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PASTENCOUNTERS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Past Encounter visit summary viewed @encounterId="+encounterId, sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PASTENCOUNTERS, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Past Encounters visit summary viewed @encounterId="+encounterId, sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		}		
		return emrResponseBean;
	}
	
}