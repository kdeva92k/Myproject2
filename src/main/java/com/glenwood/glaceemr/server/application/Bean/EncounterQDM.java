package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class EncounterQDM {

	Integer encounterId;
	String code;
	Date startDate;
	Date endDate;
	Date serviceDate;
	String dxCode;
	Integer indicator;
	
	public EncounterQDM(){
		super();
	}

	public EncounterQDM(String code, Integer encounterId, Date startDate, Date endDate,String dxCode){

		
		this.encounterId = encounterId;
		this.code = code;
		if(startDate!=null)
		{
			this.startDate = startDate;
			if(endDate != null){
				this.endDate = endDate;
			}
		}
		if(dxCode!=null)
		this.dxCode=dxCode;
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
	
	
	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getDxCode() {
		return dxCode;
	}

	public void setDxCode(String dxCode) {
		this.dxCode = dxCode;
	}

	public Integer getIndicator() {
		return indicator;
	}

	public void setIndicator(Integer indicator) {
		this.indicator = indicator;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
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