package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "hpi_symptom_qualifier")
public class HpiSymptomQualifier {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hpi_symptom_qualifier_hpi_symptom_qualifier_id_seq")
	@SequenceGenerator(name ="hpi_symptom_qualifier_hpi_symptom_qualifier_id_seq", sequenceName="hpi_symptom_qualifier_hpi_symptom_qualifier_id_seq", allocationSize=1)
	@Column(name="hpi_symptom_qualifier_id")
	private Integer hpiSymptomQualifierId;

	@Column(name="hpi_symptom_qualifier_gwid")
	private String hpiSymptomQualifierGwid;

	@Column(name="hpi_symptom_qualifier_symptom_id")
	private Integer hpiSymptomQualifierSymptomId;

	@Column(name="hpi_symptom_qualifier_name")
	private String hpiSymptomQualifierName;

	@Column(name="hpi_symptom_qualifier_orderby")
	private Integer hpiSymptomQualifierOrderby;

	@Column(name="hpi_symptom_qualifier_isactive")
	private Boolean hpiSymptomQualifierIsactive;

	@Column(name="hpi_symptom_qualifier_eandm")
	private String hpiSymptomQualifierEandm;

	@Column(name="hpi_symptom_qualifier_printtext")
	private String hpiSymptomQualifierPrinttext;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "hpi_symptom_qualifier_symptom_id", referencedColumnName = "hpi_symptom_id", insertable = false, updatable = false)
	HpiSymptom hpiSymptom;*/

	public Integer getHpiSymptomQualifierId() {
		return hpiSymptomQualifierId;
	}

	public void setHpiSymptomQualifierId(Integer hpiSymptomQualifierId) {
		this.hpiSymptomQualifierId = hpiSymptomQualifierId;
	}

	public String getHpiSymptomQualifierGwid() {
		return hpiSymptomQualifierGwid;
	}

	public void setHpiSymptomQualifierGwid(String hpiSymptomQualifierGwid) {
		this.hpiSymptomQualifierGwid = hpiSymptomQualifierGwid;
	}

	public Integer getHpiSymptomQualifierSymptomId() {
		return hpiSymptomQualifierSymptomId;
	}

	public void setHpiSymptomQualifierSymptomId(Integer hpiSymptomQualifierSymptomId) {
		this.hpiSymptomQualifierSymptomId = hpiSymptomQualifierSymptomId;
	}

	public String getHpiSymptomQualifierName() {
		return hpiSymptomQualifierName;
	}

	public void setHpiSymptomQualifierName(String hpiSymptomQualifierName) {
		this.hpiSymptomQualifierName = hpiSymptomQualifierName;
	}

	public Integer getHpiSymptomQualifierOrderby() {
		return hpiSymptomQualifierOrderby;
	}

	public void setHpiSymptomQualifierOrderby(Integer hpiSymptomQualifierOrderby) {
		this.hpiSymptomQualifierOrderby = hpiSymptomQualifierOrderby;
	}

	public Boolean getHpiSymptomQualifierIsactive() {
		return hpiSymptomQualifierIsactive;
	}

	public void setHpiSymptomQualifierIsactive(Boolean hpiSymptomQualifierIsactive) {
		this.hpiSymptomQualifierIsactive = hpiSymptomQualifierIsactive;
	}

	public String getHpiSymptomQualifierEandm() {
		return hpiSymptomQualifierEandm;
	}

	public void setHpiSymptomQualifierEandm(String hpiSymptomQualifierEandm) {
		this.hpiSymptomQualifierEandm = hpiSymptomQualifierEandm;
	}

	public String getHpiSymptomQualifierPrinttext() {
		return hpiSymptomQualifierPrinttext;
	}

	public void setHpiSymptomQualifierPrinttext(String hpiSymptomQualifierPrinttext) {
		this.hpiSymptomQualifierPrinttext = hpiSymptomQualifierPrinttext;
	}

	/*public HpiSymptom getHpiSymptom() {
		return hpiSymptom;
	}

	public void setHpiSymptom(HpiSymptom hpiSymptom) {
		this.hpiSymptom = hpiSymptom;
	}*/
	
}