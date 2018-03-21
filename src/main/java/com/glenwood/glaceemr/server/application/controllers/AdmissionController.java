package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.Admission;
import com.glenwood.glaceemr.server.application.models.AdmissionRoom;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.admission.AdmissionBean;
import com.glenwood.glaceemr.server.application.services.chart.admission.AdmissionLeafBean;
import com.glenwood.glaceemr.server.application.services.chart.admission.AdmissionService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;

/**
 * Controller for Admission module
 * @author software
 *
 */
@RestController
@Transactional
@RequestMapping(value="/user/Admission")
public class AdmissionController {


	@Autowired
	AdmissionService admissionService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	
	/**
	 * To Create and update Admission details
	 * @param dataJson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveAdmission",method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean createAdmission(@RequestBody AdmissionBean dataJson) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			dataJson = getAdmissionBeanData(dataJson);
			String data= admissionService.saveAdmission(dataJson);		
			emrResponseBean.setData(data);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Admission created/updated", sessionMap.getUserID(), request.getRemoteAddr(), dataJson.getPatientId(), "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){			
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.CREATEORUPDATE, 1, Log_Outcome.EXCEPTION, "Admission created/updated", sessionMap.getUserID(), request.getRemoteAddr(), dataJson.getPatientId(), "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * To Get open admission details
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAdmission",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAdmission(@RequestParam(value="patientId",required=false, defaultValue="") Integer patientId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			Admission admission= admissionService.getAdmission(patientId);
			emrResponseBean.setData(admission);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Admissions viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Admissions viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
	    return emrResponseBean;
	}
	
	/**
	 * To Get open discharge details from pat encounter
	 * @param patientId
	 * @param admissionId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getdischargeValues",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getdischargeValues(@RequestParam(value="admissionId",required=false, defaultValue="")  Integer admissionId,
			@RequestParam(value="patientId",required=false, defaultValue="") Integer patientId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			List<Object[]> admission= admissionService.getdischargeValues(admissionId,patientId);
			emrResponseBean.setData(admission);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Past discharge details viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Past discharge details viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
	    return emrResponseBean;
	}
	
	/**
	 * To Discharge patient (closing admission)
	 * @param patientId
	 * @param loginId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dischargePatient",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean dischargePatient(@RequestParam(value="patientId",required=false, defaultValue="") Integer patientId,
			   @RequestParam(value="loginId",required=false, defaultValue="") Integer loginId,						 
			   @RequestParam(value="userId",required=false, defaultValue="") Integer userId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			String discharge= admissionService.dischargePatient(patientId,loginId,userId);
			emrResponseBean.setData(discharge);
			if(discharge.equals("success"))
				auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Patient discharged", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
			else
				auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Unable to discharge patient", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Unable to discharge patient", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
    }

	
	/**
	 * To Get selected past Admission details
	 * @param admissionId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPastAdmission",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPastAdmission(@RequestParam(value="admissionId",required=false, defaultValue="") Integer admissionId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(admissionService.getPastAdmission(admissionId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Past Admission viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "admissionId="+admissionId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Past Admission viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "admissionId="+admissionId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * To Get recently closed Admission details
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAdmissionPast",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPastAdmissionPast(@RequestParam(value="patientId",required=false, defaultValue="") Integer patientId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(admissionService.getAdmissionPast(patientId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Recent Past Admission viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Recent Past Admission viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * To Get past admission dates
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getPastAdmissionDates",method=RequestMethod.GET)
	public EMRResponseBean getPastAdmissionDates(@RequestParam(value="patientId",required=false, defaultValue="") Integer patientId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			emrResponseBean.setData(admissionService.getPastAdmissionDates(patientId));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Past Admissions list viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Past Admissions list viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * To Get clinical notes
	 * @param encounterId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getLeafDetails",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getLeafDetails(@RequestParam(value="encounterId",required=false, defaultValue="") Integer encounterId,
			  @RequestParam(value="userId",required=false, defaultValue="") Integer userId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			List<Admission> leafDetails=admissionService.getLeafDetails(encounterId,userId);
			emrResponseBean.setData(leafDetails);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Admission leaf details viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Admission leaf details viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}
        return emrResponseBean;
      }
	
	/**
	 * To Get encounter list corresponding to Episode
	 * @param admssEpisode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAdmissionEncounterDetails",method=RequestMethod.GET)
	@ResponseBody
		public EMRResponseBean getAdmissionEncDetails(@RequestParam(value="admssEpisode",required=false, defaultValue="") Integer admssEpisode) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			String encDetails=admissionService.getAdmissionEncDetails(admssEpisode);
			emrResponseBean.setData(encDetails);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Admission episode encounters viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "admssEpisode="+admssEpisode, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Admission episode encounters viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "admssEpisode="+admssEpisode, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Creating clinical note
	 * @param dataJson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/openLeaf",method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean openAdmissionLeaf(@RequestBody AdmissionBean dataJson) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			dataJson = getAdmissionBeanData(dataJson);
			Encounter leaf=admissionService.openAdmissionLeaf(dataJson);
			emrResponseBean.setData(leaf);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Admission leaf created", sessionMap.getUserID(), request.getRemoteAddr(), dataJson.getPatientId(), "admissionDate="+dataJson.getAdmissionDate(), LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.CREATE, 1, Log_Outcome.EXCEPTION, "Admission leaf created", sessionMap.getUserID(), request.getRemoteAddr(), dataJson.getPatientId(), "admissionDate="+dataJson.getAdmissionDate(), LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * To Get clinical notes and Allergies details
	 * @param admssEpisode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/AdmissionLeafs",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAdmissionLeafs(@RequestParam(value="admssEpisode",required=false, defaultValue="") Integer admssEpisode) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			AdmissionLeafBean leafs=admissionService.getAdmissionLeafs(admssEpisode);
			emrResponseBean.setData(leafs);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Admission leafs and allergies viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "admssEpisode="+admssEpisode, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Admission leafs and allergies viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "admssEpisode="+admssEpisode, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * To Get Rooms of selected Block
	 * @param blockId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getRooms",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getRooms(@RequestParam(value="blockId",required=false, defaultValue="-1") Integer blockId) throws Exception{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			List<AdmissionRoom> room=admissionService.getRooms(blockId);
			emrResponseBean.setData(room);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Admission rooms viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "unitId::"+blockId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Admission rooms viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "unitId::"+blockId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}	
	
	/**
	 * Setting default values to data
	 * @param dataJson
	 * @return
	 */
	public AdmissionBean getAdmissionBeanData(AdmissionBean dataJson){
		
		dataJson.setAdmissionDate(Optional.fromNullable(dataJson.getAdmissionDate()+"").or("-1"));
		dataJson.setAdmssProvider(Integer.parseInt(Optional.fromNullable(dataJson.getAdmssProvider()+"").or("-1")));
		dataJson.setPos(Integer.parseInt(Optional.fromNullable(dataJson.getPos()+"").or("-1")));
		dataJson.setPatientId(Integer.parseInt(Optional.fromNullable(dataJson.getPatientId()+"").or("-1")));
		dataJson.setSelectedDx(Optional.fromNullable(dataJson.getSelectedDx()+"").or("[]"));
		dataJson.setChartId(Integer.parseInt(Optional.fromNullable(dataJson.getChartId()+"").or("-1")));
		dataJson.setUserId(Integer.parseInt(Optional.fromNullable(dataJson.getUserId()+"").or("-1")));
		dataJson.setLoginId(Integer.parseInt(Optional.fromNullable(dataJson.getLoginId()+"").or("-1")));
		dataJson.setAdmissionEpisode(Integer.parseInt(Optional.fromNullable(dataJson.getAdmissionEpisode()+"").or("-1")));		
		
		return dataJson;
	}
	
	@RequestMapping(value="/SaveDischargeDetails", method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveDischargeDetails(@RequestBody AdmissionBean dataJson) throws JSONException{
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			admissionService.saveDishcargeDetails(dataJson);
			emrResponseBean.setData("success");
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Discharge details saved and Patient discharged", sessionMap.getUserID(), request.getRemoteAddr(), dataJson.getPatientId(), "admissionId="+dataJson.getAdmissionId(), LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ADMISSION, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Discharge details saved and Patient discharged", sessionMap.getUserID(), request.getRemoteAddr(), dataJson.getPatientId(), "admissionId="+dataJson.getAdmissionId(), LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
}