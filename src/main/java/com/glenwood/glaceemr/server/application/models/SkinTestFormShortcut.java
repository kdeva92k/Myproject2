package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_form_shortcut")
public class SkinTestFormShortcut {

	@Id
	@Column(name="skin_test_form_shortcut_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_form_shortcut_id")
	@SequenceGenerator(name="skin_test_form_shortcut_id",sequenceName="skin_test_form_shortcut_id",allocationSize=1)
	private Integer skinTestFormShortcutId;

	@Column(name="skin_test_form_shortcut_name")
	private String skinTestFormShortcutName;

	@Column(name="skin_test_form_shortcut_prick_wheal_needed")
	private Boolean skinTestFormShortcutPrickWhealNeeded;

	@Column(name="skin_test_form_shortcut_prick_flare_needed")
	private Boolean skinTestFormShortcutPrickFlareNeeded;

	@Column(name="skin_test_form_shortcut_prick_erythema_needed")
	private Boolean skinTestFormShortcutPrickErythemaNeeded;

	@Column(name="skin_test_form_shortcut_prick_pseudopodia_needed")
	private Boolean skinTestFormShortcutPrickPseudopodiaNeeded;

	@Column(name="skin_test_form_shortcut_intradermal_wheal_needed")
	private Boolean skinTestFormShortcutIntradermalWhealNeeded;

	@Column(name="skin_test_form_shortcut_intradermal_flare_needed")
	private Boolean skinTestFormShortcutIntradermalFlareNeeded;

	@Column(name="skin_test_form_shortcut_intradermal_erythema_needed")
	private Boolean skinTestFormShortcutIntradermalErythemaNeeded;

	@Column(name="skin_test_form_shortcut_intradermal_pseudopodia_needed")
	private Boolean skinTestFormShortcutIntradermalPseudopodiaNeeded;

	@Column(name="skin_test_form_shortcut_scoring_notes")
	private String skinTestFormShortcutScoringNotes;
	
	@Column(name="skin_test_form_shortcut_notes")
	private String skinTestFormShortcutNotes;
	
	@Column(name="skin_test_form_shortcut_created_by")
	private Integer skinTestFormShortcutCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_form_shortcut_created_on")
	private Timestamp skinTestFormShortcutCreatedOn;

	
	@Column(name="skin_test_form_shortcut_last_modified_by")
	private Integer skinTestFormShortcutLastModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_form_shortcut_last_modified_on")
	private Timestamp skinTestFormShortcutLastModifiedOn;
	
	@Column(name="skin_test_form_shortcut_default_read_value")
	private String skinTestFormShortcutdefaultreadvalue;
	
	
	@OneToMany(mappedBy="skinTestFormShortcut")
	List<SkinTestFormShortcutCategoryDetails> skinTestFormShortcutCategoryDetails;
	
	public Integer getSkinTestFormShortcutId() {
		return skinTestFormShortcutId;
	}

	public void setSkinTestFormShortcutId(Integer skinTestFormShortcutId) {
		this.skinTestFormShortcutId = skinTestFormShortcutId;
	}

	public String getSkinTestFormShortcutName() {
		return skinTestFormShortcutName;
	}

	public void setSkinTestFormShortcutName(String skinTestFormShortcutName) {
		this.skinTestFormShortcutName = skinTestFormShortcutName;
	}

	public Integer getSkinTestFormShortcutCreatedBy() {
		return skinTestFormShortcutCreatedBy;
	}

	public void setSkinTestFormShortcutCreatedBy(
			Integer skinTestFormShortcutCreatedBy) {
		this.skinTestFormShortcutCreatedBy = skinTestFormShortcutCreatedBy;
	}

	public Timestamp getSkinTestFormShortcutCreatedOn() {
		return skinTestFormShortcutCreatedOn;
	}

	public void setSkinTestFormShortcutCreatedOn(
			Timestamp skinTestFormShortcutCreatedOn) {
		this.skinTestFormShortcutCreatedOn = skinTestFormShortcutCreatedOn;
	}

	public Integer getSkinTestFormShortcutLastModifiedBy() {
		return skinTestFormShortcutLastModifiedBy;
	}

	public void setSkinTestFormShortcutLastModifiedBy(
			Integer skinTestFormShortcutLastModifiedBy) {
		this.skinTestFormShortcutLastModifiedBy = skinTestFormShortcutLastModifiedBy;
	}

	public Timestamp getSkinTestFormShortcutLastModifiedOn() {
		return skinTestFormShortcutLastModifiedOn;
	}

	public void setSkinTestFormShortcutLastModifiedOn(
			Timestamp skinTestFormShortcutLastModifiedOn) {
		this.skinTestFormShortcutLastModifiedOn = skinTestFormShortcutLastModifiedOn;
	}

	public List<SkinTestFormShortcutCategoryDetails> getSkinTestFormShortcutCategoryDetails() {
		return skinTestFormShortcutCategoryDetails;
	}

