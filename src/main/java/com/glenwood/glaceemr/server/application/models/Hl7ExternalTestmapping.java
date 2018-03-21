package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
@Table(name = "hl7_external_testmapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hl7ExternalTestmapping implements Serializable{

	@Column(name="hl7_external_testmapping_id")
	private Integer hl7ExternalTestmappingId;

	@Id
	@Column(name="hl7_external_testmapping_localtestid", nullable=false)
	private Integer hl7ExternalTestmappingLocaltestid;

	@Column(name="hl7_external_testmapping_externaltestid", nullable=false)
	private Integer hl7ExternalTestmappingExternaltestid;

	@Column(name="hl7_external_testmapping_labcompanyid", nullable=false)
	private Integer hl7ExternalTestmappingLabcompanyid;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="hl7_external_testmapping_externaltestid", referencedColumnName="hl7_external_test_id" , insertable=false, updatable=false)
	Hl7ExternalTest hl7ExternalTestTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="hl7_external_testmapping_localtestid", referencedColumnName="lab_description_testid" , insertable=false, updatable=false)
	LabDescription labDescription;
	
	public Integer getHl7ExternalTestmappingId() {
		return hl7ExternalTestmappingId;
	}

	public void setHl7ExternalTestmappingId(Integer hl7ExternalTestmappingId) {
		this.hl7ExternalTestmappingId = hl7ExternalTestmappingId;
	}

	public Integer getHl7ExternalTestmappingLocaltestid() {
		return hl7ExternalTestmappingLocaltestid;
	}

	public void setHl7ExternalTestmappingLocaltestid(
			Integer hl7ExternalTestmappingLocaltestid) {
		this.hl7ExternalTestmappingLocaltestid = hl7ExternalTestmappingLocaltestid;
	}

	public Integer getHl7ExternalTestmappingExternaltestid() {
		return hl7ExternalTestmappingExternaltestid;
	}

	public void setHl7ExternalTestmappingExternaltestid(
			Integer hl7ExternalTestmappingExternaltestid) {
		this.hl7ExternalTestmappingExternaltestid = hl7ExternalTestmappingExternaltestid;
	}

	public Integer getHl7ExternalTestmappingLabcompanyid() {
		return hl7ExternalTestmappingLabcompanyid;
	}

	public void setHl7ExternalTestmappingLabcompanyid(
			Integer hl7ExternalTestmappingLabcompanyid) {
		this.hl7ExternalTestmappingLabcompanyid = hl7ExternalTestmappingLabcompanyid;
	}
	public Hl7ExternalTest getHl7ExternalTestTable() {
		return hl7ExternalTestTable;
	}

	public void setHl7ExternalTestTable(Hl7ExternalTest hl7ExternalTestTable) {
		this.hl7ExternalTestTable = hl7ExternalTestTable;
	}

	public LabDescription getLabDescription() {
		return labDescription;
	}

	public void setLabDescription(LabDescription labDescription) {
		this.labDescription = labDescription;
	}
}