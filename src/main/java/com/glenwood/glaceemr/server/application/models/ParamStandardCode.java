package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "param_standard_code")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParamStandardCode {

	@Id
	@Column(name="param_standard_code_id", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="param_standard_code_param_standard_code_id_seq")
	@SequenceGenerator(name ="param_standard_code_param_standard_code_id_seq", sequenceName="param_standard_code_param_standard_code_id_seq", allocationSize=1)
	private Integer paramStandardCodeId;

	@Column(name="param_standard_code_group_id")
	private Integer paramStandardCodeGroupId;

	@Column(name="param_standard_code_group_code", length=50)
	private String paramStandardCodeGroupCode;

	@Column(name="param_standard_code_group_gwid")
	private Integer paramStandardCodeGroupGwid;

	@Column(name="param_standard_code_group_codesystem", length=100)
	private String paramStandardCodeGroupCodesystem;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="param_standard_code_group_gwid",referencedColumnName="lab_parameters_id",insertable=false,updatable=false)
	@JsonManagedReference
	LabParameters labParametersTable;
	
	public ParamStandardCode(){
		super();
	}
	
	
	public ParamStandardCode(Integer paramStandardCodeId,
			Integer paramStandardCodeGroupId,
			String paramStandardCodeGroupCode,
			Integer paramStandardCodeGroupGwid,
			String paramStandardCodeGroupCodesystem) {
	
		this.paramStandardCodeId=paramStandardCodeId;
		this.paramStandardCodeGroupId=paramStandardCodeGroupId;
		this.paramStandardCodeGroupCode=paramStandardCodeGroupCode;
		this.paramStandardCodeGroupGwid=paramStandardCodeGroupGwid;
		this.paramStandardCodeGroupCodesystem=paramStandardCodeGroupCodesystem;
	}

	public Integer getParamStandardCodeId() {
		return paramStandardCodeId;
	}

	public void setParamStandardCodeId(Integer paramStandardCodeId) {
		this.paramStandardCodeId = paramStandardCodeId;
	}

	public Integer getParamStandardCodeGroupId() {
		return paramStandardCodeGroupId;
	}

	public void setParamStandardCodeGroupId(Integer paramStandardCodeGroupId) {
		this.paramStandardCodeGroupId = paramStandardCodeGroupId;
	}

	public String getParamStandardCodeGroupCode() {
		return paramStandardCodeGroupCode;
	}

	public void setParamStandardCodeGroupCode(String paramStandardCodeGroupCode) {
		this.paramStandardCodeGroupCode = paramStandardCodeGroupCode;
	}

	public Integer getParamStandardCodeGroupGwid() {
		return paramStandardCodeGroupGwid;
	}

	public void setParamStandardCodeGroupGwid(Integer paramStandardCodeGroupGwid) {
		this.paramStandardCodeGroupGwid = paramStandardCodeGroupGwid;
	}

	public String getParamStandardCodeGroupCodesystem() {
		return paramStandardCodeGroupCodesystem;
	}

	public void setParamStandardCodeGroupCodesystem(
			String paramStandardCodeGroupCodesystem) {
		this.paramStandardCodeGroupCodesystem = paramStandardCodeGroupCodesystem;
	}

	public LabParameters getLabParametersTable() {
		return labParametersTable;
	}

	public void setLabParametersTable(LabParameters labParametersTable) {
		this.labParametersTable = labParametersTable;
	}
}