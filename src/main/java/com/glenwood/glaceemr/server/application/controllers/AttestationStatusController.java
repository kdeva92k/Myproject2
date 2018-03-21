package com.glenwood.glaceemr.server.application.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.Bean.AttestationBean;
import com.glenwood.glaceemr.server.application.Bean.MUAnalyticsBean;
import com.glenwood.glaceemr.server.application.models.AttestationStatus;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.AttestationStatusService;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping(value = "/user/ReportingStatus")
public class AttestationStatusController {

	@Autowired
	AttestationStatusService statusSerivce;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/getReportingDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getProviderReportingDetails(
			@RequestParam(value = "repYear", required = true, defaultValue="2016") Integer programYear,
			@RequestParam(value = "providerId", required = true, defaultValue="-1") Integer providerId){
		
		EMRResponseBean response=new EMRResponseBean();
		
		List<AttestationStatus> providerReportingInfo = statusSerivce.getProviderReportingDetails(programYear);
		
		response.setData(providerReportingInfo);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.VIEW, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting provider attestation details" , -1, request.getRemoteAddr(),-1,"providerId="+providerId+"&reportingYear="+programYear,LogUserType.USER_LOGIN, "", "");
		
		return response;
		
	}
	
	@RequestMapping(value = "/saveAttestationDetails", method = RequestMethod.POST)
	public EMRResponseBean saveReportingDetails(@RequestBody AttestationBean providerInfo){
		
		EMRResponseBean response=new EMRResponseBean();
		
		List<AttestationStatus> providerReportingInfo = statusSerivce.saveReportingDetails(providerInfo.getReportingYear(), Integer.parseInt(providerInfo.getReportingMethod()), Integer.parseInt(providerInfo.getReportingType()), providerInfo.getReportingComments(), Integer.parseInt(providerInfo.getReportingStatus()), providerInfo.getEmployeeID());
		
		response.setData(providerReportingInfo);

		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.UPDATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in saving provider attestation details" , -1, request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		
		return response;
		
	}
	
	@RequestMapping(value = "/getActiveProviders", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getProvidersList(){
		
		EMRResponseBean response=new EMRResponseBean();
		
		List<EmployeeDataBean> employees = statusSerivce.getActiveProviderList();
		
		response.setData(employees);
	
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.VIEW, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting active providers list" , -1, request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		
		return response;
		
	}
	
	@RequestMapping(value = "/getAllReportingDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAllProviderReportingStatus() throws JSONException{
		
		EMRResponseBean response=new EMRResponseBean();
		
		List<MUAnalyticsBean> reportingInfo = statusSerivce.getAllActiveProviderStatus();
		
		Map<String, List<MUAnalyticsBean>> result = new HashMap<String, List<MUAnalyticsBean>>();
		
		result.put("reportingYears", reportingInfo);
		
		response.setData(result);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.VIEW, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting active providers attestation status details" , -1, request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		
		return response;
		
	}
	
}
