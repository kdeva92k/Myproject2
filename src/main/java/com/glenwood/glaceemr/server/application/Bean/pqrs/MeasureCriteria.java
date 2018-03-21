package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MeasureCriteria {
	
	
	private String criteriaNumber = new String();
	private String criteriaDescription = new String();
	private List<MeasureGroup> measureGroup = new ArrayList<MeasureGroup>();
	
	public String getCriteriaNumber() {
		return criteriaNumber;
	}
	public void setCriteriaNumber(String criteriaNumber) {
		this.criteriaNumber = criteriaNumber;
	}
	public String getCriteriaDescription() {
		return criteriaDescription;
	}
	public void setCriteriaDescription(String criteriaDescription) {
		this.criteriaDescription = criteriaDescription;
	}
	public List<MeasureGroup> getCodeSetList() {
		return measureGroup;
	}
	public void setCodeSetList(List<MeasureGroup> codeSetList) {
		this.measureGroup = codeSetList;
	}
	
	
	

}
