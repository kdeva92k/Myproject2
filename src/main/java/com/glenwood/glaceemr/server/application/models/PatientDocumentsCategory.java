package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "patient_doc_category")
public class PatientDocumentsCategory {

	@Id
	@Column(name="patient_doc_category_id")
	private Integer patientDocCategoryId;

	@Column(name="patient_doc_category_name")
	private String patientDocCategoryName;

	@Column(name="patient_doc_category_priority")
	private Integer patientDocCategoryPriority;

	@Column(name="patient_doc_category_order")
	private Short patientDocCategoryOrder;
	
	@Column(name="patient_doc_category_isactive")
	private Boolean patientDocCategoryIsactive;

	@Column(name="patient_doc_category_isbilling")
	private Boolean patientDocCategoryIsbilling;

	@Column(name="isadvanced_dir_category")
	private Boolean isadvancedDirCategory;
	
	
	public Integer getPatientDocCategoryId() {
		return patientDocCategoryId;
	}

	public String getPatientDocCategoryName() {
		return patientDocCategoryName;
	}

	public Integer getPatientDocCategoryPriority() {
		return patientDocCategoryPriority;
	}

	public Short getPatientDocCategoryOrder() {
		return patientDocCategoryOrder;
	}

	public Boolean getPatientDocCategoryIsactive() {
		return patientDocCategoryIsactive;
	}

	public Boolean getPatientDocCategoryIsbilling() {
		return patientDocCategoryIsbilling;
	}
	
	public Boolean getIsadvancedDirCategory() {
		return isadvancedDirCategory;
	}

	public void setPatientDocCategoryId(Integer patientDocCategoryId) {
		this.patientDocCategoryId = patientDocCategoryId;
	}

	public void setPatientDocCategoryName(String patientDocCategoryName) {
		this.patientDocCategoryName = patientDocCategoryName;
	}

	public void setPatientDocCategoryPriority(Integer patientDocCategoryPriority) {
		this.patientDocCategoryPriority = patientDocCategoryPriority;
	}

	public void setPatientDocCategoryOrder(Short patientDocCategoryOrder) {
		this.patientDocCategoryOrder = patientDocCategoryOrder;
	}

	public void setPatientDocCategoryIsactive(Boolean patientDocCategoryIsactive) {
		this.patientDocCategoryIsactive = patientDocCategoryIsactive;
	}

	public void setPatientDocCategoryIsbilling(Boolean patientDocCategoryIsbilling) {
		this.patientDocCategoryIsbilling = patientDocCategoryIsbilling;
	}
	
	public void setIsadvancedDirCategory(Boolean isadvancedDirCategory) {
		this.isadvancedDirCategory = isadvancedDirCategory;
	}
	
	
}