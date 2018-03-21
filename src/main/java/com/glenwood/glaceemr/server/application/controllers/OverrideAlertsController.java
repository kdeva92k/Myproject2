package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.reports.OverrideAlertsService;
import com.glenwood.glaceemr.server.application.services.chart.reports.OverrideBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller to override the data
 * @author smita
 */

@RestController
@RequestMapping(value="/user/OverrideAlerts")
public class OverrideAlertsController {
	
	@Autowired(required=true)
	OverrideAlertsService overrideAlertsService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(OverrideAlertsController.class);
	
	/**
	 * Request to override the flowsheet elements(includes vitals, clinical elements and medication section)
	 * @param overrideBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/OverrideFlowsheet",method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean getFlowsheets(@RequestBody OverrideBean overrideBean) throws Exception{
		logger.debug("Begin of request to override the Flowsheet data(includes vitals, clinical elements and medication section overriding functionality.)");
		String overrideAlertMessage = overrideAlertsService.insertAlert(overrideBean.getElementids(),overrideBean.getPatientid(),overrideBean.getFsElementType(),overrideBean.getFlowsheetId(),overrideBean.getUserId(),overrideBean.getReason(),overrideBean.getData());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.HMR,LogActionType.CREATE,1,Log_Outcome.SUCCESS,"Successfully overridden test from flowsheet view (elementids="+overrideBean.getElementids()+", PatientID="+overrideBean.getPatientid()+",InstanceId=-100 )",-1,request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to override the Flowsheet data(includes vitals, clinical elements and medication section overriding functionality.)");
		EMRResponseBean alertMessage=new EMRResponseBean();
		alertMessage.setData(overrideAlertMessage);
		return alertMessage;
	}
}