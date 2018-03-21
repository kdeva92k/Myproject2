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
@Table(name = "pharmacy_mapping")
public class PharmacyMapping {

	@Id
	@Column(name="pharmacy_mapping_mapid")
	private Integer pharmacyMappingMapid;

	@Column(name="pharmacy_mapping_pharmacyid")
	private Integer pharmacyMappingPharmacyid;

	@Column(name="pharmacy_mapping_patid")
	private Integer pharmacyMappingPatid;

	@Column(name="pharmacy_mapping_usr_grpid")
	private Integer pharmacyMappingUsrGrpid;

	public Integer getPharmacyMappingMapid() {
		return pharmacyMappingMapid;
	}

	public void setPharmacyMappingMapid(Integer pharmacyMappingMapid) {
		this.pharmacyMappingMapid = pharmacyMappingMapid;
	}

	public Integer getPharmacyMappingPharmacyid() {
		return pharmacyMappingPharmacyid;
	}

	public void setPharmacyMappingPharmacyid(Integer pharmacyMappingPharmacyid) {
		this.pharmacyMappingPharmacyid = pharmacyMappingPharmacyid;
	}

	public Integer getPharmacyMappingPatid() {
		return pharmacyMappingPatid;
	}

	public void setPharmacyMappingPatid(Integer pharmacyMappingPatid) {
		this.pharmacyMappingPatid = pharmacyMappingPatid;
	}

	public Integer getPharmacyMappingUsrGrpid() {
		return pharmacyMappingUsrGrpid;
	}

	public void setPharmacyMappingUsrGrpid(Integer pharmacyMappingUsrGrpid) {
		this.pharmacyMappingUsrGrpid = pharmacyMappingUsrGrpid;
	}
	
	
}