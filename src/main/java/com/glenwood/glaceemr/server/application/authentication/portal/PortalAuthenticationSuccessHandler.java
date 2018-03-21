package com.glenwood.glaceemr.server.application.authentication.portal;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.models.PortalUser;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.portalLogin.PortalLoginService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;



public class PortalAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	EMRResponseBean emrResponseBean;
	
	@Autowired
	SessionMap sessionMap;

	@Autowired
	PortalLoginService portalLoginService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication){
		try{
			System.out.println("In onAuthenticationSuccess PortalAuthenticationSuccessHandler");
			emrResponseBean.setLogin(true);
			PortalUser portalUser=portalLoginService.findByUserNameIgnoreCase(authentication.getName().toString());
			sessionMap.setPortalUserID(portalUser.getId());
			String dbAccountId = request.getParameter("dbname");
			String portal_login_url=request.getParameter("portal_login_url");
			String portal_login_context=request.getParameter("portal_login_context");
			String portal_apache_url = request.getParameter("portal_apache_url");
			String portal_tomcat_url = request.getParameter("portal_tomcat_url");
			String portal_tomcat_context = request.getParameter("portal_tomcat_context");
			String portal_spring_context = request.getParameter("portal_spring_context");
			String baseURL=request.getParameter("baseURL");
			Assert.notNull(dbAccountId, "dbAccountId is empty");
			Assert.notNull(portal_apache_url, "portal_apache_url is empty");
			sessionMap.setDbName(dbAccountId);
			sessionMap.setPracticeAccountId(dbAccountId);
			sessionMap.setPortalUser(authentication.getName().toString());
			sessionMap.setPortalSessionID(request.getSession().getId());
			sessionMap.setPortalDBName(dbAccountId);
			sessionMap.setPortalLoginUrl(portal_login_url);
			sessionMap.setPortalLoginContext(portal_login_context);
			sessionMap.setGlaceApacheUrl(portal_apache_url);
			sessionMap.setGlaceTomcatUrl(portal_tomcat_url);
			sessionMap.setGlaceTomcatContext(portal_tomcat_context);
			sessionMap.setGlaceSpringContext(portal_spring_context);
            System.out.println(":::::::::::::::: PortalAuthenticationSuccessHandler ::::::::GLACE EMR BACKEND:::::::");
            
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(45*60);
			PrintWriter out=response.getWriter();
			response.addCookie(new Cookie("JSESSIONID", request.getSession().getId()));
			response.addHeader("PORTALSESSIONID", request.getSession().getId());
			response.setContentType("application/json"); 

			if(sessionMap.getPortalPassworResetCount()<=0){

				auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
						AuditTrailEnumConstants.LogActionType.LOGIN,1,AuditTrailEnumConstants.Log_Outcome.FAILURE,"Patient Portal Login Failed.",-1,
						request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getId())),"",
						AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+portalUser.getId()+" tried to log into Patient Portal.","");
				out.println("{\"sessionId\":\""+request.getSession().getId()+"\",\"userId\":"+sessionMap.getPortalUserID()+",\"login\":true,\"isAuthenticated\":true,\"log\":12}");
				return;
			}

			if(sessionMap.getIsPortalUserActive()!=1){

				auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
						AuditTrailEnumConstants.LogActionType.LOGIN,1,AuditTrailEnumConstants.Log_Outcome.FAILURE,"Patient Portal Login Failed. Inactive account.",-1,
						request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getId())),"",
						AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+portalUser.getId()+" tried to log into Patient Portal.","");
				out.println("{\"sessionId\":\""+request.getSession().getId()+"\",\"userId\":"+sessionMap.getPortalUserID()+",\"login\":true,\"isAuthenticated\":true,\"log\":8}");
				return;
			}

			if(sessionMap.getIsOldPortalUser()!=1){
				
				auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
						AuditTrailEnumConstants.LogActionType.LOGIN,1,AuditTrailEnumConstants.Log_Outcome.FAILURE,"Patient Portal Login Failed. Inactive account.",-1,
						request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getId())),"",
						AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+portalUser.getId()+" tried to log into Patient Portal.","");
				out.println("{\"sessionId\":\""+request.getSession().getId()+"\",\"userId\":"+sessionMap.getPortalUserID()+",\"login\":true,\"isAuthenticated\":true,\"log\":8}");
				return;
			}

			auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
					AuditTrailEnumConstants.LogActionType.LOGIN,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Logged into Patient Portal successfully.",-1,
					request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getId())),"",
					AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+portalUser.getId()+" logged into Patient Portal successfully.","");
			out.println("{\"sessionId\":\""+request.getSession().getId()+"\",\"userId\":"+sessionMap.getPortalUserID()+",\"login\":true,\"isAuthenticated\":true,\"log\":-1}");

		}catch(Exception ex){
			try {
				throw ex;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
