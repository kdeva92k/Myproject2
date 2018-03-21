package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "flowsheet_dx")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowsheetDx implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="flowsheet_dx_flowsheet_dx_id_seq")
	@SequenceGenerator(name ="flowsheet_dx_flowsheet_dx_id_seq", sequenceName="flowsheet_dx_flowsheet_dx_id_seq", allocationSize=1)
	@Column(name="flowsheet_dx_id", nullable=false)
	private Integer flowsheetDxId;

	@Column(name="flowsheet_dx_mapid")
	private Integer flowsheetDxMapid;

	@Column(name="flowsheet_dx_code", length=20)
	private String flowsheetDxCode;
	
	@Column(name="flowsheet_dx_codesystem")
	private Integer flowsheetDxCodesystem;

	@ManyToOne
	@JoinColumn(name="flowsheet_dx_mapid", referencedColumnName="flowsheet_id", insertable=false, updatable=false)
	@JsonBackReference
	Flowsheet flowsheetTable;
		
	public Integer getFlowsheetDxId() {
		return flowsheetDxId;
	}

	public void setFlowsheetDxId(Integer flowsheetDxId) {
		this.flowsheetDxId = flowsheetDxId;
	}

	public Integer getFlowsheetDxMapid() {
		return flowsheetDxMapid;
	}

	public void setFlowsheetDxMapid(Integer flowsheetDxMapid) {
		this.flowsheetDxMapid = flowsheetDxMapid;
	}

	public String getFlowsheetDxCode() {
		return flowsheetDxCode;
	}

	public void setFlowsheetDxCode(String flowsheetDxCode) {
		this.flowsheetDxCode = flowsheetDxCode;
	}

	public Integer getFlowsheetDxCodesystem() {
		return flowsheetDxCodesystem;
	}

	public void setFlowsheetDxCodesystem(Integer flowsheetDxCodesystem) {
		this.flowsheetDxCodesystem = flowsheetDxCodesystem;
	}
}