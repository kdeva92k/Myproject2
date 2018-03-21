package com.glenwood.glaceemr.server.application.services.labresults;

import java.util.List;

public class ResultDetails {

	String patientId;
	String placedDate;
	String placerGroupNo;
	String status;
	String placerOrderNo;
	String labCompanyId;
	String isReviewed;
	String prelimResultId;
	String accountNo;
	String patientName;
	String labAccessionNo;
	String hl7Id;
	String labCompany;
	String documentId;
	List<UnmappedResults> unmappedResults;
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPlacedDate() {
		return placedDate;
	}
	public void setPlacedDate(String placedDate) {
		this.placedDate = placedDate;
	}
	public String getPlacerGroupNo() {
		return placerGroupNo;
	}
	public void setPlacerGroupNo(String placerGroupNo) {
		this.placerGroupNo = placerGroupNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlacerOrderNo() {
		return placerOrderNo;
	}
	public void setPlacerOrderNo(String placerOrderNo) {
		this.placerOrderNo = placerOrderNo;
	}
	public String getLabCompanyId() {
		return labCompanyId;
	}
	public void setLabCompanyId(String labCompanyId) {
		this.labCompanyId = labCompanyId;
	}
	public String getIsReviewed() {
		return isReviewed;
	}
	public void setIsReviewed(String isReviewed) {
		this.isReviewed = isReviewed;
	}
	public String getPrelimResultId() {
		return prelimResultId;
	}
	public void setPrelimResultId(String prelimResultId) {
		this.prelimResultId = prelimResultId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getLabAccessionNo() {
		return labAccessionNo;
	}
	public void setLabAccessionNo(String labAccessionNo) {
		this.labAccessionNo = labAccessionNo;
	}
	public String getHl7Id() {
		return hl7Id;
	}
	public void setHl7Id(String hl7Id) {
		this.hl7Id = hl7Id;
	}
	public String getLabCompany() {
		return labCompany;
	}
	public void setLabCompany(String labCompany) {
		this.labCompany = labCompany;
	}
	public List<UnmappedResults> getUnmappedResults() {
		return unmappedResults;
	}
	public void setUnmappedResults(List<UnmappedResults> unmappedResults) {
		this.unmappedResults = unmappedResults;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
}
