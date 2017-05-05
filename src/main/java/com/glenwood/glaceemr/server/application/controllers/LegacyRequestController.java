package com.glenwood.glaceemr.server.application.controllers;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.LegacyConnectorBean;
import com.glenwood.glaceemr.server.utils.RestDispatcherTemplate;

@RestController
@Transactional
@RequestMapping(value="/user/connect")
public class LegacyRequestController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	RestDispatcherTemplate requestDispatcher;
	
	@Autowired
	EMRResponseBean emrResponseBean;
	
	/**
	 * Method to contact legacy using rest template
	 * @return
	 */
	@RequestMapping(value="/legacy", method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean connectToLegacy(
			 @RequestParam(value="url", required=true, defaultValue="true") String urlPrefix, 
			 @RequestParam(value="method", required=true, defaultValue="GET") String httpMethod, 
			 @RequestParam(value="params", required=false, defaultValue="") String params){

		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
		String startTime = ""; //emrResponseBean.getStartTime();
		try {
			JSONObject paramsObj = new JSONObject(params);
			String[] paramNames=JSONObject.getNames(paramsObj);
			
			for(String param: paramNames){
				paramMap.set(param, paramsObj.getString(param));
			}
			paramMap.set("isFromSprings", "true");
			if(httpMethod.equalsIgnoreCase("GET")){
				emrResponseBean = requestDispatcher.sendGet(request, urlPrefix, paramMap);
			}
			else if(httpMethod.equalsIgnoreCase("POST")){
				emrResponseBean = requestDispatcher.sendPost(request, urlPrefix, paramMap);
			}
//			emrResponseBean.setStartTime(startTime);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return emrResponseBean;
		
	}
	
	/**
	 * Method to contact legacy using rest template
	 * @return
	 */
	@RequestMapping(value="/post/legacy", method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean connectToLegacyPost(
			 @RequestBody LegacyConnectorBean bean){

		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
		String startTime = ""; //emrResponseBean.getStartTime();
		try {
			JSONObject paramsObj = new JSONObject(bean.getParams());
			String[] paramNames=JSONObject.getNames(paramsObj);
			
			for(String param: paramNames){
				paramMap.set(param, URLDecoder.decode(paramsObj.getString(param),"UTF-8"));
			}
			paramMap.set("isFromSprings", "true");
			if(bean.getMethod().equalsIgnoreCase("GET")){
				emrResponseBean = requestDispatcher.sendGet(request, bean.getUrl(), paramMap);
			}
			else if(bean.getMethod().equalsIgnoreCase("POST")){
				emrResponseBean = requestDispatcher.sendPost(request, bean.getUrl(), paramMap);
			}
//			emrResponseBean.setStartTime(startTime);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return emrResponseBean;
		
	}
}