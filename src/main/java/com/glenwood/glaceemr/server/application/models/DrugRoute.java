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
@Table(name = "drug_route")
public class DrugRoute {

	@Id
	@Column(name="drug_route_id")
	private Integer drugRouteId;

	public Integer getDrugRouteId() {
		return drugRouteId;
	}

	public void setDrugRouteId(Integer drugRouteId) {
		this.drugRouteId = drugRouteId;
	}

	public Integer getDrugRouteOrderBy() {
		return drugRouteOrderBy;
	}

	public void setDrugRouteOrderBy(Integer drugRouteOrderBy) {
		this.drugRouteOrderBy = drugRouteOrderBy;
	}

	public String getDrugRouteName() {
		return drugRouteName;
	}

	public void setDrugRouteName(String drugRouteName) {
		this.drugRouteName = drugRouteName;
	}

	@Column(name="drug_route_order_by")
	private Integer drugRouteOrderBy;

	@Column(name="drug_route_name")
	private String drugRouteName;
}