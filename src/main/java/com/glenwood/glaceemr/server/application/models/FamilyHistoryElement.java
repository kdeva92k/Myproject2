package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "family_history_element")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FamilyHistoryElement implements Serializable{

	
	@Column(name="family_history_element_id")
	private Integer familyHistoryElementId;

	@Column(name="family_history_element_disease_id")
	private Integer familyHistoryElementDiseaseId;

	@Column(name="family_history_element_relationship_id")
	private Integer familyHistoryElementRelationshipId;

	@Id
	@Column(name="family_history_element_general_gwid")
	private String familyHistoryElementGeneralGwid;

	@Column(name="family_history_element_status_gwid")
	private String familyHistoryElementStatusGwid;

	@Column(name="family_history_element_notes_gwid")
	private String familyHistoryElementNotesGwid;

	@Column(name="family_history_element_isactive")
	private Boolean familyHistoryElementIsactive;

	@Column(name="family_history_element_printtext")
	private String familyHistoryElementPrinttext;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="family_history_element_disease_id", referencedColumnName="family_history_disease_id", insertable=false, updatable=false)
	@JsonManagedReference
	FamilyHistoryDisease familyHistoryDisease;
	
	
	/*@OneToMany(mappedBy="familyHistoryElement")
	List<PatientClinicalElements> patientClinicalElements;*/

	
	@OneToMany(mappedBy="familyHistoryElement")
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="family_history_element_relationship_id", referencedColumnName="family_history_relationship_id", insertable=false, updatable=false)
	@JsonManagedReference
	FamilyHistoryRelationship familyHistoryRelationship;


	
	
	public List<ClinicalElementTemplateMapping> getClinicalElementTemplateMapping() {
		return clinicalElementTemplateMapping;
	}


	public void setClinicalElementTemplateMapping(
			List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping) {
		this.clinicalElementTemplateMapping = clinicalElementTemplateMapping;
	}


	public Integer getFamilyHistoryElementId() {
		return familyHistoryElementId;
	}


	public Integer getFamilyHistoryElementDiseaseId() {
		return familyHistoryElementDiseaseId;
	}


	public Integer getFamilyHistoryElementRelationshipId() {
		return familyHistoryElementRelationshipId;
	}


	public String getFamilyHistoryElementGeneralGwid() {
		return familyHistoryElementGeneralGwid;
	}


	public String getFamilyHistoryElementStatusGwid() {
		return familyHistoryElementStatusGwid;
	}


	public String getFamilyHistoryElementNotesGwid() {
		return familyHistoryElementNotesGwid;
	}


	public Boolean getFamilyHistoryElementIsactive() {
		return familyHistoryElementIsactive;
	}


	public String getFamilyHistoryElementPrinttext() {
		return familyHistoryElementPrinttext;
	}


	public FamilyHistoryDisease getFamilyHistoryDisease() {
		return familyHistoryDisease;
	}


	/*public List<PatientClinicalElements> getPatientClinicalElements() {
		return patientClinicalElements;
	}*/


	public FamilyHistoryRelationship getFamilyHistoryRelationship() {
		return familyHistoryRelationship;
	}


	public void setFamilyHistoryElementId(Integer familyHistoryElementId) {
		this.familyHistoryElementId = familyHistoryElementId;
	}


	public void setFamilyHistoryElementDiseaseId(
			Integer familyHistoryElementDiseaseId) {
		this.familyHistoryElementDiseaseId = familyHistoryElementDiseaseId;
	}


	public void setFamilyHistoryElementRelationshipId(
			Integer familyHistoryElementRelationshipId) {
		this.familyHistoryElementRelationshipId = familyHistoryElementRelationshipId;
	}


	public void setFamilyHistoryElementGeneralGwid(
			String familyHistoryElementGeneralGwid) {
		this.familyHistoryElementGeneralGwid = familyHistoryElementGeneralGwid;
	}


	public void setFamilyHistoryElementStatusGwid(
			String familyHistoryElementStatusGwid) {
		this.familyHistoryElementStatusGwid = familyHistoryElementStatusGwid;
	}


	public void setFamilyHistoryElementNotesGwid(
			String familyHistoryElementNotesGwid) {
		this.familyHistoryElementNotesGwid = familyHistoryElementNotesGwid;
	}


	public void setFamilyHistoryElementIsactive(Boolean familyHistoryElementIsactive) {
		this.familyHistoryElementIsactive = familyHistoryElementIsactive;
	}


	public void setFamilyHistoryElementPrinttext(
			String familyHistoryElementPrinttext) {
		this.familyHistoryElementPrinttext = familyHistoryElementPrinttext;
	}


	public void setFamilyHistoryDisease(FamilyHistoryDisease familyHistoryDisease) {
		this.familyHistoryDisease = familyHistoryDisease;
	}


	/*public void setPatientClinicalElements(
			List<PatientClinicalElements> patientClinicalElements) {
		this.patientClinicalElements = patientClinicalElements;
	}*/


	public void setFamilyHistoryRelationship(
			FamilyHistoryRelationship familyHistoryRelationship) {
		this.familyHistoryRelationship = familyHistoryRelationship;
	}
	
	
	
	
}