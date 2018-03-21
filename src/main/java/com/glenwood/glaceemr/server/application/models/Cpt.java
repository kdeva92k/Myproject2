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


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "cpt")
@JsonIgnoreProperties(ignoreUnknown = true)

public class Cpt {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cpt_cpt_id_seq")
	@SequenceGenerator(name = "cpt_cpt_id_seq", sequenceName = "cpt_cpt_id_seq", allocationSize = 1)
	@Column(name="cpt_id")
	private Integer cptId;

	@Column(name="cpt_description")
	private String cptDescription;

	@Column(name="cpt_short_cut_desc")
	private String cptShortCutDesc;

	@Column(name="cpt_cpttype")
	private Short cptCpttype;

	@Column(name="cpt_cost")
	private Double cptCost;

	@Column(name="cpt_mcallowed")
	private Double cptMcallowed;

	@Column(name="cpt_rvu")
	private Double cptRvu;

	@Column(name="cpt_groupid")
	private Integer cptGroupid;

	@Column(name="cpt_orderby")
	private Integer cptOrderby;

	@Column(name="cpt_hitcount")
	private Integer cptHitcount;

	@Column(name="cpt_isactive")
	private Boolean cptIsactive;

	@Column(name="cpt_dosage_units")
	private Short cptDosageUnits;

	@Column(name="cpt_not_include_insid")
	private String cptNotIncludeInsid;

	@Column(name="cpt_pos_code")
	private String cptPosCode;

	@Column(name="cpt_type_of_service")
	private Short cptTypeOfService;

	@Column(name="cpt_mod_1")
	private String cptMod1;

	@Column(name="cpt_mod_2")
	private String cptMod2;

	@Column(name="cpt_mod_3")
	private String cptMod3;

	@Column(name="cpt_mod_4")
	private String cptMod4;

	@Column(name="cpt_is_hippa")
	private Boolean cptIsHippa;

	@Column(name="cpt_is_purchased")
	private Boolean cptIsPurchased;

	@Column(name="cpt_purchase_cost")
	private Double cptPurchaseCost;

	@Column(name="cpt_ndc")
	private String cptNdc;

