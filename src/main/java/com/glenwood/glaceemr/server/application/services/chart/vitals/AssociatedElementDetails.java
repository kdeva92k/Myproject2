package com.glenwood.glaceemr.server.application.services.chart.vitals;

import java.util.ArrayList;
import java.util.List;



public class AssociatedElementDetails {
	
	String id;
	String unit;
	String vitalType;
	String elementName;
	String elementGWID;
	Integer clinicalElementType;
	String main;
	String mainType;
	String condition;
	Integer conditionType;
	String value;
	String hidden;
	Integer isDate;
	String isSelect;
	List<DetailOptions> detailOptions=new ArrayList<DetailOptions>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getVitalType() {
		return vitalType;
	}
	public void setVitalType(String vitalType) {
		this.vitalType = vitalType;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getElementGWID() {
		return elementGWID;
	}
	public void setElementGWID(String elementGWID) {
		this.elementGWID = elementGWID;
	}
	public Integer getClinicalElementType() {
		return clinicalElementType;
	}
	public void setClinicalElementType(Integer clinicalElementType) {
		this.clinicalElementType = clinicalElementType;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getMainType() {
		return mainType;
	}
	public void setMainType(String mainType) {
		this.mainType = mainType;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Integer getConditionType() {
		return conditionType;
	}
	public void setConditionType(Integer conditionType) {
		this.conditionType = conditionType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public Integer getIsDate() {
		return isDate;
	}
	public void setIsDate(Integer isDate) {
		this.isDate = isDate;
	}
	public String getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	public List<DetailOptions> getDetailOptions() {
		return detailOptions;
	}
	public void setDetailOptions(List<DetailOptions> detailOptions) {
		this.detailOptions = detailOptions;
	}
	
	
	
	
	
}
