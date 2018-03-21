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
@Table(name = "patient_episode_attribute_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientEpisodeAttributeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_episode_attribute_details_id")
	@SequenceGenerator(name ="patient_episode_attribute_details_id", sequenceName="patient_episode_attribute_details_id", allocationSize=1)
	@Column(name="patient_episode_attribute_details_id")
	private Integer patientEpisodeAttributeDetailsId;

	@Column(name="patient_episode_attribute_details_episodeid")
	private Integer patientEpisodeAttributeDetailsEpisodeid;

	@Column(name="patient_episode_attribute_details_gwid")
	private String patientEpisodeAttributeDetailsGwid;

	@Column(name="patient_episode_attribute_details_value")
	private String patientEpisodeAttributeDetailsValue;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "patient_episode_attribute_details_episodeid", referencedColumnName = "patient_episode_id", insertable = false, updatable = false)
	PatientEpisode patientEpisode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "patient_episode_attribute_details_gwid", referencedColumnName = "clinical_elements_gwid", insertable = false, updatable = false)
	ClinicalElements clinicalElements;
	
	
	public Integer getPatientEpisodeAttributeDetailsId() {
		return patientEpisodeAttributeDetailsId;
	}

	public void setPatientEpisodeAttributeDetailsId(
			Integer patientEpisodeAttributeDetailsId) {
		this.patientEpisodeAttributeDetailsId = patientEpisodeAttributeDetailsId;
	}

	public Integer getPatientEpisodeAttributeDetailsEpisodeid() {
		return patientEpisodeAttributeDetailsEpisodeid;
	}

	public void setPatientEpisodeAttributeDetailsEpisodeid(
			Integer patientEpisodeAttributeDetailsEpisodeid) {
		this.patientEpisodeAttributeDetailsEpisodeid = patientEpisodeAttributeDetailsEpisodeid;
	}

	public String getPatientEpisodeAttributeDetailsGwid() {
		return patientEpisodeAttributeDetailsGwid;
	}

	public void setPatientEpisodeAttributeDetailsGwid(
			String patientEpisodeAttributeDetailsGwid) {
		this.patientEpisodeAttributeDetailsGwid = patientEpisodeAttributeDetailsGwid;
	}

	public String getPatientEpisodeAttributeDetailsValue() {
		return patientEpisodeAttributeDetailsValue;
	}

	public void setPatientEpisodeAttributeDetailsValue(
			String patientEpisodeAttributeDetailsValue) {
		this.patientEpisodeAttributeDetailsValue = patientEpisodeAttributeDetailsValue;
	}
	
	
}
