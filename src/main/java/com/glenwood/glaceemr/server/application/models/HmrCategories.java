package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@SuppressWarnings("deprecation")
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hmr_categories")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HmrCategories {
	
	@Id
	@Column(name="hmr_categories_id", nullable=false)
	@SequenceGenerator(name="hmr_categories_hmr_categories_id_seq", sequenceName="hmr_categories_hmr_categories_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="hmr_categories_hmr_categories_id_seq")
	private Integer hmrCategoriesId;
	
	@Column(name="hmr_categories_descrip", length=100)
	private String hmrCategoriesDescrip;
	
	@Column(name="hmr_categories_sort_by")
	private Integer hmrCategoriesSortBy;
	
	@Column(name="hmr_categories_bg_color", length=50)
	private String hmrCategoriesBgColor;
	
	@Column(name="hmr_categories_practice_id")
	private Integer hmrCategoriesPracticeId;
	
	@Column(name="hmr_categories_is_active")
	private Integer hmrCategoriesIsActive;
	
	@Column(name="hmr_categories_is_disease_mangt")
	private Integer hmrCategoriesIsDiseaseMangt;
	
	@Column(name="hmr_categories_hmr_group_id")
	private Integer hmrCategoriesHmrGroupId;
	
	@Column(name="hmr_categories_modified_by")
	private Integer hmrCategoriesModifiedBy;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="hmr_categories_modified_on")
	private Timestamp hmrCategoriesModifiedOn;
	
	@Column(name="hmr_categories_created_by")
	private Integer hmrCategoriesCreatedBy;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="hmr_categories_created_on")
	private Timestamp hmrCategoriesCreatedOn;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="hmr_categories_hmr_group_id",referencedColumnName="hmr_group_id", nullable=false, insertable=false, updatable=false)
	HmrGroups hmrGroupsTable;
	
	public Integer getHmrCategoriesId() {
		return hmrCategoriesId;
	}

	public void setHmrCategoriesId(Integer hmrCategoriesId) {
		this.hmrCategoriesId = hmrCategoriesId;
	}

	public String getHmrCategoriesDescrip() {
		return hmrCategoriesDescrip;
	}

	public void setHmrCategoriesDescrip(String hmrCategoriesDescrip) {
		this.hmrCategoriesDescrip = hmrCategoriesDescrip;
	}

	public Integer getHmrCategoriesSortBy() {
		return hmrCategoriesSortBy;
	}

	public void setHmrCategoriesSortBy(Integer hmrCategoriesSortBy) {
		this.hmrCategoriesSortBy = hmrCategoriesSortBy;
	}

	public String getHmrCategoriesBgColor() {
		return hmrCategoriesBgColor;
	}

	public void setHmrCategoriesBgColor(String hmrCategoriesBgColor) {
		this.hmrCategoriesBgColor = hmrCategoriesBgColor;
	}

	public Integer getHmrCategoriesPracticeId() {
		return hmrCategoriesPracticeId;
	}

	public void setHmrCategoriesPracticeId(Integer hmrCategoriesPracticeId) {
		this.hmrCategoriesPracticeId = hmrCategoriesPracticeId;
	}

	public Integer getHmrCategoriesIsActive() {
		return hmrCategoriesIsActive;
	}

	public void setHmrCategoriesIsActive(Integer hmrCategoriesIsActive) {
		this.hmrCategoriesIsActive = hmrCategoriesIsActive;
	}

	public Integer getHmrCategoriesIsDiseaseMangt() {
		return hmrCategoriesIsDiseaseMangt;
	}

	public void setHmrCategoriesIsDiseaseMangt(Integer hmrCategoriesIsDiseaseMangt) {
		this.hmrCategoriesIsDiseaseMangt = hmrCategoriesIsDiseaseMangt;
	}

	public Integer getHmrCategoriesHmrGroupId() {
		return hmrCategoriesHmrGroupId;
	}

	public void setHmrCategoriesHmrGroupId(Integer hmrCategoriesHmrGroupId) {
		this.hmrCategoriesHmrGroupId = hmrCategoriesHmrGroupId;
	}

	public Integer getHmrCategoriesModifiedBy() {
		return hmrCategoriesModifiedBy;
	}

	public void setHmrCategoriesModifiedBy(Integer hmrCategoriesModifiedBy) {
		this.hmrCategoriesModifiedBy = hmrCategoriesModifiedBy;
	}

	public Timestamp getHmrCategoriesModifiedOn() {
		return hmrCategoriesModifiedOn;
	}

	public void setHmrCategoriesModifiedOn(Timestamp hmrCategoriesModifiedOn) {
		this.hmrCategoriesModifiedOn = hmrCategoriesModifiedOn;
	}

	public Integer getHmrCategoriesCreatedBy() {
		return hmrCategoriesCreatedBy;
	}

	public void setHmrCategoriesCreatedBy(Integer hmrCategoriesCreatedBy) {
		this.hmrCategoriesCreatedBy = hmrCategoriesCreatedBy;
	}

	public Timestamp getHmrCategoriesCreatedOn() {
		return hmrCategoriesCreatedOn;
	}

	public void setHmrCategoriesCreatedOn(Timestamp hmrCategoriesCreatedOn) {
		this.hmrCategoriesCreatedOn = hmrCategoriesCreatedOn;
	}
}