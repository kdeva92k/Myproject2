package com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData;

public interface SaveClinicalDataService {

	void saveData(Integer patientId, Integer encounterId, Integer chartId,Integer templateId, String patientElementsJSON) throws Exception;

}
