package com.glenwood.glaceemr.server.application.services.chart.reports;

public interface OverrideAlertsService {
	String insertAlert(String elementids, Integer patientid, Integer fsElementType,Integer flowsheetId,
			Integer overriddenBy,String reason,String data) throws Exception;
}
