package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

import com.glenwood.glaceemr.server.application.models.CarePlanIntervention_;


public class ClinicalDataQDM {
	private String code;	
	private String codeSystem;
	private String codeSystemOID;
	private String elementName;
	private Date  startDate;
	private Date  endDate;
	private Date  recordedDate;
	private String resultCode;
	private String resultCodeSystem;
	private String resultValue;
	private String patientResult;
	private int patientId;
	private String optionValue;
	private int Status;
	private String notDoneDesc;
	private String notDoneCode;
	private String notDoneCodeSystemOID;
	private String gwid;
	/*private Date orderedDate;
	private Date performedDate;*/
	
	
	public int getStatus() {
		return Status;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
	public String getNotDoneDesc() {
		return notDoneDesc;
	}
	public void setNotDoneDesc(String notDoneDesc) {
		this.notDoneDesc = notDoneDesc;
	}
	public String getNotDoneCode() {
		return notDoneCode;
	}
	public void setNotDoneCode(String notDoneCode) {
		this.notDoneCode = notDoneCode;
	}
	public String getNotDoneCodeSystemOID() {
		return notDoneCodeSystemOID;
	}
	public void setNotDoneCodeSystemOID(String notDoneCodeSystemOID) {
		this.notDoneCodeSystemOID = notDoneCodeSystemOID;
	}
	public ClinicalDataQDM(int patientId,String code, String codeSystem,String elementName,String resultCode,String optionValue,String patientResult,String resultValue) {	
		super();	
		this.code = code;
		this.patientId=patientId;
		this.codeSystem = codeSystem;
		this.elementName = elementName;
		this.resultCode = resultCode;
		this.optionValue = optionValue;	
		this.patientResult=patientResult;
		this.resultValue = resultValue;	
		
	}
	public ClinicalDataQDM(int patientId,String code, String codeSystem,String elementName,String resultCode,String resultCodeSystem,String optionValue,String patientResult,String resultValue,Date recordedDate) {	
		super();	
		this.code = code;
		this.patientId=patientId;
		this.codeSystem = codeSystem;
		this.elementName = elementName;
		this.resultCode = resultCode;
		this.optionValue = optionValue;	
		this.patientResult=patientResult;
		this.resultValue = resultValue;	
		this.recordedDate=recordedDate;
		this.resultCodeSystem=resultCodeSystem;
		
	}
	public ClinicalDataQDM(int patientId,String code, String codeSystem,String elementName,Date recordedDate,String resultCode,String resultValue,String optionValue,String patientResult,String gwid) {	
		super();	
		this.patientId=patientId;
		this.code = code;
		this.codeSystem = codeSystem;
		this.elementName = elementName;
		this.recordedDate=recordedDate;
		this.resultCode = resultCode;
		this.resultValue = resultValue;	
		this.optionValue = optionValue;	
		this.patientResult=patientResult;	
		this.gwid=gwid;
	}
		
	public ClinicalDataQDM(String elementName,String code,String codeSystem,String codeSystemOID,int Status,Date orderedDate,Date performedDate,String notDoneDesc,String notDoneCode,String notDoneCodeSystemOID)
	{
		super();
		this.elementName=elementName;
		this.code=code;
		this.codeSystem=codeSystem;
		this.codeSystemOID=codeSystemOID;
		this.Status=Status;
		this.notDoneDesc=notDoneDesc;
		this.notDoneCode=notDoneCode;
		this.notDoneCodeSystemOID=notDoneCodeSystemOID;
		if(performedDate!=null)
			this.recordedDate=performedDate;
		else if(orderedDate!=null)
			this.recordedDate=orderedDate;
	}
	
	public ClinicalDataQDM(String elementName,String code,String codeSystem,String codeSystemOID,int Status,Date orderedDate,Date performedDate,String notDoneDesc,String notDoneCode,String notDoneCodeSystemOID,String resultCode,String resultCodeSystem)		
	{		
		super();		
		this.elementName=elementName;		
		this.code=code;		
		this.codeSystem=codeSystem;		
		this.codeSystemOID=codeSystemOID;		
		this.Status=Status;		
		this.notDoneDesc=notDoneDesc;		
		this.notDoneCode=notDoneCode;		
		this.notDoneCodeSystemOID=notDoneCodeSystemOID;		
		if(performedDate!=null)		
		this.recordedDate=performedDate;		
		else if(orderedDate!=null)		
		this.recordedDate=orderedDate;		
		this.resultCode=resultCode;		
		this.resultCodeSystem=resultCodeSystem;		
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeSystem() {
		return codeSystem;
	}


	public void setCodeSystem(String codeSystem) {
		this.codeSystem = codeSystem;
	}

	public String getCodeSystemOID() {
		return codeSystemOID;
	}

	public void setCodeSystemOID(String codeSystemOID) {
		this.codeSystemOID = codeSystemOID;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
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

	public Date getRecordedDate() {
		return recordedDate;
	}

	public void setRecordedDate(Date recordedDate) {
		this.recordedDate = recordedDate;
	}


	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultCodeSystem() {
		return resultCodeSystem;
	}

	public void setResultCodeSystem(String resultCodeSystem) {
		this.resultCodeSystem = resultCodeSystem;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	
	public String getPatientResult() {
		return patientResult;
	}

	public void setPatientResult(String patientResult) {
		this.patientResult = patientResult;
	}

}