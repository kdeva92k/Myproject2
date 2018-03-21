package com.glenwood.glaceemr.server.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.datasource.TennantContextHolder;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.MultiReadHttpServletRequest;

/**
 * Servlet Filter implementation class DataBaseAccessFilter
 */
@Component
public class DataBaseAccessFilter implements Filter {

	HttpServletRequest httpRequest;
	HttpSession session;

	/**
	 * Default constructor. 
	 */
	public DataBaseAccessFilter() {
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

	public static String password="";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


		/*httpRequest = (HttpServletRequest)request;
		session = httpRequest.getSession(false);
		if(session.getAttribute("databasename") == null ||session.getAttribute("databasename") == " "||session.getAttribute("databasename") == "") 

			;
		else{
			System.out.println("in tennetaware"+session.getAttribute("databasename").toString());
			TennantContextHolder.setTennantId(session.getAttribute("databasename").toString());
		}*/
		httpRequest = (HttpServletRequest)request;




		Enumeration<String> headerNames = httpRequest.getHeaderNames();
		Enumeration<String> parametersNames =httpRequest.getParameterNames();
		StringBuffer jb = new StringBuffer();

		MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);

		/*String body = myRequestWrapper.getBody();*/


		String body="";

		///////////////////////
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = multiReadRequest.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {/*
			     if (bufferedReader != null) {
			       try {
			         bufferedReader.close();
			       } catch (IOException ex) {
			         throw ex;
			       }
			     }
		 */}

		body = stringBuilder.toString();


		///////////////////////



		if(body.length()>0){

		}else
		{

			while(parametersNames.hasMoreElements())
			{
				String parameter = parametersNames.nextElement();
			}
		}

		String tennantId="-1";
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		tennantId=HUtil.Nz(httpRequest.getHeader("TENNANTDB"),"-1");
		
		if(tennantId.equalsIgnoreCase("-1"))
			tennantId=HUtil.Nz(request.getParameter("dbname"),"-1");

		if(tennantId != "-1"){
			TennantContextHolder.setTennantId(tennantId);
		}
		
		if(multiReadRequest.getRequestURI().contains("portal_login_security_check")){
			System.out.println("***request is from portal***"+TennantContextHolder.getTennantId());
			chain.doFilter(request, response);
			return;
		}
		
		if(multiReadRequest.getRequestURI().contains("JVMManager.jsp")){
			System.out.println("***request is from JVM***"+TennantContextHolder.getTennantId());
			chain.doFilter(request, response);
			return;
		}
		
		String params[] = multiReadRequest.getRequestURI().split("api/");
		String[] portalParams = params[0].split("/portal/");
		if(portalParams.length>1){
			System.out.println("***request is from portal***"+TennantContextHolder.getTennantId());
			chain.doFilter(request, response);
			return;
		}
			

		while(headerNames.hasMoreElements()){
			String header = headerNames.nextElement();
			/*if(header.trim().equalsIgnoreCase("authorization")){
				String basecut[]=httpRequest.getHeader(header).split("Basic");
				byte[] valueDecoded= Base64.decodeBase64(basecut[1].getBytes() );
				password=new String(valueDecoded).split(":")[1];
			}*/

			if(header.trim().equalsIgnoreCase("Database")){
				tennantId = HUtil.Nz(httpRequest.getHeader(header).toString(),"-1");
				if(tennantId != "-1"){
					TennantContextHolder.setTennantId(tennantId.trim().toLowerCase());
				}

			}
		}
		//		HttpServletRequest httpReq = (HttpServletRequest) request;
		//		System.out.println("request URI--->"+httpReq.getRequestURI());
		//		System.out.println("request url--->"+httpReq.getRequestURL());
		
		String[] dbParams = params[0].split("/glaceemr_backend_hxxx/");
		if(dbParams.length>1){
			String db[] = dbParams[1].split("/");
			if(db[0]!= null){
				System.out.println("***request is from GWT***"+db[0]);
				TennantContextHolder.setTennantId(db[0].trim().toLowerCase());
				request.getServletContext().getRequestDispatcher("/api/"+params[1]).forward(multiReadRequest, response);
			}
		}else{
			System.out.println("***request is from legacy***"+TennantContextHolder.getTennantId());
			chain.doFilter(multiReadRequest, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
