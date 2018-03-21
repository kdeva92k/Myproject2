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
@Table(name = "pe_element")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeElement {

	@Column(name="pe_element_id")
	private Integer peElementId;

	@Column(name="pe_element_name")
	private String peElementName;

	@Column(name="pe_element_printtext")
	private String peElementPrinttext;

	@Id
	@Column(name="pe_element_gwid")
	private String peElementGwid;

	@Column(name="pe_element_group_id")
	private Integer peElementGroupId;

	@Column(name="pe_element_orderby")
	private Integer peElementOrderby;

	@Column(name="pe_element_isactive")
	private Boolean peElementIsactive;

	@Column(name="pe_element_hitcount")
	private Integer peElementHitcount;

	@Column(name="pe_element_isnameneededinprint")
	private Boolean peElementIsnameneededinprint;
	
	@OneToMany(mappedBy="peElement",fetch=FetchType.LAZY)
	List<ClinicalTextMapping> clinicalTextMappings; 
	
	@OneToMany(mappedBy="peElement",fetch=FetchType.LAZY)
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMappings; 
	
	@OneToMany(mappedBy="peElement",fetch=FetchType.LAZY)
	List<PatientClinicalElements> patientClinicalElements; 
	
	public List<ClinicalTextMapping> getClinicalTextMappings() {
		return clinicalTextMappings;
	}

	public void setClinicalTextMappings(
			List<ClinicalTextMapping> clinicalTextMappings) {
		this.clinicalTextMappings = clinicalTextMappings;
	}

	public Integer getPeElementId() {
		return peElementId;
	}

	public void setPeElementId(Integer peElementId) {
		this.peElementId = peElementId;
	}

	public String getPeElementName() {
		return peElementName;
	}

	public void setPeElementName(String peElementName) {
		this.peElementName = peElementName;
	}

	public String getPeElementPrinttext() {
		return peElementPrinttext;
	}

	public void setPeElementPrinttext(String peElementPrinttext) {
		this.peElementPrinttext = peElementPrinttext;
	}

	public String getPeElementGwid() {
		return peElementGwid;
	}

	public void setPeElementGwid(String peElementGwid) {
		this.peElementGwid = peElementGwid;
	}

	public Integer getPeElementGroupId() {
		return peElementGroupId;
	}

	public void setPeElementGroupId(Integer peElementGroupId) {
		this.peElementGroupId = peElementGroupId;
	}

	public Integer getPeElementOrderby() {
		return peElementOrderby;
	}

	public void setPeElementOrderby(Integer peElementOrderby) {
		this.peElementOrderby = peElementOrderby;
	}

	public Boolean getPeElementIsactive() {
		return peElementIsactive;
	}

	public void setPeElementIsactive(Boolean peElementIsactive) {
		this.peElementIsactive = peElementIsactive;
	}

	public Integer getPeElementHitcount() {
		return peElementHitcount;
	}

	public void setPeElementHitcount(Integer peElementHitcount) {
		this.peElementHitcount = peElementHitcount;
	}

	public Boolean getPeElementIsnameneededinprint() {
		return peElementIsnameneededinprint;
	}

	public void setPeElementIsnameneededinprint(Boolean peElementIsnameneededinprint) {
		this.peElementIsnameneededinprint = peElementIsnameneededinprint;
	}
	
}