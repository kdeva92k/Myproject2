package com.glenwood.glaceemr.server.application.controllers;

import java.util.Date;
import java.util.List;

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

import com.glenwood.glaceemr.server.application.models.ApptRequestBean;
import com.glenwood.glaceemr.server.application.models.BookingConfig;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointment;
import com.glenwood.glaceemr.server.application.models.SchedulerApptBookingBean;
import com.glenwood.glaceemr.server.application.services.portal.portalAppointments.PortalAppointmentsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;


@RestController
@Transactional
@RequestMapping("/user/PortalAppointments")
public class PortalAppointmentsController {

	@Autowired
	PortalAppointmentsService portalAppointmentsService;
	
	
	Logger logger=LoggerFactory.getLogger(PortalAppointmentsController.class);
	
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/AppointmentsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientAppointmentsList(@RequestParam(value="patientId", required=false, defaultValue="0") int patientId,
			 @RequestParam(value="appointmentsType", required=false, defaultValue="present") String appointmentsType,
			 @RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			List<SchedulerAppointment> patientAppointmentsList;
			if(appointmentsType.equalsIgnoreCase("all"))
				patientAppointmentsList=portalAppointmentsService.getPatientTotalAppointmentsList(patientId, pageOffset, pageIndex);
			else if(appointmentsType.equalsIgnoreCase("future"))
				 patientAppointmentsList=portalAppointmentsService.getPatientFutureAppointmentsList(patientId, pageOffset, pageIndex);
			else if(appointmentsType.equalsIgnoreCase("past"))
				patientAppointmentsList=portalAppointmentsService.getPatientPastAppointmentsList(patientId, pageOffset, pageIndex);
			else 
				patientAppointmentsList=portalAppointmentsService.getPatientTodaysAppointmentsList(patientId, pageOffset, pageIndex);
			responseBean.setSuccess(true);
			responseBean.setData(patientAppointmentsList);
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient appointments list!");
			return responseBean;
		}
	}
	
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/PatientAppointments", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientAppointments( @RequestParam(value="patientId", required=false, defaultValue="0") int patientId,
			 @RequestParam(value="appointmentsType", required=false, defaultValue="present") String appointmentsType,
			@RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			 @RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalAppointmentsService.getPatientAppointments(patientId, pageOffset, pageIndex, appointmentsType));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient appointments list!");
			return responseBean;
		}
			
	}
	
	/**
	 * Appointment Requests list of a patient.
	 * @param patientId		: id of the required patient.
	 * @return List of Appointments Requests of a patient.
	 */
	@RequestMapping(value = "/AppointmentRequestsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientAppointmentRequestsList( @RequestParam(value="patientId", required=false, defaultValue="0") int patientId,
			 @RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			 @RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalAppointmentsService.getPortalApptRequestList(patientId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient appointment requests list!");
			return responseBean;
		}
		
	}
	
	/**
	 * Appointment list of a patient appointments.
	 * @param patientId		: id of the required patient.
	 * @param appointmentsType		    : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/AppointmentDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAppointmentDetails() throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalAppointmentsService.getAppointmentDetails());
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving appointment details!");
			return responseBean;
		}
		
	}
	
	
	/**
	 * Booked Slots List by resourceId and apptDate
	 * @return List of Booked Slots
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/BookedSlots", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getBookedSlotsList( @RequestParam(value="resourceId", required=false, defaultValue="0") int resourceId,
			 @RequestParam(value="apptDate", required=false, defaultValue="") String apptDate) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			System.out.println("apptDate= "+apptDate);
			String s=apptDate;

			String s1 = s.split("00:00:00")[0];
			String s2 = s.split("00:00:00")[1];
			String s3 = s2.split(" ")[2];
			
			responseBean.setData(portalAppointmentsService.getBookedSlots(resourceId, new Date(s1+" "+s3)));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving provider's booked slots list1");
			return responseBean;
		}
	}
	
	
	/**
	 * Locked Slots List by resourceId and apptDate
	 * @return List of Locked Slots
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/LockedSlots", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getLockedSlotsList( @RequestParam(value="resourceId", required=false, defaultValue="0") int resourceId,
			 @RequestParam(value="apptDate", required=false, defaultValue="") String apptDate) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			System.out.println("apptDate= "+apptDate);
			String s=apptDate;

			String s1 = s.split("00:00:00")[0];
			String s2 = s.split("00:00:00")[1];
			String s3 = s2.split(" ")[2];
			
			responseBean.setData(portalAppointmentsService.getLockedSlots(resourceId, new Date(s1+" "+s3)));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving provider's locked slots list!");
			return responseBean;
		}
	}
	
	/**
	 * Appointment Booking Locations.
	 * @return List of Appointment Booking Locations
	 */
	@RequestMapping(value = "/AppointmentBookLocationsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAppointmentBookLocationsList() throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalAppointmentsService.getApptBookLocationList());
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving appointment booking locations list!");
			return responseBean;
		}
	}
	

	/**
	 * Appointment Booking Locations.
	 * @return List of Appointment Booking Categories
	 */
	@RequestMapping(value = "/AppointmentBookCategoriesList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAppointmentBookCategoriesList() throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalAppointmentsService.getSchResourceCategoriesList());
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving appointment booking categories list!");
			return responseBean;
		}
	}
	
	
	
	/**
	 * Appointment Booking Doctors.
	 * @return List of Appointment Booking Doctors
	 */
	@RequestMapping(value = "/AppointmentBookDoctorsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getApptBookDoctorsList(@RequestParam(value="posId", required=false, defaultValue="0") int posId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalAppointmentsService.getApptBookDoctorsList(posId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving appointment providers list!");
			return responseBean;
		}
		
	}
	
	
	/**
	 * Free Slots for Appointment Booking
	 * @param providerId  :
	 * @param apptDate  :
	 * @return List of Free Slots
	 */
	@RequestMapping(value = "/Appointment/FreeSlots", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getApptFreeSlotsByProviderIdAndDate( @RequestParam(value="providerId", required=false, defaultValue="0") int providerId,
			 @RequestParam(value="apptDate", required=false, defaultValue="") String apptDate){

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			System.out.println("apptDate= "+apptDate);
			String s=apptDate;

			String s1 = s.split("00:00:00")[0];
			String s2 = s.split("00:00:00")[1];
			String s3 = s2.split(" ")[2];

			responseBean.setData(portalAppointmentsService.getApptFreeSlotsByProviderIdAndDate(providerId, new Date(s1+" "+s3)));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving provider's free slots list!");
			return responseBean;
		}
		
	}
	
	
	/**
	 * @param patientId : credit card payment details bean 
	 * @return Transaction summary details bean
	 * @throws Exception
	 */
	@RequestMapping(value = "/Appointment/AppointmentRequest", method = RequestMethod.POST)
	@ResponseBody
	public  EMRResponseBean createPortalAppointmentRequestAlert(@RequestBody ApptRequestBean apptRequestAlertEventBean) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
			
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalAppointmentsService.createPortalAppointmentRequest(apptRequestAlertEventBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in requesting an appointment!");
			return responseBean;
		}
		
	}
	
	/**
	 * @return Booked Appointment Details bean
	 * @throws Exception
	 */
	@RequestMapping(value = "/Appointment/BookAppointment", method = RequestMethod.POST)
	@ResponseBody
	public  EMRResponseBean bookAppointment(@RequestBody SchedulerApptBookingBean schedulerApptBookingBean) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalAppointmentsService.bookAppointment(schedulerApptBookingBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in booking an appointment!");
			return responseBean;
		}
		
	}
	
	@RequestMapping(value="/Appointment/getBookingConfiguration",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getBookingConfiguration(){

		List<BookingConfig> BookingconfigDetailsResultList=portalAppointmentsService.getBookingConfigDetails();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(BookingconfigDetailsResultList);
		//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SCHEDULER, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting Scheduler resource categories.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");

		return emrResponseBean;
	}

	
}
