package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
@Table(name = "patient_assessments")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientAssessments implements Serializable{
  
	private static final long serialVersionUID = 1L;

	public PatientAssessments(){
		
	}
	
	public PatientAssessments(Integer patient_assessments_id, String patient_assessments_dxcode, String patient_assessments_dxdescription, String patient_assessments_assessmentcomment, Integer patient_assessments_status, String patient_assessmentsCodingSystemid){
		this.patient_assessments_id= patient_assessments_id;
		this.patient_assessments_dxcode= patient_assessments_dxcode;
		this.patient_assessments_dxdescription= patient_assessments_dxdescription;
		this.patient_assessments_assessmentcomment= patient_assessments_assessmentcomment;
		this.patient_assessments_status= patient_assessments_status;
		this.patient_assessmentsCodingSystemid= patient_assessmentsCodingSystemid;
	}
	
	public PatientAssessments(Integer patient_assessments_id, Integer patient_assessments_encounterid, Integer patient_assessments_patientId,
			Date patient_assessments_encounterdate, String patient_assessments_dxcode, String patient_assessments_dxdescription, String patient_assessments_scribble,
			Boolean patient_assessments_isactive, Integer h555555, Integer patient_assessments_dxtype, Integer patient_assessments_dxorder,
			Date patient_assessments_createdon, Integer patient_assessments_userid, Date patient_assessments_lastmodifiedon,
			Integer patient_assessments_lastmodifiedby, String patient_assessments_assessmentcomment, Integer patient_assessments_status,
			String h611CodingSystemid, String assessmentDxcodesystem,
			String planNotes) {
		this.patient_assessments_id= patient_assessments_id;
		this.patient_assessments_encounterid= patient_assessments_encounterid;
		this.patient_assessments_patientId= patient_assessments_patientId;
		this.patient_assessments_encounterdate= patient_assessments_encounterdate!=null? new Timestamp(patient_assessments_encounterdate.getTime()): null;
		this.patient_assessments_dxcode= patient_assessments_dxcode;
		this.patient_assessments_dxdescription= patient_assessments_dxdescription;
		this.patient_assessments_scribble= patient_assessments_scribble;
		this.patient_assessments_isactive= patient_assessments_isactive;
		this.h555555= h555555;
		this.patient_assessments_dxtype= patient_assessments_dxtype;
		this.patient_assessments_dxorder= patient_assessments_dxorder;
		this.patient_assessments_createdon= patient_assessments_createdon!=null? new Timestamp(patient_assessments_createdon.getTime()): null;
		this.patient_assessments_userid= patient_assessments_userid;
		this.patient_assessments_lastmodifiedon= patient_assessments_lastmodifiedon!=null? new Timestamp(patient_assessments_lastmodifiedon.getTime()): null;
		this.patient_assessments_lastmodifiedby= patient_assessments_lastmodifiedby;
		this.patient_assessments_assessmentcomment= patient_assessments_assessmentcomment;
		this.patient_assessments_status= patient_assessments_status;
		this.patient_assessmentsCodingSystemid= patient_assessmentsCodingSystemid;
		this.assessmentDxcodesystem= assessmentDxcodesystem;
		this.planNotes= planNotes;
	}
	
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_assessments_patient_assessments_id_seq")
	@SequenceGenerator(name = "patient_assessments_patient_assessments_id_seq", sequenceName = "patient_assessments_patient_assessments_id_seq", allocationSize = 1)
	@Column(name="patient_assessments_id")
	private Integer patient_assessments_id;
	
	@Column(name="patient_assessments_encounterid")
	private Integer patient_assessments_encounterid;

	@Column(name="patient_assessments_patientId")
	private Integer patient_assessments_patientId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_assessments_encounterdate")
	private Timestamp patient_assessments_encounterdate;

	@Column(name="patient_assessments_dxcode")
	private String patient_assessments_dxcode;

	@Column(name="patient_assessments_dxdescription")
	private String patient_assessments_dxdescription;

	@Column(name="patient_assessments_scribble")
	private String patient_assessments_scribble;

	@Column(name="patient_assessments_isactive")
	private Boolean patient_assessments_isactive;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="patient_assessments_dxtype")
	private Integer patient_assessments_dxtype;

	@Column(name="patient_assessments_dxorder")
	private Integer patient_assessments_dxorder;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_assessments_createdon")
	private Timestamp patient_assessments_createdon;

	@Column(name="patient_assessments_userid")
	private Integer patient_assessments_userid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_assessments_lastmodifiedon")
	private Timestamp patient_assessments_lastmodifiedon;

	@Column(name="patient_assessments_lastmodifiedby")
	private Integer patient_assessments_lastmodifiedby;

	@Column(name="patient_assessments_assessmentcomment")
	private String patient_assessments_assessmentcomment;

	@Column(name="patient_assessments_status")
	private Integer patient_assessments_status;

	@Column(name="patient_assessments_coding_systemid")
	private String patient_assessmentsCodingSystemid;

	@Column(name="patient_assessments_dxcodesystem")
	private String assessmentDxcodesystem;
	
	@Column(name="plan_notes")
	private String planNotes;

	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name="patient_assessments_coding_systemid", referencedColumnName="coding_system_oid" , insertable=false, updatable=false)
	private CodingSystems codingsystemsTable;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="patient_assessments_encounterid", referencedColumnName="encounter_id" , insertable=false, updatable=false)
	Encounter encounter;
	
	public String getPlanNotes() {
		return planNotes;
	}

	public void setPlanNotes(String planNotes) {
		this.planNotes = planNotes;
	}

	public Integer getpatient_assessments_id() {
		return patient_assessments_id;
	}

	public Integer getpatient_assessments_encounterid() {
		return patient_assessments_encounterid;
	}

	public Integer getpatient_assessments_patientId() {
		return patient_assessments_patientId;
	}

	public Timestamp getpatient_assessments_encounterdate() {
		return patient_assessments_encounterdate;
	}

	public String getpatient_assessments_dxcode() {
		return patient_assessments_dxcode;
	}

	public String getpatient_assessments_dxdescription() {
		return patient_assessments_dxdescription;
	}

	public String getpatient_assessments_scribble() {
		return patient_assessments_scribble;
	}

	public Boolean getpatient_assessments_isactive() {
		return patient_assessments_isactive;
	}

	public Integer getH555555() {
		return h555555;
	}

	public Integer getpatient_assessments_dxtype() {
		return patient_assessments_dxtype;
	}

	public Integer getpatient_assessments_dxorder() {
		return patient_assessments_dxorder;
	}

	public Timestamp getpatient_assessments_createdon() {
		return patient_assessments_createdon;
	}

	public Integer getpatient_assessments_userid() {
		return patient_assessments_userid;
	}

	public Timestamp getpatient_assessments_lastmodifiedon() {
		return patient_assessments_lastmodifiedon;
	}

	public Integer getpatient_assessments_lastmodifiedby() {
		return patient_assessments_lastmodifiedby;
	}

	public String getpatient_assessments_assessmentcomment() {
		return patient_assessments_assessmentcomment;
	}

	public Integer getpatient_assessments_status() {
		return patient_assessments_status;
	}

	public String getpatient_assessmentsCodingSystemid() {
		return patient_assessmentsCodingSystemid;
	}

	public String getAssessmentDxcodesystem() {
		return assessmentDxcodesystem;
	}

	public void setpatient_assessments_id(Integer patient_assessments_id) {
		this.patient_assessments_id = patient_assessments_id;
	}

	public void setpatient_assessments_encounterid(Integer patient_assessments_encounterid) {
		this.patient_assessments_encounterid = patient_assessments_encounterid;
	}

	public void setpatient_assessments_patientId(Integer patient_assessments_patientId) {
		this.patient_assessments_patientId = patient_assessments_patientId;
	}

	public void setpatient_assessments_encounterdate(Timestamp patient_assessments_encounterdate) {
		this.patient_assessments_encounterdate = patient_assessments_encounterdate;
	}

	public void setpatient_assessments_dxcode(String patient_assessments_dxcode) {
		this.patient_assessments_dxcode = patient_assessments_dxcode;
	}

	public void setpatient_assessments_dxdescription(String patient_assessments_dxdescription) {
		this.patient_assessments_dxdescription = patient_assessments_dxdescription;
	}

	public void setpatient_assessments_scribble(String patient_assessments_scribble) {
		this.patient_assessments_scribble = patient_assessments_scribble;
	}

	public void setpatient_assessments_isactive(Boolean patient_assessments_isactive) {
		this.patient_assessments_isactive = patient_assessments_isactive;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public void setpatient_assessments_dxtype(Integer patient_assessments_dxtype) {
		this.patient_assessments_dxtype = patient_assessments_dxtype;
	}

	public void setpatient_assessments_dxorder(Integer patient_assessments_dxorder) {
		this.patient_assessments_dxorder = patient_assessments_dxorder;
	}

	public void setpatient_assessments_createdon(Timestamp patient_assessments_createdon) {
		this.patient_assessments_createdon = patient_assessments_createdon;
	}

	public void setpatient_assessments_userid(Integer patient_assessments_userid) {
		this.patient_assessments_userid = patient_assessments_userid;
	}

	public void setpatient_assessments_lastmodifiedon(Timestamp patient_assessments_lastmodifiedon) {
		this.patient_assessments_lastmodifiedon = patient_assessments_lastmodifiedon;
	}

	public void setpatient_assessments_lastmodifiedby(Integer patient_assessments_lastmodifiedby) {
		this.patient_assessments_lastmodifiedby = patient_assessments_lastmodifiedby;
	}

	public void setpatient_assessments_assessmentcomment(String patient_assessments_assessmentcomment) {
		this.patient_assessments_assessmentcomment = patient_assessments_assessmentcomment;
	}

	public void setpatient_assessments_status(Integer patient_assessments_status) {
		this.patient_assessments_status = patient_assessments_status;
	}

	public void setpatient_assessmentsCodingSystemid(String patient_assessmentsCodingSystemid) {
		this.patient_assessmentsCodingSystemid = patient_assessmentsCodingSystemid;
	}

	public void setAssessmentDxcodesystem(String assessmentDxcodesystem) {
		this.assessmentDxcodesystem = assessmentDxcodesystem;
	}
	
}