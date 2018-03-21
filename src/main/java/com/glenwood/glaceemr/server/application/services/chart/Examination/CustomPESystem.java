package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.util.ArrayList;
import java.util.List;

public class CustomPESystem {
	
	private int systemId;
	private String systemName;
	private String systemEandMType;
	private String systemDeferredGWID;
	private String systemChaperOne;
	private Integer systemOrderBy;
	private String isDeferredChecked;
	private String deferredValue;
	private String docStatus;
	
	List<ExaminationElement> peElements=new ArrayList<ExaminationElement>();
	
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemEandMType() {
		return systemEandMType;
	}
	public void setSystemEandMType(String systemEandMType) {
		this.systemEandMType = systemEandMType;
	}
	public String getSystemDeferredGWID() {
		return systemDeferredGWID;
	}
	public void setSystemDeferredGWID(String systemDeferredGWID) {
		this.systemDeferredGWID = systemDeferredGWID;
	}
	public String getSystemChaperOne() {
		return systemChaperOne;
	}
	public void setSystemChaperOne(String systemChaperOne) {
		this.systemChaperOne = systemChaperOne;
	}
	public Integer getSystemOrderBy() {
		return systemOrderBy;
	}
	public void setSystemOrderBy(Integer systemOrderBy) {
		this.systemOrderBy = systemOrderBy;
	}
	public List<ExaminationElement> getPeElements() {
		return peElements;
	}
	public void setPeElements(List<ExaminationElement> peElements) {
		this.peElements = peElements;
	}
	public String getIsDeferredChecked() {
		return isDeferredChecked;
	}
	public void setIsDeferredChecked(String isDeferredChecked) {
		this.isDeferredChecked = isDeferredChecked;
	}
	public String getDeferredValue() {
		return deferredValue;
	}
	public void setDeferredValue(String deferredValue) {
		this.deferredValue = deferredValue;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
}
