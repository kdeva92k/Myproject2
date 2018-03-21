package com.glenwood.glaceemr.server.application.services.chart.Allergies;

public class PatientAllergiesHistoryBean {
	Integer id;
	Integer status;
	Integer isActive;
	String type;
	String drugCategory;
	String allergen;
	String reaction;
	String onsetDate;
	String allergyStatus;
	String resolvedDate;
	String modifiedBy;
	String modifiedOn;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDrugCategory() {
		return drugCategory;
	}
	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}
	public String getAllergen() {
		return allergen;
	}
	public void setAllergen(String allergen) {
		this.allergen = allergen;
	}
	public String getReaction() {
		return reaction;
	}
	public void setReaction(String reaction) {
		this.reaction = reaction;
	}
	public String getOnsetDate() {
		return onsetDate;
	}
	public void setOnsetDate(String onsetDate) {
		this.onsetDate = onsetDate;
	}
	public String getAllergyStatus() {
		return allergyStatus;
	}
	public void setAllergyStatus(String allergyStatus) {
		this.allergyStatus = allergyStatus;
	}
	public String getResolvedDate() {
		return resolvedDate;
	}
	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
}
