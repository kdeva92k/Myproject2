package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "drug_form")
public class DrugForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="drug_form_id")
	private Integer drugFormId;

	@Column(name="drug_form_name")
	private String drugFormName;

	@Column(name="drug_form_desc")
	private String drugFormDesc;

	@Column(name="drug_form_ss_form_code")
	private String drugFormSsFormCode;
	
	@Column(name="drug_form_ss_form_qualifier")
	private String drugFormSsFormQualifier;

	public String getDrugFormSsFormQualifier() {
		return drugFormSsFormQualifier;
	}

	public void setDrugFormSsFormQualifier(String drugFormSsFormQualifier) {
		this.drugFormSsFormQualifier = drugFormSsFormQualifier;
	}

	public Integer getDrugFormId() {
		return drugFormId;
	}

	public void setDrugFormId(Integer drugFormId) {
		this.drugFormId = drugFormId;
	}

	public String getDrugFormName() {
		return drugFormName;
	}

	public void setDrugFormName(String drugFormName) {
		this.drugFormName = drugFormName;
	}

	public String getDrugFormDesc() {
		return drugFormDesc;
	}

	public void setDrugFormDesc(String drugFormDesc) {
		this.drugFormDesc = drugFormDesc;
	}

	public String getDrugFormSsFormCode() {
		return drugFormSsFormCode;
	}

	public void setDrugFormSsFormCode(String drugFormSsFormCode) {
		this.drugFormSsFormCode = drugFormSsFormCode;
	}
}