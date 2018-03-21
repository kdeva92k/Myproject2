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
@Table(name = "flowsheet_lab")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowsheetLab {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="flowsheet_lab_flowsheet_lab_id_seq")
	@SequenceGenerator(name ="flowsheet_lab_flowsheet_lab_id_seq", sequenceName="flowsheet_lab_flowsheet_lab_id_seq", allocationSize=1)
	@Column(name="flowsheet_lab_id", nullable=false)
	private Integer flowsheetLabId;

	@Column(name="flowsheet_lab_mapid")
	private Integer flowsheetLabMapid;

	@Column(name="flowsheet_lab_standard_groupid")
	private Integer flowsheetLabStandardGroupid;
	
	@Column(name="flowsheet_lab_order")
	private Integer flowsheetLabOrder;

	@ManyToOne
	@JoinColumn(name="flowsheet_lab_standard_groupid", referencedColumnName="lab_standard_group_id", insertable=false, updatable=false)
	@JsonManagedReference
	LabStandardGroup labStandardGroupTable;
	
	public Integer getFlowsheetLabId() {
		return flowsheetLabId;
	}

	public void setFlowsheetLabId(Integer flowsheetLabId) {
		this.flowsheetLabId = flowsheetLabId;
	}

	public Integer getFlowsheetLabMapid() {
		return flowsheetLabMapid;
	}

	public void setFlowsheetLabMapid(Integer flowsheetLabMapid) {
		this.flowsheetLabMapid = flowsheetLabMapid;
	}

	public Integer getFlowsheetLabOrder() {
		return flowsheetLabOrder;
	}

	public void setFlowsheetLabOrder(Integer flowsheetLabOrder) {
		this.flowsheetLabOrder = flowsheetLabOrder;
	}

	public Integer getFlowsheetLabStandardGroupid() {
		return flowsheetLabStandardGroupid;
	}

	public void setFlowsheetLabStandardGroupid(Integer flowsheetLabStandardGroupid) {
		this.flowsheetLabStandardGroupid = flowsheetLabStandardGroupid;
	}
}