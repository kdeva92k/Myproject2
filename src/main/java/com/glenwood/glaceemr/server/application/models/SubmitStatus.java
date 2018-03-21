package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "submit_status")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitStatus {
	
	@Column(name="submit_status_id")
	private Integer submitStatusId;
	@Id
	@Column(name="submit_status_code")
	private String submitStatusCode;
	
	@Column(name="submit_status_description")
	private String submitStatusDescription;
	
	@Column(name="submit_status_bgcolor")
	private String submitStatusBgcolor;
	
	@Column(name="submit_status_forecolor")
	private String submitStatusForecolor;

	public Integer getSubmitStatusId() {
		return submitStatusId;
	}

	public void setSubmitStatusId(Integer submitStatusId) {
		this.submitStatusId = submitStatusId;
	}

	public String getSubmitStatusCode() {
		return submitStatusCode;
	}

	public void setSubmitStatusCode(String submitStatusCode) {
		this.submitStatusCode = submitStatusCode;
	}

	public String getSubmitStatusDescription() {
		return submitStatusDescription;
	}

	public void setSubmitStatusDescription(String submitStatusDescription) {
		this.submitStatusDescription = submitStatusDescription;
	}

	public String getSubmitStatusBgcolor() {
		return submitStatusBgcolor;
	}

	public void setSubmitStatusBgcolor(String submitStatusBgcolor) {
		this.submitStatusBgcolor = submitStatusBgcolor;
	}

	public String getSubmitStatusForecolor() {
		return submitStatusForecolor;
	}

	public void setSubmitStatusForecolor(String submitStatusForecolor) {
		this.submitStatusForecolor = submitStatusForecolor;
	}
}
