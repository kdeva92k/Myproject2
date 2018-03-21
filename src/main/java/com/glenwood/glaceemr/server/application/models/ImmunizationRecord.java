package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

public class ImmunizationRecord {

	Integer vaccineId;
		
	String vaccineName;
	
	String vaccineCvx;
	
	int vaccineSortOrder;
	
	String vaccineGroup;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	Timestamp vaccinationPerformedOn;
	
	Integer vaccinationPerformedById;
	
	String vaccinationPerformedByName;
	
	String vaccinationPerformedSite;
	
	Integer vaccinationStatus;
	
	String vaccineLotNo;
	
	String vaccineMfg;
	
	boolean isBillable;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	Timestamp vaccineExpDate;

	public Integer getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(Integer vaccineId) {
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

	public String getVaccineGroup() {
		return vaccineGroup;
	}

	public void setVaccineGroup(String vaccineGroup) {
		this.vaccineGroup = vaccineGroup;
	}

	public Timestamp getVaccinationPerformedOn() {
		return vaccinationPerformedOn;
	}

	public void setVaccinationPerformedOn(Timestamp timestamp) {
		this.vaccinationPerformedOn = timestamp;
	}

	public Integer getVaccinationPerformedById() {
		return vaccinationPerformedById;
	}

	public void setVaccinationPerformedById(Integer vaccinationPerformedById) {
		this.vaccinationPerformedById = vaccinationPerformedById;
	}

	public String getVaccinationPerformedByName() {
		return vaccinationPerformedByName;
	}

	public void setVaccinationPerformedByName(String vaccinationPerformedByName) {
		this.vaccinationPerformedByName = vaccinationPerformedByName;
	}

	public String getVaccinationPerformedSite() {
		return vaccinationPerformedSite;
	}

	public void setVaccinationPerformedSite(String vaccinationPerformedSite) {
		this.vaccinationPerformedSite = vaccinationPerformedSite;
	}

	public Integer getVaccinationStatus() {
		return vaccinationStatus;
	}

	public void setVaccinationStatus(Integer integer) {
		this.vaccinationStatus = integer;
	}

	public String getVaccineLotNo() {
		return vaccineLotNo;
	}

	public void setVaccineLotNo(String vaccineLotNo) {
		this.vaccineLotNo = vaccineLotNo;
	}

	public String getVaccineMfg() {
		return vaccineMfg;
	}

	public void setVaccineMfg(String vaccineMfg) {
		this.vaccineMfg = vaccineMfg;
	}

	public Timestamp getVaccineExpDate() {
		return vaccineExpDate;
	}

	public void setVaccineExpDate(Timestamp timestamp) {
		this.vaccineExpDate = timestamp;
	}

	public int getVaccineSortOrder() {
		return vaccineSortOrder;
	}

	public void setVaccineSortOrder(int vaccineSortOrder) {
		this.vaccineSortOrder = vaccineSortOrder;
	}

	public boolean isBillable() {
		return isBillable;
	}

	public void setBillable(boolean isBillable) {
		this.isBillable = isBillable;
	}
	
}
