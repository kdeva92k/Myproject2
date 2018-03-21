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

import com.glenwood.glaceemr.server.application.models.PortalPasswordResetBean;
import com.glenwood.glaceemr.server.application.services.portal.RecoverPortalPasswordBean;
import com.glenwood.glaceemr.server.application.services.portal.portalRecoverUserPasswordService;
import com.glenwood.glaceemr.server.application.services.portal.portalAppointments.PortalAppointmentsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping("/login/RecoverPortalPassword")
public class PortalRecoverUserPasswordController {

	@Autowired
	PortalAppointmentsService portalAppointmentsService;
	
	@Autowired
	portalRecoverUserPasswordService portalRecoverPasswordService;
	
	Logger logger=LoggerFactory.getLogger(PortalRecoverUserPasswordController.class);
	
	/**
	 * Appointment Requests list of a patient.
	 * @param patientId		: id of the required patient.
	 * @return List of Appointments Requests of a patient.
	 */
	@RequestMapping(value = "/AuthenticateUsername", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean getPatientAppointmentRequestsList(@RequestBody RecoverPortalPasswordBean recoverPasswordBean) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(false);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalRecoverPasswordService.authenticateUsernameAndGetSecurityQuestions(recoverPasswordBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in authenticating the username!");
			return responseBean;
		}
	}
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/AuthenticateSecurityQuestions", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean getAuthenticationDetails(@RequestBody RecoverPortalPasswordBean recoverPasswordBean) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(false);

		try {
			return portalRecoverPasswordService.authenticateSecurityQuestions(recoverPasswordBean);
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in authenticating security questions!");
			return responseBean;
		}
	}	
	

@RequestMapping(value = "/Reset", method = RequestMethod.POST)
     @ResponseBody
     public EMRResponseBean resetPatientPassword(@RequestBody PortalPasswordResetBean portalPasswordResetBean) throws Exception{

         EMRResponseBean responseBean=new EMRResponseBean();

         responseBean.setCanUserAccess(true);
         responseBean.setIsAuthorizationPresent(true);
         responseBean.setLogin(false);

         try {
             responseBean.setData(portalRecoverPasswordService.resetPatientPassword(portalPasswordResetBean));
             return responseBean;
         } catch (Exception e) {
             e.printStackTrace();
             responseBean.setSuccess(false);
             responseBean.setData("Error in resetting the password!");
             return responseBean;
         }
     }

     /**
      * Appointment list of a patient appointments.
      * @param patientId : id of the required patient.
      * @param appointmentsType : indicates future or past or present day appointment .
      * @return List of Appointments of a patient.
      */
     @RequestMapping(value = "/update/resetToken", method = RequestMethod.POST)
     @ResponseBody
     public EMRResponseBean updatePortalPasswordResetToken(@RequestParam(value="patientId", required=false, defaultValue="") int patientId) throws Exception{

         EMRResponseBean responseBean=new EMRResponseBean();

         responseBean.setCanUserAccess(true);
         responseBean.setIsAuthorizationPresent(true);
         responseBean.setLogin(false);

         try {
             responseBean.setData(portalRecoverPasswordService.updatePasswordResetToken(patientId));
             return responseBean;
         } catch (Exception e) {
             e.printStackTrace();
             responseBean.setSuccess(false);
             responseBean.setData("Error in updaing the token!");
             return responseBean;
         }
     }



}
