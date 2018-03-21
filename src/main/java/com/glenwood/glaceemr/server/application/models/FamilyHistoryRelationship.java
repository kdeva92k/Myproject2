package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;
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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "family_history_relationship")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FamilyHistoryRelationship {

	@Id
	@Column(name="family_history_relationship_id")
	private Integer familyHistoryRelationshipId;

	@Column(name="family_history_relationship_name")
	private String familyHistoryRelationshipName;

	@Column(name="family_history_relationship_orderby")
	private Integer familyHistoryRelationshipOrderby;

	@Column(name="family_history_relationship_isactive")
	private Boolean familyHistoryRelationshipIsactive;
	
	@OneToMany(mappedBy="familyHistoryRelationship")
	List<FamilyHistoryElement> familyHistoryElement;

	public Integer getFamilyHistoryRelationshipId() {
		return familyHistoryRelationshipId;
	}

	public String getFamilyHistoryRelationshipName() {
		return familyHistoryRelationshipName;
	}

	public Integer getFamilyHistoryRelationshipOrderby() {
		return familyHistoryRelationshipOrderby;
	}

	public Boolean getFamilyHistoryRelationshipIsactive() {
		return familyHistoryRelationshipIsactive;
	}

	public List<FamilyHistoryElement> getFamilyHistoryElement() {
		return familyHistoryElement;
	}

	public void setFamilyHistoryRelationshipId(Integer familyHistoryRelationshipId) {
		this.familyHistoryRelationshipId = familyHistoryRelationshipId;
	}

	public void setFamilyHistoryRelationshipName(
			String familyHistoryRelationshipName) {
		this.familyHistoryRelationshipName = familyHistoryRelationshipName;
	}

	public void setFamilyHistoryRelationshipOrderby(
			Integer familyHistoryRelationshipOrderby) {
		this.familyHistoryRelationshipOrderby = familyHistoryRelationshipOrderby;
	}

	public void setFamilyHistoryRelationshipIsactive(
			Boolean familyHistoryRelationshipIsactive) {
		this.familyHistoryRelationshipIsactive = familyHistoryRelationshipIsactive;
	}

	public void setFamilyHistoryElement(
			List<FamilyHistoryElement> familyHistoryElement) {
		this.familyHistoryElement = familyHistoryElement;
	}	
	
	
	
	
}