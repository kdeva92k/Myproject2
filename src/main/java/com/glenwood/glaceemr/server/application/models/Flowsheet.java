package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "flowsheet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flowsheet {
	
	@Id
	@Column(name="flowsheet_id", nullable=false)
	@SequenceGenerator(name="flowsheet_flowsheet_id_seq", sequenceName="flowsheet_flowsheet_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="flowsheet_flowsheet_id_seq")
	private Integer flowsheetId;
	
	@Column(name="flowsheet_name", length=250)
	private String flowsheetName;
	
	@Column(name="flowsheet_type")
	private Integer flowsheetType;
	
	@Column(name="flowsheet_isactive", columnDefinition = "Boolean default true")
	private Boolean flowsheetIsactive;

	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="flowsheet_type",referencedColumnName="flowsheet_type_id", nullable=false, insertable=false, updatable=false)
	private FlowsheetType flowsheetTypeTable;

	public Integer getFlowsheetId() {
		return flowsheetId;
	}

	public void setFlowsheetId(Integer flowsheetId) {
		this.flowsheetId = flowsheetId;
	}

	public String getFlowsheetName() {
		return flowsheetName;
	}

	public void setFlowsheetName(String flowsheetName) {
		this.flowsheetName = flowsheetName;
	}

	public Integer getFlowsheetType() {
		return flowsheetType;
	}

	public void setFlowsheetType(Integer flowsheetType) {
		this.flowsheetType = flowsheetType;
	}

	public void setFlowsheetTypeId(Integer flowsheetType) {
		this.flowsheetType = flowsheetType;
	}

	public Boolean getFlowsheetIsactive() {
		return flowsheetIsactive;
	}

	public void setFlowsheetIsactive(Boolean flowsheetIsactive) {
		this.flowsheetIsactive = flowsheetIsactive;
	}
}
