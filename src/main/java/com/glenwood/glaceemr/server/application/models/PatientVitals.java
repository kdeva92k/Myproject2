package com.glenwood.glaceemr.server.application.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

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

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_vitals")
public class PatientVitals {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_vitals_id_seq")
	@SequenceGenerator(name ="patient_vitals_id_seq", sequenceName="patient_vitals_id_seq", allocationSize=1)
	@Column(name="patient_vitals_id")
	private Integer patientVitalsId;

	@Column(name="patient_vitals_patientid")
	private Integer patientVitalsPatientid;

	@Column(name="patient_vitals_chartid")
	private Integer patientVitalsChartid;

	@Column(name="patient_vitals_encounterid")
	private Integer patientVitalsEncounterid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_vitals_time_of_recording")
	private Timestamp patientVitalsTimeOfRecording;

	@Column(name="patient_vitals_date_of_recording")
	@Type(type="date")
	private Date patientVitalsDateOfRecording;

	@Column(name="patient_vitals_value")
	private String patientVitalsValue;

	@Column(name="patient_vitals_gwid")
	private String patientVitalsGwid;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "patient_vitals_encounterid", referencedColumnName = "encounter_id",insertable = false, updatable = false)
	Encounter encounter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "patient_vitals_gwid", referencedColumnName = "clinical_elements_gwid",insertable = false, updatable = false)
	ClinicalElements clinicalElement;
	
	
	/*public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}*/

	
	public Integer getPatientVitalsId() {
		return patientVitalsId;
	}

	public ClinicalElements getClinicalElement() {
		return clinicalElement;
	}

	public void setClinicalElement(ClinicalElements clinicalElement) {
		this.clinicalElement = clinicalElement;
	}

	public void setPatientVitalsId(Integer patientVitalsId) {
		this.patientVitalsId = patientVitalsId;
	}

	public Integer getPatientVitalsPatientid() {
		return patientVitalsPatientid;
	}

	public void setPatientVitalsPatientid(Integer patientVitalsPatientid) {
		this.patientVitalsPatientid = patientVitalsPatientid;
	}

	public Integer getPatientVitalsChartid() {
		return patientVitalsChartid;
	}

	public void setPatientVitalsChartid(Integer patientVitalsChartid) {
		this.patientVitalsChartid = patientVitalsChartid;
	}

	public Integer getPatientVitalsEncounterid() {
		return patientVitalsEncounterid;
	}

	public void setPatientVitalsEncounterid(Integer patientVitalsEncounterid) {
		this.patientVitalsEncounterid = patientVitalsEncounterid;
	}

	public Timestamp getPatientVitalsTimeOfRecording() {
		return patientVitalsTimeOfRecording;
	}

	public void setPatientVitalsTimeOfRecording(
			Timestamp patientVitalsTimeOfRecording) {
		this.patientVitalsTimeOfRecording = patientVitalsTimeOfRecording;
	}

	public Date getPatientVitalsDateOfRecording() {
		return patientVitalsDateOfRecording;
	}

	public void setPatientVitalsDateOfRecording(Date patientVitalsDateOfRecording) {
		this.patientVitalsDateOfRecording = patientVitalsDateOfRecording;
	}

	public String getPatientVitalsValue() {
		return patientVitalsValue;
	}

	public void setPatientVitalsValue(String patientVitalsValue) {
		this.patientVitalsValue = patientVitalsValue;
	}

	public String getPatientVitalsGwid() {
		return patientVitalsGwid;
	}

	public void setPatientVitalsGwid(String patientVitalsGwid) {
		this.patientVitalsGwid = patientVitalsGwid;
	}
	
	
	
}