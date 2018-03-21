package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "flowsheet_type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowsheetType {
	
	@Id
	@Column(name="flowsheet_type_id", nullable=false)
	@SequenceGenerator(name="flowsheet_type_flowsheet_type_id_seq", sequenceName="flowsheet_type_flowsheet_type_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="flowsheet_type_flowsheet_type_id_seq")
	private Long flowsheetTypeId;
	
	@Column(name="flowsheet_type_name", length=50)
	private String flowsheetTypeName;

	@Column(name="flowsheet_type_isactive", columnDefinition = "Boolean default true")
	private Boolean flowsheetTypeIsactive;

	public Long getFlowsheetTypeId() {
		return flowsheetTypeId;
	}

	public void setFlowsheetTypeId(Long flowsheetTypeId) {
		this.flowsheetTypeId = flowsheetTypeId;
	}

	public String getFlowsheetTypeName() {
		return flowsheetTypeName;
	}

	public void setFlowsheetTypeName(String flowsheetTypeName) {
		this.flowsheetTypeName = flowsheetTypeName;
	}

	public Boolean getFlowsheetTypeIsactive() {
		return flowsheetTypeIsactive;
	}

	public void setFlowsheetTypeIsactive(Boolean flowsheetTypeIsactive) {
		this.flowsheetTypeIsactive = flowsheetTypeIsactive;
	}
}
