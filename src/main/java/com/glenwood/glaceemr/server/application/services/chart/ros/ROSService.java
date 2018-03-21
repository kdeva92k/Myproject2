package com.glenwood.glaceemr.server.application.services.chart.ros;

import java.util.List;



public interface ROSService  {

	List<ROSSystemBean> getROSElements(String clientId,Integer patientId, Integer chartId,Integer encounterId, Integer templateId, Integer tabId);

	String getROSNotes(Integer patientId, Integer encounterId);

	
	
}
