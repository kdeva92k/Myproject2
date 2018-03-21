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
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "brandname")
public class Brandname {

	public Integer getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(Integer brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

	@Id
	@Column(name="brand_code")
	private Integer brandCode;

	public String getBrandCaseDescription() {
		return brandCaseDescription;
	}

	public void setBrandCaseDescription(String brandCaseDescription) {
		this.brandCaseDescription = brandCaseDescription;
	}

	public NdcDrugBrandMap getNdcDrugBrandMap() {
		return ndcDrugBrandMap;
	}

	public void setNdcDrugBrandMap(NdcDrugBrandMap ndcDrugBrandMap) {
		this.ndcDrugBrandMap = ndcDrugBrandMap;
	}

	@Column(name="brand_description")
	private String brandDescription;
	
	@Column(name="brand_case_description")
	private String brandCaseDescription;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="brand_code",referencedColumnName="brand_code",insertable=false,updatable=false)
	@JsonManagedReference
	NdcDrugBrandMap ndcDrugBrandMap;
}