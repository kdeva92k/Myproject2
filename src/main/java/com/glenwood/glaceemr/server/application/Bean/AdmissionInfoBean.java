package com.glenwood.glaceemr.server.application.Bean;

import java.math.BigInteger;
import java.sql.Date;

public class AdmissionInfoBean {
	
	private String patientRegistrationLastName;
	private String patientRegistrationMidInitial;
	private String patientRegistrationFirstName;
	private Date serviceDetailDos;
	private BigInteger serviceDetailId;
	private BigInteger serviceDetailPatientid;
	private String serviceDetailModifier1;
	private String serviceDetailModifier2;
	private Integer serviceDetailSdoctorid;
	private Integer serviceDetailBdoctorid;
	private Integer serviceDetailRdoctorid;
	private Integer serviceDetailUnit;
	private Double serviceDetailCharges;
	private String serviceDetailDx1;	
	private String serviceDetailDx2;
	private String serviceDetailDx3;
	private String serviceDetailDx4;
	private Integer posTablePlaceId;
	private String posTableFacilityComments;
	private String cptCptcode;
	private String empProfileFullname;
	private String serviceDoctor;
	private String billingDoctor;
	private String ReferringDoctor;
	private Integer admissionPatientId;
	private Date admissionDate;
	private String admissionPOS;
	private Integer admissionId;
	private Integer admissionDoctorId;
	private Date admissionDischargeDate;
	private Date patientRegistrationDOB;
	private String patientRegistrationAccNo;
	private String admissionAdmittedTo;
	private Date serviceDetailDop;
	private Integer posId;
	
	public String getPatientRegistrationLastName() {
		return patientRegistrationLastName;
	}
	public void setPatientRegistrationLastName(String patientRegistrationLastName) {
		this.patientRegistrationLastName = patientRegistrationLastName;
	}
	public String getPatientRegistrationMidInitial() {
		return patientRegistrationMidInitial;
	}
	public void setPatientRegistrationMidInitial(
			String patientRegistrationMidInitial) {
		this.patientRegistrationMidInitial = patientRegistrationMidInitial;
	}
	public String getPatientRegistrationFirstName() {
		return patientRegistrationFirstName;
	}
	public void setPatientRegistrationFirstName(String patientRegistrationFirstName) {
		this.patientRegistrationFirstName = patientRegistrationFirstName;
	}
	public Date getServiceDetailDos() {
		return serviceDetailDos;
	}
	public void setServiceDetailDos(Date serviceDetailDos) {
		this.serviceDetailDos = serviceDetailDos;
	}
	public BigInteger getServiceDetailId() {
		return serviceDetailId;
	}
	public void setServiceDetailId(BigInteger serviceDetailId) {
		this.serviceDetailId = serviceDetailId;
	}
	public BigInteger getServiceDetailPatientid() {
		return serviceDetailPatientid;
	}
	public void setServiceDetailPatientid(BigInteger serviceDetailPatientid) {
		this.serviceDetailPatientid = serviceDetailPatientid;
	}
	public Integer getPosTablePlaceId() {
		return posTablePlaceId;
	}
	public void setPosTablePlaceId(Integer posTablePlaceId) {
		this.posTablePlaceId = posTablePlaceId;
	}
	public String getPosTableFacilityComments() {
		return posTableFacilityComments;
	}
	public void setPosTableFacilityComments(String posTableFacilityComments) {
		this.posTableFacilityComments = posTableFacilityComments;
	}
	public String getCptCptcode() {
		return cptCptcode;
	}
	public void setCptCptcode(String cptCptcode) {
		this.cptCptcode = cptCptcode;
	}
	public String getEmpProfileFullname() {
		return empProfileFullname;
	}
	public void setEmpProfileFullname(String empProfileFullname) {
		this.empProfileFullname = empProfileFullname;
	}
	public Integer getAdmissionPatientId() {
		return admissionPatientId;
	}
	public void setAdmissionPatientId(Integer admissionPatientId) {
		this.admissionPatientId = admissionPatientId;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	
	public Date getPatientRegistrationDOB() {
		return patientRegistrationDOB;
	}
	public void setPatientRegistrationDOB(Date date) {
		this.patientRegistrationDOB = date;
	}
	public String getPatientRegistrationAccNo() {
		return patientRegistrationAccNo;
	}
	public void setPatientRegistratioAccNo(String patientRegistratioAccNo) {
		this.patientRegistrationAccNo = patientRegistratioAccNo;
	}
	public String getAdmissionPOS() {
		return admissionPOS;
	}
	public void setAdmissionPOS(String admissionPOS) {
		this.admissionPOS = admissionPOS;
	}
	public Integer getAdmissionId() {
		return admissionId;
	}
	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}
	
