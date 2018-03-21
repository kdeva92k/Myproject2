package com.glenwood.glaceemr.server.application.services.chart.charges;

import java.util.ArrayList;
import java.util.List;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PosTable;

public class ChargesPageBasicDetailsBean {
	
	List<PosTable> posDetails=new ArrayList<PosTable>();
	List<EmployeeProfile> employeeDetails=new ArrayList<EmployeeProfile>();
	List<PatientInsDetail> insDetails=new ArrayList<PatientInsDetail>();
	List<ReferringDoctor> referring_doctorReferringDr=new ArrayList<ReferringDoctor>();
	public List<PosTable> getPosDetails() {
		return posDetails;
	}
	public void setPosDetails(List<PosTable> posDetails) {
		this.posDetails = posDetails;
	}
	public List<EmployeeProfile> getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(List<EmployeeProfile> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	public List<PatientInsDetail> getInsDetails() {
		return insDetails;
	}
	public void setInsDetails(List<PatientInsDetail> insDetails) {
		this.insDetails = insDetails;
	}
	public List<ReferringDoctor> getreferring_doctorReferringDr() {
		return referring_doctorReferringDr;
	}
	public void setreferring_doctorReferringDr(List<ReferringDoctor> referring_doctorReferringDr) {
		this.referring_doctorReferringDr = referring_doctorReferringDr;
	}
}
