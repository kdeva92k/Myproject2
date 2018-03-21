package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "service_balances")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceBalances {

	@Id
	@Column(name="service_id")
	private Integer serviceId;

	@Column(name="service_balance")
	private Double serviceBalance;

	@Column(name="patient_balance")
	private Double patientBalance;

	@Column(name="insurance_balance")
	private Double insuranceBalance;

	@Column(name="primary_insurance_payment")
	private Double primaryInsurancePayment;

	@Column(name="secondary_insurance_payment")
	private Double secondaryInsurancePayment;

	@Column(name="tertiary_insurance_payment")
	private Double tertiaryInsurancePayment;

	@Column(name="patient_payment")
	private Double patientPayment;

	@Column(name="primary_insurance_adjuestment")
	private Double primaryInsuranceAdjuestment;

	@Column(name="secondary_insurance_adjuestment")
	private Double secondaryInsuranceAdjuestment;

	@Column(name="tertiary_insurance_adjuestment")
	private Double tertiaryInsuranceAdjuestment;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Double getServiceBalance() {
		return serviceBalance;
	}

	public void setServiceBalance(Double serviceBalance) {
		this.serviceBalance = serviceBalance;
	}

	public Double getPatientBalance() {
		return patientBalance;
	}

	public void setPatientBalance(Double patientBalance) {
		this.patientBalance = patientBalance;
	}

	public Double getInsuranceBalance() {
		return insuranceBalance;
	}

	public void setInsuranceBalance(Double insuranceBalance) {
		this.insuranceBalance = insuranceBalance;
	}

	public Double getPrimaryInsurancePayment() {
		return primaryInsurancePayment;
	}

	public void setPrimaryInsurancePayment(Double primaryInsurancePayment) {
		this.primaryInsurancePayment = primaryInsurancePayment;
	}

	public Double getSecondaryInsurancePayment() {
		return secondaryInsurancePayment;
	}

	public void setSecondaryInsurancePayment(Double secondaryInsurancePayment) {
		this.secondaryInsurancePayment = secondaryInsurancePayment;
	}

	public Double getTertiaryInsurancePayment() {
		return tertiaryInsurancePayment;
	}

	public void setTertiaryInsurancePayment(Double tertiaryInsurancePayment) {
		this.tertiaryInsurancePayment = tertiaryInsurancePayment;
	}

	public Double getPatientPayment() {
		return patientPayment;
	}

	public void setPatientPayment(Double patientPayment) {
		this.patientPayment = patientPayment;
	}

	public Double getPrimaryInsuranceAdjuestment() {
		return primaryInsuranceAdjuestment;
	}

	public void setPrimaryInsuranceAdjuestment(Double primaryInsuranceAdjuestment) {
		this.primaryInsuranceAdjuestment = primaryInsuranceAdjuestment;
	}

	public Double getSecondaryInsuranceAdjuestment() {
		return secondaryInsuranceAdjuestment;
	}

	public void setSecondaryInsuranceAdjuestment(
			Double secondaryInsuranceAdjuestment) {
		this.secondaryInsuranceAdjuestment = secondaryInsuranceAdjuestment;
	}

	public Double getTertiaryInsuranceAdjuestment() {
		return tertiaryInsuranceAdjuestment;
	}

	public void setTertiaryInsuranceAdjuestment(Double tertiaryInsuranceAdjuestment) {
		this.tertiaryInsuranceAdjuestment = tertiaryInsuranceAdjuestment;
	}
	
	
}