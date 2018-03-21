package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_immunity_vaccine_mapping")
public class PatientImmunityVaccineMapping {

	@Id
	@Column(name="patient_immunity_vaccine_mapping_id")
	private Integer patientImmunityVaccineMappingId;

	@Column(name="patient_immunity_vaccine_mapping_patient_id")
	private Integer patientImmunityVaccineMappingPatientId;

	@Column(name="patient_immunity_vaccine_mapping_encounter_id")
	private Integer patientImmunityVaccineMappingEncounterId;

	@Column(name="patient_immunity_vaccine_group_code")
	private Integer patientImmunityVaccineGroupCode;

	@Column(name="patient_immunity_vaccine_mapping_disease_code")
	private String patientImmunityVaccineMappingDiseaseCode;

	@Column(name="patient_immunity_vaccine_mapping_disease_code_system_oid")
	private String patientImmunityVaccineMappingDiseaseCodeSystemOid;

	@Column(name="patient_immunity_vaccine_mapping_created_by")
	private Integer patientImmunityVaccineMappingCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_immunity_vaccine_mapping_created_on")
	private Timestamp patientImmunityVaccineMappingCreatedOn;

	@Column(name="patient_immunity_vaccine_mapping_registry_status")
	private Integer patientImmunityVaccineMappingRegistryStatus;

	@Column(name="patient_immunity_vaccine_mapping_chartid")
	private Integer patientImmunityVaccineMappingChartid;

	public Integer getPatientImmunityVaccineMappingId() {
		return patientImmunityVaccineMappingId;
	}

	public void setPatientImmunityVaccineMappingId(
			Integer patientImmunityVaccineMappingId) {
		this.patientImmunityVaccineMappingId = patientImmunityVaccineMappingId;
	}

	public Integer getPatientImmunityVaccineMappingPatientId() {
		return patientImmunityVaccineMappingPatientId;
	}

	public void setPatientImmunityVaccineMappingPatientId(
			Integer patientImmunityVaccineMappingPatientId) {
		this.patientImmunityVaccineMappingPatientId = patientImmunityVaccineMappingPatientId;
	}

	public Integer getPatientImmunityVaccineMappingEncounterId() {
		return patientImmunityVaccineMappingEncounterId;
	}

	public void setPatientImmunityVaccineMappingEncounterId(
			Integer patientImmunityVaccineMappingEncounterId) {
		this.patientImmunityVaccineMappingEncounterId = patientImmunityVaccineMappingEncounterId;
	}

	public Integer getPatientImmunityVaccineGroupCode() {
		return patientImmunityVaccineGroupCode;
	}

	public void setPatientImmunityVaccineGroupCode(
			Integer patientImmunityVaccineGroupCode) {
		this.patientImmunityVaccineGroupCode = patientImmunityVaccineGroupCode;
	}

	public String getPatientImmunityVaccineMappingDiseaseCode() {
		return patientImmunityVaccineMappingDiseaseCode;
	}

	public void setPatientImmunityVaccineMappingDiseaseCode(
			String patientImmunityVaccineMappingDiseaseCode) {
		this.patientImmunityVaccineMappingDiseaseCode = patientImmunityVaccineMappingDiseaseCode;
	}

	public String getPatientImmunityVaccineMappingDiseaseCodeSystemOid() {
		return patientImmunityVaccineMappingDiseaseCodeSystemOid;
	}

	public void setPatientImmunityVaccineMappingDiseaseCodeSystemOid(
			String patientImmunityVaccineMappingDiseaseCodeSystemOid) {
		this.patientImmunityVaccineMappingDiseaseCodeSystemOid = patientImmunityVaccineMappingDiseaseCodeSystemOid;
	}

	public Integer getPatientImmunityVaccineMappingCreatedBy() {
		return patientImmunityVaccineMappingCreatedBy;
	}

	public void setPatientImmunityVaccineMappingCreatedBy(
			Integer patientImmunityVaccineMappingCreatedBy) {
		this.patientImmunityVaccineMappingCreatedBy = patientImmunityVaccineMappingCreatedBy;
	}

	public Timestamp getPatientImmunityVaccineMappingCreatedOn() {
		return patientImmunityVaccineMappingCreatedOn;
	}

	public void setPatientImmunityVaccineMappingCreatedOn(
			Timestamp patientImmunityVaccineMappingCreatedOn) {
		this.patientImmunityVaccineMappingCreatedOn = patientImmunityVaccineMappingCreatedOn;
	}

	public Integer getPatientImmunityVaccineMappingRegistryStatus() {
		return patientImmunityVaccineMappingRegistryStatus;
	}

	public void setPatientImmunityVaccineMappingRegistryStatus(
			Integer patientImmunityVaccineMappingRegistryStatus) {
		this.patientImmunityVaccineMappingRegistryStatus = patientImmunityVaccineMappingRegistryStatus;
	}

	public Integer getPatientImmunityVaccineMappingChartid() {
		return patientImmunityVaccineMappingChartid;
	}

	public void setPatientImmunityVaccineMappingChartid(
			Integer patientImmunityVaccineMappingChartid) {
		this.patientImmunityVaccineMappingChartid = patientImmunityVaccineMappingChartid;
	}
}