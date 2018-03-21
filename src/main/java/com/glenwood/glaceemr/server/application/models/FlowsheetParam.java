package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "flowsheet_param")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowsheetParam {

	@Id
	@Column(name="flowsheet_param_id", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="flowsheet_param_flowsheet_param_id_seq")
	@SequenceGenerator(name ="flowsheet_param_flowsheet_param_id_seq", sequenceName="flowsheet_param_flowsheet_param_id_seq", allocationSize=1)
	private Integer flowsheetParamId;

	@Column(name="flowsheet_param_mapid")
	private Integer flowsheetParamMapid;

	@Column(name="flowsheet_param_standard_groupid")
	private Integer flowsheetParamStandardGroupid;

	@Column(name="flowsheet_param_sortby", columnDefinition = "Integer default 1")
	private Integer flowsheetParamSortby;
	
	@ManyToOne
	@JoinColumn(name="flowsheet_param_standard_groupid", referencedColumnName="param_standard_group_id", insertable=false, updatable=false)
	@JsonManagedReference
	ParamStandardGroup paramStandardGroupTable;

	public Integer getFlowsheetParamId() {
		return flowsheetParamId;
	}

	public void setFlowsheetParamId(Integer flowsheetParamId) {
		this.flowsheetParamId = flowsheetParamId;
	}

	public Integer getFlowsheetParamMapid() {
		return flowsheetParamMapid;
	}

	public void setFlowsheetParamMapid(Integer flowsheetParamMapid) {
		this.flowsheetParamMapid = flowsheetParamMapid;
	}

	public Integer getFlowsheetParamStandardGroupid() {
		return flowsheetParamStandardGroupid;
	}

	public void setFlowsheetParamStandardGroupid(Integer flowsheetParamStandardGroupid) {
		this.flowsheetParamStandardGroupid = flowsheetParamStandardGroupid;
	}

	public Integer getFlowsheetParamSortby() {
		return flowsheetParamSortby;
	}

	public void setFlowsheetParamSortby(Integer flowsheetParamSortby) {
		this.flowsheetParamSortby = flowsheetParamSortby;
	}
}