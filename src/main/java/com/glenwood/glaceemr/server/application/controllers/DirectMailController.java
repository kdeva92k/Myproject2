package com.glenwood.glaceemr.server.application.controllers;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.DirectMail.DirectBasicDetailsSent;
import com.glenwood.glaceemr.server.application.services.DirectMail.DirectMailService;
import com.glenwood.glaceemr.server.application.services.DirectMail.DirectMessage;
import com.glenwood.glaceemr.server.application.services.DirectMail.DirectMessageStatus;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;
/**
 * 
 * @author Nithyavalli
 *
 */

@RestController
@Transactional
@RequestMapping(value = "/direct/DirectMail") 

public class DirectMailController  {
	
	
		@Autowired
		DirectMailService mailService;
		
		@Autowired
		SessionMap sessionMap;
		
		@Autowired
		HttpServletRequest request;
		
		@Autowired
		EMRResponseBean responseBean;

		@Autowired
		AuditTrailService auditTrailService;
		
		@RequestMapping(value ="/directMailReceiver", method = RequestMethod.POST) 
		@ResponseBody
		public DirectMessageStatus directMailReceiver(@RequestBody DirectMessage bean,@RequestParam (value="SharedFolderPath",required=false,defaultValue="-1")String SharedFolderPath){
			DirectMessageStatus status=new DirectMessageStatus();
			try {
			String responseMsg=	mailService.insertInWorkflow(bean,request);
			String responseMsgAttachment=	mailService.insertInWorkflowAttachment(bean,SharedFolderPath,request);
				
				responseBean.setData(responseMsg +";"+responseMsgAttachment);
				
				if(!(responseMsg.contains("Error")) && !(responseMsgAttachment.contains("Error"))){
					auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.DirectMessage,AuditLogConstants.CREATED,-1,AuditLogConstants.SUCCESS,bean.getToAddress()+" receive mail from "+bean.getFromAddress(),-1,"127.0.01",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"Direct Message received");

					status.setStatusCode(1);
					status.setStatusLabel("success");
					status.setStatusMessage(responseMsg +";"+responseMsgAttachment);
				}else{
					auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.DirectMessage,AuditLogConstants.CREATED,-1,AuditLogConstants.FAILURE,"Error while receive mail from "+bean.getFromAddress(),-1,"127.0.01",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"Error :"+responseMsg +";"+responseMsgAttachment);

					status.setStatusCode(0);
					status.setStatusLabel("failure");
					status.setStatusMessage("Error :"+responseMsg +";"+responseMsgAttachment);
				}
				
			} catch (Exception e) {
			e.printStackTrace();
			status.setStatusCode(0);
			status.setStatusLabel("Error");
			status.setStatusMessage("Error :"+e.getMessage());
			responseBean.setData("Error :"+e.getMessage());
			auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.DirectMessage,AuditLogConstants.CREATED,-1,AuditLogConstants.FAILURE,"Error while receive mail from "+bean.getFromAddress(),-1,"127.0.01",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"Error :"+e.getMessage());
			}
		return status;
		}
		
		@RequestMapping(value ="/directMailsender", method = RequestMethod.POST) 
		@ResponseBody
		public EMRResponseBean sendMail(@RequestBody DirectBasicDetailsSent bean){
			DirectMessageStatus status=new DirectMessageStatus();
			try {
				 status=mailService.sendMail(bean,request);
				responseBean.setData(status.getStatusMessage());
			} catch (Exception e) {
				e.printStackTrace();
				responseBean.setData("Error :"+e.getMessage());
			}
			return responseBean;
		}
		
		@RequestMapping(value ="/mdnReceiver", method = RequestMethod.POST) 
		@ResponseBody
		public EMRResponseBean mdnReceiver(@RequestBody DirectMessageStatus bean){
			
			try {
				String responseMsg=	mailService.mdnReceiver(bean,request);
				responseBean.setData(responseMsg);
			} catch (Exception e) {
				e.printStackTrace();
				responseBean.setData("Error :"+e.getMessage());
			}
			return responseBean;
			
		}
		
	}