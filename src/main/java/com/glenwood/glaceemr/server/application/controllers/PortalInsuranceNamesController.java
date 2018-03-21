package com.glenwood.glaceemr.server.application.controllers;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PortalSettingsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;



@RestController
@RequestMapping("/glacemonitor/PortalSettings")
public class PortalInsuranceNamesController {
	
	@Autowired
	PortalSettingsService portalSettingsService;
	
	@Autowired
	EMRResponseBean responseBean;
	
	@RequestMapping(value = "/getInsuranceNames", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean getInsuranceNames(@RequestParam(value="searchKey", required=false, defaultValue="") String searchKey)throws Exception{

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			System.out.println("inside getInsuranceNames in new condtroller");
			responseBean.setSuccess(true);
			searchKey = URLDecoder.decode(searchKey, "UTF-8");
			responseBean.setData(portalSettingsService.getInsuranceNames(searchKey));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving Insurance Names list!");
			return responseBean;
		}		
	}
	
}
