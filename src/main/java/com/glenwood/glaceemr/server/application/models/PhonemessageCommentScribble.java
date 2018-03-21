package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "phonemessage_comment_scribble")
public class PhonemessageCommentScribble {

	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "phonemessage_comment_scribble_scribbleid_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="scribbleid")
	private Integer scribbleid;

	@Column(name="encounter_id")
	private Integer encounterId;

	@Column(name="patient_id")
	private Integer patientId;

	@Column(name="chart_id")
	private Integer chartId;

	@Column(name="filename1")
	private String filename1;

	@Column(name="filename2")
	private String filename2;

	public Integer getScribbleid() {
		return scribbleid;
	}

	public void setScribbleid(Integer scribbleid) {
		this.scribbleid = scribbleid;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getChartId() {
		return chartId;
	}

	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}

	public String getFilename1() {
		return filename1;
	}

	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}

	public String getFilename2() {
		return filename2;
	}

	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}
}