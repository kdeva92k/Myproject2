package com.glenwood.glaceemr.server.application.services.chart.vitals;



public class DetailOptions {

	String optionName;
	String optionValue;
	String value;
	
	// For scribble
	
	String bkgndimage;
	Integer dataType;
	String height;
	String width;
	
	
	
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getBkgndimage() {
		return bkgndimage;
	}
	public void setBkgndimage(String bkgndimage) {
		this.bkgndimage = bkgndimage;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	
	
}
