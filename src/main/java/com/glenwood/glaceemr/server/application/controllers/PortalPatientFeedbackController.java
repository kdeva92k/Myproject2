package com.glenwood.glaceemr.server.application.controllers;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.PatientFeedbackSaveBean;
import com.glenwood.glaceemr.server.application.services.portal.portalPatientFeedback.PortalPatientFeedbackService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping(value="/user/PortalPatientFeedback")
public class PortalPatientFeedbackController {
	
	@Autowired
	PortalPatientFeedbackService portalPatientFeedbackService;
	
	Logger logger=LoggerFactory.getLogger(PortalPatientFeedbackController.class);
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/NewFeedback", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientAppointmentsList() throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPatientFeedbackService.getPatientFeedbackSurveyQuestionnaire());
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving feedback questionnaire!");
			return responseBean;
		}
	}
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/SaveFeedback", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveCahpsSurvey(@RequestBody PatientFeedbackSaveBean feedbackSaveBean) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPatientFeedbackService.savePatientFeedback(feedbackSaveBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in saving patient feedback!");
			return responseBean;
		}
	}

}
