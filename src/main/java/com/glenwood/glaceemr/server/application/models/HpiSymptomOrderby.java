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
@Table(name = "hpi_symptom_orderby")
public class HpiSymptomOrderby {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hpi_symptom_orderby_hpi_symptom_orderby_id_seq")
	@SequenceGenerator(name ="hpi_symptom_orderby_hpi_symptom_orderby_id_seq", sequenceName="hpi_symptom_orderby_hpi_symptom_orderby_id_seq", allocationSize=1)
	@Column(name="hpi_symptom_orderby_id")
	private Integer hpiSymptomOrderbyId;

	@Column(name="hpi_symptom_orderby_typeid")
	private Integer hpiSymptomOrderbyTypeid;

	@Column(name="hpi_symptom_orderby_typename")
	private String hpiSymptomOrderbyTypename;

	@Column(name="hpi_symptom_orderby_isactive")
	private Boolean hpiSymptomOrderbyIsactive;
	
	public Integer getHpiSymptomOrderbyId() {
		return hpiSymptomOrderbyId;
	}

	public void setHpiSymptomOrderbyId(Integer hpiSymptomOrderbyId) {
		this.hpiSymptomOrderbyId = hpiSymptomOrderbyId;
	}

	public Integer getHpiSymptomOrderbyTypeid() {
		return hpiSymptomOrderbyTypeid;
	}

	public void setHpiSymptomOrderbyTypeid(Integer hpiSymptomOrderbyTypeid) {
		this.hpiSymptomOrderbyTypeid = hpiSymptomOrderbyTypeid;
	}

	public String getHpiSymptomOrderbyTypename() {
		return hpiSymptomOrderbyTypename;
	}

	public void setHpiSymptomOrderbyTypename(String hpiSymptomOrderbyTypename) {
		this.hpiSymptomOrderbyTypename = hpiSymptomOrderbyTypename;
	}

	public Boolean getHpiSymptomOrderbyIsactive() {
		return hpiSymptomOrderbyIsactive;
	}

	public void setHpiSymptomOrderbyIsactive(Boolean hpiSymptomOrderbyIsactive) {
		this.hpiSymptomOrderbyIsactive = hpiSymptomOrderbyIsactive;
	}

}