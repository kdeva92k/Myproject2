package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "plan_instruction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanInstruction implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="plan_instruction_plan_instruction_id_seq")
	@SequenceGenerator(name ="plan_instruction_plan_instruction_id_seq", sequenceName="plan_instruction_plan_instruction_id_seq", allocationSize=1)
	@Column(name="plan_instruction_id")
	private Integer planInstructionId;

	@Column(name="plan_instruction_name")
	private String planInstructionName;

	@Column(name="plan_instruction_description")
	private String planInstructionDescription;

	@Column(name="plan_instruction_gwid")
	private String planInstructionGwid;

	@Column(name="plan_instruction_plantypeid")
	private Integer planInstructionPlantypeid;

	@Column(name="plan_instruction_isactive")
	private Boolean planInstructionIsactive;
	
	@Column(name="plan_instruction_orderby")
	private Integer planInstructionOrderby;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="plan_instruction_plantypeid", referencedColumnName="plan_type_id", insertable=false, updatable=false)
	private PlanType planType;
	
	@OneToMany(mappedBy="planInstruction", fetch=FetchType.LAZY)
	@JsonBackReference
	List<PatientClinicalElements> patientPlanClinicalElements;

	@OneToMany(mappedBy="planInstruction", fetch=FetchType.LAZY)
	List<PlanMapping> planMappings;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="plan_instruction_gwid", referencedColumnName="clinical_elements_gwid", insertable=false, updatable=false)
	ClinicalElements clinicalElements; 
	
	@OneToMany(mappedBy="planInstruction", fetch=FetchType.LAZY)
	@JsonManagedReference	
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping;
	
	public Integer getPlanInstructionId() {
		return planInstructionId;
	}

	public void setPlanInstructionId(Integer planInstructionId) {
		this.planInstructionId = planInstructionId;
	}

	public String getPlanInstructionName() {
		return planInstructionName;
	}

	public void setPlanInstructionName(String planInstructionName) {
		this.planInstructionName = planInstructionName;
	}

	public String getPlanInstructionDescription() {
		return planInstructionDescription;
	}

	public void setPlanInstructionDescription(String planInstructionDescription) {
		this.planInstructionDescription = planInstructionDescription;
	}

	public String getPlanInstructionGwid() {
		return planInstructionGwid;
	}

	public void setPlanInstructionGwid(String planInstructionGwid) {
		this.planInstructionGwid = planInstructionGwid;
	}

	public Integer getPlanInstructionPlantypeid() {
		return planInstructionPlantypeid;
	}

	public void setPlanInstructionPlantypeid(Integer planInstructionPlantypeid) {
		this.planInstructionPlantypeid = planInstructionPlantypeid;
	}

	public Boolean getPlanInstructionIsactive() {
		return planInstructionIsactive;
	}

	public void setPlanInstructionIsactive(Boolean planInstructionIsactive) {
		this.planInstructionIsactive = planInstructionIsactive;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public List<PatientClinicalElements> getPatientPlanClinicalElements() {
		return patientPlanClinicalElements;
	}

	public void setPatientPlanClinicalElements(
			List<PatientClinicalElements> patientPlanClinicalElements) {
		this.patientPlanClinicalElements = patientPlanClinicalElements;
	}

	public List<PlanMapping> getPlanMappings() {
		return planMappings;
	}

	public void setPlanMappings(List<PlanMapping> planMappings) {
		this.planMappings = planMappings;
	}

	public Integer getPlanInstructionOrderby() {
		return planInstructionOrderby;
	}

	public void setPlanInstructionOrderby(Integer planInstructionOrderby) {
		this.planInstructionOrderby = planInstructionOrderby;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ClinicalElements getClinicalElements() {
		return clinicalElements;
	}

	public void setClinicalElements(ClinicalElements clinicalElements) {
		this.clinicalElements = clinicalElements;
	}

	public List<ClinicalElementTemplateMapping> getClinicalElementTemplateMapping() {
		return clinicalElementTemplateMapping;
	}

	public void setClinicalElementTemplateMapping(
			List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping) {
		this.clinicalElementTemplateMapping = clinicalElementTemplateMapping;
	}

}