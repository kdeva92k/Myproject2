package com.glenwood.glaceemr.server.application.Bean;

import java.util.HashMap;

public class ReportingInfo {

	String provider;
	int employeeId;
	HashMap<String, AttestationInfo> reportingInfo;
	
	public ReportingInfo(){
		
	}

	public ReportingInfo(String provider, int employeeId,
			HashMap<String, AttestationInfo> reportingInfo) {
		super();
		this.provider = provider;
		this.employeeId = employeeId;
		this.reportingInfo = reportingInfo;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public HashMap<String, AttestationInfo> getReportingInfo() {
		return reportingInfo;
	}

	public void setReportingInfo(HashMap<String, AttestationInfo> reportingInfo) {
		this.reportingInfo = reportingInfo;
	}
	
}
