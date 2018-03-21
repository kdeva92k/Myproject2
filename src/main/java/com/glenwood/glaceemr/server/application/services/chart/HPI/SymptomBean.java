package com.glenwood.glaceemr.server.application.services.chart.HPI;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SymptomBean{
	private String symptomGWId;
	private String symptomName;
	private String symptomComments;
	private int symptomType;
	private String symptomPrintText;
	private int symptomId;
	private String retaincase;
	private LinkedHashMap<String, QualifierBean> symptomQualifiers;
	public  SymptomBean(){
		symptomGWId="-1";
		symptomName="";
		symptomComments="";
		symptomPrintText="";
		symptomType=-1;
		symptomId=-1;
		retaincase="t";
		symptomQualifiers=new LinkedHashMap<String, QualifierBean>();
	}
	
	public String getSymptomGWId() {
		return symptomGWId;
	}
	public void setSymptomGWId(String symptomGWId) {
		this.symptomGWId = symptomGWId;
	}

	public String getSymptomName() {
		return symptomName;
	}
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	public String getSymptomComments() {
		return symptomComments;
	}
	public void setSymptomComments(String symptomComments) {
		this.symptomComments = symptomComments;
	}
	public int getSymptomType() {
		return symptomType;
	}
	public void setSymptomType(int symptomType) {
		this.symptomType = symptomType;
	}
	public LinkedHashMap<String, QualifierBean> getSymptomQualifiers() {
		return symptomQualifiers;
	}
	public void setSymptomQualifiers(LinkedHashMap<String, QualifierBean> symptomQualifiers) {
		this.symptomQualifiers = symptomQualifiers;
	}

	public String getSymptomPrintText() {
		return symptomPrintText;
	}

	public void setSymptomPrintText(String symptomPrintText) {
		this.symptomPrintText = symptomPrintText;
	}

	public int getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(int symptomId) {
		this.symptomId = symptomId;
	}
	public String getRetainCase() {
		return retaincase;
	}

	public void setRetainCase(String retaincase) {
		this.retaincase = retaincase;
	}
}