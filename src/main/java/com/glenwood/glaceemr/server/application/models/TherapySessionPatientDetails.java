package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "therapy_session_patient_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TherapySessionPatientDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="therapy_session_patient_details_therapy_session_patient_details")
	@SequenceGenerator(name ="therapy_session_patient_details_therapy_session_patient_details", sequenceName="therapy_session_patient_details_therapy_session_patient_details_id_seq", allocationSize=1)
	@Column(name="therapy_session_patient_details_id")
	private Integer therapySessionPatientDetailsId;

	@Column(name="therapy_session_patient_details_session_id")
	private Integer therapySessionPatientDetailsSessionId;

	@Column(name="therapy_session_patient_details_patient_id")
	private Integer therapySessionPatientDetailsPatientId;

	@Column(name="therapy_session_patient_details_gwid")
	private String therapySessionPatientDetailsGwid;

	@Column(name="therapy_session_patient_details_value")
	private String therapySessionPatientDetailsValue;

	@Column(name="therapy_session_patient_details_entered_by")
	private String therapySessionPatientDetailsEnteredBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="therapy_session_patient_details_entered_on")
	private Timestamp therapySessionPatientDetailsEnteredOn;

	@Column(name="therapy_session_patient_details_modified_by")
	private String therapySessionPatientDetailsModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="therapy_session_patient_details_modified_on")
	private Timestamp therapySessionPatientDetailsModifiedOn;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="therapy_session_patient_details_gwid",referencedColumnName="clinical_elements_gwid",insertable=false,updatable=false)
    ClinicalElements clinicalElements;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="therapy_session_patient_details_session_id",referencedColumnName="therapy_session_details_session_id",insertable=false, updatable=false)
	@JsonBackReference
	TherapySessionDetails therapySessionPatientDetails ;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="therapy_session_patient_details_patient_id",referencedColumnName="therapy_session_details_patient_id",insertable=false, updatable=false)
	@JsonBackReference
	TherapySessionDetails therapySessionDetailsPatientId;
	
	
	public TherapySessionDetails getTherapySessionDetailsPatientId() {
		return therapySessionDetailsPatientId;
	}

	public void setTherapySessionDetailsPatientId(
			TherapySessionDetails therapySessionDetailsPatientId) {
		this.therapySessionDetailsPatientId = therapySessionDetailsPatientId;
	}

	public TherapySessionDetails getTherapySessionPatientDetails() {
		return therapySessionPatientDetails;
	}

	public void setTherapySessionPatientDetails(
			TherapySessionDetails therapySessionPatientDetails) {
		this.therapySessionPatientDetails = therapySessionPatientDetails;
	}

	public Integer getTherapySessionPatientDetailsId() {
		return therapySessionPatientDetailsId;
	}

	public void setTherapySessionPatientDetailsId(
			Integer therapySessionPatientDetailsId) {
		this.therapySessionPatientDetailsId = therapySessionPatientDetailsId;
	}

	public Integer getTherapySessionPatientDetailsSessionId() {
		return therapySessionPatientDetailsSessionId;
	}

	public void setTherapySessionPatientDetailsSessionId(
			Integer therapySessionPatientDetailsSessionId) {
		this.therapySessionPatientDetailsSessionId = therapySessionPatientDetailsSessionId;
	}

	public Integer getTherapySessionPatientDetailsPatientId() {
		return therapySessionPatientDetailsPatientId;
	}

	public void setTherapySessionPatientDetailsPatientId(
			Integer therapySessionPatientDetailsPatientId) {
		this.therapySessionPatientDetailsPatientId = therapySessionPatientDetailsPatientId;
	}

	public String getTherapySessionPatientDetailsGwid() {
		return therapySessionPatientDetailsGwid;
	}

	public void setTherapySessionPatientDetailsGwid(
			String therapySessionPatientDetailsGwid) {
		this.therapySessionPatientDetailsGwid = therapySessionPatientDetailsGwid;
	}

	public String getTherapySessionPatientDetailsValue() {
		return therapySessionPatientDetailsValue;
	}

	public void setTherapySessionPatientDetailsValue(
			String therapySessionPatientDetailsValue) {
		this.therapySessionPatientDetailsValue = therapySessionPatientDetailsValue;
	}

	public String getTherapySessionPatientDetailsEnteredBy() {
		return therapySessionPatientDetailsEnteredBy;
	}

	public void setTherapySessionPatientDetailsEnteredBy(
			String therapySessionPatientDetailsEnteredBy) {
		this.therapySessionPatientDetailsEnteredBy = therapySessionPatientDetailsEnteredBy;
	}

	public Timestamp getTherapySessionPatientDetailsEnteredOn() {
		return therapySessionPatientDetailsEnteredOn;
	}

	public void setTherapySessionPatientDetailsEnteredOn(
			Timestamp therapySessionPatientDetailsEnteredOn) {
		this.therapySessionPatientDetailsEnteredOn = therapySessionPatientDetailsEnteredOn;
	}

	public String getTherapySessionPatientDetailsModifiedBy() {
		return therapySessionPatientDetailsModifiedBy;
	}

	public void setTherapySessionPatientDetailsModifiedBy(
			String therapySessionPatientDetailsModifiedBy) {
		this.therapySessionPatientDetailsModifiedBy = therapySessionPatientDetailsModifiedBy;
	}

	public Timestamp getTherapySessionPatientDetailsModifiedOn() {
		return therapySessionPatientDetailsModifiedOn;
	}

	public void setTherapySessionPatientDetailsModifiedOn(
			Timestamp therapySessionPatientDetailsModifiedOn) {
		this.therapySessionPatientDetailsModifiedOn = therapySessionPatientDetailsModifiedOn;
	}
	
}