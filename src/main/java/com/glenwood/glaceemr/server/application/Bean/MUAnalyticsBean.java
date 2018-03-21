package com.glenwood.glaceemr.server.application.Bean;

import java.util.ArrayList;
import java.util.List;

public class MUAnalyticsBean {

	int year;
	List<ReportingInfo> providers = new ArrayList<ReportingInfo>();
	
	public MUAnalyticsBean(int year, List<ReportingInfo> providers) {
		super();
		this.year = year;
		this.providers = providers;
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public List<ReportingInfo> getProviders() {
		return providers;
	}
	
	public void setProviders(List<ReportingInfo> providers) {
		this.providers = providers;
	}
	
}
