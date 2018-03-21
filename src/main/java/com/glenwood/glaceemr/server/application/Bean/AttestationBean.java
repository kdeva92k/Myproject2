package com.glenwood.glaceemr.server.application.Bean;

import java.sql.Timestamp;
import java.util.Date;

public class AttestationBean {

	Integer reportingYear = -1;
	Integer employeeID;
	String reportingProvider = "";
	String reportingType = "";
	String reportingMethod = "";
	String reportingStatus = "";
	Timestamp reportedDate;
	String reportingComments = "";
	
	public AttestationBean(Integer reportingYear, Integer employeeID, String reportingProvider,
			String reportingType, String reportingMethod,
			String reportingStatus, Date reportedDate,
			String reportingComments) {
		super();
		this.reportingYear = reportingYear;
		this.employeeID = employeeID;
		this.reportingProvider = reportingProvider;
		this.reportingType = reportingType;
		this.reportingMethod = reportingMethod;
		this.reportingStatus = reportingStatus;
		if(reportedDate!= null) this.reportedDate = new Timestamp(reportedDate.getTime());
		this.reportingComments = reportingComments;
	}

	public AttestationBean(){
		super();
	}
	
	public Integer getReportingYear() {
		return reportingYear;
	}
	
	public void setReportingYear(Integer reportingYear) {
		this.reportingYear = reportingYear;
	}
	
	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getReportingProvider() {
		return reportingProvider;
	}
	
	public void setReportingProvider(String reportingProvider) {
		this.reportingProvider = reportingProvider;
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
