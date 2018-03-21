package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "clinical_elements")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicalElements implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="clinical_elements_id")
	@SequenceGenerator(name ="clinical_elements_clinical_elements_id_seq", sequenceName="clinical_elements_clinical_elements_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="clinical_elements_clinical_elements_id_seq")
	private Integer clinicalElementsId;

	@Id
	@Column(name="clinical_elements_gwid")
	private String clinicalElementsGwid;

	@Column(name="clinical_elements_name")
	private String clinicalElementsName;

	@Column(name="clinical_elements_notes")
	private String clinicalElementsNotes;

	@Column(name="clinical_elements_datatype")
	private Integer clinicalElementsDatatype;

	@Column(name="clinical_elements_cptcode")
	private String clinicalElementsCptcode;

	@Column(name="clinical_elements_icd9code")
	private String clinicalElementsIcd9code;

	@Column(name="clinical_elements_snomed")
	private String clinicalElementsSnomed;

	@Column(name="clinical_elements_isactive")
	private Boolean clinicalElementsIsactive;

	@Column(name="clinical_elements_isglobal")
	private Boolean clinicalElementsIsglobal;

	@Column(name="clinical_elements_ishistory")
	private Boolean clinicalElementsIshistory;

	@Column(name="clinical_elements_isepisode")
	private Boolean clinicalElementsIsepisode;

	@Column(name="clinical_elements_gender")
	private Integer clinicalElementsGender;

	@Column(name="clinical_elements_text_dimension")
	private String clinicalElementsTextDimension;

	@Column(name="clinical_elements_startage")
	private Integer clinicalElementsStartage;

	@Column(name="clinical_elements_endage")
	private Integer clinicalElementsEndage;

	@Column(name="clinical_elements_isselect")
	private Integer clinicalElementsIsselect;
	
	/*
	 * DO NOT MAKE THESE BIDIRECTIONAL RELATIONSHIPS IN CLINCIAL ELEMNETS EAGER
	 * 
	 * */
	
	@OneToMany(mappedBy="clinicalElement")
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping;
	
	@OneToMany(mappedBy="clinicalElement")
	List<ClinicalElementsCondition> clinicalElementsConditions;
	
	
	@OneToMany(mappedBy="clinicalElement")
	List<PatientClinicalHistory> patientClinicalHistory;
	
	
	@OneToMany(mappedBy="clinicalElement")
	@JsonManagedReference
	List<PatientClinicalElements> patientClinicalElements;
	
	
	@OneToMany(mappedBy="clinicalElement")
	List<ClinicalElementsOptions> clinicalElementsOptions;
	
	@OneToMany(mappedBy="clinicalElement")
	List<PatientVitals> patientVitals;
	
	
	@OneToMany(mappedBy="clinicalElements")
	List<ClinicalTextMapping> clinicalTextMappings;
	
	
	
	/*@OneToMany(mappedBy="clinicalElements")
	List<FocalShortcutElements> focalShortcutElements;
	
	@OneToMany(mappedBy="clinicalElements")
	List<HpiSymptom> hpiSymptom;*/
	
	/*
	 * DO NOT MAKE THESE BIDIRECTIONAL RELATIONSHIPS IN CLINCIAL ELEMNETS EAGER
	 * 
	 * */
	
	
	@OneToMany(mappedBy="clinicalElements")
	List<FocalShortcutElements> focalShortcutElements;
	
	@OneToMany(mappedBy="clinicalElements")
	List<TherapySessionPatientDetails> therupeticElements;
	
	public List<ClinicalElementTemplateMapping> getClinicalElementTemplateMapping() {
		return clinicalElementTemplateMapping;
	}

	public List<PatientClinicalHistory> getPatientClinicalHistory() {
		return patientClinicalHistory;
	}

	public void setPatientClinicalHistory(
			List<PatientClinicalHistory> patientClinicalHistory) {
		this.patientClinicalHistory = patientClinicalHistory;
	}

	public List<PatientClinicalElements> getPatientClinicalElements() {
		return patientClinicalElements;
	}

	public void setPatientClinicalElements(
			List<PatientClinicalElements> patientClinicalElements) {
		this.patientClinicalElements = patientClinicalElements;
	}

	public List<ClinicalElementsOptions> getClinicalElementsOptions() {
		return clinicalElementsOptions;
	}

	public void setClinicalElementsOptions(
			List<ClinicalElementsOptions> clinicalElementsOptions) {
		this.clinicalElementsOptions = clinicalElementsOptions;
	}

	public List<PatientVitals> getPatientVitals() {
		return patientVitals;
	}

	public void setPatientVitals(List<PatientVitals> patientVitals) {
		this.patientVitals = patientVitals;
	}

	/*public List<FocalShortcutElements> getFocalShortcutElements() {
		return focalShortcutElements;
	}

	public void setFocalShortcutElements(
			List<FocalShortcutElements> focalShortcutElements) {
		this.focalShortcutElements = focalShortcutElements;
	}*/

	public List<ClinicalTextMapping> getClinicalTextMappings() {
		return clinicalTextMappings;
	}

	public void setClinicalTextMappings(
			List<ClinicalTextMapping> clinicalTextMappings) {
		this.clinicalTextMappings = clinicalTextMappings;
	}

	public void setClinicalElementTemplateMapping(
			List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping) {
		this.clinicalElementTemplateMapping = clinicalElementTemplateMapping;
	}

	public List<ClinicalElementsCondition> getClinicalElementsConditions() {
		return clinicalElementsConditions;
	}

	public void setClinicalElementsConditions(
			List<ClinicalElementsCondition> clinicalElementsConditions) {
		this.clinicalElementsConditions = clinicalElementsConditions;
	}

	public Integer getClinicalElementsId() {
		return clinicalElementsId;
	}

	public void setClinicalElementsId(Integer clinicalElementsId) {
		this.clinicalElementsId = clinicalElementsId;
	}

	public String getClinicalElementsGwid() {
		return clinicalElementsGwid;
	}

	public void setClinicalElementsGwid(String clinicalElementsGwid) {
		this.clinicalElementsGwid = clinicalElementsGwid;
	}

	public String getClinicalElementsName() {
		return clinicalElementsName;
	}

	public void setClinicalElementsName(String clinicalElementsName) {
		this.clinicalElementsName = clinicalElementsName;
	}

	public String getClinicalElementsNotes() {
		return clinicalElementsNotes;
	}

	public void setClinicalElementsNotes(String clinicalElementsNotes) {
		this.clinicalElementsNotes = clinicalElementsNotes;
	}

	public Integer getClinicalElementsDatatype() {
		return clinicalElementsDatatype;
	}

	public void setClinicalElementsDatatype(Integer clinicalElementsDatatype) {
		this.clinicalElementsDatatype = clinicalElementsDatatype;
	}

	public String getClinicalElementsCptcode() {
		return clinicalElementsCptcode;
	}

	public void setClinicalElementsCptcode(String clinicalElementsCptcode) {
		this.clinicalElementsCptcode = clinicalElementsCptcode;
	}

	public String getClinicalElementsIcd9code() {
		return clinicalElementsIcd9code;
	}

	public void setClinicalElementsIcd9code(String clinicalElementsIcd9code) {
		this.clinicalElementsIcd9code = clinicalElementsIcd9code;
	}

	public String getClinicalElementsSnomed() {
		return clinicalElementsSnomed;
	}

	public void setClinicalElementsSnomed(String clinicalElementsSnomed) {
		this.clinicalElementsSnomed = clinicalElementsSnomed;
	}

	public Boolean getClinicalElementsIsactive() {
		return clinicalElementsIsactive;
	}

	public void setClinicalElementsIsactive(Boolean clinicalElementsIsactive) {
		this.clinicalElementsIsactive = clinicalElementsIsactive;
	}

	public Boolean getClinicalElementsIsglobal() {
		return clinicalElementsIsglobal;
	}

	public void setClinicalElementsIsglobal(Boolean clinicalElementsIsglobal) {
		this.clinicalElementsIsglobal = clinicalElementsIsglobal;
	}

	public Boolean getClinicalElementsIshistory() {
		return clinicalElementsIshistory;
	}

	public void setClinicalElementsIshistory(Boolean clinicalElementsIshistory) {
		this.clinicalElementsIshistory = clinicalElementsIshistory;
	}

	public Boolean getClinicalElementsIsepisode() {
		return clinicalElementsIsepisode;
	}

	public void setClinicalElementsIsepisode(Boolean clinicalElementsIsepisode) {
		this.clinicalElementsIsepisode = clinicalElementsIsepisode;
	}

	public Integer getClinicalElementsGender() {
		return clinicalElementsGender;
	}

	public void setClinicalElementsGender(Integer clinicalElementsGender) {
		this.clinicalElementsGender = clinicalElementsGender;
	}

	public String getClinicalElementsTextDimension() {
		return clinicalElementsTextDimension;
	}

	public void setClinicalElementsTextDimension(
			String clinicalElementsTextDimension) {
		this.clinicalElementsTextDimension = clinicalElementsTextDimension;
	}

	public Integer getClinicalElementsStartage() {
		return clinicalElementsStartage;
	}

	public void setClinicalElementsStartage(Integer clinicalElementsStartage) {
		this.clinicalElementsStartage = clinicalElementsStartage;
	}

	public Integer getClinicalElementsEndage() {
		return clinicalElementsEndage;
	}

	public void setClinicalElementsEndage(Integer clinicalElementsEndage) {
		this.clinicalElementsEndage = clinicalElementsEndage;
	}

	public Integer getClinicalElementsIsselect() {
		return clinicalElementsIsselect;
	}

	public void setClinicalElementsIsselect(Integer clinicalElementsIsselect) {
		this.clinicalElementsIsselect = clinicalElementsIsselect;
	}

	/*public List<HpiSymptom> getHpiSymptom() {
		return hpiSymptom;
	}

	public void setHpiSymptom(List<HpiSymptom> hpiSymptom) {
		this.hpiSymptom = hpiSymptom;
	}*/
	
	public List<FocalShortcutElements> getFocalShortcutElements() {
		return focalShortcutElements;
	}

	public void setFocalShortcutElements(
			List<FocalShortcutElements> focalShortcutElements) {
		this.focalShortcutElements = focalShortcutElements;
	}
	
	public List<TherapySessionPatientDetails> getTherupeticElements() {
		return therupeticElements;
	}

	public void setTherupeticElements(
			List<TherapySessionPatientDetails> therupeticElements) {
		this.therupeticElements = therupeticElements;
	}
	
	@OneToMany(mappedBy="clinicalElements")
    private List<CNMCodeSystem> cnmCodeSystems;
	
	public List<CNMCodeSystem> getCnmCodeSystems() {
        return cnmCodeSystems;
    }

    public void setCnmCodeSystems(List<CNMCodeSystem> cnmCodeSystems) {
        this.cnmCodeSystems = cnmCodeSystems;
    }
	
}