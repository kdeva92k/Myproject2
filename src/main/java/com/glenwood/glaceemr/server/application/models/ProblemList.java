package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "problem_list")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProblemList {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problem_list_problem_list_id_seq")
	@SequenceGenerator(name = "problem_list_problem_list_id_seq", sequenceName = "problem_list_problem_list_id_seq", allocationSize = 1)
	@Column(name="problem_list_id")
	private Integer problemListId;

	@Column(name="problem_list_patient_id")
	private Integer problemListPatientId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_list_last_mod_on")
	private Timestamp problemListLastModOn;

	@Column(name="problem_list_dx_code")
	private String problemListDxCode;

	@Column(name="problem_list_isactive")
	private Boolean problemListIsactive;

	@Column(name="h555555")
	private Short h555555;

	@Column(name="problem_list_onset_date")
	private Date problemListOnsetDate;

	@Column(name="problem_list_createdby")
	private Integer problemListCreatedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_list_createdon")
	private Timestamp problemListCreatedon;

	@Column(name="problem_list_unknown2")
	private Integer problemListUnknown2;

	@Column(name="problem_list_unknown1")
	private String problemListUnknown1;

	@Column(name="problem_list_ischronic")
	private Boolean problemListIschronic;

	@Column(name="problem_list_resolved_date")
	private Date problemListResolvedDate;

	@Column(name="problem_list_resolved_by")
	private Integer problemListResolvedBy;

	@Column(name="problem_list_dx_descp")
	private String problemListDxDescp;

	@Column(name="h063015")
	private Integer h063015;

	@Column(name="problem_list_chronicity")
	private Integer problemListChronicity;

	@Column(name="problem_list_isresolved")
	private Boolean problemListIsresolved;

	@Column(name="problem_list_comments")
	private String problemListComments;

	@Column(name="problem_list_inactive_date")
	private Date problemListInactiveDate;

	@Column(name="problem_list_inactivated_by")
	private Integer problemListInactivatedBy;

	@Column(name="problem_list_coding_systemid")
	private String problemListCodingSystemid;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="problem_list_coding_systemid",referencedColumnName="coding_system_oid",insertable=false,updatable=false)
	CodingSystems codingSystems;
	
	public Integer getProblemListId() {
		return problemListId;
	}

	public void setProblemListId(Integer problemListId) {
		this.problemListId = problemListId;
	}

	public Integer getProblemListPatientId() {
		return problemListPatientId;
	}

	public void setProblemListPatientId(Integer problemListPatientId) {
		this.problemListPatientId = problemListPatientId;
	}

	public Timestamp getProblemListLastModOn() {
		return problemListLastModOn;
	}

	public void setProblemListLastModOn(Timestamp problemListLastModOn) {
		this.problemListLastModOn = problemListLastModOn;
	}

	public String getProblemListDxCode() {
		return problemListDxCode;
	}

	public void setProblemListDxCode(String problemListDxCode) {
		this.problemListDxCode = problemListDxCode;
	}

	public Boolean getProblemListIsactive() {
		return problemListIsactive;
	}

	public void setProblemListIsactive(Boolean problemListIsactive) {
		this.problemListIsactive = problemListIsactive;
	}

	public Short getH555555() {
		return h555555;
	}

	public void setH555555(Short h555555) {
		this.h555555 = h555555;
	}

	public Date getProblemListOnsetDate() {
		return problemListOnsetDate;
	}

	public void setProblemListOnsetDate(Date problemListOnsetDate) {
		this.problemListOnsetDate = problemListOnsetDate;
	}

	public Integer getProblemListCreatedby() {
		return problemListCreatedby;
	}

	public void setProblemListCreatedby(Integer problemListCreatedby) {
		this.problemListCreatedby = problemListCreatedby;
	}

	public Timestamp getProblemListCreatedon() {
		return problemListCreatedon;
	}

	public void setProblemListCreatedon(Timestamp problemListCreatedon) {
		this.problemListCreatedon = problemListCreatedon;
	}

	public Integer getProblemListUnknown2() {
		return problemListUnknown2;
	}

	public void setProblemListUnknown2(Integer problemListUnknown2) {
		this.problemListUnknown2 = problemListUnknown2;
	}

	public String getProblemListUnknown1() {
		return problemListUnknown1;
	}

	public void setProblemListUnknown1(String problemListUnknown1) {
		this.problemListUnknown1 = problemListUnknown1;
	}

	public Boolean getProblemListIschronic() {
		return problemListIschronic;
	}

	public void setProblemListIschronic(Boolean problemListIschronic) {
		this.problemListIschronic = problemListIschronic;
	}

	public Date getProblemListResolvedDate() {
		return problemListResolvedDate;
	}

	public void setProblemListResolvedDate(Date problemListResolvedDate) {
		this.problemListResolvedDate = problemListResolvedDate;
	}

	public Integer getProblemListResolvedBy() {
		return problemListResolvedBy;
	}

	public void setProblemListResolvedBy(Integer problemListResolvedBy) {
		this.problemListResolvedBy = problemListResolvedBy;
	}

	public String getProblemListDxDescp() {
		return problemListDxDescp;
	}

	public void setProblemListDxDescp(String problemListDxDescp) {
		this.problemListDxDescp = problemListDxDescp;
	}

	public Integer getH063015() {
		return h063015;
	}

	public void setH063015(Integer h063015) {
		this.h063015 = h063015;
	}

	public Integer getProblemListChronicity() {
		return problemListChronicity;
	}

	public void setProblemListChronicity(Integer problemListChronicity) {
		this.problemListChronicity = problemListChronicity;
	}

	public Boolean getProblemListIsresolved() {
		return problemListIsresolved;
	}

	public void setProblemListIsresolved(Boolean problemListIsresolved) {
		this.problemListIsresolved = problemListIsresolved;
	}

	public String getProblemListComments() {
		return problemListComments;
	}

	public void setProblemListComments(String problemListComments) {
		this.problemListComments = problemListComments;
	}

	public Date getProblemListInactiveDate() {
		return problemListInactiveDate;
	}

	public void setProblemListInactiveDate(Date problemListInactiveDate) {
		this.problemListInactiveDate = problemListInactiveDate;
	}

	public Integer getProblemListInactivatedBy() {
		return problemListInactivatedBy;
	}

	public void setProblemListInactivatedBy(Integer problemListInactivatedBy) {
		this.problemListInactivatedBy = problemListInactivatedBy;
	}
	public String getProblemListCodingSystemid() {
		return problemListCodingSystemid;
	}

	public void setProblemListCodingSystemid(String problemListCodingSystemid) {
		this.problemListCodingSystemid = problemListCodingSystemid;
	}	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="problem_list_patient_id", referencedColumnName="patient_registration_id", insertable=false, updatable=false)
	@JsonBackReference
	PatientRegistration patientRegistration1;
	
}