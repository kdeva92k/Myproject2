package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PatientFeedbackSaveBean {
	
	
	private Integer patientId;

	private String dateOfFeedback;

	private String fillingPersonFname;

	private String fillingPersonLname;

	private String fillingPersonEmail;

	private String fillingPersonRelationship;

	private Integer patientProvider;

	private String patientName;
	
	private List<PatientFeedbackAnswersSaveBean> feedbackAnswersList;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getDateOfFeedback() {
		return dateOfFeedback;
	}

	public void setDateOfFeedback(String dateOfFeedback) {
		this.dateOfFeedback = dateOfFeedback;
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

	public String getFillingPersonEmail() {
		return fillingPersonEmail;
	}

	public void setFillingPersonEmail(String fillingPersonEmail) {
		this.fillingPersonEmail = fillingPersonEmail;
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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public List<PatientFeedbackAnswersSaveBean> getFeedbackAnswersList() {
		return feedbackAnswersList;
	}

	public void setFeedbackAnswersList(
			List<PatientFeedbackAnswersSaveBean> feedbackAnswersList) {
		this.feedbackAnswersList = feedbackAnswersList;
	}

	
}
