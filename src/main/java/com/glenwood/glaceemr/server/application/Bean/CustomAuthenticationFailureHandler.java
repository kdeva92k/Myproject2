package com.glenwood.glaceemr.server.application.Bean;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.Assert;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{
	String request_origin_URL ="";
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		System.out.println("in here in custom authentication failure handler");
		try{
/*			request_origin_URL = request.getParameter("request_origin_url");
			Assert.notNull(request_origin_URL, "request origin url is empty");
			response.sendRedirect(request_origin_URL+"/loginGWT.html?errorString=Login Failed");
*/            RequestDispatcher dispatcher = request.getRequestDispatcher("/api/emr/login/loginFailed");
            dispatcher.forward(request, response);

		} catch (Exception e) {
			
			e.printStackTrace();
			response.sendRedirect("http://dev1.glaceemr.com/OfficeLogin/loginGWT.html?errorString=Login Failed");
		}

		
	}

}
