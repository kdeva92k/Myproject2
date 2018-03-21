package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_shared_document_log")
public class PatientSharedDocumentLog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_shared_document_log_patient_shared_document_log_id_seq")
	@SequenceGenerator(name ="patient_shared_document_log_patient_shared_document_log_id_seq", sequenceName="patient_shared_document_log_patient_shared_document_log_id_seq", allocationSize=1)
	@Column(name="patient_shared_document_log_id")
	private Integer patientSharedDocumentLogId;

	@Column(name="patient_shared_document_log_patient_id")
	private Integer patientSharedDocumentLogPatientId;

	@Column(name="patient_shared_document_log_encounter_id")
	private Integer patientSharedDocumentLogEncounterId;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_shared_document_log_shared_on")
	private Timestamp patientSharedDocumentLogSharedOn;

	@Column(name="patient_shared_document_log_shared_by")
	private Integer patientSharedDocumentLogSharedBy;

	@Column(name="patient_shared_document_log_shared_mode")
	private Integer patientSharedDocumentLogSharedMode;

	@Column(name="patient_shared_document_log_document_type")
	private Integer patientSharedDocumentLogDocumentType;

	@Column(name="patient_shared_document_log_document_format")
	private Integer patientSharedDocumentLogDocumentFormat;

	@Column(name="patient_shared_document_log_filename")
	private String patientSharedDocumentLogFilename;

	@Column(name="patient_shared_document_log_email_address")
	private String patientSharedDocumentLogEmailAddress;

	@Column(name="patient_shared_document_log_status")
	private Boolean patientSharedDocumentLogStatus;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="patient_shared_document_log_encounter_id", referencedColumnName="encounter_id", insertable=false,updatable=false)
	@JsonManagedReference
	Encounter docsLogEncounterTable;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="patient_shared_document_log_shared_by", referencedColumnName="emp_profile_empid", insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile docsLogSharedBy;


	public Integer getPatientSharedDocumentLogId() {
		return patientSharedDocumentLogId;
	}

	public Integer getPatientSharedDocumentLogPatientId() {
		return patientSharedDocumentLogPatientId;
	}

	public Integer getPatientSharedDocumentLogEncounterId() {
		return patientSharedDocumentLogEncounterId;
	}

	public Timestamp getPatientSharedDocumentLogSharedOn() {
		return patientSharedDocumentLogSharedOn;
	}

	public Integer getPatientSharedDocumentLogSharedBy() {
		return patientSharedDocumentLogSharedBy;
	}

	public Integer getPatientSharedDocumentLogSharedMode() {
		return patientSharedDocumentLogSharedMode;
	}

	public Integer getPatientSharedDocumentLogDocumentType() {
		return patientSharedDocumentLogDocumentType;
	}

	public Integer getPatientSharedDocumentLogDocumentFormat() {
		return patientSharedDocumentLogDocumentFormat;
	}

	public String getPatientSharedDocumentLogFilename() {
		return patientSharedDocumentLogFilename;
	}

	public String getPatientSharedDocumentLogEmailAddress() {
		return patientSharedDocumentLogEmailAddress;
	}

	public Boolean getPatientSharedDocumentLogStatus() {
		return patientSharedDocumentLogStatus;
	}

	public void setPatientSharedDocumentLogId(Integer patientSharedDocumentLogId) {
		this.patientSharedDocumentLogId = patientSharedDocumentLogId;
	}

	public void setPatientSharedDocumentLogPatientId(
			Integer patientSharedDocumentLogPatientId) {
		this.patientSharedDocumentLogPatientId = patientSharedDocumentLogPatientId;
	}

	public void setPatientSharedDocumentLogEncounterId(
			Integer patientSharedDocumentLogEncounterId) {
		this.patientSharedDocumentLogEncounterId = patientSharedDocumentLogEncounterId;
	}

	public void setPatientSharedDocumentLogSharedOn(
			Timestamp patientSharedDocumentLogSharedOn) {
		this.patientSharedDocumentLogSharedOn = patientSharedDocumentLogSharedOn;
	}

	public void setPatientSharedDocumentLogSharedBy(
			Integer patientSharedDocumentLogSharedBy) {
		this.patientSharedDocumentLogSharedBy = patientSharedDocumentLogSharedBy;
	}

	public void setPatientSharedDocumentLogSharedMode(
			Integer patientSharedDocumentLogSharedMode) {
		this.patientSharedDocumentLogSharedMode = patientSharedDocumentLogSharedMode;
	}

	public void setPatientSharedDocumentLogDocumentType(
			Integer patientSharedDocumentLogDocumentType) {
		this.patientSharedDocumentLogDocumentType = patientSharedDocumentLogDocumentType;
	}

	public void setPatientSharedDocumentLogDocumentFormat(
			Integer patientSharedDocumentLogDocumentFormat) {
		this.patientSharedDocumentLogDocumentFormat = patientSharedDocumentLogDocumentFormat;
	}

	public void setPatientSharedDocumentLogFilename(
			String patientSharedDocumentLogFilename) {
		this.patientSharedDocumentLogFilename = patientSharedDocumentLogFilename;
	}

	public void setPatientSharedDocumentLogEmailAddress(
			String patientSharedDocumentLogEmailAddress) {
		this.patientSharedDocumentLogEmailAddress = patientSharedDocumentLogEmailAddress;
	}

	public void setPatientSharedDocumentLogStatus(
			Boolean patientSharedDocumentLogStatus) {
		this.patientSharedDocumentLogStatus = patientSharedDocumentLogStatus;
	}

	public Encounter getDocsLogEncounterTable() {
		return docsLogEncounterTable;
	}

	public void setDocsLogEncounterTable(Encounter docsLogEncounterTable) {
		this.docsLogEncounterTable = docsLogEncounterTable;
	}

	public EmployeeProfile getDocsLogSharedBy() {
		return docsLogSharedBy;
	}

	public void setDocsLogSharedBy(EmployeeProfile docsLogSharedBy) {
		this.docsLogSharedBy = docsLogSharedBy;
	}
	
}