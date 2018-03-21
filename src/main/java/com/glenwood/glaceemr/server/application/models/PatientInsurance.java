package com.glenwood.glaceemr.server.application.models;


import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "PatientInsuranceTable")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientInsurance {
 
	
	@Id
	@Column(name="InsuranceID")
	private Integer insuranceId;
    
	@Column(name="InsuranceMasterId")
	private Integer insuranceMasterId;

	@Column(name="patientid")
	private Integer patientId;
	
	@Column(name="type")
	private Integer type;
   
	@Column(name="isValid")
	private boolean isValid;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="StartDate")
	private Timestamp startDate;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="EndDate")
	private Timestamp endDate;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="insuranceMasterId", referencedColumnName="insuranceMasterId", insertable=false, updatable=false)
	@JsonManagedReference
    Insurance insuranceMasterTable;

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Integer getInsuranceMasterId() {
		return insuranceMasterId;
	}

	public void setInsuranceMasterId(Integer insuranceMasterId) {
		this.insuranceMasterId = insuranceMasterId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Insurance getInsuranceMasterTable() {
		return insuranceMasterTable;
	}

	public void setInsuranceMasterTable(Insurance insuranceMasterTable) {
		this.insuranceMasterTable = insuranceMasterTable;
	}
	
}
