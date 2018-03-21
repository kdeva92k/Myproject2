package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "cahps_proportion_table")
public class CahpsProportionTable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cahps_proportion_table_idseq")
	@SequenceGenerator(name ="cahps_proportion_table_idseq", sequenceName="cahps_proportion_table_idseq", allocationSize=1)
	@Column(name="proportion_id")
	private Integer proportionId;

	@Column(name="proportion_question_id")
	private Integer proportionQuestionId;

	@Column(name="proportion_choice_id")
	private Integer proportionChoiceId;

	@Column(name="proportion_question_freq")
	private Double proportionQuestionFreq;

	@Column(name="proportion_choice_freq")
	private Double proportionChoiceFreq;

	@Column(name="cahps_proportion")
	private Double cahpsProportion;

	@Column(name="patient_provider_id")
	private Integer patientProviderId;

	@Column(name="patient_provider_name")
	private String patientProviderName;

	public Integer getProportionId() {
		return proportionId;
	}

	public void setProportionId(Integer proportionId) {
		this.proportionId = proportionId;
	}

	public Integer getProportionQuestionId() {
		return proportionQuestionId;
	}

	public void setProportionQuestionId(Integer proportionQuestionId) {
		this.proportionQuestionId = proportionQuestionId;
	}

	public Integer getProportionChoiceId() {
		return proportionChoiceId;
	}

	public void setProportionChoiceId(Integer proportionChoiceId) {
		this.proportionChoiceId = proportionChoiceId;
	}

	public Double getProportionQuestionFreq() {
		return proportionQuestionFreq;
	}

	public void setProportionQuestionFreq(Double proportionQuestionFreq) {
		this.proportionQuestionFreq = proportionQuestionFreq;
	}

	public Double getProportionChoiceFreq() {
		return proportionChoiceFreq;
	}

	public void setProportionChoiceFreq(Double proportionChoiceFreq) {
		this.proportionChoiceFreq = proportionChoiceFreq;
	}

	public Double getCahpsProportion() {
		return cahpsProportion;
	}

	public void setCahpsProportion(Double cahpsProportion) {
		this.cahpsProportion = cahpsProportion;
	}

	public Integer getPatientProviderId() {
		return patientProviderId;
	}

	public void setPatientProviderId(Integer patientProviderId) {
		this.patientProviderId = patientProviderId;
	}

	public String getPatientProviderName() {
		return patientProviderName;
	}

	public void setPatientProviderName(String patientProviderName) {
		this.patientProviderName = patientProviderName;
	}
	
	
}