package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import com.google.common.base.Optional;

public class FS_ClinicalElementBean {
	private String clinicalElementId;
	private String clinicalElementName;
	private String clinicalElementValue;
	private String clinicalElementUnits;
	private String clinicalElementOn;
	
	private String due;
	private String dueCriteria;
	
	public String getClinicalElementName() {
		return clinicalElementName;
	}
	public void setClinicalElementName(String clinicalElementName) {
		this.clinicalElementName = clinicalElementName;
	}
	public String getClinicalElementValue() {
		return clinicalElementValue;
	}
	public void setClinicalElementValue(String clinicalElementValue) {
		try{
			if(clinicalElementValue.contains(".")){
				double temp = Double.parseDouble(Optional.fromNullable(clinicalElementValue).or(""));
				this.clinicalElementValue = String.format("%.2f",temp);
			}else{
				this.clinicalElementValue = clinicalElementValue;
			}
		}catch(Exception e){
			this.clinicalElementValue = clinicalElementValue;
		}		
	}
	public String getClinicalElementUnits() {
		return clinicalElementUnits;
	}
	public void setClinicalElementUnits(String clinicalElementUnits) {
		this.clinicalElementUnits = clinicalElementUnits;
	}
	public String getClinicalElementOn() {
		return clinicalElementOn;
	}
	public void setClinicalElementOn(String clinicalElementOn) {
		this.clinicalElementOn = clinicalElementOn;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getDueCriteria() {
		return dueCriteria;
	}
	public void setDueCriteria(String dueCriteria) {
		this.dueCriteria = dueCriteria;
	}
	public String getClinicalElementId() {
		return clinicalElementId;
	}
	public void setClinicalElementId(String clinicalElementId) {
		this.clinicalElementId = clinicalElementId;
	}
}

