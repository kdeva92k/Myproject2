package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "patient_representatives_profile")

public class PatientRepresentativesProfile {
	@Id
	@Column(name="patient_representatives_profile_id")
	private Integer id;
	
	
	@Column(name="patient_representatives_profile_lname")
	private String lname;
	
	
	@Column(name="patient_representatives_profile_fname")
	private String fname;
	
	
	@Column(name="patient_representatives_profile_mname")
	private String mname;
	
	
	
	@Column(name="patient_representatives_profile_gender")
	private Integer gender;
	
	
	@Column(name="patient_representatives_profile_dob")
	private Date dob;
	
	
	@Column(name="patient_representatives_profile_address")
	private String address;
	
	
	@Column(name="patient_representatives_profile_zip")
	private String zip;
	
	
	@Column(name="patient_representatives_profile_email")
	private String email;
	
	
	@Column(name="patient_representatives_profile_phone")
	private String phone;

	
	@Column(name="patient_representatives_profile_fax")
	private String fax;
	
	
	@Column(name="patient_representatives_profile_type")
	private Integer type;
	
	
	@Column(name="patient_representatives_profile_username")
	private String username;
	
	
	@Column(name="patient_representatives_profile_password")
	private String password;
	
	
	@Column(name="patient_representatives_profile_encpswd")
	private String encriptedpassword;
	
	
	@Column(name="patient_representatives_profile_active")
	private Boolean active;
	
	
	@Column(name="patient_representatives_profile_access_time")
	private Timestamp accesstime;
	
	
	@Column(name="patient_representatives_profile_security_question1")
	private String securityQuestion1;
	
	
	@Column(name="patient_representatives_profile_security_question2")
	private String securityQuestion2;
	
	@Column(name="patient_representatives_profile_security_question3")
	private String securityQuestion3;
	
	@Column(name="patient_representatives_profile_security_answer1")
	private String answer1;
	
	@Column(name="patient_representatives_profile_security_answer2")
	private String answer2;
	
	@Column(name="patient_representatives_profile_security_answer3")
	private String answer3;
	
	@Column(name="patient_representatives_profile_password_reset")
	private Integer passwordReset;
	
	@Column(name="patient_representatives_profile_state")
	private String state;
	
	@Column(name="patient_representatives_profile_city")
	private Integer city;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncriptedpassword() {
		return encriptedpassword;
	}

	public void setEncriptedpassword(String encriptedpassword) {
		this.encriptedpassword = encriptedpassword;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getAccesstime() {
		return accesstime;
	}

	public void setAccesstime(Timestamp accesstime) {
		this.accesstime = accesstime;
	}

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getSecurityQuestion3() {
		return securityQuestion3;
	}

	public void setSecurityQuestion3(String securityQuestion3) {
		this.securityQuestion3 = securityQuestion3;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public Integer getPasswordReset() {
		return passwordReset;
	}

	public void setPasswordReset(Integer passwordReset) {
		this.passwordReset = passwordReset;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	
	
}
