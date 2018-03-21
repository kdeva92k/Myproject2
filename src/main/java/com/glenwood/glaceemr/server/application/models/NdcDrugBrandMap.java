package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ndc_drug_brand_map")
public class NdcDrugBrandMap implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="ndc_code")
	private String ndcCode;

	@Column(name="maindrugcode")
	private Integer maindrugcode;

	@Column(name="image_filename")
	private String imageFilename;
	
	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	public String getNdcCode() {
		return ndcCode;
	}

	public void setNdcCode(String ndcCode) {
		this.ndcCode = ndcCode;
	}

	public Integer getMaindrugcode() {
		return maindrugcode;
	}

	public void setMaindrugcode(Integer maindrugcode) {
		this.maindrugcode = maindrugcode;
	}

	public Integer getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(Integer brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getDrugStatus() {
		return drugStatus;
	}

	public void setDrugStatus(String drugStatus) {
		this.drugStatus = drugStatus;
	}

	public String getTherapeuticEquivalent() {
		return therapeuticEquivalent;
	}

	public void setTherapeuticEquivalent(String therapeuticEquivalent) {
		this.therapeuticEquivalent = therapeuticEquivalent;
	}

	public String getTherapeuticAlternative() {
		return therapeuticAlternative;
	}

	public void setTherapeuticAlternative(String therapeuticAlternative) {
		this.therapeuticAlternative = therapeuticAlternative;
	}

	public String getNdcDrugBrandMapProductId() {
		return ndcDrugBrandMapProductId;
	}

	public void setNdcDrugBrandMapProductId(String ndcDrugBrandMapProductId) {
		this.ndcDrugBrandMapProductId = ndcDrugBrandMapProductId;
	}

	public String getNdcDrugBrandMapMonograph() {
		return ndcDrugBrandMapMonograph;
	}

	public void setNdcDrugBrandMapMonograph(String ndcDrugBrandMapMonograph) {
		this.ndcDrugBrandMapMonograph = ndcDrugBrandMapMonograph;
	}

	public String getNdcDrugBrandMapLeaflet() {
		return ndcDrugBrandMapLeaflet;
	}

	public void setNdcDrugBrandMapLeaflet(String ndcDrugBrandMapLeaflet) {
		this.ndcDrugBrandMapLeaflet = ndcDrugBrandMapLeaflet;
	}

	public Integer getNdcDrugBrandMapDrugCombinationId() {
		return ndcDrugBrandMapDrugCombinationId;
	}

	public void setNdcDrugBrandMapDrugCombinationId(
			Integer ndcDrugBrandMapDrugCombinationId) {
		this.ndcDrugBrandMapDrugCombinationId = ndcDrugBrandMapDrugCombinationId;
	}

	public String getDrugInnerPackUnitId() {
		return drugInnerPackUnitId;
	}

	public void setDrugInnerPackUnitId(String drugInnerPackUnitId) {
		this.drugInnerPackUnitId = drugInnerPackUnitId;
	}

	public String getDrugInnerPackSize() {
		return drugInnerPackSize;
	}

	public void setDrugInnerPackSize(String drugInnerPackSize) {
		this.drugInnerPackSize = drugInnerPackSize;
	}

	public Boolean getIsMedSup() {
		return isMedSup;
	}

	public void setIsMedSup(Boolean isMedSup) {
		this.isMedSup = isMedSup;
	}

	public Integer getMedSupDetailMapId() {
		return medSupDetailMapId;
	}

	public void setMedSupDetailMapId(Integer medSupDetailMapId) {
		this.medSupDetailMapId = medSupDetailMapId;
	}

	
	@Id
	@Column(name="brand_code")
	private Integer brandCode;

	@Column(name="brandname")
	private String brandname;

	@Column(name="drug_type")
	private String drugType;

	@Column(name="drug_status")
	private String drugStatus;

	@Column(name="therapeutic_equivalent")
	private String therapeuticEquivalent;

	@Column(name="therapeutic_alternative")
	private String therapeuticAlternative;

	@Column(name="ndc_drug_brand_map_product_id")
	private String ndcDrugBrandMapProductId;

	@Column(name="ndc_drug_brand_map_monograph")
	private String ndcDrugBrandMapMonograph;

	@Column(name="ndc_drug_brand_map_leaflet")
	private String ndcDrugBrandMapLeaflet;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ndc_drug_brand_map_ndc_drug_brand_map_drug_combination_id_seq")
	@SequenceGenerator(name =" ndc_drug_brand_map_ndc_drug_brand_map_drug_combination_id_seq", sequenceName="ndc_drug_brand_map_ndc_drug_brand_map_drug_combination_id_seq", allocationSize=1)
	@Column(name="ndc_drug_brand_map_drug_combination_id")
	private Integer ndcDrugBrandMapDrugCombinationId;


	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="maindrugcode",referencedColumnName="drug_relation_map_code",insertable=false,updatable=false)
	@JsonManagedReference
	DrugRelationMap drugRelationMap;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ndc_drug_brand_map_drug_combination_id",referencedColumnName="drugid",insertable=false,updatable=false)
	@JsonManagedReference
	Prescriberspecificdrug prescriberspecificdrug;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="brand_code",referencedColumnName="brand_code",insertable=false,updatable=false)
	@JsonManagedReference
	Brandname brandName;
	
	public Brandname getBrandName() {
		return brandName;
	}

	public void setBrandName(Brandname brandName) {
		this.brandName = brandName;
	}


	@Column(name="drug_inner_pack_unit_id")
	private String drugInnerPackUnitId;

	@Column(name="drug_inner_pack_size")
	private String drugInnerPackSize;

	@Column(name="is_med_sup")
	private Boolean isMedSup;

	@Column(name="med_sup_detail_map_id")
	private Integer medSupDetailMapId;

	public DrugRelationMap getDrugRelationMap() {
		return drugRelationMap;
	}

	public void setDrugRelationMap(DrugRelationMap drugRelationMap) {
		this.drugRelationMap = drugRelationMap;
	}

	public Prescriberspecificdrug getPrescriberspecificdrug() {
		return prescriberspecificdrug;
	}

	public void setPrescriberspecificdrug(
			Prescriberspecificdrug prescriberspecificdrug) {
		this.prescriberspecificdrug = prescriberspecificdrug;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="med_sup_detail_map_id",referencedColumnName="med_sup_details_id",insertable=false,updatable=false)
	@JsonManagedReference
	MedSupDetails medSupDetails;
	
	@OneToMany(mappedBy="ndcDrugBrandMap")
	@JsonManagedReference
	List<Rxnorm> rxnorms;
	
	@OneToMany(mappedBy="ndcDrugBrandMap")
	@JsonBackReference
	List<DrugCombination> drugCombinations; 

	public List<Rxnorm> getRxnorms() {
		return rxnorms;
	}

	public void setRxnorms(List<Rxnorm> rxnorms) {
		this.rxnorms = rxnorms;
	}

	public MedSupDetails getMedSupDetails() {
		return medSupDetails;
	}

	public void setMedSupDetails(MedSupDetails medSupDetails) {
		this.medSupDetails = medSupDetails;
	}
	
	
	
}