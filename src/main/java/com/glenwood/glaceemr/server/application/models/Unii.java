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
@Table(name = "unii")
public class Unii {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="unii_id_seq")
	@SequenceGenerator(name ="unii_id_seq", sequenceName="unii_id_seq", allocationSize=1)
	@Column(name="id")
	private Integer id;

	@Column(name="unii_code")
	private String uniiCode;

	@Column(name="unii_substance")
	private String uniiSubstance;

	@Column(name="unii_pref_substance")
	private String uniiPrefSubstance;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUniiCode() {
		return uniiCode;
	}

	public void setUniiCode(String uniiCode) {
		this.uniiCode = uniiCode;
	}

	public String getUniiSubstance() {
		return uniiSubstance;
	}

	public void setUniiSubstance(String uniiSubstance) {
		this.uniiSubstance = uniiSubstance;
	}

	public String getUniiPrefSubstance() {
		return uniiPrefSubstance;
	}

	public void setUniiPrefSubstance(String uniiPrefSubstance) {
		this.uniiPrefSubstance = uniiPrefSubstance;
	}
	
	
}