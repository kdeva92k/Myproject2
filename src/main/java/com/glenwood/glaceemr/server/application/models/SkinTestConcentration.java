package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_concentration")
public class SkinTestConcentration {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_concentration_id")
	@SequenceGenerator(name="skin_test_concentration_id",sequenceName="skin_test_concentration_id",allocationSize=1)
	@Column(name="skin_test_concentration_id")
	private Integer skinTestConcentrationId;

	@Column(name="skin_test_concentration_value")
	private String skinTestConcentrationValue;

	@Column(name="skin_test_concentration_desc")
	private String skinTestConcentrationDesc;

	@Column(name="skin_test_concentration_is_active")
	private Boolean skinTestConcentrationIsActive;

	public Integer getSkinTestConcentrationId() {
		return skinTestConcentrationId;
	}

	public void setSkinTestConcentrationId(Integer skinTestConcentrationId) {
		this.skinTestConcentrationId = skinTestConcentrationId;
	}

	public String getSkinTestConcentrationValue() {
		return skinTestConcentrationValue;
	}

	public void setSkinTestConcentrationValue(String skinTestConcentrationValue) {
		this.skinTestConcentrationValue = skinTestConcentrationValue;
	}

	public String getSkinTestConcentrationDesc() {
		return skinTestConcentrationDesc;
	}

	public void setSkinTestConcentrationDesc(String skinTestConcentrationDesc) {
		this.skinTestConcentrationDesc = skinTestConcentrationDesc;
	}

	public Boolean getSkinTestConcentrationIsActive() {
		return skinTestConcentrationIsActive;
	}

	public void setSkinTestConcentrationIsActive(
			Boolean skinTestConcentrationIsActive) {
		this.skinTestConcentrationIsActive = skinTestConcentrationIsActive;
	}
	
	
}