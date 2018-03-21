package com.glenwood.glaceemr.server.application.services.chart.print.genericheader;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.LetterHeaderEmp;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;

public class LetterHeaderEmployeeBean {

	public List<EmployeeDataBean> empList;
	
	public List<LetterHeaderEmp> savedEmpList;

	public List<EmployeeDataBean> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeDataBean> empList) {
		this.empList = empList;
	}

	public List<LetterHeaderEmp> getSavedEmpList() {
		return savedEmpList;
	}

	public void setSavedEmpList(List<LetterHeaderEmp> savedEmpList) {
		this.savedEmpList = savedEmpList;
	}
	
	
}
