package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class SkinTestOrderDetailsSaveJSON {
	Integer loginId;
	String patientId;
	String encounterId;
	String chartId;
	Integer orderId;
	Integer orderEntryId;
	Integer units;
	Integer billedStatus;
	Date testDate;
	/*Integer technician;*/
	String diluentWhealValue = null;
	String diluentFlareValue = null;
	String diluentGrade = null;
	Boolean diluentErythema = null;
	Boolean diluentPseudopodia = null;
	String histamineWhealValue = null;
	String histamineFlareValue = null;
	String histamineGrade = null;
	Boolean histamineErythema = null;
	Boolean histaminePseudopodia = null;
	List<SkinTestResultJSON> results;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderEntryId() {
		return orderEntryId;
	}
	public void setOrderEntryId(Integer orderEntryId) {
		this.orderEntryId = orderEntryId;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	/*public Integer getTechnician() {
		return technician;
	}
	public void setTechnician(Integer technician) {
		this.technician = technician;
	}*/
	
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public String getDiluentWhealValue() {
		return diluentWhealValue;
	}
	public void setDiluentWhealValue(String diluentWhealValue) {
		this.diluentWhealValue = diluentWhealValue;
	}
	public String getDiluentFlareValue() {
		return diluentFlareValue;
	}
	public void setDiluentFlareValue(String diluentFlareValue) {
		this.diluentFlareValue = diluentFlareValue;
	}
	public String getDiluentGrade() {
		return diluentGrade;
	}
	public void setDiluentGrade(String diluentGrade) {
		this.diluentGrade = diluentGrade;
	}
	public Boolean getDiluentErythema() {
		return diluentErythema;
	}
	public void setDiluentErythema(Boolean diluentErythema) {
		this.diluentErythema = diluentErythema;
	}
	public Boolean getDiluentPseudopodia() {
		return diluentPseudopodia;
	}
	public void setDiluentPseudopodia(Boolean diluentPseudopodia) {
		this.diluentPseudopodia = diluentPseudopodia;
	}
	public String getHistamineWhealValue() {
		return histamineWhealValue;
	}
	public void setHistamineWhealValue(String histamineWhealValue) {
		this.histamineWhealValue = histamineWhealValue;
	}
	public String getHistamineFlareValue() {
		return histamineFlareValue;
	}
	public void setHistamineFlareValue(String histamineFlareValue) {
		this.histamineFlareValue = histamineFlareValue;
	}
	public String getHistamineGrade() {
		return histamineGrade;
	}
	public void setHistamineGrade(String histamineGrade) {
		this.histamineGrade = histamineGrade;
	}
	public Boolean getHistamineErythema() {
		return histamineErythema;
	}
	public void setHistamineErythema(Boolean histamineErythema) {
		this.histamineErythema = histamineErythema;
	}
	public Boolean getHistaminePseudopodia() {
		return histaminePseudopodia;
	}
	public void setHistaminePseudopodia(Boolean histaminePseudopodia) {
		this.histaminePseudopodia = histaminePseudopodia;
	}
	public List<SkinTestResultJSON> getResults() {
		return results;
	}
	public void setResults(List<SkinTestResultJSON> results) {
		this.results = results;
	}
	public Integer getBilledStatus() {
		return billedStatus;
	}
	public void setBilledStatus(Integer billedStatus) {
		this.billedStatus = billedStatus;
	}
}
