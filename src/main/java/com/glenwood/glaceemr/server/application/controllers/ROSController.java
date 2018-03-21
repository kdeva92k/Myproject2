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

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.ros.ROSService;
import com.glenwood.glaceemr.server.application.services.chart.ros.ROSSystemBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;

@RestController
@Transactional
@RequestMapping(value="/user/ROSElements")
public class ROSController {
	
	
	@Autowired
	ROSService rosService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SessionMap sessionMap;
	
	/**
	 * Getting ROS Elements
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param templateId
	 * @param clientId
	 * @param tabId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ROSElements",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getROSElement(@RequestParam(value="patientId") Integer patientId,
									   @RequestParam(value="chartId") Integer chartId,
									   @RequestParam(value="encounterId") Integer encounterId,
									   @RequestParam(value="templateId") Integer templateId,
									   @RequestParam(value="clientId") String clientId,
									   @RequestParam(value="tabId") Integer tabId) throws Exception{
			
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			chartId=Integer.parseInt(Optional.fromNullable(chartId+"").or("-1"));
			encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
			templateId=Integer.parseInt(Optional.fromNullable(templateId+"").or("-1"));
			tabId=Integer.parseInt(Optional.fromNullable(tabId+"").or("-1"));
			List<ROSSystemBean> ROSBean = rosService.getROSElements(clientId,patientId,chartId,encounterId,templateId,tabId);		
			emrResponseBean.setData(ROSBean);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "ROS Elements viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROS, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "ROS Elements viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId+"|templateId="+templateId+"|tabId="+tabId+"|clientId="+clientId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	/**
	 * Getting ROS Notes
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ROSNotes",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getROSNotes(@RequestParam(value="patientId") Integer patientId,
							 @RequestParam(value="encounterId") Integer encounterId) throws Exception{

		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			patientId=Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			encounterId=Integer.parseInt(Optional.fromNullable(encounterId+"").or("-1"));
			String notes=rosService.getROSNotes(patientId,encounterId);
			emrResponseBean.setData(notes);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROS, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "ROS Notes viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROS, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "ROS Notes viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
}