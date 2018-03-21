package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "InsuranceMasterTable")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Insurance  {

	
	  @Id
	  @Column(name="InsuranceMasterId")
	  private Integer insuranceMasterId;
	  
	  @Column(name="InuranceName")
	  private String inuranceName;
	
	  @Column(name="InsuranceAddress")
	  private String insuranceAddress;
	  
	  @Column(name="PayorID")
	  private String payorID;
	  
	  
	  @Column(name="isActive")
	  private boolean isActive;
	  /* Need a check */
	  /*
	   * normally working fine when try to update an objec then it asks for json back reference for
	   * every jsonmanagedreference
	   */
	  	@OneToOne
		@JsonBackReference
	    PatientInsurance patientInsurance;
	  
		public Integer getInsuranceMasterId() {
			return insuranceMasterId;
		}

		public void setInsuranceMasterId(Integer insuranceMasterId) {
			this.insuranceMasterId = insuranceMasterId;
		}

		public String getInuranceName() {
			return inuranceName;
		}

		public void setInuranceName(String inuranceName) {
			this.inuranceName = inuranceName;
		}

		public String getInsuranceAddress() {
			return insuranceAddress;
		}

		public void setInsuranceAddress(String insuranceAddress) {
			this.insuranceAddress = insuranceAddress;
		}

		public String getPayorID() {
			return payorID;
		}

		public void setPayorID(String payorID) {
			this.payorID = payorID;
		}

		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

	    
} 
