package com.glenwood.glaceemr.server.application.models;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "emp_profile_agent_config")
public class EmpProfileAgentConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="emp_profile_agent_config_emp_profile_agent_config_id_seq")
	@SequenceGenerator(name ="emp_profile_agent_config_emp_profile_agent_config_id_seq", sequenceName="emp_profile_agent_config_emp_profile_agent_config_id_seq", allocationSize=1)
	@Column(name="emp_profile_agent_config_id")
	private Integer empProfileAgentConfigId;

	@Column(name="emp_profile_agent_config_empid")
	private Integer empProfileAgentConfigEmpid;

	@Column(name="emp_profile_agent_config_agent_id")
	private Integer empProfileAgentConfigAgentId;

	public Integer getEmpProfileAgentConfigId() {
		return empProfileAgentConfigId;
	}

	public void setEmpProfileAgentConfigId(Integer empProfileAgentConfigId) {
		this.empProfileAgentConfigId = empProfileAgentConfigId;
	}

	public Integer getEmpProfileAgentConfigEmpid() {
		return empProfileAgentConfigEmpid;
	}

	public void setEmpProfileAgentConfigEmpid(Integer empProfileAgentConfigEmpid) {
		this.empProfileAgentConfigEmpid = empProfileAgentConfigEmpid;
	}

	public Integer getEmpProfileAgentConfigAgentId() {
		return empProfileAgentConfigAgentId;
	}

	public void setEmpProfileAgentConfigAgentId(Integer empProfileAgentConfigAgentId) {
		this.empProfileAgentConfigAgentId = empProfileAgentConfigAgentId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name="emp_profile_agent_config_empid", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	EmployeeProfile employeeProfile;

	public EmployeeProfile getEmployeeProfile() {
		return employeeProfile;
	}

	public void setEmployeeProfile(EmployeeProfile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}
}