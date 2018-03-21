package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.internalmessages.InternalMessagesService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for Internal Messages.
 * IM=Internal Messages
 * @author Manikandan N
 *
 */

@RestController
@Transactional
@RequestMapping(value="/user/InternalMessages")
public class InternalMessagesController {

	@Autowired
	EncounterRepository encounterRepository;

	@Autowired
	InternalMessagesService internalMessagesService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/getEncounters", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getIMEncounters(@RequestParam(value="patientid", required=true, defaultValue="") String patientId){
		
			// For internal messages encounter type = 4
			List<Encounter> encounterList=internalMessagesService.getIMEncounters(patientId);
			EMRResponseBean result= new EMRResponseBean();
			result.setData(encounterList);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting internal messages", sessionMap.getUserID(), request.getRemoteAddr(), Integer.parseInt(patientId), "", LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
	/**
	 * To get the particular encounter details based on the encounter id
	 * @param encounterId
	 * @return
	 */
	@RequestMapping(value = "/getEncounterDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getEncounterDetails(
			@RequestParam(value="encounterid", required=true, defaultValue="1") String encounterId){
		
			Encounter encounter=internalMessagesService.getEncounterDetails(encounterId);
			EMRResponseBean result= new EMRResponseBean();
			result.setData(encounter);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting internal messages by encounter id", sessionMap.getUserID(), request.getRemoteAddr(),-1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
	/**
	 * To update the particular encounter details based on the encounter id
	 * @param encounterId
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean update(
			@RequestParam(value="encounterid", required=true, defaultValue="1") String encounterId,
			@RequestParam(value="patientid", required=true, defaultValue="1") String patientId,
			@RequestParam(value="chartid", required=true, defaultValue="-1") String chartId,
			@RequestParam(value="userid", required=true, defaultValue="-1111") String userId,
			@RequestParam(value="servicedoctor", required=true, defaultValue="-1111") String serviceDoctor,
			@RequestParam(value="message", required=true, defaultValue="") String message,
			@RequestParam(value="severity", required=true, defaultValue="1") String severity,
			@RequestParam(value="status", required=true, defaultValue="1") String status,
			@RequestParam(value="encountertype", required=true, defaultValue="1") String encounterType){
		
			Encounter encounter=internalMessagesService.update(encounterId,patientId,chartId,userId,serviceDoctor,message,severity,status,encounterType);
			EMRResponseBean result= new EMRResponseBean();
			result.setData(encounter);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Update internal messages by encounter id", sessionMap.getUserID(), request.getRemoteAddr(),-1, "encounterId="+encounterId+"patientId="+patientId+"&chartId="+chartId+"&severity="+severity+"&serviceDoctor="+serviceDoctor, LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
	/**
	 * To update the particular encounter details based on the encounter id
	 * @param encounterId
	 * @return
	 */
	@RequestMapping(value = "/compose", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean compose(
			@RequestParam(value="patientid", required=true, defaultValue="1") String patientId,
			@RequestParam(value="chartid", required=true, defaultValue="1") String chartId,
			@RequestParam(value="userid", required=true, defaultValue="-1111") String userId,
			@RequestParam(value="servicedoctor", required=true, defaultValue="-1111") String serviceDoctor,
			@RequestParam(value="toid", required=true, defaultValue="") String toId,
			@RequestParam(value="message", required=true, defaultValue="") String message,
			@RequestParam(value="severity", required=true, defaultValue="1") String severity,
			@RequestParam(value="encountertype", required=true, defaultValue="4") String encounterType){
		
			Encounter encounter=internalMessagesService.compose(patientId,chartId,userId,serviceDoctor,toId,message,severity,encounterType);
			EMRResponseBean result= new EMRResponseBean();
			result.setData(encounter);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Create internal messages", sessionMap.getUserID(), request.getRemoteAddr(),-1, "encounterType="+encounterType+"patientId="+patientId+"&chartId="+chartId+"&severity="+severity+"&serviceDoctor="+serviceDoctor, LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
}