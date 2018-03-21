package com.glenwood.glaceemr.server.application.Bean;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
//		System.out.println("in custom authentication entry point --->");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/api/emr/login/loginFailed");
        dispatcher.forward(request, response);
		
	}

}
