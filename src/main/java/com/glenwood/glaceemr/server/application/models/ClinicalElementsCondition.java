package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clinical_elements_condition")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicalElementsCondition {

	@Id
	@Column(name="clinical_elements_condition_id")
	private Integer clinicalElementsConditionId;

	@Column(name="clinical_elements_condition_element_id")
	private Integer clinicalElementsConditionElementId;

	@Column(name="clinical_elements_condition_option_id")
	private Integer clinicalElementsConditionOptionId;

	@Column(name="clinical_elements_condition_startage")
	private Integer clinicalElementsConditionStartage;

	@Column(name="clinical_elements_condition_endage")
	private Integer clinicalElementsConditionEndage;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_elements_condition_element_id", referencedColumnName = "clinical_elements_id", insertable = false, updatable = false)
	private ClinicalElements clinicalElement;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_elements_condition_option_id", referencedColumnName = "clinical_elements_options_id", insertable = false, updatable = false)
	private ClinicalElementsOptions clinicalElementsOption;
	
	

	public Integer getClinicalElementsConditionId() {
		return clinicalElementsConditionId;
	}

	public void setClinicalElementsConditionId(Integer clinicalElementsConditionId) {
		this.clinicalElementsConditionId = clinicalElementsConditionId;
	}

	public Integer getClinicalElementsConditionElementId() {
		return clinicalElementsConditionElementId;
	}

	public void setClinicalElementsConditionElementId(
			Integer clinicalElementsConditionElementId) {
		this.clinicalElementsConditionElementId = clinicalElementsConditionElementId;
	}

	public Integer getClinicalElementsConditionOptionId() {
		return clinicalElementsConditionOptionId;
	}

	public void setClinicalElementsConditionOptionId(
			Integer clinicalElementsConditionOptionId) {
		this.clinicalElementsConditionOptionId = clinicalElementsConditionOptionId;
	}

	public Integer getClinicalElementsConditionStartage() {
		return clinicalElementsConditionStartage;
	}

	public void setClinicalElementsConditionStartage(
			Integer clinicalElementsConditionStartage) {
		this.clinicalElementsConditionStartage = clinicalElementsConditionStartage;
	}

	public Integer getClinicalElementsConditionEndage() {
		return clinicalElementsConditionEndage;
	}

	public void setClinicalElementsConditionEndage(
			Integer clinicalElementsConditionEndage) {
		this.clinicalElementsConditionEndage = clinicalElementsConditionEndage;
	}
	
	
}