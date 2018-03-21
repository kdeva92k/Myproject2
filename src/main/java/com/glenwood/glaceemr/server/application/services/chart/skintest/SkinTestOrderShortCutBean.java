package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.SkinTestOrderAllergenCategory;

public class SkinTestOrderShortCutBean {
	private Integer orderId;
	private Integer patientId;
	List<SkinTestOrderAllergenCategory> allergenCategories;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public List<SkinTestOrderAllergenCategory> getAllergenCategories() {
		return allergenCategories;
	}
	public void setAllergenCategories(
			List<SkinTestOrderAllergenCategory> allergenCategories) {
		this.allergenCategories = allergenCategories;
	}
}
