package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lab_alertforwardstatus")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabAlertforwardstatus {

	@Id
	@Column(name="lab_alertforwardstatus_id")
	private Integer labAlertforwardstatusId;

	@Column(name="lab_alertforwardstatus_labstatus")
	private Integer labAlertforwardstatusLabstatus;

	@Column(name="lab_alertforwardstatus_todefault")
	private Boolean labAlertforwardstatusTodefault;

	public Integer getLabAlertforwardstatusId() {
		return labAlertforwardstatusId;
	}

	public void setLabAlertforwardstatusId(Integer labAlertforwardstatusId) {
		this.labAlertforwardstatusId = labAlertforwardstatusId;
	}

	public Integer getLabAlertforwardstatusLabstatus() {
		return labAlertforwardstatusLabstatus;
	}

	public void setLabAlertforwardstatusLabstatus(
			Integer labAlertforwardstatusLabstatus) {
		this.labAlertforwardstatusLabstatus = labAlertforwardstatusLabstatus;
	}

	public Boolean getLabAlertforwardstatusTodefault() {
		return labAlertforwardstatusTodefault;
	}

	public void setLabAlertforwardstatusTodefault(
			Boolean labAlertforwardstatusTodefault) {
		this.labAlertforwardstatusTodefault = labAlertforwardstatusTodefault;
	}
}