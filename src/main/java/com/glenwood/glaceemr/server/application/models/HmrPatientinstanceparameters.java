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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "hmr_patientinstanceparameters")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HmrPatientinstanceparameters {
	@Id
	@Column(name="testid")
	@SequenceGenerator(name="hmr_patientinstanceparameters_testid_seq", sequenceName="hmr_patientinstanceparameters_testid_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="hmr_patientinstanceparameters_testid_seq")
	private Integer hmrPatientinstanceparametersTestid;

	@Column(name="test_desc", length=100)
	private String hmrPatientinstanceparametersTestDesc;
	
	@Column(name="group_id")
	private Integer hmrPatientinstanceparametersTestDescGroupId;
	
	@Column(name="gender")
	private Integer hmrPatientinstanceparametersGender;
	
	@Column(name="sort_by")
	private Integer hmrPatientinstanceparametersSortBy;
	
	@Column(name="is_active")
	private Integer hmrPatientinstanceparametersIsActive;
	
	@Column(name="billingcode")
	private String hmrPatientinstanceparametersBillingcode;
	
	@Column(name="dxs")
	private String hmrPatientinstanceparametersDxs;
	
	@Column(name="labname", length=100)
	private String hmrPatientinstanceparametersLabname;
	
	@Column(name="drugs")
	private String hmrPatientinstanceparametersDrugs;
	
	@Column(name="from_days")
	private Integer hmrPatientinstanceparametersFromDays;
	
	@Column(name="to_days")
	private Integer hmrPatientinstanceparametersToDays;
	
	@Column(name="dxdesc")
	private String hmrPatientinstanceparametersDxdesc;
	
	@Column(name="comments")
	private String hmrPatientinstanceparametersComments;
	
	@Column(name="maxagetoshowalert")
	private Integer hmrPatientinstanceparametersMaxagetoshowalert;
	
	@Column(name="isdxbased")
	private Integer hmrPatientinstanceparametersIsdxbased;
	
	@Column(name="overduelimit")
	private Integer hmrPatientinstanceparametersOverduelimit;
	
	@Column(name="overduetype")
	private Integer hmrPatientinstanceparametersOverduetype;

	@Column(name="patientid")
	private Integer hmrPatientinstanceparametersPatientid;

	@Column(name="modified_by")
	private Integer hmrPatientinstanceparametersModifiedBy;

	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="modified_on")
	private Timestamp hmrPatientinstanceparametersModifiedOn;

	@Column(name="initiated_by")
	private Integer hmrPatientinstanceparametersInitiatedBy;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="initiated_on")
	private Timestamp hmrPatientinstanceparametersInitiatedOn;

	@Column(name="hmr_patientinstanceparameters_alert_type")
	private Integer hmrPatientinstanceparametersAlertType;

	@Column(name="hmr_patientinstanceparameters_flowsheet_element_type")
	private Integer hmrPatientinstanceparametersFlowsheetElementType;

	@Column(name="hmr_patientinstanceparameters_flowsheet_element_id")
	private Long hmrPatientinstanceparametersFlowsheetElementId;

	@Column(name="hmr_patientinstanceparameters_ruledate")
	private Integer hmrPatientinstanceparametersRuledate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="group_id",referencedColumnName="hmr_categories_id", nullable=false, insertable=false, updatable=false)
	HmrCategories hmrCategoriesTable;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="hmrPatientinstanceTestId")
	@JsonManagedReference
	List<HmrPatientinstance> hmrPatientinstanceTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="modified_by",referencedColumnName="emp_profile_empid", nullable=false, insertable=false, updatable=false)
	EmployeeProfile empProfileHmrTestsModifiedByTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="initiated_by",referencedColumnName="emp_profile_empid", nullable=false, insertable=false, updatable=false)
	EmployeeProfile empProfileHmrTestsCreatedByTable;
	
	public Integer getHmrPatientinstanceparametersTestid() {
		return hmrPatientinstanceparametersTestid;
	}

	public void setHmrPatientinstanceparametersTestid(
			Integer hmrPatientinstanceparametersTestid) {
		this.hmrPatientinstanceparametersTestid = hmrPatientinstanceparametersTestid;
	}

	public String getHmrPatientinstanceparametersTestDesc() {
		return hmrPatientinstanceparametersTestDesc;
	}

	public void setHmrPatientinstanceparametersTestDesc(
			String hmrPatientinstanceparametersTestDesc) {
		this.hmrPatientinstanceparametersTestDesc = hmrPatientinstanceparametersTestDesc;
	}

	public Integer getHmrPatientinstanceparametersTestDescGroupId() {
		return hmrPatientinstanceparametersTestDescGroupId;
	}

	public void setHmrPatientinstanceparametersTestDescGroupId(
			Integer hmrPatientinstanceparametersTestDescGroupId) {
		this.hmrPatientinstanceparametersTestDescGroupId = hmrPatientinstanceparametersTestDescGroupId;
	}

	public Integer getHmrPatientinstanceparametersGender() {
		return hmrPatientinstanceparametersGender;
	}

	public void setHmrPatientinstanceparametersGender(
			Integer hmrPatientinstanceparametersGender) {
		this.hmrPatientinstanceparametersGender = hmrPatientinstanceparametersGender;
	}

	public Integer getHmrPatientinstanceparametersSortBy() {
		return hmrPatientinstanceparametersSortBy;
	}

	public void setHmrPatientinstanceparametersSortBy(
			Integer hmrPatientinstanceparametersSortBy) {
		this.hmrPatientinstanceparametersSortBy = hmrPatientinstanceparametersSortBy;
	}

	public Integer getHmrPatientinstanceparametersIsActive() {
		return hmrPatientinstanceparametersIsActive;
	}

	public void setHmrPatientinstanceparametersIsActive(
			Integer hmrPatientinstanceparametersIsActive) {
		this.hmrPatientinstanceparametersIsActive = hmrPatientinstanceparametersIsActive;
	}

	public String getHmrPatientinstanceparametersBillingcode() {
		return hmrPatientinstanceparametersBillingcode;
	}

	public void setHmrPatientinstanceparametersBillingcode(
			String hmrPatientinstanceparametersBillingcode) {
		this.hmrPatientinstanceparametersBillingcode = hmrPatientinstanceparametersBillingcode;
	}

	public String getHmrPatientinstanceparametersDxs() {
		return hmrPatientinstanceparametersDxs;
	}

	public void setHmrPatientinstanceparametersDxs(
			String hmrPatientinstanceparametersDxs) {
		this.hmrPatientinstanceparametersDxs = hmrPatientinstanceparametersDxs;
	}

	public String getHmrPatientinstanceparametersLabname() {
		return hmrPatientinstanceparametersLabname;
	}

	public void setHmrPatientinstanceparametersLabname(
			String hmrPatientinstanceparametersLabname) {
		this.hmrPatientinstanceparametersLabname = hmrPatientinstanceparametersLabname;
	}

	public String getHmrPatientinstanceparametersDrugs() {
		return hmrPatientinstanceparametersDrugs;
	}

	public void setHmrPatientinstanceparametersDrugs(
			String hmrPatientinstanceparametersDrugs) {
		this.hmrPatientinstanceparametersDrugs = hmrPatientinstanceparametersDrugs;
	}

	public Integer getHmrPatientinstanceparametersFromDays() {
		return hmrPatientinstanceparametersFromDays;
	}

	public void setHmrPatientinstanceparametersFromDays(
			Integer hmrPatientinstanceparametersFromDays) {
		this.hmrPatientinstanceparametersFromDays = hmrPatientinstanceparametersFromDays;
	}

	public Integer getHmrPatientinstanceparametersToDays() {
		return hmrPatientinstanceparametersToDays;
	}

	public void setHmrPatientinstanceparametersToDays(
			Integer hmrPatientinstanceparametersToDays) {
		this.hmrPatientinstanceparametersToDays = hmrPatientinstanceparametersToDays;
	}

	public String getHmrPatientinstanceparametersDxdesc() {
		return hmrPatientinstanceparametersDxdesc;
	}

	public void setHmrPatientinstanceparametersDxdesc(
			String hmrPatientinstanceparametersDxdesc) {
		this.hmrPatientinstanceparametersDxdesc = hmrPatientinstanceparametersDxdesc;
	}

	public String getHmrPatientinstanceparametersComments() {
		return hmrPatientinstanceparametersComments;
	}

	public void setHmrPatientinstanceparametersComments(
			String hmrPatientinstanceparametersComments) {
		this.hmrPatientinstanceparametersComments = hmrPatientinstanceparametersComments;
	}

	public Integer getHmrPatientinstanceparametersMaxagetoshowalert() {
		return hmrPatientinstanceparametersMaxagetoshowalert;
	}

	public void setHmrPatientinstanceparametersMaxagetoshowalert(
			Integer hmrPatientinstanceparametersMaxagetoshowalert) {
		this.hmrPatientinstanceparametersMaxagetoshowalert = hmrPatientinstanceparametersMaxagetoshowalert;
	}

	public Integer getHmrPatientinstanceparametersIsdxbased() {
		return hmrPatientinstanceparametersIsdxbased;
	}

	public void setHmrPatientinstanceparametersIsdxbased(
			Integer hmrPatientinstanceparametersIsdxbased) {
		this.hmrPatientinstanceparametersIsdxbased = hmrPatientinstanceparametersIsdxbased;
	}

	public Integer getHmrPatientinstanceparametersOverduelimit() {
		return hmrPatientinstanceparametersOverduelimit;
	}

	public void setHmrPatientinstanceparametersOverduelimit(
			Integer hmrPatientinstanceparametersOverduelimit) {
		this.hmrPatientinstanceparametersOverduelimit = hmrPatientinstanceparametersOverduelimit;
	}

	public Integer getHmrPatientinstanceparametersOverduetype() {
		return hmrPatientinstanceparametersOverduetype;
	}

	public void setHmrPatientinstanceparametersOverduetype(
			Integer hmrPatientinstanceparametersOverduetype) {
		this.hmrPatientinstanceparametersOverduetype = hmrPatientinstanceparametersOverduetype;
	}

	public Integer getHmrPatientinstanceparametersPatientid() {
		return hmrPatientinstanceparametersPatientid;
	}

	public void setHmrPatientinstanceparametersPatientid(
			Integer hmrPatientinstanceparametersPatientid) {
		this.hmrPatientinstanceparametersPatientid = hmrPatientinstanceparametersPatientid;
	}

	public Integer getHmrPatientinstanceparametersModifiedBy() {
		return hmrPatientinstanceparametersModifiedBy;
	}

	public void setHmrPatientinstanceparametersModifiedBy(
			Integer hmrPatientinstanceparametersModifiedBy) {
		this.hmrPatientinstanceparametersModifiedBy = hmrPatientinstanceparametersModifiedBy;
	}

	public Timestamp getHmrPatientinstanceparametersModifiedOn() {
		return hmrPatientinstanceparametersModifiedOn;
	}

	public void setHmrPatientinstanceparametersModifiedOn(
			Timestamp hmrPatientinstanceparametersModifiedOn) {
		this.hmrPatientinstanceparametersModifiedOn = hmrPatientinstanceparametersModifiedOn;
	}

	public Integer getHmrPatientinstanceparametersInitiatedBy() {
		return hmrPatientinstanceparametersInitiatedBy;
	}

	public void setHmrPatientinstanceparametersInitiatedBy(
			Integer hmrPatientinstanceparametersInitiatedBy) {
		this.hmrPatientinstanceparametersInitiatedBy = hmrPatientinstanceparametersInitiatedBy;
	}

	public Timestamp getHmrPatientinstanceparametersInitiatedOn() {
		return hmrPatientinstanceparametersInitiatedOn;
	}

	public void setHmrPatientinstanceparametersInitiatedOn(
			Timestamp hmrPatientinstanceparametersInitiatedOn) {
		this.hmrPatientinstanceparametersInitiatedOn = hmrPatientinstanceparametersInitiatedOn;
	}

	public Integer getHmrPatientinstanceparametersAlertType() {
		return hmrPatientinstanceparametersAlertType;
	}

	public void setHmrPatientinstanceparametersAlertType(
			Integer hmrPatientinstanceparametersAlertType) {
		this.hmrPatientinstanceparametersAlertType = hmrPatientinstanceparametersAlertType;
	}

	public Integer getHmrPatientinstanceparametersFlowsheetElementType() {
		return hmrPatientinstanceparametersFlowsheetElementType;
	}

	public void setHmrPatientinstanceparametersFlowsheetElementType(
			Integer hmrPatientinstanceparametersFlowsheetElementType) {
		this.hmrPatientinstanceparametersFlowsheetElementType = hmrPatientinstanceparametersFlowsheetElementType;
	}

	public Long getHmrPatientinstanceparametersFlowsheetElementId() {
		return hmrPatientinstanceparametersFlowsheetElementId;
	}

	public void setHmrPatientinstanceparametersFlowsheetElementId(
			Long hmrPatientinstanceparametersFlowsheetElementId) {
		this.hmrPatientinstanceparametersFlowsheetElementId = hmrPatientinstanceparametersFlowsheetElementId;
	}

	public Integer getHmrPatientinstanceparametersRuledate() {
		return hmrPatientinstanceparametersRuledate;
	}

	public void setHmrPatientinstanceparametersRuledate(
			Integer hmrPatientinstanceparametersRuledate) {
		this.hmrPatientinstanceparametersRuledate = hmrPatientinstanceparametersRuledate;
	}

}