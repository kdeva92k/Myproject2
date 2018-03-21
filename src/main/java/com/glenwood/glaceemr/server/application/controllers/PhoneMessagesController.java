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
import com.glenwood.glaceemr.server.application.services.phonemessages.PhoneMessagesService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@Transactional
@RequestMapping(value="/user/PhoneMessages")
public class PhoneMessagesController {
	
	@Autowired
	PhoneMessagesService phoneMessageService;

	@Autowired
	EncounterRepository encounterRepository;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	/**
	 * To get the list of phone messages based on the patient id
	 * @param patientId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "/getPhoneEncounters", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPhoneEncounters(@RequestParam(value="patientid", required=true, defaultValue="") String patientId,
			@RequestParam(value="startdate", required=false, defaultValue="-1") String startDate,
			@RequestParam(value="enddate", required=false, defaultValue="-1") String endDate){
			List<Encounter> encounterList=phoneMessageService.getPhoneEncounters(patientId, startDate, endDate);
			EMRResponseBean encounter=new EMRResponseBean();
			encounter.setData(encounterList); 
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting phone messages", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return encounter;
	}
	
	/**
	 * To create phone message encounter
	 * @param patientId
	 * @param chartId
	 * @param severity
	 * @param serviceDr
	 * @param comments
	 * @param createdBy
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean create(
			@RequestParam(value="patientid", required=false, defaultValue="") String patientId,
			@RequestParam(value="chartid", required=true, defaultValue="-1") String chartId,
			@RequestParam(value="severity", required=false, defaultValue="-1") String severity,
			@RequestParam(value="servicedr", required=true, defaultValue="-1") String serviceDr,
			@RequestParam(value="comments", required=false, defaultValue="-1") String comments,
			@RequestParam(value="createdby", required=true, defaultValue="-1") String createdBy){
		
			Short encounterType=2;
			Encounter encounterList=phoneMessageService.createEncounter(chartId, severity, encounterType, serviceDr, comments, createdBy);
			EMRResponseBean encounter=new EMRResponseBean();
			encounter.setData(encounterList);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Create phone message.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "patientId="+patientId+"&chartId="+chartId+"&severity="+severity+"&serviceDr="+serviceDr, LogUserType.USER_LOGIN, "", "");
		return encounter;
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
		
			Encounter encounter=phoneMessageService.getEncounterDetails(encounterId);
			EMRResponseBean encounters=new EMRResponseBean();
			encounters.setData(encounter);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting phone messages by encounter id", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
			return encounters;
	}
	
	/**
	 * To update the particular encounter details
	 * @param encounterId
	 * @param response
	 * @param status
	 * @param modifiedBy
	 * @param serviceDr
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean update(
			@RequestParam(value="encounterid", required=false, defaultValue="1") String encounterId,
			@RequestParam(value="response", required=true, defaultValue="") String response,
			@RequestParam(value="status", required=false, defaultValue="-1") String status,
			@RequestParam(value="modifiedby", required=true, defaultValue="-1") String modifiedBy,
			@RequestParam(value="servicedr", required=false, defaultValue="-1") String serviceDr){
			Encounter encounter=phoneMessageService.update(encounterId, response, status, modifiedBy, serviceDr);
			EMRResponseBean encounters=new EMRResponseBean();
			encounters.setData(encounter);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Update phone messages by encounter id", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return encounters;
	}
	
	/**
	 * To send reply to a phone message encounter.
	 * @param encounterId
	 * @param replyMessage
	 * @param status
	 * @param modifiedBy
	 * @param severity
	 * @return
	 */
	@RequestMapping(value = "/sendreply", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean sendReply(
			@RequestParam(value="encounterid", required=false, defaultValue="1") String encounterId,
			@RequestParam(value="replymsg", required=true, defaultValue="-1") String replyMessage,
			@RequestParam(value="status", required=false, defaultValue="-1") String status,
			@RequestParam(value="modifiedby", required=true, defaultValue="-1") String modifiedBy,
			@RequestParam(value="severity", required=false, defaultValue="-1") String severity){
		
			Encounter encounter=phoneMessageService.sendReply(encounterId, replyMessage, status, modifiedBy, severity);
			EMRResponseBean encounters=new EMRResponseBean();
			encounters.setData(encounter);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Update phone messages by encounter id", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return encounters;
	}
}