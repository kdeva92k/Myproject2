package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalMessageThreadBean {

	Integer patientId;
	
	List<PortalMessage> messageThread;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public List<PortalMessage> getMessageThread() {
		return messageThread;
	}

	public void setMessageThread(List<PortalMessage> messageThread) {
		this.messageThread = messageThread;
	}
		
}
