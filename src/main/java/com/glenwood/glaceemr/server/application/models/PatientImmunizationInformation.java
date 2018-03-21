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
@Table(name = "patient_immunization_information")
public class PatientImmunizationInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_immunization_information_id_seq")
	@SequenceGenerator(name ="patient_immunization_information_id_seq", sequenceName="patient_immunization_information_id_seq", allocationSize=1)
	@Column(name="id")
	private Integer id;

	@Column(name="patient_id")
	private Integer patientId;

	@Column(name="chart_id")
	private Integer chartId;

	@Column(name="vaccine_id")
	private Integer vaccineId;

	@Column(name="vaccine_cvx")
	private String vaccineCvx;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="administrated_on")
	private Timestamp administratedOn;

	@Column(name="reason_id")
	private Integer reasonId;

	@Column(name="notes")
	private String notes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getChartId() {
		return chartId;
	}

	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}

	public Integer getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
	}

	public String getVaccineCvx() {
		return vaccineCvx;
	}

	public void setVaccineCvx(String vaccineCvx) {
		this.vaccineCvx = vaccineCvx;
	}

	public Timestamp getAdministratedOn() {
		return administratedOn;
	}

	public void setAdministratedOn(Timestamp administratedOn) {
		this.administratedOn = administratedOn;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}