package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "problem_leaf_details")
public class ProblemLeafDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="problem_leaf_details_problem_leaf_details_id_seq")
	@SequenceGenerator(name ="problem_leaf_details_problem_leaf_details_id_seq", sequenceName="problem_leaf_details_problem_leaf_details_id_seq", allocationSize=1)
	@Column(name="problem_leaf_details_id")
	private Integer problem_leaf_details_id;

	@Column(name="problem_leaf_details_desc")
	private String problem_leaf_details_desc;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_leaf_details_createddate")
	private Timestamp problem_leaf_details_createddate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="problem_leaf_details_enddate")
	private Timestamp problem_leaf_details_enddate;

	@Column(name="problem_leaf_details_userid")
	private Integer problem_leaf_details_userid;

	@Column(name="problem_leaf_details_iscompleted")
	private Boolean problem_leaf_details_iscompleted;

	@Column(name="problem_leaf_details_patientid")
	private String problem_leaf_details_patientid;

	@NotFound(action=NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="problem_leaf_details_userid", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
    EmployeeProfile empProfile;
    
    
	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}

	public Integer getproblem_leaf_details_id() {
		return problem_leaf_details_id;
	}

	public void setproblem_leaf_details_id(Integer problem_leaf_details_id) {
		this.problem_leaf_details_id = problem_leaf_details_id;
	}

	public String getproblem_leaf_details_desc() {
		return problem_leaf_details_desc;
	}

	public void setproblem_leaf_details_desc(String problem_leaf_details_desc) {
		this.problem_leaf_details_desc = problem_leaf_details_desc;
	}

	public Timestamp getproblem_leaf_details_createddate() {
		return problem_leaf_details_createddate;
	}

	public void setproblem_leaf_details_createddate(Timestamp problem_leaf_details_createddate) {
		this.problem_leaf_details_createddate = problem_leaf_details_createddate;
	}

	public Timestamp getproblem_leaf_details_enddate() {
		return problem_leaf_details_enddate;
	}

	public void setproblem_leaf_details_enddate(Timestamp problem_leaf_details_enddate) {
		this.problem_leaf_details_enddate = problem_leaf_details_enddate;
	}

	public Integer getproblem_leaf_details_userid() {
		return problem_leaf_details_userid;
	}

	public void setproblem_leaf_details_userid(Integer problem_leaf_details_userid) {
		this.problem_leaf_details_userid = problem_leaf_details_userid;
	}

	public Boolean getproblem_leaf_details_iscompleted() {
		return problem_leaf_details_iscompleted;
	}

	public void setproblem_leaf_details_iscompleted(Boolean problem_leaf_details_iscompleted) {
		this.problem_leaf_details_iscompleted = problem_leaf_details_iscompleted;
	}

	public String getproblem_leaf_details_patientid() {
		return problem_leaf_details_patientid;
	}

	public void setproblem_leaf_details_patientid(String problem_leaf_details_patientid) {
		this.problem_leaf_details_patientid = problem_leaf_details_patientid;
	}
	
}