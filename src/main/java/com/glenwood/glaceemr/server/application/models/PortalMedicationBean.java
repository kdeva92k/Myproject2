package com.glenwood.glaceemr.server.application.models;

import java.util.Date;
import java.sql.Timestamp;

public class PortalMedicationBean {
	
	String drugName;
	String onsetDate;
	Boolean isActive;
	
	
	public PortalMedicationBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getDrugName() {
		return drugName;
	}


	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	
	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public String getOnsetDate() {
		return onsetDate;
	}


	public void setOnsetDate(String onsetDate) {
		this.onsetDate = onsetDate;
	}


	public PortalMedicationBean(String drugName, Date onsetDate,
			Boolean isActive) {
		super();
		if(drugName != null)
			this.drugName = drugName;
		
		if(onsetDate != null)
		this.onsetDate = onsetDate+"";
		
		if(isActive != null)
		this.isActive = isActive;
	}


		

}
