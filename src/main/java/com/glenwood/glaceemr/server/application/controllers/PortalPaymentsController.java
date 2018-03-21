package com.glenwood.glaceemr.server.application.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.CreditCardPaymentBean;
import com.glenwood.glaceemr.server.application.services.portal.portalPayments.PortalPaymentsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping(value="/user/PortalPayments")
public class PortalPaymentsController {
	
	@Autowired
	PortalPaymentsService portalPaymentsService;
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @param chartId 		: Required patient's chart id 
	 * @return List of patient's bill history
	 * @throws Exception
	 */
	@RequestMapping(value = "/Statements/History", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientStatementHistory(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="chartId", required=false, defaultValue="") int chartId,
			@RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPaymentsService.getPatientStatementHistory(patientId, chartId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving the patient statement history!");
			return responseBean;
		}
	}
	
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @param chartId 		: Required patient's chart id 
	 * @return List of patient's bill history
	 * @throws Exception
	 */
	@RequestMapping(value = "/Payments/History", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientPaymentHistory( @RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			 @RequestParam(value="chartId", required=false, defaultValue="") int chartId,
			 @RequestParam(value="pageOffset", required=false, defaultValue="5") int pageOffset,
			 @RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPaymentsService.getPatientPaymentHistory(patientId, chartId, pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving the patient payment history!");
			return responseBean;
		}
	}
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @param chartId 		: Required patient's chart id 
	 * @return List of patient's bill history
	 * @throws Exception
	 */
	@RequestMapping(value = "/Payments/Summary", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientPaymentsDeails(@RequestParam(value="patientId", required=false, defaultValue="") int patientId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPaymentsService.getPatientPaymentsSummary(patientId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving the patient payment summary!");
			return responseBean;
		}
	}
	
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's insurances
	 * @throws Exception
	 */
	@RequestMapping(value = "/Payments/PatientInsurances", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientInsDetails(@RequestParam(value="patientId", required=false, defaultValue="") int patientId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPaymentsService.getPatientInsDetails(patientId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving the patient insurances!");
			return responseBean;
		}
	}
	
	
	
	/**
	 * @param patientId : credit card payment details bean 
	 * @return Transaction summary details bean
	 * @throws Exception
	 */
	@RequestMapping(value = "/Payments/CreditCardPayment", method = RequestMethod.POST)
	@ResponseBody
	public  EMRResponseBean processPaymentTransaction(@RequestBody CreditCardPaymentBean paymentDetailsBean) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPaymentsService.processPaymentTransaction(paymentDetailsBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in credit card payment!");
			return responseBean;
		}
	}
	
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @param patientId 		: Required billId 
	 * @param patientId 		: Required billType 
	 * @return List of patient's insurances
	 * @throws Exception
	 */
	@RequestMapping(value = "/Statements/PatientStatement", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientStatementDetails(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="billId", required=false, defaultValue="") int billId,
			@RequestParam(value="billType", required=false, defaultValue="") String billType) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPaymentsService.getPatientStatementFileDetails(patientId, billId, billType));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving the patient statement!");
			return responseBean;
		}
	}
	
	/**
	 * @return List of TransFirst configured POS
	 * @throws Exception
	 */
	@RequestMapping(value = "/POS/TransFirst", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getTransFirstConfiguredPosList() throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalPaymentsService.getTransFirstConfiguredPosList());
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retieving transfirst configured pos list!");
			return responseBean;
		}
	}
	
	
}
