package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
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
@Table(name = "problem_report")
public class ProblemReport {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problem_report_problem_report_uniqueid_seq")
	@SequenceGenerator(name = "problem_report_problem_report_uniqueid_seq", sequenceName = "problem_report_problem_report_uniqueid_seq", allocationSize = 1)
	@Column(name="problem_report_uniqueid")
	private Integer problemReportUniqueid;

	@Column(name="problem_report_ticket_no")
	private String problemReportTicketNo;

	@Column(name="problem_report_problem_id")
	private Integer problemReportProblemId;

	@Column(name="problem_report_practice_id")
	private Integer problemReportPracticeId;

	@Column(name="problem_report_problemstatus")
	private Integer problemReportProblemstatus;

	@Column(name="problem_report_problem_type")
	private Integer problemReportProblemType;

	@Column(name="problem_report_problem")
	private String problemReportProblem;

	@Column(name="problem_report_solution")
	private String problemReportSolution;

	@Column(name="problem_report_complexity")
	private Integer problemReportComplexity;

	@Column(name="problem_report_priority")
	private Integer problemReportPriority;

	@Column(name="problem_report_reported_by")
	private String problemReportReportedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_report_reported_on" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp problemReportReportedOn;

	@Column(name="problem_report_reported_to")
	private String problemReportReportedTo;

	@Column(name="problem_report_resolved_by")
	private String problemReportResolvedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_report_resolved_on")
	private Timestamp problemReportResolvedOn;

	@Column(name="problem_report_patient_name")
	private String problemReportPatientName;

	@Column(name="problem_report_account_no")
	private String problemReportAccountNo;

	@Column(name="problem_report_patient_dob")
	private Date problemReportPatientDob;

	@Column(name="problem_report_contact_phone_no")
	private String problemReportContactPhoneNo;

	@Column(name="problem_report_login_user")
	private String problemReportLoginUser;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_report_last_modified",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp problemReportLastModified;

	@Column(name="problem_report_patient_ins_id")
	private String problemReportPatientInsId;

	@Column(name="problem_report_ins_comp_name")
	private String problemReportInsCompName;

	@Column(name="problem_report_service_id")
	private Integer problemReportServiceId;

	@Column(name="problem_report_cycle_count")
	private Integer problemReportCycleCount;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_report_call_startedtime")
	private Timestamp problemReportCallStartedtime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_report_call_endedtime")
	private Timestamp problemReportCallEndedtime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_report_activity_startedtime")
	private Timestamp problemReportActivityStartedtime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_report_activity_endedtime")
	private Timestamp problemReportActivityEndedtime;

	@Column(name="problem_report_service_period")
	private String problemReportServicePeriod;

	@Column(name="problem_report_patientid")
	private Integer problemReportPatientid;

	@Column(name="problem_report_department")
	private Integer problemReportDepartment;

	@Column(name="problem_report_placedby")
	private String problemReportPlacedby;

