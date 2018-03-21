package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pe_element_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeElementDetail {

	@Id
	@Column(name="pe_element_detail_id")
	private Integer peElementDetailId;

	@Column(name="pe_element_detail_name")
	private String peElementDetailName;

	@Column(name="pe_element_detail_printtext")
	private String peElementDetailPrinttext;

	@Column(name="pe_element_detail_element_id")
	private Integer peElementDetailElementId;

	@Column(name="pe_element_detail_orderby")
	private Integer peElementDetailOrderby;

	@Column(name="pe_element_detail_isactive")
	private Boolean peElementDetailIsactive;

	@Column(name="pe_element_detail_gwid")
	private String peElementDetailGwid;

	@Column(name="pe_element_detail_displayname")
	private String peElementDetailDisplayname;

	public Integer getPeElementDetailId() {
		return peElementDetailId;
	}

	public void setPeElementDetailId(Integer peElementDetailId) {
		this.peElementDetailId = peElementDetailId;
	}

	public String getPeElementDetailName() {
		return peElementDetailName;
	}

	public void setPeElementDetailName(String peElementDetailName) {
		this.peElementDetailName = peElementDetailName;
	}

	public String getPeElementDetailPrinttext() {
		return peElementDetailPrinttext;
	}

	public void setPeElementDetailPrinttext(String peElementDetailPrinttext) {
		this.peElementDetailPrinttext = peElementDetailPrinttext;
	}

	public Integer getPeElementDetailElementId() {
		return peElementDetailElementId;
	}

	public void setPeElementDetailElementId(Integer peElementDetailElementId) {
		this.peElementDetailElementId = peElementDetailElementId;
	}

	public Integer getPeElementDetailOrderby() {
		return peElementDetailOrderby;
	}

	public void setPeElementDetailOrderby(Integer peElementDetailOrderby) {
		this.peElementDetailOrderby = peElementDetailOrderby;
	}

	public Boolean getPeElementDetailIsactive() {
		return peElementDetailIsactive;
	}

	public void setPeElementDetailIsactive(Boolean peElementDetailIsactive) {
		this.peElementDetailIsactive = peElementDetailIsactive;
	}

	public String getPeElementDetailGwid() {
		return peElementDetailGwid;
	}

	public void setPeElementDetailGwid(String peElementDetailGwid) {
		this.peElementDetailGwid = peElementDetailGwid;
	}

	public String getPeElementDetailDisplayname() {
		return peElementDetailDisplayname;
	}

	public void setPeElementDetailDisplayname(String peElementDetailDisplayname) {
		this.peElementDetailDisplayname = peElementDetailDisplayname;
	}
	
	
}