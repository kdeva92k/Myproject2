package com.glenwood.glaceemr.server.application.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.Bean.MUAnalyticsBean;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.AttestationStatusService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping(value = "/glacemonitor/ReportingStatus")
public class GlaceAttestationController {

	@Autowired
	AttestationStatusService statusSerivce;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/getAllReportingDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAllProviderReportingStatus() throws JSONException{
		
		EMRResponseBean response=new EMRResponseBean();
		
		List<MUAnalyticsBean> reportingInfo = statusSerivce.getAllActiveProviderStatus();
		
		Map<String, List<MUAnalyticsBean>> result = new HashMap<String, List<MUAnalyticsBean>>();
		
		result.put("reportingYears", reportingInfo);
		
		response.setData(result);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting attestation details of all providers" , -1, request.getRemoteAddr(),-1,"",LogUserType.GLACE_BATCH, "", "");
		
		return response;
		
	}
	
}
