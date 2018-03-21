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
@Table(name = "plan_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="plan_mapping_plan_mapping_id_seq")
	@SequenceGenerator(name ="plan_mapping_plan_mapping_id_seq", sequenceName="plan_mapping_plan_mapping_id_seq", allocationSize=1)
	@Column(name="plan_mapping_id")
	private Integer planMappingId;

	@Column(name="plan_mapping_instructionid")
	private Integer planMappingInstructionid;

	@Column(name="plan_mapping_type")
	private Integer planMappingType;

	@Column(name="plan_mapping_code")
	private String planMappingCode;
	
	@Column(name="plan_mapping_codingsystem")
	private String planMappingCodingsystem;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plan_mapping_instructionid", referencedColumnName="plan_instruction_id", insertable=false, updatable=false)
	PlanInstruction planInstruction;

	public Integer getPlanMappingId() {
		return planMappingId;
	}

	public void setPlanMappingId(Integer planMappingId) {
		this.planMappingId = planMappingId;
	}

	public Integer getPlanMappingInstructionid() {
		return planMappingInstructionid;
	}

	public void setPlanMappingInstructionid(Integer planMappingInstructionid) {
		this.planMappingInstructionid = planMappingInstructionid;
	}

	public Integer getPlanMappingType() {
		return planMappingType;
	}

	public void setPlanMappingType(Integer planMappingType) {
		this.planMappingType = planMappingType;
	}

	public String getPlanMappingCode() {
		return planMappingCode;
	}

	public void setPlanMappingCode(String planMappingCode) {
		this.planMappingCode = planMappingCode;
	}

	public PlanInstruction getPlanInstruction() {
		return planInstruction;
	}

	public void setPlanInstruction(PlanInstruction planInstruction) {
		this.planInstruction = planInstruction;
	}

	public String getPlanMappingCodingsystem() {
		return planMappingCodingsystem;
	}

	public void setPlanMappingCodingsystem(String planMappingCodingsystem) {
		this.planMappingCodingsystem = planMappingCodingsystem;
	}
	
}