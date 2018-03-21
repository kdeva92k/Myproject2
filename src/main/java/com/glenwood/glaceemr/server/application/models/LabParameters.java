package com.glenwood.glaceemr.server.application.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "lab_parameters")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabParameters {

	@Id
	@Column(name="lab_parameters_id")
	private Integer labParametersId;

	@Column(name="lab_parameters_name", length=300)
	private String labParametersName;

	@Column(name="lab_parameters_displayname", length=300)
	private String labParametersDisplayname;

	@Column(name="lab_parameters_units", length=50)
	private String labParametersUnits;

	@Column(name="lab_parameters_normalrange", length=300)
	private String labParametersNormalrange;

	@Column(name="lab_parameters_type", length=20)
	private String labParametersType;

	@Column(name="lab_parameters_isactive")
	private Boolean labParametersIsactive;

	@Column(name="lab_parameters_isflowsheetneeded", columnDefinition="Boolean default false")
	private Boolean labParametersIsflowsheetneeded;

	@Column(name="lab_parameters_flowsheeturl", length=50)
	private String labParametersFlowsheeturl;
	
	@OneToMany(mappedBy="labParameterCodeParamid")
	@JsonManagedReference
	List<LabParameterCode> labParameterCodeTable;
	
	@OneToMany(mappedBy="labDescpParameterMapid")
	@JsonManagedReference
	List<LabDescpParameters> labDescParameterTable;
	
	public Integer getLabParametersId() {
		return labParametersId;
	}

	public void setLabParametersId(Integer labParametersId) {
		this.labParametersId = labParametersId;
	}

	public String getLabParametersName() {
		return labParametersName;
	}

	public void setLabParametersName(String labParametersName) {
		this.labParametersName = labParametersName;
	}

	public String getLabParametersDisplayname() {
		return labParametersDisplayname;
	}

	public void setLabParametersDisplayname(String labParametersDisplayname) {
		this.labParametersDisplayname = labParametersDisplayname;
	}

	public String getLabParametersUnits() {
		return labParametersUnits;
	}

	public void setLabParametersUnits(String labParametersUnits) {
		this.labParametersUnits = labParametersUnits;
	}

	public String getLabParametersNormalrange() {
		return labParametersNormalrange;
	}

	public void setLabParametersNormalrange(String labParametersNormalrange) {
		this.labParametersNormalrange = labParametersNormalrange;
	}

	public String getLabParametersType() {
		return labParametersType;
	}

	public void setLabParametersType(String labParametersType) {
		this.labParametersType = labParametersType;
	}

	public Boolean getLabParametersIsactive() {
		return labParametersIsactive;
	}

	public void setLabParametersIsactive(Boolean labParametersIsactive) {
		this.labParametersIsactive = labParametersIsactive;
	}

	public Boolean getLabParametersIsflowsheetneeded() {
		return labParametersIsflowsheetneeded;
	}

	public void setLabParametersIsflowsheetneeded(
			Boolean labParametersIsflowsheetneeded) {
		this.labParametersIsflowsheetneeded = labParametersIsflowsheetneeded;
	}

	public String getLabParametersFlowsheeturl() {
		return labParametersFlowsheeturl;
	}

	public void setLabParametersFlowsheeturl(String labParametersFlowsheeturl) {
		this.labParametersFlowsheeturl = labParametersFlowsheeturl;
	}
	@OneToMany(mappedBy="labParams")
	private List<LabParameterCode> labParamCode;
	
	
}
