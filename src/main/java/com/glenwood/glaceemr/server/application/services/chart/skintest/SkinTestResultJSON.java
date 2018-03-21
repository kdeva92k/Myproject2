package com.glenwood.glaceemr.server.application.services.chart.skintest;

public class SkinTestResultJSON {
	
	Integer orderDetailId;
	Integer allergenId;
	SkinTestResult result;
	
	public Integer getAllergenId() {
		return allergenId;
	}
	public void setAllergenId(Integer allergenId) {
		this.allergenId = allergenId;
	}
	public SkinTestResult getResult() {
		return result;
	}
	public void setResult(SkinTestResult result) {
		this.result = result;
	}
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	
}
