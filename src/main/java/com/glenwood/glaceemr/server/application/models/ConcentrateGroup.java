package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "concentrate_group")
public class ConcentrateGroup {

	@Id
	@Column(name="concentrate_group_id")
	private Integer concentrateGroupId;

	@Column(name="concentrate_group_name")
	private String concentrateGroupName;

	@Column(name="concentrate_group_status")
	private Boolean concentrateGroupStatus;

	@Column(name="is_food_allergen_group")
	private Boolean isFoodAllergenGroup;

	@OneToMany(mappedBy="concentrateGroup")
	List<Concentrate> allergens;
	
	public Integer getConcentrateGroupId() {
		return concentrateGroupId;
	}

	public void setConcentrateGroupId(Integer concentrateGroupId) {
		this.concentrateGroupId = concentrateGroupId;
	}

	public String getConcentrateGroupName() {
		return concentrateGroupName;
	}

	public void setConcentrateGroupName(String concentrateGroupName) {
		this.concentrateGroupName = concentrateGroupName;
	}

	public Boolean getConcentrateGroupStatus() {
		return concentrateGroupStatus;
	}

	public void setConcentrateGroupStatus(Boolean concentrateGroupStatus) {
		this.concentrateGroupStatus = concentrateGroupStatus;
	}

	public Boolean getIsFoodAllergenGroup() {
		return isFoodAllergenGroup;
	}

	public void setIsFoodAllergenGroup(Boolean isFoodAllergenGroup) {
		this.isFoodAllergenGroup = isFoodAllergenGroup;
	}

	public List<Concentrate> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<Concentrate> allergens) {
		this.allergens = allergens;
	}
	
}