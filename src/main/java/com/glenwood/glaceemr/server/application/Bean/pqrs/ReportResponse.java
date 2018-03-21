package com.glenwood.glaceemr.server.application.Bean.pqrs;

public class ReportResponse {

	private Integer providerId;
	private Integer patientId;
	private String dos;
	private String cptcode; 
	private String dxcode1;
	private String dxcode2;
	private String dxcode3;
	private String dxcode4;
	int ippcount = 0;
	int denominatorCount = 0;
	int denominatorExclusionCount = 0;
	int numeratorCount = 0;
	int numeratorExclusionCount = 0;
	int denominatorExceptionCount = 0;
	

	public ReportResponse(Integer providerId, Integer patientId, String dos,String cptcode, String dxcode1,String dxcode2,String dxcode3,String dxcode4,int ippcount,int denominatorCount,int denominatorExclusionCount,int numeratorCount,int numeratorExclusionCount,int denominatorExceptionCount) {
		super();
		this.providerId = providerId;
		this.patientId = patientId;
		this.dos = dos;
		this.cptcode = cptcode;
		this.dxcode1 = dxcode1;
		this.dxcode2 = dxcode2;
		this.dxcode3 = dxcode3;
		this.dxcode4 = dxcode4;
		this.ippcount = ippcount;
		this.denominatorCount = denominatorCount;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.numeratorCount = numeratorCount;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.denominatorExceptionCount = denominatorExceptionCount;
		
	}
		
	public int getIppcount() {
		return ippcount;
	}
	public void setIppcount(int ippcount) {
		this.ippcount = ippcount;
	}
	public int getDenominatorCount() {
		return denominatorCount;
	}
	public void setDenominatorCount(int denominatorCount) {
		this.denominatorCount = denominatorCount;
	}
	public int getDenominatorExclusionCount() {
		return denominatorExclusionCount;
	}
	public void setDenominatorExclusionCount(int denominatorExclusionCount) {
		this.denominatorExclusionCount = denominatorExclusionCount;
	}
	public int getNumeratorCount() {
		return numeratorCount;
	}
	public void setNumeratorCount(int numeratorCount) {
		this.numeratorCount = numeratorCount;
	}
	public int getNumeratorExclusionCount() {
		return numeratorExclusionCount;
	}
	public void setNumeratorExclusionCount(int numeratorExclusionCount) {
		this.numeratorExclusionCount = numeratorExclusionCount;
	}
	public int getDenominatorExceptionCount() {
		return denominatorExceptionCount;
	}
	public void setDenominatorExceptionCount(int denominatorExceptionCount) {
		this.denominatorExceptionCount = denominatorExceptionCount;
	}
	public String getDxcode1() {
		return dxcode1;
	}
	public void setDxcode1(String dxcode1) {
		this.dxcode1 = dxcode1;
	}
	public String getDxcode2() {
		return dxcode2;
	}
	public void setDxcode2(String dxcode2) {
		this.dxcode2 = dxcode2;
	}
	public String getDxcode3() {
		return dxcode3;
	}
	public void setDxcode3(String dxcode3) {
		this.dxcode3 = dxcode3;
	}
	public String getDxcode4() {
		return dxcode4;
	}
	public void setDxcode4(String dxcode4) {
		this.dxcode4 = dxcode4;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getCptcode() {
		return cptcode;
	}
	public void setCptcode(String cptcode) {
		this.cptcode = cptcode;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	
}
