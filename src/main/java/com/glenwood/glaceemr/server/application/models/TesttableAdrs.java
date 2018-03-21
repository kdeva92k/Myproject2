package com.glenwood.glaceemr.server.application.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "AddressTable")
public class TesttableAdrs {
	
	@Id
	@Column(name="addressId")
	private Integer addressId;
    
	@Column(name="address")
	private String address;
	
	@Column(name="patientId")
	private Integer patientId;
	
	@Column(name="isActive")
	private boolean isActive;
	
	@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	@JoinColumn(name="patientId", referencedColumnName="patientId", insertable=false, updatable=false)
	@JsonBackReference
	TesttablePtn patientTable;
	

	public TesttablePtn getPatientTable() {
		return patientTable;
	}

	public void setPatientTable(TesttablePtn patientTable) {
		this.patientTable = patientTable;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
}
