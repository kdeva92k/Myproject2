package com.glenwood.glaceemr.server.application.services.chart.plan;

public class Pages{
	
	String nextOffset;
	String currentPage;
	String totalPages;
	
	public String getNextOffset() {
		return nextOffset;
	}
	public void setNextOffset(String nextOffset) {
		this.nextOffset = nextOffset;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

}
