package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import java.util.Date;

public class LabParametersData {

	private Integer labEntriesParameterMapid;
	private Date labEntriesParameterDate;
	private String labEntriesParameterValue;
	private String labParametersUnits;
	
	public LabParametersData(){
		super();
	}
	
	

	public LabParametersData(Integer labEntriesParameterMapid,
			String labEntriesParameterValue,
			Date labEntriesParameterDate,
			String labParametersUnits) {
		this.labEntriesParameterMapid=labEntriesParameterMapid;
		this.labEntriesParameterValue=labEntriesParameterValue;
		this.labEntriesParameterDate=labEntriesParameterDate;
		this.labParametersUnits=labParametersUnits;
	}



	public Integer getLabEntriesParameterMapid() {
		return labEntriesParameterMapid;
	}

	public void setLabEntriesParameterMapid(Integer labEntriesParameterMapid) {
		this.labEntriesParameterMapid = labEntriesParameterMapid;
	}

	public Date getLabEntriesParameterDate() {
		return labEntriesParameterDate;
	}

	public void setLabEntriesParameterDate(Date labEntriesParameterDate) {
		this.labEntriesParameterDate = labEntriesParameterDate;
	}

	public String getLabEntriesParameterValue() {
		return labEntriesParameterValue;
	}

	public void setLabEntriesParameterValue(String labEntriesParameterValue) {
		this.labEntriesParameterValue = labEntriesParameterValue;
	}

	public String getLabParametersUnits() {
		return labParametersUnits;
	}

	public void setGender(String labParametersUnits) {
		this.labParametersUnits = labParametersUnits;
	}
}