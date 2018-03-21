package com.glenwood.glaceemr.server.application.services.chart.HPI;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

public interface HPIService {

	JSONArray getSymptomData(String clientId, Integer patientId,
			Integer chartId, Integer encounterId, Integer templateId, String symptomGWId, String symptomId, String isFollowUp, String tabId) throws JSONException;

	JSONArray fetchCCData(Integer patientId, Integer chartId, Integer encounterId);

	String fetchPatientHPINotesData(Integer patientId, Integer chartId,
			Integer encounterId);

	JSONArray fetchDefaultShortcutData(Integer mode);

	void deleteSymptomDetails(String symptomGWId, Integer patientId, Integer encounterId);

	JSONArray fetchShortcutData(String shortcutId);

	JSONArray fetchShortCutListBasedOnSearchString(Integer mode, String searchStr,
			Integer limit, Integer offset);

	void addShorctutInCC(String complaint);

	void addShorctutInNotes(String id, String shortCutCode, String description,
			String categoryId);

	String fetchSymptom(Integer patientId, Integer chartId, Integer encounterId,
			Integer templateId, String isCompleted, String symptomTypeList,
			String isFollowUp,String clientId);

	List<HPISymptomBean> getWhereHpiSympGwidNotInPatElemGwid(int ageinDay,
			Integer templateId, String searchStr, Integer patientId,
			boolean isAgeBased, int mode);

}
