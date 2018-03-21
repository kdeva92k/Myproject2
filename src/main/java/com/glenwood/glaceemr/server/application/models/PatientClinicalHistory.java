package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "patient_clinical_history")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientClinicalHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_clinical_history_patient_clinical_history_id_seq")
	@SequenceGenerator(name ="patient_clinical_history_patient_clinical_history_id_seq", sequenceName="patient_clinical_history_patient_clinical_history_id_seq", allocationSize=1)
	@Column(name="patient_clinical_history_id")
	private Integer patientClinicalHistoryId;

	@Column(name="patient_clinical_history_patientid")
	private Integer patientClinicalHistoryPatientid;

	@Column(name="patient_clinical_history_chartid")
	private Integer patientClinicalHistoryChartid;

	@Column(name="patient_clinical_history_encounterid")
	private Integer patientClinicalHistoryEncounterid;

	@Column(name="patient_clinical_history_gwid")
	private String patientClinicalHistoryGwid;

	@Column(name="patient_clinical_history_value")
	private String patientClinicalHistoryValue;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="patient_clinical_history_gwid",referencedColumnName="clinical_elements_gwid",insertable = false, updatable = false)
	private ClinicalElements clinicalElement;

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="patient_clinical_history_gwid",referencedColumnName="clinical_text_mapping_textbox_gwid",insertable = false, updatable = false)
	private ClinicalTextMapping clinicalTextMapping;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="patient_clinical_history_encounterid",referencedColumnName="encounter_id",insertable = false, updatable = false)
	private Encounter encounter;
	
	public ClinicalElements getClinicalElement() {
		return clinicalElement;
	}

	public void setClinicalElement(ClinicalElements clinicalElement) {
		this.clinicalElement = clinicalElement;
	}

	public Integer getPatientClinicalHistoryId() {
		return patientClinicalHistoryId;
	}

	public void setPatientClinicalHistoryId(Integer patientClinicalHistoryId) {
		this.patientClinicalHistoryId = patientClinicalHistoryId;
	}

	public Integer getPatientClinicalHistoryPatientid() {
		return patientClinicalHistoryPatientid;
	}

	public void setPatientClinicalHistoryPatientid(
			Integer patientClinicalHistoryPatientid) {
		this.patientClinicalHistoryPatientid = patientClinicalHistoryPatientid;
	}

	public Integer getPatientClinicalHistoryChartid() {
		return patientClinicalHistoryChartid;
	}

	public void setPatientClinicalHistoryChartid(
			Integer patientClinicalHistoryChartid) {
		this.patientClinicalHistoryChartid = patientClinicalHistoryChartid;
	}

	public Integer getPatientClinicalHistoryEncounterid() {
		return patientClinicalHistoryEncounterid;
	}

	public void setPatientClinicalHistoryEncounterid(
			Integer patientClinicalHistoryEncounterid) {
		this.patientClinicalHistoryEncounterid = patientClinicalHistoryEncounterid;
	}

	public String getPatientClinicalHistoryGwid() {
		return patientClinicalHistoryGwid;
	}

	public void setPatientClinicalHistoryGwid(String patientClinicalHistoryGwid) {
		this.patientClinicalHistoryGwid = patientClinicalHistoryGwid;
	}

	public String getPatientClinicalHistoryValue() {
		return patientClinicalHistoryValue;
	}

	public void setPatientClinicalHistoryValue(String patientClinicalHistoryValue) {
		this.patientClinicalHistoryValue = patientClinicalHistoryValue;
	}
	
}