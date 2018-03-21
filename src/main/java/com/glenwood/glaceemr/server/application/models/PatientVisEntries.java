package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_vis_entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientVisEntries {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_vis_entries_patient_vis_entries_id_seq")
	@SequenceGenerator(name ="patient_vis_entries_patient_vis_entries_id_seq", sequenceName="patient_vis_entries_patient_vis_entries_id_seq", allocationSize=1)
	@Column(name="patient_vis_entries_id", nullable=false)
	private Integer patientVisEntriesId;

	@Column(name="patient_vis_entries_administration_id")
	private Integer patientVisEntriesAdministrationId;

	@Column(name="patient_vis_entries_cvx")
	private String patientVisEntriesCvx;

	@Column(name="patient_vis_entries_vaccine_group_code")
	private String patientVisEntriesVaccineGroupCode;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_vis_entries_publication_date")
	private Timestamp patientVisEntriesPublicationDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_vis_entries_presentation_date")
	private Timestamp patientVisEntriesPresentationDate;

	@Column(name="patient_vis_entries_is_active")
	private Boolean patientVisEntriesIsActive;

	@Column(name="patient_vis_entries_created_by")
	private Integer patientVisEntriesCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_vis_entries_created_on")
	private Timestamp patientVisEntriesCreatedOn;

	@Column(name="patient_vis_entries_modified_by")
	private Integer patientVisEntriesModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_vis_entries_modified_on")
	private Timestamp patientVisEntriesModifiedOn;

	@Column(name="patient_vis_entries_vis_id")
	private Integer patientVisEntriesVisId;

	public Integer getPatientVisEntriesId() {
		return patientVisEntriesId;
	}

	public void setPatientVisEntriesId(Integer patientVisEntriesId) {
		this.patientVisEntriesId = patientVisEntriesId;
	}

	public Integer getPatientVisEntriesAdministrationId() {
		return patientVisEntriesAdministrationId;
	}

	public void setPatientVisEntriesAdministrationId(
			Integer patientVisEntriesAdministrationId) {
		this.patientVisEntriesAdministrationId = patientVisEntriesAdministrationId;
	}

	public String getPatientVisEntriesCvx() {
		return patientVisEntriesCvx;
	}

	public void setPatientVisEntriesCvx(String patientVisEntriesCvx) {
		this.patientVisEntriesCvx = patientVisEntriesCvx;
	}

	public String getPatientVisEntriesVaccineGroupCode() {
		return patientVisEntriesVaccineGroupCode;
	}

	public void setPatientVisEntriesVaccineGroupCode(
			String patientVisEntriesVaccineGroupCode) {
		this.patientVisEntriesVaccineGroupCode = patientVisEntriesVaccineGroupCode;
	}

	public Timestamp getPatientVisEntriesPublicationDate() {
		return patientVisEntriesPublicationDate;
	}

	public void setPatientVisEntriesPublicationDate(
			Timestamp patientVisEntriesPublicationDate) {
		this.patientVisEntriesPublicationDate = patientVisEntriesPublicationDate;
	}

	public Timestamp getPatientVisEntriesPresentationDate() {
		return patientVisEntriesPresentationDate;
	}

	public void setPatientVisEntriesPresentationDate(
			Timestamp patientVisEntriesPresentationDate) {
		this.patientVisEntriesPresentationDate = patientVisEntriesPresentationDate;
	}

	public Boolean getPatientVisEntriesIsActive() {
		return patientVisEntriesIsActive;
	}

	public void setPatientVisEntriesIsActive(Boolean patientVisEntriesIsActive) {
		this.patientVisEntriesIsActive = patientVisEntriesIsActive;
	}

	public Integer getPatientVisEntriesCreatedBy() {
		return patientVisEntriesCreatedBy;
	}

	public void setPatientVisEntriesCreatedBy(Integer patientVisEntriesCreatedBy) {
		this.patientVisEntriesCreatedBy = patientVisEntriesCreatedBy;
	}

	public Timestamp getPatientVisEntriesCreatedOn() {
		return patientVisEntriesCreatedOn;
	}

	public void setPatientVisEntriesCreatedOn(Timestamp patientVisEntriesCreatedOn) {
		this.patientVisEntriesCreatedOn = patientVisEntriesCreatedOn;
	}

	public Integer getPatientVisEntriesModifiedBy() {
		return patientVisEntriesModifiedBy;
	}

	public void setPatientVisEntriesModifiedBy(Integer patientVisEntriesModifiedBy) {
		this.patientVisEntriesModifiedBy = patientVisEntriesModifiedBy;
	}

	public Timestamp getPatientVisEntriesModifiedOn() {
		return patientVisEntriesModifiedOn;
	}

	public void setPatientVisEntriesModifiedOn(Timestamp patientVisEntriesModifiedOn) {
		this.patientVisEntriesModifiedOn = patientVisEntriesModifiedOn;
	}

	public Integer getPatientVisEntriesVisId() {
		return patientVisEntriesVisId;
	}

	public void setPatientVisEntriesVisId(Integer patientVisEntriesVisId) {
		this.patientVisEntriesVisId = patientVisEntriesVisId;
	}
}