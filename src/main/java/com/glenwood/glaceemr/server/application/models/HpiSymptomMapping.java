package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "hpi_symptom_mapping")
public class HpiSymptomMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hpi_symptom_mapping_hpi_symptom_mapping_id_seq")
	@SequenceGenerator(name ="hpi_symptom_mapping_hpi_symptom_mapping_id_seq", sequenceName="hpi_symptom_mapping_hpi_symptom_mapping_id_seq", allocationSize=1)
	@Column(name="hpi_symptom_mapping_id")
	private Integer hpiSymptomMappingId;

	@Column(name="hpi_symptom_mapping_name")
	private String hpiSymptomMappingName;

	@Column(name="hpi_symptom_mapping_gwid")
	private String hpiSymptomMappingGwid;

	@Column(name="hpi_symptom_mapping_isactive")
	private Boolean hpiSymptomMappingIsactive;

	public Integer getHpiSymptomMappingId() {
		return hpiSymptomMappingId;
	}

	public void setHpiSymptomMappingId(Integer hpiSymptomMappingId) {
		this.hpiSymptomMappingId = hpiSymptomMappingId;
	}

	public String getHpiSymptomMappingName() {
		return hpiSymptomMappingName;
	}

	public void setHpiSymptomMappingName(String hpiSymptomMappingName) {
		this.hpiSymptomMappingName = hpiSymptomMappingName;
	}

	public String getHpiSymptomMappingGwid() {
		return hpiSymptomMappingGwid;
	}

	public void setHpiSymptomMappingGwid(String hpiSymptomMappingGwid) {
		this.hpiSymptomMappingGwid = hpiSymptomMappingGwid;
	}

	public Boolean getHpiSymptomMappingIsactive() {
		return hpiSymptomMappingIsactive;
	}

	public void setHpiSymptomMappingIsactive(Boolean hpiSymptomMappingIsactive) {
		this.hpiSymptomMappingIsactive = hpiSymptomMappingIsactive;
	}
	
	
}