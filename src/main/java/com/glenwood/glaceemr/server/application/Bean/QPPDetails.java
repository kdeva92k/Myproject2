package com.glenwood.glaceemr.server.application.Bean;

import java.util.ArrayList;
import java.util.List;

public class QPPDetails {

	private String programName="mips";
	private String entityType;
	private String taxpayerIdentificationNumber;
	private String nationalProviderIdentifier;
	private Integer performanceYear;
	private List<MeasureDetails> measurements =new ArrayList<MeasureDetails>();
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getTaxpayerIdentificationNumber() {
		return taxpayerIdentificationNumber;
	}
	public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
		this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
	}
	public String getNationalProviderIdentifier() {
		return nationalProviderIdentifier;
	}
	public void setNationalProviderIdentifier(String nationalProviderIdentifier) {
		this.nationalProviderIdentifier = nationalProviderIdentifier;
	}
	public Integer getPerformanceYear() {
		return performanceYear;
	}
	public void setPerformanceYear(Integer performanceYear) {
		this.performanceYear = performanceYear;
	}
	public List<MeasureDetails> getMeasurementSets() {
		return measurements;
	}
	public void setMeasurementSets(MeasureDetails measurementSets) {
		this.measurements.add(measurementSets);
	}
	
	
}
