package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "billinglookup")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Billinglookup {

	@Id
	@Column(name="blook_id")
	private Integer blookId;

	@Column(name="blook_group")
	private Short blookGroup;

	@Column(name="blook_intid")
	private Short blookIntid;

	@Column(name="blook_charid")
	private String blookCharid;

	@Column(name="blook_name")
	private String blookName;

	@Column(name="blook_desc")
	private String blookDesc;

	@Column(name="blook_extra")
	private String blookExtra;

	@Column(name="blook_subgroup")
	private Short blookSubgroup;

	@Column(name="blook_orderby")
	private Short blookOrderby;

	@Column(name="blook_isactive")
	private Boolean blookIsactive;

	@Column(name="billinglookup_keycode")
	private String billinglookupKeycode;
	
	@Column(name="blook_code_value")
	private String blookcodevalue;
	
	public String getBlookcodevalue() {
		return blookcodevalue;
	}

	public void setBlookcodevalue(String blookcodevalue) {
		this.blookcodevalue = blookcodevalue;
	}

	public Integer getBlookId() {
		return blookId;
	}

	public void setBlookId(Integer blookId) {
		this.blookId = blookId;
	}

	public Short getBlookGroup() {
		return blookGroup;
	}

	public void setBlookGroup(Short blookGroup) {
		this.blookGroup = blookGroup;
	}

	public Short getBlookIntid() {
		return blookIntid;
	}

	public void setBlookIntid(Short blookIntid) {
		this.blookIntid = blookIntid;
	}

	public String getBlookCharid() {
		return blookCharid;
	}

	public void setBlookCharid(String blookCharid) {
		this.blookCharid = blookCharid;
	}

	public String getBlookName() {
		return blookName;
	}

	public void setBlookName(String blookName) {
		this.blookName = blookName;
	}

	public String getBlookDesc() {
		return blookDesc;
	}

	public void setBlookDesc(String blookDesc) {
		this.blookDesc = blookDesc;
	}

	public String getBlookExtra() {
		return blookExtra;
	}

	public void setBlookExtra(String blookExtra) {
		this.blookExtra = blookExtra;
	}

	public Short getBlookSubgroup() {
		return blookSubgroup;
	}

	public void setBlookSubgroup(Short blookSubgroup) {
		this.blookSubgroup = blookSubgroup;
	}

	public Short getBlookOrderby() {
		return blookOrderby;
	}

	public void setBlookOrderby(Short blookOrderby) {
		this.blookOrderby = blookOrderby;
	}

	public Boolean getBlookIsactive() {
		return blookIsactive;
	}

	public void setBlookIsactive(Boolean blookIsactive) {
		this.blookIsactive = blookIsactive;
	}

	public String getBillinglookupKeycode() {
		return billinglookupKeycode;
	}

	public void setBillinglookupKeycode(String billinglookupKeycode) {
		this.billinglookupKeycode = billinglookupKeycode;
	}
	
}