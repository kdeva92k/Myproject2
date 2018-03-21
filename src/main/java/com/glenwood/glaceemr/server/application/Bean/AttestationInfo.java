package com.glenwood.glaceemr.server.application.Bean;

import java.sql.Timestamp;

public class AttestationInfo {

	String reportingType = "";
	String reportingMethod = "";
	String reportingStatus = "";
	Timestamp reportedDate;
	String reportingComments = "";
	
	public AttestationInfo(){
		
	}
	
	public AttestationInfo(String reportingType, String reportingMethod,
			String reportingStatus, Timestamp reportedDate,
			String reportingComments) {
		
		super();
		this.reportingType = reportingType;
		this.reportingMethod = reportingMethod;
		this.reportingStatus = reportingStatus;
		this.reportedDate = reportedDate;
		this.reportingComments = reportingComments;
		
	}

	public String getReportingType() {
		return reportingType;
	}
	
	public void setReportingType(String reportingType) {
		this.reportingType = reportingType;
	}
	
	public String getReportingMethod() {
		return reportingMethod;
	}
	
	public void setReportingMethod(String reportingMethod) {
		this.reportingMethod = reportingMethod;
	}
	
	public String getReportingStatus() {
		return reportingStatus;
	}
	
	public void setReportingStatus(String reportingStatus) {
		this.reportingStatus = reportingStatus;
	}
	
	public Timestamp getReportedDate() {
		return reportedDate;
	}
	
	public void setReportedDate(Timestamp reportedDate) {
		this.reportedDate = reportedDate;
	}
	
	public String getReportingComments() {
		return reportingComments;
	}

	public void setReportingComments(String reportingComments) {
		this.reportingComments = reportingComments;
	}
	
}
