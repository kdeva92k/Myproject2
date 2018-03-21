package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vital_group")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VitalGroup {

	@Id
	@Column(name="vital_group_id",unique = true,nullable=false)
	private Integer vitalGroupId;

	@Column(name="vital_group_name")
	private String vitalGroupName;

	@Column(name="vital_group_orderby")
	private Integer vitalGroupOrderby;
	
	@OneToMany(mappedBy="vitalGroup",fetch=FetchType.LAZY)
	List<VitalsParameter> vitalsParameter;	
	
	public Integer getVitalGroupId() {
		return vitalGroupId;
	}

	public void setVitalGroupId(Integer vitalGroupId) {
		this.vitalGroupId = vitalGroupId;
	}

	public List<VitalsParameter> getVitalsParameter() {
		return vitalsParameter;
	}

	public void setVitalsParameter(List<VitalsParameter> vitalsParameter) {
		this.vitalsParameter = vitalsParameter;
	}

	public String getVitalGroupName() {
		return vitalGroupName;
	}

	public void setVitalGroupName(String vitalGroupName) {
		this.vitalGroupName = vitalGroupName;
	}

	public Integer getVitalGroupOrderby() {
		return vitalGroupOrderby;
	}

	public void setVitalGroupOrderby(Integer vitalGroupOrderby) {
		this.vitalGroupOrderby = vitalGroupOrderby;
	}	
}