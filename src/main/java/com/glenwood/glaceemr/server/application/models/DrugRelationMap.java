package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "drug_relation_map")
public class DrugRelationMap {

	
	@Id
	@Column(name="drug_relation_map_code")
	private Integer drugRelationMapCode;

	@Column(name="drug_relation_map_drug_id")
	private Integer drugRelationMapDrugId;

	@Column(name="drug_relation_map_dosage_id")
	private Integer drugRelationMapDosageId;

	@Column(name="drug_relation_map_unit_id")
	private Integer drugRelationMapUnitId;

	@Column(name="drug_relation_map_route_id")
	private Integer drugRelationMapRouteId;

	@Column(name="drug_relation_map_form_id")
	private Integer drugRelationMapFormId;

	@Column(name="drug_relation_map_schedule1_id")
	private Integer drugRelationMapSchedule1Id;

	@Column(name="drug_relation_map_schedule2_id")
	private Integer drugRelationMapSchedule2Id;

	@Column(name="drug_relation_map_qty")
	private Integer drugRelationMapQty;

	@Column(name="drug_relation_map_refills_id")
	private Integer drugRelationMapRefillsId;

	@Column(name="drug_relation_map_is_default")
	private Boolean drugRelationMapIsDefault;

	@Column(name="drug_relation_map_hit_count")
	private Long drugRelationMapHitCount;

	@Column(name="drug_relation_map_dea_schedule")
	private Integer drugRelationMapDeaSchedule;

	@Column(name="drug_relation_map_take")
	private String drugRelationMapTake;

	@Column(name="drug_relation_map_days")
	private String drugRelationMapDays;

