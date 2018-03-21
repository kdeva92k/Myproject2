package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class LabEntriesDetailsBean {

	Integer labEntriesTestStatus;
	Integer labEntriesPrelimTestStatus;
	Integer labEntriesConfirmTestStatus;
	String labEntriesResultNotes;
	Date labEntriesRevOn;
	Integer labEntriesRevBy;
	Integer labEntriesSepcimenId;
	Integer labEntriesTestdetailId;
	
	public LabEntriesDetailsBean(){
		
	}
	public LabEntriesDetailsBean(Integer labEntriesTestStatus,Integer labEntriesPrelimTestStatus,Integer labEntriesConfirmTestStatus,String labEntriesResultNotes,
			Date labEntriesRevOn,Integer labEntriesRevBy,Integer labEntriesSepcimenId,Integer labEntriesTestdetailId){
		this.labEntriesTestStatus=labEntriesTestStatus;
		this.labEntriesPrelimTestStatus=labEntriesPrelimTestStatus;
		this.labEntriesConfirmTestStatus=labEntriesConfirmTestStatus;
		this.labEntriesResultNotes=labEntriesResultNotes;
		this.labEntriesRevOn=labEntriesRevOn;
		this.labEntriesRevBy=labEntriesRevBy;
		this.labEntriesSepcimenId=labEntriesSepcimenId;
		this.labEntriesTestdetailId=labEntriesTestdetailId;
	}
	
	public Integer getLabEntriesTestStatus() {
		return labEntriesTestStatus;
	}

	public void setLabEntriesTestStatus(Integer labEntriesTestStatus) {
		this.labEntriesTestStatus = labEntriesTestStatus;
	}
	
	public Integer getLabEntriesPrelimTestStatus() {
		return labEntriesPrelimTestStatus;
	}

	public void setLabEntriesPrelimTestStatus(Integer labEntriesPrelimTestStatus) {
		this.labEntriesPrelimTestStatus = labEntriesPrelimTestStatus;
	}
	
	public Integer getLabEntriesConfirmTestStatus() {
		return labEntriesConfirmTestStatus;
	}

	public void setLabEntriesConfirmTestStatus(Integer labEntriesConfirmTestStatus) {
		this.labEntriesConfirmTestStatus = labEntriesConfirmTestStatus;
	}
	
	public String getLabEntriesResultNotes() {
		return labEntriesResultNotes;
	}

	public void setLabEntriesResultNotes(String labEntriesResultNotes) {
		this.labEntriesResultNotes = labEntriesResultNotes;
	}
	
	public Date getLabEntriesRevOn() {
		return labEntriesRevOn;
	}

	public void setLabEntriesRevOn(Date labEntriesRevOn) {
		this.labEntriesRevOn = labEntriesRevOn;
	}
	public Integer getLabEntriesRevBy() {
		return labEntriesRevBy;
	}

	public void setLabEntriesRevBy(Integer labEntriesRevBy) {
		this.labEntriesRevBy = labEntriesRevBy;
	}

	public Integer getLabEntriesSepcimenId() {
		return labEntriesSepcimenId;
	}

	public void setLabEntriesSepcimenId(Integer labEntriesSepcimenId) {
		this.labEntriesSepcimenId = labEntriesSepcimenId;
	}
	
	public Integer getLabEntriesTestdetailId() {
		return labEntriesTestdetailId;
	}

	public void setLabEntriesTestdetailId(Integer labEntriesTestdetailId) {
		this.labEntriesTestdetailId = labEntriesTestdetailId;
	}

}