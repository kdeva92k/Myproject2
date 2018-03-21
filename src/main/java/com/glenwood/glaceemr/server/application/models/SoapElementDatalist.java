package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "soap_element_datalist")
public class SoapElementDatalist {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="soap_element_datalist_soap_element_datalist_id_seq")
	@SequenceGenerator(name ="soap_element_datalist_soap_element_datalist_id_seq", sequenceName="soap_element_datalist_soap_element_datalist_id_seq", allocationSize=1)
	@Column(name="soap_element_datalist_id")
	private Integer soapElementDatalistId;

	@Column(name="soap_element_datalist_tabid")
	private Integer soapElementDatalistTabid;

	@Column(name="soap_element_datalist_elementid")
	private String soapElementDatalistElementid;

	@Column(name="soap_element_datalist_data")
	private String soapElementDatalistData;

	@Column(name="soap_element_datalist_iscomplete")
	private Integer soapElementDatalistIscomplete;

	@Column(name="soap_element_datalist_patientid")
	private Integer soapElementDatalistPatientid;

	@Column(name="soap_element_datalist_encounterid")
	private Integer soapElementDatalistEncounterid;

	public Integer getSoapElementDatalistId() {
		return soapElementDatalistId;
	}

	public void setSoapElementDatalistId(Integer soapElementDatalistId) {
		this.soapElementDatalistId = soapElementDatalistId;
	}

	public Integer getSoapElementDatalistTabid() {
		return soapElementDatalistTabid;
	}

	public void setSoapElementDatalistTabid(Integer soapElementDatalistTabid) {
		this.soapElementDatalistTabid = soapElementDatalistTabid;
	}

	public String getSoapElementDatalistElementid() {
		return soapElementDatalistElementid;
	}

	public void setSoapElementDatalistElementid(String soapElementDatalistElementid) {
		this.soapElementDatalistElementid = soapElementDatalistElementid;
	}

	public String getSoapElementDatalistData() {
		return soapElementDatalistData;
	}

	public void setSoapElementDatalistData(String soapElementDatalistData) {
		this.soapElementDatalistData = soapElementDatalistData;
	}

	public Integer getSoapElementDatalistIscomplete() {
		return soapElementDatalistIscomplete;
	}

	public void setSoapElementDatalistIscomplete(
			Integer soapElementDatalistIscomplete) {
		this.soapElementDatalistIscomplete = soapElementDatalistIscomplete;
	}

	public Integer getSoapElementDatalistPatientid() {
		return soapElementDatalistPatientid;
	}

	public void setSoapElementDatalistPatientid(Integer soapElementDatalistPatientid) {
		this.soapElementDatalistPatientid = soapElementDatalistPatientid;
	}

	public Integer getSoapElementDatalistEncounterid() {
		return soapElementDatalistEncounterid;
	}

	public void setSoapElementDatalistEncounterid(
			Integer soapElementDatalistEncounterid) {
		this.soapElementDatalistEncounterid = soapElementDatalistEncounterid;
	}
	
	
}