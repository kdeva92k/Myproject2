package com.glenwood.glaceemr.server.application.models;

public class PortalPasswordResetBean {

	Integer patientId;
	
	String password;
	
	String practiceId;
	
	String token;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(String practiceId) {
		this.practiceId = practiceId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
