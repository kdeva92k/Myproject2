package com.glenwood.glaceemr.server.application.services.chart.ImportData;

public interface ImportDataService {
	
	String getImportEncList(Integer patientId, Integer encounterId);

	void importData(Integer patientId, Integer encounterId, Integer templateId,
			Integer chartId, Integer tabId, Integer prevEncounterId,
			String dxSpecific, String dxCode, Integer userId);
	
}
