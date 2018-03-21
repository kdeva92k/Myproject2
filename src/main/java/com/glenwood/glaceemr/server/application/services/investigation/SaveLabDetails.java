package com.glenwood.glaceemr.server.application.services.investigation;

public class SaveLabDetails {

	Integer encounterId;
	Integer patientId;
	Integer chartId;
	Integer testId;
	Integer userId;
	String fullData;
	String groupId;
	Integer testDetailId;
	String requestToSave;
	String testStatusSave;
	
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
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
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFullData() {
		return fullData;
	}
	public void setFullData(String fullData) {
		this.fullData = fullData;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Integer getTestDetailId() {
		return testDetailId;
	}
	public void setTestDetailId(Integer testDetailId) {
		this.testDetailId = testDetailId;
	}
	public String getRequestToSave() {
		return requestToSave;
	}
	public void setRequestToSave(String requestToSave) {
		this.requestToSave = requestToSave;
	}
	public void setTestStatus(String testStatusSave) {
		this.testStatusSave = testStatusSave;
	}
	public String getTestStatusSave() {
		return testStatusSave;
	}
}