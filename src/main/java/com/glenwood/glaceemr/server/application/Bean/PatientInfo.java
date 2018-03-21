package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class PatientInfo {

	private int patientId;
	private String firstName = new String();
	private String lastName = new String();
	private Date dob = new Date();
	private String gender = new String();
/*	private String race = new String();
	private String ethnicity = new String();
*/	
	public int getPatientId() {
		return patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/*public String getRace() {
		return race;
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public String getEthnicity() {
		return ethnicity;
	}
	
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}*/
	
}