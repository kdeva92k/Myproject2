package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "hmr_groups")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HmrGroups {
	@Id
	@Column(name="hmr_group_id", nullable=false)
	@SequenceGenerator(name="hmr_groups_hmr_group_id_seq", sequenceName="hmr_groups_hmr_group_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="hmr_groups_hmr_group_id_seq")
	private Integer hmrGroupId;

	@Column(name="hmr_group_name", length=100)
	private String hmrGroupName;
	
	@Column(name="hmr_group_isactive")
	private Integer hmrGroupIsactive;
	
	@Column(name="modified_by")
	private Integer modifiedBy;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="modified_on")
	private Timestamp modifiedOn;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="created_on")
	private Timestamp createdOn;
	
	public Integer getHmrGroupId() {
		return hmrGroupId;
	}

	public void setHmrGroupId(Integer hmrGroupId) {
		this.hmrGroupId = hmrGroupId;
	}

	public String getHmrGroupName() {
		return hmrGroupName;
	}

	public void setHmrGroupName(String hmrGroupName) {
		this.hmrGroupName = hmrGroupName;
	}

	public Integer getHmrGroupIsactive() {
		return hmrGroupIsactive;
	}

	public void setHmrGroupIsactive(Integer hmrGroupIsactive) {
		this.hmrGroupIsactive = hmrGroupIsactive;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
}
