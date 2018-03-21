package com.glenwood.glaceemr.server.application.models;

public class DxDataSuperbillSummary {
 
	private String dx;
	private String description;
	
	public DxDataSuperbillSummary(String dx,String description){
		this.dx=dx;
		this.description=description;
	}

	public String getDx() {
		return dx;
	}

	public String getDescription() {
		return description;
	}

	public void setDx(String dx) {
		this.dx = dx;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
