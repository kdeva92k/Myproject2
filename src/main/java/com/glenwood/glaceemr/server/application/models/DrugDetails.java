package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "drug_details")
public class DrugDetails {

	@Id
	@Column(name="drug_details_id")
	private Integer drugDetailsId;

	@Column(name="drug_details_generic_id")
	private Integer drugDetailsGenericId;

	@Column(name="drug_details_category_id")
	private Integer drugDetailsCategoryId;

	@Column(name="drug_details_is_com_used")
	private Boolean drugDetailsIsComUsed;

	@Column(name="drug_details_last_modified_on")
	private Date drugDetailsLastModifiedOn;

	@Column(name="drug_details_last_modified_by")
	private Integer drugDetailsLastModifiedBy;

	@Column(name="drug_details_name")
	private String drugDetailsName;

	@Column(name="drug_details_desc")
	private String drugDetailsDesc;

	@Column(name="drug_details_generic_name")
	private String drugDetailsGenericName;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="drug_details_id",referencedColumnName="drug_details_rxnorm_map_drug_id",insertable=false,updatable=false)
	@JsonManagedReference
	DrugDetailsRxnormMap detailsRxnormMap;
	
	public Integer getDrugDetailsId() {
		return drugDetailsId;
	}

	public void setDrugDetailsId(Integer drugDetailsId) {
		this.drugDetailsId = drugDetailsId;
	}

	public Integer getDrugDetailsGenericId() {
		return drugDetailsGenericId;
	}

	public void setDrugDetailsGenericId(Integer drugDetailsGenericId) {
		this.drugDetailsGenericId = drugDetailsGenericId;
	}

	public Integer getDrugDetailsCategoryId() {
		return drugDetailsCategoryId;
	}

	public void setDrugDetailsCategoryId(Integer drugDetailsCategoryId) {
		this.drugDetailsCategoryId = drugDetailsCategoryId;
	}

	public Boolean getDrugDetailsIsComUsed() {
		return drugDetailsIsComUsed;
	}

	public void setDrugDetailsIsComUsed(Boolean drugDetailsIsComUsed) {
		this.drugDetailsIsComUsed = drugDetailsIsComUsed;
	}

	public Date getDrugDetailsLastModifiedOn() {
		return drugDetailsLastModifiedOn;
	}

	public void setDrugDetailsLastModifiedOn(Date drugDetailsLastModifiedOn) {
		this.drugDetailsLastModifiedOn = drugDetailsLastModifiedOn;
	}

	public Integer getDrugDetailsLastModifiedBy() {
		return drugDetailsLastModifiedBy;
	}

	public void setDrugDetailsLastModifiedBy(Integer drugDetailsLastModifiedBy) {
		this.drugDetailsLastModifiedBy = drugDetailsLastModifiedBy;
	}

	public String getDrugDetailsName() {
		return drugDetailsName;
	}

	public void setDrugDetailsName(String drugDetailsName) {
		this.drugDetailsName = drugDetailsName;
	}

	public String getDrugDetailsDesc() {
		return drugDetailsDesc;
	}

	public void setDrugDetailsDesc(String drugDetailsDesc) {
		this.drugDetailsDesc = drugDetailsDesc;
	}

	public String getDrugDetailsGenericName() {
		return drugDetailsGenericName;
	}

	public void setDrugDetailsGenericName(String drugDetailsGenericName) {
		this.drugDetailsGenericName = drugDetailsGenericName;
	}

	public DrugDetailsRxnormMap getDetailsRxnormMap() {
		return detailsRxnormMap;
	}

	public void setDetailsRxnormMap(DrugDetailsRxnormMap detailsRxnormMap) {
		this.detailsRxnormMap = detailsRxnormMap;
	}
}