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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "leaf_patient")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeafPatient {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaf_patient_leaf_tab_patient_id_seq")
	@SequenceGenerator(name = "leaf_patient_leaf_tab_patient_id_seq", sequenceName = "leaf_patient_leaf_tab_patient_id_seq", allocationSize = 1)
	@Column(name="leaf_patient_id")
	private Integer leafPatientId;

	@Column(name="leaf_patient_patient_id")
	private Integer leafPatientPatientId;

	@Column(name="leaf_patient_encounter_id")
	private Integer leafPatientEncounterId;

	@Column(name="leaf_patient_leaf_library_id")
	private Integer leafPatientLeafLibraryId;

	@Column(name="leaf_patient_created_by")
	private Integer leafPatientCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="leaf_patient_created_date")
	private Timestamp leafPatientCreatedDate;

	@Column(name="leaf_patient_last_modyby")
	private Integer leafPatientLastModyby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="leaf_patient_lastmoddate")
	private Timestamp leafPatientLastmoddate;

	@Column(name="leaf_patient_iscomplete")
	private Boolean leafPatientIscomplete;

	@Column(name="leaf_patient_sign_one")
	private String leafPatientSignOne;

	@Column(name="leaf_patient_isactive")
	private Boolean leafPatientIsactive;

	@Column(name="leaf_patient_isprinted")
	private Boolean leafPatientIsprinted;

	@Column(name="leaf_patient_isfaxed")
	private Boolean leafPatientIsfaxed;

	@Column(name="leaf_patient_sign_two")
	private Boolean leafPatientSignTwo;

	@Column(name="leaf_patient_sign_data")
	private String leafPatientSignData;

	@Column(name="leaf_patient_problemid")
	private Integer leafPatientProblemid;

	@Column(name="leaf_patient_sign_userid")
	private Integer leafPatientSignUserid;

	@Column(name="leaf_patient_multiple_sign")
	private String leafPatientMultipleSign;

	@Column(name="leaf_patient_ma_signed_userid")
	private Integer leafPatientMaSignedUserid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="leaf_patient_ma_signed_on")
	private Timestamp leafPatientMaSignedOn;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="leaf_patient_completed_on")
	private Timestamp leafPatientCompletedOn;

	@Column(name="leaf_patient_eandm_values")
	private String leafPatientEandmValues;

	@Column(name="leaf_patient_alert_id")
	private Integer leafPatientAlertId;

	@Column(name="leaf_patient_np_alert_id")
	private Integer leafPatientNpAlertId;

	@Column(name="leaf_patient_ccdetails")
	private String leafPatientCcdetails;

	@Column(name="leaf_patient_isfollowup")
	private Boolean leafPatientIsfollowup;

	@Column(name="leaf_patient_add_templates")
	private String leafPatientAddTemplates;

	@Column(name="leaf_patient_notes_stoptime")
	private String leafPatientNotesStoptime;

	@Column(name="leaf_patient_notes_starttime")
	private String leafPatientNotesStarttime;

	@Column(name="leaf_patient_plan_mode")
	private Boolean leafPatientPlanMode;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name="leaf_patient_leaf_library_id", referencedColumnName="leaf_library_id" , insertable=false, updatable=false)
	private LeafLibrary leafLibraryTable;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER) 
	@JsonManagedReference
	@JoinColumn(name="leaf_patient_encounter_id", referencedColumnName="encounter_id" , insertable=false, updatable=false)
	private Encounter encounterTable;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) 
	@JoinColumn(name="leaf_patient_problemid", referencedColumnName="problem_leaf_details_id" , insertable=false, updatable=false)
	private ProblemLeafDetails problem_leaf_details;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) 
	@JoinColumn(name="leaf_patient_created_by", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfile_createdBy;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) 
	@JoinColumn(name="leaf_patient_last_modyby", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfile_lastModifiedBy;
	
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) 
	@JoinColumn(name="leaf_patient_ma_signed_userid", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfile_ma_signed_userid;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) 
	@JoinColumn(name="leaf_patient_sign_userid", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfile_sign_userid;
	
	
	
	public EmployeeProfile getEmpProfile_sign_userid() {
		return empProfile_sign_userid;
	}

	public void setEmpProfile_sign_userid(EmployeeProfile empProfile_sign_userid) {
		this.empProfile_sign_userid = empProfile_sign_userid;
	}

	public ProblemLeafDetails getproblem_leaf_details() {
		return problem_leaf_details;
	}

	public void setproblem_leaf_details(ProblemLeafDetails problem_leaf_details) {
		this.problem_leaf_details = problem_leaf_details;
	}

	
	public EmployeeProfile getEmpProfile_createdBy() {
		return empProfile_createdBy;
	}

	public void setEmpProfile_createdBy(EmployeeProfile empProfile_createdBy) {
		this.empProfile_createdBy = empProfile_createdBy;
	}

	public EmployeeProfile getEmpProfile_lastModifiedBy() {
		return empProfile_lastModifiedBy;
	}

	public void setEmpProfile_lastModifiedBy(EmployeeProfile empProfile_lastModifiedBy) {
		this.empProfile_lastModifiedBy = empProfile_lastModifiedBy;
	}

	public EmployeeProfile getEmpProfile_ma_signed_userid() {
		return empProfile_ma_signed_userid;
	}

	public void setEmpProfile_ma_signed_userid(
			EmployeeProfile empProfile_ma_signed_userid) {
		this.empProfile_ma_signed_userid = empProfile_ma_signed_userid;
	}
	
	public Integer getLeafPatientId() {
		return leafPatientId;
	}

	public LeafLibrary getLeafLibraryTable() {
		return leafLibraryTable;
	}

	public void setLeafLibraryTable(LeafLibrary leafLibraryTable) {
		this.leafLibraryTable = leafLibraryTable;
	}

	public Encounter getEncounterTable() {
		return encounterTable;
	}

	public void setEncounterTable(Encounter encounterTable) {
		this.encounterTable = encounterTable;
	}

	public void setLeafPatientId(Integer leafPatientId) {
		this.leafPatientId = leafPatientId;
	}

	public Integer getLeafPatientPatientId() {
		return leafPatientPatientId;
	}

	public void setLeafPatientPatientId(Integer leafPatientPatientId) {
		this.leafPatientPatientId = leafPatientPatientId;
	}

	public Integer getLeafPatientEncounterId() {
		return leafPatientEncounterId;
	}

	public void setLeafPatientEncounterId(Integer leafPatientEncounterId) {
		this.leafPatientEncounterId = leafPatientEncounterId;
	}

	public Integer getLeafPatientLeafLibraryId() {
		return leafPatientLeafLibraryId;
	}

	public void setLeafPatientLeafLibraryId(Integer leafPatientLeafLibraryId) {
		this.leafPatientLeafLibraryId = leafPatientLeafLibraryId;
	}

	public Integer getLeafPatientCreatedBy() {
		return leafPatientCreatedBy;
	}

	public void setLeafPatientCreatedBy(Integer leafPatientCreatedBy) {
		this.leafPatientCreatedBy = leafPatientCreatedBy;
	}

	public Timestamp getLeafPatientCreatedDate() {
		return leafPatientCreatedDate;
	}

	public void setLeafPatientCreatedDate(Timestamp leafPatientCreatedDate) {
		this.leafPatientCreatedDate = leafPatientCreatedDate;
	}

	public Integer getLeafPatientLastModyby() {
		return leafPatientLastModyby;
	}

	public void setLeafPatientLastModyby(Integer leafPatientLastModyby) {
		this.leafPatientLastModyby = leafPatientLastModyby;
	}

	public Timestamp getLeafPatientLastmoddate() {
		return leafPatientLastmoddate;
	}

	public void setLeafPatientLastmoddate(Timestamp leafPatientLastmoddate) {
		this.leafPatientLastmoddate = leafPatientLastmoddate;
	}

	public Boolean getLeafPatientIscomplete() {
		return leafPatientIscomplete;
	}

	public void setLeafPatientIscomplete(Boolean leafPatientIscomplete) {
		this.leafPatientIscomplete = leafPatientIscomplete;
	}

	public String getLeafPatientSignOne() {
		return leafPatientSignOne;
	}

	public void setLeafPatientSignOne(String leafPatientSignOne) {
		this.leafPatientSignOne = leafPatientSignOne;
	}

	public Boolean getLeafPatientIsactive() {
		return leafPatientIsactive;
	}

	public void setLeafPatientIsactive(Boolean leafPatientIsactive) {
		this.leafPatientIsactive = leafPatientIsactive;
	}

	public Boolean getLeafPatientIsprinted() {
		return leafPatientIsprinted;
	}

	public void setLeafPatientIsprinted(Boolean leafPatientIsprinted) {
		this.leafPatientIsprinted = leafPatientIsprinted;
	}

	public Boolean getLeafPatientIsfaxed() {
		return leafPatientIsfaxed;
	}

	public void setLeafPatientIsfaxed(Boolean leafPatientIsfaxed) {
		this.leafPatientIsfaxed = leafPatientIsfaxed;
	}

	public Boolean getLeafPatientSignTwo() {
		return leafPatientSignTwo;
	}

	public void setLeafPatientSignTwo(Boolean leafPatientSignTwo) {
		this.leafPatientSignTwo = leafPatientSignTwo;
	}

	public String getLeafPatientSignData() {
		return leafPatientSignData;
	}

	public void setLeafPatientSignData(String leafPatientSignData) {
		this.leafPatientSignData = leafPatientSignData;
	}

	public Integer getLeafPatientProblemid() {
		return leafPatientProblemid;
	}

	public void setLeafPatientProblemid(Integer leafPatientProblemid) {
		this.leafPatientProblemid = leafPatientProblemid;
	}

	public Integer getLeafPatientSignUserid() {
		return leafPatientSignUserid;
	}

	public void setLeafPatientSignUserid(Integer leafPatientSignUserid) {
		this.leafPatientSignUserid = leafPatientSignUserid;
	}

	public String getLeafPatientMultipleSign() {
		return leafPatientMultipleSign;
	}

	public void setLeafPatientMultipleSign(String leafPatientMultipleSign) {
		this.leafPatientMultipleSign = leafPatientMultipleSign;
	}

	public Integer getLeafPatientMaSignedUserid() {
		return leafPatientMaSignedUserid;
	}

	public void setLeafPatientMaSignedUserid(Integer leafPatientMaSignedUserid) {
		this.leafPatientMaSignedUserid = leafPatientMaSignedUserid;
	}

	public Timestamp getLeafPatientMaSignedOn() {
		return leafPatientMaSignedOn;
	}

	public void setLeafPatientMaSignedOn(Timestamp leafPatientMaSignedOn) {
		this.leafPatientMaSignedOn = leafPatientMaSignedOn;
	}

	public Timestamp getLeafPatientCompletedOn() {
		return leafPatientCompletedOn;
	}

	public void setLeafPatientCompletedOn(Timestamp leafPatientCompletedOn) {
		this.leafPatientCompletedOn = leafPatientCompletedOn;
	}

	public String getLeafPatientEandmValues() {
		return leafPatientEandmValues;
	}

	public void setLeafPatientEandmValues(String leafPatientEandmValues) {
		this.leafPatientEandmValues = leafPatientEandmValues;
	}

	public Integer getLeafPatientAlertId() {
		return leafPatientAlertId;
	}

	public void setLeafPatientAlertId(Integer leafPatientAlertId) {
		this.leafPatientAlertId = leafPatientAlertId;
	}

	public Integer getLeafPatientNpAlertId() {
		return leafPatientNpAlertId;
	}

	public void setLeafPatientNpAlertId(Integer leafPatientNpAlertId) {
		this.leafPatientNpAlertId = leafPatientNpAlertId;
	}

	public String getLeafPatientCcdetails() {
		return leafPatientCcdetails;
	}

	public void setLeafPatientCcdetails(String leafPatientCcdetails) {
		this.leafPatientCcdetails = leafPatientCcdetails;
	}

	public Boolean getLeafPatientIsfollowup() {
		return leafPatientIsfollowup;
	}

	public void setLeafPatientIsfollowup(Boolean leafPatientIsfollowup) {
		this.leafPatientIsfollowup = leafPatientIsfollowup;
	}

	public String getLeafPatientAddTemplates() {
		return leafPatientAddTemplates;
	}

	public void setLeafPatientAddTemplates(String leafPatientAddTemplates) {
		this.leafPatientAddTemplates = leafPatientAddTemplates;
	}

	public String getLeafPatientNotesStoptime() {
		return leafPatientNotesStoptime;
	}

	public void setLeafPatientNotesStoptime(String leafPatientNotesStoptime) {
		this.leafPatientNotesStoptime = leafPatientNotesStoptime;
	}

	public String getLeafPatientNotesStarttime() {
		return leafPatientNotesStarttime;
	}

	public void setLeafPatientNotesStarttime(String leafPatientNotesStarttime) {
		this.leafPatientNotesStarttime = leafPatientNotesStarttime;
	}
	
	public Boolean getLeafPatientPlanMode() {
		return leafPatientPlanMode;
	}

	public void setLeafPatientPlanMode(Boolean leafPatientPlanMode) {
		this.leafPatientPlanMode = leafPatientPlanMode;
	}
}