package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.util.List;

public interface ExaminationService {

	List<CustomPESystem> getActiveSystems(String clientId,Integer patientId,Integer encounterId,Integer chartId,Integer templateId);

	CustomPESystem getSystemActiveElements(String clientId,Integer patientId,Integer chartId, Integer encounterId, Integer templateId,Integer selectedSystemId);

	void markAllNormal(Integer patientId, Integer encounterId,Integer templateId,Integer chartId,String systemIds);

	void clearAllNormal(Integer patientId, Integer encounterId,Integer templateId, Integer chartId, String systemIds);

	String getQuickNotes(Integer tabId, String elementId);

	String getPENotes(Integer patientId, Integer encounterId);

	String addQuickNotes(Integer tabId, String elementId, String elementData);

	String deleteQuickNotes(Integer dataListId);

}
