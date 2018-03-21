package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.glenwood.glaceemr.server.application.services.chart.flowsheet.FlowsheetService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller to get the flowsheet data
 * @author smita
 *
 */

@RestController
@RequestMapping(value="/user/Flowsheet")
public class FlowsheetController {
	
	@Autowired(required=true)
	FlowsheetService flowsheetService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(FlowsheetController.class);
	
	/**
	 * Request to get the list of flowsheets configured
	 * @param flowsheetType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/Flowsheets",method = RequestMethod.GET)
	public EMRResponseBean getFlowsheets(
			 @RequestParam(value="flowsheetType", required=true, defaultValue="-1") Integer flowsheetType) throws Exception{
		logger.debug("Begin of request to get the list of flowsheets based on type.");
//		List<Flowsheet> flowsheetPatData = flowsheetService.getFlowsheetPatientData(flowsheetType);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.FLOWSHEET,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Successfully loaded data for flowsheet type="+flowsheetType, sessionMap.getUserID(),request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");logger.debug("End of request to get the list of flowsheets based on type.");
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.getFlowsheetPatientData(flowsheetType));
		
		return emrResponseBean;
	}
	
	/**
	 * Request to get the complete flowsheet details for a particular flowsheet for a patient.
	 * @param flowsheetId
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetData",method = RequestMethod.GET)
	public EMRResponseBean getFlowsheetsDetails(
			 @RequestParam(value="flowsheetId", required=true, defaultValue="-1") Integer flowsheetId,
			 @RequestParam(value="patientId", required=true, defaultValue="-1") Integer patientId,
			 @RequestParam(value="encounterId", required=true, defaultValue="-1") Integer encounterId,
				@RequestParam(value="chartId", required=true, defaultValue="-1") Integer chartId)throws Exception{
		//logger.debug("Begin of request to get the complete flowsheet details based on flowsheet id and patient Id.");
	//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.FLOWSHEET,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Successfully loaded data for flowsheet for the patient having PatientId="+patientId,sessionMap.getUserID(),request.getRemoteAddr(),patientId,"encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		 emrResponseBean.setData(flowsheetService.getFlowsheetData(-1,flowsheetId,patientId,encounterId,chartId));
		return emrResponseBean;
	}
	
	
	/**
	 * Request to get the list of complete flowsheet details for a particular flowsheet for a patient for multiple sheets
	 * @param flowsheetType
	 * @param patientId
	 * @param encounterId
	 * @param dxCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetDataMultipleSheets",method = RequestMethod.GET)
	public EMRResponseBean getFlowsheetsDetailsList(
			 @RequestParam(value="flowsheetType", required=true, defaultValue="-1") Integer flowsheetType,
			  @RequestParam(value="patientId", required=true, defaultValue="-1") Integer patientId,
			  @RequestParam(value="encounterId", required=true, defaultValue="-1") Integer encounterId,
			  @RequestParam(value="dxCode", required=true, defaultValue="") String dxCode,
			  @RequestParam(value="chartId", required=true, defaultValue="-1") Integer chartId) throws Exception{
		//logger.debug("Begin of request to get the complete flowsheet details based on flowsheet id and patient Id for multiple sheets.");
//		List<FlowsheetBean> flowsheetData = flowsheetService.getFlowsheetDataList(flowsheetType,dxCode,patientId,encounterId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.FLOWSHEET,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Successfully loaded data for flowsheet type="+flowsheetType+" for the patient having PatientId="+patientId,sessionMap.getUserID(),request.getRemoteAddr(),patientId,"encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		//logger.debug("End of request to get the complete flowsheet details based on flowsheet id and patient Id for multiple sheets.");
	        EMRResponseBean emrResponseBean=new EMRResponseBean();
			emrResponseBean.setData(flowsheetService.getFlowsheetDataList(flowsheetType,dxCode,patientId,encounterId,chartId));
	        
	        return emrResponseBean;
	}
	
	/**
	 * Request to get the clinical details for a particular clinical element.
	 * @param chartId
	 * @param encounterId
	 * @param gwIds
	 * @param isVital
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetClinicalData",method = RequestMethod.GET)
	public EMRResponseBean getClinicalDetails(
			 @RequestParam(value="chartId", required=true, defaultValue="-1") Integer chartId,
			 @RequestParam(value="encounterId", required=true, defaultValue="-1") Integer encounterId,
			 @RequestParam(value="elementId", required=true, defaultValue="-1") String gwIds,
			 @RequestParam(value="isVital", required=true, defaultValue="-1") Integer isVital) throws Exception{
		logger.debug("Begin of request to get the clinical details for a particular clinical element based on glenwood id, patient id and encounter id.");
//		List<FS_ClinicalElementOptionBean> flowsheetData = flowsheetService.getClinicalElements(chartId, encounterId, gwIds, isVital);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.FLOWSHEET,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Successfully loaded data for clinical element="+gwIds+" for the patient having chartId="+chartId+" and encounterId="+encounterId,sessionMap.getUserID(),request.getRemoteAddr(),-1,"encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to get the clinical details for a particular clinical element based on glenwood id, patient id and encounter id.");
		
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.getClinicalElements(chartId, encounterId, gwIds, isVital));
        
        return emrResponseBean;
	}
	
	/**
	 * Request to get the lab details for recommended labs.
	 * @param chartId
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetRecommendedLabDetails",method = RequestMethod.GET)
	public EMRResponseBean getRecommendedLabDetails(
			 @RequestParam(value="chartId", required=true, defaultValue="-1") Integer chartId,
			 @RequestParam(value="patientId", required=true, defaultValue="-1") Integer patientId,
			 @RequestParam(value="encounterId", required=true, defaultValue="-1") Integer encounterId) throws Exception{
		logger.debug("Begin of request to get the lab details for recommended labs.");
//		List<FlowsheetBean> flowsheetData = flowsheetService.getRecommendedLabs(chartId, patientId, encounterId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.FLOWSHEET,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Successfully loaded data for recommended labs for the patient having PatientId="+patientId,sessionMap.getUserID(),request.getRemoteAddr(),patientId,"encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to get the lab details for recommended labs.");
		
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.getRecommendedLabs(chartId, patientId, encounterId));
        
        return emrResponseBean;
	}

	/**
	 * request to get the list of overdue alerts.
	 * @param chartId
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetOverdueAlerts",method = RequestMethod.GET)
	public EMRResponseBean getFlowsheetOverdueAlerts(
			 @RequestParam(value="chartId", required=true, defaultValue="-1") Integer chartId,
			 @RequestParam(value="patientId", required=true, defaultValue="-1") Integer patientId,
			 @RequestParam(value="encounterId", required=true, defaultValue="-1") Integer encounterId) throws Exception{
		logger.debug("Begin of request to get the list of overdue alerts.");
//		List<OverdueAlert> flowsheetData = flowsheetService.overdueLabs(patientId,chartId, encounterId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.FLOWSHEET,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Successfully loaded data for the list of overdue alerts.",sessionMap.getUserID(),request.getRemoteAddr(),patientId,"encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to get the list of overdue alerts.");
		
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.overdueLabs(patientId,chartId, encounterId));
        
        return emrResponseBean;
	}
	
	/**
	 * Request to get the flowsheet details for labs and params section(as on 2016/01/21) for a particular flowsheet for a patient.
	 * @param flowsheetId
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetDataSOAP",method = RequestMethod.GET)
	public EMRResponseBean getFlowsheetsDetailsSOAP(
			 @RequestParam(value="flowsheetId", required=true, defaultValue="-1") Integer flowsheetId,
			 @RequestParam(value="patientId", required=true, defaultValue="-1") Integer patientId,
			 @RequestParam(value="encounterId", required=true, defaultValue="-1") Integer encounterId,
			 @RequestParam(value="chartId", required=true, defaultValue="-1") Integer chartId) throws Exception{
		logger.debug("Begin of request to get the flowsheet details for labs and params section for a particular flowsheet for a patient");
//		FlowsheetBean flowsheetData = flowsheetService.getFlowsheetDataSOAP(-1,flowsheetId,patientId,encounterId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.FLOWSHEET,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Successfully loaded data for flowsheet="+flowsheetId+" for the patient having PatientId="+patientId,sessionMap.getUserID(),request.getRemoteAddr(),patientId,"encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to get the complete flowsheet details based on flowsheet id and patient Id.");
		
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.getFlowsheetDataSOAP(-1,flowsheetId,patientId,encounterId,chartId));
        
        return emrResponseBean;
	}
	
}