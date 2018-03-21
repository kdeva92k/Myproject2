package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class MedicationOrderQDM {

	String description;
	String dose;
	String form;
	String route;
	String status;
	String refills;
	String days;
	Date orderDate;
	String startDate;
	Integer CMD;
	
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

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Integer getCMD() {
		return CMD;
	}

	public void setCMD(Integer cMD) {
		CMD = cMD;
	}

	public MedicationOrderQDM(String description,String dose,String form,String route,String status,String refills,String days,Date orderDate,String startDate){
		
		this.description=description;
		this.dose=dose;
		this.form=form;
		this.route=route;
		this.status=status;
		this.refills=refills;
		this.days=days;
		this.orderDate=orderDate;
		this.startDate=startDate;
		
	}
	
}
