package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.print.PatientHeader;
import com.glenwood.glaceemr.server.application.models.print.PatientHeaderDetails;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.print.patientheader.PatientHeaderService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@RequestMapping(value="/user/GenericPatientHeader.Action")
public class PatientHeaderController {
	
	@Autowired
    PatientHeaderService patientHeaderService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(PatientHeaderController.class);
	
	/**
	 * Request to get the list of all patient headers for configuration
	 * 
	 */
	@RequestMapping(value = "/FetchPatientHeader",method = RequestMethod.GET)
	public EMRResponseBean fetchPatientHeaderList() throws Exception{
		logger.debug("Begin of request to get the list of all patient headers.");
		List<PatientHeader> patientHeaderList = patientHeaderService.getPatientHeaderList();
		logger.debug("End of request to get the list of all patient headers.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(patientHeaderList);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded header list for configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to get the list of all patient header details based on header id and page id for configuration
	 * 
	 */
	@RequestMapping(value = "/FetchPatientHeaderDetails",method = RequestMethod.GET)
	public EMRResponseBean fetchPatientHeaderDetailsList(@RequestParam(value="headerId") Integer headerId,@RequestParam(value="pageId") Integer pageId) throws Exception{
		logger.debug("Begin of request to get patient headers details based on header id and page id.");
		List<PatientHeaderDetails> patientHeaderDetails = patientHeaderService.getPatientHeaderDetailList(headerId,pageId);
		logger.debug("End of request to get patient headers details based on header id and page id.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(patientHeaderDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded patient headers details based on header id and page id", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId+"|pageId="+pageId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to save Patient header
	 * 
	 */
	@RequestMapping(value = "/savePatientHeader",method = RequestMethod.POST)
	public EMRResponseBean savePatientHeader(@RequestParam(value="headerName") String headerName,@RequestParam(value="headerType") Integer headerType,
			@RequestParam(value="isDefault") Boolean isDefault) throws Exception{
		logger.debug("Begin of request to Save patient header.");
		
		PatientHeader newPatientHeader=new PatientHeader();
		newPatientHeader.setPatientHeaderName(headerName);
		newPatientHeader.setPatientHeaderType(headerType);
		newPatientHeader.setPatientHeaderIsDefault(isDefault);
		newPatientHeader.setPatientHeaderIsActive(true);
		PatientHeader patientHeader = patientHeaderService.savePatientHeader(newPatientHeader);
		logger.debug("End of request to Save patient header.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(patientHeader);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved patient header", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerName="+headerName+"|headerType="+headerType, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to update Patient header
	 * 
	 */
	@RequestMapping(value = "/updatePatientHeader",method = RequestMethod.POST)
	public EMRResponseBean updatePatientHeader(@RequestParam(value="headerName") String headerName,@RequestParam(value="headerType") Integer headerType,
			@RequestParam(value="isDefault") Boolean isDefault,@RequestParam(value="isActive") Boolean isActive,@RequestParam(value="headerId") Integer headerId) throws Exception{
		logger.debug("Begin of request to Update Patient header.");
		PatientHeader updatePatientHeader=new PatientHeader();
		updatePatientHeader.setPatientHeaderId(headerId);
		updatePatientHeader.setPatientHeaderName(headerName);
		updatePatientHeader.setPatientHeaderType(headerType);
		updatePatientHeader.setPatientHeaderIsDefault(isDefault);
		updatePatientHeader.setPatientHeaderIsActive(isActive);
		PatientHeader patientHeader = patientHeaderService.savePatientHeader(updatePatientHeader);
		logger.debug("End of request to Update Patient header.");
		
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(patientHeader);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Successfully updated Patient header", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}  
	
	/**
	 * Request to delete patient header details
	 * 
	 */
	@RequestMapping(value = "/deletePatientHeaderDetails",method = RequestMethod.POST)
	public void deletePatientHeaderDetails(@RequestParam(value="headerId") Integer headerId,@RequestParam(value="pageId") Integer pageId) throws Exception{
		logger.debug("Begin of request to delete patient header details.");
		List<PatientHeaderDetails> patientHeaderDetails = patientHeaderService.getPatientHeaderDetailList(headerId,pageId);
		if(patientHeaderDetails!=null && !patientHeaderDetails.isEmpty()){
			patientHeaderService.deletePatientHeaderDetails(patientHeaderDetails);
		}
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.DELETE, 1, Log_Outcome.SUCCESS, "Successfully deleted patient header details", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId+"|pageId="+pageId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to delete patient header details.");
	}
	
	/**
	 * Request to save patient header details
	 * 
	 */
	@RequestMapping(value = "/savePatientHeaderDetails",method = RequestMethod.POST)
	public void savePatientHeaderDetails(@RequestParam(value="headerId") Integer headerId,@RequestParam(value="pageId") Integer pageId,
			@RequestParam(value="componentId") Integer componentId,@RequestParam(value="order") Integer order) throws Exception{
		logger.debug("Begin of request to save patient header details.");
	
		PatientHeaderDetails newPatientHeaderDetails=new PatientHeaderDetails();
		newPatientHeaderDetails.setPatientHeaderId(headerId);
		newPatientHeaderDetails.setPageId(pageId);
		newPatientHeaderDetails.setComponentId(componentId);
		newPatientHeaderDetails.setComponentOrder(order);
		patientHeaderService.savePatientHeaderDetails(newPatientHeaderDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved patient header details", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId+"|pageId="+pageId, LogUserType.USER_LOGIN, "", "");		
		logger.debug("End of request to save patient header details.");
		
	}

}
