package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "med_status")
public class MedStatus {

	@Id
	@Column(name="med_status_id")
	private Integer medStatusId;

	@Column(name="med_status_is_active")
	private Boolean medStatusIsActive;

	@Column(name="med_status_name")
	private String medStatusName;

	@Column(name="med_status_desc")
	private String medStatusDesc;

	@Column(name="med_status_group")
	private String medStatusGroup;

	@Column(name="med_status_color")
	private String medStatusColor;

	public Integer getMedStatusId() {
		return medStatusId;
	}

	public void setMedStatusId(Integer medStatusId) {
		this.medStatusId = medStatusId;
	}

	public Boolean getMedStatusIsActive() {
		return medStatusIsActive;
	}

	public void setMedStatusIsActive(Boolean medStatusIsActive) {
		this.medStatusIsActive = medStatusIsActive;
	}

	public String getMedStatusName() {
		return medStatusName;
	}

	public void setMedStatusName(String medStatusName) {
		this.medStatusName = medStatusName;
	}

	public String getMedStatusDesc() {
		return medStatusDesc;
	}

	public void setMedStatusDesc(String medStatusDesc) {
		this.medStatusDesc = medStatusDesc;
	}

	public String getMedStatusGroup() {
		return medStatusGroup;
	}

	public void setMedStatusGroup(String medStatusGroup) {
		this.medStatusGroup = medStatusGroup;
	}

	public String getMedStatusColor() {
		return medStatusColor;
	}

	public void setMedStatusColor(String medStatusColor) {
		this.medStatusColor = medStatusColor;
	}
}