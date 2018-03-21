package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_form_shortcut_category_details")
public class SkinTestFormShortcutCategoryDetails {

	@Id
	@Column(name="skin_test_form_shortcut_category_details_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_form_shortcut_category_details_id")
	@SequenceGenerator(name="skin_test_form_shortcut_category_details_id",sequenceName="skin_test_form_shortcut_category_details_id",allocationSize=1)
	private Integer skinTestFormShortcutCategoryDetailsId;

	@Column(name="skin_test_form_shortcut_category_details_shortcut_id")
	private Integer skinTestFormShortcutCategoryDetailsShortcutId;

	@Column(name="skin_test_form_shortcut_category_details_allergen_category_id")
	private Integer skinTestFormShortcutCategoryDetailsAllergenCategoryId;

	@Column(name="skin_test_form_shortcut_category_details_category_order_id")
	private Integer skinTestFormShortcutCategoryDetailsCategoryOrderId;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_form_shortcut_category_details_created_on")
	private Timestamp skinTestFormShortcutCategoryDetailsCreatedOn;
	
	@Column(name="skin_test_form_shortcut_category_details_created_by")
	private Integer skinTestFormShortcutCategoryDetailsCreatedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_form_shortcut_category_details_modified_on")
	private Timestamp skinTestFormShortcutCategoryDetailsModifiedOn;
	
	@Column(name="skin_test_form_shortcut_category_details_modified_by")
	private Integer skinTestFormShortcutCategoryDetailsModifiedBy;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="skin_test_form_shortcut_category_details_allergen_category_id",referencedColumnName="concentrate_group_id",insertable=false,updatable=false)
	@JsonManagedReference
	ConcentrateGroup concentrateGroup;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="skin_test_form_shortcut_category_details_shortcut_id",referencedColumnName="skin_test_form_shortcut_id",insertable=false,updatable=false)
	@JsonBackReference
	SkinTestFormShortcut skinTestFormShortcut;
		
	@OneToMany(mappedBy="skinTestFormShortcutCategoryDetails")
	List<SkinTestFormShortcutAllergenDetails> skinTestFormShortcutAllergenDetails; 
	
	
	public Integer getSkinTestFormShortcutCategoryDetailsId() {
		return skinTestFormShortcutCategoryDetailsId;
	}

	public void setSkinTestFormShortcutCategoryDetailsId(
			Integer skinTestFormShortcutCategoryDetailsId) {
		this.skinTestFormShortcutCategoryDetailsId = skinTestFormShortcutCategoryDetailsId;
	}

	public Integer getSkinTestFormShortcutCategoryDetailsShortcutId() {
		return skinTestFormShortcutCategoryDetailsShortcutId;
	}

	public void setSkinTestFormShortcutCategoryDetailsShortcutId(
			Integer skinTestFormShortcutCategoryDetailsShortcutId) {
		this.skinTestFormShortcutCategoryDetailsShortcutId = skinTestFormShortcutCategoryDetailsShortcutId;
	}

	public Integer getSkinTestFormShortcutCategoryDetailsAllergenCategoryId() {
		return skinTestFormShortcutCategoryDetailsAllergenCategoryId;
	}

	public void setSkinTestFormShortcutCategoryDetailsAllergenCategoryId(
			Integer skinTestFormShortcutCategoryDetailsAllergenCategoryId) {
		this.skinTestFormShortcutCategoryDetailsAllergenCategoryId = skinTestFormShortcutCategoryDetailsAllergenCategoryId;
	}

	public Integer getSkinTestFormShortcutCategoryDetailsCategoryOrderId() {
		return skinTestFormShortcutCategoryDetailsCategoryOrderId;
	}

	public void setSkinTestFormShortcutCategoryDetailsCategoryOrderId(
			Integer skinTestFormShortcutCategoryDetailsCategoryOrderId) {
		this.skinTestFormShortcutCategoryDetailsCategoryOrderId = skinTestFormShortcutCategoryDetailsCategoryOrderId;
	}
	
	public Timestamp getSkinTestFormShortcutCategoryDetailsCreatedOn() {
		return skinTestFormShortcutCategoryDetailsCreatedOn;
	}

	public void setSkinTestFormShortcutCategoryDetailsCreatedOn(
			Timestamp skinTestFormShortcutCategoryDetailsCreatedOn) {
		this.skinTestFormShortcutCategoryDetailsCreatedOn = skinTestFormShortcutCategoryDetailsCreatedOn;
	}

	public Integer getSkinTestFormShortcutCategoryDetailsCreatedBy() {
		return skinTestFormShortcutCategoryDetailsCreatedBy;
	}

	public void setSkinTestFormShortcutCategoryDetailsCreatedBy(
			Integer skinTestFormShortcutCategoryDetailsCreatedBy) {
		this.skinTestFormShortcutCategoryDetailsCreatedBy = skinTestFormShortcutCategoryDetailsCreatedBy;
	}

	public Timestamp getSkinTestFormShortcutCategoryDetailsModifiedOn() {
		return skinTestFormShortcutCategoryDetailsModifiedOn;
	}

	public void setSkinTestFormShortcutCategoryDetailsModifiedOn(
			Timestamp skinTestFormShortcutCategoryDetailsModifiedOn) {
		this.skinTestFormShortcutCategoryDetailsModifiedOn = skinTestFormShortcutCategoryDetailsModifiedOn;
	}

	public Integer getSkinTestFormShortcutCategoryDetailsModifiedBy() {
		return skinTestFormShortcutCategoryDetailsModifiedBy;
	}

	public void setSkinTestFormShortcutCategoryDetailsModifiedBy(
			Integer skinTestFormShortcutCategoryDetailsModifiedBy) {
		this.skinTestFormShortcutCategoryDetailsModifiedBy = skinTestFormShortcutCategoryDetailsModifiedBy;
	}

	public SkinTestFormShortcut getSkinTestFormShortcut() {
		return skinTestFormShortcut;
	}

	public void setSkinTestFormShortcut(SkinTestFormShortcut skinTestFormShortcut) {
		this.skinTestFormShortcut = skinTestFormShortcut;
	}

	public List<SkinTestFormShortcutAllergenDetails> getSkinTestFormShortcutAllergenDetails() {
		return skinTestFormShortcutAllergenDetails;
	}

	public void setSkinTestFormShortcutAllergenDetails(
			List<SkinTestFormShortcutAllergenDetails> skinTestFormShortcutAllergenDetails) {
		this.skinTestFormShortcutAllergenDetails = skinTestFormShortcutAllergenDetails;
	}

	public ConcentrateGroup getConcentrateGroup() {
		return concentrateGroup;
	}

	public void setConcentrateGroup(ConcentrateGroup concentrateGroup) {
		this.concentrateGroup = concentrateGroup;
	}
	
}