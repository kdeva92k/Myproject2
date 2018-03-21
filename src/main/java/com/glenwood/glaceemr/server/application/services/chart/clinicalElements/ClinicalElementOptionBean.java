package com.glenwood.glaceemr.server.application.services.chart.clinicalElements;

public class ClinicalElementOptionBean{
	private int clinicalelementoptionId;
	private int clinicalelementoptionOrderby;
	private String clinicalelementoptionGwid;
	private String clinicalelementoptionName;
	private String clinicalelementoptionValue;
	private boolean clinicalelementoptionRetainCase;

	public ClinicalElementOptionBean(){
		
	}
	
	public ClinicalElementOptionBean(String clinicalelementoptionValue, String clinicalelementoptionName, boolean clinicalelementoptionRetainCase){
		this.clinicalelementoptionValue= clinicalelementoptionValue;
		this.clinicalelementoptionName= clinicalelementoptionName;
		this.clinicalelementoptionRetainCase= clinicalelementoptionRetainCase;
	}
	
	public int getClinicalelementoptionId() {
		return clinicalelementoptionId;
	}

	public void setClinicalelementoptionId(int clinicalelementoptionId) {
		this.clinicalelementoptionId = clinicalelementoptionId;
	}

	public int getClinicalelementoptionOrderby() {
		return clinicalelementoptionOrderby;
	}

	public void setClinicalelementoptionOrderby(int clinicalelementoptionOrderby) {
		this.clinicalelementoptionOrderby = clinicalelementoptionOrderby;
	}

	public String getClinicalelementoptionGwid() {
		return clinicalelementoptionGwid;
	}

	public void setClinicalelementoptionGwid(String clinicalelementoptionGwid) {
		this.clinicalelementoptionGwid = clinicalelementoptionGwid;
	}

	public String getClinicalelementoptionName() {
		return clinicalelementoptionName;
	}

	public void setClinicalelementoptionName(String clinicalelementoptionName) {
		this.clinicalelementoptionName = clinicalelementoptionName;
	}

	public String getClinicalelementoptionValue() {
		return clinicalelementoptionValue;
	}

	public void setClinicalelementoptionValue(String clinicalelementoptionValue) {
		this.clinicalelementoptionValue = clinicalelementoptionValue;
	}

	public boolean getClinicalelementoptionRetainCase() {
		return clinicalelementoptionRetainCase;
	}

	public void setClinicalelementoptionRetainCase(
			boolean clinicalelementoptionRetainCase) {
		this.clinicalelementoptionRetainCase = clinicalelementoptionRetainCase;
	}

	
}

