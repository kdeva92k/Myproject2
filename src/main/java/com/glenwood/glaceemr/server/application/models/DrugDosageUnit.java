package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "drug_dosage_unit")
public class DrugDosageUnit {

	@Id
	@Column(name="drug_dosage_unit_id")
	private Integer drugDosageUnitId;

	public Integer getDrugDosageUnitId() {
		return drugDosageUnitId;
	}

	public void setDrugDosageUnitId(Integer drugDosageUnitId) {
		this.drugDosageUnitId = drugDosageUnitId;
	}

	public String getDrugDosageUnitName() {
		return drugDosageUnitName;
	}

	public void setDrugDosageUnitName(String drugDosageUnitName) {
		this.drugDosageUnitName = drugDosageUnitName;
	}

	public String getDrugDosageUnitDesc() {
		return drugDosageUnitDesc;
	}

	public void setDrugDosageUnitDesc(String drugDosageUnitDesc) {
		this.drugDosageUnitDesc = drugDosageUnitDesc;
	}

	@Column(name="drug_dosage_unit_name")
	private String drugDosageUnitName;

	@Column(name="drug_dosage_unit_desc")
	private String drugDosageUnitDesc;
}