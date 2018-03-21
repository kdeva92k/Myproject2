package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "loaddefaultprescriberdrug")
public class Loaddefaultprescriberdrug {

	@Id
	@Column(name="loginid")
	private Integer loginid;
	
	public Integer getLoginid() {
		return loginid;
	}

	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name="flag")
	private Integer flag;
}