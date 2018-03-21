package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.glenwood.glaceemr.server.application.services.chart.Examination.ExaminationService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.google.common.base.Optional;



@RestController
@RequestMapping(value="/user/ExaminationElements.Action")
public class ExaminationController {

	@Autowired
	ExaminationService examinationService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping(value="/ExaminationSystems",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getExaminationSystems(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="templateId") Integer templateId,
			@RequestParam(value="clientId") String clientId) throws Exception{

		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		templateId=Integer.parseInt(Optional.fromNullable(templateId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(examinationService.getActiveSystems(clientId,patientId,chartId,encounterId,templateId));		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PHYSICALEXAMINATION, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved Examination system details", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+templateId+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	@RequestMapping(value="/ExaminationSystems/ExaminationElements",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getExaminationElements(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="templateId") Integer templateId,
			@RequestParam(value="systemId") Integer systemId,
			@RequestParam(value="clientId") String clientId) throws Exception{
		
		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		templateId=Integer.parseInt(Optional.fromNullable(templateId+"").or("-1"));
		systemId=Integer.parseInt(Optional.fromNullable(systemId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(examinationService.getSystemActiveElements(clientId,patientId, chartId, encounterId, templateId, systemId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PHYSICALEXAMINATION, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved Examination element details", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+templateId+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	
	@RequestMapping(value="/MarkAllNormal",method=RequestMethod.GET)
	@ResponseBody
	public void markAllNormal(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="templateId") Integer templateId,
			@RequestParam(value="systemIds") String systemIds) throws Exception{

		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		templateId=Integer.parseInt(Optional.fromNullable(templateId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		examinationService.markAllNormal(patientId, encounterId, templateId,chartId,systemIds);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PHYSICALEXAMINATION, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved Mark all normal details", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+templateId, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	
	@RequestMapping(value="/ClearAllNormal",method=RequestMethod.GET)
	@ResponseBody
	public void clearAllNormal(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="chartId") Integer chartId,
			@RequestParam(value="encounterId") Integer encounterId,
			@RequestParam(value="templateId") Integer templateId,
			@RequestParam(value="systemIds") String systemIds) throws Exception{

		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		templateId=Integer.parseInt(Optional.fromNullable(templateId+"").or("-1"));
		chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
		examinationService.clearAllNormal(patientId, encounterId, templateId,chartId,systemIds);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PHYSICALEXAMINATION, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved clear all normal details", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId"+encounterId+"|templateId="+templateId, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	@RequestMapping(value="/PeNotes",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPeNotes(@RequestParam(value="patientId") Integer patientId,
			@RequestParam(value="encounterId") Integer encounterId) throws Exception{

		patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
		encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(examinationService.getPENotes(patientId,encounterId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PHYSICALEXAMINATION, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved PE Notes data", -1, request.getRemoteAddr(), patientId, "encounterId"+encounterId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	@RequestMapping(value="/getQuickNotes",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getQuickNotes(@RequestParam(value="tabId") Integer tabId,
			@RequestParam(value="elementId") String elementId) throws Exception{
		tabId=Integer.parseInt(Optional.fromNullable(tabId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(examinationService.getQuickNotes(tabId,elementId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PHYSICALEXAMINATION, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved Quick Notes data", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	@RequestMapping(value="/addQuickNotes",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getQuickNotes(@RequestParam(value="tabId") Integer tabId,
			@RequestParam(value="elementId") String elementId,
			@RequestParam(value="elementData") String elementData) throws Exception{

		tabId=Integer.parseInt(Optional.fromNullable(tabId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(examinationService.addQuickNotes(tabId,elementId,elementData));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PHYSICALEXAMINATION, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully added Quick Notes data", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	@RequestMapping(value="/deleteQuickNotes",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deleteQuickNotes(@RequestParam(value="dataListId") Integer dataListId) throws Exception{

		dataListId=Integer.parseInt(Optional.fromNullable(dataListId+"").or("-1"));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(examinationService.deleteQuickNotes(dataListId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PHYSICALEXAMINATION, LogActionType.DELETE, 0, Log_Outcome.SUCCESS, "successfully deleted Quick Notes data", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	
}