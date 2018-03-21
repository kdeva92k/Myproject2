package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "units_of_measure")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitsOfMeasure {

	@Id
	@Column(name="units_of_measure_id")
	private Integer unitsOfMeasureId;

	@Column(name="units_of_measure_code", length=20)
	private String unitsOfMeasureCode;

	@Column(name="units_of_measure_description", length=50)
	private String unitsOfMeasureDescription;

	@Column(name="units_of_measure_hl7_code", length=20)
	private String unitsOfMeasureHl7Code;
	
	@Column(name="units_of_measure_surescripts_code", length=20)
	private String unitsOfMeasureSurescriptsCode;

	public Integer getUnitsOfMeasureId() {
		return unitsOfMeasureId;
	}

	public void setUnitsOfMeasureId(Integer unitsOfMeasureId) {
		this.unitsOfMeasureId = unitsOfMeasureId;
	}

	public String getUnitsOfMeasureCode() {
		return unitsOfMeasureCode;
	}

	public void setUnitsOfMeasureCode(String unitsOfMeasureCode) {
		this.unitsOfMeasureCode = unitsOfMeasureCode;
	}

	public String getUnitsOfMeasureDescription() {
		return unitsOfMeasureDescription;
	}

	public void setUnitsOfMeasureDescription(String unitsOfMeasureDescription) {
		this.unitsOfMeasureDescription = unitsOfMeasureDescription;
	}

	public String getUnitsOfMeasureHl7Code() {
		return unitsOfMeasureHl7Code;
	}

	public void setUnitsOfMeasureHl7Code(String unitsOfMeasureHl7Code) {
		this.unitsOfMeasureHl7Code = unitsOfMeasureHl7Code;
	}

	public String getUnitsOfMeasureSurescriptsCode() {
		return unitsOfMeasureSurescriptsCode;
	}

	public void setUnitsOfMeasureSurescriptsCode(
			String unitsOfMeasureSurescriptsCode) {
		this.unitsOfMeasureSurescriptsCode = unitsOfMeasureSurescriptsCode;
	}
}