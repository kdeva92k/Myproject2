package com.glenwood.glaceemr.server.application.models;

public class PortalPatientServiceDoctor {

	int serviceDoctorId;
		
	String serviceDoctorFirstName;
	
	String serviceDoctorLastName;
	
	String serviceDoctorFullName;

	public int getServiceDoctorId() {
		return serviceDoctorId;
	}

	public void setServiceDoctorId(int serviceDoctorId) {
		this.serviceDoctorId = serviceDoctorId;
	}

	public String getServiceDoctorFirstName() {
		return serviceDoctorFirstName;
	}

	public void setServiceDoctorFirstName(String serviceDoctorFirstName) {
		this.serviceDoctorFirstName = serviceDoctorFirstName;
	}

	public String getServiceDoctorLastName() {
		return serviceDoctorLastName;
	}

	public void setServiceDoctorLastName(String serviceDoctorLastName) {
		this.serviceDoctorLastName = serviceDoctorLastName;
	}

	public String getServiceDoctorFullName() {
		return serviceDoctorFullName;
	}

	public void setServiceDoctorFullName(String serviceDoctorFullName) {
		this.serviceDoctorFullName = serviceDoctorFullName;
	}
	
}
