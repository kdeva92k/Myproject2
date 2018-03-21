package com.glenwood.glaceemr.server.application.services.investigation;

import java.util.List;

public class OrderLogGroups {
	int count;
	List<OrderLog> laboratories;
	List<OrderLog> radiology;
	List<OrderLog> miscellaneous;
	List<OrderLog> procedures;
	List<OrderLog> vaccinations;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<OrderLog> getLaboratories() {
		return laboratories;
	}	
	public void setLaboratories(List<OrderLog> laboratories) {
		this.laboratories = laboratories;
	}
	public List<OrderLog> getRadiology() {
		return radiology;
	}
	public void setRadiology(List<OrderLog> radiology) {
		this.radiology = radiology;
	}
	public List<OrderLog> getMiscellaneous() {
		return miscellaneous;
	}
	public void setMiscellaneous(List<OrderLog> miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	public List<OrderLog> getProcedures() {
		return procedures;
	}
	public void setProcedures(List<OrderLog> procedures) {
		this.procedures = procedures;
	}
	public List<OrderLog> getVaccinations() {
		return vaccinations;
	}
	public void setVaccinations(List<OrderLog> vaccinations) {
		this.vaccinations = vaccinations;
	}
}
