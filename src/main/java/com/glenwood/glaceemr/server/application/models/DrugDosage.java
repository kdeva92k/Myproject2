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
@Table(name = "drug_dosage")
public class DrugDosage {

	@Id
	@Column(name="drug_dosage_id")
	private Integer drugDosageId;

	public Integer getDrugDosageId() {
		return drugDosageId;
	}

	public void setDrugDosageId(Integer drugDosageId) {
		this.drugDosageId = drugDosageId;
	}

	public Integer getDrugDosageMapUnitId() {
		return drugDosageMapUnitId;
	}

	public void setDrugDosageMapUnitId(Integer drugDosageMapUnitId) {
		this.drugDosageMapUnitId = drugDosageMapUnitId;
	}

	public String getDrugDosageName() {
		return drugDosageName;
	}

	public void setDrugDosageName(String drugDosageName) {
		this.drugDosageName = drugDosageName;
	}

	@Column(name="drug_dosage_map_unit_id")
	private Integer drugDosageMapUnitId;

	@Column(name="drug_dosage_name")
	private String drugDosageName;
	
	@Column(name="drug_dosage_desc")
	private String DrugDosageDesc;
	
	
}