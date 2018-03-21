package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalRefillRequestBean {

	int patientId;
	
	int chartId;
	
	String username;
		
	String pharmacyName;
	
	int pharmacyId;
	
	List<PortalRefillRequestRxBean> precriptionList;
		
	String comments;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getChartId() {
		return chartId;
	}

	public void setChartId(int chartId) {
		this.chartId = chartId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public int getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(int pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public List<PortalRefillRequestRxBean> getPrecriptionList() {
		return precriptionList;
	}

	public void setPrecriptionList(List<PortalRefillRequestRxBean> precriptionList) {
		this.precriptionList = precriptionList;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
