package com.glenwood.glaceemr.server.application.models;

public class PortalPatientCahpsSurveyBean {

	private Integer surveyId;

	private Integer patientId;

	private String dateOfSurvey;

	private String fillingPersonFname;

	private String fillingPersonLname;

	private String patientGender;

	private Integer patientAge;

	private String fillingPersonRelationship;

	private Integer patientProvider;

	private String patientProviderName;

	private String patientName;
	
	private boolean isPatient;

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getDateOfSurvey() {
		return dateOfSurvey;
	}

	public void setDateOfSurvey(String dateOfSurvey) {
		this.dateOfSurvey = dateOfSurvey;
	}

	public String getFillingPersonFname() {
		return fillingPersonFname;
	}

	public void setFillingPersonFname(String fillingPersonFname) {
		this.fillingPersonFname = fillingPersonFname;
	}

	public String getFillingPersonLname() {
		return fillingPersonLname;
	}

	public void setFillingPersonLname(String fillingPersonLname) {
		this.fillingPersonLname = fillingPersonLname;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public Integer getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	public String getFillingPersonRelationship() {
		return fillingPersonRelationship;
	}

	public void setFillingPersonRelationship(String fillingPersonRelationship) {
		this.fillingPersonRelationship = fillingPersonRelationship;
	}

	public Integer getPatientProvider() {
		return patientProvider;
	}

	public void setPatientProvider(Integer patientProvider) {
		this.patientProvider = patientProvider;
	}

	public String getPatientProviderName() {
		return patientProviderName;
	}

	public void setPatientProviderName(String patientProviderName) {
		this.patientProviderName = patientProviderName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public boolean isPatient() {
		return isPatient;
	}

	public void setPatient(boolean isPatient) {
		this.isPatient = isPatient;
	}
	
}
