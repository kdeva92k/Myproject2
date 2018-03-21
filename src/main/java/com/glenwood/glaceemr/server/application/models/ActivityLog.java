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
@Table(name = "activity_log")
public class ActivityLog {

	@Column(name="patientid")
	private Integer patientid;

	@Column(name="encounterid")
	private Integer encounterid;

	@Column(name="moduleid")
	private Integer moduleid;

	@Column(name="textmessage")
	private String textmessage;

	@Column(name="entityid")
	private Integer entityid;

	@Column(name="status")
	private Boolean status;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="activity_log_entryid_seq")
	@SequenceGenerator(name ="activity_log_entryid_seq", sequenceName="activity_log_entryid_seq", allocationSize=1)
	@Column(name="entryid")
	private Integer entryid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="time")
	private Timestamp time;

	@Column(name="userid")
	private Integer userid;

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public Integer getEncounterid() {
		return encounterid;
	}

	public void setEncounterid(Integer encounterid) {
		this.encounterid = encounterid;
	}

	public Integer getModuleid() {
		return moduleid;
	}

	public void setModuleid(Integer moduleid) {
		this.moduleid = moduleid;
	}

	public String getTextmessage() {
		return textmessage;
	}

	public void setTextmessage(String textmessage) {
		this.textmessage = textmessage;
	}

	public Integer getEntityid() {
		return entityid;
	}

	public void setEntityid(Integer entityid) {
		this.entityid = entityid;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getEntryid() {
		return entryid;
	}

	public void setEntryid(Integer entryid) {
		this.entryid = entryid;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}