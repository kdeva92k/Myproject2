package com.glenwood.glaceemr.server.application.Bean.pqrs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDetails {
	
	private String code = new String();
	private String modifier = new String();
	private String shortDescription = new String();
	private String longDescription = new String();
	private String performanceIndicator = new String();
	private String reasonFlag = new String();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getPerformanceIndicator() {
		return performanceIndicator;
	}
	public void setPerformanceIndicator(String performanceIndicator) {
		this.performanceIndicator = performanceIndicator;
	}
	public String getReasonFlag() {
		return reasonFlag;
	}
	public void setReasonFlag(String reasonFlag) {
		this.reasonFlag = reasonFlag;
	}
	
    
}
