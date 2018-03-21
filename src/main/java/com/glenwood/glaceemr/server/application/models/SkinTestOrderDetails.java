package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonDateSerializer;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_order_details")
public class SkinTestOrderDetails {

	@Id
	@Column(name="skin_test_order_details_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_order_details_id")
	@SequenceGenerator(name="skin_test_order_details_id",sequenceName="skin_test_order_details_id",allocationSize=1)
	private Integer skinTestOrderDetailsId;

	@Column(name="skin_test_order_details_allergen_id")
	private Integer skinTestOrderDetailsAllergenId;

	@Column(name="skin_test_order_details_result_value")
	private String skinTestOrderDetailsResultValue;

	@Column(name="skin_test_order_details_test_concentration")
	private String skinTestOrderDetailsTestConcentration;

	@Column(name="skin_test_order_details_skin_test_order_entry_id")
	private Integer skinTestOrderDetailsSkinTestOrderEntryId;

	@Column(name="skin_test_order_details_whealvalue")
	private String skinTestOrderDetailsWhealvalue;

	@Column(name="skin_test_order_details_flarevalue")
	private String skinTestOrderDetailsFlarevalue;

	@Column(name="skin_test_order_details_erythema")
	private Boolean skinTestOrderDetailsErythema;

	@Column(name="skin_test_order_details_pseudopodia")
	private Boolean skinTestOrderDetailsPseudopodia;
	
	@Column(name="skin_test_order_details_allergen_category_id")
	private Integer skinTestOrderDetailsAllergenCategoryId;
	
	@Column(name="skin_test_order_details_encounter_id")
	private Integer skinTestOrderDetailsEncounterId;

	@Column(name="skin_test_order_details_created_by")
	private Integer skinTestOrderDetailsCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_details_created_on")
	private Timestamp skinTestOrderDetailsCreatedOn;

	@Column(name="skin_test_order_details_modified_by")
	private Integer skinTestOrderDetailsModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_details_modified_on")
	private Timestamp skinTestOrderDetailsModifiedOn;
	
