package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "hmr_category_url")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HmrCategoryUrl {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hmr_category_url_hmr_category_url_id_seq")
	@SequenceGenerator(name ="hmr_category_url_hmr_category_url_id_seq", sequenceName="hmr_category_url_hmr_category_url_id_seq", allocationSize=1)
	@Column(name="hmr_category_url_id",nullable=false)
	private Integer hmrCategoryUrlId;

	@Column(name="hmr_category_url_name",length=100)
	private String hmrCategoryUrlName;

	@Column(name="hmr_category_url_modifiedby")
	private Integer hmrCategoryUrlModifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="hmr_category_url_modifiedon")
	private Timestamp hmrCategoryUrlModifiedon;

	@Column(name="hmr_category_url_categoryid")
	private Integer hmrCategoryUrlCategoryid;

	@Column(name="created_by")
	private Integer createdBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="created_on")
	private Timestamp createdOn;

	public Integer getHmrCategoryUrlId() {
		return hmrCategoryUrlId;
	}

	public void setHmrCategoryUrlId(Integer hmrCategoryUrlId) {
		this.hmrCategoryUrlId = hmrCategoryUrlId;
	}

	public String getHmrCategoryUrlName() {
		return hmrCategoryUrlName;
	}

	public void setHmrCategoryUrlName(String hmrCategoryUrlName) {
		this.hmrCategoryUrlName = hmrCategoryUrlName;
	}

	public Integer getHmrCategoryUrlModifiedby() {
		return hmrCategoryUrlModifiedby;
	}

	public void setHmrCategoryUrlModifiedby(Integer hmrCategoryUrlModifiedby) {
		this.hmrCategoryUrlModifiedby = hmrCategoryUrlModifiedby;
	}

	public Timestamp getHmrCategoryUrlModifiedon() {
		return hmrCategoryUrlModifiedon;
	}

	public void setHmrCategoryUrlModifiedon(Timestamp hmrCategoryUrlModifiedon) {
		this.hmrCategoryUrlModifiedon = hmrCategoryUrlModifiedon;
	}

	public Integer getHmrCategoryUrlCategoryid() {
		return hmrCategoryUrlCategoryid;
	}

	public void setHmrCategoryUrlCategoryid(Integer hmrCategoryUrlCategoryid) {
		this.hmrCategoryUrlCategoryid = hmrCategoryUrlCategoryid;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
}