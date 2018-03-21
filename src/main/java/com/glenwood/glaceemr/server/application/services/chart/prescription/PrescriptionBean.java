package com.glenwood.glaceemr.server.application.services.chart.prescription;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.CurrentMedication;
import com.glenwood.glaceemr.server.application.models.Prescription;


public class PrescriptionBean {
 
	 
	public PrescriptionBean(List<Prescription> presclist,
			List<CurrentMedication> currmed) {
		this.presclist = presclist;
		this.currmed = currmed;
	}
	List<Prescription> presclist;
	List<CurrentMedication> currmed;
	
	public List<Prescription> getPresclist() {
		return presclist;
	}
	public void setPresclist(List<Prescription> presclist) {
		this.presclist = presclist;
	}
	public List<CurrentMedication> getCurrmed() {
		return currmed;
	}
	public void setCurrmed(List<CurrentMedication> currmed) {
		this.currmed = currmed;
	}
	
}
