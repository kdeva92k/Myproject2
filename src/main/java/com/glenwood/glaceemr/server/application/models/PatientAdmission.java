package com.glenwood.glaceemr.server.application.models;

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

@Entity
@Table(name = "patient_admission")
public class PatientAdmission {

	@SequenceGenerator(name ="patient_admission_patient_admission_id_seq", sequenceName="patient_admission_patient_admission_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_admission_patient_admission_id_seq")
	
	@Id
	@Column(name="patient_admission_id")
	private Integer patientAdmissionId;

	@Column(name="patient_admission_admission_id")
	private Integer patientAdmissionAdmissionId;

	@Column(name="patient_admission_dx_code")
	private String patientAdmissionDxCode;

	@Column(name="patient_admission_dx_description")
	private String patientAdmissionDxDescription;

	@Column(name="patient_admission_dx_coding_system")
	private String patientAdmissionDxCodingSystem;

	@Column(name="patient_admission_dx_type")
	private Integer patientAdmissionDxType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_admission_admission_id", referencedColumnName="admission_id", insertable=false, updatable=false)
	@JsonBackReference
	Admission admission;

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Integer getPatientAdmissionId() {
		return patientAdmissionId;
	}

	public void setPatientAdmissionId(Integer patientAdmissionId) {
		this.patientAdmissionId = patientAdmissionId;
	}

	public Integer getPatientAdmissionAdmissionId() {
		return patientAdmissionAdmissionId;
	}

	public void setPatientAdmissionAdmissionId(Integer patientAdmissionAdmissionId) {
		this.patientAdmissionAdmissionId = patientAdmissionAdmissionId;
	}

	public String getPatientAdmissionDxCode() {
		return patientAdmissionDxCode;
	}

	public void setPatientAdmissionDxCode(String patientAdmissionDxCode) {
		this.patientAdmissionDxCode = patientAdmissionDxCode;
	}

	public String getPatientAdmissionDxDescription() {
		return patientAdmissionDxDescription;
	}

	public void setPatientAdmissionDxDescription(
			String patientAdmissionDxDescription) {
		this.patientAdmissionDxDescription = patientAdmissionDxDescription;
	}

	public String getPatientAdmissionDxCodingSystem() {
		return patientAdmissionDxCodingSystem;
	}

	public void setPatientAdmissionDxCodingSystem(
			String patientAdmissionDxCodingSystem) {
		this.patientAdmissionDxCodingSystem = patientAdmissionDxCodingSystem;
	}

	public Integer getPatientAdmissionDxType() {
		return patientAdmissionDxType;
	}

	public void setPatientAdmissionDxType(Integer patientAdmissionDxType) {
		this.patientAdmissionDxType = patientAdmissionDxType;
	}
	
}