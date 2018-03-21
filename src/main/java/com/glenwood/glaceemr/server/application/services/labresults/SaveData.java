package com.glenwood.glaceemr.server.application.services.labresults;

import java.util.List;

public class SaveData {

	String doctorId;
	String hl7FileId;
	Boolean isReviewAll;
	List<TestDetails> testDetails;
	
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getHl7FileId() {
		return hl7FileId;
	}
	public void setHl7FileId(String hl7FileId) {
		this.hl7FileId = hl7FileId;
	}
	public Boolean getIsReviewAll() {
		return isReviewAll;
	}
	public void setIsReviewAll(Boolean isReviewAll) {
		this.isReviewAll = isReviewAll;
	}
	public List<TestDetails> getTestDetails() {
		return testDetails;
	}
	public void setTestDetails(List<TestDetails> testDetails) {
		this.testDetails = testDetails;
	}
}
