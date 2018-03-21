package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "icdm")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Icdm {

	@Column(name="icdm_id")
	private Integer icdmId;

	@Id
	@Column(name="icdm_icdcode", length=50)
	private String icdmIcdcode;

	@Column(name="icdm_description", length=3700)
	private String icdmDescription;

	@Column(name="icdm_short_cut_desc")
	private String icdmShortCutDesc;

	@Column(name="icdm_groupid")
	private Integer icdmGroupid;

	@Column(name="icdm_orderby")
	private Integer icdmOrderby;

	@Column(name="icdm_icdmtype")
	private Integer icdmIcdmtype;

	@Column(name="icdm_hitcount")
	private Integer icdmHitcount;

	@Column(name="icdm_instruction_page", length=255)
	private String icdmInstructionPage;

	@Column(name="icdm_isactive")
	private Boolean icdmIsactive;

	@Column(name="icdm_group")
	private Integer icdmGroup;

	@Column(name="icdm_pier_resource_id")
	private Integer icdmPierResourceId;
	
	@Column(name="icdm_keywords", length=1000)
	private String icdmKeywords;

	public Integer getIcdmId() {
		return icdmId;
	}

	public void setIcdmId(Integer icdmId) {
		this.icdmId = icdmId;
	}

	public String getIcdmIcdcode() {
		return icdmIcdcode;
	}

	public void setIcdmIcdcode(String icdmIcdcode) {
		this.icdmIcdcode = icdmIcdcode;
	}

	public String getIcdmDescription() {
		return icdmDescription;
	}

	public void setIcdmDescription(String icdmDescription) {
		this.icdmDescription = icdmDescription;
	}

	public String getIcdmShortCutDesc() {
		return icdmShortCutDesc;
	}

	public void setIcdmShortCutDesc(String icdmShortCutDesc) {
		this.icdmShortCutDesc = icdmShortCutDesc;
	}

	public Integer getIcdmGroupid() {
		return icdmGroupid;
	}

	public void setIcdmGroupid(Integer icdmGroupid) {
		this.icdmGroupid = icdmGroupid;
	}

	public Integer getIcdmOrderby() {
		return icdmOrderby;
	}

	public void setIcdmOrderby(Integer icdmOrderby) {
		this.icdmOrderby = icdmOrderby;
	}

	public Integer getIcdmIcdmtype() {
		return icdmIcdmtype;
	}

	public void setIcdmIcdmtype(Integer icdmIcdmtype) {
		this.icdmIcdmtype = icdmIcdmtype;
	}

	public Integer getIcdmHitcount() {
		return icdmHitcount;
	}

	public void setIcdmHitcount(Integer icdmHitcount) {
		this.icdmHitcount = icdmHitcount;
	}

	public String getIcdmInstructionPage() {
		return icdmInstructionPage;
	}

	public void setIcdmInstructionPage(String icdmInstructionPage) {
		this.icdmInstructionPage = icdmInstructionPage;
	}

	public Boolean getIcdmIsactive() {
		return icdmIsactive;
	}

	public void setIcdmIsactive(Boolean icdmIsactive) {
		this.icdmIsactive = icdmIsactive;
	}

	public Integer getIcdmGroup() {
		return icdmGroup;
	}

	public void setIcdmGroup(Integer icdmGroup) {
		this.icdmGroup = icdmGroup;
	}

	public Integer getIcdmPierResourceId() {
		return icdmPierResourceId;
	}

	public void setIcdmPierResourceId(Integer icdmPierResourceId) {
		this.icdmPierResourceId = icdmPierResourceId;
	}

	public String getIcdmKeywords() {
		return icdmKeywords;
	}

	public void setIcdmKeywords(String icdmKeywords) {
		this.icdmKeywords = icdmKeywords;
	}
}