	@Column(name="skin_test_order_details_read_value")
	private Boolean skinTestOrderDetailsReadValue;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="skin_test_order_details_skin_test_order_entry_id",referencedColumnName="skin_test_order_entry_id",insertable=false,updatable=false)
	@JsonBackReference
	SkinTestOrderEntry skinTestOrderEntry;
	
	/*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_details_allergen_id",referencedColumnName="concentrate_id",insertable=false,updatable=false)
	@JsonManagedReference
	Concentrate concentrate;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_details_allergen_category_id",referencedColumnName="concentrate_group_id",insertable=false,updatable=false)
	@JsonManagedReference
	ConcentrateGroup concentrateGroup;*/
	
	public Integer getSkinTestOrderDetailsId() {
		return skinTestOrderDetailsId;
	}

	public void setSkinTestOrderDetailsId(Integer skinTestOrderDetailsId) {
		this.skinTestOrderDetailsId = skinTestOrderDetailsId;
	}

	public Integer getSkinTestOrderDetailsAllergenId() {
		return skinTestOrderDetailsAllergenId;
	}

	public void setSkinTestOrderDetailsAllergenId(
			Integer skinTestOrderDetailsAllergenId) {
		this.skinTestOrderDetailsAllergenId = skinTestOrderDetailsAllergenId;
	}

	public String getSkinTestOrderDetailsResultValue() {
		return skinTestOrderDetailsResultValue;
	}

	public void setSkinTestOrderDetailsResultValue(
			String skinTestOrderDetailsResultValue) {
		this.skinTestOrderDetailsResultValue = skinTestOrderDetailsResultValue;
	}

	public String getSkinTestOrderDetailsTestConcentration() {
		return skinTestOrderDetailsTestConcentration;
	}

	public void setSkinTestOrderDetailsTestConcentration(String skinTestOrderDetailsTestConcentration) {
		this.skinTestOrderDetailsTestConcentration = skinTestOrderDetailsTestConcentration;
	}

	public Integer getSkinTestOrderDetailsSkinTestOrderEntryId() {
		return skinTestOrderDetailsSkinTestOrderEntryId;
	}

	public void setSkinTestOrderDetailsSkinTestOrderEntryId(Integer skinTestOrderDetailsSkinTestOrderEntryId) {
		this.skinTestOrderDetailsSkinTestOrderEntryId = skinTestOrderDetailsSkinTestOrderEntryId;
	}

	public String getSkinTestOrderDetailsWhealvalue() {
		return skinTestOrderDetailsWhealvalue;
	}

	public void setSkinTestOrderDetailsWhealvalue(String skinTestOrderDetailsWhealvalue) {
		this.skinTestOrderDetailsWhealvalue = skinTestOrderDetailsWhealvalue;
	}

	public String getSkinTestOrderDetailsFlarevalue() {
		return skinTestOrderDetailsFlarevalue;
	}

	public void setSkinTestOrderDetailsFlarevalue(String skinTestOrderDetailsFlarevalue) {
		this.skinTestOrderDetailsFlarevalue = skinTestOrderDetailsFlarevalue;
	}

	public Boolean getSkinTestOrderDetailsErythema() {
		return skinTestOrderDetailsErythema;
	}

	public void setSkinTestOrderDetailsErythema(Boolean skinTestOrderDetailsErythema) {
		this.skinTestOrderDetailsErythema = skinTestOrderDetailsErythema;
	}

	public Boolean getSkinTestOrderDetailsPseudopodia() {
		return skinTestOrderDetailsPseudopodia;
	}

	public void setSkinTestOrderDetailsPseudopodia(Boolean skinTestOrderDetailsPseudopodia) {
		this.skinTestOrderDetailsPseudopodia = skinTestOrderDetailsPseudopodia;
	}

	public Integer getSkinTestOrderDetailsAllergenCategoryId() {
		return skinTestOrderDetailsAllergenCategoryId;
	}

	public void setSkinTestOrderDetailsAllergenCategoryId(Integer skinTestOrderDetailsAllergenCategoryId) {
		this.skinTestOrderDetailsAllergenCategoryId = skinTestOrderDetailsAllergenCategoryId;
	}

	public Integer getSkinTestOrderDetailsEncounterId() {
		return skinTestOrderDetailsEncounterId;
	}

	public void setSkinTestOrderDetailsEncounterId(Integer skinTestOrderDetailsEncounterId) {
		this.skinTestOrderDetailsEncounterId = skinTestOrderDetailsEncounterId;
	}

	public Integer getSkinTestOrderDetailsCreatedBy() {
		return skinTestOrderDetailsCreatedBy;
	}

	public void setSkinTestOrderDetailsCreatedBy(
			Integer skinTestOrderDetailsCreatedBy) {
		this.skinTestOrderDetailsCreatedBy = skinTestOrderDetailsCreatedBy;
	}

	public Timestamp getSkinTestOrderDetailsCreatedOn() {
		return skinTestOrderDetailsCreatedOn;
	}

	public void setSkinTestOrderDetailsCreatedOn(
			Timestamp skinTestOrderDetailsCreatedOn) {
		this.skinTestOrderDetailsCreatedOn = skinTestOrderDetailsCreatedOn;
	}

	public Integer getSkinTestOrderDetailsModifiedBy() {
		return skinTestOrderDetailsModifiedBy;
	}

	public void setSkinTestOrderDetailsModifiedBy(
			Integer skinTestOrderDetailsModifiedBy) {
		this.skinTestOrderDetailsModifiedBy = skinTestOrderDetailsModifiedBy;
	}

	public Timestamp getSkinTestOrderDetailsModifiedOn() {
		return skinTestOrderDetailsModifiedOn;
	}

	public void setSkinTestOrderDetailsModifiedOn(
			Timestamp skinTestOrderDetailsModifiedOn) {
		this.skinTestOrderDetailsModifiedOn = skinTestOrderDetailsModifiedOn;
	}
	
	public SkinTestOrderEntry getSkinTestOrderEntry() {
		return skinTestOrderEntry;
	}

	public void setSkinTestOrderEntry(SkinTestOrderEntry skinTestOrderEntry) {
		this.skinTestOrderEntry = skinTestOrderEntry;
	}

	public Boolean getSkinTestOrderDetailsReadValue() {
		return skinTestOrderDetailsReadValue;
	}

	public void setSkinTestOrderDetailsReadValue(
			Boolean skinTestOrderDetailsReadValue) {
		this.skinTestOrderDetailsReadValue = skinTestOrderDetailsReadValue;
	}
	
	
	/*public Concentrate getConcentrate() {
		return concentrate;
	}

	public void setConcentrate(Concentrate concentrate) {
		this.concentrate = concentrate;
	}

	public ConcentrateGroup getConcentrateGroup() {
		return concentrateGroup;
	}

	public void setConcentrateGroup(ConcentrateGroup concentrateGroup) {
		this.concentrateGroup = concentrateGroup;
	}*/
	
}