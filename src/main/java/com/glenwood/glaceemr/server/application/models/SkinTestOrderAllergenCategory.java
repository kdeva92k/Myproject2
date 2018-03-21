package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_order_allergen_category")
public class SkinTestOrderAllergenCategory {

	@Id
	@Column(name="skin_test_order_allergen_category_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_order_allergen_category_id")
	@SequenceGenerator(name="skin_test_order_allergen_category_id",sequenceName="skin_test_order_allergen_category_id",allocationSize=1)
	private Integer skinTestOrderAllergenCategoryId;

	@Column(name="skin_test_order_allergen_category_test_order_id")
	private Integer skinTestOrderAllergenCategoryTestOrderId;
	
	@Column(name="skin_test_order_allergen_category_patient_id")
	private Integer skinTestOrderAllergenCategoryPatientId;

	@Column(name="skin_test_order_allergen_category_concentrate_group_id")
	private Integer skinTestOrderAllergenCategoryConcentrateGroupId;
	
	@Column(name="skin_test_order_allergen_category_concentrate_group_order_id")
	private Integer skinTestOrderAllergenCategoryConcentrateGroupOrderId;
	
	@Column(name="skin_test_order_allergen_category_concentrate_group_name")
	private String skinTestOrderAllergenCategoryConcentrateGroupName;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_allergen_category_created_on")
	private Timestamp skinTestOrderAllergenCategoryCreatedOn;

	@Column(name="skin_test_order_allergen_category_created_by")
	private Integer skinTestOrderAllergenCategoryCreatedBy;
	
	@OneToMany(mappedBy="allergenCategory")
	@JsonManagedReference
	List<SkinTestOrderAllergen> skinTestOrderAllergens;

	public Integer getSkinTestOrderAllergenCategoryId() {
		return skinTestOrderAllergenCategoryId;
	}

	public void setSkinTestOrderAllergenCategoryId(
			Integer skinTestOrderAllergenCategoryId) {
		this.skinTestOrderAllergenCategoryId = skinTestOrderAllergenCategoryId;
	}

	public Integer getSkinTestOrderAllergenCategoryTestOrderId() {
		return skinTestOrderAllergenCategoryTestOrderId;
	}

	public void setSkinTestOrderAllergenCategoryTestOrderId(
			Integer skinTestOrderAllergenCategoryTestOrderId) {
		this.skinTestOrderAllergenCategoryTestOrderId = skinTestOrderAllergenCategoryTestOrderId;
	}

	public Integer getSkinTestOrderAllergenCategoryPatientId() {
		return skinTestOrderAllergenCategoryPatientId;
	}

	public void setSkinTestOrderAllergenCategoryPatientId(
			Integer skinTestOrderAllergenCategoryPatientId) {
		this.skinTestOrderAllergenCategoryPatientId = skinTestOrderAllergenCategoryPatientId;
	}

	public Integer getSkinTestOrderAllergenCategoryConcentrateGroupId() {
		return skinTestOrderAllergenCategoryConcentrateGroupId;
	}

	public void setSkinTestOrderAllergenCategoryConcentrateGroupId(
			Integer skinTestOrderAllergenCategoryConcentrateGroupId) {
		this.skinTestOrderAllergenCategoryConcentrateGroupId = skinTestOrderAllergenCategoryConcentrateGroupId;
	}
	
	public Integer getSkinTestOrderAllergenCategoryConcentrateGroupOrderId() {
		return skinTestOrderAllergenCategoryConcentrateGroupOrderId;
	}

	public void setSkinTestOrderAllergenCategoryConcentrateGroupOrderId(
			Integer skinTestOrderAllergenCategoryConcentrateGroupOrderId) {
		this.skinTestOrderAllergenCategoryConcentrateGroupOrderId = skinTestOrderAllergenCategoryConcentrateGroupOrderId;
	}

	public String getSkinTestOrderAllergenCategoryConcentrateGroupName() {
		return skinTestOrderAllergenCategoryConcentrateGroupName;
	}

	public void setSkinTestOrderAllergenCategoryConcentrateGroupName(
			String skinTestOrderAllergenCategoryConcentrateGroupName) {
		this.skinTestOrderAllergenCategoryConcentrateGroupName = skinTestOrderAllergenCategoryConcentrateGroupName;
	}

	public Timestamp getSkinTestOrderAllergenCategoryCreatedOn() {
		return skinTestOrderAllergenCategoryCreatedOn;
	}

	public void setSkinTestOrderAllergenCategoryCreatedOn(
			Timestamp skinTestOrderAllergenCategoryCreatedOn) {
		this.skinTestOrderAllergenCategoryCreatedOn = skinTestOrderAllergenCategoryCreatedOn;
	}

	public Integer getSkinTestOrderAllergenCategoryCreatedBy() {
		return skinTestOrderAllergenCategoryCreatedBy;
	}

	public void setSkinTestOrderAllergenCategoryCreatedBy(
			Integer skinTestOrderAllergenCategoryCreatedBy) {
		this.skinTestOrderAllergenCategoryCreatedBy = skinTestOrderAllergenCategoryCreatedBy;
	}

	public List<SkinTestOrderAllergen> getSkinTestOrderAllergens() {
		return skinTestOrderAllergens;
	}

	public void setSkinTestOrderAllergens(List<SkinTestOrderAllergen> skinTestOrderAllergens) {
		this.skinTestOrderAllergens = skinTestOrderAllergens;
	}
	
}