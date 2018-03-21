package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PharmDetails;
import com.glenwood.glaceemr.server.application.models.ReceiptDetail;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.ActiveMedicationsBean;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.CurrentMedDataBean;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.CurrentMedicationService;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.EncounterDataBean;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.InactiveMedBean;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.MedicationDetailBean;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.PatientAllergiesBean;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.SearchBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;



@RestController
@Transactional
@RequestMapping(value = "/user/CurrentMedication")
public class CurrentMedicationController {
		
	@Autowired
	CurrentMedicationService currentMedicationService;
	
	@Autowired
	AuditTrailSaveService auditTrailService;
	
	@Autowired
	SessionMap sessionMap;
	
	/**
	 *  
	 * @return Getting patient allergies data
	 * @param chartId
	 * @throws Exception
	 */
	@RequestMapping(value ="/allergyData", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getAllergyData(@RequestParam(value="chartId",required=false,defaultValue="-1")int chartId)throws Exception
	{
		List<PatientAllergiesBean> allergies=currentMedicationService.getAllergies(chartId);
		EMRResponseBean allergyList = new EMRResponseBean();
		allergyList.setData(allergies);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved allergy data",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return allergyList;
	}
	
	
	/**
	 *  
	 * @return Getting Patient pharmacy details
	 * @param patientId
	 * @throws Exception
	 */
	@RequestMapping(value ="/pharmacyData", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getPharmacy( @RequestParam(value="patientId",required=false,defaultValue="-1")int patientId)throws Exception
	{
		PharmDetails pharmData=currentMedicationService.getPharmacy(patientId);
		EMRResponseBean dataBean = new EMRResponseBean();
		dataBean.setData(pharmData);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved patient pharmacy data",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return dataBean;
	}
	
	
	/**
	 *  
	 * @return Getting Patient Encounter and copay details
	 * @param patientId
	 * @param encounterId
	 * @throws Exception
	 */
	@RequestMapping(value ="/encounterData", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getEncounterData(@RequestParam(value="patientId",required=false,defaultValue="-1")int patientId, @RequestParam(value="encounterId",required=false,defaultValue="-1")int encounterId)throws Exception
	{
		List<Encounter> encData=currentMedicationService.getEncounterData(encounterId);
		PatientRegistration copay=currentMedicationService.getCopay(patientId);
		List<ReceiptDetail> copayment=currentMedicationService.getCopayment(encounterId,patientId);
		EncounterDataBean resultBean= new EncounterDataBean(encData,copay,copayment);
		EMRResponseBean dataBean = new EMRResponseBean();
		dataBean.setData(resultBean);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved encounter details",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return dataBean;
	}
	
	
	/**
	 *  
	 * @return Getting Patient current medications,transition of care and summary care details 
	 * @param patientId
	 * @param encounterId
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value ="/currentMedData", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getCurrentMedData(@RequestParam(value="patientId",required=false,defaultValue="-1")int patientId, @RequestParam(value="encounterId",required=false,defaultValue="-1")int encounterId, @RequestParam(value="userId",required=false,defaultValue="-1")int userId)throws Exception
	{
		int locEncounterId=encounterId;
		Chart chartDetails=currentMedicationService.getchartDetails(patientId);
		if(encounterId==-1){
		Encounter enc=currentMedicationService.getMaxEnc(chartDetails.getChartId().toString(),patientId,userId);
		locEncounterId=enc.getEncounterId();
		}
		List<MedicationDetailBean> medDetails=currentMedicationService.getMedDetails(locEncounterId,patientId);
		Encounter transitionSummaryCare=currentMedicationService.getTransitionSummaryCare(locEncounterId);
		Boolean summaryOfCare=false;
		Boolean transitionOfCare=false;
		if(transitionSummaryCare!=null){
			summaryOfCare=transitionSummaryCare.getSummaryOfCare();
			transitionOfCare=transitionSummaryCare.getTransitionOfCare();
		}
		Boolean noMedicationFlag=false;
		if(chartDetails!=null)
			noMedicationFlag=chartDetails.getNomedication();
		CurrentMedDataBean bean=new CurrentMedDataBean(transitionOfCare,summaryOfCare,noMedicationFlag,medDetails);
		EMRResponseBean dataBean = new EMRResponseBean();
		dataBean.setData(bean);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved current medication data",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return dataBean;
	}
	
	/**
	 *  
	 * @return Getting Active medications of the patient
	 * @param patientId
	 * @throws Exception
	 */
	@RequestMapping(value ="/activeMed", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getActiveMedications( @RequestParam(value="patientId",required=false,defaultValue="-1")int patientId)throws Exception
	{
		List<ActiveMedicationsBean> currentMeds =currentMedicationService.getActiveCurrentMeds(patientId);
		List<ActiveMedicationsBean> prescriptionMeds=currentMedicationService.getActivePrescMeds(patientId);
		for(int i=0;i<prescriptionMeds.size();i++){
			currentMeds.add(prescriptionMeds.get(i));
		}
		EMRResponseBean dataBean = new EMRResponseBean();
		dataBean.setData(currentMeds);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved patient active medications",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return dataBean;
	}
	
	/**
	 *  
	 * @return Getting Inactive medications of the patient
	 * @param patientId
	 * @throws Exception
	 */
	@RequestMapping(value ="/inActiveMed", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getInActiveMedications( @RequestParam(value="patientId",required=false,defaultValue="-1")int patientId)throws Exception
	{
		List<InactiveMedBean> currentMeds =currentMedicationService.getInActiveCurrentMeds(patientId);
		EMRResponseBean dataBean = new EMRResponseBean();
		dataBean.setData(currentMeds);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfull retrieved patient inactive medications",sessionMap.getUserID(),"127.0.0.1",patientId,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return dataBean;
	}
	
	/**
	 *  
	 * @return Getting medication list based on search keyword
	 * @param keyword
	 * @param prescriberspecific
	 * @param mapid
	 * @param userId
	 * @param offset
	 * @param limit
	 * @throws Exception
	 */
	@RequestMapping(value ="/medSearch", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getMedSearchDetails(@RequestParam(value="keyword",required=false,defaultValue="-1")String keyword,  @RequestParam(value="prescriberspecific",required=false,defaultValue="false")String prescriberspecific,  @RequestParam(value="mapid",required=false,defaultValue="-1")String mapid,  @RequestParam(value="userId",required=false,defaultValue="-1")int userId,  @RequestParam(value="offset",required=false,defaultValue="-1")int offset, @RequestParam(value="limit",required=false,defaultValue="-1")int limit)throws Exception
	{
		List<SearchBean> drugList=currentMedicationService.getsearchData(keyword,prescriberspecific,mapid,userId,offset,limit);
		EMRResponseBean dataBean = new EMRResponseBean();
		dataBean.setData(drugList);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved medication list",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return dataBean;
	}
}
