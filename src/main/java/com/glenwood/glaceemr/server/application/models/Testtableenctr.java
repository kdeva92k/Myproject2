package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;



@Entity
@Table(name = "EncounterTable")
public class Testtableenctr {
	
	
	@Id
	@Column(name="encounterId")
	private Integer encounterId;
	
	
	@Column(name="PatientId")
	private Integer patientId;
	
	
	@Column(name="encounterReason")
	private String encounterReason;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="encounterDate")
	private Timestamp encounterDate;
	
	
	@Column(name="isActive")
	private boolean isActive;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="patientId", referencedColumnName="patientId", insertable=false, updatable=false)
	@JsonBackReference
	TesttablePtn patientTable;
	
	public Integer getEncounterId() {
		return encounterId;
	}


	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}


	public Integer getPatientId() {
		return patientId;
	}


	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}


	public String getEncounterReason() {
		return encounterReason;
	}


	public void setEncounterReason(String encounterReason) {
		this.encounterReason = encounterReason;
	}


	public Date getEncounterDate() {
		return encounterDate;
	}


	public void setEncounterDate(Timestamp encounterDate) {
		this.encounterDate = encounterDate;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public TesttablePtn getPatientTable() {
		return patientTable;
	}


	public void setPatientTable(TesttablePtn patientTable) {
		this.patientTable = patientTable;
	}


	

}
