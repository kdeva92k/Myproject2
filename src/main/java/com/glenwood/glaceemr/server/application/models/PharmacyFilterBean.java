package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PharmacyFilterBean {
	
	String pharmacyName;
	
	String pharmacyNCPDPID;
	
	String pharmacyStoreNo;
	
	String pharmacyAddress;
	
	String pharmacyCity;
	
	String pharmacyState;
	
	String pharmacyZip;
	
	String pharamcyFax;
	
	String pharmacyPhoneNo;
	
	Boolean pharmacyIs24Hours;
	
	Boolean pharmacyIsECPS;
	
	String pharmacyGroup;
	
	Integer pageOffset;
	
	Integer pageIndex;
	
	long totalPharamaciesCount;
	
	List<PharmDetails> pharmacyList;

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public String getPharmacyNCPDPID() {
		return pharmacyNCPDPID;
	}

	public void setPharmacyNCPDPID(String pharmacyNCPDPID) {
		this.pharmacyNCPDPID = pharmacyNCPDPID;
	}

	public String getPharmacyStoreNo() {
		return pharmacyStoreNo;
	}

	public void setPharmacyStoreNo(String pharmacyStoreNo) {
		this.pharmacyStoreNo = pharmacyStoreNo;
	}

	public String getPharmacyAddress() {
		return pharmacyAddress;
	}

	public void setPharmacyAddress(String pharmacyAddress) {
		this.pharmacyAddress = pharmacyAddress;
	}

	public String getPharmacyCity() {
		return pharmacyCity;
	}

	public void setPharmacyCity(String pharmacyCity) {
		this.pharmacyCity = pharmacyCity;
	}

	public String getPharmacyState() {
		return pharmacyState;
	}

	public void setPharmacyState(String pharmacyState) {
		this.pharmacyState = pharmacyState;
	}

	public String getPharmacyZip() {
		return pharmacyZip;
	}

	public String getPharamcyFax() {
		return pharamcyFax;
	}

	public void setPharamcyFax(String pharamcyFax) {
		this.pharamcyFax = pharamcyFax;
	}

	public void setPharmacyZip(String pharmacyZip) {
		this.pharmacyZip = pharmacyZip;
	}

	public String getPharmacyPhoneNo() {
		return pharmacyPhoneNo;
	}

	public void setPharmacyPhoneNo(String pharmacyPhoneNo) {
		this.pharmacyPhoneNo = pharmacyPhoneNo;
	}

	public Boolean getPharmacyIs24Hours() {
		return pharmacyIs24Hours;
	}

	public void setPharmacyIs24Hours(Boolean pharmacyIs24Hours) {
		this.pharmacyIs24Hours = pharmacyIs24Hours;
	}

	public Boolean getPharmacyIsECPS() {
		return pharmacyIsECPS;
	}

	public void setPharmacyIsECPS(Boolean pharmacyIsECPS) {
		this.pharmacyIsECPS = pharmacyIsECPS;
	}

	public String getPharmacyGroup() {
		return pharmacyGroup;
	}

	public void setPharmacyGroup(String pharmacyGroup) {
		this.pharmacyGroup = pharmacyGroup;
	}

	public Integer getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<PharmDetails> getPharmacyList() {
		return pharmacyList;
	}

	public void setPharmacyList(List<PharmDetails> pharmacyList) {
		this.pharmacyList = pharmacyList;
	}

	public long getTotalPharamaciesCount() {
		return totalPharamaciesCount;
	}

	public void setTotalPharamaciesCount(long totalPharamaciesCount) {
		this.totalPharamaciesCount = totalPharamaciesCount;
	}
	
}
