package com.glenwood.glaceemr.server.application.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "expected_cpt_amount")
public class ExpectedCptAmount {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expected_cpt_amount_expected_cpt_amount_id_seq")
	@SequenceGenerator(name = "expected_cpt_amount_expected_cpt_amount_id_seq", sequenceName = "expected_cpt_amount_expected_cpt_amount_id_seq", allocationSize = 1)
	@Column(name="expected_cpt_amount_Id")
	private Integer expected_cpt_amount_Id;
	
	@Column(name="expected_cpt_amount_cptId")
	private Integer expected_cpt_amount_cptId;
	
	@Column(name="expected_cpt_amount_modifier1")
	private String expected_cpt_amount_modifier1;
	
	@Column(name="expected_cpt_amount_modifier2")
	private String expected_cpt_amount_modifier2;
	
	@Column(name="expected_cpt_amount_insuranceId")
	private Integer expected_cpt_amount_insuranceId;
	
	@Column(name="expected_cpt_amount_unknown1")
	private String expected_cpt_amount_unknown1;
	
	@Column(name="expected_cpt_amount_allowedamt")
	private BigDecimal expected_cpt_amount_allowedamt;
	
	@Column(name="h555555")
	private Integer h555555;

	@Column(name="expected_cpt_amount_postype")
	private Integer expected_cpt_amount_postype;
	
	@Column(name="expected_cpt_amount_year")
	private Integer expected_cpt_amount_year;
	
	@Column(name="expected_cpt_amount_CreatedBy")
	private String expected_cpt_amount_CreatedBy;
	
	@Column(name="expected_cpt_amount_ModifiedBy")
	private String expected_cpt_amount_ModifiedBy;
	
	@Column(name="expected_cpt_amount_CreatedOn")
	private String expected_cpt_amount_CreatedOn;
	
	@Column(name="expected_cpt_amount_ModifiedOn")
	private String expected_cpt_amount_ModifiedOn;

	public Integer getexpected_cpt_amount_Id() {
		return expected_cpt_amount_Id;
	}

	public void setexpected_cpt_amount_Id(Integer expected_cpt_amount_Id) {
		this.expected_cpt_amount_Id = expected_cpt_amount_Id;
	}

	public Integer getexpected_cpt_amount_cptId() {
		return expected_cpt_amount_cptId;
	}

	public void setexpected_cpt_amount_cptId(Integer expected_cpt_amount_cptId) {
		this.expected_cpt_amount_cptId = expected_cpt_amount_cptId;
	}

	public String getexpected_cpt_amount_modifier1() {
		return expected_cpt_amount_modifier1;
	}

	public void setexpected_cpt_amount_modifier1(String expected_cpt_amount_modifier1) {
		this.expected_cpt_amount_modifier1 = expected_cpt_amount_modifier1;
	}

	public String getexpected_cpt_amount_modifier2() {
		return expected_cpt_amount_modifier2;
	}

	public void setexpected_cpt_amount_modifier2(String expected_cpt_amount_modifier2) {
		this.expected_cpt_amount_modifier2 = expected_cpt_amount_modifier2;
	}

	public Integer getexpected_cpt_amount_insuranceId() {
		return expected_cpt_amount_insuranceId;
	}

	public void setexpected_cpt_amount_insuranceId(Integer expected_cpt_amount_insuranceId) {
		this.expected_cpt_amount_insuranceId = expected_cpt_amount_insuranceId;
	}

	public String getexpected_cpt_amount_unknown1() {
		return expected_cpt_amount_unknown1;
	}

	public void setexpected_cpt_amount_unknown1(String expected_cpt_amount_unknown1) {
		this.expected_cpt_amount_unknown1 = expected_cpt_amount_unknown1;
	}

	public BigDecimal getexpected_cpt_amount_allowedamt() {
		return expected_cpt_amount_allowedamt;
	}

	public void setexpected_cpt_amount_allowedamt(BigDecimal expected_cpt_amount_allowedamt) {
		this.expected_cpt_amount_allowedamt = expected_cpt_amount_allowedamt;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Integer getexpected_cpt_amount_postype() {
		return expected_cpt_amount_postype;
	}

	public void setexpected_cpt_amount_postype(Integer expected_cpt_amount_postype) {
		this.expected_cpt_amount_postype = expected_cpt_amount_postype;
	}

	public Integer getexpected_cpt_amount_year() {
		return expected_cpt_amount_year;
	}

	public void setexpected_cpt_amount_year(Integer expected_cpt_amount_year) {
		this.expected_cpt_amount_year = expected_cpt_amount_year;
	}

	public String getexpected_cpt_amount_CreatedBy() {
		return expected_cpt_amount_CreatedBy;
	}

	public void setexpected_cpt_amount_CreatedBy(String expected_cpt_amount_CreatedBy) {
		this.expected_cpt_amount_CreatedBy = expected_cpt_amount_CreatedBy;
	}

	public String getexpected_cpt_amount_ModifiedBy() {
		return expected_cpt_amount_ModifiedBy;
	}

	public void setexpected_cpt_amount_ModifiedBy(String expected_cpt_amount_ModifiedBy) {
		this.expected_cpt_amount_ModifiedBy = expected_cpt_amount_ModifiedBy;
	}

	public String getexpected_cpt_amount_CreatedOn() {
		return expected_cpt_amount_CreatedOn;
	}

	public void setexpected_cpt_amount_CreatedOn(String expected_cpt_amount_CreatedOn) {
		this.expected_cpt_amount_CreatedOn = expected_cpt_amount_CreatedOn;
	}

	public String getexpected_cpt_amount_ModifiedOn() {
		return expected_cpt_amount_ModifiedOn;
	}

	public void setexpected_cpt_amount_ModifiedOn(String expected_cpt_amount_ModifiedOn) {
		this.expected_cpt_amount_ModifiedOn = expected_cpt_amount_ModifiedOn;
	}
}
