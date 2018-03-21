package com.glenwood.glaceemr.server.application.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.SchedulerApptBookingBean;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeService;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PatientRegistrationSetupFields;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PortalPatientRegistrationBean;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PortalSettingsService;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PrincipalDoctor;
import com.glenwood.glaceemr.server.application.services.portalLogin.PortalLoginService;
import com.glenwood.glaceemr.server.datasource.TennantContextHolder;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;


@RestController
@RequestMapping("/login/PatientRegistration")
public class PortalPatientRegistrationController {


	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	PortalSettingsService portalSettingsService;

	@Autowired
	PortalLoginService portalLoginService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EMRResponseBean responseBean;

	Logger logger=LoggerFactory.getLogger(PortalPatientRegistrationController.class);

	/**
	 * @return List of patient profile settings fields with available options.
	 * @throws Exception
	 */
	@RequestMapping(value = "/RegistrationSetupFields", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientProfileSettingsFieldsOprions(){


		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			
			PatientRegistrationSetupFields setupFields=new PatientRegistrationSetupFields();
			setupFields.setPatientProfileSettingsFields(portalSettingsService.getPatientProfileSettingsFieldsList());
			setupFields.setPortalBillingConfigFields(portalSettingsService.getPortalBillingConfigFields());

			List<EmployeeProfile> doctorsList=portalSettingsService.getProvidersList();

			List<PrincipalDoctor> principalDoctorsList=new ArrayList<PrincipalDoctor>();

			for(int i=0;i<doctorsList.size();i++){
				PrincipalDoctor doctor=new PrincipalDoctor();
				doctor.setPrincipalDoctorId(doctorsList.get(i).getEmpProfileEmpid());
				doctor.setPrincipalDoctorName(doctorsList.get(i).getEmpProfileFullname());
				principalDoctorsList.add(doctor);
			}

			setupFields.setPrincipalDoctorsList(principalDoctorsList);
			
			responseBean.setSuccess(true);
			responseBean.setData(setupFields);
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient registration setup fields!");
			return responseBean;
		}

	}


	/**
	 * @return List of patient profile settings fields with available options.
	 * @throws Exception
	 */
	@RequestMapping(value = "/UsernameVerification", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean verifyUsername(@RequestParam(value="username", required=false, defaultValue="-1") String username,
			@RequestParam(value="dob", required=false, defaultValue="-1") String dob,
			@RequestParam(value="firstName", required=false, defaultValue="-1") String firstName,
			@RequestParam(value="lastName", required=false, defaultValue="-1") String lastName){

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLoginService.checkDuplicatePatientData(username, dob, firstName, lastName));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in veryfying the username!");
			return responseBean;
		}

	}

	/**
	 * @return List of patient profile settings fields with available options.
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws JSONException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/RegisterNewUserForPortal", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean registerNewUser(@RequestBody PortalPatientRegistrationBean registrationDetailsBean) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException, JSONException{

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLoginService.registerNewUserForPortal(registrationDetailsBean, TennantContextHolder.getTennantId()));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in registering the new patient for patient portal and emr!");
			return responseBean;
		}
	}
	
	/**
	 * @return List of patient profile settings fields with available options.
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws JSONException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/RegisterExistingUserForPortal", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean registerExistingUser(@RequestBody PortalPatientRegistrationBean registrationDetailsBean) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException, JSONException{

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLoginService.registerExistingUserForPortal(registrationDetailsBean, TennantContextHolder.getTennantId()));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in registering the existing emr patient for patient portal!");
			return responseBean;
		}
	}
	
	/**
	 * @return List of patient profile settings fields with available options.
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws JSONException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/RequestSignupCredentials", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean requestSignupCredentials(@RequestBody PortalPatientRegistrationBean registrationDetailsBean) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException, JSONException{

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLoginService.requestSignupCredentials(registrationDetailsBean, TennantContextHolder.getTennantId()));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving the signup credentials!");
			return responseBean;
		}
	}
	
	
	/**
	 * @return List of patient profile settings fields with available options.
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws JSONException 
	 * @throws Exception
	 */
	@RequestMapping(value = "/ActivateAccount", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean activateAccount(@RequestParam(value="patientId", required=false, defaultValue="0") int patientId,
			@RequestParam(value="practiceId", required=false, defaultValue="0") String practiceId) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException, JSONException{

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalLoginService.activateUserAccount(patientId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in activating the patient portal account!");
			return responseBean;
		}
	}

}
