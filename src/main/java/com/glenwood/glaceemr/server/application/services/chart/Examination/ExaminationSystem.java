package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.util.LinkedHashMap;


public class ExaminationSystem{
	private int systemId;
	private String systemName;
	private String systemEandMType;
	private String systemDeferredGWID;
	private String systemChaperoneGWID;
	

	private LinkedHashMap<Integer, ExaminationElementGroupBean> examinationElementGroups;

	
	public int getSystemId() {
		return systemId;
	}
	
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	
	public String getSystemName() {
		return systemName;
	}
	
	public String getSystemDeferredGWID() {
		return systemDeferredGWID;
	}
	
	public String getChaperoneGWID(){
		return systemChaperoneGWID;
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
	
	public void setSystemDeferredGWID(String systemDeferredGWID) {
		this.systemDeferredGWID = systemDeferredGWID;
	}

	public void setChaperoneGWID(String systemChaperoneGWID) {
		this.systemChaperoneGWID = systemChaperoneGWID;
	}

	public String getSystemChaperoneGWID() {
		return systemChaperoneGWID;
	}

	public void setSystemChaperoneGWID(String systemChaperoneGWID) {
		this.systemChaperoneGWID = systemChaperoneGWID;
	}

	public LinkedHashMap<Integer, ExaminationElementGroupBean> getExaminationElementGroups() {
		return examinationElementGroups;
	}

	public void setExaminationElementGroups(
			LinkedHashMap<Integer, ExaminationElementGroupBean> examinationElementGroups) {
		this.examinationElementGroups = examinationElementGroups;
	}


}