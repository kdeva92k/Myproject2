package com.glenwood.glaceemr.server.application.services.labresults;

import java.util.List;

public class AttachLabData {
	String userId;
	String sharedFolderPath;
	String patientId;
	String hl7FileId;
	List<String> testDetailId;
	List<String> testId;
	List<String> testName;
	List<String> resultName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSharedFolderPath() {
		return sharedFolderPath;
	}
	public void setSharedFolderPath(String sharedFolderPath) {
		this.sharedFolderPath = sharedFolderPath;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getHl7FileId() {
		return hl7FileId;
	}
	public void setHl7FileId(String hl7FileId) {
		this.hl7FileId = hl7FileId;
	}
	public List<String> getTestDetailId() {
		return testDetailId;
	}
	public void setTestDetailId(List<String> testDetailId) {
		this.testDetailId = testDetailId;
	}
	public List<String> getTestId() {
		return testId;
	}
	public void setTestId(List<String> testId) {
		this.testId = testId;
	}
	public List<String> getTestName() {
		return testName;
	}
	public void setTestName(List<String> testName) {
		this.testName = testName;
	}
	public List<String> getResultName() {
		return resultName;
	}
	public void setResultName(List<String> resultName) {
		this.resultName = resultName;
	}
}
