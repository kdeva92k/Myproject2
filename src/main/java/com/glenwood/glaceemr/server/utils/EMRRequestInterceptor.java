/**
 * 
 */
package com.glenwood.glaceemr.server.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Anupama
 * all the requests and responses are assumed to be controllers
 * Requests will be handled by Spring
 * There is no need for tomcat or any other application server to handle the requests
 * 
 * Differentiation for any requirement is taken as
 * 
 * Controller name ---controllerName.Action is considered as action file
 * 
 *
 */
@Component
public class EMRRequestInterceptor implements HandlerInterceptor{

	@Autowired
	EMRResponseBean emrResponseBean;
	
	@Autowired
	SessionMap sessionMap;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		

//		String tennantId = sessionMap.getDbName();
//		System.out.println("dbname -->"+tennantId);
	
		System.out.println("EMR Requestor URL :: "+request.getRequestURI());

		if(request.getHeader("STARTSAT") != null)
		{
			emrResponseBean.setStartTime(request.getHeader("STARTSAT"));
		}
		
		String[] splitURl = request.getRequestURI().split(request.getServletPath()+"/");
		String formattedURl= splitURl[1].substring(1, splitURl[1].length());
		formattedURl="/"+formattedURl; 
		if(formattedURl.contains("/")){
			String actionName = formattedURl.substring(0, formattedURl.indexOf("/")).toLowerCase();
			setLoginParameterInResponse(actionName);
		}else{
			String servletName=formattedURl.toLowerCase();
			setLoginParameterInResponse(servletName);
		}

		/*if(tennantId != "-1"){
			TennantContextHolder.setTennantId(tennantId);
		}*/
		return true;
	}


	public void setLoginParameterInResponse(String actionName){

		if(actionName.contains(".action")){
			
			if(emrResponseBean.getCanUserAccess()==null)
				emrResponseBean.setCanUserAccess(true);
			if(emrResponseBean.getIsAuthorizationPresent() == null)
				emrResponseBean.setIsAuthorizationPresent(true);
			
		}
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
					throws Exception {


	}

}
