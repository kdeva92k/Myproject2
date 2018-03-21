package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "direct_email_log")
public class DirectEmailLog {

	@Id
	@Column(name="direct_email_log_id")
	private Integer directEmailLogId;

	@Column(name="direct_email_log_type")
	private String directEmailLogType;

	@Column(name="direct_email_log_entity_id")
	private Integer directEmailLogEntityId;

	@Column(name="direct_email_log_message_id")
	private String directEmailLogMessageId;

	@Column(name="direct_email_log_sent_by")
	private Integer directEmailLogSentBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="direct_email_log_sent_on")
	private Timestamp directEmailLogSentOn;

	@Column(name="direct_email_log_user_type")
	private Integer directEmailLogUserType;

	@Column(name="direct_email_log_action_type")
	private Integer directEmailLogActionType;

	@Column(name="direct_email_log_patientid")
	private Integer directEmailLogPatientid;

	public Integer getDirectEmailLogId() {
		return directEmailLogId;
	}

	public void setDirectEmailLogId(Integer directEmailLogId) {
		this.directEmailLogId = directEmailLogId;
	}

	public String getDirectEmailLogType() {
		return directEmailLogType;
	}

	public void setDirectEmailLogType(String directEmailLogType) {
		this.directEmailLogType = directEmailLogType;
	}

	public Integer getDirectEmailLogEntityId() {
		return directEmailLogEntityId;
	}

	public void setDirectEmailLogEntityId(Integer directEmailLogEntityId) {
		this.directEmailLogEntityId = directEmailLogEntityId;
	}

	public String getDirectEmailLogMessageId() {
		return directEmailLogMessageId;
	}

	public void setDirectEmailLogMessageId(String directEmailLogMessageId) {
		this.directEmailLogMessageId = directEmailLogMessageId;
	}

	public Integer getDirectEmailLogSentBy() {
		return directEmailLogSentBy;
	}

	public void setDirectEmailLogSentBy(Integer directEmailLogSentBy) {
		this.directEmailLogSentBy = directEmailLogSentBy;
	}

	public Timestamp getDirectEmailLogSentOn() {
		return directEmailLogSentOn;
	}

	public void setDirectEmailLogSentOn(Timestamp directEmailLogSentOn) {
		this.directEmailLogSentOn = directEmailLogSentOn;
	}

	public Integer getDirectEmailLogUserType() {
		return directEmailLogUserType;
	}

	public void setDirectEmailLogUserType(Integer directEmailLogUserType) {
		this.directEmailLogUserType = directEmailLogUserType;
	}

	public Integer getDirectEmailLogActionType() {
		return directEmailLogActionType;
	}

	public void setDirectEmailLogActionType(Integer directEmailLogActionType) {
		this.directEmailLogActionType = directEmailLogActionType;
	}

	public Integer getDirectEmailLogPatientid() {
		return directEmailLogPatientid;
	}

	public void setDirectEmailLogPatientid(Integer directEmailLogPatientid) {
		this.directEmailLogPatientid = directEmailLogPatientid;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="direct_email_log_sent_by", referencedColumnName="chart_patientid" , insertable=false, updatable=false)
	private Chart chartTable;

	public Chart getChartTable() {
		return chartTable;
	}

	public void setChartTable(Chart chartTable) {
		this.chartTable = chartTable;
	}

}