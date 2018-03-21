package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_episode")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientEpisode {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_episode_patient_episode_id_seq")
	@SequenceGenerator(name ="patient_episode_patient_episode_id_seq", sequenceName="patient_episode_patient_episode_id_seq", allocationSize=1)
	@Column(name="patient_episode_id")
	private Integer patientEpisodeId;

	@Column(name="patient_episode_patientid")
	private Integer patientEpisodePatientid;

	@Column(name="patient_episode_name")
	private String patientEpisodeName;

	@Column(name="patient_episode_type")
	private Integer patientEpisodeType;

	@Column(name="patient_episode_start_date")
	private Date patientEpisodeStartDate;

	@Column(name="patient_episode_end_date")
	private Date patientEpisodeEndDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_episode_created_date")
	private Timestamp patientEpisodeCreatedDate;

	@Column(name="patient_episode_created_by")
	private Integer patientEpisodeCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_episode_modified_date")
	private Timestamp patientEpisodeModifiedDate;

	@Column(name="patient_episode_modified_by")
	private Integer patientEpisodeModifiedBy;

	@Column(name="patient_episode_status")
	private Integer patientEpisodeStatus;

	@Column(name="patient_episode_isactive")
	private Boolean patientEpisodeIsactive;

	
	@OneToMany(mappedBy="patientEpisode",fetch=FetchType.LAZY)
	List<PatientEpisodeAttributeDetails> patientEpisodeAttributeDetails;	
	
	
	public Integer getPatientEpisodeId() {
		return patientEpisodeId;
	}

	public void setPatientEpisodeId(Integer patientEpisodeId) {
		this.patientEpisodeId = patientEpisodeId;
	}

	public Integer getPatientEpisodePatientid() {
		return patientEpisodePatientid;
	}

	public void setPatientEpisodePatientid(Integer patientEpisodePatientid) {
		this.patientEpisodePatientid = patientEpisodePatientid;
	}

	public String getPatientEpisodeName() {
		return patientEpisodeName;
	}

	public void setPatientEpisodeName(String patientEpisodeName) {
		this.patientEpisodeName = patientEpisodeName;
	}

	public Integer getPatientEpisodeType() {
		return patientEpisodeType;
	}

	public void setPatientEpisodeType(Integer patientEpisodeType) {
		this.patientEpisodeType = patientEpisodeType;
	}

	public Date getPatientEpisodeStartDate() {
		return patientEpisodeStartDate;
	}

	public void setPatientEpisodeStartDate(Date patientEpisodeStartDate) {
		this.patientEpisodeStartDate = patientEpisodeStartDate;
	}

	public Date getPatientEpisodeEndDate() {
		return patientEpisodeEndDate;
	}

	public void setPatientEpisodeEndDate(Date patientEpisodeEndDate) {
		this.patientEpisodeEndDate = patientEpisodeEndDate;
	}

	public Timestamp getPatientEpisodeCreatedDate() {
		return patientEpisodeCreatedDate;
	}

	public void setPatientEpisodeCreatedDate(Timestamp patientEpisodeCreatedDate) {
		this.patientEpisodeCreatedDate = patientEpisodeCreatedDate;
	}

	public Integer getPatientEpisodeCreatedBy() {
		return patientEpisodeCreatedBy;
	}

	public void setPatientEpisodeCreatedBy(Integer patientEpisodeCreatedBy) {
		this.patientEpisodeCreatedBy = patientEpisodeCreatedBy;
	}

	public Timestamp getPatientEpisodeModifiedDate() {
		return patientEpisodeModifiedDate;
	}

	public void setPatientEpisodeModifiedDate(Timestamp patientEpisodeModifiedDate) {
		this.patientEpisodeModifiedDate = patientEpisodeModifiedDate;
	}

	public Integer getPatientEpisodeModifiedBy() {
		return patientEpisodeModifiedBy;
	}

	public void setPatientEpisodeModifiedBy(Integer patientEpisodeModifiedBy) {
		this.patientEpisodeModifiedBy = patientEpisodeModifiedBy;
	}

	public Integer getPatientEpisodeStatus() {
		return patientEpisodeStatus;
	}

	public void setPatientEpisodeStatus(Integer patientEpisodeStatus) {
		this.patientEpisodeStatus = patientEpisodeStatus;
	}

	public Boolean getPatientEpisodeIsactive() {
		return patientEpisodeIsactive;
	}

	public void setPatientEpisodeIsactive(Boolean patientEpisodeIsactive) {
		this.patientEpisodeIsactive = patientEpisodeIsactive;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "patient_episode_type", referencedColumnName = "episode_type_list_id", insertable = false, updatable = false)
	EpisodeTypeList episodeTypeList;


	public EpisodeTypeList getEpisodeTypeList() {
		return episodeTypeList;
	}

	public void setEpisodeTypeList(EpisodeTypeList episodeTypeList) {
		this.episodeTypeList = episodeTypeList;
	}
	
}