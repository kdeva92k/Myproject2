package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class EncounterQDM {

	String code;
	Date startDate;
	Date endDate;

	public EncounterQDM(){
		super();
	}

	public EncounterQDM(String code, Date startDate, Date endDate){

		this.code = code;
		this.startDate = startDate;
		if(endDate != null){
			this.endDate = endDate;
		}

	}
	public EncounterQDM(Date startDate, Date endDate){

		this.startDate = startDate;
		if(endDate != null){
			this.endDate = endDate;
		}

	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}