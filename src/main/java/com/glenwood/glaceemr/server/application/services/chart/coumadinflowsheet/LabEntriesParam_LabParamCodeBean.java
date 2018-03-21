package com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet;


import java.sql.Date;

public class LabEntriesParam_LabParamCodeBean {
	private String LabEntriesParamValue;
	private Date LabEntriesParamDate;
	private Integer LabParameterCodeParamid;
	private String LabParameterCodeValue;
	private Integer LabEntriesParamId;
	
	public Integer getLabEntriesParamId() {
		return LabEntriesParamId;
	}
	public void setLabEntriesParamId(Integer labEntriesParamId) {
		LabEntriesParamId = labEntriesParamId;
	}
	public LabEntriesParam_LabParamCodeBean(String LabEntriesParamValue,Date LabEntriesParamDate,Integer LabParameterCodeParamid,String LabParameterCodeValue){
		this.LabEntriesParamValue=LabEntriesParamValue;
		this.LabEntriesParamDate=LabEntriesParamDate;
		this.LabParameterCodeParamid=LabParameterCodeParamid;
		this.LabParameterCodeValue=LabParameterCodeValue;
		
		
	}
	public LabEntriesParam_LabParamCodeBean(String LabEntriesParamValue,Integer LabParameterCodeParamid,String LabParameterCodeValue,Integer LabEntriesParamId){
		this.LabEntriesParamValue=LabEntriesParamValue;
		this.LabEntriesParamId=LabEntriesParamId;
		this.LabParameterCodeParamid=LabParameterCodeParamid;
		this.LabParameterCodeValue=LabParameterCodeValue;
		
		
	}

	public String getLabEntriesParamValue() {
		return LabEntriesParamValue;
	}

	public void setLabEntriesParamValue(String labEntriesParamValue) {
		LabEntriesParamValue = labEntriesParamValue;
	}

	public Date getLabEntriesParamDate() {
		return LabEntriesParamDate;
	}

	public void setLabEntriesParamDate(Date labEntriesParamDate) {
		LabEntriesParamDate = labEntriesParamDate;
	}

	public Integer getLabParameterCodeParamid() {
		return LabParameterCodeParamid;
	}

	public void setLabParameterCodeParamid(Integer labParameterCodeParamid) {
		LabParameterCodeParamid = labParameterCodeParamid;
	}

	public String getLabParameterCodeValue() {
		return LabParameterCodeValue;
	}

	public void setLabParameterCodeValue(String labParameterCodeValue) {
		LabParameterCodeValue = labParameterCodeValue;
	}
}

