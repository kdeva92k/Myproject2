package com.glenwood.glaceemr.server.application.services.patient;

import java.util.List;

import com.glenwood.glaceemr.server.application.services.chart.insurance.InsuranceDataBean;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;

/**
 * Patient details bean 
 * @author software
 *
 */
public class PatientDataBean {
	
	private String patientName;
	private String age;
	private String dos;
	private String gender;
	private String accountId;
	private String phNum;
	private String dob;
	private String mobileNum;
	private String address;	
	private String refPhyName;
	private String serviceReferral;
	private List<InsuranceDataBean> insuranceDetails;
	private List<PosDataBean> posDetails;
	private EmployeeDataBean principalDr;
	private EmployeeDataBean serviceDr;
	private String ethinicity;
	private String race;
	private String prefLang;
	private Integer patientId;
	private Integer encounterId;
	
	public PatientDataBean(String patientName, String age, String dos,
			String gender, String accountId, String phNum, String dob,
			String mobileNum, String address, String refPhyName,
			String serviceReferral, List<InsuranceDataBean> insuranceDetails,
			List<PosDataBean> posDetails, EmployeeDataBean principalDr,
			EmployeeDataBean serviceDr, String ethinicity, String race,
			String prefLang, Integer patientId, Integer encounterId) {
		this.patientName = patientName;
		this.age = age;
		this.dos = dos;
		this.gender = gender;
		this.accountId = accountId;
		this.phNum = phNum;
		this.dob = dob;
		this.mobileNum = mobileNum;
		this.address = address;
		this.refPhyName = refPhyName;
		this.serviceReferral = serviceReferral;
		this.insuranceDetails = insuranceDetails;
		this.posDetails = posDetails;
		this.principalDr = principalDr;
		this.serviceDr = serviceDr;
		this.ethinicity = ethinicity;
		this.race = race;
		this.prefLang = prefLang;
		this.patientId = patientId;
		this.encounterId = encounterId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPhNum() {
		return phNum;
	}
	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRefPhyName() {
		return refPhyName;
	}
	public void setRefPhyName(String refPhyName) {
		this.refPhyName = refPhyName;
	}
	public String getServiceReferral() {
		return serviceReferral;
	}
	public void setServiceReferral(String serviceReferral) {
		this.serviceReferral = serviceReferral;
	}
	public List<InsuranceDataBean> getInsuranceDetails() {
		return insuranceDetails;
	}
	public void setInsuranceDetails(List<InsuranceDataBean> insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}
	public List<PosDataBean> getPosDetails() {
		return posDetails;
	}
	public void setPosDetails(List<PosDataBean> posDetails) {
		this.posDetails = posDetails;
	}
	public EmployeeDataBean getPrincipalDr() {
		return principalDr;
	}
	public void setPrincipalDr(EmployeeDataBean principalDr) {
		this.principalDr = principalDr;
	}
	public EmployeeDataBean getServiceDr() {
		return serviceDr;
	}
	public void setServiceDr(EmployeeDataBean serviceDr) {
		this.serviceDr = serviceDr;
	}
	public String getEthinicity() {
		return ethinicity;
	}
	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getPrefLang() {
		return prefLang;
	}
	public void setPrefLang(String prefLang) {
		this.prefLang = prefLang;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	
}