	public String getServiceDetailModifier1() {
		return serviceDetailModifier1;
	}
	public void setServiceDetailModifier1(String serviceDetailModifier1) {
		this.serviceDetailModifier1 = serviceDetailModifier1;
	}
	public String getServiceDetailModifier2() {
		return serviceDetailModifier2;
	}
	public void setServiceDetailModifier2(String serviceDetailModifier2) {
		this.serviceDetailModifier2 = serviceDetailModifier2;
	}
	public Integer getServiceDetailSdoctorid() {
		return serviceDetailSdoctorid;
	}
	public void setServiceDetailSdoctorid(Integer serviceDetailSdoctorid) {
		this.serviceDetailSdoctorid = serviceDetailSdoctorid;
	}
	public Integer getServiceDetailBdoctorid() {
		return serviceDetailBdoctorid;
	}
	public void setServiceDetailBdoctorid(Integer serviceDetailBdoctorid) {
		this.serviceDetailBdoctorid = serviceDetailBdoctorid;
	}
	public Integer getServiceDetailRdoctorid() {
		return serviceDetailRdoctorid;
	}
	public void setServiceDetailRdoctorid(Integer serviceDetailRdoctorid) {
		this.serviceDetailRdoctorid = serviceDetailRdoctorid;
	}
	public Integer getServiceDetailUnit() {
		return serviceDetailUnit;
	}
	public void setServiceDetailUnit(Integer integer) {
		this.serviceDetailUnit = integer;
	}
	public Double getServiceDetailCharges() {
		return serviceDetailCharges;
	}
	public void setServiceDetailCharges(Double serviceDetailCharges) {
		this.serviceDetailCharges = serviceDetailCharges;
	}
	public String getServiceDetailDx1() {
		return serviceDetailDx1;
	}
	public void setServiceDetailDx1(String serviceDetailDx1) {
		this.serviceDetailDx1 = serviceDetailDx1;
	}
	public String getServiceDetailDx2() {
		return serviceDetailDx2;
	}
	public void setServiceDetailDx2(String serviceDetailDx2) {
		this.serviceDetailDx2 = serviceDetailDx2;
	}
	public String getServiceDetailDx3() {
		return serviceDetailDx3;
	}
	public void setServiceDetailDx3(String serviceDetailDx3) {
		this.serviceDetailDx3 = serviceDetailDx3;
	}
	public String getServiceDetailDx4() {
		return serviceDetailDx4;
	}
	public void setServiceDetailDx4(String serviceDetailDx4) {
		this.serviceDetailDx4 = serviceDetailDx4;
	}
	public void setPatientRegistrationAccNo(String patientRegistrationAccNo) {
		this.patientRegistrationAccNo = patientRegistrationAccNo;
	}
	public Integer getAdmissionDoctorId() {
		return admissionDoctorId;
	}
	public void setAdmissionDoctorId(Integer admissionDoctorId) {
		this.admissionDoctorId = admissionDoctorId;
	}
	public Date getAdmissionDischargeDate() {
		return admissionDischargeDate;
	}
	public void setAdmissionDischargeDate(Date admissionDischargeDate) {
		this.admissionDischargeDate = admissionDischargeDate;
	}
	public String getAdmissionAdmittedTo() {
		return admissionAdmittedTo;
	}
	public void setAdmissionAdmittedTo(String admissionAdmittedTo) {
		this.admissionAdmittedTo = admissionAdmittedTo;
	}
	public Date getServiceDetailDop() {
		return serviceDetailDop;
	}
	public void setServiceDetailDop(Date serviceDetailDop) {
		this.serviceDetailDop = serviceDetailDop;
	}
	public Integer getPosId() {
		return posId;
	}
	public void setPosId(Integer posId) {
		this.posId = posId;
	}
	public String getServiceDoctor() {
		return serviceDoctor;
	}
	public void setServiceDoctor(String serviceDoctor) {
		this.serviceDoctor = serviceDoctor;
	}
	public String getBillingDoctor() {
		return billingDoctor;
	}
	public void setBillingDoctor(String billingDoctor) {
		this.billingDoctor = billingDoctor;
	}
	public String getReferringDoctor() {
		return ReferringDoctor;
	}
	public void setReferringDoctor(String referringDoctor) {
		ReferringDoctor = referringDoctor;
	}
	
}
