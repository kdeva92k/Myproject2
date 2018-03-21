package com.glenwood.glaceemr.server.application.authentication.portal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.services.users.UsersService;
import com.glenwood.glaceemr.server.utils.SessionMap;

@Component
public class PortalLogoutSuccessHandler implements LogoutSuccessHandler{  
	
	@Autowired 
	UsersService usersService;
	
	@Autowired
	SessionMap sessionMap;
	
    @Override  
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,  
                                Authentication authentication) throws IOException, ServletException {  
    	System.out.println("in custom logout success handler-->");
  
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);  
        //redirect to login  
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/api/portal/login/PortalLoginAndLogout/LoggedOut");
        dispatcher.forward(httpServletRequest, httpServletResponse);

//        httpServletResponse.sendRedirect("/office/login/loggedOut");  
    }  
}  