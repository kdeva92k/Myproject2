package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class ParameterDetails {

	int labEntriesParameterTestdetailid;
	String labParameterCodeValue;
	String labEntriesParameterName;
	int labEntriesParameterStatus;
	String labEntriesParameterCodeSystem;
	String labEntriesParameterValue;
	Date labEntriesParameterDate;
	
	public int getLabEntriesParameterTestdetailid() {
		return labEntriesParameterTestdetailid;
	}
	public void setLabEntriesParameterTestdetailid(
			int labEntriesParameterTestdetailid) {
		this.labEntriesParameterTestdetailid = labEntriesParameterTestdetailid;
	}
	public String getLabParameterCodeValue() {
		return labParameterCodeValue;
	}
	public void setLabParameterCodeValue(String labParameterCodeValue) {
		this.labParameterCodeValue = labParameterCodeValue;
	}
	public String getLabEntriesParameterName() {
		return labEntriesParameterName;
	}
	public void setLabEntriesParameterName(String labEntriesParameterName) {
		this.labEntriesParameterName = labEntriesParameterName;
	}
	public int getLabEntriesParameterStatus() {
		return labEntriesParameterStatus;
	}
	public void setLabEntriesParameterStatus(int labEntriesParameterStatus) {
		this.labEntriesParameterStatus = labEntriesParameterStatus;
	}
	public String getLabEntriesParameterCodeSystem() {
		return labEntriesParameterCodeSystem;
	}
	public void setLabEntriesParameterCodeSystem(
			String labEntriesParameterCodeSystem) {
		this.labEntriesParameterCodeSystem = labEntriesParameterCodeSystem;
	}
	public String getLabEntriesParameterValue() {
		return labEntriesParameterValue;
	}
	public void setLabEntriesParameterValue(String labEntriesParameterValue) {
		this.labEntriesParameterValue = labEntriesParameterValue;
	}
	public Date getLabEntriesParameterDate() {
		return labEntriesParameterDate;
	}
	public void setLabEntriesParameterDate(Date labEntriesParameterDate) {
		this.labEntriesParameterDate = labEntriesParameterDate;
	}
	public ParameterDetails(int labEntriesParameterTestdetailid,
			String labParameterCodeValue, String labEntriesParameterName,
			int labEntriesParameterStatus,
			String labEntriesParameterCodeSystem,
			String labEntriesParameterValue, Date labEntriesParameterDate) {
		super();
		
		this.labEntriesParameterTestdetailid = labEntriesParameterTestdetailid;
		if(labParameterCodeValue!=null && !labParameterCodeValue.equals(null))
		this.labParameterCodeValue = labParameterCodeValue;
		this.labEntriesParameterName = labEntriesParameterName;
		this.labEntriesParameterStatus = 3;
		if(labEntriesParameterCodeSystem!=null && !labEntriesParameterCodeSystem.equals(null))
		this.labEntriesParameterCodeSystem = labEntriesParameterCodeSystem;
		if(labEntriesParameterValue!=null && !labEntriesParameterCodeSystem.equals(null))
		this.labEntriesParameterValue = labEntriesParameterValue;
		this.labEntriesParameterDate = labEntriesParameterDate;
	}
	
	
}
