package com.glenwood.glaceemr.server.application.services.roomStatus;

import java.util.List;

public class OrderedData {
	private List<OrderedBean> orderedLabs;
	private List<OrderedBean> orderdVaccine;
	private String patientId;
	
	

	public List<OrderedBean> getOrderedLabs() {
		return orderedLabs;
	}

	public void setOrderedLabs(List<OrderedBean> orderedLabs) {
		this.orderedLabs = orderedLabs;
	}

	public List<OrderedBean> getOrderdVaccine() {
		return orderdVaccine;
	}

	public void setOrderdVaccine(List<OrderedBean> orderdVaccine) {
		this.orderdVaccine = orderdVaccine;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
}
