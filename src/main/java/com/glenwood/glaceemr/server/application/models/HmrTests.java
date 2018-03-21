package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@SuppressWarnings("deprecation")
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hmr_tests")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HmrTests {
	
	@Id
	@Column(name="hmr_tests_id", nullable=false)
	@SequenceGenerator(name="hmr_tests_hmr_tests_id_seq", sequenceName="hmr_tests_hmr_tests_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="hmr_tests_hmr_tests_id_seq")
	private Integer hmrTestsId;
	
	@Column(name="hmr_tests_description", length=100)
	private String hmrTestsDescription;
	
	@Column(name="hmr_tests_category_id")
	private Integer hmrTestsCategoryId;
	
	@Column(name="hmr_tests_gender")
	private Integer hmrTestsGender;
	
	@Column(name="hmr_tests_sort_by")
	private Integer hmrTestsSortBy;
	
	@Column(name="hmr_tests_is_active")
	private Integer hmrTestsIsActive;
	
	@Column(name="hmr_tests_lab_name", length=100)
	private String hmrTestsLabName;
	
	@Column(name="hmr_tests_drugs", length=100)
	private String hmrTestsDrugs;
	
	@Column(name="hmr_tests_from")
	private Integer hmrTestsFrom;
	
	@Column(name="hmr_tests_to")
	private Integer hmrTestsTo;
	
	@Column(name="hmr_tests_cpt", length=500)
	private String hmrTestsCpt;
	
	@Column(name="hmr_tests_dxs", length=500)
	private String hmrTestsDxs;
	
	@Column(name="hmr_tests_dx_desc", length=5000)
	private String hmrTestsDxDesc;
	
	@Column(name="hmr_tests_comments", length=2000)
	private String hmrTestsComments;
	
	@Column(name="hmr_tests_max_age")
	private Integer hmrTestsMaxAge;
	
	@Column(name="hmr_tests_is_dx_based", columnDefinition = "Integer default 0")
	private Integer hmrTestsIsDxBased;
	
	@Column(name="hmr_tests_overdue_limit")
	private Integer hmrTestsOverdueLimit;
	
	@Column(name="hmr_tests_overdue_type")
	private Integer hmrTestsOverdueType;
	
	@Column(name="hmr_tests_modified_by")
	private Integer hmrTestsModifiedBy;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="hmr_tests_modified_on")
	private Timestamp hmrTestsModifiedOn;
	
	@Column(name="hmr_tests_created_by")
	private Integer hmrTestsCreatedBy;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="hmr_tests_created_on")
	private Timestamp hmrTestsCreatedOn;
	
	@Column(name="hmr_tests_alert_type")
	private Integer hmrTestsAlertType;
	
	@Column(name="hmr_tests_flowsheet_element_type")
	private Integer hmrTestsFlowsheetElementType;
	
	@Column(name="hmr_tests_flowsheet_element_id")
	private Long hmrTestsFlowsheetElementId;
	
	@Column(name="hmr_tests_ruledate")
	private Integer hmrTestsRuledate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="hmr_tests_category_id",referencedColumnName="hmr_categories_id", nullable=false, insertable=false, updatable=false)
	HmrCategories hmrTestsTable;
	
	@OneToMany(mappedBy="hmrCategoryUrlCategoryid")
	@JsonManagedReference
	List<HmrCategoryUrl> hmrCategoryUrl;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="hmr_tests_id",referencedColumnName="hmr_test_detail_test_id", insertable=false, updatable=false)
	HmrTestDetail hmrTestDetailTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="hmr_tests_modified_by",referencedColumnName="emp_profile_empid", nullable=false, insertable=false, updatable=false)
	EmployeeProfile empProfileHmrTestsModifiedByTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="hmr_tests_created_by",referencedColumnName="emp_profile_empid", nullable=false, insertable=false, updatable=false)
	EmployeeProfile empProfileHmrTestsCreatedByTable;
	
	public Integer getHmrTestsId() {
		return hmrTestsId;
	}

	public void setHmrTestsId(Integer hmrTestsId) {
		this.hmrTestsId = hmrTestsId;
	}

	public String getHmrTestsDescription() {
		return hmrTestsDescription;
	}

	public void setHmrTestsDescription(String hmrTestsDescription) {
		this.hmrTestsDescription = hmrTestsDescription;
	}

	public Integer getHmrTestsCategoryId() {
		return hmrTestsCategoryId;
	}

	public void setHmrTestsCategoryId(Integer hmrTestsCategoryId) {
		this.hmrTestsCategoryId = hmrTestsCategoryId;
	}

	public Integer getHmrTestsGender() {
		return hmrTestsGender;
	}

	public void setHmrTestsGender(Integer hmrTestsGender) {
		this.hmrTestsGender = hmrTestsGender;
	}

	public Integer getHmrTestsSortBy() {
		return hmrTestsSortBy;
	}

	public void setHmrTestsSortBy(Integer hmrTestsSortBy) {
		this.hmrTestsSortBy = hmrTestsSortBy;
	}

	public Integer getHmrTestsIsActive() {
		return hmrTestsIsActive;
	}

	public void setHmrTestsIsActive(Integer hmrTestsIsActive) {
		this.hmrTestsIsActive = hmrTestsIsActive;
	}

	public String getHmrTestsLabName() {
		return hmrTestsLabName;
	}

	public void setHmrTestsLabName(String hmrTestsLabName) {
		this.hmrTestsLabName = hmrTestsLabName;
	}

	public String getHmrTestsDrugs() {
		return hmrTestsDrugs;
	}

	public void setHmrTestsDrugs(String hmrTestsDrugs) {
		this.hmrTestsDrugs = hmrTestsDrugs;
	}

	public Integer getHmrTestsFrom() {
		return hmrTestsFrom;
	}

	public void setHmrTestsFrom(Integer hmrTestsFrom) {
		this.hmrTestsFrom = hmrTestsFrom;
	}

	public Integer getHmrTestsTo() {
		return hmrTestsTo;
	}

	public void setHmrTestsTo(Integer hmrTestsTo) {
		this.hmrTestsTo = hmrTestsTo;
	}

	public String getHmrTestsCpt() {
		return hmrTestsCpt;
	}

	public void setHmrTestsCpt(String hmrTestsCpt) {
		this.hmrTestsCpt = hmrTestsCpt;
	}

	public String getHmrTestsDxs() {
		return hmrTestsDxs;
	}

	public void setHmrTestsDxs(String hmrTestsDxs) {
		this.hmrTestsDxs = hmrTestsDxs;
	}

	public String getHmrTestsDxDesc() {
		return hmrTestsDxDesc;
	}

	public void setHmrTestsDxDesc(String hmrTestsDxDesc) {
		this.hmrTestsDxDesc = hmrTestsDxDesc;
	}

	public String getHmrTestsComments() {
		return hmrTestsComments;
	}

	public void setHmrTestsComments(String hmrTestsComments) {
		this.hmrTestsComments = hmrTestsComments;
	}

	public Integer getHmrTestsMaxAge() {
		return hmrTestsMaxAge;
	}

	public void setHmrTestsMaxAge(Integer hmrTestsMaxAge) {
		this.hmrTestsMaxAge = hmrTestsMaxAge;
	}

	public Integer getHmrTestsIsDxBased() {
		return hmrTestsIsDxBased;
	}

	public void setHmrTestsIsDxBased(Integer hmrTestsIsDxBased) {
		this.hmrTestsIsDxBased = hmrTestsIsDxBased;
	}

	public Integer getHmrTestsOverdueLimit() {
		return hmrTestsOverdueLimit;
	}

	public void setHmrTestsOverdueLimit(Integer hmrTestsOverdueLimit) {
		this.hmrTestsOverdueLimit = hmrTestsOverdueLimit;
	}

	public Integer getHmrTestsOverdueType() {
		return hmrTestsOverdueType;
	}

	public void setHmrTestsOverdueType(Integer hmrTestsOverdueType) {
		this.hmrTestsOverdueType = hmrTestsOverdueType;
	}

	public Integer getHmrTestsModifiedBy() {
		return hmrTestsModifiedBy;
	}

	public void setHmrTestsModifiedBy(Integer hmrTestsModifiedBy) {
		this.hmrTestsModifiedBy = hmrTestsModifiedBy;
	}

	public Timestamp getHmrTestsModifiedOn() {
		return hmrTestsModifiedOn;
	}

	public void setHmrTestsModifiedOn(Timestamp hmrTestsModifiedOn) {
		this.hmrTestsModifiedOn = hmrTestsModifiedOn;
	}

	public Integer getHmrTestsCreatedBy() {
		return hmrTestsCreatedBy;
	}

	public void setHmrTestsCreatedBy(Integer hmrTestsCreatedBy) {
		this.hmrTestsCreatedBy = hmrTestsCreatedBy;
	}

	public Timestamp getHmrTestsCreatedOn() {
		return hmrTestsCreatedOn;
	}

	public void setHmrTestsCreatedOn(Timestamp hmrTestsCreatedOn) {
		this.hmrTestsCreatedOn = hmrTestsCreatedOn;
	}

	public Integer getHmrTestsAlertType() {
		return hmrTestsAlertType;
	}

	public void setHmrTestsAlertType(Integer hmrTestsAlertType) {
		this.hmrTestsAlertType = hmrTestsAlertType;
	}

	public Integer getHmrTestsFlowsheetElementType() {
		return hmrTestsFlowsheetElementType;
	}

	public void setHmrTestsFlowsheetElementType(Integer hmrTestsFlowsheetElementType) {
		this.hmrTestsFlowsheetElementType = hmrTestsFlowsheetElementType;
	}

	public Long getHmrTestsFlowsheetElementId() {
		return hmrTestsFlowsheetElementId;
	}

	public void setHmrTestsFlowsheetElementId(Long hmrTestsFlowsheetElementId) {
		this.hmrTestsFlowsheetElementId = hmrTestsFlowsheetElementId;
	}

	public Integer getHmrTestsRuledate() {
		return hmrTestsRuledate;
	}

	public void setHmrTestsRuledate(Integer hmrTestsRuledate) {
		this.hmrTestsRuledate = hmrTestsRuledate;
	}
}
	