package com.glenwood.glaceemr.server.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.portal.portalLabResults.PortalLabResultsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

/**
 * @author Manne Teja
 *
 *         Controller for Lab results in Patient Portal, contains request to get
 *         the list of results, reviewed results. and ability to filter results.
 */
@RestController
@Transactional
@RequestMapping(value = "/user/PortalLabResults")
public class PortalLabResultsController {

	@Autowired
	PortalLabResultsService portalLabResultsService;

	/**
	 * Method to get list of lab results based on patient Id
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/PortalLabResultsConfigBean", method = RequestMethod.GET)
	public EMRResponseBean getPatientLabResultList()
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLabResultsService.getPortalLabResultsConfigBean());
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving portal labresults config bean!");
			return responseBean;
		}
	}

	/**
	 * Method to get list of lab results based on patient Id
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientLabResultsList", method = RequestMethod.GET)
	public EMRResponseBean getPatientLabResultList(
			@RequestParam(value = "patientId", required = false, defaultValue = "") int patientId,
			@RequestParam(value = "chartId", required = false, defaultValue = "") int chartId,
			@RequestParam(value = "pageOffset", required = false, defaultValue = "5") int pageOffset,
			 @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex)
					throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLabResultsService.getPatientLabResultsList(patientId, chartId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient's lab results list!");
			return responseBean;
		}
	}

	/**
	 * Method to get list of lab result parameters based on test detail Id
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientLabResultParametersList", method = RequestMethod.GET)
	public EMRResponseBean getPatientLabResultParametersList(
			@RequestParam(value = "testDetailId", required = false, defaultValue = "") int testDetailId,
			@RequestParam(value = "chartId", required = false, defaultValue = "") int chartId,
			@RequestParam(value = "patientId", required = false, defaultValue = "") int patientId,
			 @RequestParam(value = "pageOffset", required = false, defaultValue = "5") int pageOffset,
			 @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex)
					throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLabResultsService.getPatientLabResultParametersList(testDetailId, chartId, patientId, pageOffset,pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving lab result parameters list!");
			return responseBean;
		}
	}

	/**
	 * File Details of a Patient Lab Attachments.
	 * @param patientId		: id of the required patient.
	 * @param fileDetailEntityId		: fileDetail EntityId.
	 * @return File Details of a patient document.
	 */
	@RequestMapping(value = "/LabAttachments", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getLabAttachmentsDetails( @RequestParam(value="patientId", required=true, defaultValue="") int patientId,
			 @RequestParam(value="fileDetailEntityId", required=true, defaultValue="") int fileDetailEntityId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLabResultsService.getLabAttachmentsFileDetails(patientId, fileDetailEntityId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retieving lab attachemnts!");
			return responseBean;
		}
	}

}
