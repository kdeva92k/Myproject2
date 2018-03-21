package com.glenwood.glaceemr.server.application.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "drug_combination")
public class DrugCombination {

	@Id
	@Column(name="drug_combination_id")
	private Integer drugCombinationId;

	@Column(name="drug_combination_ndc_code")
	private String drugCombinationNdcCode;

	@Column(name="drug_combination_relation_id")
	private Integer drugCombinationRelationId;

	@Column(name="drug_combination_brand_code")
	private Integer drugCombinationBrandCode;

	@Column(name="drug_combination_specific_id")
	private Integer drugCombinationSpecificId;

	public Integer getDrugCombinationId() {
		return drugCombinationId;
	}

	public void setDrugCombinationId(Integer drugCombinationId) {
		this.drugCombinationId = drugCombinationId;
	}

	public String getDrugCombinationNdcCode() {
		return drugCombinationNdcCode;
	}

	public void setDrugCombinationNdcCode(String drugCombinationNdcCode) {
		this.drugCombinationNdcCode = drugCombinationNdcCode;
	}

	public Integer getDrugCombinationRelationId() {
		return drugCombinationRelationId;
	}

	public void setDrugCombinationRelationId(Integer drugCombinationRelationId) {
		this.drugCombinationRelationId = drugCombinationRelationId;
	}

	public Integer getDrugCombinationBrandCode() {
		return drugCombinationBrandCode;
	}

	public void setDrugCombinationBrandCode(Integer drugCombinationBrandCode) {
		this.drugCombinationBrandCode = drugCombinationBrandCode;
	}

	public Integer getDrugCombinationSpecificId() {
		return drugCombinationSpecificId;
	}

	public void setDrugCombinationSpecificId(Integer drugCombinationSpecificId) {
		this.drugCombinationSpecificId = drugCombinationSpecificId;
		
		
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_combination_ndc_code",referencedColumnName="ndc_code",insertable=false,updatable=false)
	@JsonManagedReference
	NdcDrugBrandMap ndcDrugBrandMap;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_combination_relation_id",referencedColumnName="drug_relation_map_code",insertable=false,updatable=false)
	@JsonManagedReference
	DrugRelationMap drugRelationMap;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_combination_brand_code",referencedColumnName="brand_code",insertable=false,updatable=false)
	@JsonManagedReference
	Brandname brandname;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_combination_specific_id",referencedColumnName="drugid",insertable=false,updatable=false)
	@JsonManagedReference
	Prescriberspecificdrug prescriberspecificdrug;

	public NdcDrugBrandMap getNdcDrugBrandMap() {
		return ndcDrugBrandMap;
	}

	public void setNdcDrugBrandMap(NdcDrugBrandMap ndcDrugBrandMap) {
		this.ndcDrugBrandMap = ndcDrugBrandMap;
	}

	public DrugRelationMap getDrugRelationMap() {
		return drugRelationMap;
	}

	public void setDrugRelationMap(DrugRelationMap drugRelationMap) {
		this.drugRelationMap = drugRelationMap;
	}

	public Brandname getBrandname() {
		return brandname;
	}

	public void setBrandname(Brandname brandname) {
		this.brandname = brandname;
	}

	public Prescriberspecificdrug getPrescriberspecificdrug() {
		return prescriberspecificdrug;
	}

	public void setPrescriberspecificdrug(
			Prescriberspecificdrug prescriberspecificdrug) {
		this.prescriberspecificdrug = prescriberspecificdrug;
	}
	
	
}