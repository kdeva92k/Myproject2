package com.glenwood.glaceemr.server.application.services.chart.RefillRequest;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox;

public class PatientRefillDataBean {

	String appt;
	String lastVisitPOS;
	PatientRegistration patdata;
	List<SSMessageInbox> pedingdata;
	public PatientRefillDataBean(String appt, String lastVisitPOS,PatientRegistration patdata,List<SSMessageInbox> pedingdata) {
		// TODO Auto-generated constructor stub
		this.appt=appt;
		this.lastVisitPOS=lastVisitPOS;
		this.patdata=patdata;
		this.pedingdata=pedingdata;
	}
	public String getAppt() {
		return appt;
	}
	public void setAppt(String appt) {
		this.appt = appt;
	}
	public String getLastVisitPOS() {
		return lastVisitPOS;
	}
	public void setLastVisitPOS(String lastVisitPOS) {
		this.lastVisitPOS = lastVisitPOS;
	}
	public PatientRegistration getPatdata() {
		return patdata;
	}
	public void setPatdata(PatientRegistration patdata) {
		this.patdata = patdata;
	}
	public List<SSMessageInbox> getPedingdata() {
		return pedingdata;
	}
	public void setPedingdata(List<SSMessageInbox> pedingdata) {
		this.pedingdata = pedingdata;
	}
		
}
