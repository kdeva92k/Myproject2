package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "core_gendrug")
public class CoreGendrug {

	@Column(name="id")
	private Integer id;

	@Id
	@Column(name="drug_id")
	private String drugId;

	@Column(name="generic_name")
	private String genericName;

	@Column(name="is_ingredient")
	private String isIngredient;

	@Column(name="is_product")
	private String isProduct;

	@Column(name="rx_otc_status_code")
	private String rxOtcStatusCode;

	@Column(name="is_active")
	private String isActive;

	@Column(name="obsolete_date")
	private String obsoleteDate;

	@Column(name="half_life_hours")
	private String halfLifeHours;

	@Column(name="half_life_is_empirical")
	private String halfLifeIsEmpirical;

	@Column(name="pregnancy_risk_factor")
	private String pregnancyRiskFactor;

	@Column(name="manufacturer_generic_name")
	private String manufacturerGenericName;

	@Column(name="create_date")
	private String createDate;
	
	@Column(name="update_date")
	private String updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getIsIngredient() {
		return isIngredient;
	}

	public void setIsIngredient(String isIngredient) {
		this.isIngredient = isIngredient;
	}

	public String getIsProduct() {
		return isProduct;
	}

	public void setIsProduct(String isProduct) {
		this.isProduct = isProduct;
	}

	public String getRxOtcStatusCode() {
		return rxOtcStatusCode;
	}

	public void setRxOtcStatusCode(String rxOtcStatusCode) {
		this.rxOtcStatusCode = rxOtcStatusCode;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getObsoleteDate() {
		return obsoleteDate;
	}

	public void setObsoleteDate(String obsoleteDate) {
		this.obsoleteDate = obsoleteDate;
	}

	public String getHalfLifeHours() {
		return halfLifeHours;
	}

	public void setHalfLifeHours(String halfLifeHours) {
		this.halfLifeHours = halfLifeHours;
	}

	public String getHalfLifeIsEmpirical() {
		return halfLifeIsEmpirical;
	}

	public void setHalfLifeIsEmpirical(String halfLifeIsEmpirical) {
		this.halfLifeIsEmpirical = halfLifeIsEmpirical;
	}

	public String getPregnancyRiskFactor() {
		return pregnancyRiskFactor;
	}

	public void setPregnancyRiskFactor(String pregnancyRiskFactor) {
		this.pregnancyRiskFactor = pregnancyRiskFactor;
	}

	public String getManufacturerGenericName() {
		return manufacturerGenericName;
	}

	public void setManufacturerGenericName(String manufacturerGenericName) {
		this.manufacturerGenericName = manufacturerGenericName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}