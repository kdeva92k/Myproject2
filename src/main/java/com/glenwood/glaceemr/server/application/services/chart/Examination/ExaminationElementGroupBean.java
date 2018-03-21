package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.util.LinkedHashMap;

public class ExaminationElementGroupBean {
	private int elementGroupId;
	private String elementGroupName;
	private LinkedHashMap<Integer, ExaminationElementBean> examinationElements;

	public int getElementGroupId() {
		return elementGroupId;
	}
	
	public void setElementGroupId(int elementGroupId) {
		this.elementGroupId = elementGroupId;
	}
	
	public String getElementGroupName() {
		return elementGroupName;
	}
	
	public void setElementGroupName(String elementGroupName) {
		this.elementGroupName = elementGroupName;
	}
	
	public LinkedHashMap<Integer, ExaminationElementBean> getExaminationElements() {
		return examinationElements;
	}
	
	public void setExaminationElements(
			LinkedHashMap<Integer, ExaminationElementBean> examinationElements) {
		this.examinationElements = examinationElements;
	}

}
