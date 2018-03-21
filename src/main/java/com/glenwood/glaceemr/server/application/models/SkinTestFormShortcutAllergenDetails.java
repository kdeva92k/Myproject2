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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_form_shortcut_allergen_details")
public class SkinTestFormShortcutAllergenDetails {

	@Id
	@Column(name="skin_test_form_shortcut_allergen_details_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_form_shortcut_allergen_details_id")
	@SequenceGenerator(name="skin_test_form_shortcut_allergen_details_id",sequenceName="skin_test_form_shortcut_allergen_details_id",allocationSize=1)
	private Integer skinTestFormShortcutAllergenDetailsId;

	@Column(name="skin_test_form_shortcut_allergen_details_shortcut_id")
	private Integer skinTestFormShortcutAllergenDetailsShortcutId;

	@Column(name="skin_test_form_shortcut_allergen_details_category_details_id")
	private Integer skinTestFormShortcutAllergenDetailsCategoryDetailsId;

	@Column(name="skin_test_form_shortcut_allergen_details_allergen_id")
	private Integer skinTestFormShortcutAllergenDetailsAllergenId;

	@Column(name="skin_test_form_shortcut_allergen_details_allergen_order_id")
	private Integer skinTestFormShortcutAllergenDetailsAllergenOrderId;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_form_shortcut_allergen_details_created_on")
	private Timestamp skinTestFormShortcutAllergenDetailsCreatedOn;
	
	@Column(name="skin_test_form_shortcut_allergen_details_created_by")
	private Integer skinTestFormShortcutAllergenDetailsCreatedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_form_shortcut_allergen_details_modified_on")
	private Timestamp skinTestFormShortcutAllergenDetailsModifiedOn;
	
	@Column(name="skin_test_form_shortcut_allergen_details_modified_by")
	private Integer skinTestFormShortcutAllergenDetailsModifiedBy;
		
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="skin_test_form_shortcut_allergen_details_allergen_id",referencedColumnName="concentrate_id",insertable=false,updatable=false)
	@JsonManagedReference
	Concentrate concentrate;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="skin_test_form_shortcut_allergen_details_category_details_id",referencedColumnName="skin_test_form_shortcut_category_details_id",insertable=false,updatable=false)
	@JsonBackReference
	SkinTestFormShortcutCategoryDetails skinTestFormShortcutCategoryDetails;
	
	public Integer getSkinTestFormShortcutAllergenDetailsId() {
		return skinTestFormShortcutAllergenDetailsId;
	}

	public void setSkinTestFormShortcutAllergenDetailsId(
			Integer skinTestFormShortcutAllergenDetailsId) {
		this.skinTestFormShortcutAllergenDetailsId = skinTestFormShortcutAllergenDetailsId;
	}

	public Integer getSkinTestFormShortcutAllergenDetailsShortcutId() {
		return skinTestFormShortcutAllergenDetailsShortcutId;
	}

	public void setSkinTestFormShortcutAllergenDetailsShortcutId(
			Integer skinTestFormShortcutAllergenDetailsShortcutId) {
		this.skinTestFormShortcutAllergenDetailsShortcutId = skinTestFormShortcutAllergenDetailsShortcutId;
	}

	public Integer getSkinTestFormShortcutAllergenDetailsCategoryDetailsId() {
		return skinTestFormShortcutAllergenDetailsCategoryDetailsId;
	}

	public void setSkinTestFormShortcutAllergenDetailsCategoryDetailsId(
			Integer skinTestFormShortcutAllergenDetailsCategoryDetailsId) {
		this.skinTestFormShortcutAllergenDetailsCategoryDetailsId = skinTestFormShortcutAllergenDetailsCategoryDetailsId;
	}

	public Integer getSkinTestFormShortcutAllergenDetailsAllergenId() {
		return skinTestFormShortcutAllergenDetailsAllergenId;
	}

	public void setSkinTestFormShortcutAllergenDetailsAllergenId(
			Integer skinTestFormShortcutAllergenDetailsAllergenId) {
		this.skinTestFormShortcutAllergenDetailsAllergenId = skinTestFormShortcutAllergenDetailsAllergenId;
	}

	public Integer getSkinTestFormShortcutAllergenDetailsAllergenOrderId() {
		return skinTestFormShortcutAllergenDetailsAllergenOrderId;
	}

	public void setSkinTestFormShortcutAllergenDetailsAllergenOrderId(
			Integer skinTestFormShortcutAllergenDetailsAllergenOrderId) {
		this.skinTestFormShortcutAllergenDetailsAllergenOrderId = skinTestFormShortcutAllergenDetailsAllergenOrderId;
	}

	public Timestamp getSkinTestFormShortcutAllergenDetailsCreatedOn() {
		return skinTestFormShortcutAllergenDetailsCreatedOn;
	}

	public void setSkinTestFormShortcutAllergenDetailsCreatedOn(
			Timestamp skinTestFormShortcutAllergenDetailsCreatedOn) {
		this.skinTestFormShortcutAllergenDetailsCreatedOn = skinTestFormShortcutAllergenDetailsCreatedOn;
	}

	public Integer getSkinTestFormShortcutAllergenDetailsCreatedBy() {
		return skinTestFormShortcutAllergenDetailsCreatedBy;
	}

	public void setSkinTestFormShortcutAllergenDetailsCreatedBy(
			Integer skinTestFormShortcutAllergenDetailsCreatedBy) {
		this.skinTestFormShortcutAllergenDetailsCreatedBy = skinTestFormShortcutAllergenDetailsCreatedBy;
	}

	public Timestamp getSkinTestFormShortcutAllergenDetailsModifiedOn() {
		return skinTestFormShortcutAllergenDetailsModifiedOn;
	}

	public void setSkinTestFormShortcutAllergenDetailsModifiedOn(
			Timestamp skinTestFormShortcutAllergenDetailsModifiedOn) {
		this.skinTestFormShortcutAllergenDetailsModifiedOn = skinTestFormShortcutAllergenDetailsModifiedOn;
	}

	public Integer getSkinTestFormShortcutAllergenDetailsModifiedBy() {
		return skinTestFormShortcutAllergenDetailsModifiedBy;
	}

	public void setSkinTestFormShortcutAllergenDetailsModifiedBy(
			Integer skinTestFormShortcutAllergenDetailsModifiedBy) {
		this.skinTestFormShortcutAllergenDetailsModifiedBy = skinTestFormShortcutAllergenDetailsModifiedBy;
	}

	public Concentrate getConcentrate() {
		return concentrate;
	}

	public void setConcentrate(Concentrate concentrate) {
		this.concentrate = concentrate;
	}

	public SkinTestFormShortcutCategoryDetails getSkinTestFormShortcutCategoryDetails() {
		return skinTestFormShortcutCategoryDetails;
	}

	public void setSkinTestFormShortcutCategoryDetails(
			SkinTestFormShortcutCategoryDetails skinTestFormShortcutCategoryDetails) {
		this.skinTestFormShortcutCategoryDetails = skinTestFormShortcutCategoryDetails;
	}

}