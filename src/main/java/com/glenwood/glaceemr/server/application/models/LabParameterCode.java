package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "lab_parameter_code")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabParameterCode implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lab_parameter_code_lab_parameter_code_id_seq")
	@SequenceGenerator(name ="lab_parameter_code_lab_parameter_code_id_seq", sequenceName="lab_parameter_code_lab_parameter_code_id_seq", allocationSize=1)
	@Column(name="lab_parameter_code_id")
	private Integer labParameterCodeId;

	@Column(name="lab_parameter_code_paramid")
	private Integer labParameterCodeParamid;

	@Column(name="lab_parameter_code_value")
	private String labParameterCodeValue;

	@Column(name="lab_parameter_code_system")
	private String labParameterCodeSystem;

	public Integer getLabParameterCodeId() {
		return labParameterCodeId;
	}

	public void setLabParameterCodeId(Integer labParameterCodeId) {
		this.labParameterCodeId = labParameterCodeId;
	}

	public Integer getLabParameterCodeParamid() {
		return labParameterCodeParamid;
	}

	public void setLabParameterCodeParamid(Integer labParameterCodeParamid) {
		this.labParameterCodeParamid = labParameterCodeParamid;
	}

	public String getLabParameterCodeValue() {
		return labParameterCodeValue;
	}

	public void setLabParameterCodeValue(String labParameterCodeValue) {
		this.labParameterCodeValue = labParameterCodeValue;
	}

	public String getLabParameterCodeSystem() {
		return labParameterCodeSystem;
	}

	public void setLabParameterCodeSystem(String labParameterCodeSystem) {
		this.labParameterCodeSystem = labParameterCodeSystem;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
	@JoinColumn(name="lab_parameter_code_paramid", referencedColumnName="lab_parameters_id", insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	LabParameters labParams;

	
	
}
