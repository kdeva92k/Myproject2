package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.DrugSchedule;
import com.glenwood.glaceemr.server.application.models.MedsAdminLog;
import com.glenwood.glaceemr.server.application.models.MedsAdminPlanShortcut;
import com.glenwood.glaceemr.server.application.models.PharmacyFilterBean;
import com.glenwood.glaceemr.server.application.models.PortalRefillRequestBean;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.prescription.IntakeBean;
import com.glenwood.glaceemr.server.application.services.chart.prescription.PrescriptionBean;
import com.glenwood.glaceemr.server.application.services.chart.prescription.PrescriptionService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;


/**
 * Controller for Prescriptions in GlaceEMR, 
 * contains the methods to get the patient medications and performing different operations on those. 
 * 
 * @author software
 *
 */
@RestController
@RequestMapping(value = "/user/Prescription.Action")
public class PrescriptionController {
	
	@Autowired
	PrescriptionService prescriptionService;
	
	@Autowired
	AuditTrailSaveService auditTrailService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	EMRResponseBean responseBean;
	
	private Logger logger = Logger.getLogger(PrescriptionController.class);
	
	/**
	 * To get the list of active medications with class details
	 * @param patientid
	 * @return the list of active medications of the given patient
	 * @throws Exception
	 */
	
	@RequestMapping(value ="/Bypatientid", method = RequestMethod.GET) 
	@ResponseBody
	
