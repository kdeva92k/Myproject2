package com.glenwood.glaceemr.server.application.services.chart.dischargeVitals;

import java.util.HashMap;
import java.util.Map;

public class DischargeSaveVitalBean {

	Integer patientId;
	Integer chartId;
	Integer encounterId;
	Map<String, String> savedataMap = new HashMap<String, String>();
	Map<String, String> deletedataMap = new HashMap<String, String>();
	
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getChartId() {
		return chartId;
	}
	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public Map<String, String> getSavedataMap() {
		return savedataMap;
	}
	public void setSavedataMap(Map<String, String> savedataMap) {
		this.savedataMap = savedataMap;
	}
	public Map<String, String> getDeletedataMap() {
		return deletedataMap;
	}
	public void setDeletedataMap(Map<String, String> deletedataMap) {
		this.deletedataMap = deletedataMap;
	}
	
	
	
	
	
}
