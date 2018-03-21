package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "flowsheet_drug")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowsheetDrug {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="flowsheet_drug_flowsheet_drug_id_seq")
	@SequenceGenerator(name ="flowsheet_drug_flowsheet_drug_id_seq", sequenceName="flowsheet_drug_flowsheet_drug_id_seq", allocationSize=1)
	@Column(name="flowsheet_drug_id", nullable=false)
	private Integer flowsheetDrugId;

	@Column(name="flowsheet_drug_mapid")
	private Integer flowsheetDrugMapid;

	@Column(name="flowsheet_drug_class_name", length=100)
	private String flowsheetDrugClassName;

	@Column(name="flowsheet_drug_code", length=20)
	private String flowsheetDrugCode;

	@Column(name="flowsheet_drug_codesystem")
	private Integer flowsheetDrugCodesystem;

	@Column(name="flowsheet_drug_gender", columnDefinition="Integer default 5")
	private Integer flowsheetDrugGender;

	@Column(name="flowsheet_drug_class_id", length=20)
	private String flowsheetDrugClassId;

	public Integer getFlowsheetDrugId() {
		return flowsheetDrugId;
	}

	public void setFlowsheetDrugId(Integer flowsheetDrugId) {
		this.flowsheetDrugId = flowsheetDrugId;
	}

	public Integer getFlowsheetDrugMapid() {
		return flowsheetDrugMapid;
	}

	public void setFlowsheetDrugMapid(Integer flowsheetDrugMapid) {
		this.flowsheetDrugMapid = flowsheetDrugMapid;
	}

	public String getFlowsheetDrugClassName() {
		return flowsheetDrugClassName;
	}

	public void setFlowsheetDrugClassName(String flowsheetDrugClassName) {
		this.flowsheetDrugClassName = flowsheetDrugClassName;
	}

	public String getFlowsheetDrugCode() {
		return flowsheetDrugCode;
	}

	public void setFlowsheetDrugCode(String flowsheetDrugCode) {
		this.flowsheetDrugCode = flowsheetDrugCode;
	}

	public Integer getFlowsheetDrugCodesystem() {
		return flowsheetDrugCodesystem;
	}

	public void setFlowsheetDrugCodesystem(Integer flowsheetDrugCodesystem) {
		this.flowsheetDrugCodesystem = flowsheetDrugCodesystem;
	}

	public Integer getFlowsheetDrugGender() {
		return flowsheetDrugGender;
	}

	public void setFlowsheetDrugGender(Integer flowsheetDrugGender) {
		this.flowsheetDrugGender = flowsheetDrugGender;
	}

	public String getFlowsheetDrugClassId() {
		return flowsheetDrugClassId;
	}

	public void setFlowsheetDrugClassId(String flowsheetDrugClassId) {
		this.flowsheetDrugClassId = flowsheetDrugClassId;
	}
}