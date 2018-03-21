package com.glenwood.glaceemr.server.application.models;

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
@Table(name = "surgical_history_element_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgicalHistoryElementDetails {

	
	@Column(name="surgical_history_element_details_id")
	private Integer surgicalHistoryElementDetailsId;

	@Column(name="surgical_history_element_details_element_id")
	private Integer surgicalHistoryElementDetailsElementId;

	@Id
	@Column(name="surgical_history_element_details_gwid")
	private String surgicalHistoryElementDetailsGwid;

	@Column(name="surgical_history_element_details_location_type")
	private Integer surgicalHistoryElementDetailsLocationType;

	@Column(name="surgical_history_element_details_isactive")
	private Boolean surgicalHistoryElementDetailsIsactive;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="surgical_history_element_details_element_id", referencedColumnName="surgical_history_element_id", insertable=false, updatable=false)
	@JsonManagedReference
	SurgicalHistoryElement surgicalHistoryElement;

	@OneToMany(mappedBy="surgicalHistoryElementDetails")
    List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="surgical_history_element_details_gwid", referencedColumnName="clinical_elements_gwid", insertable=false, updatable=false)
	@JsonManagedReference
	ClinicalElements clinicalElements;

	
	
	public ClinicalElements getClinicalElements() {
		return clinicalElements;
	}


	public void setClinicalElements(ClinicalElements clinicalElements) {
		this.clinicalElements = clinicalElements;
	}


	public List<ClinicalElementTemplateMapping> getClinicalElementTemplateMapping() {
		return clinicalElementTemplateMapping;
	}


	public void setClinicalElementTemplateMapping(
			List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping) {
		this.clinicalElementTemplateMapping = clinicalElementTemplateMapping;
	}


	public Integer getSurgicalHistoryElementDetailsId() {
		return surgicalHistoryElementDetailsId;
	}


	public Integer getSurgicalHistoryElementDetailsElementId() {
		return surgicalHistoryElementDetailsElementId;
	}


	public String getSurgicalHistoryElementDetailsGwid() {
		return surgicalHistoryElementDetailsGwid;
	}


	public Integer getSurgicalHistoryElementDetailsLocationType() {
		return surgicalHistoryElementDetailsLocationType;
	}


	public Boolean getSurgicalHistoryElementDetailsIsactive() {
		return surgicalHistoryElementDetailsIsactive;
	}


	public SurgicalHistoryElement getSurgicalHistoryElement() {
		return surgicalHistoryElement;
	}


	public void setSurgicalHistoryElementDetailsId(
			Integer surgicalHistoryElementDetailsId) {
		this.surgicalHistoryElementDetailsId = surgicalHistoryElementDetailsId;
	}


	public void setSurgicalHistoryElementDetailsElementId(
			Integer surgicalHistoryElementDetailsElementId) {
		this.surgicalHistoryElementDetailsElementId = surgicalHistoryElementDetailsElementId;
	}


	public void setSurgicalHistoryElementDetailsGwid(
			String surgicalHistoryElementDetailsGwid) {
		this.surgicalHistoryElementDetailsGwid = surgicalHistoryElementDetailsGwid;
	}


	public void setSurgicalHistoryElementDetailsLocationType(
			Integer surgicalHistoryElementDetailsLocationType) {
		this.surgicalHistoryElementDetailsLocationType = surgicalHistoryElementDetailsLocationType;
	}


	public void setSurgicalHistoryElementDetailsIsactive(
			Boolean surgicalHistoryElementDetailsIsactive) {
		this.surgicalHistoryElementDetailsIsactive = surgicalHistoryElementDetailsIsactive;
	}


	public void setSurgicalHistoryElement(
			SurgicalHistoryElement surgicalHistoryElement) {
		this.surgicalHistoryElement = surgicalHistoryElement;
	}
	
	
	
}