	public EMRResponseBean getactivemedwithclass(@RequestParam(value="patientid",required=false,defaultValue="-1")Integer patientid)throws Exception{
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			Map<String, Object> drugs=prescriptionService.getactivemedwithclass(patientid);
			responseBean.setSuccess(true);
			responseBean.setData(drugs);
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull retrieved active medication data",sessionMap.getUserID(),"127.0.0.1",patientid,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving active meds!");
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.FAILURE,"Failed to retrieve active medication data",sessionMap.getUserID(),"127.0.0.1",patientid,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		}		
	}
	
	/**
	 * To get the list of active medications with class details 
	 * @param patientid
	 * @param whichMonth
	 * @param year
	 * @return the list of medications for a particular month
	 * @throws Exception
	 */
		
	@RequestMapping(value ="/ByMonth", method = RequestMethod.GET) 
	@ResponseBody
	
	public EMRResponseBean getOneMonthActiveMeds(@RequestParam(value="patientid",required=false,defaultValue="-1")Integer patientid,
			@RequestParam(value="month",required=false,defaultValue="-1")Integer whichMonth,
			@RequestParam(value="year",required=false,defaultValue="-1")Integer year)throws Exception{
		Map<String, Object> drugs=prescriptionService.getOneMonthActiveMeds(patientid,whichMonth,year);
		EMRResponseBean activeMedsByMonth = new EMRResponseBean();
		activeMedsByMonth.setData(drugs);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved medications of the month",sessionMap.getUserID(),"127.0.0.1",patientid,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return activeMedsByMonth;
	}
	
	/**
	 * To save the medication's new administration plan 
	 * @param dataToSave
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveMedPlan", method = RequestMethod.GET) 
	@ResponseBody
	
	public void savemedAdministrationNewPlan(@RequestParam(value="dataToSave",required=false,defaultValue="-1")String dataToSave)throws Exception{
		logger.debug("In saveMedPlan - new plan is going to save");
		prescriptionService.saveMedicationAdminPlan(dataToSave);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.MOVE,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully saved medAdministration New Plan",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		logger.debug("In saveMedPlan - saved");
	}
	
	/**
	 * To save the medication's administered log 
	 * @param dataToSave
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveMedAdminLog", method = RequestMethod.GET) 
	@ResponseBody
	
	public void savemedAdministrationLog(@RequestParam(value="dataToSave",required=false,defaultValue="-1")String dataToSave)throws Exception{
		logger.debug("In saveMedAdminLog - administration log for a plan is going to save");
		prescriptionService.saveMedicationAdminLog(dataToSave);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.MOVE,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully saved medAdministration Log",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		logger.debug("In saveMedAdminLog - administration log for a plan got saved");
	}
	
	/**
	 * To get the Medication full with previous administered log 
	 * @param dataToSave
	 * @throws Exception
	 */
	@RequestMapping(value ="/getMedAdminLogHistory", method = RequestMethod.GET) 
	@ResponseBody
	
	public EMRResponseBean getMedAdministrationLogHistory(@RequestParam(value="planId",required=false,defaultValue="-1")Integer planId)throws Exception{
		@SuppressWarnings("unchecked")
		List<MedsAdminLog> logDetails = prescriptionService.getMedicationAdminLogHistory(planId);
		EMRResponseBean logHistory = new EMRResponseBean();
		logHistory.setData(logDetails);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved MedAdministration Log History",sessionMap.getUserID(),"127.0.0.1",-1,planId.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return logHistory;
	}

	/**
	 * To get the Medication administered log 
	 * @param dataToSave
	 * @throws Exception
	 */
	@RequestMapping(value ="/getMedAdminLog", method = RequestMethod.GET) 
	@ResponseBody
	
	public EMRResponseBean getMedAdminLog(@RequestParam(value="logId",required=false,defaultValue="-1")Integer logId)throws Exception{
		@SuppressWarnings("unchecked")
		List<MedsAdminLog> logDetails = prescriptionService.getMedicationAdminLog(logId);
		EMRResponseBean logData = new EMRResponseBean();
		logData.setData(logDetails);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved MedAdmin Log",sessionMap.getUserID(),"127.0.0.1",-1,logId.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

		return logData;
	}
	
	/**
	 * To delete the medication administered log 
	 * @param dataToSave
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteMedAdminLog", method = RequestMethod.GET) 
	@ResponseBody
	
	public void deletemedAdministrationLog(@RequestParam(value="deletedBy",required=false,defaultValue="-1")Integer deletedBy,
			@RequestParam(value="logId",required=false,defaultValue="-1")Integer logId)throws Exception{
		logger.debug("In deleteMedAdminLog - "+logId+"is going to be deleted");
		prescriptionService.deleteMedicationAdminLog(deletedBy,logId);
		logger.debug("In deleteMedAdminLog - "+logId+"got deleted");
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.DELETE,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull deleted medAdministration Log",sessionMap.getUserID(),"127.0.0.1",-1,logId.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

	}

	/**
	 * To edit the medication's administration plan 
	 * @param dataToSave
	 * @throws Exception
	 */
	@RequestMapping(value ="/editDeleteMedAdminPlan", method = RequestMethod.GET) 
	@ResponseBody
	public void editmedAdministrationPlan(@RequestParam(value="dataToSave",required=false,defaultValue="-1")String dataToSave)throws Exception{
		logger.debug("In editDeleteMedAdminPlan - plan is going to be edited");
		prescriptionService.editMedicationAdminPlan(dataToSave);
		logger.debug("In editDeleteMedAdminPlan - saved");
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.DELETE,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull edited medAdministration Plan",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

	}
	
	
	/*
	 *To get the medical supplies details for the selected patient
	 *@param patientid
	 *@throws Exception
	 */
	@RequestMapping(value ="/patientid", method = RequestMethod.GET) 
	@ResponseBody
	
	public EMRResponseBean getactivemedicalsupplies(@RequestParam(value="patientid",required=false,defaultValue="-1")Integer patientid)throws Exception{
		logger.debug("In medical supplies");
		PrescriptionBean drugs=prescriptionService.getactivemedicalsupplies(patientid);
		logger.debug("Got the medical supplies data");
		EMRResponseBean activeMedSupplies = new EMRResponseBean();
		activeMedSupplies.setData(drugs);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull retrieved active medication data",sessionMap.getUserID(),"127.0.0.1",patientid,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

		return activeMedSupplies;
	}
	
	/*To get the take and frequency list based on selected medications
	 * 
	 */
		@RequestMapping(value="/getTakeAndFrequencyList", method = RequestMethod.GET)
		@ResponseBody
		public EMRResponseBean getTakeAndFrequencyList(@RequestParam(value="brandname",required=false,defaultValue="-1")String brandname,@RequestParam(value="mode",required=false,defaultValue="-1")String mode)throws Exception{

			logger.debug("In getting frequency list");
			List<DrugSchedule> schedulename=prescriptionService.getfrequencylist(brandname.replaceAll("'","''"),mode);
			EMRResponseBean frequencyList = new EMRResponseBean();
			frequencyList.setData(schedulename);
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull retrieved Take And Frequency List",sessionMap.getUserID(),"127.0.0.1",-1,brandname,AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

			return frequencyList;

		}

		
		/*To get the all the frequency list based on selected medications
		 * 
		 */
		@RequestMapping(value="/getFrequencyListall", method = RequestMethod.GET)
		@ResponseBody
		public EMRResponseBean getFrequencyListall(@RequestParam(value="brandname",required=false,defaultValue="-1")String brandname,@RequestParam(value="mode",required=false,defaultValue="-1")String mode)throws Exception{
			logger.debug("In getting all  frequency list");
			List<DrugSchedule> schedulename=prescriptionService.getfrequencylistall(brandname.replaceAll("'","''"),mode);
			EMRResponseBean frequencyList = new EMRResponseBean();
			frequencyList.setData(schedulename);
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull retrieved all frequency list",sessionMap.getUserID(),"127.0.0.1",-1,brandname,AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

			return frequencyList;

		}

		/*To get the take list based on selected medications
		 * 
		 */
		@RequestMapping(value="/gettake", method = RequestMethod.GET)
		@ResponseBody
		public EMRResponseBean gettake(@RequestParam(value="brandname",required=false,defaultValue="-1")String brandname)throws Exception{
			logger.debug("In getting t list");
			List<IntakeBean> takevalues=prescriptionService.gettake(brandname.replaceAll("'","''"));
			EMRResponseBean intakeData = new EMRResponseBean();
			intakeData.setData(takevalues);
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull retrieved take List",sessionMap.getUserID(),"127.0.0.1",-1,brandname,AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

			return intakeData;

		}
	
	/**
	 * To modify the medication administered log notes 
	 * @param dataToSave
	 * @throws Exception
	 */
	@RequestMapping(value ="/updateMedAdminLogNotes", method = RequestMethod.GET) 
	@ResponseBody
	public void updateMedAdministrationLogNotes(@RequestParam(value="modifiedBy",required=false,defaultValue="-1")Integer modifiedBy,
			@RequestParam(value="logId",required=false,defaultValue="-1")Integer logId,
			@RequestParam(value="notes",required=false,defaultValue="")String notes)throws Exception{
		logger.debug("In updateMedAdministrationLogNotes - "+logId+"is going to be modified");
		prescriptionService.updateMedicationAdminLogNotes(modifiedBy,logId,notes);
		logger.debug("In updateMedAdministrationLogNotes - "+logId+"got modified");
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.UPDATE,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull updating MedAdministration Log Notes",sessionMap.getUserID(),"127.0.0.1",-1,notes,AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

	}
	
	/**
	 * To get the medication administration plan shortcuts 
	 * * @throws Exception
	 */
	@RequestMapping(value ="/getMedAdminPlanShortcuts", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getMedAdminPlanShortcuts()throws Exception{
		logger.debug("In getMedAdminPlanShortcuts");
		List<MedsAdminPlanShortcut> shortcutsList = prescriptionService.getMedAdminPlanShortcuts();
		EMRResponseBean medShortcuts = new EMRResponseBean();
		medShortcuts.setData(shortcutsList);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull retrieved MedAdmin Plan Shortcuts",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");

		return medShortcuts;
	}
	
	/**
	 * @return EMRResponseBean with pharmacy list
	 * @throws Exception
	 */
	@RequestMapping(value = "/PharmacyList", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean getPharmacyList(@RequestBody PharmacyFilterBean pharmacyFilterBean)throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(prescriptionService.getPharmacyList(pharmacyFilterBean));
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved Pharmacy List",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving pharmacy list!");
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Failed to retrieved Pharmacy List",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		}		
	}
	
	
	/**
	 * @param patientId 
	 * @param chartId
	 * @return EMRResponseBEan with completed prescription list
	 * @throws Exception
	 */
	@RequestMapping(value = "/RefillRequestHistory", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientRefillRequestHistory(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			 @RequestParam(value="chartId", required=false, defaultValue="") int chartId)throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(prescriptionService.getPatientRefillRequestHistory(patientId, chartId));
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved Patient RefillRequest History",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving refill request history!");
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Failed to retrieve Patient RefillRequest History",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		}	
	}
	
	/**
	 * @param patientId 
	 * @param chartId
	 * @return EMRResponseBean with refill request medications
	 * @throws Exception
	 */
	@RequestMapping(value = "/RefillRequestMedications", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientRefillRequestMedications(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			 @RequestParam(value="chartId", required=false, defaultValue="") int chartId)throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(prescriptionService.getPatientRefillRequestMedications(patientId, chartId));
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull retrieved Patient RefillRequest Medications",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving refill request medications!");
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Failed to retrieve Patient RefillRequest Medications",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		}	
	}
	
	/**
	 * @return EMRResponseBean with request status
	 * @throws Exception
	 */
	@RequestMapping(value = "/RefillRequest", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean requestFillFromPortal(@RequestBody PortalRefillRequestBean portalRefillRequestBean)throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(prescriptionService.requestRefill(portalRefillRequestBean));
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull requested Fill From Portal",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving refill request medications!");
			auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Failed to request Fill From Portal",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
			return responseBean;
		}
		
	}
}