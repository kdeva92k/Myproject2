package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistrationSearchBean;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chartcenter.ChartcenterService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@RequestMapping(value="/user/PatientSearch.Action")
public class ChartcenterController {

	@Autowired(required=true)
	private ChartcenterService patientSearchService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(ChartcenterController.class);
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public EMRResponseBean   getSearchResult(
			@RequestParam(value="toSearchData",required = false)String toSearchData,
			@RequestParam(value="searchType",required = false)String searchTypeParam) throws Exception{
		logger.debug("This is an info log entry");
        
		Page<PatientRegistration> patients = patientSearchService.getPatientSearchResult(toSearchData,searchTypeParam);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.PATIENT_REGISTRATION,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Sucessfull login User Name(" +1+")",-1,request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		EMRResponseBean result=new EMRResponseBean();
		result.setData(patients);
			return result;
	}
	
	@RequestMapping(value = "/searchByPatientName",method = RequestMethod.GET)
	public EMRResponseBean   getSearchByPatientName(
			@RequestParam(value="toSearchData",required = false)String toSearchData) throws Exception{
		logger.debug("This is an info log entry");
        
		List<PatientRegistrationSearchBean> patients = patientSearchService.getPatientNameBySearch(toSearchData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.PATIENT_REGISTRATION,LogActionType.VIEW,1,Log_Outcome.SUCCESS,"Sucessfull login User Name(" +1+")",-1,request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		EMRResponseBean result=new EMRResponseBean();
		result.setData(patients);
			return result;
	}
	
}
