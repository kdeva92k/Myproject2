package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_allergies_history")
public class PatientAllergiesHistory {

	@Column(name="patallerg_id")
	private Integer patallergId;

	@Column(name="patallerg_chartid")
	private Integer patallergChartid;

	@Column(name="patallerg_encounterid")
	private Integer patallergEncounterid;

	@Column(name="patallerg_typeid")
	private Integer patallergTypeid;

	@Column(name="patallerg_allergicto")
	private String patallergAllergicto;

	@Column(name="patallerg_reactionto")
	private String patallergReactionto;

	@Column(name="patallerg_onsetdate")
	private String patallergOnsetdate;

	@Column(name="patallerg_status")
	private Integer patallergStatus;

	@Column(name="patallerg_resolveddate")
	private String patallergResolveddate;

	@Column(name="patallerg_createdby")
	private String patallergCreatedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patallerg_createdon")
	private Timestamp patallergCreatedon;

	@Column(name="patallerg_modifiedby")
	private String patallergModifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patallerg_modifiedon")
	private Timestamp patallergModifiedon;

	@Column(name="patallerg_drugcategory")
	private String patallergDrugcategory;

	@Column(name="patallerg_severity")
	private Integer patallergSeverity;

	@Column(name="patallerg_allergy_code")
	private String patallergAllergyCode;

	@Column(name="patallerg_interactionxml")
	private String patallergInteractionxml;

	@Column(name="patallerg_resolvedby")
	private String patallergResolvedby;

	@Column(name="patallerg_inactiveby")
	private String patallergInactiveby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patallerg_inactiveon")
	private Timestamp patallergInactiveon;

	@Column(name="patallerg_inactive_reason")
	private String patallergInactiveReason;

	@Column(name="patallerg_uncoded_med")
	private Boolean patallergUncodedMed;

