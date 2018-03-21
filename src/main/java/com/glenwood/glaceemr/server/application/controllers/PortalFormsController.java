package com.glenwood.glaceemr.server.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.portal.portalForms.PortalConsentFormDetailsBean;
import com.glenwood.glaceemr.server.application.services.portal.portalForms.PortalFormsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping("/user/PortalForms")
public class PortalFormsController {

	@Autowired
	PortalFormsService portalFormsService;

	Logger logger = LoggerFactory.getLogger(PortalFormsController.class);

	/**
	 * Appointment list of a patient appointments.
	 * 
	 * @param patientId
	 *            : id of the required patient.
	 * @param appointmentsType
	 *            : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/ClinicalIntakeAndConsent/FormsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientClinicalIntakeAndConsentFormsList(
			@RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			 @RequestParam(value = "chartId", required = false, defaultValue = "0") int chartId,
			 @RequestParam(value = "isXML", required = false, defaultValue = "0") int isXML)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalFormsService
					.getPatientClinicalIntakeAndConsentFormsList(patientId,
							chartId, isXML));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean
					.setData("Error in retrieving patient's clinical and intake forms list!");
			return responseBean;
		}
	}

	/**
	 * Appointment list of a patient appointments.
	 * 
	 * @param patientId
	 *            : id of the required patient.
	 * @param appointmentsType
	 *            : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/ClinicalIntake/FormsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientClinicalIntakeFormsList(
			 @RequestParam(value = "isXML", required = false, defaultValue = "0") int isXML)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalFormsService
					.getPatientClinicalIntakeFormsList(isXML));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean
					.setData("Error in retrieving patient's intake forms list!");
			return responseBean;
		}
	}

	/**
	 * Appointment list of a patient appointments.
	 * 
	 * @param patientId
	 *            : id of the required patient.
	 * @param appointmentsType
	 *            : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/IntakeFormsQuestionnaire", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getIntakeFormsQuestionnaire(
			 @RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			 @RequestParam(value = "groupId", required = false, defaultValue = "present") int groupId,
			@RequestParam(value = "pageOffset", required = false, defaultValue = "5") int pageOffset,
			 @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalFormsService
					.getLibraryQustionnaireElements(patientId, groupId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean
					.setData("Error in retrieving intake forms questionnaire!");
			return responseBean;
		}
	}

	/**
	 * Appointment list of a patient appointments.
	 * 
	 * @param patientId
	 *            : id of the required patient.
	 * @param appointmentsType
	 *            : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/ClinicalIntake/Form", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientClinicalIntakeForm(
			@RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			 @RequestParam(value = "requestId", required = false, defaultValue = "-1") int requestId,
			@RequestParam(value = "mode", required = false, defaultValue = "-1") int mode,
			@RequestParam(value = "groupId", required = false, defaultValue = "-1") int groupId,
			 @RequestParam(value = "groupName", required = false, defaultValue = "") String groupName,
			 @RequestParam(value = "isXML", required = false, defaultValue = "-1") int isXML,
			 @RequestParam(value = "tabId", required = false, defaultValue = "-1") int tabId,
			 @RequestParam(value = "isXML", required = false, defaultValue = "-1") int retXML,
			 @RequestParam(value = "pageOffset", required = false, defaultValue = "5") int pageOffset,
			 @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalFormsService
					.getClinicalIntakePatientXml(patientId, requestId, mode,
							groupId, groupName, isXML, retXML, tabId,
							pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean
					.setData("Error in retrieving patient clinical intake form!");
			return responseBean;
		}
	}

	/**
	 * Appointment list of a patient appointments.
	 * 
	 * @param patientId
	 *            : id of the required patient.
	 * @param appointmentsType
	 *            : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/Consent/FormsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getIncompletePatientConsentFormsList(
			@RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			 @RequestParam(value = "chartId", required = false, defaultValue = "0") int chartId)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalFormsService
					.getIncompletePatientConsentFormsList(patientId, chartId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean
					.setData("Error in retrieving patient's consent forms list!");
			return responseBean;
		}
	}

	/**
	 * Appointment list of a patient appointments.
	 * 
	 * @param patientId
	 *            : id of the required patient.
	 * @param appointmentsType
	 *            : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/Consent/FormDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getIncompletePatientConsentFormsList(
			 @RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			 @RequestParam(value = "chartId", required = false, defaultValue = "0") int chartId,
			 @RequestParam(value = "fileTemplateId", required = false, defaultValue = "0") int fileTemplateId)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalFormsService
					.getIncompletePatientConsentFormsDetails(patientId,
							chartId, fileTemplateId));
			
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving consent form details!");
			return responseBean;
			
		}
	}

	/**
	 * Appointment list of a patient appointments.
	 * 
	 * @param patientId
	 *            : id of the required patient.
	 * @param appointmentsType
	 *            : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/Consent/SaveForm", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean savePatientConsentForm(
			@RequestBody PortalConsentFormDetailsBean consentSaveBean)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalFormsService
					.savePatientConsentForm(consentSaveBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in saving consent form!");
			return responseBean;
		}
	}

}
