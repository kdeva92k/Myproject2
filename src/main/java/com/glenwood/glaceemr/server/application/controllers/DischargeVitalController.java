package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.glenwood.glaceemr.server.application.services.chart.dischargeVitals.DischargeSaveVitalBean;
import com.glenwood.glaceemr.server.application.services.chart.dischargeVitals.DischargeVitalService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for Discharge vitals module
 * @author software
 *
 */
@RestController
@Transactional
@RequestMapping(value="/user/DischargeVitals")
public class DischargeVitalController {

	@Autowired
	DischargeVitalService dischargeVitalService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	
	@RequestMapping(value="/saveVitals",method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveDischargeVitals(@RequestBody DischargeSaveVitalBean vitalDataBean) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(dischargeVitalService.saveDischargeVitals(vitalDataBean));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Discharge vitals saved", sessionMap.getUserID(), request.getRemoteAddr(), vitalDataBean.getPatientId(), "encounterId="+vitalDataBean.getEncounterId(), LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.CREATEORUPDATE, 1, Log_Outcome.EXCEPTION, "Discharge vitals saved", sessionMap.getUserID(), request.getRemoteAddr(), vitalDataBean.getPatientId(), "encounterId="+vitalDataBean.getEncounterId(), LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
		
	}
	
	@RequestMapping(value="/getDischargeVitals",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAdmissionEncDetails(@RequestParam(value="patientId",required=false, defaultValue="") Integer patientId,
								@RequestParam(value="chartId",required=false, defaultValue="") Integer encounterId,
								@RequestParam(value="admssEpisode",required=false, defaultValue="") Integer admssEpisode) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(dischargeVitalService.getDischartgeVitals(patientId,encounterId,admssEpisode));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Discharge vitals viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|episodeId="+admssEpisode, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Discharge vitals viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|episodeId="+admssEpisode, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
}
