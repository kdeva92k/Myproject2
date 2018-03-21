package com.glenwood.glaceemr.server.application.controllers;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.PatientSurveySaveBean;
import com.glenwood.glaceemr.server.application.services.portal.portalCahpsSurvey.PortalCahpsSurveyService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;


@RestController
@Transactional
@RequestMapping(value="/user/PortalCahpsSurvey")
public class PortalCahpsSurveyController {

	@Autowired
	PortalCahpsSurveyService portalCahpsSurveyService;

	
	Logger logger=LoggerFactory.getLogger(PortalCahpsSurveyController.class);
	
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/NewSurvey", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientCahpsQuestionnaire( @RequestParam(value="patientAge", required=false, defaultValue="0") int patientAge) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalCahpsSurveyService.getPatientCahpsSurveyQuestionnaire(patientAge));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient cahps questionnaire!");
			return responseBean;
		}
		
	}
	
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/SaveSurvey", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveCahpsSurvey(@RequestBody PatientSurveySaveBean surveySaveBean) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalCahpsSurveyService.saveCahpsSurvey(surveySaveBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in saving patient cahps survey!");
			return responseBean;
		}
		
	}
	
}