	@Column(name="patallerg_codesystem")
	private String patallergCodesystem;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_allergies_history_patallerg_history_id_seq")
	@SequenceGenerator(name ="patient_allergies_history_patallerg_history_id_seq", sequenceName="patient_allergies_history_patallerg_history_id_seq", allocationSize=1)
	@Column(name="patallerg_history_id")
	private Integer patallergHistoryId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patallerg_typeid", referencedColumnName="allergtype_id", insertable=false, updatable=false)
	AllergiesType allergiesType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula= @JoinFormula(value="patallerg_modifiedby::integer" , referencedColumnName="emp_profile_empid"))})
	@JsonManagedReference
	EmployeeProfile empProfileAllgModifiedByTable;


	public Integer getPatallergId() {
		return patallergId;
	}

	public void setPatallergId(Integer patallergId) {
		this.patallergId = patallergId;
	}

	public Integer getPatallergChartid() {
		return patallergChartid;
	}

	public void setPatallergChartid(Integer patallergChartid) {
		this.patallergChartid = patallergChartid;
	}

	public Integer getPatallergEncounterid() {
		return patallergEncounterid;
	}

	public void setPatallergEncounterid(Integer patallergEncounterid) {
		this.patallergEncounterid = patallergEncounterid;
	}

	public Integer getPatallergTypeid() {
		return patallergTypeid;
	}

	public void setPatallergTypeid(Integer patallergTypeid) {
		this.patallergTypeid = patallergTypeid;
	}

	public String getPatallergAllergicto() {
		return patallergAllergicto;
	}

	public void setPatallergAllergicto(String patallergAllergicto) {
		this.patallergAllergicto = patallergAllergicto;
	}

	public String getPatallergReactionto() {
		return patallergReactionto;
	}

	public void setPatallergReactionto(String patallergReactionto) {
		this.patallergReactionto = patallergReactionto;
	}

	public String getPatallergOnsetdate() {
		return patallergOnsetdate;
	}

	public void setPatallergOnsetdate(String patallergOnsetdate) {
		this.patallergOnsetdate = patallergOnsetdate;
	}

	public Integer getPatallergStatus() {
		return patallergStatus;
	}

	public void setPatallergStatus(Integer patallergStatus) {
		this.patallergStatus = patallergStatus;
	}

	public String getPatallergResolveddate() {
		return patallergResolveddate;
	}

	public void setPatallergResolveddate(String patallergResolveddate) {
		this.patallergResolveddate = patallergResolveddate;
	}

	public String getPatallergCreatedby() {
		return patallergCreatedby;
	}

	public void setPatallergCreatedby(String patallergCreatedby) {
		this.patallergCreatedby = patallergCreatedby;
	}

	public Timestamp getPatallergCreatedon() {
		return patallergCreatedon;
	}

	public void setPatallergCreatedon(Timestamp patallergCreatedon) {
		this.patallergCreatedon = patallergCreatedon;
	}

	public String getPatallergModifiedby() {
		return patallergModifiedby;
	}

	public void setPatallergModifiedby(String patallergModifiedby) {
		this.patallergModifiedby = patallergModifiedby;
	}

	public Timestamp getPatallergModifiedon() {
		return patallergModifiedon;
	}

	public void setPatallergModifiedon(Timestamp patallergModifiedon) {
		this.patallergModifiedon = patallergModifiedon;
	}

	public String getPatallergDrugcategory() {
		return patallergDrugcategory;
	}

	public void setPatallergDrugcategory(String patallergDrugcategory) {
		this.patallergDrugcategory = patallergDrugcategory;
	}

	public Integer getPatallergSeverity() {
		return patallergSeverity;
	}

	public void setPatallergSeverity(Integer patallergSeverity) {
		this.patallergSeverity = patallergSeverity;
	}

	public String getPatallergAllergyCode() {
		return patallergAllergyCode;
	}

	public void setPatallergAllergyCode(String patallergAllergyCode) {
		this.patallergAllergyCode = patallergAllergyCode;
	}

	public String getPatallergInteractionxml() {
		return patallergInteractionxml;
	}

	public void setPatallergInteractionxml(String patallergInteractionxml) {
		this.patallergInteractionxml = patallergInteractionxml;
	}

	public String getPatallergResolvedby() {
		return patallergResolvedby;
	}

	public void setPatallergResolvedby(String patallergResolvedby) {
		this.patallergResolvedby = patallergResolvedby;
	}

	public String getPatallergInactiveby() {
		return patallergInactiveby;
	}

	public void setPatallergInactiveby(String patallergInactiveby) {
		this.patallergInactiveby = patallergInactiveby;
	}

	public Timestamp getPatallergInactiveon() {
		return patallergInactiveon;
	}

	public void setPatallergInactiveon(Timestamp patallergInactiveon) {
		this.patallergInactiveon = patallergInactiveon;
	}

	public String getPatallergInactiveReason() {
		return patallergInactiveReason;
	}

	public void setPatallergInactiveReason(String patallergInactiveReason) {
		this.patallergInactiveReason = patallergInactiveReason;
	}

	public Boolean getPatallergUncodedMed() {
		return patallergUncodedMed;
	}

	public void setPatallergUncodedMed(Boolean patallergUncodedMed) {
		this.patallergUncodedMed = patallergUncodedMed;
	}

	public String getPatallergCodesystem() {
		return patallergCodesystem;
	}

	public void setPatallergCodesystem(String patallergCodesystem) {
		this.patallergCodesystem = patallergCodesystem;
	}

	public Integer getPatallergHistoryId() {
		return patallergHistoryId;
	}

	public void setPatallergHistoryId(Integer patallergHistoryId) {
		this.patallergHistoryId = patallergHistoryId;
	}

	public AllergiesType getAllergiesType() {
		return allergiesType;
	}

	public void setAllergiesType(AllergiesType allergiesType) {
		this.allergiesType = allergiesType;
	}

	public EmployeeProfile getEmpProfileAllgModifiedByTable() {
		return empProfileAllgModifiedByTable;
	}

	public void setEmpProfileAllgModifiedByTable(
			EmployeeProfile empProfileAllgModifiedByTable) {
		this.empProfileAllgModifiedByTable = empProfileAllgModifiedByTable;
	}
	
	
}