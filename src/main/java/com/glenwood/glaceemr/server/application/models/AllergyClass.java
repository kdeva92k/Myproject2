package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "allergy_class")
public class AllergyClass {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="allergy_class_allergy_class_id_seq")
	@SequenceGenerator(name ="allergy_class_allergy_class_id_seq", sequenceName="allergy_class_allergy_class_id_seq", allocationSize=1)
	@Column(name="allergy_class_id")
	private Integer allergyClassId;

	@Column(name="allergy_class_code")
	private Integer allergyClassCode;

	@Column(name="allergy_class_name")
	private String allergyClassName;

	@Column(name="allergy_class_generic")
	private String allergyClassGeneric;

	@Column(name="allergy_class_display_name")
	private String allergyClassDisplayName;

	@Column(name="allergy_class_search_name")
	private String allergyClassSearchName;

	@Column(name="allergy_class_generic_id")
	private Integer allergyClassGenericId;

	public Integer getAllergyClassId() {
		return allergyClassId;
	}

	public void setAllergyClassId(Integer allergyClassId) {
		this.allergyClassId = allergyClassId;
	}

	public Integer getAllergyClassCode() {
		return allergyClassCode;
	}

	public void setAllergyClassCode(Integer allergyClassCode) {
		this.allergyClassCode = allergyClassCode;
	}

	public String getAllergyClassName() {
		return allergyClassName;
	}

	public void setAllergyClassName(String allergyClassName) {
		this.allergyClassName = allergyClassName;
	}

	public String getAllergyClassGeneric() {
		return allergyClassGeneric;
	}

	public void setAllergyClassGeneric(String allergyClassGeneric) {
		this.allergyClassGeneric = allergyClassGeneric;
	}

	public String getAllergyClassDisplayName() {
		return allergyClassDisplayName;
	}

	public void setAllergyClassDisplayName(String allergyClassDisplayName) {
		this.allergyClassDisplayName = allergyClassDisplayName;
	}

	public String getAllergyClassSearchName() {
		return allergyClassSearchName;
	}

	public void setAllergyClassSearchName(String allergyClassSearchName) {
		this.allergyClassSearchName = allergyClassSearchName;
	}

	public Integer getAllergyClassGenericId() {
		return allergyClassGenericId;
	}

	public void setAllergyClassGenericId(Integer allergyClassGenericId) {
		this.allergyClassGenericId = allergyClassGenericId;
	}
	
}