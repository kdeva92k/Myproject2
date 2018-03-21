package com.glenwood.glaceemr.server.application.services.investigation;

public class OrderLog {
	String labName;
	String testDetailId;
	String testId;
	String encounterId;
	String orderedDate;
	String performedDate;
	String resultStatus;
	String prelimStatus;
	String confirmStatus;
	String testStatus;
	String testCategory;
	String testGroupId;
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getTestDetailId() {
		return testDetailId;
	}
	public void setTestDetailId(String testDetailId) {
		this.testDetailId = testDetailId;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public String getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}
	public String getPerformedDate() {
		return performedDate;
	}
	public void setPerformedDate(String performedDate) {
		this.performedDate = performedDate;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getPrelimStatus() {
		return prelimStatus;
	}
	public void setPrelimStatus(String prelimStatus) {
		this.prelimStatus = prelimStatus;
	}
	public String getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getTestCategory() {
		return testCategory;
	}
	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}
	public String getTestGroupId() {
		return testGroupId;
	}
	public void setTestGroupId(String testGroupId) {
		this.testGroupId = testGroupId;
	}
}
