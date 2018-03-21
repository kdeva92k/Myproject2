package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "lab_include_previous")
public class LabIncludePrevious {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lab_include_previous_lab_include_previous_id_seq")
	@SequenceGenerator(name ="lab_include_previous_lab_include_previous_id_seq", sequenceName="lab_include_previous_lab_include_previous_id_seq", allocationSize=1)
	@Column(name="lab_include_previous_id")
	private Integer labIncludePreviousId;

	@Column(name="lab_include_previous_labid")
	private Integer labIncludePreviousLabid;

	@Column(name="lab_include_previous_encounterid")
	private Integer labIncludePreviousEncounterid;

	public Integer getLabIncludePreviousId() {
		return labIncludePreviousId;
	}

	public void setLabIncludePreviousId(Integer labIncludePreviousId) {
		this.labIncludePreviousId = labIncludePreviousId;
	}

	public Integer getLabIncludePreviousLabid() {
		return labIncludePreviousLabid;
	}

	public void setLabIncludePreviousLabid(Integer labIncludePreviousLabid) {
		this.labIncludePreviousLabid = labIncludePreviousLabid;
	}

	public Integer getLabIncludePreviousEncounterid() {
		return labIncludePreviousEncounterid;
	}

	public void setLabIncludePreviousEncounterid(
			Integer labIncludePreviousEncounterid) {
		this.labIncludePreviousEncounterid = labIncludePreviousEncounterid;
	}
}