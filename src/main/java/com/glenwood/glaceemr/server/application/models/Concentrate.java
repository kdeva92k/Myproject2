package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "concentrate")
public class Concentrate {

	@Id
	@Column(name="concentrate_id")
	private Integer concentrateId;

	@Column(name="concentrate_name")
	private String concentrateName;

	@Column(name="concentrate_volume")
	private Double concentrateVolume;

	@Column(name="concentrate_unit")
	private String concentrateUnit;

	@Column(name="concentrate_groupid")
	private Integer concentrateGroupid;

	@Column(name="concentrate_status")
	private Boolean concentrateStatus;

	@Column(name="concentrate_orderby")
	private Integer concentrateOrderby;
	
	@Column(name="is_intradermal_needed")
	private Boolean isIntradermalNeeded;
	
	@Column(name="is_food_allergen")
	private Boolean isFoodAllergen;

	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="concentrate_groupid",referencedColumnName="concentrate_group_id",insertable=false, updatable=false)
	@JsonBackReference
	ConcentrateGroup concentrateGroup;
	
	public Integer getConcentrateId() {
		return concentrateId;
	}

	public void setConcentrateId(Integer concentrateId) {
		this.concentrateId = concentrateId;
	}

	public String getConcentrateName() {
		return concentrateName;
	}

	public void setConcentrateName(String concentrateName) {
		this.concentrateName = concentrateName;
	}

	public Double getConcentrateVolume() {
		return concentrateVolume;
	}

	public void setConcentrateVolume(Double concentrateVolume) {
		this.concentrateVolume = concentrateVolume;
	}

	public String getConcentrateUnit() {
		return concentrateUnit;
	}

	public void setConcentrateUnit(String concentrateUnit) {
		this.concentrateUnit = concentrateUnit;
	}

	public Integer getConcentrateGroupid() {
		return concentrateGroupid;
	}

	public void setConcentrateGroupid(Integer concentrateGroupid) {
		this.concentrateGroupid = concentrateGroupid;
	}

	public Boolean getConcentrateStatus() {
		return concentrateStatus;
	}

	public void setConcentrateStatus(Boolean concentrateStatus) {
		this.concentrateStatus = concentrateStatus;
	}

	public Integer getConcentrateOrderby() {
		return concentrateOrderby;
	}

	public void setConcentrateOrderby(Integer concentrateOrderby) {
		this.concentrateOrderby = concentrateOrderby;
	}

	public ConcentrateGroup getConcentrateGroup() {
		return concentrateGroup;
	}

	public void setConcentrateGroup(ConcentrateGroup concentrateGroup) {
		this.concentrateGroup = concentrateGroup;
	}

	public Boolean getIsIntradermalNeeded() {
		return isIntradermalNeeded;
	}

	public void setIsIntradermalNeeded(Boolean isIntradermalNeeded) {
		this.isIntradermalNeeded = isIntradermalNeeded;
	}

	public Boolean getIsFoodAllergen() {
		return isFoodAllergen;
	}

	public void setIsFoodAllergen(Boolean isFoodAllergen) {
		this.isFoodAllergen = isFoodAllergen;
	}
}