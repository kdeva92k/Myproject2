package com.glenwood.glaceemr.server.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class RestDispatcherTemplate {
	
	@Autowired
	ObjectMapper objectMapper;

	public EMRResponseBean sendGet(HttpServletRequest request, String connectionPath,MultiValueMap<String, String> params) throws JsonParseException, JsonMappingException, IOException{
		
		EMRResponseBean responseBean=new EMRResponseBean();
		
		try {
			
			RestTemplate restTemplate = new RestTemplate();

	        HttpHeaders headers = new HttpHeaders();
	        
	        String baseURL=request.getHeader("URL");
	        
	        if(baseURL.contains(".com"))
			{
				String fromPort[] = baseURL.split(".com");
				String port = fromPort[1];
				String p[] = port.split("/");
				String portNum = p[0];
				baseURL = baseURL.replace(portNum, "");
			}
			else if(baseURL.contains(".net"))
			{
				String s[] = baseURL.split(".net");
				String port = s[1];
				String p[] = port.split("/");
				String portNum = p[0];
				baseURL = baseURL.replace(portNum, "");
			}
	        
	        if(connectionPath.contains("/patientdetails/getSession")){
	        	baseURL = baseURL.replace("/GWT", "");
	        }
	        System.out.println("URL :::::: "+baseURL+connectionPath);
	        
	        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL+connectionPath);
	        
	        
	        builder.queryParams(params);
	                
	        setAndCheckJSESSIONIDCookie(request, headers);
	        
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        
	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	        ResponseEntity<String> result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
			String responseBuffer=null;
	        
	        if(result.hasBody())
	        	responseBuffer=result.getBody();
	        	
	        if(connectionPath.contains("/SoapNotesPrint.Action")){
	        	responseBean.setData(URLEncoder.encode(responseBuffer,"UTF-8"));
	        }
	        else
	        {
	        	responseBean = objectMapper.readValue(responseBuffer.toString(), EMRResponseBean.class);
	        }
	                        		
			return responseBean;
			
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setLogin(false);
			responseBean.setIsAuthorizationPresent(false);
			responseBean.setData("Error in glaceemr data api ! Connection Path:"+connectionPath);
			responseBean.setCanUserAccess(false);
			return responseBean;
		}
		
	}
	
	private boolean setAndCheckJSESSIONIDCookie(HttpServletRequest request,
			HttpHeaders headers) {
			
			String EMRJSESSIONID=request.getHeader("EMRJSESSIONID");
			
	        headers.set("Cookie", "JSESSIONID="+EMRJSESSIONID);
	        
	        if(request.getParameter("dbname")!=null&&request.getParameter("dbname").trim().length()>0)
	        	headers.set("TENNANTDB", request.getParameter("dbname"));
	        else
	        	headers.set("TENNANTDB", request.getHeader("TENNANTDB"));
	        
	        if(EMRJSESSIONID==null)
	        	return false;
	        else 
	        	return true;
	}

	public EMRResponseBean sendPost(HttpServletRequest request, String connectionPath,MultiValueMap<String, String> requestObj) throws JsonParseException, JsonMappingException, IOException{EMRResponseBean responseBean=new EMRResponseBean();
	
	try {RestTemplate restTemplate = new RestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	
	String baseURL=request.getHeader("URL");
    
    if(baseURL.contains(".com"))
	{
		String fromPort[] = baseURL.split(".com");
		String port = fromPort[1];
		String p[] = port.split("/");
		String portNum = p[0];
		baseURL = baseURL.replace(portNum, "");
	}
	else if(baseURL.contains(".net"))
	{
		String s[] = baseURL.split(".net");
		String port = s[1];
		String p[] = port.split("/");
		String portNum = p[0];
		baseURL = baseURL.replace(portNum, "");
	}
    
	 
	setAndCheckJSESSIONIDCookie(request, headers);
    
	System.out.println("URL :::: "+baseURL+connectionPath);
	HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(requestObj, headers);
	
	responseBean = restTemplate.postForObject(baseURL+connectionPath, entity, EMRResponseBean.class);

	return responseBean;} catch (Exception e) {
		e.printStackTrace();
		responseBean.setSuccess(false);
		responseBean.setLogin(false);
		responseBean.setIsAuthorizationPresent(false);
		responseBean.setData("Error in glaceemr data api ! Connection Path:"+connectionPath);
		responseBean.setCanUserAccess(false);
		return responseBean;
	}}
	
	public TokenValidationResponse validateToken(HttpServletRequest request, String connectionPath,Object requestObj) throws JsonParseException, JsonMappingException, IOException{


		TokenValidationResponse tokenValidationResponse=new TokenValidationResponse(); 
		
		try {

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();

			setAndCheckJSESSIONIDCookie(request, headers);

			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			System.out.println("***********************************"+objectMapper.writeValueAsString(requestObj));

			HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(requestObj), headers);

			tokenValidationResponse = restTemplate.postForObject(connectionPath, entity, TokenValidationResponse.class);

			return tokenValidationResponse;

		} catch (Exception e) {
			e.printStackTrace();
			tokenValidationResponse.setMessage("Error in validating the token.");
			tokenValidationResponse.setValid(false);
			return tokenValidationResponse;
		}

	}
}