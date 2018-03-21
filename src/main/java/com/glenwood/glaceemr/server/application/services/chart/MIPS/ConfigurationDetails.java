package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.util.Date;


public class ConfigurationDetails {

	public Date macraProviderConfigurationReportingStart;

	public Date macraProviderConfigurationReportingEnd;
	
	public Integer macraProviderConfigurationReportingYear;

	public Integer macraProviderConfigurationReportingMethod;
	
	public Short macraProviderConfigurationReportType;
	
	public String qualityMeasuresProviderMappingMeasureId;
	
	public String qualityMeasuresProviderMappingTitle;
	
	public String qualityMeasuresProviderMappingPriority;
	
	public Boolean IAMeasuresStatus;
	
	public Integer IAMeasuresPoints;
	
	public String getQualityMeasuresProviderMappingMeasureId() {
		return qualityMeasuresProviderMappingMeasureId;
	}

	public void setQualityMeasuresProviderMappingMeasureId(
			String qualityMeasuresProviderMappingMeasureId) {
		this.qualityMeasuresProviderMappingMeasureId = qualityMeasuresProviderMappingMeasureId;
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

	
	public Short getMacraProviderConfigurationReportType() {
		return macraProviderConfigurationReportType;
	}

	public void setMacraProviderConfigurationReportType(
			Short macraProviderConfigurationReportType) {
		this.macraProviderConfigurationReportType = macraProviderConfigurationReportType;
	}

	public String getQualityMeasuresProviderMappingTitle() {
		return qualityMeasuresProviderMappingTitle;
	}

	public void setQualityMeasuresProviderMappingTitle(
			String qualityMeasuresProviderMappingTitle) {
		this.qualityMeasuresProviderMappingTitle = qualityMeasuresProviderMappingTitle;
	}

	public String getQualityMeasuresProviderMappingPriority() {
		return qualityMeasuresProviderMappingPriority;
	}

	public void setQualityMeasuresProviderMappingPriority(
			String qualityMeasuresProviderMappingPriority) {
		this.qualityMeasuresProviderMappingPriority = qualityMeasuresProviderMappingPriority;
	}
	
	public Boolean isIAMeasuresStatus() {
		return IAMeasuresStatus;
	}

	public void setIAMeasuresStatus(Boolean IAMeasuresStatus) {
		this.IAMeasuresStatus = IAMeasuresStatus;
	}

	public Integer getIAMeasuresPoints() {
		return IAMeasuresPoints;
	}

	public void setIaMeasuresPoints(Integer IAMeasuresPoints) {
		this.IAMeasuresPoints = IAMeasuresPoints;
	}

	public void setMacraProviderConfigurationReportingMethod(Integer macraProviderConfigurationReportingMethod) {
		this.macraProviderConfigurationReportingMethod = macraProviderConfigurationReportingMethod;
	}

	public ConfigurationDetails(Date macraProviderConfigurationReportingStart,Date macraProviderConfigurationReportingEnd,Integer macraProviderConfigurationReportingYear,Integer macraProviderConfigurationReportingMethod){
		this.macraProviderConfigurationReportingEnd=macraProviderConfigurationReportingEnd;
		this.macraProviderConfigurationReportingStart=macraProviderConfigurationReportingStart;
		this.macraProviderConfigurationReportingYear=macraProviderConfigurationReportingYear;
		this.macraProviderConfigurationReportingMethod=macraProviderConfigurationReportingMethod;
	}
	
	public ConfigurationDetails(String qualityMeasuresProviderMappingMeasureId){
		this.qualityMeasuresProviderMappingMeasureId=qualityMeasuresProviderMappingMeasureId;
	}

	

	public ConfigurationDetails(Date macraProviderConfigurationReportingStart,
			Date macraProviderConfigurationReportingEnd,
			Integer macraProviderConfigurationReportingYear,
			Integer macraProviderConfigurationReportingMethod,
			Short macraProviderConfigurationReportType) {
		super();
		this.macraProviderConfigurationReportingStart = macraProviderConfigurationReportingStart;
		this.macraProviderConfigurationReportingEnd = macraProviderConfigurationReportingEnd;
		this.macraProviderConfigurationReportingYear = macraProviderConfigurationReportingYear;
		this.macraProviderConfigurationReportingMethod = macraProviderConfigurationReportingMethod;
		this.macraProviderConfigurationReportType = macraProviderConfigurationReportType;
	}

	public ConfigurationDetails(String qualityMeasuresProviderMappingMeasureId,
			String qualityMeasuresProviderMappingTitle,
			String qualityMeasuresProviderMappingPriority) {
		super();
		this.qualityMeasuresProviderMappingMeasureId = qualityMeasuresProviderMappingMeasureId;
		this.qualityMeasuresProviderMappingTitle = qualityMeasuresProviderMappingTitle;
		this.qualityMeasuresProviderMappingPriority = qualityMeasuresProviderMappingPriority;
	}

	public ConfigurationDetails(String qualityMeasuresProviderMappingMeasureId,
			String qualityMeasuresProviderMappingTitle,
			String qualityMeasuresProviderMappingPriority,
			Boolean IAMeasuresStatus, Integer IAMeasuresPoints) {
		super();
		this.qualityMeasuresProviderMappingMeasureId = qualityMeasuresProviderMappingMeasureId;
		this.qualityMeasuresProviderMappingTitle = qualityMeasuresProviderMappingTitle;
		this.qualityMeasuresProviderMappingPriority = qualityMeasuresProviderMappingPriority;
		this.IAMeasuresStatus = IAMeasuresStatus;
		this.IAMeasuresPoints = IAMeasuresPoints;
	}
	
	
	
	
	
}