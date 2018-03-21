package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "surgical_history_element")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurgicalHistoryElement {

	@Id
	@Column(name="surgical_history_element_id")
	private Integer surgicalHistoryElementId;

	@Column(name="surgical_history_element_name")
	private String surgicalHistoryElementName;

	@Column(name="surgical_history_element_isactive")
	private Boolean surgicalHistoryElementIsactive;

	@Column(name="surgical_history_element_orderby")
	private Integer surgicalHistoryElementOrderby;

	@Column(name="surgical_history_element_system_id")
	private Integer surgicalHistoryElementSystemId;

	@Column(name="surgical_history_element_printtext")
	private String surgicalHistoryElementPrinttext;
	
	@OneToMany(mappedBy="surgicalHistoryElement")
	List<SurgicalHistoryElementDetails> surgicalHistoryElementDetails;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="surgical_history_element_system_id", referencedColumnName="clinical_system_hx_gwid", insertable=false, updatable=false)
	@JsonManagedReference
	ClinicalSystem clinicalSystem;



	public Integer getSurgicalHistoryElementId() {
		return surgicalHistoryElementId;
	}



	public String getSurgicalHistoryElementName() {
		return surgicalHistoryElementName;
	}



	public Boolean getSurgicalHistoryElementIsactive() {
		return surgicalHistoryElementIsactive;
	}



	public Integer getSurgicalHistoryElementOrderby() {
		return surgicalHistoryElementOrderby;
	}



	public Integer getSurgicalHistoryElementSystemId() {
		return surgicalHistoryElementSystemId;
	}



	public String getSurgicalHistoryElementPrinttext() {
		return surgicalHistoryElementPrinttext;
	}



	public List<SurgicalHistoryElementDetails> getSurgicalHistoryElementDetails() {
		return surgicalHistoryElementDetails;
	}



	public ClinicalSystem getClinicalSystem() {
		return clinicalSystem;
	}



	public void setSurgicalHistoryElementId(Integer surgicalHistoryElementId) {
		this.surgicalHistoryElementId = surgicalHistoryElementId;
	}



	public void setSurgicalHistoryElementName(String surgicalHistoryElementName) {
		this.surgicalHistoryElementName = surgicalHistoryElementName;
	}



	public void setSurgicalHistoryElementIsactive(
			Boolean surgicalHistoryElementIsactive) {
		this.surgicalHistoryElementIsactive = surgicalHistoryElementIsactive;
	}



	public void setSurgicalHistoryElementOrderby(
			Integer surgicalHistoryElementOrderby) {
		this.surgicalHistoryElementOrderby = surgicalHistoryElementOrderby;
	}



	public void setSurgicalHistoryElementSystemId(
			Integer surgicalHistoryElementSystemId) {
		this.surgicalHistoryElementSystemId = surgicalHistoryElementSystemId;
	}



	public void setSurgicalHistoryElementPrinttext(
			String surgicalHistoryElementPrinttext) {
		this.surgicalHistoryElementPrinttext = surgicalHistoryElementPrinttext;
	}



	public void setSurgicalHistoryElementDetails(
			List<SurgicalHistoryElementDetails> surgicalHistoryElementDetails) {
		this.surgicalHistoryElementDetails = surgicalHistoryElementDetails;
	}



	public void setClinicalSystem(ClinicalSystem clinicalSystem) {
		this.clinicalSystem = clinicalSystem;
	}
	
	

}