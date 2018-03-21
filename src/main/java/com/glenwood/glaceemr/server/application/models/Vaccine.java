package com.glenwood.glaceemr.server.application.models;

public class Vaccine {

	int vaccineId;
	
	String vaccineName;
	
	String vaccineCvx;
	
	public Vaccine(){
		
	}

	public Vaccine(int vaccineId, String vaccineName, String vaccineCvx) {
		super();
		this.vaccineId = vaccineId;
		this.vaccineName = vaccineName;
		this.vaccineCvx = vaccineCvx;
	}

	public int getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(int vaccineId) {
		this.vaccineId = vaccineId;
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
	
}
