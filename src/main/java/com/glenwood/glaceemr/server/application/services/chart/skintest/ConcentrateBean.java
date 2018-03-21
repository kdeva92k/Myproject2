package com.glenwood.glaceemr.server.application.services.chart.skintest;

public class ConcentrateBean {
	
	int concentrateId;
	String concentrateName;
	boolean isChecked;
	int concentrateOrder;
	
	public int getConcentrateId() {
		return concentrateId;
	}
	public void setConcentrateId(int concentrateId) {
		this.concentrateId = concentrateId;
	}
	public String getConcentrateName() {
		return concentrateName;
	}
	public void setConcentrateName(String concentrateName) {
		this.concentrateName = concentrateName;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public int getConcentrateOrder() {
		return concentrateOrder;
	}
	public void setConcentrateOrder(int concentrateOrder) {
		this.concentrateOrder = concentrateOrder;
	}
	@Override
	public String toString() {
		return "Order:"+concentrateOrder +"Name:" +concentrateName + "Is checked: "+isChecked;
	}
	
}
