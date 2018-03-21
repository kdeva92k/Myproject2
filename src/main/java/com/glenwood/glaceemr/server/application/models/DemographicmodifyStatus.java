package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "demographicmodify_status")
public class DemographicmodifyStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="demographicmodify_status_demographicmodify_status_id_seq")
	@SequenceGenerator(name ="demographicmodify_status_demographicmodify_status_id_seq", sequenceName="demographicmodify_status_demographicmodify_status_id_seq", allocationSize=1)
	@Column(name="demographicmodify_status_id")
	private Integer demographicmodifyStatusId;

	@Column(name="demographicmodify_status_patientid")
	private Integer demographicmodifyStatusPatientid;

	@Column(name="demographicmodify_status_requestdate")
	private Date demographicmodifyStatusRequestdate;

	@Column(name="demographicmodify_status_status")
	private String demographicmodifyStatusStatus;

	@Column(name="demographicmodify_status_display")
	private Integer demographicmodifyStatusDisplay;

	public Integer getDemographicmodifyStatusId() {
		return demographicmodifyStatusId;
	}

	public void setDemographicmodifyStatusId(Integer demographicmodifyStatusId) {
		this.demographicmodifyStatusId = demographicmodifyStatusId;
	}

	public Integer getDemographicmodifyStatusPatientid() {
		return demographicmodifyStatusPatientid;
	}

	public void setDemographicmodifyStatusPatientid(
			Integer demographicmodifyStatusPatientid) {
		this.demographicmodifyStatusPatientid = demographicmodifyStatusPatientid;
	}

	public Date getDemographicmodifyStatusRequestdate() {
		return demographicmodifyStatusRequestdate;
	}

	public void setDemographicmodifyStatusRequestdate(
			Date demographicmodifyStatusRequestdate) {
		this.demographicmodifyStatusRequestdate = demographicmodifyStatusRequestdate;
	}

	public String getDemographicmodifyStatusStatus() {
		return demographicmodifyStatusStatus;
	}

	public void setDemographicmodifyStatusStatus(
			String demographicmodifyStatusStatus) {
		this.demographicmodifyStatusStatus = demographicmodifyStatusStatus;
	}

	public Integer getDemographicmodifyStatusDisplay() {
		return demographicmodifyStatusDisplay;
	}

	public void setDemographicmodifyStatusDisplay(
			Integer demographicmodifyStatusDisplay) {
		this.demographicmodifyStatusDisplay = demographicmodifyStatusDisplay;
	}
	
}