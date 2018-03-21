package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_order_allergen")
public class SkinTestOrderAllergen {
	
	@Id
	@Column(name="skin_test_order_allergen_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_order_allergen_id")
	@SequenceGenerator(name="skin_test_order_allergen_id",sequenceName="skin_test_order_allergen_id",allocationSize=1)
	private Integer skinTestOrderAllergenId;

	@Column(name="skin_test_order_allergen_category_map_id")
	private Integer skinTestOrderAllergenCategoryMapId;
	
	@Column(name="skin_test_order_allergen_patient_id")
	private Integer skinTestOrderAllergenPatientId;

	@Column(name="skin_test_order_allergen_concentrate_id")
	private Integer skinTestOrderAllergenConcentrateId;
	
	@Column(name="skin_test_order_allergen_concentrate_order_id")
	private Integer skinTestOrderAllergenConcentrateOrderId;
	
	@Column(name="skin_test_order_allergen_concentrate_name")
	private String skinTestOrderAllergenConcentrateName;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_allergen_created_on")
	private Timestamp skinTestOrderAllergenCreatedOn;

	@Column(name="skin_test_order_allergen_created_by")
	private Integer skinTestOrderAllergenCreatedBy;
	
	@Column(name="is_intradermal_needed")
	private boolean isIntradermalNeeded;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="skin_test_order_allergen_category_map_id",referencedColumnName="skin_test_order_allergen_category_id",insertable=false,updatable=false)
	@JsonBackReference
	SkinTestOrderAllergenCategory allergenCategory;

	public Integer getSkinTestOrderAllergenId() {
		return skinTestOrderAllergenId;
	}

	public void setSkinTestOrderAllergenId(Integer skinTestOrderAllergenId) {
		this.skinTestOrderAllergenId = skinTestOrderAllergenId;
	}

	public Integer getSkinTestOrderAllergenCategoryMapId() {
		return skinTestOrderAllergenCategoryMapId;
	}

	public void setSkinTestOrderAllergenCategoryMapId(
			Integer skinTestOrderAllergenCategoryMapId) {
		this.skinTestOrderAllergenCategoryMapId = skinTestOrderAllergenCategoryMapId;
	}

	public Integer getSkinTestOrderAllergenPatientId() {
		return skinTestOrderAllergenPatientId;
	}

	public void setSkinTestOrderAllergenPatientId(
			Integer skinTestOrderAllergenPatientId) {
		this.skinTestOrderAllergenPatientId = skinTestOrderAllergenPatientId;
	}

	public Integer getSkinTestOrderAllergenConcentrateId() {
		return skinTestOrderAllergenConcentrateId;
	}

	public void setSkinTestOrderAllergenConcentrateId(
			Integer skinTestOrderAllergenConcentrateId) {
		this.skinTestOrderAllergenConcentrateId = skinTestOrderAllergenConcentrateId;
	}

	public Integer getSkinTestOrderAllergenConcentrateOrderId() {
		return skinTestOrderAllergenConcentrateOrderId;
	}

	public void setSkinTestOrderAllergenConcentrateOrderId(
			Integer skinTestOrderAllergenConcentrateOrderId) {
		this.skinTestOrderAllergenConcentrateOrderId = skinTestOrderAllergenConcentrateOrderId;
	}

	public String getSkinTestOrderAllergenConcentrateName() {
		return skinTestOrderAllergenConcentrateName;
	}

	public void setSkinTestOrderAllergenConcentrateName(
			String skinTestOrderAllergenConcentrateName) {
		this.skinTestOrderAllergenConcentrateName = skinTestOrderAllergenConcentrateName;
	}

	public Timestamp getSkinTestOrderAllergenCreatedOn() {
		return skinTestOrderAllergenCreatedOn;
	}

	public void setSkinTestOrderAllergenCreatedOn(
			Timestamp skinTestOrderAllergenCreatedOn) {
		this.skinTestOrderAllergenCreatedOn = skinTestOrderAllergenCreatedOn;
	}

	public Integer getSkinTestOrderAllergenCreatedBy() {
		return skinTestOrderAllergenCreatedBy;
	}

	public void setSkinTestOrderAllergenCreatedBy(
			Integer skinTestOrderAllergenCreatedBy) {
		this.skinTestOrderAllergenCreatedBy = skinTestOrderAllergenCreatedBy;
	}

	public boolean getIsIntradermalNeeded() {
		return isIntradermalNeeded;
	}

	public void setIsIntradermalNeeded(boolean isIntradermalNeeded) {
		this.isIntradermalNeeded = isIntradermalNeeded;
	}

	public SkinTestOrderAllergenCategory getAllergenCategory() {
		return allergenCategory;
	}

	public void setAllergenCategory(SkinTestOrderAllergenCategory allergenCategory) {
		this.allergenCategory = allergenCategory;
	}
}