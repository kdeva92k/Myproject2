package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "ins_company")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsCompany {

	@Id
	@Column(name="ins_company_id")
	private Integer insCompanyId;

	@Column(name="ins_company_name")
	private String insCompanyName;

	@Column(name="ins_company_comments")
	private String insCompanyComments;

	@Column(name="ins_company_isactive")
	private Boolean insCompanyIsactive;

	@Column(name="ins_company_source")
	private String insCompanySource;

	@Column(name="ins_company_extra1")
	private String insCompanyExtra1;

	@Column(name="ins_company_created_by")
	private String insCompanyCreatedBy;

	@Column(name="ins_company_modified_by")
	private String insCompanyModifiedBy;

	@Column(name="h555555")
	private Integer h555555;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="ins_company_modified_date")
	private Timestamp insCompanyModifiedDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="ins_company_created_date")
	private Timestamp insCompanyCreatedDate;

	@Column(name="ins_company_gcodeneeded")
	private Boolean insCompanyGcodeneeded;

	public Boolean getInsCompanyGcodeneeded() {
		return insCompanyGcodeneeded;
	}

	public void setInsCompanyGcodeneeded(Boolean insCompanyGcodeneeded) {
		this.insCompanyGcodeneeded = insCompanyGcodeneeded;
	}

	public Integer getInsCompanyId() {
		return insCompanyId;
	}

	public void setInsCompanyId(Integer insCompanyId) {
		this.insCompanyId = insCompanyId;
	}

	public String getInsCompanyName() {
		return insCompanyName;
	}

	public void setInsCompanyName(String insCompanyName) {
		this.insCompanyName = insCompanyName;
	}

	public String getInsCompanyComments() {
		return insCompanyComments;
	}

	public void setInsCompanyComments(String insCompanyComments) {
		this.insCompanyComments = insCompanyComments;
	}

	public Boolean getInsCompanyIsactive() {
		return insCompanyIsactive;
	}

	public void setInsCompanyIsactive(Boolean insCompanyIsactive) {
		this.insCompanyIsactive = insCompanyIsactive;
	}

	public String getInsCompanySource() {
		return insCompanySource;
	}

	public void setInsCompanySource(String insCompanySource) {
		this.insCompanySource = insCompanySource;
	}

	public String getInsCompanyExtra1() {
		return insCompanyExtra1;
	}

	public void setInsCompanyExtra1(String insCompanyExtra1) {
		this.insCompanyExtra1 = insCompanyExtra1;
	}

	public String getInsCompanyCreatedBy() {
		return insCompanyCreatedBy;
	}

	public void setInsCompanyCreatedBy(String insCompanyCreatedBy) {
		this.insCompanyCreatedBy = insCompanyCreatedBy;
	}

	public String getInsCompanyModifiedBy() {
		return insCompanyModifiedBy;
	}

	public void setInsCompanyModifiedBy(String insCompanyModifiedBy) {
		this.insCompanyModifiedBy = insCompanyModifiedBy;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Timestamp getInsCompanyModifiedDate() {
		return insCompanyModifiedDate;
	}

	public void setInsCompanyModifiedDate(Timestamp insCompanyModifiedDate) {
		this.insCompanyModifiedDate = insCompanyModifiedDate;
	}

	public Timestamp getInsCompanyCreatedDate() {
		return insCompanyCreatedDate;
	}

	public void setInsCompanyCreatedDate(Timestamp insCompanyCreatedDate) {
		this.insCompanyCreatedDate = insCompanyCreatedDate;
	}	
}