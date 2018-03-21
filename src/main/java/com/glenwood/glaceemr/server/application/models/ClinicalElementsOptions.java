package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clinical_elements_options")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicalElementsOptions {

	@Id
	@Column(name="clinical_elements_options_id", nullable=false)
	@SequenceGenerator(name ="clinical_elements_options_clinical_elements_options_id_seq", sequenceName="clinical_elements_options_clinical_elements_options_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="clinical_elements_options_clinical_elements_options_id_seq")
	private Integer clinicalElementsOptionsId;

	@Column(name="clinical_elements_options_gwid", length=20)
	private String clinicalElementsOptionsGwid;

	@Column(name="clinical_elements_options_value", length=50)
	private String clinicalElementsOptionsValue;

	@Column(name="clinical_elements_options_name", length=200)
	private String clinicalElementsOptionsName;

	@Column(name="clinical_elements_options_active")
	private Boolean clinicalElementsOptionsActive;

	@Column(name="clinical_elements_options_order_by")
	private Integer clinicalElementsOptionsOrderBy;

	@Column(name="clinical_elements_options_snomed", length=15)
	private String clinicalElementsOptionsSnomed;

	@Column(name="clinical_elements_options_retaincase")
	private Boolean clinicalElementsOptionsRetaincase;

	@Column(name="clinical_elements_options_ishistory")
	private Boolean clinicalElementsOptionsIshistory;

	@Column(name="clinical_elements_options_gender")
	private Integer clinicalElementsOptionsGender;

	@Column(name="clinical_elements_options_startage")
	private Integer clinicalElementsOptionsStartage;
	
	@Column(name="clinical_elements_options_endage")
	private Integer clinicalElementsOptionsEndage;
	

	@OneToMany(mappedBy="clinicalElementsOption")
	List<ClinicalElementsCondition> clinicalElementsConditions; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_elements_options_gwid", referencedColumnName = "clinical_elements_gwid", insertable = false, updatable = false)
	ClinicalElements clinicalElement;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_elements_options_gwid", referencedColumnName = "pe_system_deferred_gwid", insertable = false, updatable = false)
	PeSystem peSystem;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_elements_options_value", referencedColumnName = "patient_clinical_elements_value", insertable = false, updatable = false)
	PatientClinicalElements patientClinicalElements;*/
	
	
	public Integer getClinicalElementsOptionsId() {
		return clinicalElementsOptionsId;
	}

	public void setClinicalElementsOptionsId(Integer clinicalElementsOptionsId) {
		this.clinicalElementsOptionsId = clinicalElementsOptionsId;
	}

	public String getClinicalElementsOptionsGwid() {
		return clinicalElementsOptionsGwid;
	}

	public void setClinicalElementsOptionsGwid(String clinicalElementsOptionsGwid) {
		this.clinicalElementsOptionsGwid = clinicalElementsOptionsGwid;
	}

	public String getClinicalElementsOptionsValue() {
		return clinicalElementsOptionsValue;
	}

	public void setClinicalElementsOptionsValue(String clinicalElementsOptionsValue) {
		this.clinicalElementsOptionsValue = clinicalElementsOptionsValue;
	}

	public String getClinicalElementsOptionsName() {
		return clinicalElementsOptionsName;
	}

	public void setClinicalElementsOptionsName(String clinicalElementsOptionsName) {
		this.clinicalElementsOptionsName = clinicalElementsOptionsName;
	}

	public Boolean getClinicalElementsOptionsActive() {
		return clinicalElementsOptionsActive;
	}

	public void setClinicalElementsOptionsActive(
			Boolean clinicalElementsOptionsActive) {
		this.clinicalElementsOptionsActive = clinicalElementsOptionsActive;
	}

	public Integer getClinicalElementsOptionsOrderBy() {
		return clinicalElementsOptionsOrderBy;
	}

	public void setClinicalElementsOptionsOrderBy(
			Integer clinicalElementsOptionsOrderBy) {
		this.clinicalElementsOptionsOrderBy = clinicalElementsOptionsOrderBy;
	}

	public String getClinicalElementsOptionsSnomed() {
		return clinicalElementsOptionsSnomed;
	}

	public void setClinicalElementsOptionsSnomed(
			String clinicalElementsOptionsSnomed) {
		this.clinicalElementsOptionsSnomed = clinicalElementsOptionsSnomed;
	}

	public Boolean getClinicalElementsOptionsRetaincase() {
		return clinicalElementsOptionsRetaincase;
	}

	public void setClinicalElementsOptionsRetaincase(
			Boolean clinicalElementsOptionsRetaincase) {
		this.clinicalElementsOptionsRetaincase = clinicalElementsOptionsRetaincase;
	}

	public Boolean getClinicalElementsOptionsIshistory() {
		return clinicalElementsOptionsIshistory;
	}

	public void setClinicalElementsOptionsIshistory(
			Boolean clinicalElementsOptionsIshistory) {
		this.clinicalElementsOptionsIshistory = clinicalElementsOptionsIshistory;
	}

	public Integer getClinicalElementsOptionsGender() {
		return clinicalElementsOptionsGender;
	}

	public void setClinicalElementsOptionsGender(
			Integer clinicalElementsOptionsGender) {
		this.clinicalElementsOptionsGender = clinicalElementsOptionsGender;
	}

	public Integer getClinicalElementsOptionsStartage() {
		return clinicalElementsOptionsStartage;
	}

	public void setClinicalElementsOptionsStartage(
			Integer clinicalElementsOptionsStartage) {
		this.clinicalElementsOptionsStartage = clinicalElementsOptionsStartage;
	}

	public Integer getClinicalElementsOptionsEndage() {
		return clinicalElementsOptionsEndage;
	}

	public void setClinicalElementsOptionsEndage(
			Integer clinicalElementsOptionsEndage) {
		this.clinicalElementsOptionsEndage = clinicalElementsOptionsEndage;
	}
	
	public List<ClinicalElementsCondition> getClinicalElementsConditions() {
		return clinicalElementsConditions;
	}

	public void setClinicalElementsConditions(
			List<ClinicalElementsCondition> clinicalElementsConditions) {
		this.clinicalElementsConditions = clinicalElementsConditions;
	}

	public ClinicalElements getClinicalElement() {
		return clinicalElement;
	}

	public void setClinicalElement(ClinicalElements clinicalElement) {
		this.clinicalElement = clinicalElement;
	}

	public PeSystem getPeSystem() {
		return peSystem;
	}

	public void setPeSystem(PeSystem peSystem) {
		this.peSystem = peSystem;
	}

	/*public PatientClinicalElements getPatientClinicalElements() {
		return patientClinicalElements;
	}

	public void setPatientClinicalElements(
			PatientClinicalElements patientClinicalElements) {
		this.patientClinicalElements = patientClinicalElements;
	}*/
}