	@Column(name="problem_report_tmpstatus")
	private String problemReportTmpstatus;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="problem_report_subject")
	private String problemReportSubject;

	@Column(name="problem_report_trail_id")
	private String problemReportTrailId;

	@Column(name="problem_report_group_id")
	private String problemReportGroupId;

	@Column(name="problem_report_patientcall")
	private Integer problemReportPatientcall;

	@Column(name="problem_report_problem_denialrulevalidator_id")
	private Integer problemReportProblemDenialrulevalidatorId;

	@Column(name="problem_report_filepaths")
	private String problemReportFilepaths;

	@Column(name="problem_report_filenames")
	private String problemReportFilenames;

	@Column(name="problem_report_reviewed")
	private Boolean problemReportReviewed;

	@Column(name="problem_report_problem_typedesc")
	private String problemReportProblemTypedesc;

	@Column(name="problem_report_reply_filepath")
	private String problemReportReplyFilepath;

	@Column(name="problem_report_reply_filename")
	private String problemReportReplyFilename;

	@Column(name="problem_report_last_forwarded_to")
	private String problemReportLastForwardedTo;

	@Column(name="problem_report_last_forwarded_by")
	private String problemReportLastForwardedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_report_last_forwarded_on")
	private Timestamp problemReportLastForwardedOn;

	public Integer getProblemReportUniqueid() {
		return problemReportUniqueid;
	}

	public void setProblemReportUniqueid(Integer problemReportUniqueid) {
		this.problemReportUniqueid = problemReportUniqueid;
	}

	public String getProblemReportTicketNo() {
		return problemReportTicketNo;
	}

	public void setProblemReportTicketNo(String problemReportTicketNo) {
		this.problemReportTicketNo = problemReportTicketNo;
	}

	public Integer getProblemReportProblemId() {
		return problemReportProblemId;
	}

	public void setProblemReportProblemId(Integer problemReportProblemId) {
		this.problemReportProblemId = problemReportProblemId;
	}

	public Integer getProblemReportPracticeId() {
		return problemReportPracticeId;
	}

	public void setProblemReportPracticeId(Integer problemReportPracticeId) {
		this.problemReportPracticeId = problemReportPracticeId;
	}

	public Integer getProblemReportProblemstatus() {
		return problemReportProblemstatus;
	}

	public void setProblemReportProblemstatus(Integer problemReportProblemstatus) {
		this.problemReportProblemstatus = problemReportProblemstatus;
	}

	public Integer getProblemReportProblemType() {
		return problemReportProblemType;
	}

	public void setProblemReportProblemType(Integer problemReportProblemType) {
		this.problemReportProblemType = problemReportProblemType;
	}

	public String getProblemReportProblem() {
		return problemReportProblem;
	}

	public void setProblemReportProblem(String problemReportProblem) {
		this.problemReportProblem = problemReportProblem;
	}

	public String getProblemReportSolution() {
		return problemReportSolution;
	}

	public void setProblemReportSolution(String problemReportSolution) {
		this.problemReportSolution = problemReportSolution;
	}

	public Integer getProblemReportComplexity() {
		return problemReportComplexity;
	}

	public void setProblemReportComplexity(Integer problemReportComplexity) {
		this.problemReportComplexity = problemReportComplexity;
	}

	public Integer getProblemReportPriority() {
		return problemReportPriority;
	}

	public void setProblemReportPriority(Integer problemReportPriority) {
		this.problemReportPriority = problemReportPriority;
	}

	public String getProblemReportReportedBy() {
		return problemReportReportedBy;
	}

	public void setProblemReportReportedBy(String problemReportReportedBy) {
		this.problemReportReportedBy = problemReportReportedBy;
	}

	public Timestamp getProblemReportReportedOn() {
		return problemReportReportedOn;
	}

	public void setProblemReportReportedOn(Timestamp problemReportReportedOn) {
		this.problemReportReportedOn = problemReportReportedOn;
	}

	public String getProblemReportReportedTo() {
		return problemReportReportedTo;
	}

	public void setProblemReportReportedTo(String problemReportReportedTo) {
		this.problemReportReportedTo = problemReportReportedTo;
	}

	public String getProblemReportResolvedBy() {
		return problemReportResolvedBy;
	}

	public void setProblemReportResolvedBy(String problemReportResolvedBy) {
		this.problemReportResolvedBy = problemReportResolvedBy;
	}

	public Timestamp getProblemReportResolvedOn() {
		return problemReportResolvedOn;
	}

	public void setProblemReportResolvedOn(Timestamp problemReportResolvedOn) {
		this.problemReportResolvedOn = problemReportResolvedOn;
	}

	public String getProblemReportPatientName() {
		return problemReportPatientName;
	}

	public void setProblemReportPatientName(String problemReportPatientName) {
		this.problemReportPatientName = problemReportPatientName;
	}

	public String getProblemReportAccountNo() {
		return problemReportAccountNo;
	}

	public void setProblemReportAccountNo(String problemReportAccountNo) {
		this.problemReportAccountNo = problemReportAccountNo;
	}

	public Date getProblemReportPatientDob() {
		return problemReportPatientDob;
	}

	public void setProblemReportPatientDob(Date problemReportPatientDob) {
		this.problemReportPatientDob = problemReportPatientDob;
	}

	public String getProblemReportContactPhoneNo() {
		return problemReportContactPhoneNo;
	}

	public void setProblemReportContactPhoneNo(String problemReportContactPhoneNo) {
		this.problemReportContactPhoneNo = problemReportContactPhoneNo;
	}

	public String getProblemReportLoginUser() {
		return problemReportLoginUser;
	}

	public void setProblemReportLoginUser(String problemReportLoginUser) {
		this.problemReportLoginUser = problemReportLoginUser;
	}

	public Timestamp getProblemReportLastModified() {
		return problemReportLastModified;
	}

	public void setProblemReportLastModified(Timestamp problemReportLastModified) {
		this.problemReportLastModified = problemReportLastModified;
	}

	public String getProblemReportPatientInsId() {
		return problemReportPatientInsId;
	}

	public void setProblemReportPatientInsId(String problemReportPatientInsId) {
		this.problemReportPatientInsId = problemReportPatientInsId;
	}

	public String getProblemReportInsCompName() {
		return problemReportInsCompName;
	}

	public void setProblemReportInsCompName(String problemReportInsCompName) {
		this.problemReportInsCompName = problemReportInsCompName;
	}

	public Integer getProblemReportServiceId() {
		return problemReportServiceId;
	}

	public void setProblemReportServiceId(Integer problemReportServiceId) {
		this.problemReportServiceId = problemReportServiceId;
	}

	public Integer getProblemReportCycleCount() {
		return problemReportCycleCount;
	}

	public void setProblemReportCycleCount(Integer problemReportCycleCount) {
		this.problemReportCycleCount = problemReportCycleCount;
	}

	public Timestamp getProblemReportCallStartedtime() {
		return problemReportCallStartedtime;
	}

	public void setProblemReportCallStartedtime(
			Timestamp problemReportCallStartedtime) {
		this.problemReportCallStartedtime = problemReportCallStartedtime;
	}

	public Timestamp getProblemReportCallEndedtime() {
		return problemReportCallEndedtime;
	}

	public void setProblemReportCallEndedtime(Timestamp problemReportCallEndedtime) {
		this.problemReportCallEndedtime = problemReportCallEndedtime;
	}

	public Timestamp getProblemReportActivityStartedtime() {
		return problemReportActivityStartedtime;
	}

	public void setProblemReportActivityStartedtime(
			Timestamp problemReportActivityStartedtime) {
		this.problemReportActivityStartedtime = problemReportActivityStartedtime;
	}

	public Timestamp getProblemReportActivityEndedtime() {
		return problemReportActivityEndedtime;
	}

	public void setProblemReportActivityEndedtime(
			Timestamp problemReportActivityEndedtime) {
		this.problemReportActivityEndedtime = problemReportActivityEndedtime;
	}

	public String getProblemReportServicePeriod() {
		return problemReportServicePeriod;
	}

	public void setProblemReportServicePeriod(String problemReportServicePeriod) {
		this.problemReportServicePeriod = problemReportServicePeriod;
	}

	public Integer getProblemReportPatientid() {
		return problemReportPatientid;
	}

	public void setProblemReportPatientid(Integer problemReportPatientid) {
		this.problemReportPatientid = problemReportPatientid;
	}

	public Integer getProblemReportDepartment() {
		return problemReportDepartment;
	}

	public void setProblemReportDepartment(Integer problemReportDepartment) {
		this.problemReportDepartment = problemReportDepartment;
	}

	public String getProblemReportPlacedby() {
		return problemReportPlacedby;
	}

	public void setProblemReportPlacedby(String problemReportPlacedby) {
		this.problemReportPlacedby = problemReportPlacedby;
	}

	public String getProblemReportTmpstatus() {
		return problemReportTmpstatus;
	}

	public void setProblemReportTmpstatus(String problemReportTmpstatus) {
		this.problemReportTmpstatus = problemReportTmpstatus;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public String getProblemReportSubject() {
		return problemReportSubject;
	}

	public void setProblemReportSubject(String problemReportSubject) {
		this.problemReportSubject = problemReportSubject;
	}

	public String getProblemReportTrailId() {
		return problemReportTrailId;
	}

	public void setProblemReportTrailId(String problemReportTrailId) {
		this.problemReportTrailId = problemReportTrailId;
	}

	public String getProblemReportGroupId() {
		return problemReportGroupId;
	}

	public void setProblemReportGroupId(String problemReportGroupId) {
		this.problemReportGroupId = problemReportGroupId;
	}

	public Integer getProblemReportPatientcall() {
		return problemReportPatientcall;
	}

	public void setProblemReportPatientcall(Integer problemReportPatientcall) {
		this.problemReportPatientcall = problemReportPatientcall;
	}

	public Integer getProblemReportProblemDenialrulevalidatorId() {
		return problemReportProblemDenialrulevalidatorId;
	}

	public void setProblemReportProblemDenialrulevalidatorId(
			Integer problemReportProblemDenialrulevalidatorId) {
		this.problemReportProblemDenialrulevalidatorId = problemReportProblemDenialrulevalidatorId;
	}

	public String getProblemReportFilepaths() {
		return problemReportFilepaths;
	}

	public void setProblemReportFilepaths(String problemReportFilepaths) {
		this.problemReportFilepaths = problemReportFilepaths;
	}

	public String getProblemReportFilenames() {
		return problemReportFilenames;
	}

	public void setProblemReportFilenames(String problemReportFilenames) {
		this.problemReportFilenames = problemReportFilenames;
	}

	public Boolean getProblemReportReviewed() {
		return problemReportReviewed;
	}

	public void setProblemReportReviewed(Boolean problemReportReviewed) {
		this.problemReportReviewed = problemReportReviewed;
	}

	public String getProblemReportProblemTypedesc() {
		return problemReportProblemTypedesc;
	}

	public void setProblemReportProblemTypedesc(String problemReportProblemTypedesc) {
		this.problemReportProblemTypedesc = problemReportProblemTypedesc;
	}

	public String getProblemReportReplyFilepath() {
		return problemReportReplyFilepath;
	}

	public void setProblemReportReplyFilepath(String problemReportReplyFilepath) {
		this.problemReportReplyFilepath = problemReportReplyFilepath;
	}

	public String getProblemReportReplyFilename() {
		return problemReportReplyFilename;
	}

	public void setProblemReportReplyFilename(String problemReportReplyFilename) {
		this.problemReportReplyFilename = problemReportReplyFilename;
	}

	public String getProblemReportLastForwardedTo() {
		return problemReportLastForwardedTo;
	}

	public void setProblemReportLastForwardedTo(String problemReportLastForwardedTo) {
		this.problemReportLastForwardedTo = problemReportLastForwardedTo;
	}

	public String getProblemReportLastForwardedBy() {
		return problemReportLastForwardedBy;
	}

	public void setProblemReportLastForwardedBy(String problemReportLastForwardedBy) {
		this.problemReportLastForwardedBy = problemReportLastForwardedBy;
	}

	public Timestamp getProblemReportLastForwardedOn() {
		return problemReportLastForwardedOn;
	}

	public void setProblemReportLastForwardedOn(
			Timestamp problemReportLastForwardedOn) {
		this.problemReportLastForwardedOn = problemReportLastForwardedOn;
	}
	
	
}