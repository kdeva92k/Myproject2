package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vitals_parameter_condition")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VitalsParameterCondition {

	@Column(name="vitals_parameter_condition_id")
	private Integer vitalsParameterConditionId;
	
	@Id
	@Column(name="vitals_parameter_condition_gw_id")
	private String vitalsParameterConditionGwId;

	@Column(name="vitals_parameter_condition_type")
	private Integer vitalsParameterConditionType;

	@Column(name="vitals_parameter_condition_condition")
	private String vitalsParameterConditionCondition;
	
	
	
	


	public Integer getVitalsParameterConditionId() {
		return vitalsParameterConditionId;
	}

	public void setVitalsParameterConditionId(Integer vitalsParameterConditionId) {
		this.vitalsParameterConditionId = vitalsParameterConditionId;
	}

	public String getVitalsParameterConditionGwId() {
		return vitalsParameterConditionGwId;
	}

	public void setVitalsParameterConditionGwId(String vitalsParameterConditionGwId) {
		this.vitalsParameterConditionGwId = vitalsParameterConditionGwId;
	}

	public Integer getVitalsParameterConditionType() {
		return vitalsParameterConditionType;
	}

	public void setVitalsParameterConditionType(Integer vitalsParameterConditionType) {
		this.vitalsParameterConditionType = vitalsParameterConditionType;
	}

	public String getVitalsParameterConditionCondition() {
		return vitalsParameterConditionCondition;
	}

	public void setVitalsParameterConditionCondition(
			String vitalsParameterConditionCondition) {
		this.vitalsParameterConditionCondition = vitalsParameterConditionCondition;
	}

	/*public VitalsParameter getVitalsParameter() {
		return vitalsParameter;
	}

	public void setVitalsParameter(VitalsParameter vitalsParameter) {
		this.vitalsParameter = vitalsParameter;
	}
	*/
	
	
	
}