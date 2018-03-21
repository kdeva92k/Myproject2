package com.glenwood.glaceemr.server.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.RestDispatcherTemplate;
import com.glenwood.glaceemr.server.utils.TokenValidationRequest;
import com.glenwood.glaceemr.server.utils.TokenValidationResponse;

/**
 * Servlet Filter implementation class TokenValidationFilter
 */
@WebFilter("/TokenValidationFilter")
public class TokenValidationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TokenValidationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String token = HUtil.Nz(httpRequest.getParameter("access_token"), null);
		String resource = HUtil.Nz(httpRequest.getParameter("resource_id"), null);
		TokenValidationResponse tokenValidationResponse;
		ObjectMapper objectMapper=new ObjectMapper();
		if(token!=null){
			String connectionPath="https://fasttrack.glaceemr.com/glaceoauth/validate/token";
			TokenValidationRequest validationRequest=new TokenValidationRequest();
			validationRequest.setAccessToken(token);
			validationRequest.setDbname("glaceoauth");
			validationRequest.setResourceId(resource);
			validationRequest.setRemoteAddress("127.0.0.1");
			validationRequest.setScope("glace_read");
			RestDispatcherTemplate restDispatcherTemplate=new RestDispatcherTemplate();
			tokenValidationResponse = restDispatcherTemplate.validateToken(httpRequest, connectionPath, validationRequest);
			request.setAttribute("isAccessTokenValid", tokenValidationResponse.getValid());
			request.setAttribute("message", tokenValidationResponse.getMessage());
			request.setAttribute("access_token", token);
			System.out.println("glaceemr_backend:::::::::::::::TokenValidationResponse::::::::"+objectMapper.writeValueAsString(tokenValidationResponse));
			if(tokenValidationResponse.valid)
				chain.doFilter(request, response);
			else{
				PrintWriter pw=response.getWriter();
				pw.println(objectMapper.writeValueAsString(tokenValidationResponse));
			}
				
		}else{
			System.out.println("No token parameter available.");
			tokenValidationResponse=new TokenValidationResponse();
			tokenValidationResponse.setValid(false);
			tokenValidationResponse.setMessage("No token parameter available.");
			response.setContentType("application/json");
			PrintWriter pw=response.getWriter();
			System.out.println("glaceemr_backend:::::::::::::::TokenValidationResponse::::::::"+objectMapper.writeValueAsString(tokenValidationResponse));
			pw.println(objectMapper.writeValueAsString(tokenValidationResponse));
			//chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
