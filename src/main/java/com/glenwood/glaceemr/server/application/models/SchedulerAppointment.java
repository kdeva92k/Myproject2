package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_appt")
public class SchedulerAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sch_appt_sch_appt_id_seq")
	@SequenceGenerator(name = "sch_appt_sch_appt_id_seq", sequenceName = "sch_appt_sch_appt_id_seq", allocationSize = 1)
	@Column(name="sch_appt_id")
	private Integer schApptId;

	@Column(name="sch_appt_date")
	private Date schApptDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_appt_starttime")
	private Timestamp schApptStarttime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_appt_endtime")
	private Timestamp schApptEndtime;

	@Column(name="sch_appt_interval")
	private Integer schApptInterval;

	@Column(name="sch_appt_patient_id")
	private Integer schApptPatientId;

	@Column(name="sch_appt_patientname")
	private String schApptPatientname;

	@Column(name="sch_appt_location")
	private Integer schApptLocation;

	@Column(name="sch_appt_resource")
	private Integer schApptResource;

	@Column(name="sch_appt_status")
	private Integer schApptStatus;

	@Column(name="sch_appt_type")
	private Integer schApptType;

	@Column(name="sch_appt_reason")
	private Integer schApptReason;

	@Column(name="sch_appt_nextcons_id")
	private Integer schApptNextconsId;

	@Column(name="sch_appt_comments")
	private String schApptComments;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_appt_rescheduledfrom_date")
	private Timestamp schApptRescheduledfromDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_appt_rescheduledfrom_time")
	private Timestamp schApptRescheduledfromTime;

	@Column(name="sch_appt_lastmodified_user_id")
	private Integer schApptLastmodifiedUserId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_appt_lastmodified_time")
	private Timestamp schApptLastmodifiedTime;

	@Column(name="sch_appt_lastmodified_username")
	private String schApptLastmodifiedUsername;

	@Column(name="sch_appt_room_id")
	private Integer schApptRoomId;

	@Column(name="sch_appt_homephone")
	private String schApptHomephone;

	@Column(name="sch_appt_homeextn")
	private String schApptHomeextn;

	@Column(name="sch_appt_workphone")
	private String schApptWorkphone;

	@Column(name="sch_appt_workextn")
	private String schApptWorkextn;

	@Column(name="sch_appt_cancelletion_reason_id")
	private Integer schApptCancelletionReasonId;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="sch_appt_statusgrp_id")
	private Integer schApptStatusgrpId;

	@Column(name="sch_appt_referringdoctor_id")
	private Integer schApptReferringdoctorId;

	@Column(name="h101028")
	private Integer h101028;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_patient_id",referencedColumnName="patient_registration_id",insertable=false,updatable=false)
	PatientRegistration patRegPatientId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_status",referencedColumnName="App_Reference_Values_statusId",insertable=false,updatable=false)
	AppReferenceValues App_Reference_ValuesApptStatus;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_type",referencedColumnName="App_Reference_Values_statusId",insertable=false,updatable=false)
	AppReferenceValues App_Reference_ValuesApptType;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_reason",referencedColumnName="App_Reference_Values_statusId",insertable=false,updatable=false)
	AppReferenceValues App_Reference_ValuesApptReason;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_id",referencedColumnName="sch_appt_parameter_appt_id",insertable=false,updatable=false)
	SchedulerAppointmentParameter schApptParam;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_location",referencedColumnName="sch_resource_id",insertable=false,updatable=false)
	SchedulerResource schResLoc;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_referringdoctor_id",referencedColumnName="referring_doctor_uniqueid",insertable=false,updatable=false)
	ReferringDoctor schRefDrId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_patient_id",referencedColumnName="workflow_patientid",insertable=false,updatable=false)
	Workflow workflowPatientId;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="sch_appt_resource", referencedColumnName="sch_resource_id", insertable=false, updatable=false)
	@JsonManagedReference
	SchedulerResource schResProvider;

	public Integer getSchApptId() {
		return schApptId;
	}

	public void setSchApptId(Integer schApptId) {
		this.schApptId = schApptId;
	}

	public Date getSchApptDate() {
		return schApptDate;
	}

	public void setSchApptDate(Date schApptDate) {
		this.schApptDate = schApptDate;
	}

	public Timestamp getSchApptStarttime() {
		return schApptStarttime;
	}

	public void setSchApptStarttime(Timestamp schApptStarttime) {
		this.schApptStarttime = schApptStarttime;
	}

	public Timestamp getSchApptEndtime() {
		return schApptEndtime;
	}

	public void setSchApptEndtime(Timestamp schApptEndtime) {
		this.schApptEndtime = schApptEndtime;
	}

	public Integer getSchApptInterval() {
		return schApptInterval;
	}

	public void setSchApptInterval(Integer schApptInterval) {
		this.schApptInterval = schApptInterval;
	}

	public Integer getSchApptPatientId() {
		return schApptPatientId;
	}

	public void setSchApptPatientId(Integer schApptPatientId) {
		this.schApptPatientId = schApptPatientId;
	}

	public String getSchApptPatientname() {
		return schApptPatientname;
	}

	public void setSchApptPatientname(String schApptPatientname) {
		this.schApptPatientname = schApptPatientname;
	}

	public Integer getSchApptLocation() {
		return schApptLocation;
	}

	public void setSchApptLocation(Integer schApptLocation) {
		this.schApptLocation = schApptLocation;
	}

	public Integer getSchApptResource() {
		return schApptResource;
	}

	public void setSchApptResource(Integer schApptResource) {
		this.schApptResource = schApptResource;
	}

	public Integer getSchApptStatus() {
		return schApptStatus;
	}

	public void setSchApptStatus(Integer schApptStatus) {
		this.schApptStatus = schApptStatus;
	}

	public Integer getSchApptType() {
		return schApptType;
	}

	public void setSchApptType(Integer schApptType) {
		this.schApptType = schApptType;
	}

	public Integer getSchApptReason() {
		return schApptReason;
	}

	public void setSchApptReason(Integer schApptReason) {
		this.schApptReason = schApptReason;
	}

	public Integer getSchApptNextconsId() {
		return schApptNextconsId;
	}

	public void setSchApptNextconsId(Integer schApptNextconsId) {
		this.schApptNextconsId = schApptNextconsId;
	}

	public String getSchApptComments() {
		return schApptComments;
	}

	public void setSchApptComments(String schApptComments) {
		this.schApptComments = schApptComments;
	}

	public Timestamp getSchApptRescheduledfromDate() {
		return schApptRescheduledfromDate;
	}

	public void setSchApptRescheduledfromDate(Timestamp schApptRescheduledfromDate) {
		this.schApptRescheduledfromDate = schApptRescheduledfromDate;
	}

	public Timestamp getSchApptRescheduledfromTime() {
		return schApptRescheduledfromTime;
	}

	public void setSchApptRescheduledfromTime(Timestamp schApptRescheduledfromTime) {
		this.schApptRescheduledfromTime = schApptRescheduledfromTime;
	}

	public Integer getSchApptLastmodifiedUserId() {
		return schApptLastmodifiedUserId;
	}

	public void setSchApptLastmodifiedUserId(Integer schApptLastmodifiedUserId) {
		this.schApptLastmodifiedUserId = schApptLastmodifiedUserId;
	}

	public Timestamp getSchApptLastmodifiedTime() {
		return schApptLastmodifiedTime;
	}

	public void setSchApptLastmodifiedTime(Timestamp schApptLastmodifiedTime) {
		this.schApptLastmodifiedTime = schApptLastmodifiedTime;
	}

	public String getSchApptLastmodifiedUsername() {
		return schApptLastmodifiedUsername;
	}

	public void setSchApptLastmodifiedUsername(String schApptLastmodifiedUsername) {
		this.schApptLastmodifiedUsername = schApptLastmodifiedUsername;
	}

	public Integer getSchApptRoomId() {
		return schApptRoomId;
	}

	public void setSchApptRoomId(Integer schApptRoomId) {
		this.schApptRoomId = schApptRoomId;
	}

	public String getSchApptHomephone() {
		return schApptHomephone;
	}

	public void setSchApptHomephone(String schApptHomephone) {
		this.schApptHomephone = schApptHomephone;
	}

	public String getSchApptHomeextn() {
		return schApptHomeextn;
	}

	public void setSchApptHomeextn(String schApptHomeextn) {
		this.schApptHomeextn = schApptHomeextn;
	}

	public String getSchApptWorkphone() {
		return schApptWorkphone;
	}

	public void setSchApptWorkphone(String schApptWorkphone) {
		this.schApptWorkphone = schApptWorkphone;
	}

	public String getSchApptWorkextn() {
		return schApptWorkextn;
	}

	public void setSchApptWorkextn(String schApptWorkextn) {
		this.schApptWorkextn = schApptWorkextn;
	}

	public Integer getSchApptCancelletionReasonId() {
		return schApptCancelletionReasonId;
	}

	public void setSchApptCancelletionReasonId(Integer schApptCancelletionReasonId) {
		this.schApptCancelletionReasonId = schApptCancelletionReasonId;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Integer getSchApptStatusgrpId() {
		return schApptStatusgrpId;
	}

	public void setSchApptStatusgrpId(Integer schApptStatusgrpId) {
		this.schApptStatusgrpId = schApptStatusgrpId;
	}

	public Integer getSchApptReferringdoctorId() {
		return schApptReferringdoctorId;
	}

	public void setSchApptReferringdoctorId(Integer schApptReferringdoctorId) {
		this.schApptReferringdoctorId = schApptReferringdoctorId;
	}

	public Integer getH101028() {
		return h101028;
	}

	public void setH101028(Integer h101028) {
		this.h101028 = h101028;
	}

	public PatientRegistration getPatRegPatientId() {
		return patRegPatientId;
	}

	public void setPatRegPatientId(PatientRegistration patRegPatientId) {
		this.patRegPatientId = patRegPatientId;
	}

	public AppReferenceValues getApp_Reference_ValuesApptStatus() {
		return App_Reference_ValuesApptStatus;
	}

	public void setApp_Reference_ValuesApptStatus(AppReferenceValues App_Reference_ValuesApptStatus) {
		this.App_Reference_ValuesApptStatus = App_Reference_ValuesApptStatus;
	}

	public AppReferenceValues getApp_Reference_ValuesApptType() {
		return App_Reference_ValuesApptType;
	}

	public void setApp_Reference_ValuesApptType(AppReferenceValues App_Reference_ValuesApptType) {
		this.App_Reference_ValuesApptType = App_Reference_ValuesApptType;
	}

	public AppReferenceValues getApp_Reference_ValuesApptReason() {
		return App_Reference_ValuesApptReason;
	}

	public void setApp_Reference_ValuesApptReason(AppReferenceValues App_Reference_ValuesApptReason) {
		this.App_Reference_ValuesApptReason = App_Reference_ValuesApptReason;
	}

	public SchedulerAppointmentParameter getSchApptParam() {
		return schApptParam;
	}

	public void setSchApptParam(SchedulerAppointmentParameter schApptParam) {
		this.schApptParam = schApptParam;
	}

	public SchedulerResource getSchResLoc() {
		return schResLoc;
	}

	public void setSchResLoc(SchedulerResource schResLoc) {
		this.schResLoc = schResLoc;
	}

	public ReferringDoctor getSchRefDrId() {
		return schRefDrId;
	}

	public void setSchRefDrId(ReferringDoctor schRefDrId) {
		this.schRefDrId = schRefDrId;
	}

	public Workflow getWorkflowPatientId() {
		return workflowPatientId;
	}

	public void setWorkflowPatientId(Workflow workflowPatientId) {
		this.workflowPatientId = workflowPatientId;
	}

	public SchedulerResource getSchResProvider() {
		return schResProvider;
	}

	public void setSchResProvider(SchedulerResource schResProvider) {
		this.schResProvider = schResProvider;
	}
	
}