	public void setSkinTestFormShortcutCategoryDetails(
			List<SkinTestFormShortcutCategoryDetails> skinTestFormShortcutCategoryDetails) {
		this.skinTestFormShortcutCategoryDetails = skinTestFormShortcutCategoryDetails;
	}

	public Boolean getSkinTestFormShortcutPrickWhealNeeded() {
		return skinTestFormShortcutPrickWhealNeeded;
	}

	public void setSkinTestFormShortcutPrickWhealNeeded(
			Boolean skinTestFormShortcutPrickWhealNeeded) {
		this.skinTestFormShortcutPrickWhealNeeded = skinTestFormShortcutPrickWhealNeeded;
	}

	public Boolean getSkinTestFormShortcutPrickFlareNeeded() {
		return skinTestFormShortcutPrickFlareNeeded;
	}

	public void setSkinTestFormShortcutPrickFlareNeeded(
			Boolean skinTestFormShortcutPrickFlareNeeded) {
		this.skinTestFormShortcutPrickFlareNeeded = skinTestFormShortcutPrickFlareNeeded;
	}

	public Boolean getSkinTestFormShortcutPrickErythemaNeeded() {
		return skinTestFormShortcutPrickErythemaNeeded;
	}

	public void setSkinTestFormShortcutPrickErythemaNeeded(
			Boolean skinTestFormShortcutPrickErythemaNeeded) {
		this.skinTestFormShortcutPrickErythemaNeeded = skinTestFormShortcutPrickErythemaNeeded;
	}

	public Boolean getSkinTestFormShortcutPrickPseudopodiaNeeded() {
		return skinTestFormShortcutPrickPseudopodiaNeeded;
	}

	public void setSkinTestFormShortcutPrickPseudopodiaNeeded(
			Boolean skinTestFormShortcutPrickPseudopodiaNeeded) {
		this.skinTestFormShortcutPrickPseudopodiaNeeded = skinTestFormShortcutPrickPseudopodiaNeeded;
	}

	public Boolean getSkinTestFormShortcutIntradermalWhealNeeded() {
		return skinTestFormShortcutIntradermalWhealNeeded;
	}

	public void setSkinTestFormShortcutIntradermalWhealNeeded(
			Boolean skinTestFormShortcutIntradermalWhealNeeded) {
		this.skinTestFormShortcutIntradermalWhealNeeded = skinTestFormShortcutIntradermalWhealNeeded;
	}

	public Boolean getSkinTestFormShortcutIntradermalFlareNeeded() {
		return skinTestFormShortcutIntradermalFlareNeeded;
	}

	public void setSkinTestFormShortcutIntradermalFlareNeeded(
			Boolean skinTestFormShortcutIntradermalFlareNeeded) {
		this.skinTestFormShortcutIntradermalFlareNeeded = skinTestFormShortcutIntradermalFlareNeeded;
	}

	public Boolean getSkinTestFormShortcutIntradermalErythemaNeeded() {
		return skinTestFormShortcutIntradermalErythemaNeeded;
	}

	public void setSkinTestFormShortcutIntradermalErythemaNeeded(
			Boolean skinTestFormShortcutIntradermalErythemaNeeded) {
		this.skinTestFormShortcutIntradermalErythemaNeeded = skinTestFormShortcutIntradermalErythemaNeeded;
	}

	public Boolean getSkinTestFormShortcutIntradermalPseudopodiaNeeded() {
		return skinTestFormShortcutIntradermalPseudopodiaNeeded;
	}

	public void setSkinTestFormShortcutIntradermalPseudopodiaNeeded(
			Boolean skinTestFormShortcutIntradermalPseudopodiaNeeded) {
		this.skinTestFormShortcutIntradermalPseudopodiaNeeded = skinTestFormShortcutIntradermalPseudopodiaNeeded;
	}

	public String getSkinTestFormShortcutScoringNotes() {
		return skinTestFormShortcutScoringNotes;
	}

	public void setSkinTestFormShortcutScoringNotes(
			String skinTestFormShortcutScoringNotes) {
		this.skinTestFormShortcutScoringNotes = skinTestFormShortcutScoringNotes;
	}

	public String getSkinTestFormShortcutNotes() {
		return skinTestFormShortcutNotes;
	}

	public void setSkinTestFormShortcutNotes(String skinTestFormShortcutNotes) {
		this.skinTestFormShortcutNotes = skinTestFormShortcutNotes;
	}
		
	
	public String getskinTestFormShortcutdefaultreadvalue() {
		return skinTestFormShortcutdefaultreadvalue;
	}

	public void setskinTestFormShortcutdefaultreadvalue(String skinTestFormShortcutdefaultreadvalue) {
		this.skinTestFormShortcutdefaultreadvalue = skinTestFormShortcutdefaultreadvalue;
	}
	
	

}