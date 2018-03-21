package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "attestation_status")
public class AttestationStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="attestation_status_reporting_id_seq")
	@SequenceGenerator(name ="attestation_status_reporting_id_seq", sequenceName="attestation_status_reporting_id_seq", allocationSize=1)
	@Column(name="reporting_id")
	private Integer reportingId;

	@Column(name="reporting_year")
	private Integer reportingYear;

	@Column(name="reporting_provider")
	private String reportingProvider;

	@Column(name="reporting_type")
	private String reportingType;

	@Column(name="reporting_method")
	private String reportingMethod;

	@Column(name="reporting_status")
	private String reportingStatus;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="reported_date")
	private Timestamp reportedDate;

	@Column(name="reporting_comments")
	private String reportingComments;

	public Integer getReportingId() {
		return reportingId;
	}

	public void setReportingId(Integer reportingId) {
		this.reportingId = reportingId;
	}

	public Integer getReportingYear() {
		return reportingYear;
	}

	public void setReportingYear(Integer reportingYear) {
		this.reportingYear = reportingYear;
	}

	public String getReportingProvider() {
		return reportingProvider;
	}

	public void setReportingProvider(String reportingProvider) {
		this.reportingProvider = reportingProvider;
	}

	public String getReportingType() {
		return reportingType;
	}

	public void setReportingType(String reportingType) {
		this.reportingType = reportingType;
	}

	public String getReportingMethod() {
		return reportingMethod;
	}

	public void setReportingMethod(String reportingMethod) {
		this.reportingMethod = reportingMethod;
	}

	public String getReportingStatus() {
		return reportingStatus;
	}

	public void setReportingStatus(String reportingStatus) {
		this.reportingStatus = reportingStatus;
	}

	public Timestamp getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Timestamp reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getReportingComments() {
		return reportingComments;
	}

	public void setReportingComments(String reportingComments) {
		this.reportingComments = reportingComments;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional=true)
	@JoinColumn(name="reporting_provider",referencedColumnName="emp_profile_fullname",insertable=false, updatable=false)
	EmployeeProfile empProfileTableFullName;
	
}