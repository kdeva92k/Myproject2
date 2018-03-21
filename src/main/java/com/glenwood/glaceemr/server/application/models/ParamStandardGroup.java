package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "param_standard_group")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParamStandardGroup {

	@Id
	@Column(name="param_standard_group_id", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="param_standard_group_param_standard_group_id_seq")
	@SequenceGenerator(name ="param_standard_group_param_standard_group_id_seq", sequenceName="param_standard_group_param_standard_group_id_seq", allocationSize=1)
	private Integer paramStandardGroupId;

	@Column(name="param_standard_group_name", length=100)
	private String paramStandardGroupName;

	@Column(name="param_standard_group_gender", columnDefinition="Integer default 5")
	private Integer paramStandardGroupGender;

	@Column(name="param_standard_group_isactive", columnDefinition="Boolean default true")
	private Boolean paramStandardGroupIsactive;

	@OneToMany(mappedBy="paramStandardCodeGroupId")
	@JsonManagedReference
	List<ParamStandardCode> paramStandardCodeTable;
	
	public Integer getParamStandardGroupId() {
		return paramStandardGroupId;
	}

	public void setParamStandardGroupId(Integer paramStandardGroupId) {
		this.paramStandardGroupId = paramStandardGroupId;
	}

	public String getParamStandardGroupName() {
		return paramStandardGroupName;
	}

	public void setParamStandardGroupName(String paramStandardGroupName) {
		this.paramStandardGroupName = paramStandardGroupName;
	}

	public Integer getParamStandardGroupGender() {
		return paramStandardGroupGender;
	}

	public void setParamStandardGroupGender(Integer paramStandardGroupGender) {
		this.paramStandardGroupGender = paramStandardGroupGender;
	}

	public Boolean getParamStandardGroupIsactive() {
		return paramStandardGroupIsactive;
	}

	public void setParamStandardGroupIsactive(Boolean paramStandardGroupIsactive) {
		this.paramStandardGroupIsactive = paramStandardGroupIsactive;
	}

	public List<ParamStandardCode> getParamStandardCodeTable() {
		return paramStandardCodeTable;
	}

	public void setParamStandardCodeTable(
			List<ParamStandardCode> paramStandardCodeTable) {
		this.paramStandardCodeTable = paramStandardCodeTable;
	}
}