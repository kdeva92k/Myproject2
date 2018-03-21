package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_order_status")
public class SkinTestOrderStatus {

	@Id
	@Column(name="skin_test_order_status_id")
	private Integer skinTestOrderStatusId;

	@Column(name="skin_test_order_status_name")
	private String skinTestOrderStatusName;

	@Column(name="skin_test_order_status_desc")
	private String skinTestOrderStatusDesc;

	public Integer getSkinTestOrderStatusId() {
		return skinTestOrderStatusId;
	}

	public void setSkinTestOrderStatusId(Integer skinTestOrderStatusId) {
		this.skinTestOrderStatusId = skinTestOrderStatusId;
	}

	public String getSkinTestOrderStatusName() {
		return skinTestOrderStatusName;
	}

	public void setSkinTestOrderStatusName(String skinTestOrderStatusName) {
		this.skinTestOrderStatusName = skinTestOrderStatusName;
	}

	public String getSkinTestOrderStatusDesc() {
		return skinTestOrderStatusDesc;
	}

	public void setSkinTestOrderStatusDesc(String skinTestOrderStatusDesc) {
		this.skinTestOrderStatusDesc = skinTestOrderStatusDesc;
	}
	
}