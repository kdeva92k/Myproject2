package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class MedicationQDM {
	int id;
	String description;
	String dose;
	String form;
	Integer route;
	String status;
	String refills;
	String days;
	Date orderDate;
	Date startDate;
	Integer CMD;
	String code;
	String frequency;
	
	Date attestationDate;
	boolean reviewStatus;
	
	public Date getAttestationDate() {
		return attestationDate;
	}

	public void setAttestationDate(Date attestationDate) {
		this.attestationDate = attestationDate;
	}

	public boolean getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(boolean reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public Integer getRoute() {
		return route;
	}

	public void setRoute(Integer route) {
		this.route = route;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRefills() {
		return refills;
	}

	public void setRefills(String refills) {
		this.refills = refills;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getCMD() {
		return CMD;
	}

	public void setCMD(Integer cMD) {
		CMD = cMD;
	}

	public MedicationQDM(Integer id,String description,String dose,String form,Integer route,String status,String frequency,String code,String refills,String days,Date orderDate,Date startDate){
		this.id=id;
		this.description=description;
		this.dose=dose;
		this.form=form;
		this.route=route;
		this.status=status;
		this.code=code;
		this.frequency=frequency;
		this.refills=refills;
		this.days=days;
		this.orderDate=orderDate;
		this.startDate=startDate;
		
	}
	
	public MedicationQDM(Date attestationDate,boolean reviewStatus){
		this.attestationDate=attestationDate;
		this.reviewStatus=reviewStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
