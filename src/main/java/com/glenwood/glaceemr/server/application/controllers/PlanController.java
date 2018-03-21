package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.plan.PlanService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for plan module
 * @author Chandrahas
 *
 */
@RestController
@RequestMapping(value="/user/Plan")
public class PlanController {
	
	@Autowired
	PlanService service;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	
	/**
	 * Get systems
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param templateId
	 * @param tabId
	 * @param clientId
	 * @return
	 */
	@RequestMapping(value="/FetchSystems", method=RequestMethod.GET)	
	public EMRResponseBean getSystems(
		@RequestParam(value="patientId", defaultValue="-1", required=false) Integer patientId,
		@RequestParam(value="chartId", defaultValue="-1", required=false) Integer chartId,
		@RequestParam(value="encounterId", defaultValue="-1", required=false) Integer encounterId,
		@RequestParam(value="templateId", defaultValue="-1", required=false) Integer templateId,
		@RequestParam(value="tabId", defaultValue="-1", required=false) Integer tabId,
		@RequestParam(value="clientId", defaultValue="-1", required=false) String clientId){
			
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{			
			emrResponseBean.setData(service.getSystems(patientId, chartId, encounterId, templateId, clientId, tabId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Plan systems viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Plan systems viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Get instructions
	 * @param patientId
	 * @param encounterId
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value="/FetchInstructions", method=RequestMethod.GET)	
	public EMRResponseBean getInstructions(
		@RequestParam(value="patientId", defaultValue="-1", required=true) Integer patientId,
		@RequestParam(value="chartId", defaultValue="-1", required=true) Integer chartId,
		@RequestParam(value="encounterId", defaultValue="-1", required=true) Integer encounterId,
		@RequestParam(value="templateId", defaultValue="-1", required=true) Integer templateId,
		@RequestParam(value="tabId", defaultValue="-1", required=true) Integer tabId,
		@RequestParam(value="clientId", defaultValue="-1", required=false) String clientId,
		@RequestParam(value="dxcode", defaultValue="", required=false) String dxcode){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(service.getInstructions(patientId, chartId, encounterId, templateId, clientId, tabId, dxcode));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Plan instructions viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId+"|clientId="+clientId+"|dxcode="+dxcode, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Plan instructions viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId+"|clientId="+clientId+"|dxcode="+dxcode, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Get notes
	 * @param patientId
	 * @param encounterId
	 * @param dxcode
	 * @return
	 */
	@RequestMapping(value="/FetchNotes", method=RequestMethod.GET)
	public EMRResponseBean getNotes(
		@RequestParam(value="patientId", defaultValue="-1", required=false) Integer patientId,
		@RequestParam(value="encounterId", defaultValue="-1", required=false) Integer encounterId,
		@RequestParam(value="dxcode", defaultValue="", required=false) String dxcode){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(service.getNotes(patientId, encounterId, dxcode));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Plan notes viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|dxcode="+dxcode, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){			
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Plan notes viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|dxcode="+dxcode, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
		
	}
	
	/**
	 * Get plan shortcuts
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value="/FetchPlanShortcuts", method= RequestMethod.GET)
	public EMRResponseBean getPlanShortcus(
		@RequestParam(value="limit", defaultValue="10", required=false) Integer limit,
		@RequestParam(value="offset", defaultValue="0", required=false) Integer offset,
		@RequestParam(value="key", defaultValue="", required=false) String key){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(service.getPlanShortcuts(limit, offset, key));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Plan notes shortcuts viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "key="+key+"|offset="+offset+"|limit="+limit, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Plan notes shortcuts viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "key="+key+"|offset="+offset+"|limit="+limit, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Get Patient instructions
	 * @param encounterId
	 * @return
	 */
	@RequestMapping(value="/FetchPatientInstructions", method= RequestMethod.GET)
	public EMRResponseBean fetchPatientInstructions(
		@RequestParam(value="encounterId", defaultValue="-1", required= true) Integer encounterId){

		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(service.getPatientIns(encounterId, "0000400000000000007"));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Patient instructions viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Patient instructions viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Get Current diagnosis
	 * @param encounterId
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value="/FetchCurrentDiagnosis", method= RequestMethod.GET)
	public EMRResponseBean fetchCurrentDiagnosis(
		@RequestParam(value="encounterId", defaultValue="-1", required= true) Integer encounterId,
		@RequestParam(value="patientId", defaultValue="-1", required= true) Integer patientId){

		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(service.getCurrentDx(patientId, encounterId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Imported current encounter Dx", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Imported current encounter Dx", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");			
		}
		return emrResponseBean;
	}
	
	/**
	 * Get Return visit elements
	 * @param encounterId
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value="/FetchReturnVisit", method= RequestMethod.GET)
	public EMRResponseBean fetchReturnVisit(
		@RequestParam(value="encounterId", defaultValue="-1", required= true) Integer encounterId,
		@RequestParam(value="templateId", defaultValue="-1", required= true) Integer templateId){

		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(service.getReturnVisit(templateId, encounterId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Plan Return visit viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId+"|templateId="+templateId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Plan Return visit viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId+"|templateId="+templateId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Map plan instruction to dx code
	 * @param insId
	 * @param mappingType
	 * @param dxcode
	 * @param codingsystem
	 * @return
	 */
	@RequestMapping(value="/mapInstructiontoDx", method= RequestMethod.GET)
	public EMRResponseBean mapInstructiontoDx(
		@RequestParam(value="insId", defaultValue="-1", required=true) Integer insId,
		@RequestParam(value="mappingType", defaultValue="-1", required=true) Integer mappingType,
		@RequestParam(value="dxcode", defaultValue="", required=true) String dxcode,
		@RequestParam(value="codingsystem", defaultValue="-1", required=true) String codingsystem){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			service.mapInstructiontoDx(insId, dxcode, mappingType, codingsystem);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Plan Instruction mapped to '"+dxcode+"'", sessionMap.getUserID(), request.getRemoteAddr(), -1, "insId="+insId+"|mappingType="+mappingType+"|dxcode="+dxcode+"|codingsystem="+codingsystem, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.CREATEORUPDATE, 1, Log_Outcome.EXCEPTION, "Plan Instruction mapped to '"+dxcode+"'", sessionMap.getUserID(), request.getRemoteAddr(), -1, "insId="+insId+"|mappingType="+mappingType+"|dxcode="+dxcode+"|codingsystem="+codingsystem, LogUserType.USER_LOGIN, "", "");
		}
		
		emrResponseBean.setData("success");
		return emrResponseBean;
	}
	
	@RequestMapping(value="/updateAftercareIns", method= RequestMethod.POST)
	public EMRResponseBean updateAftercareIns(
		@RequestParam(value="insId", defaultValue="-1", required=true) Integer insId,
		@RequestParam(value="insName", defaultValue="", required=true) String insName,
		@RequestParam(value="insStatus", defaultValue="false", required=true) Boolean insStatus,
		@RequestParam(value="encounterId", defaultValue="-1", required=true) Integer encounterId,
		@RequestParam(value="patientId", defaultValue="-1", required=true) Integer patientId,
		@RequestParam(value="otherIns", defaultValue="-1", required=false) Integer otherIns,
		@RequestParam(value="dxHandout", defaultValue="-1", required=false) Integer dxHandout,
		@RequestParam(value="dxCode", defaultValue="", required=false) String dxCode,
		@RequestParam(value="codingsystem", defaultValue="", required=false) String codingsystem){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			service.updatePatientAftercareIns(insId, insName, insStatus, encounterId, patientId, otherIns, dxHandout, dxCode, codingsystem);
			emrResponseBean.setData("success");
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Plan Handouts mapped to '"+dxCode+"'", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "insId="+insId+"|insName="+insName+"|insStatus="+insStatus+"|encounterId="+encounterId+"|otherIns="+otherIns+"|dxCode="+dxCode+"|codingsystem="+codingsystem, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.CREATEORUPDATE, 1, Log_Outcome.EXCEPTION, "Plan Handouts mapped to '"+dxCode+"'", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "insId="+insId+"|insName="+insName+"|insStatus="+insStatus+"|encounterId="+encounterId+"|otherIns="+otherIns+"|dxCode="+dxCode+"|codingsystem="+codingsystem, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	@RequestMapping(value="/getLanguages", method= RequestMethod.GET)
	public EMRResponseBean getLanguages(
		@RequestParam(value="insId", defaultValue="-1", required=true) Integer insId,
		@RequestParam(value="patientId", defaultValue="-1", required=true) Integer patientId){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			service.getLanguages(insId, patientId);
			emrResponseBean.setData("success");
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Handout Languages viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "insId="+insId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Handout Languages viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "insId="+insId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Get Active Referring Physicians
	 * @return
	 */
	@RequestMapping(value="/getReferringPhysicians", method= RequestMethod.GET)
	public EMRResponseBean getReferringPhysicians(){
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(service.getReferringPhysicians());
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Referring physicians viewed in Plan", sessionMap.getUserID(), request.getRemoteAddr(), 1, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Referring physicians viewed in Plan", sessionMap.getUserID(), request.getRemoteAddr(), 1, "", LogUserType.USER_LOGIN, "", "");
		}
		
		return emrResponseBean;
	}
		
}
