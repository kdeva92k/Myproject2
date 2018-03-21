package com.glenwood.glaceemr.server.application.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.utils.GlaceMonitoringParameters;


/**
 * @author Anupama
 *
 */
@RestController
@RequestMapping(value="/glacemonitor/monitor")

public class GlaceMonitoringController {
	
	@Autowired
	AuditTrailService auditTrailService;

	@RequestMapping(value = "/GlaceMonitoringAdapter",method = RequestMethod.POST,produces="text/html")
	@ResponseBody
	public String glaceMonitoringAdapter() throws Exception {
		GlaceMonitoringParameters monitoringParams = auditTrailService.getServerMonitorResults();
		String glaceMonitoringResult = "Shared:" + monitoringParams.getShared() + "\n" + "DB:" + monitoringParams.getDB() + "\n"
				+ "DBRT:" + monitoringParams.getDBRT() + "\n" + "DBFS:" + monitoringParams.getDBFS() + "\n"
				+ "SHFS:" + monitoringParams.getSHFS() + "\n" + "TFS:" + monitoringParams.getTFS() + "\n" + "RFS:"
				+ monitoringParams.getRFS() + "\n" + "FMEM:" + monitoringParams.getFMEM() + "\n" + "FJVM:" + monitoringParams.getFJVM()
				+ "\n" + "VARFS:" + monitoringParams.getVARFS() + "\n" + "USRFS:" + monitoringParams.getUSRFS();
		return glaceMonitoringResult;
	}

}