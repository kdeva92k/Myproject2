package com.glenwood.glaceemr.server.application.models;


import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "warfarin_log")
public class WarfarinLog implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="warfarin_log_warfarin_log_id_seq")
	@SequenceGenerator(name ="warfarin_log_warfarin_log_id_seq", sequenceName="warfarin_log_warfarin_log_id_seq", allocationSize=1)
	@Column(name="warfarin_log_id")
	private Integer warfarinLogId;

	public Integer getWarfarinLogId() {
		return warfarinLogId;
	}

	public void setWarfarinLogId(Integer warfarinLogId) {
		this.warfarinLogId = warfarinLogId;
	}

	public Integer getWarfarinLogPatientId() {
		return warfarinLogPatientId;
	}

	public void setWarfarinLogPatientId(Integer warfarinLogPatientId) {
		this.warfarinLogPatientId = warfarinLogPatientId;
	}

	public Integer getWarfarinLogEpisodeId() {
		return warfarinLogEpisodeId;
	}

	public void setWarfarinLogEpisodeId(Integer warfarinLogEpisodeId) {
		this.warfarinLogEpisodeId = warfarinLogEpisodeId;
	}

	public Integer getWarfarinLogPhoneEncounterId() {
		return warfarinLogPhoneEncounterId;
	}

	public void setWarfarinLogPhoneEncounterId(Integer warfarinLogPhoneEncounterId) {
		this.warfarinLogPhoneEncounterId = warfarinLogPhoneEncounterId;
	}

	public Date getWarfarinLogEnteredOn() {
		return warfarinLogEnteredOn;
	}

	public void setWarfarinLogEnteredOn(Date warfarinLogEnteredOn) {
		this.warfarinLogEnteredOn = warfarinLogEnteredOn;
	}

	public Integer getWarfarinLogEnteredBy() {
		return warfarinLogEnteredBy;
	}

	public void setWarfarinLogEnteredBy(Integer warfarinLogEnteredBy) {
		this.warfarinLogEnteredBy = warfarinLogEnteredBy;
	}

	public Date getWarfarinLogPerformedDate() {
		return warfarinLogPerformedDate;
	}

	public void setWarfarinLogPerformedDate(Date warfarinLogPerformedDate) {
		this.warfarinLogPerformedDate = warfarinLogPerformedDate;
	}

	public String getWarfarinLogTotalWeeklyDose() {
		return warfarinLogTotalWeeklyDose;
	}

	public void setWarfarinLogTotalWeeklyDose(String warfarinLogTotalWeeklyDose) {
		this.warfarinLogTotalWeeklyDose = warfarinLogTotalWeeklyDose;
	}

	public String getWarfarinLogSundayDose() {
		return warfarinLogSundayDose;
	}

	public void setWarfarinLogSundayDose(String warfarinLogSundayDose) {
		this.warfarinLogSundayDose = warfarinLogSundayDose;
	}

	public String getWarfarinLogMondayDose() {
		return warfarinLogMondayDose;
	}

	public void setWarfarinLogMondayDose(String warfarinLogMondayDose) {
		this.warfarinLogMondayDose = warfarinLogMondayDose;
	}

	public String getWarfarinLogTuesdayDose() {
		return warfarinLogTuesdayDose;
	}

	public void setWarfarinLogTuesdayDose(String warfarinLogTuesdayDose) {
		this.warfarinLogTuesdayDose = warfarinLogTuesdayDose;
	}

	public String getWarfarinLogWednesdayDose() {
		return warfarinLogWednesdayDose;
	}

	public void setWarfarinLogWednesdayDose(String warfarinLogWednesdayDose) {
		this.warfarinLogWednesdayDose = warfarinLogWednesdayDose;
	}

	public String getWarfarinLogThursdayDose() {
		return warfarinLogThursdayDose;
	}

	public void setWarfarinLogThursdayDose(String warfarinLogThursdayDose) {
		this.warfarinLogThursdayDose = warfarinLogThursdayDose;
	}

	public String getWarfarinLogFridayDose() {
		return warfarinLogFridayDose;
	}

	public void setWarfarinLogFridayDose(String warfarinLogFridayDose) {
		this.warfarinLogFridayDose = warfarinLogFridayDose;
	}

	public String getWarfarinLogSaturdayDose() {
		return warfarinLogSaturdayDose;
	}

	public void setWarfarinLogSaturdayDose(String warfarinLogSaturdayDose) {
		this.warfarinLogSaturdayDose = warfarinLogSaturdayDose;
	}

	public String getWarfarinLogReturnVisitDate() {
		return warfarinLogReturnVisitDate;
	}

	public void setWarfarinLogReturnVisitDate(String warfarinLogReturnVisitDate) {
		this.warfarinLogReturnVisitDate = warfarinLogReturnVisitDate;
	}

	public String getWarfarinLogReturnVisitInst() {
		return warfarinLogReturnVisitInst;
	}

	public void setWarfarinLogReturnVisitInst(String warfarinLogReturnVisitInst) {
		this.warfarinLogReturnVisitInst = warfarinLogReturnVisitInst;
	}

	public String getWarfarinLogComments() {
		return warfarinLogComments;
	}

	public void setWarfarinLogComments(String warfarinLogComments) {
		this.warfarinLogComments = warfarinLogComments;
	}

	public Integer getWarfarinLogPatientInformed() {
		return warfarinLogPatientInformed;
	}

	public void setWarfarinLogPatientInformed(Integer warfarinLogPatientInformed) {
		this.warfarinLogPatientInformed = warfarinLogPatientInformed;
	}

	public Integer getWarfarinLogStatus() {
		return warfarinLogStatus;
	}

	public void setWarfarinLogStatus(Integer warfarinLogStatus) {
		this.warfarinLogStatus = warfarinLogStatus;
	}

	public Integer getWarfarinLogReviewedBy() {
		return warfarinLogReviewedBy;
	}

	public void setWarfarinLogReviewedBy(Integer warfarinLogReviewedBy) {
		this.warfarinLogReviewedBy = warfarinLogReviewedBy;
	}

	public Timestamp getWarfarinLogReviewedOn() {
		return warfarinLogReviewedOn;
	}

	public void setWarfarinLogReviewedOn(Timestamp warfarinLogReviewedOn) {
		this.warfarinLogReviewedOn = warfarinLogReviewedOn;
	}

	public Integer getWarfarinLogCreatedBy() {
		return warfarinLogCreatedBy;
	}

	public void setWarfarinLogCreatedBy(Integer warfarinLogCreatedBy) {
		this.warfarinLogCreatedBy = warfarinLogCreatedBy;
	}

	public Timestamp getWarfarinLogCreatedOn() {
		return warfarinLogCreatedOn;
	}

	public void setWarfarinLogCreatedOn(Timestamp warfarinLogCreatedOn) {
		this.warfarinLogCreatedOn = warfarinLogCreatedOn;
	}

	public Integer getWarfarinLogModifiedBy() {
		return warfarinLogModifiedBy;
	}

	public void setWarfarinLogModifiedBy(Integer warfarinLogModifiedBy) {
		this.warfarinLogModifiedBy = warfarinLogModifiedBy;
	}

	public Timestamp getWarfarinLogModifiedOn() {
		return warfarinLogModifiedOn;
	}

	public void setWarfarinLogModifiedOn(Timestamp warfarinLogModifiedOn) {
		this.warfarinLogModifiedOn = warfarinLogModifiedOn;
	}

	public Integer getWarfarinLogReturnVisitReminderId() {
		return warfarinLogReturnVisitReminderId;
	}

	public void setWarfarinLogReturnVisitReminderId(
			Integer warfarinLogReturnVisitReminderId) {
		this.warfarinLogReturnVisitReminderId = warfarinLogReturnVisitReminderId;
	}

	@Column(name="warfarin_log_patient_id")
	private Integer warfarinLogPatientId;

	@Column(name="warfarin_log_episode_id")
	private Integer warfarinLogEpisodeId;

	@Column(name="warfarin_log_phone_encounter_id")
	private Integer warfarinLogPhoneEncounterId;

	@Column(name="warfarin_log_entered_on")
	private Date warfarinLogEnteredOn;

	@Column(name="warfarin_log_entered_by")
	private Integer warfarinLogEnteredBy;

	@Column(name="warfarin_log_performed_date")
	private Date warfarinLogPerformedDate;

	@Column(name="warfarin_log_total_weekly_dose")
	private String warfarinLogTotalWeeklyDose;

	@Column(name="warfarin_log_sunday_dose")
	private String warfarinLogSundayDose;

	@Column(name="warfarin_log_monday_dose")
	private String warfarinLogMondayDose;

	@Column(name="warfarin_log_tuesday_dose")
	private String warfarinLogTuesdayDose;

	@Column(name="warfarin_log_wednesday_dose")
	private String warfarinLogWednesdayDose;

	@Column(name="warfarin_log_thursday_dose")
	private String warfarinLogThursdayDose;

	@Column(name="warfarin_log_friday_dose")
	private String warfarinLogFridayDose;

	@Column(name="warfarin_log_saturday_dose")
	private String warfarinLogSaturdayDose;

	@Column(name="warfarin_log_return_visit_date")
	private String warfarinLogReturnVisitDate;

	@Column(name="warfarin_log_return_visit_inst")
	private String warfarinLogReturnVisitInst;

	@Column(name="warfarin_log_comments")
	private String warfarinLogComments;

	@Column(name="warfarin_log_patient_informed")
	private Integer warfarinLogPatientInformed;

	@Column(name="warfarin_log_status")
	private Integer warfarinLogStatus;

	@Column(name="warfarin_log_reviewed_by")
	private Integer warfarinLogReviewedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="warfarin_log_reviewed_on")
	private Timestamp warfarinLogReviewedOn;

	@Column(name="warfarin_log_created_by")
	private Integer warfarinLogCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="warfarin_log_created_on")
	private Timestamp warfarinLogCreatedOn;

	@Column(name="warfarin_log_modified_by")
	private Integer warfarinLogModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="warfarin_log_modified_on")
	private Timestamp warfarinLogModifiedOn;

	@Column(name="warfarin_log_return_visit_reminder_id")
	private Integer warfarinLogReturnVisitReminderId;
	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional=true)
	@JoinColumn(name="warfarin_log_created_by",referencedColumnName="emp_profile_loginid",insertable=false,updatable=false)
	EmployeeProfile employeetableByCreatedName;

	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional=true)
	@JoinColumn(name="warfarin_log_modified_by",referencedColumnName="emp_profile_loginid",insertable=false, updatable=false)
	EmployeeProfile employeetableModifiesName;
	
	public EmployeeProfile getEmployeetable2() {
		return employeetableModifiesName;
	}

	public void setEmployeetable2(EmployeeProfile employeetable2) {
		this.employeetableModifiesName = employeetable2;
	}

	public EmployeeProfile getEmployeetable3() {
		return employeetableReviewedName;
	}

	public void setEmployeetable3(EmployeeProfile employeetable3) {
		this.employeetableReviewedName = employeetable3;
	}

	public EmployeeProfile getEmployeetable4() {
		return employeetableByEnteredName;
	}

	public void setEmployeetable4(EmployeeProfile employeetable4) {
		this.employeetableByEnteredName = employeetable4;
	}

	public ReminderEvent getReminderevent() {
		return reminderevent;
	}

	public void setReminderevent(ReminderEvent reminderevent) {
		this.reminderevent = reminderevent;
	}

	public AssociatedPhoneLogs getPhonelogs() {
		return phonelogs;
	}

	public void setPhonelogs(AssociatedPhoneLogs phonelogs) {
		this.phonelogs = phonelogs;
	}

	public EmployeeProfile getEmployeetable1() {
		return employeetableByCreatedName;
	}

	public void setEmployeetable1(EmployeeProfile employeetable1) {
		this.employeetableByCreatedName = employeetable1;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional=true)
	@JoinColumn(name="warfarin_log_reviewed_by",referencedColumnName="emp_profile_loginid",insertable=false, updatable=false)
	EmployeeProfile employeetableReviewedName;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional=true)
	@JoinColumn(name="warfarin_log_entered_by",referencedColumnName="emp_profile_loginid",insertable=false, updatable=false)
	EmployeeProfile employeetableByEnteredName;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="warfarin_log_return_visit_reminder_id",referencedColumnName="reminder_event_id", nullable=false, insertable=false, updatable=false)
	ReminderEvent reminderevent;
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="warfarin_log_id",referencedColumnName="associated_phone_logs_entity_id", nullable=false, insertable=false, updatable=false)
	AssociatedPhoneLogs phonelogs;
	
}
