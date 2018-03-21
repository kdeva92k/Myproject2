package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "account_type")
public class AccountType {

	@Id
	@Column(name="account_type_id")
	private Integer accId;
	
	@Column(name="account_type_type")
	private String accType;

	

	@Column(name="account_type_description")
	private String accTypeDesc;
	
	@OneToMany(mappedBy="ptAccType")
	@JsonManagedReference
	List<PatientRegistration> patients;

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public List<PatientRegistration> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientRegistration> patients) {
		this.patients = patients;
	}

	public String getAccTypeDesc() {
		return accTypeDesc;
	}

	public void setAccTypeDesc(String accTypeDesc) {
		this.accTypeDesc = accTypeDesc;
	}


}
