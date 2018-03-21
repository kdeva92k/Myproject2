package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

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
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.ImportData.ImportDataService;
import com.glenwood.glaceemr.server.application.services.chart.vitals.VitalsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;

@RestController
@Transactional
@RequestMapping(value="/user/VitalElements.Action")
public class VitalsController {
	
	
	@Autowired
	VitalsService vitalService;
	
	@Autowired
	ImportDataService importDataService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	
	/**
	 * Get Vital groups
	 * @param patientId
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/VitalGroups",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getVitalGroups(@RequestParam(value="patientId",required=false, defaultValue="") Integer patientId,
			@RequestParam(value="groupId") Integer groupId,
			@RequestParam(value="encounterId" ,defaultValue="-1", required=false)Integer encounterId,
			@RequestParam(value="dischargeVitals", defaultValue="false", required=false) Boolean isDischargeVitals,
			@RequestParam(value="admssEpisode", defaultValue="-1", required=false) Integer admssEpisode,
			@RequestParam(value="isFromVitals", defaultValue="0", required=false) Integer isFromVitals) throws Exception{
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			groupId=Integer.parseInt(Optional.fromNullable(groupId+"").or("-1"));
			emrResponseBean.setData(vitalService.getActiveVitalsGroup(patientId,groupId));
			encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
			isDischargeVitals=Boolean.parseBoolean(Optional.fromNullable(isDischargeVitals+"").or("false"));
			admssEpisode=Integer.parseInt(Optional.fromNullable(admssEpisode+"").or("-1"));
			if(isFromVitals==1){
				vitalService.insertvitals(patientId,encounterId,isDischargeVitals,admssEpisode);
			}
			
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.VITALS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Vital Groups viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "groupId="+groupId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.VITALS, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Vital Groups viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "groupId="+groupId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Get Vital group elements
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param groupId
	 * @param isDischargeVitals
	 * @param admssEpisode
	 * @param clientId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/VitalGroups/Vital",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getGroupVitals(@RequestParam(value="patientId") Integer patientId,
									@RequestParam(value="chartId") Integer chartId,
									@RequestParam(value="encounterId") Integer encounterId,
									@RequestParam(value="groupId") Integer groupId,
									@RequestParam(value="dischargeVitals", defaultValue="false", required=false) Boolean isDischargeVitals,
									@RequestParam(value="admssEpisode", defaultValue="-1", required=false) Integer admssEpisode,
									@RequestParam(value="clientId", defaultValue="000", required=false) String clientId) throws Exception{
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
			encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
			groupId=Integer.parseInt(Optional.fromNullable(groupId+"").or("-1"));
			isDischargeVitals=Boolean.parseBoolean(Optional.fromNullable(isDischargeVitals+"").or("false"));
			admssEpisode=Integer.parseInt(Optional.fromNullable(admssEpisode+"").or("-1"));
			emrResponseBean.setData(vitalService.setVitals(patientId,encounterId,groupId,isDischargeVitals,admssEpisode,clientId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.VITALS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Vital Group Elements viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|groupId="+groupId+"|isDischargeVitals="+isDischargeVitals+"|admssEpisode="+admssEpisode+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		}
		catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.VITALS, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Vital Group Elements viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|groupId="+groupId+"|isDischargeVitals="+isDischargeVitals+"|admssEpisode="+admssEpisode+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Get Vital Group elements
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param groupId
	 * @param isDischargeVitals
	 * @param admssEpisode
	 * @param clientId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/VitalGroups/VitalPrint",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getGroupVitalsPrint(@RequestParam(value="patientId") Integer patientId,
									@RequestParam(value="chartId") Integer chartId,
									@RequestParam(value="encounterId") Integer encounterId,
									@RequestParam(value="groupId") Integer groupId,
									@RequestParam(value="dischargeVitals") Boolean isDischargeVitals,
									@RequestParam(value="admssEpisode") Integer admssEpisode,
									@RequestParam(value="clientId") String clientId) throws Exception{
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
			encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
			groupId=Integer.parseInt(Optional.fromNullable(groupId+"").or("-1"));
			isDischargeVitals=Boolean.parseBoolean(Optional.fromNullable(isDischargeVitals+"").or("false"));
			admssEpisode=Integer.parseInt(Optional.fromNullable(admssEpisode+"").or("-1"));
			emrResponseBean.setData(vitalService.setVitals(patientId,encounterId,groupId,isDischargeVitals,admssEpisode,clientId,1));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.VITALS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Vital Group Elements viewed for print", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|groupId="+groupId+"|isDischargeVitals="+isDischargeVitals+"|admssEpisode="+admssEpisode+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.VITALS, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Vital Group Elements viewed for print", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|groupId="+groupId+"|isDischargeVitals="+isDischargeVitals+"|admssEpisode="+admssEpisode+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Get VITALS comments
	 * @param patientId
	 * @param encounterId
	 * @param gwId
	 * @return
	 */
	@RequestMapping(value="/FetchNotes", method=RequestMethod.GET)
	public EMRResponseBean getVitalNotes(@RequestParam(value="patientId") Integer patientId,
								@RequestParam(value="encounterId") Integer encounterId,
								@RequestParam(value="gwId") String gwId) {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
			gwId=Optional.fromNullable(gwId+"").or("");
			emrResponseBean.setData(vitalService.getNotes(patientId,encounterId,gwId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.VITALS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Vital Notes viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|gwId="+gwId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.VITALS, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Vital Notes viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|gwId="+gwId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
}
