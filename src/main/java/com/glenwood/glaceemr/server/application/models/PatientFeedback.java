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
@Table(name = "patient_feedback")
public class PatientFeedback {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_feedback_idseq")
	@SequenceGenerator(name ="patient_feedback_idseq", sequenceName="patient_feedback_idseq", allocationSize=1)
	@Column(name="feedback_id")
	private Integer feedbackId;

	@Column(name="patient_id")
	private Integer patientId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="date_of_feedback")
	private Timestamp dateOfFeedback;

	@Column(name="filling_person_fname")
	private String fillingPersonFname;

	@Column(name="filling_person_lname")
	private String fillingPersonLname;

	@Column(name="filling_person_email")
	private String fillingPersonEmail;

	@Column(name="filling_person_relationship")
	private String fillingPersonRelationship;

	@Column(name="patient_provider")
	private Integer patientProvider;

	@Column(name="patient_name")
	private String patientName;

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Timestamp getDateOfFeedback() {
		return dateOfFeedback;
	}

	public void setDateOfFeedback(Timestamp dateOfFeedback) {
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
	
}