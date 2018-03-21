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
@Table(name = "aftercare_ins_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AftercareInsMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="aftercare_ins_mapping_aftercare_ins_mapping_id_seq")
	@SequenceGenerator(name ="aftercare_ins_mapping_aftercare_ins_mapping_id_seq", sequenceName="aftercare_ins_mapping_aftercare_ins_mapping_id_seq", allocationSize=1)
	@Column(name="aftercare_ins_mapping_id")
	private Integer aftercareInsMappingId;

	@Column(name="aftercare_ins_mapping_code")
	private String aftercareInsMappingCode;

	@Column(name="aftercare_ins_mapping_ins_id")
	private Integer aftercareInsMappingInsId;

	@Column(name="aftercare_ins_mapping_ins_type")
	private Integer aftercareInsMappingInsType;

	@Column(name="aftercare_ins_mapping_coding_system")
	private String aftercareInsMappingCodingSystem;

	public Integer getAftercareInsMappingId() {
		return aftercareInsMappingId;
	}

	public void setAftercareInsMappingId(Integer aftercareInsMappingId) {
		this.aftercareInsMappingId = aftercareInsMappingId;
	}

	public String getAftercareInsMappingCode() {
		return aftercareInsMappingCode;
	}

	public void setAftercareInsMappingCode(String aftercareInsMappingCode) {
		this.aftercareInsMappingCode = aftercareInsMappingCode;
	}

	public Integer getAftercareInsMappingInsId() {
		return aftercareInsMappingInsId;
	}

	public void setAftercareInsMappingInsId(Integer aftercareInsMappingInsId) {
		this.aftercareInsMappingInsId = aftercareInsMappingInsId;
	}

	public Integer getAftercareInsMappingInsType() {
		return aftercareInsMappingInsType;
	}

	public void setAftercareInsMappingInsType(Integer aftercareInsMappingInsType) {
		this.aftercareInsMappingInsType = aftercareInsMappingInsType;
	}

	public String getAftercareInsMappingCodingSystem() {
		return aftercareInsMappingCodingSystem;
	}

	public void setAftercareInsMappingCodingSystem(
			String aftercareInsMappingCodingSystem) {
		this.aftercareInsMappingCodingSystem = aftercareInsMappingCodingSystem;
	}
	
}