package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_cahps_survey")
public class PatientCahpsSurvey {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_cahps_survey_idseq")
	@SequenceGenerator(name ="patient_cahps_survey_idseq", sequenceName="patient_cahps_survey_idseq", allocationSize=1)
	@Column(name="survey_id")
	private Integer surveyId;

	@Column(name="patient_id")
	private Integer patientId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="date_of_survey")
	private Timestamp dateOfSurvey;

	@Column(name="filling_person_fname")
	private String fillingPersonFname;

	@Column(name="filling_person_lname")
	private String fillingPersonLname;

	@Column(name="patient_gender")
	private String patientGender;

	@Column(name="patient_age")
	private Integer patientAge;

	@Column(name="filling_person_relationship")
	private String fillingPersonRelationship;

	@Column(name="patient_provider")
	private Integer patientProvider;

	@Column(name="patient_provider_name")
	private String patientProviderName;

	@Column(name="patient_name")
	private String patientName;

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

	public Timestamp getDateOfSurvey() {
		return dateOfSurvey;
	}

	public void setDateOfSurvey(Timestamp dateOfSurvey) {
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
	
}