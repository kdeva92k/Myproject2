package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalLabResultsConfigBean {
	
	List<ChartStatus> labResultStatusList;

	public List<ChartStatus> getLabResultStatusList() {
		return labResultStatusList;
	}

	
	public void setLabResultStatusList(List<ChartStatus> labResultStatusList) {
		this.labResultStatusList = labResultStatusList;
	}
	
}
