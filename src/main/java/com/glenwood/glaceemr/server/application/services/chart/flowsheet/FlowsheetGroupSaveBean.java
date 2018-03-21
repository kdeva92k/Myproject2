package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class FlowsheetGroupSaveBean {

	Integer isStandardLab;
	String groupData="{}";

	public String getGroupData() {
		return groupData;
	}

	public void setGroupData(String groupData) {
		this.groupData = groupData;
	}

	public Integer getIsStandardLab() {
		return isStandardLab;
	}

	public void setIsStandardLab(Integer isStandardLab) {
		this.isStandardLab = isStandardLab;
	}
	
}
