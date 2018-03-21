package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "modified_dose")
public class ModifiedDose implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="modified_dose_id")
	private Integer modifiedDoseId;

	@Column(name="modified_dose_parent_root_id")
	private Integer modifiedDoseParentRootId;

	@Column(name="modified_dose_internal_source")
	private String modifiedDoseInternalSource;

	@Column(name="modified_dose_patient_id")
	private Integer modifiedDosePatientId;

	@Column(name="modified_dose_parent_rx_name")
	private String modifiedDoseParentRxName;

	@Column(name="modified_dose_parent_dose")
	private String modifiedDoseParentDose;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="modified_dose_on")
	private Timestamp modifiedDoseOn;

	@Column(name="modified_dose_by")
	private Integer modifiedDoseBy;

	public Integer getModifiedDoseId() {
		return modifiedDoseId;
	}

	public void setModifiedDoseId(Integer modifiedDoseId) {
		this.modifiedDoseId = modifiedDoseId;
	}

	public Integer getModifiedDoseParentRootId() {
		return modifiedDoseParentRootId;
	}

	public void setModifiedDoseParentRootId(Integer modifiedDoseParentRootId) {
		this.modifiedDoseParentRootId = modifiedDoseParentRootId;
	}

	public String getModifiedDoseInternalSource() {
		return modifiedDoseInternalSource;
	}

	public void setModifiedDoseInternalSource(String modifiedDoseInternalSource) {
		this.modifiedDoseInternalSource = modifiedDoseInternalSource;
	}

	public Integer getModifiedDosePatientId() {
		return modifiedDosePatientId;
	}

	public void setModifiedDosePatientId(Integer modifiedDosePatientId) {
		this.modifiedDosePatientId = modifiedDosePatientId;
	}

	public String getModifiedDoseParentRxName() {
		return modifiedDoseParentRxName;
	}

	public void setModifiedDoseParentRxName(String modifiedDoseParentRxName) {
		this.modifiedDoseParentRxName = modifiedDoseParentRxName;
	}

	public String getModifiedDoseParentDose() {
		return modifiedDoseParentDose;
	}

	public void setModifiedDoseParentDose(String modifiedDoseParentDose) {
		this.modifiedDoseParentDose = modifiedDoseParentDose;
	}

	public Timestamp getModifiedDoseOn() {
		return modifiedDoseOn;
	}

	public void setModifiedDoseOn(Timestamp modifiedDoseOn) {
		this.modifiedDoseOn = modifiedDoseOn;
	}

	public Integer getModifiedDoseBy() {
		return modifiedDoseBy;
	}

	public void setModifiedDoseBy(Integer modifiedDoseBy) {
		this.modifiedDoseBy = modifiedDoseBy;
	}
}