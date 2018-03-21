package com.glenwood.glaceemr.server.application.authentication.portal;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class PortalAuthenticationEntryPoint implements AuthenticationEntryPoint{

	
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		//logger.info("In custom authentication entry point --->Logger:::Status:::"+response.getStatus());
        System.out.println("In EMRPortalAuthenticationEntryPoint:::::::::::----------->");
        System.out.println("Username----EMR--->"+request.getParameter("username"));
        System.out.println("Password----EMR--->"+request.getParameter("password"));
		response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );       		
	}

}
