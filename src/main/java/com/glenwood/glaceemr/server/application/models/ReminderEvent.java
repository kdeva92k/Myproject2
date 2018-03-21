package com.glenwood.glaceemr.server.application.models;


import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "reminder_event")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReminderEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="reminder_event_reminder_event_id_seq")
	@SequenceGenerator(name ="reminder_event_reminder_event_id_seq", sequenceName="reminder_event_reminder_event_id_seq", allocationSize=1)
	@Column(name="reminder_event_id")
	private Integer reminderEventId;

	@Column(name="reminder_event_subject")
	private String reminderEventSubject;

	@Column(name="reminder_event_description")
	private String reminderEventDescription;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="reminder_event_duedate")
	private Timestamp reminderEventDuedate;//

	@Column(name="reminder_event_status")
	private Integer reminderEventStatus;

	@Column(name="reminder_event_createdby")
	private Integer reminderEventCreatedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="reminder_event_createddate")
	private Timestamp reminderEventCreateddate;

	@Column(name="reminder_event_modifiedby")
	private Integer reminderEventModifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="reminder_event_modifieddate")
	private Timestamp reminderEventModifieddate;

	@Column(name="reminder_event_patientid")
	private Integer reminderEventPatientid;

	@Column(name="reminder_event_type")
	private Integer reminderEventType;

	@Column(name="reminder_event_customid1")
	private Integer reminderEventCustomid1;

	@Column(name="reminder_event_customid2")
	private Integer reminderEventCustomid2;

	@Column(name="reminder_event_customid3")
	private Integer reminderEventCustomid3;

	@Column(name="reminder_event_customid4")
	private Integer reminderEventCustomid4;

	@Column(name="reminder_event_rem_dismiss_date")
	private Date reminderEventRemDismissDate;

	@Column(name="reminder_event_rem_status")
	private Integer reminderEventRemStatus;

	public Integer getReminderEventId() {
		return reminderEventId;
	}

	public void setReminderEventId(Integer reminderEventId) {
		this.reminderEventId = reminderEventId;
	}

	public String getReminderEventSubject() {
		return reminderEventSubject;
	}

	public void setReminderEventSubject(String reminderEventSubject) {
		this.reminderEventSubject = reminderEventSubject;
	}

	public String getReminderEventDescription() {
		return reminderEventDescription;
	}

	public void setReminderEventDescription(String reminderEventDescription) {
		this.reminderEventDescription = reminderEventDescription;
	}

	public Timestamp getReminderEventDuedate() {
		return reminderEventDuedate;
	}

	public void setReminderEventDuedate(Timestamp reminderEventDuedate) {
		this.reminderEventDuedate = reminderEventDuedate;
	}

	public Integer getReminderEventStatus() {
		return reminderEventStatus;
	}

	public void setReminderEventStatus(Integer reminderEventStatus) {
		this.reminderEventStatus = reminderEventStatus;
	}

	public Integer getReminderEventCreatedby() {
		return reminderEventCreatedby;
	}

	public void setReminderEventCreatedby(Integer reminderEventCreatedby) {
		this.reminderEventCreatedby = reminderEventCreatedby;
	}

	public Timestamp getReminderEventCreateddate() {
		return reminderEventCreateddate;
	}

	public void setReminderEventCreateddate(Timestamp reminderEventCreateddate) {
		this.reminderEventCreateddate = reminderEventCreateddate;
	}

	public Integer getReminderEventModifiedby() {
		return reminderEventModifiedby;
	}

	public void setReminderEventModifiedby(Integer reminderEventModifiedby) {
		this.reminderEventModifiedby = reminderEventModifiedby;
	}

	public Timestamp getReminderEventModifieddate() {
		return reminderEventModifieddate;
	}

	public void setReminderEventModifieddate(Timestamp reminderEventModifieddate) {
		this.reminderEventModifieddate = reminderEventModifieddate;
	}

	public Integer getReminderEventPatientid() {
		return reminderEventPatientid;
	}

	public void setReminderEventPatientid(Integer reminderEventPatientid) {
		this.reminderEventPatientid = reminderEventPatientid;
	}

	public Integer getReminderEventType() {
		return reminderEventType;
	}

	public void setReminderEventType(Integer reminderEventType) {
		this.reminderEventType = reminderEventType;
	}

	public Integer getReminderEventCustomid1() {
		return reminderEventCustomid1;
	}

	public void setReminderEventCustomid1(Integer reminderEventCustomid1) {
		this.reminderEventCustomid1 = reminderEventCustomid1;
	}

	public Integer getReminderEventCustomid2() {
		return reminderEventCustomid2;
	}

	public void setReminderEventCustomid2(Integer reminderEventCustomid2) {
		this.reminderEventCustomid2 = reminderEventCustomid2;
	}

	public Integer getReminderEventCustomid3() {
		return reminderEventCustomid3;
	}

	public void setReminderEventCustomid3(Integer reminderEventCustomid3) {
		this.reminderEventCustomid3 = reminderEventCustomid3;
	}

	public Integer getReminderEventCustomid4() {
		return reminderEventCustomid4;
	}

	public void setReminderEventCustomid4(Integer reminderEventCustomid4) {
		this.reminderEventCustomid4 = reminderEventCustomid4;
	}

	public Date getReminderEventRemDismissDate() {
		return reminderEventRemDismissDate;
	}

	public void setReminderEventRemDismissDate(Date reminderEventRemDismissDate) {
		this.reminderEventRemDismissDate = reminderEventRemDismissDate;
	}

	public Integer getReminderEventRemStatus() {
		return reminderEventRemStatus;
	}

	public void setReminderEventRemStatus(Integer reminderEventRemStatus) {
		this.reminderEventRemStatus = reminderEventRemStatus;
	}

	public Integer getReminderEventRemMethod() {
		return reminderEventRemMethod;
	}

	public void setReminderEventRemMethod(Integer reminderEventRemMethod) {
		this.reminderEventRemMethod = reminderEventRemMethod;
	}

	public Integer getReminderEventRemindedBy() {
		return reminderEventRemindedBy;
	}

	public void setReminderEventRemindedBy(Integer reminderEventRemindedBy) {
		this.reminderEventRemindedBy = reminderEventRemindedBy;
	}

	@Column(name="reminder_event_rem_method")
	private Integer reminderEventRemMethod;

	@Column(name="reminder_event_reminded_by")
	private Integer reminderEventRemindedBy;
}
