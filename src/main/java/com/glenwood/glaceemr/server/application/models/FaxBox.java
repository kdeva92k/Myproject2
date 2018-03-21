package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "fax_box")
public class FaxBox {

	@Id
	@Column(name="id")
	private Integer faxBoxId;

	@Column(name="faxboxname")
	private String faxBoxName;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="createdon")
	private Timestamp createdOn;

	@Column(name="isdisabled")
	private Boolean isDisabled;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="fax_lastaccessedtime")
	private Timestamp lastAccessedTime;
	
	@Column(name="is_default")
	private Boolean isDefault;
	
	
	public Integer getFaxBoxId() {
		return faxBoxId;
	}

	public void setFaxBoxId(Integer faxBoxId) {
		this.faxBoxId = faxBoxId;
	}

	public String getFaxBoxName() {
		return faxBoxName;
	}

	public void setFaxBoxName(String faxBoxName) {
		this.faxBoxName = faxBoxName;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public Timestamp getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(Timestamp lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}



}
