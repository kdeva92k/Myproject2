package com.glenwood.glaceemr.server.application.models;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lab_descp_parameters")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabDescpParameters {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lab_descp_parameters_lab_descp_parameter_id_seq")
	@SequenceGenerator(name ="lab_descp_parameters_lab_descp_parameter_id_seq", sequenceName="lab_descp_parameters_lab_descp_parameter_id_seq", allocationSize=1)
	@Column(name="lab_descp_parameter_id", nullable=false)
	private Integer labDescpParameterId;

	@Column(name="lab_descp_parameter_testid", nullable=false)
	private Integer labDescpParameterTestid;

	@Column(name="lab_descp_parameter_mapid", nullable=false)
	private Integer labDescpParameterMapid;

	@Column(name="lab_descp_parameter_sortorder", columnDefinition="Integer default 1")
	private Integer labDescpParameterSortorder;

	@Column(name="lab_descp_parameter_isactive", columnDefinition="Boolean default true")
	private Boolean labDescpParameterIsactive;

	@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
	@JoinColumn(name="lab_descp_parameter_testid", referencedColumnName="lab_description_testid", insertable=false, updatable=false)
	@JsonBackReference
	LabDescription labDescriptionTable;
	
	@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
	@JoinColumn(name="lab_descp_parameter_mapid", referencedColumnName="lab_parameters_id", insertable=false, updatable=false)
	@JsonBackReference
	LabParameters labParametersTable;
		
	public LabDescription getLabDescriptionTable() {
		return labDescriptionTable;
	}

	public void setLabDescriptionTable(LabDescription labDescriptionTable) {
		this.labDescriptionTable = labDescriptionTable;
	}

	public LabParameters getLabParametersTable() {
		return labParametersTable;
	}

	public void setLabParametersTable(LabParameters labParametersTable) {
		this.labParametersTable = labParametersTable;
	}

	public Integer getLabDescpParameterId() {
		return labDescpParameterId;
	}

	public void setLabDescpParameterId(Integer labDescpParameterId) {
		this.labDescpParameterId = labDescpParameterId;
	}

	public Integer getLabDescpParameterTestid() {
		return labDescpParameterTestid;
	}

	public void setLabDescpParameterTestid(Integer labDescpParameterTestid) {
		this.labDescpParameterTestid = labDescpParameterTestid;
	}

	public Integer getLabDescpParameterMapid() {
		return labDescpParameterMapid;
	}

	public void setLabDescpParameterMapid(Integer labDescpParameterMapid) {
		this.labDescpParameterMapid = labDescpParameterMapid;
	}

	public Integer getLabDescpParameterSortorder() {
		return labDescpParameterSortorder;
	}

	public void setLabDescpParameterSortorder(Integer labDescpParameterSortorder) {
		this.labDescpParameterSortorder = labDescpParameterSortorder;
	}

	public Boolean getLabDescpParameterIsactive() {
		return labDescpParameterIsactive;
	}

	public void setLabDescpParameterIsactive(Boolean labDescpParameterIsactive) {
		this.labDescpParameterIsactive = labDescpParameterIsactive;
	}
}