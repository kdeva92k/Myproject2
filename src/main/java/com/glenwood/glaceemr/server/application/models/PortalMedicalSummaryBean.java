package com.glenwood.glaceemr.server.application.models;

public class PortalMedicalSummaryBean {
	
	int totalActiveProblemsCount;
	
	int totalResolvedProblemsCount;
	
	int totalProblemsCount;
	
	int totalAllergiesCount;
	
	int totalPlanOfCareCount;
	
	
	public PortalMedicalSummaryBean(){
		
		
	}

	public PortalMedicalSummaryBean(int totalActiveProblemsCount,
			int totalResolvedProblemsCount, int totalProblemsCount,
			int totalAllergiesCount, int totalPlanOfCareCount) {
		super();
		this.totalActiveProblemsCount = totalActiveProblemsCount;
		this.totalResolvedProblemsCount = totalResolvedProblemsCount;
		this.totalProblemsCount = totalProblemsCount;
		this.totalAllergiesCount = totalAllergiesCount;
		this.totalPlanOfCareCount = totalPlanOfCareCount;
	}

	public int getTotalActiveProblemsCount() {
		return totalActiveProblemsCount;
	}

	public void setTotalActiveProblemsCount(int totalActiveProblemsCount) {
		this.totalActiveProblemsCount = totalActiveProblemsCount;
	}

	public int getTotalResolvedProblemsCount() {
		return totalResolvedProblemsCount;
	}

	public void setTotalResolvedProblemsCount(int totalResolvedProblemsCount) {
		this.totalResolvedProblemsCount = totalResolvedProblemsCount;
	}

	public int getTotalProblemsCount() {
		return totalProblemsCount;
	}

	public void setTotalProblemsCount(int totalProblemsCount) {
		this.totalProblemsCount = totalProblemsCount;
	}

	public int getTotalAllergiesCount() {
		return totalAllergiesCount;
	}

	public void setTotalAllergiesCount(int totalAllergiesCount) {
		this.totalAllergiesCount = totalAllergiesCount;
	}

	public int getTotalPlanOfCareCount() {
		return totalPlanOfCareCount;
	}

	public void setTotalPlanOfCareCount(int totalPlanOfCareCount) {
		this.totalPlanOfCareCount = totalPlanOfCareCount;
	}

	
}
