package com.glenwood.glaceemr.server.application.controllers;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.chart.flowsheet.FlowsheetGroupSaveBean;
import com.glenwood.glaceemr.server.application.services.chart.flowsheet.FlowsheetManagementSaveBean;
import com.glenwood.glaceemr.server.application.services.chart.flowsheet.FlowsheetService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@RequestMapping(value="/user/FlowsheetConfiguration")
public class FlowsheetManagementController {
	
	@Autowired(required=true)
	private FlowsheetService flowsheetService;

	@Autowired
	AuditTrailService auditTrailService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(FlowsheetManagementController.class);
	
	/**
	 * Request to get the flowsheet basic details for configuration
	 * @param loadFromFlowsheet
	 * @param flowsheetId
	 * @param flowsheetTypeId
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetManagement",method = RequestMethod.GET)
	public EMRResponseBean getFlowsheetsManagementDetails(
			@RequestParam(value="loadFromFlowsheet", required=false, defaultValue="-1") Integer loadFromFlowsheet,
			@RequestParam(value="flowsheetId", required=false, defaultValue="-1") Integer flowsheetId,
			@RequestParam(value="flowsheetTypeId", required=false, defaultValue="-1") Integer flowsheetTypeId) throws Exception{
		logger.debug("Begin of request to get the flowsheets basic details for configuration.");
//		FS_Management flowsheetsManagementDetails = flowsheetService.getFlowsheetsManagementDetails(loadFromFlowsheet,flowsheetId,flowsheetTypeId);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Flowsheet,AuditLogConstants.Flowsheet,1,AuditLogConstants.SUCCESS,"Successfully loaded data flowsheet basic details for configuration",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Flowsheet,request,"Successfully loaded data flowsheet basic details for configuration");
		logger.debug("End of request to get the flowsheets basic details for configuration.");
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.getFlowsheetsManagementDetails(loadFromFlowsheet,flowsheetId,flowsheetTypeId));
        return emrResponseBean;
	}
	
	/**
	 * Request to get the configured details of flowsheet
	 * @param loadFromFlowsheet
	 * @param flowsheetId
	 * @param flowsheetTypeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetConfiguredDetails",method = RequestMethod.GET)
	public EMRResponseBean getFlowsheetsConfiguredDetails(
			 @RequestParam(value="flowsheetId", required=true, defaultValue="-1") Integer flowsheetId) throws Exception{
		logger.debug("Begin of request to get the configured details of flowsheet.");
//		FS_ConfiguredDetails flowsheetsManagementDetails = flowsheetService.getFlowsheetsConfiguredDetails(flowsheetId);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Flowsheet,AuditLogConstants.Flowsheet,1,AuditLogConstants.SUCCESS,"Successfully loaded configured details of flowsheet for flowsheet id="+flowsheetId,-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Flowsheet,request,"Successfully loaded configured details of flowsheet for flowsheet id="+flowsheetId);
		logger.debug("End of request to get the configured details of flowsheet.");
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.getFlowsheetsConfiguredDetails(flowsheetId));
        return emrResponseBean;
	}
	
	/**
	 * Request to get the group details of lab and params
	 * @param isStandardLab
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetStandardGroupDetails",method = RequestMethod.GET)
	public EMRResponseBean getFlowsheetsStandardGroupDetails(
			 @RequestParam(value="isStandardLab", required=true, defaultValue="-1") Integer isStandardLab) throws Exception{
		logger.debug("Begin of request to get group details of lab and params.");
//		FS_ConfiguredDetails flowsheetsManagementDetails = flowsheetService.getFlowsheetsStandardGroupDetails(isStandardLab);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Flowsheet,AuditLogConstants.Flowsheet,1,AuditLogConstants.SUCCESS,"Successfully loaded standard group details of flowsheet",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Flowsheet,request,"Successfully loaded standard group details of flowsheet");
		logger.debug("End of request to get group details of lab and params.");
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.getFlowsheetsStandardGroupDetails(isStandardLab));
        return emrResponseBean;
	}
	
	/** 
	 * Request to save the group details of lab and params
	 * @param saveData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetStandardGroupDetailsSave",method = RequestMethod.POST)
	public EMRResponseBean saveFlowsheetsStandardGroupDetails(@RequestBody FlowsheetGroupSaveBean saveData) throws Exception{
		logger.debug("Begin of request to save group details of lab and params.");
//		FS_ConfiguredDetails flowsheetsManagementDetails = flowsheetService.saveFlowsheetsStandardGroupDetails(saveData.getIsStandardLab(),URLDecoder.decode(saveData.getGroupData(), "UTF-8"));
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Flowsheet,AuditLogConstants.Flowsheet,1,AuditLogConstants.SUCCESS,"Successfully saved standard group details of flowsheet",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Flowsheet,request,"Successfully saved standard group details of flowsheet");
		logger.debug("End of request to save group details of lab and params.");
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.saveFlowsheetsStandardGroupDetails(saveData.getIsStandardLab(),URLDecoder.decode(saveData.getGroupData(), "UTF-8")));
        return emrResponseBean;
	}
	
	/**
	 * Request to save the flowsheet details of lab and params
	 * @param saveData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/FlowsheetDetailsSave",method = RequestMethod.POST)
	public EMRResponseBean saveFlowsheetsDetails(@RequestBody FlowsheetManagementSaveBean saveData) throws Exception{
		logger.debug("Begin of request to save group details of lab and params.");
//		FS_Management flowsheetsManagementDetails = flowsheetService.saveFlowsheetsManagmentDetails(saveData.getSheetId(),saveData.getSheetTypeId(),saveData.getSheetName(),URLDecoder.decode(saveData.getDataToSave(), "UTF-8"));
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Flowsheet,AuditLogConstants.Flowsheet,1,AuditLogConstants.SUCCESS,"Successfully saved standard group details of flowsheet",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Flowsheet,request,"Successfully saved standard group details of flowsheet");
		logger.debug("End of request to save group details of lab and params.");
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.saveFlowsheetsManagmentDetails(saveData.getSheetId(),saveData.getSheetTypeId(),saveData.getSheetName(),URLDecoder.decode(saveData.getDataToSave(), "UTF-8")));
        return emrResponseBean;
	}

	/**
	 * Request to get the HMR details
	 * @param saveData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/HMRRuleDetails",method = RequestMethod.GET)
	public EMRResponseBean getHMRDetail(
			@RequestParam(value="groupIds", required=true, defaultValue="-1") String groupId,
			@RequestParam(value="flowsheetElementType", required=true, defaultValue="-1") Integer flowsheetElementType) throws Exception{
		logger.debug("Begin of request to get the HMR details.");
//		HmrRuleBean flowsheetData = flowsheetService.getFlowSheetRuleQuery(groupId,flowsheetElementType);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.HMR,AuditLogConstants.HMR,1,AuditLogConstants.SUCCESS,"Successfully loaded data for request to get the HMR details having groupIds="+groupId+" and flowsheet element type="+flowsheetElementType,-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.HMR,request,"Successfully loaded data for request to get the HMR details having groupIds="+groupId+" and flowsheet element type="+flowsheetElementType);
		logger.debug("End of request to get the HMR details.");
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(flowsheetService.getFlowSheetRuleQuery(groupId,flowsheetElementType));
        return emrResponseBean;
	}
}