package com.glenwood.glaceemr.server.application.services.chart.print;

import java.util.List;

import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.services.patient.PatientDataBean;
import com.glenwood.glaceemr.server.application.services.pos.DefaultPracticeBean;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;

public class GenericPrintBean {
	
	private PatientDataBean patientBean;
	private List<EmployeeDataBean> employeeBean;
	private List<PosDataBean> posBean;
	private DefaultPracticeBean practiceBean; 
	
	public GenericPrintBean(PatientDataBean patientBean,
			List<EmployeeDataBean> employeeBean, List<PosDataBean> posBean,
			DefaultPracticeBean practiceBean) {
		super();
		this.patientBean = patientBean;
		this.employeeBean = employeeBean;
		this.posBean = posBean;
		this.practiceBean = practiceBean;
	}
	public PatientDataBean getPatientBean() {
		return patientBean;
	}
	public void setPatientBean(PatientDataBean patientBean) {
		this.patientBean = patientBean;
	}
	public List<EmployeeDataBean> getEmployeeBean() {
		return employeeBean;
	}
	public void setEmployeeBean(List<EmployeeDataBean> employeeBean) {
		this.employeeBean = employeeBean;
	}
	public List<PosDataBean> getPosBean() {
		return posBean;
	}
	public void setPosBean(List<PosDataBean> posBean) {
		this.posBean = posBean;
	}
	public DefaultPracticeBean getPracticeBean() {
		return practiceBean;
	}
	public void setPracticeBean(DefaultPracticeBean practiceBean) {
		this.practiceBean = practiceBean;
	}
	
}
