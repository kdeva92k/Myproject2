package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "ss_med_details")
public class SSMedDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getSsMedDetailsMapInboxId() {
		return ssMedDetailsMapInboxId;
	}

	public void setSsMedDetailsMapInboxId(Integer ssMedDetailsMapInboxId) {
		this.ssMedDetailsMapInboxId = ssMedDetailsMapInboxId;
	}

	public String getSsMedDetailsDrugDescription() {
		return ssMedDetailsDrugDescription;
	}

	public void setSsMedDetailsDrugDescription(String ssMedDetailsDrugDescription) {
		this.ssMedDetailsDrugDescription = ssMedDetailsDrugDescription;
	}

	public String getSsMedDetailsQuantity() {
		return ssMedDetailsQuantity;
	}

	public void setSsMedDetailsQuantity(String ssMedDetailsQuantity) {
		this.ssMedDetailsQuantity = ssMedDetailsQuantity;
	}

	public String getSsMedDetailsQuantityQualifier() {
		return ssMedDetailsQuantityQualifier;
	}

	public void setSsMedDetailsQuantityQualifier(
			String ssMedDetailsQuantityQualifier) {
		this.ssMedDetailsQuantityQualifier = ssMedDetailsQuantityQualifier;
	}

	public String getSsMedDetailsDaysSupply() {
		return ssMedDetailsDaysSupply;
	}

	public void setSsMedDetailsDaysSupply(String ssMedDetailsDaysSupply) {
		this.ssMedDetailsDaysSupply = ssMedDetailsDaysSupply;
	}

	public String getSsMedDetailsDirection() {
		return ssMedDetailsDirection;
	}

	public void setSsMedDetailsDirection(String ssMedDetailsDirection) {
		this.ssMedDetailsDirection = ssMedDetailsDirection;
	}

	public String getSsMedDetailsNotes() {
		return ssMedDetailsNotes;
	}

	public void setSsMedDetailsNotes(String ssMedDetailsNotes) {
		this.ssMedDetailsNotes = ssMedDetailsNotes;
	}

	public Integer getSsMedDetailsRefills() {
		return ssMedDetailsRefills;
	}

	public void setSsMedDetailsRefills(Integer ssMedDetailsRefills) {
		this.ssMedDetailsRefills = ssMedDetailsRefills;
	}

	public String getSsMedDetailsSubstitution() {
		return ssMedDetailsSubstitution;
	}

	public void setSsMedDetailsSubstitution(String ssMedDetailsSubstitution) {
		this.ssMedDetailsSubstitution = ssMedDetailsSubstitution;
	}

	public String getSsMedDetailsStrengthWithUnits() {
		return ssMedDetailsStrengthWithUnits;
	}

	public void setSsMedDetailsStrengthWithUnits(
			String ssMedDetailsStrengthWithUnits) {
		this.ssMedDetailsStrengthWithUnits = ssMedDetailsStrengthWithUnits;
	}

	public String getSsMedDetailsForm() {
		return ssMedDetailsForm;
	}

	public void setSsMedDetailsForm(String ssMedDetailsForm) {
		this.ssMedDetailsForm = ssMedDetailsForm;
	}

	public String getSsMedDetailsDiagnosisQualifier() {
		return ssMedDetailsDiagnosisQualifier;
	}

	public void setSsMedDetailsDiagnosisQualifier(
			String ssMedDetailsDiagnosisQualifier) {
		this.ssMedDetailsDiagnosisQualifier = ssMedDetailsDiagnosisQualifier;
	}

	public String getSsMedDetailsDiagnosisCode() {
		return ssMedDetailsDiagnosisCode;
	}

	public void setSsMedDetailsDiagnosisCode(String ssMedDetailsDiagnosisCode) {
		this.ssMedDetailsDiagnosisCode = ssMedDetailsDiagnosisCode;
	}

	public String getSsMedDetailsProductCode() {
		return ssMedDetailsProductCode;
	}

	public void setSsMedDetailsProductCode(String ssMedDetailsProductCode) {
		this.ssMedDetailsProductCode = ssMedDetailsProductCode;
	}

	public String getSsMedDetailsProductCodeQualifier() {
		return ssMedDetailsProductCodeQualifier;
	}

	public void setSsMedDetailsProductCodeQualifier(
			String ssMedDetailsProductCodeQualifier) {
		this.ssMedDetailsProductCodeQualifier = ssMedDetailsProductCodeQualifier;
	}

	public Date getSsMedDetailsStartDate() {
		return ssMedDetailsStartDate;
	}

	public void setSsMedDetailsStartDate(Date ssMedDetailsStartDate) {
		this.ssMedDetailsStartDate = ssMedDetailsStartDate;
	}

	public String getSsMedDetailsRefillsQualifier() {
		return ssMedDetailsRefillsQualifier;
	}

	public void setSsMedDetailsRefillsQualifier(String ssMedDetailsRefillsQualifier) {
		this.ssMedDetailsRefillsQualifier = ssMedDetailsRefillsQualifier;
	}

	@Id
	@Column(name="ss_med_details_map_inbox_id")
	private Integer ssMedDetailsMapInboxId;
	
	@Column(name="ss_med_details_drug_description")
	private String ssMedDetailsDrugDescription;

	@Column(name="ss_med_details_quantity")
	private String ssMedDetailsQuantity;

	@Column(name="ss_med_details_quantity_qualifier")
	private String ssMedDetailsQuantityQualifier;

	@Column(name="ss_med_details_days_supply")
	private String ssMedDetailsDaysSupply;

	@Column(name="ss_med_details_direction")
	private String ssMedDetailsDirection;

	@Column(name="ss_med_details_notes")
	private String ssMedDetailsNotes;

	@Column(name="ss_med_details_refills")
	private Integer ssMedDetailsRefills;

	@Column(name="ss_med_details_substitution")
	private String ssMedDetailsSubstitution;

	@Column(name="ss_med_details_strength_with_units")
	private String ssMedDetailsStrengthWithUnits;

	@Column(name="ss_med_details_form")
	private String ssMedDetailsForm;

	@Column(name="ss_med_details_diagnosis_qualifier")
	private String ssMedDetailsDiagnosisQualifier;

	@Column(name="ss_med_details_diagnosis_code")
	private String ssMedDetailsDiagnosisCode;

	@Column(name="ss_med_details_product_code")
	private String ssMedDetailsProductCode;

	@Column(name="ss_med_details_product_code_qualifier")
	private String ssMedDetailsProductCodeQualifier;

	@Column(name="ss_med_details_start_date")
	private Date ssMedDetailsStartDate;

	@Column(name="ss_med_details_refills_qualifier")
	private String ssMedDetailsRefillsQualifier;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ss_med_details_product_code",referencedColumnName="ndc_code",insertable=false,updatable=false)
	@JsonManagedReference
	NdcDrugBrandMap ndcdrugbrandmap;
	
	public NdcDrugBrandMap getNdcdrugbrandmap() {
		return ndcdrugbrandmap;
	}

	public void setNdcdrugbrandmap(NdcDrugBrandMap ndcdrugbrandmap) {
		this.ndcdrugbrandmap = ndcdrugbrandmap;
	}
	
	 @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
     @JoinColumn(name="ss_med_details_product_code",referencedColumnName="pkg_product_id",insertable=false, updatable=false)
     @JsonManagedReference
     NdcPkgProduct ndcpkgproduct;

	public NdcPkgProduct getNdcpkgproduct() {
		return ndcpkgproduct;
	}

	public void setNdcpkgproduct(NdcPkgProduct ndcpkgproduct) {
		this.ndcpkgproduct = ndcpkgproduct;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ss_med_details_quantity_qualifier",referencedColumnName="code",insertable=false,updatable=false)
	@JsonManagedReference
	PrescriptionUnits prescriptionunits;

	public PrescriptionUnits getPrescriptionunits() {
		return prescriptionunits;
	}

	public void setPrescriptionunits(PrescriptionUnits prescriptionunits) {
		this.prescriptionunits = prescriptionunits;
	}

	
}