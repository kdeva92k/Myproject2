package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "hpi_symptom")
public class HpiSymptom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7683549919619723076L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hpi_symptom_hpi_symptom_id_seq")
	@SequenceGenerator(name ="hpi_symptom_hpi_symptom_id_seq", sequenceName="hpi_symptom_hpi_symptom_id_seq", allocationSize=1)
	@Column(name="hpi_symptom_id")
	private Integer hpiSymptomId;

	@Column(name="hpi_symptom_gwid")
	private String hpiSymptomGwid;

	@Column(name="hpi_symptom_system_id")
	private Integer hpiSymptomSystemId;

	@Column(name="hpi_symptom_name")
	private String hpiSymptomName;

	@Column(name="hpi_symptom_comments")
	private String hpiSymptomComments;

	@Column(name="hpi_symptom_type")
	private Integer hpiSymptomType;

	@Column(name="hpi_symptom_printtext")
	private String hpiSymptomPrinttext;

	@Column(name="hpi_symptom_hitcount")
	private Integer hpiSymptomHitcount;

	@Column(name="hpi_symptom_orderby")
	private Integer hpiSymptomOrderby;

	@Column(name="hpi_symptom_isactive")
	private Boolean hpiSymptomIsactive;

	@Column(name="hpi_symptom_retaincase")
	private Boolean hpiSymptomRetaincase;
	
	/*@OneToMany(mappedBy="hpiSymptom")
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "hpi_symptom_gwid", referencedColumnName = "clinical_elements_gwid", insertable = false, updatable = false)
	ClinicalElements clinicalElements;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "hpi_symptom_system_id", referencedColumnName = "clinical_system_id", insertable = false, updatable = false)
	ClinicalSystem clinicalSystem;
	
	@OneToMany(mappedBy="hpiSymptom")
	List<PatientClinicalElements> patientClinicalElements;
	
	@OneToMany(mappedBy="hpiSymptom")
	List<HpiSymptomQualifier> hpiSymptomQualifier;*/
	
	
	public Integer getHpiSymptomId() {
		return hpiSymptomId;
	}

	public void setHpiSymptomId(Integer hpiSymptomId) {
		this.hpiSymptomId = hpiSymptomId;
	}

	public String getHpiSymptomGwid() {
		return hpiSymptomGwid;
	}

	public void setHpiSymptomGwid(String hpiSymptomGwid) {
		this.hpiSymptomGwid = hpiSymptomGwid;
	}

	public Integer getHpiSymptomSystemId() {
		return hpiSymptomSystemId;
	}

	public void setHpiSymptomSystemId(Integer hpiSymptomSystemId) {
		this.hpiSymptomSystemId = hpiSymptomSystemId;
	}

	public String getHpiSymptomName() {
		return hpiSymptomName;
	}

	public void setHpiSymptomName(String hpiSymptomName) {
		this.hpiSymptomName = hpiSymptomName;
	}

	public String getHpiSymptomComments() {
		return hpiSymptomComments;
	}

	public void setHpiSymptomComments(String hpiSymptomComments) {
		this.hpiSymptomComments = hpiSymptomComments;
	}

	public Integer getHpiSymptomType() {
		return hpiSymptomType;
	}

	public void setHpiSymptomType(Integer hpiSymptomType) {
		this.hpiSymptomType = hpiSymptomType;
	}

	public String getHpiSymptomPrinttext() {
		return hpiSymptomPrinttext;
	}

	public void setHpiSymptomPrinttext(String hpiSymptomPrinttext) {
		this.hpiSymptomPrinttext = hpiSymptomPrinttext;
	}

	public Integer getHpiSymptomHitcount() {
		return hpiSymptomHitcount;
	}

	public void setHpiSymptomHitcount(Integer hpiSymptomHitcount) {
		this.hpiSymptomHitcount = hpiSymptomHitcount;
	}

	public Integer getHpiSymptomOrderby() {
		return hpiSymptomOrderby;
	}

	public void setHpiSymptomOrderby(Integer hpiSymptomOrderby) {
		this.hpiSymptomOrderby = hpiSymptomOrderby;
	}

	public Boolean getHpiSymptomIsactive() {
		return hpiSymptomIsactive;
	}

	public void setHpiSymptomIsactive(Boolean hpiSymptomIsactive) {
		this.hpiSymptomIsactive = hpiSymptomIsactive;
	}

	public Boolean getHpiSymptomRetaincase() {
		return hpiSymptomRetaincase;
	}

	public void setHpiSymptomRetaincase(Boolean hpiSymptomRetaincase) {
		this.hpiSymptomRetaincase = hpiSymptomRetaincase;
	}

	/*public List<ClinicalElementTemplateMapping> getClinicalElementTemplateMapping() {
		return clinicalElementTemplateMapping;
	}

	public void setClinicalElementTemplateMapping(
			List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping) {
		this.clinicalElementTemplateMapping = clinicalElementTemplateMapping;
	}

	public ClinicalElements getClinicalElements() {
		return clinicalElements;
	}

	public void setClinicalElements(ClinicalElements clinicalElements) {
		this.clinicalElements = clinicalElements;
	}

	public ClinicalSystem getClinicalSystem() {
		return clinicalSystem;
	}

	public void setClinicalSystem(ClinicalSystem clinicalSystem) {
		this.clinicalSystem = clinicalSystem;
	}

	public List<PatientClinicalElements> getPatientClinicalElements() {
		return patientClinicalElements;
	}

	public void setPatientClinicalElements(
			List<PatientClinicalElements> patientClinicalElements) {
		this.patientClinicalElements = patientClinicalElements;
	}

	public List<HpiSymptomQualifier> getHpiSymptomQualifier() {
		return hpiSymptomQualifier;
	}

	public void setHpiSymptomQualifier(List<HpiSymptomQualifier> hpiSymptomQualifier) {
		this.hpiSymptomQualifier = hpiSymptomQualifier;
	}*/

	
}