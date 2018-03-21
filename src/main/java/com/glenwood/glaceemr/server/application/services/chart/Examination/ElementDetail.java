package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.util.ArrayList;
import java.util.List;

import com.glenwood.glaceemr.server.application.services.chart.vitals.DetailOptions;

public class ElementDetail {

	String elementGwid;
	String elementDetailName;
	String elementPrintText;
	Integer dataType;
	String value;
	List<DetailOptions> detailOptions=new ArrayList<DetailOptions>();
	
	
	public String getElementGwid() {
		return elementGwid;
	}
	public void setElementGwid(String elementGwid) {
		this.elementGwid = elementGwid;
	}
	public String getElementDetailName() {
		return elementDetailName;
	}
	public void setElementDetailName(String elementDetailName) {
		this.elementDetailName = elementDetailName;
	}
	public String getElementPrintText() {
		return elementPrintText;
	}
	public void setElementPrintText(String elementPrintText) {
		this.elementPrintText = elementPrintText;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public List<DetailOptions> getDetailOptions() {
		return detailOptions;
	}
	public void setDetailOptions(List<DetailOptions> detailOptions) {
		this.detailOptions = detailOptions;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
