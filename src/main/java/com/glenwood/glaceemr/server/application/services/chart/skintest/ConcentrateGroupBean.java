package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcentrateGroupBean {
	
	private int concentrateGroupId;
	private String concentrateGroupName;
	private boolean isChecked;
	private int concentrateGroupOrder;

	private Map<Integer,ConcentrateBean> concentrateBeans;
	
	public int getConcentrateGroupId() {
		return concentrateGroupId;
	}
	public void setConcentrateGroupId(int concentrateGroupId) {
		this.concentrateGroupId = concentrateGroupId;
	}
	public String getConcentrateGroupName() {
		return concentrateGroupName;
	}
	public void setConcentrateGroupName(String concentrateGroupName) {
		this.concentrateGroupName = concentrateGroupName;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public int getConcentrateGroupOrder() {
		return concentrateGroupOrder;
	}
	public void setConcentrateGroupOrder(int concentrateGroupOrder) {
		this.concentrateGroupOrder = concentrateGroupOrder;
	}
	public Map<Integer,ConcentrateBean> getConcentrateBeans() {
		return concentrateBeans;
	}
	public void setConcentrateBeans(Map<Integer,ConcentrateBean> concentrateBeans) {
		this.concentrateBeans = concentrateBeans;
	}
	
	@Override
	public String toString() {
		return "Order: "+concentrateGroupOrder +"Name: " +concentrateGroupName +" is checked: "+isChecked+"\n Allergens: "+
				concentrateBeans.toString();
	}
}
