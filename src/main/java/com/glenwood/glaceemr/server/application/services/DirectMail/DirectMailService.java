package com.glenwood.glaceemr.server.application.services.DirectMail;

import javax.servlet.http.HttpServletRequest;

public interface DirectMailService {
	DirectMessageStatus sendMail(DirectBasicDetailsSent bean,HttpServletRequest request)throws Exception;
	public String insertInWorkflow(DirectMessage bean,HttpServletRequest request) throws Exception;
	public String insertInWorkflowAttachment(DirectMessage bean,String SharedFolderPath,HttpServletRequest request) throws Exception;
	public String mdnReceiver(DirectMessageStatus bean,HttpServletRequest request) throws Exception;
}