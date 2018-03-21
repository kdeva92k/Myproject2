package com.glenwood.glaceemr.server.application.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.services.portal.portalMedicalSummary.PortalMedicalSummaryService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;


@RestController
@Transactional
@RequestMapping(value="/user/PortalMedicalSummary")
public class PortalMedicalSummaryController {

	@Autowired
	PortalMedicalSummaryService portalMedicalSummaryService;
	
	@Autowired
	WebApplicationContext context;
	
	/**
	 * @param username 		: username of a patient
	 * @return List of patient details
	 * @throws Exception
	 */
	@RequestMapping(value = "/SessionMap", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getSessionMap( @RequestParam(value="dbname", required=false, defaultValue="") String dbname,
			 @RequestParam(value="username", required=false, defaultValue="") String username) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getSessionMap(username));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving portal session!");
			return responseBean;
		}
	}
	
	
	/**
	 * @param username 		: username of a patient
	 * @return List of patient details
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientDetailsByUsername(@RequestParam(value="username", required=false, defaultValue="") String username) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPatientDetailsByUsername(username));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient details!");
			return responseBean;
		}
	}
	
	
	
	
	
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's personal details
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientPersonalDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientPersonalDetails(@RequestParam(value="patientId", required=false, defaultValue="") int patientId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPatientPersonalDetails(patientId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient personal details!");
			return responseBean;
		}
	}
	

	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's problems
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientProblemsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientProblemsList(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="problemType", required=false, defaultValue="") String problemType,
			@RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPatientProblemList(patientId, problemType, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient problems list!");
			return responseBean;
		}
	}
	
     
	
	/**
	 * @param chartId 		: Chart id of a required patient 
	 * @return List of patient's problems
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientAllergies", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientAllergies(@RequestParam(value="chartId", required=false, defaultValue="") int chartId,
			@RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPatientAllergies(chartId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient allergies!");
			return responseBean;
		}
	}
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @param chartId 		: Required patient's chart id 
	 * @return List of plans of care of a patient.
	 * @throws Exception
	 */
	@RequestMapping(value = "/PlanOfCare/PatientPlanOfCare", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientPlanOfCareList(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="chartId", required=false, defaultValue="") int chartId,
			@RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPlanOfCare(patientId, chartId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient plan of care!");
			return responseBean;
		}
	}
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @param chartId 		: Required patient's chart id 
	 * @return List of plans of care plan types list of a patient.
	 * @throws Exception
	 */
	@RequestMapping(value = "/PlanOfCare/PatientPlanTypesList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientPlanOfCarePlanTypesList(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="encounterId", required=false, defaultValue="") int encounterId,
			@RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPlanOfCareDetails(patientId, encounterId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving plan of care plan types list!");
			return responseBean;
		}
	}
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @param chartId 		: Required patient's chart id 
	 * @return PortalMedicalSummaryBean.
	 * @throws Exception
	 */
	@RequestMapping(value = "/MedicalSummaryDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPortalMedicalSummaryDetails(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="chartId", required=false, defaultValue="") int chartId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPortalMedicalSummaryDetails(patientId, chartId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient medical summary details!");
			return responseBean;
		}
	}
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @param chartId 		: Required patient's chart id 
	 * @return List of Patient Medication .
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientMediationList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientMediationList(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="chartId", required=false, defaultValue="") int chartId) throws Exception{
		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPatientMedication(patientId, chartId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving Medication  list!");
			return responseBean;
		}
	}
	
	
	@RequestMapping(value = "/Vital/VitalListByPatient", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientVitalList(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="chartId", required=false, defaultValue="") int chartId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMedicalSummaryService.getPatientVital(patientId, chartId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving vital  list!");
			return responseBean;
		}
	}
	
	@RequestMapping(value = "/PatientAllergiesByPatientIdNewMedicalSummary", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientAllergiesByPatientIdForNewMedicalSummary(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=context.getBean(EMRResponseBean.class);
		
		List<PatientAllergies> patientAllergiesList=portalMedicalSummaryService.getPatientAllergiesByPatientIdNew(patientId, pageOffset, pageIndex);
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(patientAllergiesList.toArray());
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving patient allergies!");
			return responseBean;
		}
	}
	
	
	@RequestMapping(value = "/log", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean putlog(@RequestParam(value="patientId", required=false, defaultValue="-1") int patientId,
			@RequestParam(value="chartId", required=false, defaultValue="") int chartId) throws Exception{
		EMRResponseBean responseBean=new EMRResponseBean();
		portalMedicalSummaryService.putLog(patientId, chartId);
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		
		return responseBean;
		
	}
	
	@RequestMapping(value = "/getemailresponse", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getemailresponse(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="chartId", required=false, defaultValue="") int chartId,
			@RequestParam(value="fromDate", required=false, defaultValue="") String fromDate,
			@RequestParam(value="toDate", required=false, defaultValue="") String  toDate,
			@RequestParam(value="email", required=false, defaultValue="") String  email,
			@RequestParam(value="comments", required=false, defaultValue="") String  comments,
			@RequestParam(value="encounterids", required=false, defaultValue="") String  encounterids,
			@RequestParam(value="accountId", required=false, defaultValue="") String  accountId,
			@RequestParam(value="Transmitcheckboxflag", required=false, defaultValue="") int Transmitcheckboxflag) throws Exception{
		EMRResponseBean responseBean=new EMRResponseBean();
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			Date formattedFromDate = null;
			Date one=null;
			Timestamp ts1=null; 
			Timestamp ts2=null; 
			String time1="";
			String time2="";
			try{
				@SuppressWarnings("deprecation")
				Date sample = new Date(fromDate);
				String fromdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sample);
				ts1 = Timestamp.valueOf(fromdate);
				time1=ts1+"";
			}catch (Exception e){ e.printStackTrace(); }
			Date formattedToDate = null;
			try{

				@SuppressWarnings("deprecation")
				Date sample = new Date(toDate);
				String todate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sample);
				formattedToDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todate);
				ts2 = Timestamp.valueOf(todate);
				time2=ts2+"";
			}catch (Exception e){ e.printStackTrace(); }
			responseBean.setData(portalMedicalSummaryService.getemailresponse(patientId, chartId,time1,time2,email,comments,encounterids,accountId,Transmitcheckboxflag));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving Encounter  l"
					+ ""
					+ ""
					+ ""
					+ ""     
					+ "ist!");
			return responseBean;
		}
	}
	@RequestMapping(value = "/EncountersByPatient1", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean EncountersByPatient1(@RequestParam(value="patientId", required=false, defaultValue="-1") int patientId,
			@RequestParam(value="chartId", required=false, defaultValue="-1") int chartId,
			@RequestParam(value="fromDate", required=false, defaultValue="") String fromDate,
			@RequestParam(value="toDate", required=false, defaultValue="") String  toDate,
			@RequestParam(value="offset", required=false, defaultValue="0") int offset,
			@RequestParam(value="pageindex", required=false, defaultValue="0") int pageindex,
			@RequestParam(value="fromLegacy", required=false, defaultValue="0") int flag) throws Exception{
		EMRResponseBean responseBean=new EMRResponseBean();
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);
		Timestamp timestampFromDate=null; 
		Timestamp timestampToDate=null;
		Date fromdateTemp = null;
		Date todateTemp= null;
		
		if(flag==1){
			 TimeZone.setDefault(TimeZone.getTimeZone("EDT"));
			if((!fromDate.equals(""))&&(!toDate.equals("")))
			{
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format1.parse(fromDate);
		 fromdateTemp=format2.parse(format2.format(date));
		Date date1 = format1.parse(toDate);
		 todateTemp=format2.parse(format2.format(date1));
			}
		 
		}else{
		if((fromDate!=null)&&(toDate!=null))
		{
			SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

			Date formattedFromDate = null;
			Date one=null;

			try{
				@SuppressWarnings("deprecation")
				Date sample = new Date(fromDate);
				String fromdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sample);
				timestampFromDate = Timestamp.valueOf(fromdate);
			}catch (Exception e){ e.printStackTrace(); }
			Date formattedToDate = null;
			try{

				@SuppressWarnings("deprecation")
				Date sample = new Date(toDate);
				String todate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sample);
				formattedToDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todate);
				timestampToDate = Timestamp.valueOf(todate);
			}catch (Exception e){ e.printStackTrace(); }

		}
		}

		try {
			responseBean.setSuccess(true);
			if(flag==1){
				responseBean.setData(portalMedicalSummaryService.getEncounterList1(patientId, chartId,fromdateTemp,todateTemp,offset,pageindex));
			}else{
				responseBean.setData(portalMedicalSummaryService.getEncounterList1(patientId, chartId,timestampFromDate,timestampToDate,offset,pageindex));
			}
			
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving Encounter  list!");
			return responseBean;
		}
	}
	
}
