package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class FS_DrugBean {
	
	private String genericDrugId;
	private String genericDrugName;
	private String drugName;
	private String status;
	private String statusOn;
	private String drugCode;
	private String drugCodeSystem;
	private String gender;
	
	private String due;
	private String dueCriteria;
	
	public String getGenericDrugName() {
		return genericDrugName;
	}
	public void setGenericDrugName(String genericDrugName) {
		this.genericDrugName = genericDrugName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusOn() {
		return statusOn;
	}
	public void setStatusOn(String statusOn) {
		this.statusOn = statusOn;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getDrugCodeSystem() {
		return drugCodeSystem;
	}
	public void setDrugCodeSystem(String drugCodeSystem) {
		this.drugCodeSystem = drugCodeSystem;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getDueCriteria() {
		return dueCriteria;
	}
	public void setDueCriteria(String dueCriteria) {
		this.dueCriteria = dueCriteria;
	}
	public String getGenericDrugId() {
		return genericDrugId;
	}
	public void setGenericDrugId(String genericDrugId) {
		this.genericDrugId = genericDrugId;
	}
}