	@Column(name="cpt_unknown1")
	private String cptUnknown1;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="cpt_creation_date")
	private Timestamp cptCreationDate;

	@Column(name="cpt_created_by")
	private String cptCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="cpt_modified_date")
	private Timestamp cptModifiedDate;

	@Column(name="cpt_modified_by")
	private String cptModifiedBy;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="cpt_cptcode")
	private String cptCptcode;

	@Column(name="cpt_include_insid")
	private String cptIncludeInsid;

	@Column(name="cpt_is_include")
	private Boolean cptIsInclude;

	@Column(name="cpt_unit_charge")
	private Double cptUnitCharge;

	@Column(name="cpt_unit")
	private String cptUnit;

	@Column(name="cpt_min_dosage")
	private String cptMinDosage;

	@Column(name="cpt_max_dosage")
	private String cptMaxDosage;

	@Column(name="cpt_isadmin")
	private Boolean cptIsadmin;
	
	@Column(name="cpt_use_defaultcopay")
	private Boolean cptUseDefaultcopay;
	
    public Boolean getCptUseDefaultcopay() {
		return cptUseDefaultcopay;
	}


	public void setCptUseDefaultcopay(Boolean cptUseDefaultcopay) {
		this.cptUseDefaultcopay = cptUseDefaultcopay;
	}


	@ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="cpt_cptcode", referencedColumnName="cpt_code" , insertable=false, updatable=false)
    Quickcpt quickCpt;
	
	public Quickcpt getQuickCpt() {
		return quickCpt;
	}


	public void setQuickCpt(Quickcpt quickCpt) {
		this.quickCpt = quickCpt;
	}


	public Integer getCptId() {
		return cptId;
	}


	public void setCptId(Integer cptId) {
		this.cptId = cptId;
	}


	public String getCptDescription() {
		return cptDescription;
	}


	public void setCptDescription(String cptDescription) {
		this.cptDescription = cptDescription;
	}


	public String getCptShortCutDesc() {
		return cptShortCutDesc;
	}


	public void setCptShortCutDesc(String cptShortCutDesc) {
		this.cptShortCutDesc = cptShortCutDesc;
	}


	public Short getCptCpttype() {
		return cptCpttype;
	}


	public void setCptCpttype(Short cptCpttype) {
		this.cptCpttype = cptCpttype;
	}


	public Double getCptCost() {
		return cptCost;
	}


	public void setCptCost(Double cptCost) {
		this.cptCost = cptCost;
	}


	public Double getCptMcallowed() {
		return cptMcallowed;
	}


	public void setCptMcallowed(Double cptMcallowed) {
		this.cptMcallowed = cptMcallowed;
	}


	public Double getCptRvu() {
		return cptRvu;
	}


	public void setCptRvu(Double cptRvu) {
		this.cptRvu = cptRvu;
	}


	public Integer getCptGroupid() {
		return cptGroupid;
	}


	public void setCptGroupid(Integer cptGroupid) {
		this.cptGroupid = cptGroupid;
	}


	public Integer getCptOrderby() {
		return cptOrderby;
	}


	public void setCptOrderby(Integer cptOrderby) {
		this.cptOrderby = cptOrderby;
	}


	public Integer getCptHitcount() {
		return cptHitcount;
	}


	public void setCptHitcount(Integer cptHitcount) {
		this.cptHitcount = cptHitcount;
	}


	public Boolean getCptIsactive() {
		return cptIsactive;
	}


	public void setCptIsactive(Boolean cptIsactive) {
		this.cptIsactive = cptIsactive;
	}


	public Short getCptDosageUnits() {
		return cptDosageUnits;
	}


	public void setCptDosageUnits(Short cptDosageUnits) {
		this.cptDosageUnits = cptDosageUnits;
	}


	public String getCptNotIncludeInsid() {
		return cptNotIncludeInsid;
	}


	public void setCptNotIncludeInsid(String cptNotIncludeInsid) {
		this.cptNotIncludeInsid = cptNotIncludeInsid;
	}


	public String getCptPosCode() {
		return cptPosCode;
	}


	public void setCptPosCode(String cptPosCode) {
		this.cptPosCode = cptPosCode;
	}


	public Short getCptTypeOfService() {
		return cptTypeOfService;
	}


	public void setCptTypeOfService(Short cptTypeOfService) {
		this.cptTypeOfService = cptTypeOfService;
	}


	public String getCptMod1() {
		return cptMod1;
	}


	public void setCptMod1(String cptMod1) {
		this.cptMod1 = cptMod1;
	}


	public String getCptMod2() {
		return cptMod2;
	}


	public void setCptMod2(String cptMod2) {
		this.cptMod2 = cptMod2;
	}


	public String getCptMod3() {
		return cptMod3;
	}


	public void setCptMod3(String cptMod3) {
		this.cptMod3 = cptMod3;
	}


	public String getCptMod4() {
		return cptMod4;
	}


	public void setCptMod4(String cptMod4) {
		this.cptMod4 = cptMod4;
	}


	public Boolean getCptIsHippa() {
		return cptIsHippa;
	}


	public void setCptIsHippa(Boolean cptIsHippa) {
		this.cptIsHippa = cptIsHippa;
	}


	public Boolean getCptIsPurchased() {
		return cptIsPurchased;
	}


	public void setCptIsPurchased(Boolean cptIsPurchased) {
		this.cptIsPurchased = cptIsPurchased;
	}


	public Double getCptPurchaseCost() {
		return cptPurchaseCost;
	}


	public void setCptPurchaseCost(Double cptPurchaseCost) {
		this.cptPurchaseCost = cptPurchaseCost;
	}


	public String getCptNdc() {
		return cptNdc;
	}


	public void setCptNdc(String cptNdc) {
		this.cptNdc = cptNdc;
	}


	public String getCptUnknown1() {
		return cptUnknown1;
	}


	public void setCptUnknown1(String cptUnknown1) {
		this.cptUnknown1 = cptUnknown1;
	}


	public Timestamp getCptCreationDate() {
		return cptCreationDate;
	}


	public void setCptCreationDate(Timestamp cptCreationDate) {
		this.cptCreationDate = cptCreationDate;
	}


	public String getCptCreatedBy() {
		return cptCreatedBy;
	}


	public void setCptCreatedBy(String cptCreatedBy) {
		this.cptCreatedBy = cptCreatedBy;
	}


	public Timestamp getCptModifiedDate() {
		return cptModifiedDate;
	}


	public void setCptModifiedDate(Timestamp cptModifiedDate) {
		this.cptModifiedDate = cptModifiedDate;
	}


	public String getCptModifiedBy() {
		return cptModifiedBy;
	}


	public void setCptModifiedBy(String cptModifiedBy) {
		this.cptModifiedBy = cptModifiedBy;
	}


	public Integer getH555555() {
		return h555555;
	}


	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}


	public String getCptCptcode() {
		return cptCptcode;
	}


	public void setCptCptcode(String cptCptcode) {
		this.cptCptcode = cptCptcode;
	}


	public String getCptIncludeInsid() {
		return cptIncludeInsid;
	}


	public void setCptIncludeInsid(String cptIncludeInsid) {
		this.cptIncludeInsid = cptIncludeInsid;
	}


	public Boolean getCptIsInclude() {
		return cptIsInclude;
	}


	public void setCptIsInclude(Boolean cptIsInclude) {
		this.cptIsInclude = cptIsInclude;
	}


	public Double getCptUnitCharge() {
		return cptUnitCharge;
	}


	public void setCptUnitCharge(Double cptUnitCharge) {
		this.cptUnitCharge = cptUnitCharge;
	}


	public String getCptUnit() {
		return cptUnit;
	}


	public void setCptUnit(String cptUnit) {
		this.cptUnit = cptUnit;
	}


	public String getCptMinDosage() {
		return cptMinDosage;
	}


	public void setCptMinDosage(String cptMinDosage) {
		this.cptMinDosage = cptMinDosage;
	}


	public String getCptMaxDosage() {
		return cptMaxDosage;
	}


	public void setCptMaxDosage(String cptMaxDosage) {
		this.cptMaxDosage = cptMaxDosage;
	}


	public Boolean getCptIsadmin() {
		return cptIsadmin;
	}


	public void setCptIsadmin(Boolean cptIsadmin) {
		this.cptIsadmin = cptIsadmin;
	}
	
}