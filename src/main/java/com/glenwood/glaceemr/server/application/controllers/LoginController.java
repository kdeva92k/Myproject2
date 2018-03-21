/**
 * 
 */
package com.glenwood.glaceemr.server.application.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.users.UsersService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;


/**
 * @author Anupama
 *
 */
@RestController
@RequestMapping(value="/login")

public class LoginController {

	@Autowired
	UsersService usersService;

	@Autowired 
	EMRResponseBean emrResponseBean;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "accessDenied",method = RequestMethod.GET)
	public EMRResponseBean accessDenied(HttpServletRequest request,HttpServletResponse response) 
	{
		emrResponseBean.setSuccess(true);
		emrResponseBean.setData(null);
		emrResponseBean.setIsAuthorizationPresent(true);
		emrResponseBean.setCanUserAccess(false);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.LoginAndLogOut, LogActionType.LOGIN, -1, Log_Outcome.SECURITY_VIOLATION, "Access denied", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		
		return emrResponseBean;
	}

	@RequestMapping(value = "loginFailed",method = RequestMethod.GET)
	public EMRResponseBean loginFailed(HttpServletRequest request,HttpServletResponse response) 
	{
		System.out.println("Authentication failed.");
		emrResponseBean.setLogin(false);
		emrResponseBean.setSuccess(false);
		emrResponseBean.setData(null);
		emrResponseBean.setIsAuthorizationPresent(false);
		emrResponseBean.setCanUserAccess(false);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.LoginAndLogOut, LogActionType.LOGIN, -1, Log_Outcome.SECURITY_VIOLATION, "Authentication failed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		
		return emrResponseBean;
	}


	@RequestMapping(value = "loginSuccess",method = RequestMethod.GET)
	public EMRResponseBean loginSuccess(HttpServletRequest request,HttpServletResponse response) 
	{
		String message ="success";
		emrResponseBean.setLogin(true);
		emrResponseBean.setSuccess(true);
		emrResponseBean.setData(message);
		emrResponseBean.setIsAuthorizationPresent(true);
		emrResponseBean.setCanUserAccess(true);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.LoginAndLogOut, LogActionType.LOGIN, -1, Log_Outcome.SUCCESS, "Login Success", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		
		return emrResponseBean;
	}

	
	
	@RequestMapping(value = "loggedOut",method = RequestMethod.GET)
	public String loggedOut(HttpServletRequest request,HttpServletResponse response) 
	{
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.LoginAndLogOut, LogActionType.LOGOUT, -1, Log_Outcome.SUCCESS, "Session logged out", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		
		return "invalidatedSession~~true";
	}
	
}
