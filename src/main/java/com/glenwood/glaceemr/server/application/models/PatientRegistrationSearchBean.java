package com.glenwood.glaceemr.server.application.models;

public class PatientRegistrationSearchBean {
	 long totalcount;
	 Integer patientRegistrationId;
	 String patientRegistrationLastName;
	 String patientRegistrationFirstName;
	 String patientRegistrationAccountno;
	 Integer patientRegistrationPreferredContact;
	 
	 public PatientRegistrationSearchBean(Integer patientRegistrationId,String patientRegistrationLastName,
				String patientRegistrationFirstName,String patientRegistrationAccountno,
				Integer patientRegistrationPreferredContact){
		super();
		this.patientRegistrationId = patientRegistrationId;
		this.patientRegistrationLastName = patientRegistrationLastName;
		this.patientRegistrationFirstName = patientRegistrationFirstName;
		this.patientRegistrationAccountno = patientRegistrationAccountno;
		
		if(patientRegistrationPreferredContact != null)
			this.patientRegistrationPreferredContact = patientRegistrationPreferredContact;
	 }
	 
	public long getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(long totalcount) {
		this.totalcount = totalcount;
	}
	public Integer getPatientRegistrationId() {
		return patientRegistrationId;
	}
	public void setPatientRegistrationId(Integer patientRegistrationId) {
		this.patientRegistrationId = patientRegistrationId;
	}
	public String getPatientRegistrationLastName() {
		return patientRegistrationLastName;
	}
	public void setPatientRegistrationLastName(String patientRegistrationLastName) {
		this.patientRegistrationLastName = patientRegistrationLastName;
	}
	public String getPatientRegistrationFirstName() {
		return patientRegistrationFirstName;
	}
	public void setPatientRegistrationFirstName(String patientRegistrationFirstName) {
		this.patientRegistrationFirstName = patientRegistrationFirstName;
	}
	public String getPatientRegistrationAccountno() {
		return patientRegistrationAccountno;
	}
	public void setPatientRegistrationAccountno(String patientRegistrationAccountno) {
		this.patientRegistrationAccountno = patientRegistrationAccountno;
	}
	public Integer getPatientRegistrationPreferredContact() {
		return patientRegistrationPreferredContact;
	}
	public void setPatientRegistrationPreferredContact(Integer patientRegistrationPreferredContact) {
		this.patientRegistrationPreferredContact = patientRegistrationPreferredContact;
	}
}
