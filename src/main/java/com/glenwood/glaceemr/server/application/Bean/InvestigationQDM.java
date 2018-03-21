package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class InvestigationQDM {
	int labEntriesStatus;
	int labEntriesConfirmTestStatus;
	Integer labEntriesTestdetailId;
	String code;
	String codeDescription;
	Integer status;
	Integer companyId;
	String stringCompanyId;
	Date createdOn;
	Date performeOn;
	String resultValue;
	public InvestigationQDM(Integer labEntriesStatus,Integer labEntriesConfirmTestStatus,Integer labEntriesTestdetailId,String code, String codeDescription, Integer status, Integer companyId, Date createdOn, Date performeOn) {
		
		super();
		if(labEntriesStatus != null)
			this.labEntriesStatus=labEntriesStatus;
		if(labEntriesConfirmTestStatus != null)
		this.labEntriesConfirmTestStatus=labEntriesConfirmTestStatus;
		
		this.labEntriesTestdetailId=labEntriesTestdetailId;
		this.code = code;
		this.codeDescription = codeDescription;
		this.status = status;
		this.createdOn = createdOn;
		this.stringCompanyId = companyId.toString();
		
		if(performeOn!=null){
			this.performeOn = performeOn;
		}
		else
			this.performeOn=createdOn;
		
	}
	
public InvestigationQDM(String resultValue,String code, String codeDescription, Integer status, String companyId, Date createdOn, Date performeOn) {
		
		super();
		this.resultValue=resultValue;
		this.code = code;
		this.codeDescription = codeDescription;
		this.status = status;
		this.createdOn = createdOn;
		this.stringCompanyId = companyId;
		
		if(performeOn!=null){
			this.performeOn = performeOn;
		}else
			this.performeOn=createdOn;
		
	}

	public int getLabEntriesStatus() {
	return labEntriesStatus;
}

public void setLabEntriesStatus(int labEntriesStatus) {
	this.labEntriesStatus = labEntriesStatus;
}

public String getStringCompanyId() {
	return stringCompanyId;
}

public void setStringCompanyId(String stringCompanyId) {
	this.stringCompanyId = stringCompanyId;
}

public int getLabEntriesConfirmTestStatus() {
	return labEntriesConfirmTestStatus;
}

public void setLabEntriesConfirmTestStatus(int labEntriesConfirmTestStatus) {
	this.labEntriesConfirmTestStatus = labEntriesConfirmTestStatus;
}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public Integer getLabEntriesTestdetailId() {
		return labEntriesTestdetailId;
	}

	public void setLabEntriesTestdetailId(Integer labEntriesTestdetailId) {
		this.labEntriesTestdetailId = labEntriesTestdetailId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getPerformeOn() {
		return performeOn;
	}

	public void setPerformeOn(Date performeOn) {
		this.performeOn = performeOn;
	}
	
}