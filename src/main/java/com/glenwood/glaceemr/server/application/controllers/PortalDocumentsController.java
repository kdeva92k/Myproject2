package com.glenwood.glaceemr.server.application.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.portal.portalDocuments.PortalDocumentsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping(value="/user/PortalPatientDocuments")
public class PortalDocumentsController {
	
	@Autowired
	PortalDocumentsService documentsService;
	
	
	/**
	 * Shared Documents list of a patient.
	 * @param patientId		: id of the required patient.
	 * @return List of shared documents of a patient.
	 */
	@RequestMapping(value = "/Documents/PatientSharedDocs", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientSharedDocs( @RequestParam(value="patientId", required=true, defaultValue="") int patientId,
			 @RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			 @RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(documentsService.getPatientSharedDocs(patientId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient's shared docs list!");
			return responseBean;
		}
	}
	
	
	
	/**
	 * Shared Documents list of a patient.
	 * @param patientId		: id of the required patient.
	 * @return List of shared documents of a patient.
	 */
	@RequestMapping(value = "/VisitSummary/PatientVisitSummary", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientVisitSummary( @RequestParam(value="patientId", required=true, defaultValue="") int patientId,
			 @RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(documentsService.getPatientVisitSummary(patientId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient's visit summary list!");
			return responseBean;
		}
	}
	
	
	
	/**
	 * File Details of a Patient Document.
	 * @param patientId		: id of the required patient.
	 * @param fileId		: id of the file.
	 * @return File Details of a patient document.
	 */
	@RequestMapping(value = "/Documents/FileDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientFileDetails(@RequestParam(value="patientId", required=true, defaultValue="") int patientId,
			@RequestParam(value="fileId", required=true, defaultValue="") int fileId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(documentsService.getFileDetails(patientId, fileId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient file details!");
			return responseBean;
		}
	}
	
	
	/**
	 * File Details of a Patient Document.
	 * @param patientId		: id of the required patient.
	 * @param fileId		: id of the file.
	 * @return File Details of a patient document.
	 */
	@RequestMapping(value = "/Documents/FileNamesList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientFileNameDetails( @RequestParam(value="patientId", required=true, defaultValue="") int patientId,
			@RequestParam(value="fileDetailsId", required=true, defaultValue="") int fileDetailsId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(documentsService.getFileNameDetails(patientId, fileDetailsId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient file details list!");
			return responseBean;
		}
	}
	
	/**
	 * File Details of a Patient Document.
	 * @param patientId		: id of the required patient.
	 * @param fileId		: id of the file.
	 * @return File Details of a patient document.
	 */
	@RequestMapping(value = "/VisitSummary/PatientCDA", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientCDAFileDetails( @RequestParam(value="patientId", required=true, defaultValue="") int patientId,
			 @RequestParam(value="encounterId", required=true, defaultValue="") int encounterId,
			@RequestParam(value="fileName", required=true, defaultValue="") String fileName) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(documentsService.getPatientCDAFileDetails(patientId, encounterId, fileName));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient's CDA!");
			return responseBean;
		}
	}
	

}
