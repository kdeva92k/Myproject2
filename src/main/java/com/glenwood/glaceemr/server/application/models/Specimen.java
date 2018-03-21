package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;


@Entity
@Table(name = "specimen")
public class Specimen {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="specimen_specimen_id_seq")
	@SequenceGenerator(name ="specimen_specimen_id_seq", sequenceName="specimen_specimen_id_seq", allocationSize=1)
	@Column(name="specimen_id")
	private Integer specimenId;

	@Column(name="specimen_source")
	private String specimenSource;

	@Column(name="specimen_coll_vol")
	private String specimenCollVol;

	@Column(name="specimen_coll_vol_unit")
	private String specimenCollVolUnit;

	@Column(name="specimen_condition")
	private String specimenCondition;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="specimen_date")
	private Timestamp specimenDate;

	public Specimen(){

	}
	public Specimen(String specimenSource,String specimenCondition,Timestamp specimenDate){
		this.specimenSource=specimenSource;
		this.specimenCondition=specimenCondition;
		this.specimenDate=specimenDate;
	}

	public Integer getSpecimenId() {
		return specimenId;
	}

	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	public String getSpecimenSource() {
		return specimenSource;
	}

	public void setSpecimenSource(String specimenSource) {
		this.specimenSource = specimenSource;
	}

	public String getSpecimenCollVol() {
		return specimenCollVol;
	}

	public void setSpecimenCollVol(String specimenCollVol) {
		this.specimenCollVol = specimenCollVol;
	}

	public String getSpecimenCollVolUnit() {
		return specimenCollVolUnit;
	}

	public void setSpecimenCollVolUnit(String specimenCollVolUnit) {
		this.specimenCollVolUnit = specimenCollVolUnit;
	}

	public String getSpecimenCondition() {
		return specimenCondition;
	}

	public void setSpecimenCondition(String specimenCondition) {
		this.specimenCondition = specimenCondition;
	}

	public Timestamp getSpecimenDate() {
		return specimenDate;
	}

	public void setSpecimenDate(Timestamp specimenDate) {
		this.specimenDate = specimenDate;
	}
}