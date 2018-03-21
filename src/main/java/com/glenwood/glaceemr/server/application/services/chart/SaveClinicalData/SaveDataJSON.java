package com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData;

public class SaveDataJSON {

int templateId=-1;
int chartId=-1;
int patientId=-1;
int encounterId=-1;
String saveObj="{}";


public int getTemplateId() {
	return templateId;
}
public void setTemplateId(int templateId) {
	this.templateId = templateId;
}
public int getChartId() {
	return chartId;
}
public void setChartId(int chartId) {
	this.chartId = chartId;
}
public int getPatientId() {
	return patientId;
}
public void setPatientId(int patientId) {
	this.patientId = patientId;
}
public int getEncounterId() {
	return encounterId;
}
public void setEncounterId(int encounterId) {
	this.encounterId = encounterId;
}
public String getSaveObj() {
	return saveObj;
}
public void setSaveObj(String saveObj) {
	this.saveObj = saveObj;
}
	
}
