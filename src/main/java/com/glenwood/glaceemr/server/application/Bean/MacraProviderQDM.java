package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class MacraProviderQDM {

	Integer macraProviderConfigurationProviderId;
	Integer macraProviderConfigurationReportingYear;
	Date macraProviderConfigurationReportingStart;
	Date macraProviderConfigurationReportingEnd;
	Integer macraProviderConfigurationReportingMethod;
	String measures;
	
	public MacraProviderQDM(Integer macraProviderConfigurationProviderId,
			Integer macraProviderConfigurationReportingYear,
			Date macraProviderConfigurationReportingStart,
			Date macraProviderConfigurationReportingEnd,
			Integer macraProviderConfigurationReportingMethod) {
		super();
		this.macraProviderConfigurationProviderId = macraProviderConfigurationProviderId;
		this.macraProviderConfigurationReportingYear = macraProviderConfigurationReportingYear;
		this.macraProviderConfigurationReportingStart = macraProviderConfigurationReportingStart;
		this.macraProviderConfigurationReportingEnd = macraProviderConfigurationReportingEnd;
		this.macraProviderConfigurationReportingMethod = macraProviderConfigurationReportingMethod;
	}

	public Integer getMacraProviderConfigurationProviderId() {
		return macraProviderConfigurationProviderId;
	}
	
	public void setMacraProviderConfigurationProviderId(
			Integer macraProviderConfigurationProviderId) {
		this.macraProviderConfigurationProviderId = macraProviderConfigurationProviderId;
	}
	
	public Integer getMacraProviderConfigurationReportingYear() {
		return macraProviderConfigurationReportingYear;
	}
	
	public void setMacraProviderConfigurationReportingYear(
			Integer macraProviderConfigurationReportingYear) {
		this.macraProviderConfigurationReportingYear = macraProviderConfigurationReportingYear;
	}
	
	public Date getMacraProviderConfigurationReportingStart() {
		return macraProviderConfigurationReportingStart;
	}
	
	public void setMacraProviderConfigurationReportingStart(
			Date macraProviderConfigurationReportingStart) {
		this.macraProviderConfigurationReportingStart = macraProviderConfigurationReportingStart;
	}
	
	public Date getMacraProviderConfigurationReportingEnd() {
		return macraProviderConfigurationReportingEnd;
	}
	
	public void setMacraProviderConfigurationReportingEnd(
			Date macraProviderConfigurationReportingEnd) {
		this.macraProviderConfigurationReportingEnd = macraProviderConfigurationReportingEnd;
	}
	
	public Integer getMacraProviderConfigurationReportingMethod() {
		return macraProviderConfigurationReportingMethod;
	}
	
	public void setMacraProviderConfigurationReportingMethod(
			Integer macraProviderConfigurationReportingMethod) {
		this.macraProviderConfigurationReportingMethod = macraProviderConfigurationReportingMethod;
	}
	
	public String getMeasures() {
		return measures;
	}
	
	public void setMeasures(String measures) {
		this.measures = measures;
	}
	
}
