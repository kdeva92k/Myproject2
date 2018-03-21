package com.glenwood.glaceemr.server.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.PortalMessageBean;
import com.glenwood.glaceemr.server.application.services.portal.portalMessages.PortalMessagesService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;



@RestController
@Transactional
@RequestMapping(value="/user/PortalMessages")
public class PortalMessagesController {
	
	@Autowired
	PortalMessagesService portalMessagesService;

	
	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's messages details
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientMessagesDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getMessagesDetailsByPatientId(@RequestParam(value="patientId", required=false, defaultValue="") int patientId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMessagesService.getMessagesDetailsByPatientId(patientId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in patient message details!");
			return responseBean;
		}
	}
	
	
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's inbox messages
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientMessagesList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientMessagesList(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="offset", required=false, defaultValue="5") int offset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMessagesService.getMessagesByPatientId(patientId,offset,pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in patient messages list!");
			return responseBean;
		}
	}
	
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's inbox messages
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientMessageThreadsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientMessagesThreadsList( @RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="offset", required=false, defaultValue="5") int offset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMessagesService.getPatientMessageThreadsByPatientId(patientId,offset,pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in patient messages thread list!");
			return responseBean;
		}
	}
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's inbox messages
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientInboxMessagesList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientInboxMessagesList(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="offset", required=false, defaultValue="5") int offset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMessagesService.getInboxMessagesByPatientId(patientId,offset,pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in patient inbox messages list!");
			return responseBean;
		}
	}

	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's sent messages
	 * @throws Exception
	 */
	@RequestMapping(value = "/PatientSentMessagesList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientSentMessagesList(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="offset", required=false, defaultValue="5") int offset,
			@RequestParam(value="pageIndex", required=false, defaultValue="0") int pageIndex) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMessagesService.getSentMessagesByPatientId(patientId,offset,pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in patient sent messages list!");
			return responseBean;
		}
	}
	
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's sent messages
	 * @throws Exception
	 */
	@RequestMapping(value = "/DeletePortalMessageByMessageId", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deletePatientMessage(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			 @RequestParam(value="messageId", required=false, defaultValue="-1") int messageId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMessagesService.deletePortalMessageByMessageId(patientId,messageId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in deleting patient message!");
			return responseBean;
		}
	}
	
	
	/**
	 * @param patientId 		: Required patient's id 
	 * @return List of patient's messages details
	 * @throws Exception
	 */
	@RequestMapping(value = "/DeletePortalMessageThread", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deletePortalMessageThread(@RequestParam(value="patientId", required=false, defaultValue="") int patientId,
			@RequestParam(value="threadId", required=false, defaultValue="-1") int threadId) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMessagesService.deletePortalMessageThread(patientId, threadId));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in deleting patient message thread!");
			return responseBean;
		}
	}
	
	
	
	/**
	 * @param portalmessageBean : PortalMessage Model which contains all details to save a message. 
	 * @return Message saving response bean
	 * @throws Exception
	 */
	@RequestMapping(value = "/Message/Create", method = RequestMethod.POST)
	@ResponseBody
	public  EMRResponseBean saveNewMessage(@RequestBody PortalMessageBean portalMessageBean) throws Exception{

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalMessagesService.saveNewMessage(portalMessageBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in creating a message!");
			return responseBean;
		}
	}
	
	
	
}
