package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "allergies_type")
public class AllergiesType {

	@Id
	@Column(name="allergtype_id")
	private Integer allergtypeId;

	@Column(name="allergtype_name")
	private String allergtypeName;

	@Column(name="allergtype_isactive")
	private Boolean allergtypeIsactive;

	@Column(name="allergtype_gender")
	private Integer allergtypeGender;

	@OneToMany(mappedBy="allergiesType",fetch=FetchType.LAZY)
	List<PatientAllergies> patientAllergies;
	
	
	
	public List<PatientAllergies> getPatientAllergies() {
		return patientAllergies;
	}

	public void setPatientAllergies(List<PatientAllergies> patientAllergies) {
		this.patientAllergies = patientAllergies;
	}

	public Integer getAllergtypeId() {
		return allergtypeId;
	}

	public void setAllergtypeId(Integer allergtypeId) {
		this.allergtypeId = allergtypeId;
	}

	public String getAllergtypeName() {
		return allergtypeName;
	}

	public void setAllergtypeName(String allergtypeName) {
		this.allergtypeName = allergtypeName;
	}

	public Boolean getAllergtypeIsactive() {
		return allergtypeIsactive;
	}

	public void setAllergtypeIsactive(Boolean allergtypeIsactive) {
		this.allergtypeIsactive = allergtypeIsactive;
	}

	public Integer getAllergtypeGender() {
		return allergtypeGender;
	}

	public void setAllergtypeGender(Integer allergtypeGender) {
		this.allergtypeGender = allergtypeGender;
	}
	
	
}