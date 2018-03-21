package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "hpi_element")
public class HpiElement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hpi_element_hpi_element_id_seq")
	@SequenceGenerator(name ="hpi_element_hpi_element_id_seq", sequenceName="hpi_element_hpi_element_id_seq", allocationSize=1)
	@Column(name="hpi_element_id")
	private Integer hpiElementId;

	@Column(name="hpi_element_gwid")
	private String hpiElementGwid;

	@Column(name="hpi_element_qualifierid")
	private Integer hpiElementQualifierid;

	@Column(name="hpi_element_name")
	private String hpiElementName;

	@Column(name="hpi_element_isactive")
	private Boolean hpiElementIsactive;

	@Column(name="hpi_element_orderby")
	private Integer hpiElementOrderby;

	@Column(name="hpi_element_printtext")
	private String hpiElementPrinttext;
	
	/*@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="hpi_element_gwid",referencedColumnName="clinical_elements_gwid",insertable = false, updatable = false)
	private ClinicalElements clinicalElements;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="hpiElement")
	@JsonBackReference
	private List<PatientClinicalElements> patientClinicalElements;*/

	public Integer getHpiElementId() {
		return hpiElementId;
	}


	public void setHpiElementId(Integer hpiElementId) {
		this.hpiElementId = hpiElementId;
	}


	public String getHpiElementGwid() {
		return hpiElementGwid;
	}


	public void setHpiElementGwid(String hpiElementGwid) {
		this.hpiElementGwid = hpiElementGwid;
	}


	public Integer getHpiElementQualifierid() {
		return hpiElementQualifierid;
	}


	public void setHpiElementQualifierid(Integer hpiElementQualifierid) {
		this.hpiElementQualifierid = hpiElementQualifierid;
	}


	public String getHpiElementName() {
		return hpiElementName;
	}


	public void setHpiElementName(String hpiElementName) {
		this.hpiElementName = hpiElementName;
	}


	public Boolean getHpiElementIsactive() {
		return hpiElementIsactive;
	}


	public void setHpiElementIsactive(Boolean hpiElementIsactive) {
		this.hpiElementIsactive = hpiElementIsactive;
	}


	public Integer getHpiElementOrderby() {
		return hpiElementOrderby;
	}


	public void setHpiElementOrderby(Integer hpiElementOrderby) {
		this.hpiElementOrderby = hpiElementOrderby;
	}


	public String getHpiElementPrinttext() {
		return hpiElementPrinttext;
	}


	public void setHpiElementPrinttext(String hpiElementPrinttext) {
		this.hpiElementPrinttext = hpiElementPrinttext;
	}


	/*public ClinicalElements getClinicalElements() {
		return clinicalElements;
	}


	public void setClinicalElements(ClinicalElements clinicalElements) {
		this.clinicalElements = clinicalElements;
	}


	public List<PatientClinicalElements> getPatientClinicalElements() {
		return patientClinicalElements;
	}


	public void setPatientClinicalElements(
			List<PatientClinicalElements> patientClinicalElements) {
		this.patientClinicalElements = patientClinicalElements;
	}*/



}