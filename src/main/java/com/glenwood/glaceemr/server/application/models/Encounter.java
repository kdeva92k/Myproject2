package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "encounter")
public class Encounter implements Serializable {

	public Encounter(Integer encounterId, String encounterDate, Integer encounterPos, Long encounterRefDoctor, Long encounterServiceDoctor){
		this.medicationAttestationStatus= encounterDate;
		this.encounterId= encounterId;
		this.encounterPos= encounterPos;
		this.encounterRefDoctor= encounterRefDoctor;
		this.encounter_service_doctor= encounterServiceDoctor;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "encounter_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="encounter_id")
	private Integer encounterId;

	@Column(name="encounter_chartid")
	private Integer encounterChartid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="encounter_date")
	private Timestamp encounterDate;

	@Column(name="encounter_type")
	private Short encounterType;

	@Column(name="encounter_reason")
	private Integer encounterReason;

	@Column(name="encounter_service_doctor")
	private Long encounter_service_doctor;

	@Column(name="encounter_room")
	private Short encounterRoom;

	@Column(name="encounter_status")
	private Short encounterStatus;

	@Column(name="encounter_severity")
	private Short encounterSeverity;

	@Column(name="encounter_created_by")
	private Long encounterCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="encounter_created_date")
	private Timestamp encounterCreatedDate;

	@Column(name="encounter_closed_by")
	private Long encounterClosedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="encounter_closed_date")
	private Timestamp encounterClosedDate;

	@Column(name="encounter_chargeable")
	private Boolean encounterChargeable;

	@Column(name="encounter_already_seen")
	private Boolean encounterAlreadySeen;

	@Column(name="encounter_comment_scribble")
	private String encounterCommentScribble;

	@Column(name="encounter_response_scribble")
	private String encounterResponseScribble;

	@Column(name="encounter_ins_review")
	private Integer encounterInsReview;

	@Column(name="encounter_ins_review_comment")
	private String encounterInsReviewComment;

	@Column(name="encounter_med_review")
	private Integer encounterMedReview;

	@Column(name="encounter_user_login_id")
	private Integer encounterUserLoginId;

	@Column(name="encounter_alert_id")
	private Long encounterAlertId;

	@Column(name="encounter_pos")
	private Integer encounterPos;

	@Column(name="encounter_ref_doctor")
	private Long encounterRefDoctor;

	@Column(name="encounter_scribbledata")
	private String encounterScribbledata;

	@Column(name="encounter_chiefcomp")
	private String encounterChiefcomp;

	@Column(name="encounter_comments")
	private String encounterComments;

	@Column(name="encounter_doc_response")
	private String encounterDocResponse;

	@Column(name="encounter_access_status")
	private Boolean encounterAccessStatus;

	@Column(name="encounter_access_id")
	private String encounterAccessId;

	@Column(name="encounter_multireason_id")
	private String encounterMultireasonId;

	@Column(name="encounter_isinstruction")
	private Boolean encounterIsinstruction;

	@Column(name="encounter_visittype")
	private Short encounterVisittype;

	@Column(name="encounter_instructions_provided")
	private Boolean encounterInstructionsProvided;

	@Column(name="encounter_billingcomments")
	private String encounterBillingcomments;

	@Column(name="encounter_patientcomments")
	private String encounterPatientcomments;

	@Column(name="encounter_ass_provider")
	private String encounterAssProvider;

	@Column(name="encounter_modifiedby")
	private Integer encounterModifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="encounter_modifiedon")
	private Timestamp encounterModifiedon;

	@Column(name="encounter_chiefcomplaint_dirty")
	private Boolean encounterChiefcomplaintDirty;

	@Column(name="encounter_include_active_med_in_report")
	private Boolean encounterIncludeActiveMedInReport;

	@Column(name="encounter_patient_episodeid")
	private Integer encounterPatientEpisodeid;

	@Column(name="encounter_room_isactive")
	private Boolean encounterRoomIsactive;

	@Column(name="transition_of_care")
	private Boolean transitionOfCare;

	@Column(name="summary_of_care")
	private Boolean summaryOfCare;

	@Column(name="medication_attestation_status")
	private String medicationAttestationStatus;

	@Column(name="encounter_isportal")
	private Integer encounterIsportal;
	
	
	public Encounter(){
		super();
	}
	
	public Encounter( Date encounterDate, int encounterId,String empProfileFullname){
		try {
			if(encounterDate!=null)
			{
				this.encounterDate = (Timestamp)encounterDate;
			}
			this.empProfileEmpId = new EmployeeProfile();
			this.empProfileEmpId.setEmpProfileFullname(empProfileFullname);
			this.encounterId= encounterId;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	
	public Encounter(Long encounter_service_doctor,Date encounterDate,Integer encounterPos){
		Timestamp timeStamp = new Timestamp(encounterDate.getTime());
		this.encounter_service_doctor=encounter_service_doctor;
		this.encounterDate=timeStamp;
		this.encounterPos=encounterPos;
	}
	public Long getEncounter_service_doctor() {
		return encounter_service_doctor;
	}

	public void setEncounter_service_doctor(Long encounter_service_doctor) {
		this.encounter_service_doctor = encounter_service_doctor;
	}

	public EmployeeProfile getEmpProfileEmpId() {
		return empProfileEmpId;
	}

	public void setEmpProfileEmpId(EmployeeProfile empProfileEmpId) {
		this.empProfileEmpId = empProfileEmpId;
	}

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="encounter_service_doctor",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	EmployeeProfile empProfileEmpId;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="encounter_created_by",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	EmployeeProfile encounterCreatedByEmpProf;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="encounter_chartid",referencedColumnName="chart_id",insertable=false,updatable=false)
    Chart chart;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="encounter_visittype",referencedColumnName="categoryid",insertable=false,updatable=false)
    PatientEncounterType patientEncounterType;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="encounter_ref_doctor",referencedColumnName="referring_doctor_uniqueid",insertable=false,updatable=false)
	ReferringDoctor referringTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="encounter_room",referencedColumnName="room_details_id",insertable=false,updatable=false)
    Room room;
	
	public ReferringDoctor getReferringTable() {
		return referringTable;
	}

	
	public void setReferringTable(ReferringDoctor referringTable) {
		this.referringTable = referringTable;
	}

	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="encounter_chartid",referencedColumnName="chart_id",insertable=false,updatable=false)
    Chart encounterChartTable;*/
	
	@OneToMany(mappedBy="encounterTable")
	private List<LeafPatient> leafPatient;
		
	public List<LeafPatient> getLeafPatient() {
		return leafPatient;
	}

	public void setLeafPatient(List<LeafPatient> leafPatient) {
		this.leafPatient = leafPatient;
	}
	
	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public PatientEncounterType getPatientEncounterType() {
		return patientEncounterType;
	}

	public void setPatientEncounterType(PatientEncounterType patientEncounterType) {
		this.patientEncounterType = patientEncounterType;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getEncounterChartid() {
		return encounterChartid;
	}

	public void setEncounterChartid(Integer encounterChartid) {
		this.encounterChartid = encounterChartid;
	}

	public Timestamp getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Timestamp encounterDate) {
		this.encounterDate = encounterDate;
	}

	public Short getEncounterType() {
		return encounterType;
	}

	public void setEncounterType(Short encounterType) {
		this.encounterType = encounterType;
	}

	public Integer getEncounterReason() {
		return encounterReason;
	}

	public void setEncounterReason(Integer encounterReason) {
		this.encounterReason = encounterReason;
	}

	public Long getEncounterServiceDoctor() {
		return encounter_service_doctor;
	}

	public void setEncounterServiceDoctor(Long encounterServiceDoctor) {
		this.encounter_service_doctor = encounterServiceDoctor;
	}

	public Short getEncounterRoom() {
		return encounterRoom;
	}

	public void setEncounterRoom(Short encounterRoom) {
		this.encounterRoom = encounterRoom;
	}

	public Short getEncounterStatus() {
		return encounterStatus;
	}

	public void setEncounterStatus(Short encounterStatus) {
		this.encounterStatus = encounterStatus;
	}

	public Short getEncounterSeverity() {
		return encounterSeverity;
	}

	public void setEncounterSeverity(Short encounterSeverity) {
		this.encounterSeverity = encounterSeverity;
	}

	public Long getEncounterCreatedBy() {
		return encounterCreatedBy;
	}

	public void setEncounterCreatedBy(Long encounterCreatedBy) {
		this.encounterCreatedBy = encounterCreatedBy;
	}

	public Timestamp getEncounterCreatedDate() {
		return encounterCreatedDate;
	}

	public void setEncounterCreatedDate(Timestamp encounterCreatedDate) {
		this.encounterCreatedDate = encounterCreatedDate;
	}

	public Long getEncounterClosedBy() {
		return encounterClosedBy;
	}

	public void setEncounterClosedBy(Long encounterClosedBy) {
		this.encounterClosedBy = encounterClosedBy;
	}

	public Timestamp getEncounterClosedDate() {
		return encounterClosedDate;
	}

	public void setEncounterClosedDate(Timestamp encounterClosedDate) {
		this.encounterClosedDate = encounterClosedDate;
	}

	public Boolean getEncounterChargeable() {
		return encounterChargeable;
	}

	public void setEncounterChargeable(Boolean encounterChargeable) {
		this.encounterChargeable = encounterChargeable;
	}

	public Boolean getEncounterAlreadySeen() {
		return encounterAlreadySeen;
	}

	public void setEncounterAlreadySeen(Boolean encounterAlreadySeen) {
		this.encounterAlreadySeen = encounterAlreadySeen;
	}

	public String getEncounterCommentScribble() {
		return encounterCommentScribble;
	}

	public void setEncounterCommentScribble(String encounterCommentScribble) {
		this.encounterCommentScribble = encounterCommentScribble;
	}

	public String getEncounterResponseScribble() {
		return encounterResponseScribble;
	}

	public void setEncounterResponseScribble(String encounterResponseScribble) {
		this.encounterResponseScribble = encounterResponseScribble;
	}

	public Integer getEncounterInsReview() {
		return encounterInsReview;
	}

	public void setEncounterInsReview(Integer encounterInsReview) {
		this.encounterInsReview = encounterInsReview;
	}

	public String getEncounterInsReviewComment() {
		return encounterInsReviewComment;
	}

	public void setEncounterInsReviewComment(String encounterInsReviewComment) {
		this.encounterInsReviewComment = encounterInsReviewComment;
	}

	public Integer getEncounterMedReview() {
		return encounterMedReview;
	}

	public void setEncounterMedReview(Integer encounterMedReview) {
		this.encounterMedReview = encounterMedReview;
	}

	public Integer getEncounterUserLoginId() {
		return encounterUserLoginId;
	}

	public void setEncounterUserLoginId(Integer encounterUserLoginId) {
		this.encounterUserLoginId = encounterUserLoginId;
	}

	public Long getEncounterAlertId() {
		return encounterAlertId;
	}

	public void setEncounterAlertId(Long encounterAlertId) {
		this.encounterAlertId = encounterAlertId;
	}

	public Integer getEncounterPos() {
		return encounterPos;
	}

	public void setEncounterPos(Integer encounterPos) {
		this.encounterPos = encounterPos;
	}

	public Long getEncounterRefDoctor() {
		return encounterRefDoctor;
	}

	public void setEncounterRefDoctor(Long encounterRefDoctor) {
		this.encounterRefDoctor = encounterRefDoctor;
	}

	public String getEncounterScribbledata() {
		return encounterScribbledata;
	}

	public void setEncounterScribbledata(String encounterScribbledata) {
		this.encounterScribbledata = encounterScribbledata;
	}

	public String getEncounterChiefcomp() {
		return encounterChiefcomp;
	}

	public void setEncounterChiefcomp(String encounterChiefcomp) {
		this.encounterChiefcomp = encounterChiefcomp;
	}

	public String getEncounterComments() {
		return encounterComments;
	}

	public void setEncounterComments(String encounterComments) {
		this.encounterComments = encounterComments;
	}

	public String getEncounterDocResponse() {
		return encounterDocResponse;
	}

	public void setEncounterDocResponse(String encounterDocResponse) {
		this.encounterDocResponse = encounterDocResponse;
	}

	public Boolean getEncounterAccessStatus() {
		return encounterAccessStatus;
	}

	public void setEncounterAccessStatus(Boolean encounterAccessStatus) {
		this.encounterAccessStatus = encounterAccessStatus;
	}

	public String getEncounterAccessId() {
		return encounterAccessId;
	}

	public void setEncounterAccessId(String encounterAccessId) {
		this.encounterAccessId = encounterAccessId;
	}

	public String getEncounterMultireasonId() {
		return encounterMultireasonId;
	}

	public void setEncounterMultireasonId(String encounterMultireasonId) {
		this.encounterMultireasonId = encounterMultireasonId;
	}

	public Boolean getEncounterIsinstruction() {
		return encounterIsinstruction;
	}

	public void setEncounterIsinstruction(Boolean encounterIsinstruction) {
		this.encounterIsinstruction = encounterIsinstruction;
	}

	public Short getEncounterVisittype() {
		return encounterVisittype;
	}

	public void setEncounterVisittype(Short encounterVisittype) {
		this.encounterVisittype = encounterVisittype;
	}

	public Boolean getEncounterInstructionsProvided() {
		return encounterInstructionsProvided;
	}

	public void setEncounterInstructionsProvided(
			Boolean encounterInstructionsProvided) {
		this.encounterInstructionsProvided = encounterInstructionsProvided;
	}

	public String getEncounterBillingcomments() {
		return encounterBillingcomments;
	}

	public void setEncounterBillingcomments(String encounterBillingcomments) {
		this.encounterBillingcomments = encounterBillingcomments;
	}

	public String getEncounterPatientcomments() {
		return encounterPatientcomments;
	}

	public void setEncounterPatientcomments(String encounterPatientcomments) {
		this.encounterPatientcomments = encounterPatientcomments;
	}

	public String getEncounterAssProvider() {
		return encounterAssProvider;
	}

	public void setEncounterAssProvider(String encounterAssProvider) {
		this.encounterAssProvider = encounterAssProvider;
	}

	public Integer getEncounterModifiedby() {
		return encounterModifiedby;
	}

	public void setEncounterModifiedby(Integer encounterModifiedby) {
		this.encounterModifiedby = encounterModifiedby;
	}

	public Timestamp getEncounterModifiedon() {
		return encounterModifiedon;
	}

	public void setEncounterModifiedon(Timestamp encounterModifiedon) {
		this.encounterModifiedon = encounterModifiedon;
	}

	public Boolean getEncounterChiefcomplaintDirty() {
		return encounterChiefcomplaintDirty;
	}

	public void setEncounterChiefcomplaintDirty(Boolean encounterChiefcomplaintDirty) {
		this.encounterChiefcomplaintDirty = encounterChiefcomplaintDirty;
	}

	public Boolean getEncounterIncludeActiveMedInReport() {
		return encounterIncludeActiveMedInReport;
	}

	public void setEncounterIncludeActiveMedInReport(
			Boolean encounterIncludeActiveMedInReport) {
		this.encounterIncludeActiveMedInReport = encounterIncludeActiveMedInReport;
	}

	public Integer getEncounterPatientEpisodeid() {
		return encounterPatientEpisodeid;
	}

	public void setEncounterPatientEpisodeid(Integer encounterPatientEpisodeid) {
		this.encounterPatientEpisodeid = encounterPatientEpisodeid;
	}

	public Boolean getEncounterRoomIsactive() {
		return encounterRoomIsactive;
	}

	public void setEncounterRoomIsactive(Boolean encounterRoomIsactive) {
		this.encounterRoomIsactive = encounterRoomIsactive;
	}

	public Boolean getTransitionOfCare() {
		return transitionOfCare;
	}

	public void setTransitionOfCare(Boolean transitionOfCare) {
		this.transitionOfCare = transitionOfCare;
	}

	public Boolean getSummaryOfCare() {
		return summaryOfCare;
	}

	public void setSummaryOfCare(Boolean summaryOfCare) {
		this.summaryOfCare = summaryOfCare;
	}

	public String getMedicationAttestationStatus() {
		return medicationAttestationStatus;
	}

	public void setMedicationAttestationStatus(String medicationAttestationStatus) {
		this.medicationAttestationStatus = medicationAttestationStatus;
	}

	public Integer getEncounterIsportal() {
		return encounterIsportal;
	}

	public void setEncounterIsportal(Integer encounterIsportal) {
		this.encounterIsportal = encounterIsportal;
	}

	public EmployeeProfile getEncounterCreatedByEmpProf() {
		return encounterCreatedByEmpProf;
	}

	public void setEncounterCreatedByEmpProf(EmployeeProfile encounterCreatedByEmpProf) {
		this.encounterCreatedByEmpProf = encounterCreatedByEmpProf;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="encounter_chartid",referencedColumnName="chart_id",insertable=false,updatable=false)
    Chart chartTable;

	@OneToMany(mappedBy="encounter",fetch=FetchType.LAZY)
	List<PatientClinicalElements> patientClinicalElements;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="encounter_service_doctor", referencedColumnName="emp_profile_empid", insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile encounterEmployeeProfileTable;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="encounter_pos", referencedColumnName="pos_table_relation_id", insertable=false,updatable=false)
	@JsonManagedReference
	PosTable encounterPosTable;
	
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JoinColumn(name="encounter_patient_episodeid", referencedColumnName="admission_episode", insertable=false,updatable=false)
	Admission admission;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="encounter_pos", referencedColumnName="pos_table_relation_id", insertable=false, updatable=false)
	PosTable posTable;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="encounter_reason", referencedColumnName="room_details_id", insertable=false, updatable=false)
	RoomDetails room_details;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
	@JsonManagedReference
	@JoinColumn(name="encounter_patient_episodeid", referencedColumnName="patient_episode_id", insertable=false,updatable=false)
	PatientEpisode patientEpisode;
	
	
	public PosTable getPosTable() {
		return posTable;
	}

	public void setPosTable(PosTable posTable) {
		this.posTable = posTable;
	}

	public Chart getChartTable() {
		return chartTable;
	}

	public void setChartTable(Chart chartTable) {
		this.chartTable = chartTable;
	}
	
	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public EmployeeProfile getEncounterEmployeeProfileTable() {
		return encounterEmployeeProfileTable;
	}

	public void setEncounterEmployeeProfileTable(EmployeeProfile encounterEmployeeProfileTable) {
		this.encounterEmployeeProfileTable = encounterEmployeeProfileTable;
	}

	public PosTable getEncounterPosTable() {
		return encounterPosTable;
	}

	public void setEncounterPosTable(PosTable encounterPosTable) {
		this.encounterPosTable = encounterPosTable;
	}
	
	public List<PatientClinicalElements> getPatientClinicalElements() {
		return patientClinicalElements;
	}

	public void setPatientClinicalElements(
			List<PatientClinicalElements> patientClinicalElements) {
		this.patientClinicalElements = patientClinicalElements;
	}
	
}