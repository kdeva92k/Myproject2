package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "chartcenter_encounter")
public class ChartcenterEncounter {

	@Id
	@Column(name="encounter_id")
	private Integer encounterId;

	@Column(name="encounter_chartid")
	private Integer encounterChartid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="encounter_date")
	private Timestamp encounterDate;

	@Column(name="encounter_status")
	private Integer encounterStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="encounter_chartid", referencedColumnName="chart_id", insertable=false, updatable=false)
    @JsonBackReference
    Chart chart;

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getEncounterChartid() {
		return encounterChartid;
	}

	public void setEncounterChartid(Integer encounterChartid) {
		this.encounterChartid = encounterChartid;
	}

	public Timestamp getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Timestamp encounterDate) {
		this.encounterDate = encounterDate;
	}

	public Integer getEncounterStatus() {
		return encounterStatus;
	}

	public void setEncounterStatus(Integer encounterStatus) {
		this.encounterStatus = encounterStatus;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

}