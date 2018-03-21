package com.glenwood.glaceemr.server.application.services.employee;

/**
 * Employee data bean
 * @author software
 *
 */
public class EmployeeDataBean {
	
	Integer empId;
	Integer empLoginId;
	String empFullname;
	String empLastName;
	String empFirstName;
	String empMiddleName;
	String empCredentials;
	String empAddress;
	String empState;
	String empCity;
	String empZip;
	String empPhNum;
	String empMailId;
	String empSpecialty;
	
	public EmployeeDataBean(Integer empId, String empFullname){
		this.empId = empId;
		this.empFullname = empFullname;
	}
	
	public EmployeeDataBean(Integer empId, Integer empLoginId,
			String empLastName, String empFirstName, String empMiddleName,
			String empCredentials, String empAddress, String empState,
			String empCity, String empZip, String empPhNum, String empMailId) {
		this.empId = empId;
		this.empLoginId = empLoginId;
		this.empLastName = empLastName;
		this.empFirstName = empFirstName;
		this.empMiddleName = empMiddleName;
		this.empCredentials = empCredentials;
		this.empAddress = empAddress;
		this.empState = empState;
		this.empCity = empCity;
		this.empZip = empZip;
		this.empPhNum = empPhNum;
		this.empMailId = empMailId;
	}
	public EmployeeDataBean(Integer empId, Integer empLoginId,
			String empLastName, String empFirstName, String empMiddleName,
			String empCredentials, String empAddress, String empState,
			String empCity, String empZip, String empPhNum, 
			String empMailId, String empSpecialty) {
		this.empId = empId;
		this.empLoginId = empLoginId;
		this.empLastName = empLastName;
		this.empFirstName = empFirstName;
		this.empMiddleName = empMiddleName;
		this.empCredentials = empCredentials;
		this.empAddress = empAddress;
		this.empState = empState;
		this.empCity = empCity;
		this.empZip = empZip;
		this.empPhNum = empPhNum;
		this.empMailId = empMailId;
		this.empSpecialty = empSpecialty;
	}
	public EmployeeDataBean(Integer empId, Integer empLoginId,
			String empFullname, String empAddress, String empState,
			String empCity, String empZip, String empPhNum,String empMailId) {
		this.empId = empId;
		this.empLoginId = empLoginId;
		this.empFullname = empFullname;
		this.empAddress = empAddress;
		this.empState = empState;
		this.empCity = empCity;
		this.empZip = empZip;
		this.empPhNum = empPhNum;
		this.empMailId = empMailId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getEmpLoginId() {
		return empLoginId;
	}
	public void setEmpLoginId(Integer empLoginId) {
		this.empLoginId = empLoginId;
	}
	public String getEmpFullname() {
		return empFullname;
	}
	public void setEmpFullname(String empFullname) {
		this.empFullname = empFullname;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public String getEmpMiddleName() {
		return empMiddleName;
	}
	public void setEmpMiddleName(String empMiddleName) {
		this.empMiddleName = empMiddleName;
	}
	public String getEmpCredentials() {
		return empCredentials;
	}
	public void setEmpCredentials(String empCredentials) {
		this.empCredentials = empCredentials;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpState() {
		return empState;
	}
	public void setEmpState(String empState) {
		this.empState = empState;
	}
	public String getEmpCity() {
		return empCity;
	}
	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}
	public String getEmpZip() {
		return empZip;
	}
	public void setEmpZip(String empZip) {
		this.empZip = empZip;
	}
	public String getEmpPhNum() {
		return empPhNum;
	}
	public void setEmpPhNum(String empPhNum) {
		this.empPhNum = empPhNum;
	}
	public String getEmpMailId() {
		return empMailId;
	}
	public void setEmpMailId(String empMailId) {
		this.empMailId = empMailId;
	}
}
