package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vaccine_man_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VaccineManDetails {

	@Id
	@Column(name="vaccine_man_details_id")
	private Integer vaccineManDetailsId;

	@Column(name="vaccine_man_details_name")
	private String vaccineManDetailsName;

	@Column(name="vaccine_man_details_address")
	private String vaccineManDetailsAddress;

	@Column(name="vaccine_man_details_city")
	private String vaccineManDetailsCity;

	@Column(name="vaccine_man_details_state")
	private String vaccineManDetailsState;

	@Column(name="vaccine_man_details_zip")
	private String vaccineManDetailsZip;

	@Column(name="vaccine_man_details_phone")
	private String vaccineManDetailsPhone;

	@Column(name="vaccine_man_details_fax")
	private String vaccineManDetailsFax;

	@Column(name="vaccine_man_details_code")
	private String vaccineManDetailsCode;

	@Column(name="vaccine_man_details_mvxcode")
	private String vaccineManDetailsMvxcode;
	
	public Integer getVaccineManDetailsId() {
		return vaccineManDetailsId;
	}

	public void setVaccineManDetailsId(Integer vaccineManDetailsId) {
		this.vaccineManDetailsId = vaccineManDetailsId;
	}

	public String getVaccineManDetailsName() {
		return vaccineManDetailsName;
	}

	public void setVaccineManDetailsName(String vaccineManDetailsName) {
		this.vaccineManDetailsName = vaccineManDetailsName;
	}

	public String getVaccineManDetailsAddress() {
		return vaccineManDetailsAddress;
	}

	public void setVaccineManDetailsAddress(String vaccineManDetailsAddress) {
		this.vaccineManDetailsAddress = vaccineManDetailsAddress;
	}

	public String getVaccineManDetailsCity() {
		return vaccineManDetailsCity;
	}

	public void setVaccineManDetailsCity(String vaccineManDetailsCity) {
		this.vaccineManDetailsCity = vaccineManDetailsCity;
	}

	public String getVaccineManDetailsState() {
		return vaccineManDetailsState;
	}

	public void setVaccineManDetailsState(String vaccineManDetailsState) {
		this.vaccineManDetailsState = vaccineManDetailsState;
	}

	public String getVaccineManDetailsZip() {
		return vaccineManDetailsZip;
	}

	public void setVaccineManDetailsZip(String vaccineManDetailsZip) {
		this.vaccineManDetailsZip = vaccineManDetailsZip;
	}

	public String getVaccineManDetailsPhone() {
		return vaccineManDetailsPhone;
	}

	public void setVaccineManDetailsPhone(String vaccineManDetailsPhone) {
		this.vaccineManDetailsPhone = vaccineManDetailsPhone;
	}

	public String getVaccineManDetailsFax() {
		return vaccineManDetailsFax;
	}

	public void setVaccineManDetailsFax(String vaccineManDetailsFax) {
		this.vaccineManDetailsFax = vaccineManDetailsFax;
	}

	public String getVaccineManDetailsCode() {
		return vaccineManDetailsCode;
	}

	public void setVaccineManDetailsCode(String vaccineManDetailsCode) {
		this.vaccineManDetailsCode = vaccineManDetailsCode;
	}

	public String getVaccineManDetailsMvxcode() {
		return vaccineManDetailsMvxcode;
	}

	public void setVaccineManDetailsMvxcode(String vaccineManDetailsMvxcode) {
		this.vaccineManDetailsMvxcode = vaccineManDetailsMvxcode;
	}
}