	@Column(name="drug_relation_map_quantity_unit")
	private String drugRelationMapQuantityUnit;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_relation_map_drug_id",referencedColumnName="drug_details_id",insertable=false,updatable=false)
	@JsonManagedReference
	DrugDetails drugDetails;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_relation_map_form_id",referencedColumnName="drug_form_id",insertable=false,updatable=false)
	@JsonManagedReference
	DrugForm drugForm;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_relation_map_dosage_id",referencedColumnName="drug_dosage_id",insertable=false,updatable=false)
	@JsonManagedReference
	 DrugDosage drugDosage;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_relation_map_unit_id",referencedColumnName="drug_dosage_unit_id",insertable=false,updatable=false)
	@JsonManagedReference
	DrugDosageUnit drugDosageUnit;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_relation_map_route_id",referencedColumnName="drug_route_id",insertable=false,updatable=false)
	@JsonManagedReference
	DrugRoute drugRoute;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_relation_map_schedule1_id",referencedColumnName="drug_schedule_id",insertable=false,updatable=false)
	@JsonManagedReference
	DrugSchedule drugSchedule;

	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="drug_relation_map_dea_schedule",referencedColumnName="dea_schedule_value",insertable=false,updatable=false)
	@JsonManagedReference
	DeaSchedule deaSchedule;
	
	@OneToMany(mappedBy="drugRelationMap")
	@JsonManagedReference
	List<NdcDrugBrandMap> ndcList;
	
	
	public List<NdcDrugBrandMap> getNdcList() {
		return ndcList;
	}


	public void setNdcList(List<NdcDrugBrandMap> ndcList) {
		this.ndcList = ndcList;
	}


	public DeaSchedule getDeaSchedule() {
		return deaSchedule;
	}


	public void setDeaSchedule(DeaSchedule deaSchedule) {
		this.deaSchedule = deaSchedule;
	}


	public Integer getDrugRelationMapCode() {
		return drugRelationMapCode;
	}


	public void setDrugRelationMapCode(Integer drugRelationMapCode) {
		this.drugRelationMapCode = drugRelationMapCode;
	}


	public Integer getDrugRelationMapDrugId() {
		return drugRelationMapDrugId;
	}


	public void setDrugRelationMapDrugId(Integer drugRelationMapDrugId) {
		this.drugRelationMapDrugId = drugRelationMapDrugId;
	}


	public Integer getDrugRelationMapDosageId() {
		return drugRelationMapDosageId;
	}


	public void setDrugRelationMapDosageId(Integer drugRelationMapDosageId) {
		this.drugRelationMapDosageId = drugRelationMapDosageId;
	}


	public Integer getDrugRelationMapUnitId() {
		return drugRelationMapUnitId;
	}


	public void setDrugRelationMapUnitId(Integer drugRelationMapUnitId) {
		this.drugRelationMapUnitId = drugRelationMapUnitId;
	}


	public Integer getDrugRelationMapRouteId() {
		return drugRelationMapRouteId;
	}


	public void setDrugRelationMapRouteId(Integer drugRelationMapRouteId) {
		this.drugRelationMapRouteId = drugRelationMapRouteId;
	}


	public Integer getDrugRelationMapFormId() {
		return drugRelationMapFormId;
	}


	public void setDrugRelationMapFormId(Integer drugRelationMapFormId) {
		this.drugRelationMapFormId = drugRelationMapFormId;
	}


	public Integer getDrugRelationMapSchedule1Id() {
		return drugRelationMapSchedule1Id;
	}


	public void setDrugRelationMapSchedule1Id(Integer drugRelationMapSchedule1Id) {
		this.drugRelationMapSchedule1Id = drugRelationMapSchedule1Id;
	}


	public Integer getDrugRelationMapSchedule2Id() {
		return drugRelationMapSchedule2Id;
	}


	public void setDrugRelationMapSchedule2Id(Integer drugRelationMapSchedule2Id) {
		this.drugRelationMapSchedule2Id = drugRelationMapSchedule2Id;
	}


	public Integer getDrugRelationMapQty() {
		return drugRelationMapQty;
	}


	public void setDrugRelationMapQty(Integer drugRelationMapQty) {
		this.drugRelationMapQty = drugRelationMapQty;
	}


	public Integer getDrugRelationMapRefillsId() {
		return drugRelationMapRefillsId;
	}


	public void setDrugRelationMapRefillsId(Integer drugRelationMapRefillsId) {
		this.drugRelationMapRefillsId = drugRelationMapRefillsId;
	}


	public Boolean getDrugRelationMapIsDefault() {
		return drugRelationMapIsDefault;
	}


	public void setDrugRelationMapIsDefault(Boolean drugRelationMapIsDefault) {
		this.drugRelationMapIsDefault = drugRelationMapIsDefault;
	}


	public Long getDrugRelationMapHitCount() {
		return drugRelationMapHitCount;
	}


	public void setDrugRelationMapHitCount(Long drugRelationMapHitCount) {
		this.drugRelationMapHitCount = drugRelationMapHitCount;
	}


	public Integer getDrugRelationMapDeaSchedule() {
		return drugRelationMapDeaSchedule;
	}


	public void setDrugRelationMapDeaSchedule(Integer drugRelationMapDeaSchedule) {
		this.drugRelationMapDeaSchedule = drugRelationMapDeaSchedule;
	}


	public String getDrugRelationMapTake() {
		return drugRelationMapTake;
	}


	public void setDrugRelationMapTake(String drugRelationMapTake) {
		this.drugRelationMapTake = drugRelationMapTake;
	}


	public String getDrugRelationMapDays() {
		return drugRelationMapDays;
	}


	public void setDrugRelationMapDays(String drugRelationMapDays) {
		this.drugRelationMapDays = drugRelationMapDays;
	}


	public String getDrugRelationMapQuantityUnit() {
		return drugRelationMapQuantityUnit;
	}


	public void setDrugRelationMapQuantityUnit(String drugRelationMapQuantityUnit) {
		this.drugRelationMapQuantityUnit = drugRelationMapQuantityUnit;
	}


	public DrugDetails getDrugDetails() {
		return drugDetails;
	}


	public void setDrugDetails(DrugDetails drugDetails) {
		this.drugDetails = drugDetails;
	}


	public DrugForm getDrugForm() {
		return drugForm;
	}


	public void setDrugForm(DrugForm drugForm) {
		this.drugForm = drugForm;
	}


	public DrugDosage getDrugDosage() {
		return drugDosage;
	}


	public void setDrugDosage(DrugDosage drugDosage) {
		this.drugDosage = drugDosage;
	}


	public DrugDosageUnit getDrugDosageUnit() {
		return drugDosageUnit;
	}


	public void setDrugDosageUnit(DrugDosageUnit drugDosageUnit) {
		this.drugDosageUnit = drugDosageUnit;
	}


	public DrugRoute getDrugRoute() {
		return drugRoute;
	}


	public void setDrugRoute(DrugRoute drugRoute) {
		this.drugRoute = drugRoute;
	}


	public DrugSchedule getDrugSchedule() {
		return drugSchedule;
	}


	public void setDrugSchedule(DrugSchedule drugSchedule) {
		this.drugSchedule = drugSchedule;
	}
	
	
}