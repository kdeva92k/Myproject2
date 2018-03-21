package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.sql.Date;
import java.util.List;


public class SkinTestOrderEntrySaveJSON {
	Boolean newEntry;
	Boolean isAllergensRecorded;
	Integer orderEntryId;
	Integer loginId;
	Integer orderId;
	String patientId;
	String chartId;
	String encounterId;
	String testDate;
	Integer technician;
	Boolean prickFlag;
	Boolean intradermalFlag;
	String concentration = null;
	Integer serviceDoctor;
	Integer pos;
	Integer billedStatus;
	String diluentWhealValue = null;
	String diluentFlareValue = null;
	String diluentGrade = null;
	Boolean diluentErythema = null;
	Boolean diluentPseudopodia =null;
	String histamineWhealValue =null;
	String histamineFlareValue = null;
	String histamineGrade = null;
	Boolean histamineErythema = null;
	Boolean histaminePseudopodia = null;
	Integer units;
	String defaultReadValue;

	List<SkinTestResultJSON> results;
	
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public Integer getTechnician() {
		return technician;
	}
	public void setTechnician(Integer technician) {
		this.technician = technician;
	}
	public Boolean getPrickFlag() {
		return prickFlag;
	}
	public void setPrickFlag(Boolean prickFlag) {
		this.prickFlag = prickFlag;
	}
	public Boolean getIntradermalFlag() {
		return intradermalFlag;
	}
	public void setIntradermalFlag(Boolean intradermalFlag) {
		this.intradermalFlag = intradermalFlag;
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
	public Integer getServiceDoctor() {
		return serviceDoctor;
	}
	public void setServiceDoctor(Integer serviceDoctor) {
		this.serviceDoctor = serviceDoctor;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public Integer getBilledStatus() {
		return billedStatus;
	}
	public void setBilledStatus(Integer billedStatus) {
		this.billedStatus = billedStatus;
	}
	public String getConcentration() {
		return concentration;
	}
	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public List<SkinTestResultJSON> getResults() {
		return results;
	}
	public void setResults(List<SkinTestResultJSON> results) {
		this.results = results;
	}
	public Boolean getNewEntry() {
		return newEntry;
	}
	public void setNewEntry(Boolean newEntry) {
		this.newEntry = newEntry;
	}
	public Integer getOrderEntryId() {
		return orderEntryId;
	}
	public void setOrderEntryId(Integer orderEntryId) {
		this.orderEntryId = orderEntryId;
	}
	public Boolean getIsAllergensRecorded() {
		return isAllergensRecorded;
	}
	public void setIsAllergensRecorded(Boolean isAllergensRecorded) {
		this.isAllergensRecorded = isAllergensRecorded;
	}
	public String getDefaultReadValue() {
		return defaultReadValue;
	}
	public void setDefaultReadValue(String defaultReadValue) {
		this.defaultReadValue = defaultReadValue;
	}
	
}