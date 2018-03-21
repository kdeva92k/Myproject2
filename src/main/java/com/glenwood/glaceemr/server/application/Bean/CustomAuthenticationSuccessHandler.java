package com.glenwood.glaceemr.server.application.Bean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
/*
	@Autowired
	EMRResponseBean emrResponseBean;
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	UsersService usersService;*/
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication){
		
		System.out.println("in on authentication success handler >>>>>>");
		/*try{
			emrResponseBean.setLogin(true);
			sessionMap.setUserID(usersService.findByUserName(authentication.getName().toString()));
			
				System.out.println("in authentication success handler >> userid >>"+sessionMap.getUserID());			
		}catch(Exception ex){
			try {
				throw ex;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}

}
