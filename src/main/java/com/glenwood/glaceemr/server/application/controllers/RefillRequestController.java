package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.RefillRequest.MedicationListBean;
import com.glenwood.glaceemr.server.application.services.chart.RefillRequest.PatientRefillDataBean;
import com.glenwood.glaceemr.server.application.services.chart.RefillRequest.RefillRequestService;
import com.glenwood.glaceemr.server.application.services.chart.RefillRequest.SSMessageBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;


@RestController
@Transactional
@RequestMapping(value = "/user/RefillRequest")
public class RefillRequestController {
	
	@Autowired
	RefillRequestService refillrequestservice;
	
	@Autowired
	AuditTrailSaveService auditTrailService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;

	private Logger logger = Logger.getLogger(RefillRequestController.class);

	
	@RequestMapping(value ="/byId", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getdenieddrugs(@RequestParam(value="encounterid",required=false,defaultValue="-1")Integer encounterid,@RequestParam(value="patientid",required=false,defaultValue="-1")Integer patientid)throws Exception
	{
		logger.debug("Getting  the encounter id"+encounterid);
		logger.debug("Getting  the patientid "+patientid);
		List<Prescription> drugs=refillrequestservice.getDeniedDrugs(encounterid,patientid);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved denied drug data",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		EMRResponseBean drugData=new EMRResponseBean();
		drugData.setData(drugs);
		return drugData;
	}
	
	
	/**
	 *  
	 * @return Bean consists of both inbox and outbox alerts count
	 * @throws Exception
	 */
	@RequestMapping(value ="/alertCount", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getCount()throws Exception
	{
		SSMessageBean drugs=refillrequestservice.getCount();
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved total alert count",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");		EMRResponseBean alertCountBean=new EMRResponseBean();
		alertCountBean.setData(drugs);
		return alertCountBean;
	}
	
	
	/**
	 * 
	 * @param erxUserAlertType
	 * @param userId
	 * @param isNewPage
	 * @return Bean consists of both inbox and outbox alerts list
	 * @throws Exception
	 */
	@RequestMapping(value ="/alertInbox", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getalertinbox(@RequestParam(value="erxUserAlertType",required=false,defaultValue="MYALERT")String erxUserAlertType,@RequestParam(value="userId",required=false,defaultValue="-1")String userId,@RequestParam(value="isNewPage",required=false,defaultValue="false") boolean isNewPage)throws Exception
	{
		MedicationListBean drugs=refillrequestservice.getAlertInbox(erxUserAlertType,userId,isNewPage);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully all refill alert details",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");		EMRResponseBean alertDataBean=new EMRResponseBean();
		alertDataBean.setData(drugs);
		return alertDataBean;
	}
	
	
	/**
	 * 
	 * @param userId
	 * @param alertId
	 * @param alertEventIds
	 * @return string represents successful closing of alerts
	 * @throws Exception
	 */
	@RequestMapping(value ="/isCloseAlert", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean isCloseAlert( @RequestParam(value="userId",required=false,defaultValue="-1")int userId, @RequestParam(value="alertId",required=false,defaultValue="-1")String alertId, @RequestParam(value="alertEventIds",required=false,defaultValue="-1")String alertEventIds) throws Exception
	{
		String responseString=refillrequestservice.isCloseAlert(userId,alertId,alertEventIds);
		EMRResponseBean closingMessage=new EMRResponseBean();
		closingMessage.setData(responseString);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully deleted alert",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return closingMessage;
	}
	
	
	/**
	 * 
	 * @param patientId
	 * @param alertId
	 * @return String represents successful merge of requests
	 * @throws Exception
	 */
	@RequestMapping(value ="/mergeRequest", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean mergeRequest( @RequestParam(value="patientId",required=false,defaultValue="-1")int patientId, @RequestParam(value="alertId",required=false,defaultValue="-1")String alertId) throws Exception
	{
		List<SSMessageInbox> responseString=refillrequestservice.mergeRequest(patientId,alertId);
		EMRResponseBean mergeStatusMessage=new EMRResponseBean();
		mergeStatusMessage.setData(responseString);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully merged alert data",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return mergeStatusMessage;
		
	}
	
	
	/**
	 * 
	 * @param patientId
	 * @return Data bean contains POS,patient,medication details. 
	 * @throws Exception
	 */
	@RequestMapping(value ="/pendingRequest", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean pendingRequest( @RequestParam(value="patientId",required=false,defaultValue="-1")int patientId) throws Exception
	{
		String appt=refillrequestservice.BillingConfig(patientId);
		String lastVisitPOS=refillrequestservice.getPosDetails(patientId);
		PatientRegistration patdata=refillrequestservice.getPatientData(patientId);
		List<SSMessageInbox> pedingdata=refillrequestservice.patRequestsData(patientId);
		PatientRefillDataBean databean=new PatientRefillDataBean(appt,lastVisitPOS,patdata,pedingdata);
		EMRResponseBean requestDataBean=new EMRResponseBean();
		requestDataBean.setData(databean);
		auditTrailService.LogEvent(AuditTrailEnumConstants.LogType.AUDIT_LOG,AuditTrailEnumConstants.LogModuleType.PRESCRIPTIONS,AuditTrailEnumConstants.LogActionType.READ,-1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Sucessfully retrieved patient's pending refills data",sessionMap.getUserID(),"127.0.0.1",-1,AuditTrailEnumConstants.Log_Relavant_Id.PATIENT_ID.toString(),AuditTrailEnumConstants.LogUserType.USER_LOGIN,"-1","User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return requestDataBean;
		
	}
	
}