package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

public class VaccineUpdateBean {

	int patientId;
	
	int chartId;
	
	int vaccineId;
	
	String patientName;
	
	String vaccineName;
	
	String vaccineCvx;
	
	Date administeredDate;
	
	int updateReasonId;
	
	String updateReasonName;
	
	String notes;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getChartId() {
		return chartId;
	}

	public void setChartId(int chartId) {
		this.chartId = chartId;
	}

	public int getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(int vaccineId) {
		this.vaccineId = vaccineId;
	}
	
	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getVaccineCvx() {
		return vaccineCvx;
	}

	public void setVaccineCvx(String vaccineCvx) {
		this.vaccineCvx = vaccineCvx;
	}

	public Date getAdministeredDate() {
		return administeredDate;
	}

	public void setAdministeredDate(Date administeredDate) {
		this.administeredDate = administeredDate;
	}

	public int getUpdateReasonId() {
		return updateReasonId;
	}

	public void setUpdateReasonId(int updateReasonId) {
		this.updateReasonId = updateReasonId;
	}

	public String getUpdateReasonName() {
		return updateReasonName;
	}

	public void setUpdateReasonName(String updateReasonName) {
		this.updateReasonName = updateReasonName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
