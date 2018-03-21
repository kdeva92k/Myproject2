package com.glenwood.glaceemr.server.application.models;

public class EncryptedPatientDetails {

	String authString;
	
	String tennantDB;
	
	int patientId;

	public String getAuthString() {
		return authString;
	}

	public void setAuthString(String authString) {
		this.authString = authString;
	}

	public String getTennantDB() {
		return tennantDB;
	}

	public void setTennantDB(String tennantDB) {
		this.tennantDB = tennantDB;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
}
