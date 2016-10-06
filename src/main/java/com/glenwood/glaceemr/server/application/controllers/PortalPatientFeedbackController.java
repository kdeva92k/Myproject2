package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.models.PatientFeedback;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackQuestionnaire;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackSaveBean;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.portal.portalPatientFeedback.PortalPatientFeedbackService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Transactional
@RequestMapping(value="/user/PortalPatientFeedback")
@Api(value="PortalPatientFeedbackController", description="Handles all the requests regarding patient feedback.")
public class PortalPatientFeedbackController {
	
	@Autowired
	PortalPatientFeedbackService portalPatientFeedbackService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	AuditTrailService auditTrailService;
	
	Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/NewFeedback", method = RequestMethod.GET)
    @ApiOperation(value = "Returns patient's feedback questionnaire", notes = "Returns a complete list of patient feedback questionnaire.", response = User.class)
	@ApiResponses(value= {
		    @ApiResponse(code = 200, message = "Successful retrieval of patient's feedback questionnaire"),
		    @ApiResponse(code = 404, message = "Patient with given id does not exist"),
		    @ApiResponse(code = 500, message = "Internal server error")})
	@ResponseBody
	public List<PatientFeedbackQuestionnaire> getPatientAppointmentsList() throws Exception{
		
		return portalPatientFeedbackService.getPatientFeedbackSurveyQuestionnaire();
	}
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/SaveFeedback", method = RequestMethod.POST)
    @ApiOperation(value = "Save the patient feedback", notes = "Returns the feedback summary.", response = User.class)
	@ApiResponses(value= {
		    @ApiResponse(code = 200, message = "Successful retrieval of patient's appointments list"),
		    @ApiResponse(code = 404, message = "Patient with given id does not exist"),
		    @ApiResponse(code = 500, message = "Internal server error")})
	@ResponseBody
	public PatientFeedback saveCahpsSurvey(@RequestBody PatientFeedbackSaveBean feedbackSaveBean) throws Exception{
		
		return portalPatientFeedbackService.savePatientFeedback(feedbackSaveBean);
	}

}