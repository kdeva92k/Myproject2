package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
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
@Table(name = "patient_clinical_elements_questions")
public class PatientClinicalElementsQuestions {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_clinical_elements_que_patient_clinical_elements_que_seq")
	@SequenceGenerator(name ="patient_clinical_elements_que_patient_clinical_elements_que_seq", sequenceName="patient_clinical_elements_que_patient_clinical_elements_que_seq", allocationSize=1)
	@Column(name="patient_clinical_elements_questions_id")
	private Integer patientClinicalElementsQuestionsId;

	@Column(name="patient_clinical_elements_questions_patientid")
	private Integer patientClinicalElementsQuestionsPatientid;

	@Column(name="patient_clinical_elements_questions_groupid")
	private Integer patientClinicalElementsQuestionsGroupid;

	@Column(name="patient_clinical_elements_questions_gwid")
	private String patientClinicalElementsQuestionsGwid;

	@Column(name="patient_clinical_elements_questions_data")
	private String patientClinicalElementsQuestionsData;

	@Column(name="patient_clinical_elements_questions_status")
	private Integer patientClinicalElementsQuestionsStatus;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_clinical_elements_questions_modified_on")
	private Timestamp patientClinicalElementsQuestionsModifiedOn;

	@Column(name="patient_clinical_elements_questions_isactive")
	private Boolean patientClinicalElementsQuestionsIsactive;

	@Column(name="patient_clinical_elements_questions_isxml")
	private Integer patientClinicalElementsQuestionsIsxml;

	@Column(name="patient_clinical_elements_questions_tabid")
	private Integer patientClinicalElementsQuestionsTabid;
	

	public Integer getPatientClinicalElementsQuestionsId() {
		return patientClinicalElementsQuestionsId;
	}

	public void setPatientClinicalElementsQuestionsId(
			Integer patientClinicalElementsQuestionsId) {
		this.patientClinicalElementsQuestionsId = patientClinicalElementsQuestionsId;
	}

	public Integer getPatientClinicalElementsQuestionsPatientid() {
		return patientClinicalElementsQuestionsPatientid;
	}

	public void setPatientClinicalElementsQuestionsPatientid(
			Integer patientClinicalElementsQuestionsPatientid) {
		this.patientClinicalElementsQuestionsPatientid = patientClinicalElementsQuestionsPatientid;
	}

	public Integer getPatientClinicalElementsQuestionsGroupid() {
		return patientClinicalElementsQuestionsGroupid;
	}

	public void setPatientClinicalElementsQuestionsGroupid(
			Integer patientClinicalElementsQuestionsGroupid) {
		this.patientClinicalElementsQuestionsGroupid = patientClinicalElementsQuestionsGroupid;
	}

	public String getPatientClinicalElementsQuestionsGwid() {
		return patientClinicalElementsQuestionsGwid;
	}

	public void setPatientClinicalElementsQuestionsGwid(
			String patientClinicalElementsQuestionsGwid) {
		this.patientClinicalElementsQuestionsGwid = patientClinicalElementsQuestionsGwid;
	}

	public String getPatientClinicalElementsQuestionsData() {
		return patientClinicalElementsQuestionsData;
	}

	public void setPatientClinicalElementsQuestionsData(
			String patientClinicalElementsQuestionsData) {
		this.patientClinicalElementsQuestionsData = patientClinicalElementsQuestionsData;
	}

	public Integer getPatientClinicalElementsQuestionsStatus() {
		return patientClinicalElementsQuestionsStatus;
	}

	public void setPatientClinicalElementsQuestionsStatus(
			Integer patientClinicalElementsQuestionsStatus) {
		this.patientClinicalElementsQuestionsStatus = patientClinicalElementsQuestionsStatus;
	}

	public Timestamp getPatientClinicalElementsQuestionsModifiedOn() {
		return patientClinicalElementsQuestionsModifiedOn;
	}

	public void setPatientClinicalElementsQuestionsModifiedOn(
			Timestamp patientClinicalElementsQuestionsModifiedOn) {
		this.patientClinicalElementsQuestionsModifiedOn = patientClinicalElementsQuestionsModifiedOn;
	}

	public Boolean getPatientClinicalElementsQuestionsIsactive() {
		return patientClinicalElementsQuestionsIsactive;
	}

	public void setPatientClinicalElementsQuestionsIsactive(
			Boolean patientClinicalElementsQuestionsIsactive) {
		this.patientClinicalElementsQuestionsIsactive = patientClinicalElementsQuestionsIsactive;
	}

	public Integer getPatientClinicalElementsQuestionsIsxml() {
		return patientClinicalElementsQuestionsIsxml;
	}

	public void setPatientClinicalElementsQuestionsIsxml(
			Integer patientClinicalElementsQuestionsIsxml) {
		this.patientClinicalElementsQuestionsIsxml = patientClinicalElementsQuestionsIsxml;
	}

	public Integer getPatientClinicalElementsQuestionsTabid() {
		return patientClinicalElementsQuestionsTabid;
	}

	public void setPatientClinicalElementsQuestionsTabid(
			Integer patientClinicalElementsQuestionsTabid) {
		this.patientClinicalElementsQuestionsTabid = patientClinicalElementsQuestionsTabid;
	}
	
}