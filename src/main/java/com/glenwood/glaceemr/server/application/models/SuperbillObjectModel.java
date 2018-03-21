package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class SuperbillObjectModel {

	List<PosTable> posData;
	List<PatientInsDetail> insData;
	List<EmployeeProfile> docData;
	public List<PosTable> getPosData() {
		return posData;
	}
	public void setPosData(List<PosTable> posData) {
		this.posData = posData;
	}
	public List<PatientInsDetail> getInsData() {
		return insData;
	}
	public void setInsData(List<PatientInsDetail> insData) {
		this.insData = insData;
	}
	public List<EmployeeProfile> getDocData() {
		return docData;
	}
	public void setDocData(List<EmployeeProfile> docData) {
		this.docData = docData;
	}
	
	
	
	
	
	
	
}
