package com.glenwood.glaceemr.server.application.services.chart.skintest;

public class SkinTestResult {
	
	Integer concentrateGroupId;
	String grade;
	String wheal;
	String flare;
	Boolean erythema;
	Boolean pseudopodia;
	Boolean readValue;
	
	public Integer getconcentrateGroupId() {
		return concentrateGroupId;
	}
	public void setconcentrateGroupId(Integer concentrateGroupId) {
		this.concentrateGroupId = concentrateGroupId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getWheal() {
		return wheal;
	}
	public void setWheal(String wheal) {
		this.wheal = wheal;
	}
	public String getFlare() {
		return flare;
	}
	public void setFlare(String flare) {
		this.flare = flare;
	}
	public Boolean isErythema() {
		return erythema;
	}
	public void setErythema(Boolean erythema) {
		this.erythema = erythema;
	}
	public Boolean isPseudopodia() {
		return pseudopodia;
	}
	public void setPseudopodia(Boolean pseudopodia) {
		this.pseudopodia = pseudopodia;
	}
	public Boolean getReadValue() {
		return readValue;
	}
	public void setReadValue(Boolean readValue) {
		this.readValue = readValue;
	}
	
}