package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "direct_mail_permit")
public class DirectMailPermit {
	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "direct_mail_permit_direct_mail_permit_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="direct_mail_permit_id")
	private Integer id;
	
	@Column(name="direct_mail_permit_docid")
	private String docId;
	
	@Column(name="direct_mail_permit_empid")
	private String empId;
	
	@Column(name="isactive")
	private Boolean isactive;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "direct_mail_permit_docid", referencedColumnName = "emp_profile_doctorid", insertable = false, updatable = false)
	private EmployeeProfile empProfile; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
}
