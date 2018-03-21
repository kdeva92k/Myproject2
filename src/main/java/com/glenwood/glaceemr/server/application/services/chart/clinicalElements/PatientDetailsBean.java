package com.glenwood.glaceemr.server.application.services.chart.clinicalElements;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS)

public class PatientDetailsBean {
	
	Short patientSex=0;
	Date patDOB=null;
	Integer ageinDay=0;
	
	public Short getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(Short patientSex) {
		this.patientSex = patientSex;
	}
	public Date getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(Date patDOB) {
		this.patDOB = patDOB;
	}
	public Integer getAgeinDay() {
		return ageinDay;
	}
	public void setAgeinDay(Integer ageinDay) {
		this.ageinDay = ageinDay;
	}
	
	
}
