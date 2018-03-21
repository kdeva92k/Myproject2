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
@Table(name = "flowsheet_clinical_param")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowsheetClinicalParam {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="flowsheet_clinical_param_flowsheet_clinical_param_id_seq")
	@SequenceGenerator(name ="flowsheet_clinical_param_flowsheet_clinical_param_id_seq", sequenceName="flowsheet_clinical_param_flowsheet_clinical_param_id_seq", allocationSize=1)
	@Column(name="flowsheet_clinical_param_id", nullable=false)
	private Integer flowsheetClinicalParamId;

	@Column(name="flowsheet_clinical_param_mapid")
	private Integer flowsheetClinicalParamMapid;

	@Column(name="flowsheet_clinical_param_map_elementgwid", length=20)
	private String flowsheetClinicalParamMapElementgwid;

	@Column(name="flowsheet_clinical_param_map_elementtype")
	private Integer flowsheetClinicalParamMapElementtype;
	
	@Column(name="flowsheet_clinical_param_sortorder")
	private Integer flowsheetClinicalParamSortorder;

	public Integer getFlowsheetClinicalParamId() {
		return flowsheetClinicalParamId;
	}

	public void setFlowsheetClinicalParamId(Integer flowsheetClinicalParamId) {
		this.flowsheetClinicalParamId = flowsheetClinicalParamId;
	}

	public Integer getFlowsheetClinicalParamMapid() {
		return flowsheetClinicalParamMapid;
	}

	public void setFlowsheetClinicalParamMapid(Integer flowsheetClinicalParamMapid) {
		this.flowsheetClinicalParamMapid = flowsheetClinicalParamMapid;
	}

	public String getFlowsheetClinicalParamMapElementgwid() {
		return flowsheetClinicalParamMapElementgwid;
	}

	public void setFlowsheetClinicalParamMapElementgwid(
			String flowsheetClinicalParamMapElementgwid) {
		this.flowsheetClinicalParamMapElementgwid = flowsheetClinicalParamMapElementgwid;
	}

	public Integer getFlowsheetClinicalParamMapElementtype() {
		return flowsheetClinicalParamMapElementtype;
	}

	public void setFlowsheetClinicalParamMapElementtype(
			Integer flowsheetClinicalParamMapElementtype) {
		this.flowsheetClinicalParamMapElementtype = flowsheetClinicalParamMapElementtype;
	}

	public Integer getFlowsheetClinicalParamSortorder() {
		return flowsheetClinicalParamSortorder;
	}

	public void setFlowsheetClinicalParamSortorder(
			Integer flowsheetClinicalParamSortorder) {
		this.flowsheetClinicalParamSortorder = flowsheetClinicalParamSortorder;
	}
	
	
	public  FlowsheetClinicalParam()
	{
		super();
	}
	
	public FlowsheetClinicalParam(Integer flowsheetClinicalParamId,
			Integer flowsheetClinicalParamMapid,String flowsheetClinicalParamMapElementgwid,
			Integer flowsheetClinicalParamMapElementtype,Integer flowsheetClinicalParamSortorder){
		this.flowsheetClinicalParamId=flowsheetClinicalParamId;
		this.flowsheetClinicalParamMapid=flowsheetClinicalParamMapid;
		this.flowsheetClinicalParamMapElementgwid=flowsheetClinicalParamMapElementgwid;
		this.flowsheetClinicalParamMapElementtype=flowsheetClinicalParamMapElementtype;
		this.flowsheetClinicalParamSortorder=flowsheetClinicalParamSortorder;
	}
	
	
	
}