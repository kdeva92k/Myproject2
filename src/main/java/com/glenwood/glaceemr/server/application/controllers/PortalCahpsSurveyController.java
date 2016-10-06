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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.models.CahpsQuestionnaire;
import com.glenwood.glaceemr.server.application.models.PatientCahpsSurvey;
import com.glenwood.glaceemr.server.application.models.PatientSurveySaveBean;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.portal.portalCahpsSurvey.PortalCahpsSurveyService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;


@RestController
@Transactional
@RequestMapping(value="/user/PortalCahpsSurvey")
@Api(value="PortalCahpsSurveyController", description="Handles all the requests regarding patient cahps survey.")
public class PortalCahpsSurveyController {

	@Autowired
	PortalCahpsSurveyService portalCahpsSurveyService;
	
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
	@RequestMapping(value = "/NewSurvey", method = RequestMethod.GET)
    @ApiOperation(value = "Returns patient's appointments list", notes = "Returns a complete list of appointments.", response = User.class)
	@ApiResponses(value= {
		    @ApiResponse(code = 200, message = "Successful retrieval of patient's appointments list"),
		    @ApiResponse(code = 404, message = "Patient with given id does not exist"),
		    @ApiResponse(code = 500, message = "Internal server error")})
	@ResponseBody
	public List<CahpsQuestionnaire> getPatientCahpsQuestionnaire(@ApiParam(name="patientAge", value="patient age to retrieve the corresponding cahps questionnaire") @RequestParam(value="patientAge", required=false, defaultValue="0") int patientAge) throws Exception{
		
		return portalCahpsSurveyService.getPatientCahpsSurveyQuestionnaire(patientAge);
	}
	
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/SaveSurvey", method = RequestMethod.POST)
    @ApiOperation(value = "Save the patient cahps survey", notes = "Returns a complete list of appointments.", response = User.class)
	@ApiResponses(value= {
		    @ApiResponse(code = 200, message = "Successful retrieval of patient's appointments list"),
		    @ApiResponse(code = 404, message = "Patient with given id does not exist"),
		    @ApiResponse(code = 500, message = "Internal server error")})
	@ResponseBody
	public PatientCahpsSurvey saveCahpsSurvey(@RequestBody PatientSurveySaveBean surveySaveBean) throws Exception{
		
		return portalCahpsSurveyService.saveCahpsSurvey(surveySaveBean);
	}
	
}