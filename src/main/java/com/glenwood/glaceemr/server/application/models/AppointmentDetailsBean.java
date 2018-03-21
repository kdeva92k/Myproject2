package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class AppointmentDetailsBean {

	List<AppReferenceValues> schApptStatusList;
	
	List<AppReferenceValues> schApptTypeList;
	
	List<AppReferenceValues> schApptReasonList;

	public List<AppReferenceValues> getSchApptStatusList() {
		return schApptStatusList;
	}

	
	public void setSchApptStatusList(List<AppReferenceValues> schApptStatusList) {
		this.schApptStatusList = schApptStatusList;
	}

	public List<AppReferenceValues> getSchApptTypeList() {
		return schApptTypeList;
	}

	public void setSchApptTypeList(List<AppReferenceValues> schApptTypeList) {
		this.schApptTypeList = schApptTypeList;
	}

	public List<AppReferenceValues> getSchApptReasonList() {
		return schApptReasonList;
	}

	public void setSchApptReasonList(List<AppReferenceValues> schApptReasonList) {
		this.schApptReasonList = schApptReasonList;
	}
	
}
