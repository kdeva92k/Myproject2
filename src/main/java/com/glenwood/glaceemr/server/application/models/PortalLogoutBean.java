package com.glenwood.glaceemr.server.application.models;

public class PortalLogoutBean {

	String logoutURL;
	
	String logoutMessage;
	
	String username;
	
	String patientId;
	
	boolean isLogoutSuccess;

	public String getLogoutURL() {
		return logoutURL;
	}

	public void setLogoutURL(String logoutURL) {
		this.logoutURL = logoutURL;
	}

	public String getLogoutMessage() {
		return logoutMessage;
	}

	public void setLogoutMessage(String logoutMessage) {
		this.logoutMessage = logoutMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public boolean isLogoutSuccess() {
		return isLogoutSuccess;
	}

	public void setLogoutSuccess(boolean isLogoutSuccess) {
		this.isLogoutSuccess = isLogoutSuccess;
	}
	
}
