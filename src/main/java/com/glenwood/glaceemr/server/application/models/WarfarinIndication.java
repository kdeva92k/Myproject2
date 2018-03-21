package com.glenwood.glaceemr.server.application.models;


import java.sql.Timestamp;


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
@Table(name = "warfarin_indication")
public class WarfarinIndication {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="warfarin_indication_warfarin_indication_id_seq")
	@SequenceGenerator(name ="warfarin_indication_warfarin_indication_id_seq", sequenceName="warfarin_indication_warfarin_indication_id_seq", allocationSize=1)
	@Id
	@Column(name="warfarin_indication_id")
	private Integer warfarinIndicationId;//

	@Column(name="warfarin_indication_episode_id")
	private Integer warfarinIndicationEpisodeId;

	@Column(name="warfarin_indication_code")
	private String warfarinIndicationCode;

	@Column(name="warfarin_indication_code_system")
	private Integer warfarinIndicationCodeSystem;

	@Column(name="warfarin_indication_status")
	private Integer warfarinIndicationStatus;

	@Column(name="warfarin_indication_created_by")
	private Integer warfarinIndicationCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="warfarin_indication_created_on")
	private Timestamp warfarinIndicationCreatedOn;

	@Column(name="warfarin_indication_modified_by")
	private Integer warfarinIndicationModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="warfarin_indication_modified_on")
	private Timestamp warfarinIndicationModifiedOn;

	@Column(name="warfarin_indication_code_description")
	private String warfarinIndicationCodeDescription;

	public Integer getWarfarinIndicationId() {
		return warfarinIndicationId;
	}

	public void setWarfarinIndicationId(Integer warfarinIndicationId) {
		this.warfarinIndicationId = warfarinIndicationId;
	}
	public Integer getWarfarinIndicationEpisodeId() {
		return warfarinIndicationEpisodeId;
	}

	public void setWarfarinIndicationEpisodeId(Integer warfarinIndicationEpisodeId) {
		this.warfarinIndicationEpisodeId = warfarinIndicationEpisodeId;
	}

	public String getWarfarinIndicationCode() {
		return warfarinIndicationCode;
	}

	public void setWarfarinIndicationCode(String warfarinIndicationCode) {
		this.warfarinIndicationCode = warfarinIndicationCode;
	}

	public Integer getWarfarinIndicationCodeSystem() {
		return warfarinIndicationCodeSystem;
	}

	public void setWarfarinIndicationCodeSystem(Integer warfarinIndicationCodeSystem) {
		this.warfarinIndicationCodeSystem = warfarinIndicationCodeSystem;
	}

	public Integer getWarfarinIndicationStatus() {
		return warfarinIndicationStatus;
	}

	public void setWarfarinIndicationStatus(Integer warfarinIndicationStatus) {
		this.warfarinIndicationStatus = warfarinIndicationStatus;
	}

	public Integer getWarfarinIndicationCreatedBy() {
		return warfarinIndicationCreatedBy;
	}

	public void setWarfarinIndicationCreatedBy(Integer warfarinIndicationCreatedBy) {
		this.warfarinIndicationCreatedBy = warfarinIndicationCreatedBy;
	}

	public Timestamp getWarfarinIndicationCreatedOn() {
		return warfarinIndicationCreatedOn;
	}

	public void setWarfarinIndicationCreatedOn(Timestamp warfarinIndicationCreatedOn) {
		this.warfarinIndicationCreatedOn = warfarinIndicationCreatedOn;
	}

	public Integer getWarfarinIndicationModifiedBy() {
		return warfarinIndicationModifiedBy;
	}

	public void setWarfarinIndicationModifiedBy(Integer warfarinIndicationModifiedBy) {
		this.warfarinIndicationModifiedBy = warfarinIndicationModifiedBy;
	}

	public Timestamp getWarfarinIndicationModifiedOn() {
		return warfarinIndicationModifiedOn;
	}

	public void setWarfarinIndicationModifiedOn(
			Timestamp warfarinIndicationModifiedOn) {
		this.warfarinIndicationModifiedOn = warfarinIndicationModifiedOn;
	}

	public String getWarfarinIndicationCodeDescription() {
		return warfarinIndicationCodeDescription;
	}

	public void setWarfarinIndicationCodeDescription(
			String warfarinIndicationCodeDescription) {
		this.warfarinIndicationCodeDescription = warfarinIndicationCodeDescription;
	}
	
	
	
}
