package com.glenwood.glaceemr.server.application.authentication.portal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.Assert;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;

public class PortalAuthenticationFailureHandler implements AuthenticationFailureHandler{
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	String request_origin_URL ="";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("application/json"); 
		
		try{
			
			System.out.println("In PortalAuthenticationFailureHandler");
			auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
					AuditTrailEnumConstants.LogActionType.LOGIN,1,AuditTrailEnumConstants.Log_Outcome.FAILURE,"Unknown user tried to log into Patient Portal.",-1,
					request.getRemoteAddr(),-1,"",
					AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Unknown user tried to log into Patient Portal.","");
			out.println("{\"sessionId\":\"-1\",\"userId\":-1,\"login\":false,\"isAuthenticated\":false,\"log\":0}");
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
					AuditTrailEnumConstants.LogActionType.LOGIN,1,AuditTrailEnumConstants.Log_Outcome.FAILURE,"Unknown user tried to log into Patient Portal.",-1,
					request.getRemoteAddr(),-1,"",
					AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Unknown user tried to log into Patient Portal.","");
			out.println("{\"sessionId\":\"-1\",\"userId\":-1,\"login\":false,\"isAuthenticated\":false,\"log\":0}");
			out.println("{\"sessionId\":\"-1\",\"userId\":-1,\"login\":false,\"isAuthenticated\":false,\"log\":0}");
		}
		
	}

}
