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

@Entity
@Table(name = "patient_clinical_dx_elements")
public class PatientClinicalDxElements {

	
	public PatientClinicalDxElements() {
	}

	public PatientClinicalDxElements(Integer patientClinicalDxElementsMapid,
			Integer patientClinicalDxElementsPatientid,
			Integer patientClinicalDxElementsChartid,
			Integer patientClinicalDxElementsEncounterid,
			String patientClinicalDxElementsGwid,
			String patientClinicalDxElementsCode,
			String patientClinicalDxElementsCodesystem,
			String patientClinicalDxElementsValue) {
		super();
		this.patientClinicalDxElementsMapid = patientClinicalDxElementsMapid;
		this.patientClinicalDxElementsPatientid = patientClinicalDxElementsPatientid;
		this.patientClinicalDxElementsChartid = patientClinicalDxElementsChartid;
		this.patientClinicalDxElementsEncounterid = patientClinicalDxElementsEncounterid;
		this.patientClinicalDxElementsGwid = patientClinicalDxElementsGwid;
		this.patientClinicalDxElementsCode = patientClinicalDxElementsCode;
		this.patientClinicalDxElementsCodesystem = patientClinicalDxElementsCodesystem;
		this.patientClinicalDxElementsValue = patientClinicalDxElementsValue;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_clinical_dx_elements_patient_clinical_dx_elements_i_seq")
	@SequenceGenerator(name ="patient_clinical_dx_elements_patient_clinical_dx_elements_i_seq", sequenceName="patient_clinical_dx_elements_patient_clinical_dx_elements_i_seq", allocationSize=1)
	@Column(name="patient_clinical_dx_elements_id")
	private Integer patientClinicalDxElementsId;

	@Column(name="patient_clinical_dx_elements_mapid")
	private Integer patientClinicalDxElementsMapid;

	@Column(name="patient_clinical_dx_elements_patientid")
	private Integer patientClinicalDxElementsPatientid;

	@Column(name="patient_clinical_dx_elements_chartid")
	private Integer patientClinicalDxElementsChartid;

	@Column(name="patient_clinical_dx_elements_encounterid")
	private Integer patientClinicalDxElementsEncounterid;

	@Column(name="patient_clinical_dx_elements_gwid")
	private String patientClinicalDxElementsGwid;

	@Column(name="patient_clinical_dx_elements_code")
	private String patientClinicalDxElementsCode;

	@Column(name="patient_clinical_dx_elements_codesystem")
	private String patientClinicalDxElementsCodesystem;

	@Column(name="patient_clinical_dx_elements_value")
	private String patientClinicalDxElementsValue;
	
	@Column(name="patient_clinical_dx_elements_last_modified_by")
	private Integer patientClinicalDxElementsLastModifiedBy;
	
	@Column(name="patient_clinical_dx_elements_last_modified_on")
	private Timestamp patientClinicalDxElementsLastModifiedOn;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_clinical_dx_elements_gwid", referencedColumnName="plan_instruction_gwid", insertable=false, updatable=false)
	PlanInstruction planInstruction;
	
	public PlanInstruction getPlanInstruction() {
		return planInstruction;
	}

	public void setPlanInstruction(PlanInstruction planInstruction) {
		this.planInstruction = planInstruction;
	}

	public Integer getPatientClinicalDxElementsId() {
		return patientClinicalDxElementsId;
	}

	public void setPatientClinicalDxElementsId(Integer patientClinicalDxElementsId) {
		this.patientClinicalDxElementsId = patientClinicalDxElementsId;
	}

	public Integer getPatientClinicalDxElementsMapid() {
		return patientClinicalDxElementsMapid;
	}

	public void setPatientClinicalDxElementsMapid(
			Integer patientClinicalDxElementsMapid) {
		this.patientClinicalDxElementsMapid = patientClinicalDxElementsMapid;
	}

	public Integer getPatientClinicalDxElementsPatientid() {
		return patientClinicalDxElementsPatientid;
	}

	public void setPatientClinicalDxElementsPatientid(
			Integer patientClinicalDxElementsPatientid) {
		this.patientClinicalDxElementsPatientid = patientClinicalDxElementsPatientid;
	}

	public Integer getPatientClinicalDxElementsChartid() {
		return patientClinicalDxElementsChartid;
	}

	public void setPatientClinicalDxElementsChartid(
			Integer patientClinicalDxElementsChartid) {
		this.patientClinicalDxElementsChartid = patientClinicalDxElementsChartid;
	}

	public Integer getPatientClinicalDxElementsEncounterid() {
		return patientClinicalDxElementsEncounterid;
	}

	public void setPatientClinicalDxElementsEncounterid(
			Integer patientClinicalDxElementsEncounterid) {
		this.patientClinicalDxElementsEncounterid = patientClinicalDxElementsEncounterid;
	}

	public String getPatientClinicalDxElementsGwid() {
		return patientClinicalDxElementsGwid;
	}

	public void setPatientClinicalDxElementsGwid(
			String patientClinicalDxElementsGwid) {
		this.patientClinicalDxElementsGwid = patientClinicalDxElementsGwid;
	}

	public String getPatientClinicalDxElementsCode() {
		return patientClinicalDxElementsCode;
	}

	public void setPatientClinicalDxElementsCode(
			String patientClinicalDxElementsCode) {
		this.patientClinicalDxElementsCode = patientClinicalDxElementsCode;
	}

	public String getPatientClinicalDxElementsCodesystem() {
		return patientClinicalDxElementsCodesystem;
	}

	public void setPatientClinicalDxElementsCodesystem(
			String patientClinicalDxElementsCodesystem) {
		this.patientClinicalDxElementsCodesystem = patientClinicalDxElementsCodesystem;
	}

	public String getPatientClinicalDxElementsValue() {
		return patientClinicalDxElementsValue;
	}

	public void setPatientClinicalDxElementsValue(
			String patientClinicalDxElementsValue) {
		this.patientClinicalDxElementsValue = patientClinicalDxElementsValue;
	}

	public Integer getPatientClinicalDxElementsLastModifiedBy() {
		return patientClinicalDxElementsLastModifiedBy;
	}

	public void setPatientClinicalDxElementsLastModifiedBy(
			Integer patientClinicalDxElementsLastModifiedBy) {
		this.patientClinicalDxElementsLastModifiedBy = patientClinicalDxElementsLastModifiedBy;
	}

	public Timestamp getPatientClinicalDxElementsLastModifiedOn() {
		return patientClinicalDxElementsLastModifiedOn;
	}

	public void setPatientClinicalDxElementsLastModifiedOn(
			Timestamp patientClinicalDxElementsLastModifiedOn) {
		this.patientClinicalDxElementsLastModifiedOn = patientClinicalDxElementsLastModifiedOn;
	}
	
}