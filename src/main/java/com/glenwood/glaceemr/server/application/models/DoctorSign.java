package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "doctorsign")
public class DoctorSign implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="loginid")
	private int loginid;

	@Column(name="filename")
	private String filename;

	@Column(name="signatureid")
	private Integer signatureid;

	@Column(name="signaccess")
	private Boolean signaccess;
	
	@Column(name="signfooter")
	private String signfooter;
	
	@Column(name="signstyle")
	private String signstyle;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "loginid", referencedColumnName = "login_users_id", insertable = false, updatable = false)
	public LoginUsers empLoginName;
	
	
	public LoginUsers getEmpLoginID() {
		return empLoginName;
	}

	public void setEmpLoginID(LoginUsers empLoginName) {
		this.empLoginName = empLoginName;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "loginid", referencedColumnName = "emp_profile_loginid", insertable = false, updatable = false)
	public EmployeeProfile empLoginId;
	
	public EmployeeProfile getEmpLoginId() {
		return empLoginId;
	}

	public void setEmpLoginId(EmployeeProfile empLoginId) {
		this.empLoginId = empLoginId;
	}

	public int getLoginid() {
		return loginid;
	}

	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getSignatureid() {
		return signatureid;
	}

	public void setSignatureid(Integer signatureid) {
		this.signatureid = signatureid;
	}

	public Boolean getSignaccess() {
		return signaccess;
	}

	public void setSignaccess(Boolean signaccess) {
		this.signaccess = signaccess;
	}
	
	public String getSignfooter() {
		return signfooter;
	}

	public void setSignfooter(String signfooter) {
		this.signfooter = signfooter;
	}

	public String getSignstyle() {
		return signstyle;
	}

	public void setSignstyle(String signstyle) {
		this.signstyle = signstyle;
	}
}