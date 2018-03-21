package com.glenwood.glaceemr.server.application.models;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pqrs_patient_entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PqrsPatientEntries {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pqrs_patient_entries_pqrs_patient_entries_id_seq")
	@SequenceGenerator(name ="pqrs_patient_entries_pqrs_patient_entries_id_seq", sequenceName="pqrs_patient_entries_pqrs_patient_entries_id_seq", allocationSize=1)
	@Column(name="pqrs_patient_entries_id")
	private Integer pqrsPatientEntriesId;

	@Column(name="pqrs_patient_entries_patient_id")
	private Integer pqrsPatientEntriesPatientId;

	@Column(name="pqrs_patient_entries_provider_id")
	private Integer pqrsPatientEntriesProviderId;

	@Column(name="pqrs_patient_entries_encounter_id")
	private Integer pqrsPatientEntriesEncounterId;

	@Column(name="pqrs_patient_entries_dos")
	private Date pqrsPatientEntriesDos;

	@Column(name="pqrs_patient_entries_measure_id")
	private String pqrsPatientEntriesMeasureId;

	@Column(name="pqrs_patient_entries_cptcode")
	private String pqrsPatientEntriesCptcode;

	@Column(name="pqrs_patient_entries_modifier")
	private String pqrsPatientEntriesModifier;

	@Column(name="pqrs_patient_entries_reason_field")
	private String pqrsPatientEntriesReasonField;

	@Column(name="pqrs_patient_entries_criteria_number")
	private Integer pqrsPatientEntriesCriteriaNumber;

	@Column(name="pqrs_patient_entries_short_description")
	private String pqrsPatientEntriesShortDescription;

	@Column(name="pqrs_patient_entries_performance_indicator")
	private Integer pqrsPatientEntriesPerformanceIndicator;

	@Column(name="pqrs_patient_entries_is_active")
	private Boolean pqrsPatientEntriesIsActive;
	
	/*@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	@JoinColumn(name="pqrs_patient_entries_provider_id", referencedColumnName="macra_provider_configuration_provider_id", insertable=false, updatable=false)
	@JsonManagedReference
	private MacraProviderConfiguration macraproviderid;
	
	public MacraProviderConfiguration getMacraproviderid() {
		return macraproviderid;
	}

	public void setMacraproviderid(MacraProviderConfiguration macraproviderid) {
		this.macraproviderid = macraproviderid;
	}*/

	
	public Integer getPqrsPatientEntriesId() {
		return pqrsPatientEntriesId;
	}

	public void setPqrsPatientEntriesId(Integer pqrsPatientEntriesId) {
		this.pqrsPatientEntriesId = pqrsPatientEntriesId;
	}

	public Integer getPqrsPatientEntriesPatientId() {
		return pqrsPatientEntriesPatientId;
	}

	public void setPqrsPatientEntriesPatientId(Integer pqrsPatientEntriesPatientId) {
		this.pqrsPatientEntriesPatientId = pqrsPatientEntriesPatientId;
	}

	public Integer getPqrsPatientEntriesProviderId() {
		return pqrsPatientEntriesProviderId;
	}

	public void setPqrsPatientEntriesProviderId(Integer pqrsPatientEntriesProviderId) {
		this.pqrsPatientEntriesProviderId = pqrsPatientEntriesProviderId;
	}

	public Integer getPqrsPatientEntriesEncounterId() {
		return pqrsPatientEntriesEncounterId;
	}

	public void setPqrsPatientEntriesEncounterId(Integer pqrsPatientEntriesEncounterId) {
		this.pqrsPatientEntriesEncounterId = pqrsPatientEntriesEncounterId;
	}

	public Date getPqrsPatientEntriesDos() {
		return pqrsPatientEntriesDos;
	}

	public void setPqrsPatientEntriesDos(Date pqrsPatientEntriesDos) {
		this.pqrsPatientEntriesDos = pqrsPatientEntriesDos;
	}

	public String getPqrsPatientEntriesMeasureId() {
		return pqrsPatientEntriesMeasureId;
	}

	public void setPqrsPatientEntriesMeasureId(String pqrsPatientEntriesMeasureId) {
		this.pqrsPatientEntriesMeasureId = pqrsPatientEntriesMeasureId;
	}

	public String getPqrsPatientEntriesCptcode() {
		return pqrsPatientEntriesCptcode;
	}

	public void setPqrsPatientEntriesCptcode(String pqrsPatientEntriesCptcode) {
		this.pqrsPatientEntriesCptcode = pqrsPatientEntriesCptcode;
	}

	public String getPqrsPatientEntriesModifier() {
		return pqrsPatientEntriesModifier;
	}

	public void setPqrsPatientEntriesModifier(String pqrsPatientEntriesModifier) {
		this.pqrsPatientEntriesModifier = pqrsPatientEntriesModifier;
	}

	public String getPqrsPatientEntriesReasonField() {
		return pqrsPatientEntriesReasonField;
	}

	public void setPqrsPatientEntriesReasonField(String pqrsPatientEntriesReasonField) {
		this.pqrsPatientEntriesReasonField = pqrsPatientEntriesReasonField;
	}

	public Integer getPqrsPatientEntriesCriteriaNumber() {
		return pqrsPatientEntriesCriteriaNumber;
	}

	public void setPqrsPatientEntriesCriteriaNumber(Integer pqrsPatientEntriesCriteriaNumber) {
		this.pqrsPatientEntriesCriteriaNumber = pqrsPatientEntriesCriteriaNumber;
	}

	public String getPqrsPatientEntriesShortDescription() {
		return pqrsPatientEntriesShortDescription;
	}

	public void setPqrsPatientEntriesShortDescription(String pqrsPatientEntriesShortDescription) {
		this.pqrsPatientEntriesShortDescription = pqrsPatientEntriesShortDescription;
	}

	public Integer getPqrsPatientEntriesPerformanceIndicator() {
		return pqrsPatientEntriesPerformanceIndicator;
	}

	public void setPqrsPatientEntriesPerformanceIndicator(Integer pqrsPatientEntriesPerformanceIndicator) {
		this.pqrsPatientEntriesPerformanceIndicator = pqrsPatientEntriesPerformanceIndicator;
	}

	public Boolean getPqrsPatientEntriesIsActive() {
		return pqrsPatientEntriesIsActive;
	}

	public void setPqrsPatientEntriesIsActive(Boolean pqrsPatientEntriesIsActive) {
		this.pqrsPatientEntriesIsActive = pqrsPatientEntriesIsActive;
	}
		
	
}