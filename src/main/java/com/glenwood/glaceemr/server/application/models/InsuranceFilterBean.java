package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class InsuranceFilterBean {
	
	String insShortId;
	
	String insName;
	
	String insAddress;
	
	String insState;
	
	String insGroupId;
	
	Boolean isInsActive;
			
	Integer pageOffset;
	
	Integer pageIndex;
	
	long totalInsurancesCount;
	
	//List<InsCompAddr> insuranceList;

	public String getInsShortId() {
		return insShortId;
	}

	public void setInsShortId(String insShortId) {
		this.insShortId = insShortId;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}

	public String getInsAddress() {
		return insAddress;
	}

	public void setInsAddress(String insAddress) {
		this.insAddress = insAddress;
	}
	
	public String getInsState() {
		return insState;
	}

	public void setInsState(String insState) {
		this.insState = insState;
	}

	public String getInsGroupId() {
		return insGroupId;
	}

	public void setInsGroupId(String insGroupId) {
		this.insGroupId = insGroupId;
	}

	public Boolean getIsInsActive() {
		return isInsActive;
	}

	public void setIsInsActive(Boolean isInsActive) {
		this.isInsActive = isInsActive;
	}

	public Integer getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getTotalInsurancesCount() {
		return totalInsurancesCount;
	}

	public void setTotalInsurancesCount(long totalInsurancesCount) {
		this.totalInsurancesCount = totalInsurancesCount;
	}

	/*
	public List<InsCompAddr> getInsuranceList() {
		return insuranceList;
	}

	public void setInsuranceList(List<InsCompAddr> insuranceList) {
		this.insuranceList = insuranceList;
	}*/
	
}
