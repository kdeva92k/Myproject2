package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.Bean.AdmissionInfoBean;
import com.glenwood.glaceemr.server.application.models.Admission;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.services.HospitalSuperBill.HospitalSuperbillService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@RequestMapping(value="/user/HospitalSuperbill")
public class HospitalSuperBillController {
	
	@Autowired
	HospitalSuperbillService hospitalSuperBillService;
	
	@Autowired
	AuditTrailService auditTrailService;
	
	@Autowired
	SessionMap sessionMap;
	
    private Logger logger = Logger.getLogger(HospitalSuperBillController.class);
	
	@Autowired
	HttpServletRequest request;
	
	/**
	 * To get the admitted patients list
	 * @param posId
	 * @param date
	 * @param doctorId
	 * @return admission
	 * @throws Exception
	 */
	@RequestMapping(value="/getAdmittedPatientsList",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAdmittedPatientsList(@RequestParam(value="posId",required=false)Integer posId,@RequestParam(value="date",required=false)String date,@RequestParam(value="doctorId",required=false)Integer doctorId) throws Exception{
	List<AdmissionInfoBean> admittedList=hospitalSuperBillService.getAdmittedPatientsList(posId,doctorId,date);
   EMRResponseBean admission=new EMRResponseBean();
   admission.setData(admittedList);
	return admission;
	}
	
	/**
	 * Get all the frequently used cpt codes related to the hospital and Nursinghome
	 * @param posTypeId
	 * if posTypeId is equalTo "Zero" then get hospital visit cpt codes
	 * if posTypeId is equalTo "one" then get Nursing home visit cpt codes
	 * @return cpt
	 * @throws Exception
	 */
	@RequestMapping(value="/getCptCodes",method=RequestMethod.GET)
	@ResponseBody 
	public EMRResponseBean getCptCodes(@RequestParam(value="selectedPosType", required=false)String posTypeId) throws Exception{
		logger.info("Get list of frequently used cpt codes for both hospital and nursing home superbill");
		List<Cpt> cptValue=hospitalSuperBillService.getCptCodes(posTypeId);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.LoginAndLogOut,AuditLogConstants.LOGIN,1,AuditLogConstants.SUCCESS,"Sucessfull login User Name(" +1+")",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"User (" + sessionMap.getUserID()+ ") logged in through SSO");
		EMRResponseBean cpt=new EMRResponseBean();
		   cpt.setData(cptValue);
		return cpt;
	}
	
	
	/**
	 * Discharge the patient from Hospital/NursingHome SuperBill
	 * @param patientId
	 * @param dischargeDate
	 * @param admissionId
	 * @return discharge
	 * @throws Exception 
	 */
	@RequestMapping(value="/dischargePatient",method=RequestMethod.PUT)
	@ResponseBody
	public EMRResponseBean updateDischargeDate(@RequestParam(value="patientId", required=false)Integer patientId,
			@RequestParam(value="dischargeDate", required=false)String  dischargeDate,
			@RequestParam(value="admissionId", required=false)Integer admissionId) throws Exception {
		logger.info("Discharge the patient from pos");
		List<Admission> dischargePatient=hospitalSuperBillService.updateDischargeDate(patientId,dischargeDate,admissionId);
		EMRResponseBean discharge=new EMRResponseBean();
		discharge.setData(dischargePatient);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.LoginAndLogOut,AuditLogConstants.LOGIN,1,AuditLogConstants.SUCCESS,"Sucessfull login User Name(" +1+")",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return discharge;
	}
	
	/**
	 * Request to get previous Dx codes
	 * @param patientId
	 * @return previousDx
	 * @throws Exception
	 */
	@RequestMapping(value="/getDxCodes",method=RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public EMRResponseBean getPreviousDxCodes(@RequestParam(value="patientId",required=false)Integer patientId) throws Exception{
		logger.info("Get previous Dx codes");
		List<ServiceDetail> previousDxCodes=hospitalSuperBillService.getPreviousVisitDxCodes(patientId);
		EMRResponseBean previousDx=new EMRResponseBean();
		previousDx.setData(previousDxCodes);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.LoginAndLogOut,AuditLogConstants.LOGIN,1,AuditLogConstants.SUCCESS,"Sucessfull login User Name(" +1+")",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return previousDx;
	}
	
	/**
	 * Request to get the provider information
	 * @return List<EmpProfile>
	 * @throws Exception
	 */
	@RequestMapping(value="/getProviderList",method= RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getProviderList() throws Exception{
		logger.info("Request to get provider information");
		List<EmployeeProfile> providersList=hospitalSuperBillService.getProviderList();
		EMRResponseBean providers=new EMRResponseBean();
		providers.setData(providersList);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.LoginAndLogOut,AuditLogConstants.LOGIN,1,AuditLogConstants.SUCCESS,"Sucessfull login User Name(" +1+")",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return providers;
	}
	
	/**
	 * Request to get services information for a patient
	 * @param patientId
	 * @param admissionDate
	 * @return services
	 * @throws Exception
	 */
	@RequestMapping(value="/getServicesList",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getServicesList(@RequestParam(value="patientId",required=false)Integer patientId,@RequestParam(value="admissionDate",required=false)String admissionDate) throws Exception{
		logger.info("Request to get the list of services");
		List<ServiceDetail> getServices=hospitalSuperBillService.getServicesList(patientId,admissionDate);
		EMRResponseBean services=new EMRResponseBean();
		services.setData(getServices);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.LoginAndLogOut,AuditLogConstants.LOGIN,1,AuditLogConstants.SUCCESS,"Sucessfull login User Name(" +1+")",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"User (" + sessionMap.getUserID()+ ") logged in through SSO");
		return services;
	}
	
}