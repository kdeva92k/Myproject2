package com.glenwood.glaceemr.server.application.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "letter_header_emp")
public class LetterHeaderEmp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="letter_header_emp_id")
	private Integer letterHeaderEmpId;

	@Column(name="letter_header_emp_map_id")
	private Integer letterHeaderEmpMapId;

	@Column(name="letter_header_emp_empid")
	private Integer letterHeaderEmpEmpid;

	@Column(name="letter_header_emp_order")
	private Integer letterHeaderEmpOrder;

	@Column(name="letter_header_emp_variant")
	private Integer letterHeaderEmpVariant;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="letter_header_emp_empid", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfile;	
	
	public Integer getLetterHeaderEmpId() {
		return letterHeaderEmpId;
	}

	public void setLetterHeaderEmpId(Integer letterHeaderEmpId) {
		this.letterHeaderEmpId = letterHeaderEmpId;
	}

	public Integer getLetterHeaderEmpMapId() {
		return letterHeaderEmpMapId;
	}

	public void setLetterHeaderEmpMapId(Integer letterHeaderEmpMapId) {
		this.letterHeaderEmpMapId = letterHeaderEmpMapId;
	}

	public Integer getLetterHeaderEmpEmpid() {
		return letterHeaderEmpEmpid;
	}

	public void setLetterHeaderEmpEmpid(Integer letterHeaderEmpEmpid) {
		this.letterHeaderEmpEmpid = letterHeaderEmpEmpid;
	}

	public Integer getLetterHeaderEmpOrder() {
		return letterHeaderEmpOrder;
	}

	public void setLetterHeaderEmpOrder(Integer letterHeaderEmpOrder) {
		this.letterHeaderEmpOrder = letterHeaderEmpOrder;
	}

	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}

	public Integer getLetterHeaderEmpVariant() {
		return letterHeaderEmpVariant;
	}

	public void setLetterHeaderEmpVariant(Integer letterHeaderEmpVariant) {
		this.letterHeaderEmpVariant = letterHeaderEmpVariant;
	}
	
}