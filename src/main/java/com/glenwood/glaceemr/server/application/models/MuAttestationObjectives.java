package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "mu_attestation_objectives")
public class MuAttestationObjectives {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mu_attestation_objectives_objective_id_seq")
	@SequenceGenerator(name ="mu_attestation_objectives_objective_id_seq", sequenceName="mu_attestation_objectives_objective_id_seq", allocationSize=1)
	@Column(name="objective_id")
	private Integer objectiveId;

	@Column(name="objective_measureid")
	private String objectiveMeasureid;

	@Column(name="objective_status")
	private Boolean objectiveStatus;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="objective_lastmodified_on")
	private Timestamp objectiveLastmodifiedOn;

	@Column(name="objective_comments")
	private String objectiveComments;

	@Column(name="objective_reporting_year")
	private Integer objectiveReportingYear;

	public Integer getObjectiveId() {
		return objectiveId;
	}

	public void setObjectiveId(Integer objectiveId) {
		this.objectiveId = objectiveId;
	}

	public String getObjectiveMeasureid() {
		return objectiveMeasureid;
	}

	public void setObjectiveMeasureid(String objectiveMeasureid) {
		this.objectiveMeasureid = objectiveMeasureid;
	}

	public Boolean getObjectiveStatus() {
		return objectiveStatus;
	}

	public void setObjectiveStatus(Boolean objectiveStatus) {
		this.objectiveStatus = objectiveStatus;
	}

	public Timestamp getObjectiveLastmodifiedOn() {
		return objectiveLastmodifiedOn;
	}

	public void setObjectiveLastmodifiedOn(Timestamp objectiveLastmodifiedOn) {
		this.objectiveLastmodifiedOn = objectiveLastmodifiedOn;
	}

	public String getObjectiveComments() {
		return objectiveComments;
	}

	public void setObjectiveComments(String objectiveComments) {
		this.objectiveComments = objectiveComments;
	}

	public Integer getObjectiveReportingYear() {
		return objectiveReportingYear;
	}

	public void setObjectiveReportingYear(Integer objectiveReportingYear) {
		this.objectiveReportingYear = objectiveReportingYear;
	}
	
}