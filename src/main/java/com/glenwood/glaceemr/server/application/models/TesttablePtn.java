package com.glenwood.glaceemr.server.application.models;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;


@Entity
@Table(name = "PatientTable")
public class TesttablePtn  {

	@Id
	@Column(name="patientId")
	private Integer patientId;

	@Column(name="PatientLName")
	private String patientLName;

	@Column(name="PatientFName")
	private String patientFName;

	@Column(name="patientGender")
	private Integer patientGender;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="patientDob")
	private Timestamp patientDob;

	@Column(name="isActive")
	private boolean isActive;

	 
	@OneToMany(cascade=CascadeType.ALL, mappedBy="patientTable")
	@JsonManagedReference
	Set<Testtableenctr> encounterTable;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="patientTable")
	@JsonManagedReference
	Set<TesttablePntIns> patientInsuranceTable;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="patientTable")
	@JsonManagedReference
	Set<TesttableAdrs> pAddressTable;

	public Set<TesttableAdrs> getpAddressTable() {
		return pAddressTable;
	}

	public void setpAddressTable(Set<TesttableAdrs> pAddressTable) {
		this.pAddressTable = pAddressTable;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientLName() {
		return patientLName;
	}

	public void setPatientLName(String patientLName) {
		this.patientLName = patientLName;
	}

	public String getPatientFName() {
		return patientFName;
	}

	public void setPatientFName(String patientFName) {
		this.patientFName = patientFName;
	}

	public Integer getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(Integer patientGender) {
		this.patientGender = patientGender;
	}

	public Date getPatientDob() {
		return patientDob;
	}

	
	/* Need a check */
	
	public void setPatientDob(String  patientDob) {
		Timestamp toTimeStamp = Timestamp.valueOf(patientDob+" 00:00:00");
		this.patientDob = toTimeStamp;
	}

	public boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Testtableenctr> getEncounterTable() {
		return encounterTable;
	}

	public void setEncounterTable(Set<Testtableenctr> encounterTable) {
		this.encounterTable = encounterTable;
	}

	public Set<TesttablePntIns> getPatientInsuranceTable() {
		return patientInsuranceTable;
	}

	public void setPatientInsuranceTable(
			Set<TesttablePntIns> patientInsuranceTable) {
		this.patientInsuranceTable = patientInsuranceTable;
	}


}
