package com.glenwood.glaceemr.server.application.services.chart.print;

public class CustomGenericBean {

	String headerHTML;
	String patientHeaderHTML;
	String leftHeaderHTML;
	String footerHTML;
	
	public String getHeaderHTML() {
		return headerHTML;
	}
	public void setHeaderHTML(String headerHTML) {
		this.headerHTML = headerHTML;
	}
	public String getPatientHeaderHTML() {
		return patientHeaderHTML;
	}
	public void setPatientHeaderHTML(String patientHeaderHTML) {
		this.patientHeaderHTML = patientHeaderHTML;
	}
	public String getLeftHeaderHTML() {
		return leftHeaderHTML;
	}
	public void setLeftHeaderHTML(String leftHeaderHTML) {
		this.leftHeaderHTML = leftHeaderHTML;
	}
	public String getFooterHTML() {
		return footerHTML;
	}
	public void setFooterHTML(String footerHTML) {
		this.footerHTML = footerHTML;
	}
	
}
