package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pe_element_detail_option")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeElementDetailOption {

	
	@Column(name="pe_element_detail_option_id")
	private Integer peElementDetailOptionId;

	@Column(name="pe_element_detail_option_name")
	private String peElementDetailOptionName;

	@Id
	@Column(name="pe_element_detail_option_gwid")
	private String peElementDetailOptionGwid;

	@Column(name="pe_element_detail_option_detail_id")
	private Integer peElementDetailOptionDetailId;

	@Column(name="pe_element_detail_option_orderby")
	private Integer peElementDetailOptionOrderby;

	@Column(name="pe_element_detail_option_isactive")
	private Boolean peElementDetailOptionIsactive;

	@Column(name="pe_element_detail_option_printtext")
	private String peElementDetailOptionPrinttext;

	@Column(name="pe_element_detail_option_displayname")
	private String peElementDetailOptionDisplayname;

	
	@OneToMany(mappedBy="peElementDetailOption",fetch=FetchType.LAZY)
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMappings; 
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "pe_element_detail_option_gwid ", referencedColumnName = "clinical_elements_gwid", insertable = false, updatable = false)
	private ClinicalElements clinicalElement; 
	
	
	@OneToMany(mappedBy="peElementDetailOption",fetch=FetchType.LAZY)
	List<PatientClinicalElements> patientClinicalElements;
	
	
	
	public ClinicalElements getClinicalElement() {
		return clinicalElement;
	}

	public void setClinicalElement(ClinicalElements clinicalElement) {
		this.clinicalElement = clinicalElement;
	}

	public Integer getPeElementDetailOptionId() {
		return peElementDetailOptionId;
	}

	public void setPeElementDetailOptionId(Integer peElementDetailOptionId) {
		this.peElementDetailOptionId = peElementDetailOptionId;
	}

	public String getPeElementDetailOptionName() {
		return peElementDetailOptionName;
	}

	public void setPeElementDetailOptionName(String peElementDetailOptionName) {
		this.peElementDetailOptionName = peElementDetailOptionName;
	}

	public String getPeElementDetailOptionGwid() {
		return peElementDetailOptionGwid;
	}

	public void setPeElementDetailOptionGwid(String peElementDetailOptionGwid) {
		this.peElementDetailOptionGwid = peElementDetailOptionGwid;
	}

	public Integer getPeElementDetailOptionDetailId() {
		return peElementDetailOptionDetailId;
	}

	public void setPeElementDetailOptionDetailId(
			Integer peElementDetailOptionDetailId) {
		this.peElementDetailOptionDetailId = peElementDetailOptionDetailId;
	}

	public Integer getPeElementDetailOptionOrderby() {
		return peElementDetailOptionOrderby;
	}

	public void setPeElementDetailOptionOrderby(Integer peElementDetailOptionOrderby) {
		this.peElementDetailOptionOrderby = peElementDetailOptionOrderby;
	}

	public Boolean getPeElementDetailOptionIsactive() {
		return peElementDetailOptionIsactive;
	}

	public void setPeElementDetailOptionIsactive(
			Boolean peElementDetailOptionIsactive) {
		this.peElementDetailOptionIsactive = peElementDetailOptionIsactive;
	}

	public String getPeElementDetailOptionPrinttext() {
		return peElementDetailOptionPrinttext;
	}

	public void setPeElementDetailOptionPrinttext(
			String peElementDetailOptionPrinttext) {
		this.peElementDetailOptionPrinttext = peElementDetailOptionPrinttext;
	}

	public String getPeElementDetailOptionDisplayname() {
		return peElementDetailOptionDisplayname;
	}

	public void setPeElementDetailOptionDisplayname(
			String peElementDetailOptionDisplayname) {
		this.peElementDetailOptionDisplayname = peElementDetailOptionDisplayname;
	}
	
	
}