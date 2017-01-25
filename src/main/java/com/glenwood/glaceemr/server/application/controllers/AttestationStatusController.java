package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.Bean.AttestationBean;
import com.glenwood.glaceemr.server.application.Bean.ReportingInfo;
import com.glenwood.glaceemr.server.application.models.AttestationStatus;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.AttestationStatusService;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.wordnik.swagger.annotations.Api;

@Api(value = "AttestationStatusController", description = "AttestationStatusController", consumes = "application/json")
@RestController
@Transactional
@RequestMapping(value = "/user/ReportingStatus")
public class AttestationStatusController {

	@Autowired
	AttestationStatusService statusSerivce;
	
	@RequestMapping(value = "/getReportingDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getProviderReportingDetails(
			@RequestParam(value = "repYear", required = true, defaultValue="2016") Integer programYear,
			@RequestParam(value = "providerId", required = true, defaultValue="-1") Integer providerId){
		
		EMRResponseBean response=new EMRResponseBean();
		
		List<AttestationStatus> providerReportingInfo = statusSerivce.getProviderReportingDetails(programYear);
		
		response.setData(providerReportingInfo);
		
		return response;
		
	}
	
	@RequestMapping(value = "/saveAttestationDetails", method = RequestMethod.POST)
	public EMRResponseBean saveReportingDetails(@RequestBody AttestationBean providerInfo){
		
		EMRResponseBean response=new EMRResponseBean();
		
		List<AttestationStatus> providerReportingInfo = statusSerivce.saveReportingDetails(providerInfo.getReportingYear(), Integer.parseInt(providerInfo.getReportingMethod()), Integer.parseInt(providerInfo.getReportingType()), providerInfo.getReportingComments(), Integer.parseInt(providerInfo.getReportingStatus()), providerInfo.getEmployeeID());
		
		response.setData(providerReportingInfo);
		
		return response;
		
	}
	
	@RequestMapping(value = "/getActiveProviders", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getProvidersList(){
		
		EMRResponseBean response=new EMRResponseBean();
		
		List<EmployeeDataBean> employees = statusSerivce.getActiveProviderList();
		
		response.setData(employees);
		
		return response;
		
	}
	
	@RequestMapping(value = "/getAllReportingDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAllProviderReportingStatus(
			@RequestParam(value = "reportingYear", required = true, defaultValue="2016") Integer reportingYear){
		
		EMRResponseBean response=new EMRResponseBean();
		
		ReportingInfo reportingInfo = statusSerivce.getAllActiveProviderStatus(reportingYear);
		
		response.setData(reportingInfo);
		
		return response;
		
	}
	
}