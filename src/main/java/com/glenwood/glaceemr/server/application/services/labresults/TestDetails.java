package com.glenwood.glaceemr.server.application.services.labresults;


public class TestDetails {

	String testDetailId;
	String reviewedOn;
	String testNotes;
	String testStatus;
	String prelimStatus;
	String finalStatus;
	
	public String getTestDetailId() {
		return testDetailId;
	}
	public void setTestDetailId(String testDetailId) {
		this.testDetailId = testDetailId;
	}
	public String getReviewedOn() {
		return reviewedOn;
	}
	public void setReviewedOn(String reviewedOn) {
		this.reviewedOn = reviewedOn;
	}
	public String getTestNotes() {
		return testNotes;
	}
	public void setTestNotes(String testNotes) {
		this.testNotes = testNotes;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getPrelimStatus() {
		return prelimStatus;
	}
	public void setPrelimStatus(String prelimStatus) {
		this.prelimStatus = prelimStatus;
	}
	public String getFinalStatus() {
		return finalStatus;
	}
	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}	
}
