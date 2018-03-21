package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "refill_message_type")
public class RefillMessageType {

	@Column(name="id")
	private Integer id;

	@Id
	@Column(name="refill_message_type_id")
	private Integer refillMessageTypeId;

	@Column(name="refill_message_type_name")
	private String refillMessageTypeName;

	@Column(name="refill_message_type_desc")
	private String refillMessageTypeDesc;

	@Column(name="refill_message_type_code")
	private Integer refillMessageTypeCode;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRefillMessageTypeId() {
		return refillMessageTypeId;
	}

	public void setRefillMessageTypeId(Integer refillMessageTypeId) {
		this.refillMessageTypeId = refillMessageTypeId;
	}

	public String getRefillMessageTypeName() {
		return refillMessageTypeName;
	}

	public void setRefillMessageTypeName(String refillMessageTypeName) {
		this.refillMessageTypeName = refillMessageTypeName;
	}

	public String getRefillMessageTypeDesc() {
		return refillMessageTypeDesc;
	}

	public void setRefillMessageTypeDesc(String refillMessageTypeDesc) {
		this.refillMessageTypeDesc = refillMessageTypeDesc;
	}

	public Integer getRefillMessageTypeCode() {
		return refillMessageTypeCode;
	}

	public void setRefillMessageTypeCode(Integer refillMessageTypeCode) {
		this.refillMessageTypeCode = refillMessageTypeCode;
	}

}