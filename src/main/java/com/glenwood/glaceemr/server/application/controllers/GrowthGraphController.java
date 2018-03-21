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

import com.glenwood.glaceemr.server.application.models.GrowthGraphPatientData;
import com.glenwood.glaceemr.server.application.models.GrowthGraphVitalData;
import com.glenwood.glaceemr.server.application.models.GrowthGraph;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.growthgraph.GrowthGraphService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for growth graph
 * @author Jeyanthkumar
 *
 */


@RestController
@Transactional
@RequestMapping(value="/user/GrowthGraph")
public class GrowthGraphController {

	@Autowired
	GrowthGraphService growthGraphService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	/**
	 * To get the default graph for the patient. (Based on patient's age)
	 * @param patientId
	 * @return default graph id
	 */
	@RequestMapping(value = "/getdefaultgraphid", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean defaultGraphId(
			 @RequestParam(value="patientid", required=false, defaultValue="true") String patientId){
		
			String defaultId=growthGraphService.getDefaultGraphId(patientId);
			EMRResponseBean result= new EMRResponseBean();
			result.setData(defaultId);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting default graph id", sessionMap.getUserID(), request.getRemoteAddr(), Integer.parseInt(patientId), "", LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
	/**
	 * To get patient details based on patient id
	 * @param patientId
	 * @return patient details from GrowthGraphPatientData bean.
	 */
	@RequestMapping(value = "/getpatientinfo", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getpatientinfo(
			 @RequestParam(value="patientid", required=false, defaultValue="true") String patientId){
		
		GrowthGraphPatientData patientDetails=growthGraphService.getpatientinfo(patientId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(patientDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting patient info for growth graph", sessionMap.getUserID(), request.getRemoteAddr(), Integer.parseInt(patientId), "", LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
	/**
	 * To get patient vital details based on patient id
	 * @param patientId
	 * @return list of patient vital details from GrowthGraphVitalData bean.
	 */
	@RequestMapping(value = "/getvitalvalues", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getVitalValues(
			 @RequestParam(value="patientid", required=false, defaultValue="true") String patientId,
			 @RequestParam(value="wellvisit", required=false, defaultValue="false") boolean wellvisit){
		
		List<GrowthGraphVitalData> patientDetails=growthGraphService.getVitalValues(patientId,wellvisit);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(patientDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting patient vital dta for growth graph", sessionMap.getUserID(), request.getRemoteAddr(), Integer.parseInt(patientId), "", LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
	/**
	 * To get graph list based on patient id
	 * @param patientId
	 * @return list of graph details from growthgraph table
	 */
	@RequestMapping(value = "/getgraphlist", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getGraphList(
			 @RequestParam(value="patientid", required=false, defaultValue="true") String patientId){
		
		List<GrowthGraph> patientDetails=growthGraphService.getGraphList(patientId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(patientDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting list of growth graph", sessionMap.getUserID(), request.getRemoteAddr(), Integer.parseInt(patientId), "", LogUserType.USER_LOGIN, "", "");
		return result;
	}
}