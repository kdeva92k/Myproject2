package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

public class PortalPlanOfCareBean {
	
	Integer encounterId;

	Date encounterDate;
	
	String encounterType;
	
	Integer encounterServiceDoctorId;
	
	String encouterServiceDoctor;

	public PortalPlanOfCareBean(Integer encounterId, Date encounterDate,
			String encounterType, Integer encounterServiceDoctorId,
			String encouterServiceDoctor) {
		super();
		if(encounterId!=null)
			this.encounterId = encounterId;
		if(encounterDate!=null)
			this.encounterDate = encounterDate;
		if(encounterType!=null)
			this.encounterType = encounterType;
		if(encounterServiceDoctorId!=null)
			this.encounterServiceDoctorId = encounterServiceDoctorId;
		if(encouterServiceDoctor!=null)
			this.encouterServiceDoctor = encouterServiceDoctor;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Date getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(Date encounterDate) {
		this.encounterDate = encounterDate;
	}

	public String getEncounterType() {
		return encounterType;
	}

	public void setEncounterType(String encounterType) {
		this.encounterType = encounterType;
	}

	public Integer getEncounterServiceDoctorId() {
		return encounterServiceDoctorId;
	}

	public void setEncounterServiceDoctorId(Integer encounterServiceDoctorId) {
		this.encounterServiceDoctorId = encounterServiceDoctorId;
	}

	public String getEncouterServiceDoctor() {
		return encouterServiceDoctor;
	}

	public void setEncouterServiceDoctor(String encouterServiceDoctor) {
		this.encouterServiceDoctor = encouterServiceDoctor;
	}
	

}
