package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PatientSurveySaveBean {

	int patientId;
	
	int chartId;
	
	int providerId;
	
	String fillingPersonFirstName;
	
	String fillingPersonLastName;
	
	String fillingPersonRelationship;
	
	String patientName;
	
	String providerName;
	
	int patientAge;
	
	String patientGender;
	
	List<PatientSurveyAnswersSaveBean> surveyAnswers;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getChartId() {
		return chartId;
	}

	public void setChartId(int chartId) {
		this.chartId = chartId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getFillingPersonFirstName() {
		return fillingPersonFirstName;
	}

	public void setFillingPersonFirstName(String fillingPersonFirstName) {
		this.fillingPersonFirstName = fillingPersonFirstName;
	}

	public String getFillingPersonLastName() {
		return fillingPersonLastName;
	}

	public void setFillingPersonLastName(String fillingPersonLastName) {
		this.fillingPersonLastName = fillingPersonLastName;
	}

	public String getFillingPersonRelationship() {
		return fillingPersonRelationship;
	}

	public void setFillingPersonRelationship(String fillingPersonRelationship) {
		this.fillingPersonRelationship = fillingPersonRelationship;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public List<PatientSurveyAnswersSaveBean> getSurveyAnswers() {
		return surveyAnswers;
	}

	public void setSurveyAnswers(List<PatientSurveyAnswersSaveBean> surveyAnswers) {
		this.surveyAnswers = surveyAnswers;
	}

	
	
}
