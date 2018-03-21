package com.glenwood.glaceemr.server.application.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "vitals_parameter")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VitalsParameter {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vitals_parameter_vitals_parameter_id_seq")
	@SequenceGenerator(name ="vitals_parameter_vitals_parameter_id_seq", sequenceName="vitals_parameter_vitals_parameter_id_seq", allocationSize=1)
	@Column(name = "vitals_parameter_id", nullable = false)
	private Integer vitalsParameterId;

	@Id
	@Column(name = "vitals_parameter_gw_id", length = 20)
	private String vitalsParameterGwId;

	@Column(name = "vitals_parameter_name", length = 50)
	private String vitalsParameterName;

	@Column(name = "vitals_parameter_unit_of_measure_id")
	private Integer vitalsParameterUnitOfMeasureId;

	@Column(name = "vitals_parameter_gender")
	private Short vitalsParameterGender;

	@Column(name = "vitals_parameter_graph_needed")
	private Boolean vitalsParameterGraphNeeded;

	@Column(name = "vitals_parameter_preload_from_last_visit")
	private Boolean vitalsParameterPreloadFromLastVisit;

	@Column(name = "vitals_parameter_display_order")
	private Integer vitalsParameterDisplayOrder;

	@Column(name = "vitals_parameter_isactive")
	private Boolean vitalsParameterIsactive;

	@Column(name = "vitals_parameter_group")
	private Integer vitalsParameterGroup;

	@Column(name = "vitals_parameter_display_unit")
	private Integer vitalsParameterDisplayUnit;

	@Column(name = "vitals_parameter_element_order")
	private Integer vitalsParameterElementOrder;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)  //This annotation is added because vitals_parameter_unit_of_measure_id has entries as -2 which are not present in units_of_measure
	@JoinColumn(name = "vitals_parameter_unit_of_measure_id", referencedColumnName = "units_of_measure_id", insertable = false, updatable = false)
	private UnitsOfMeasure unitsOfMeasureTable;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "vitals_parameter_group", referencedColumnName = "vital_group_id", insertable = false, updatable = false)
	private VitalGroup vitalGroup;

	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "vitals_parameter_gw_id",referencedColumnName="vitals_parameter_condition_gw_id",insertable = false, updatable = false)
	private VitalsParameterCondition vitalsParameterCondition;
	
	@OneToMany(mappedBy="cnmCodeSystem",fetch=FetchType.LAZY)
	List<CNMCodeSystem> cnmCodeSystem;	
	
	public VitalGroup getVitalGroup() {
		return vitalGroup;
	}

	public void setVitalGroup(VitalGroup vitalGroup) {
		this.vitalGroup = vitalGroup;
	}

	public VitalsParameterCondition getVitalsParameterCondition() {
		return vitalsParameterCondition;
	}

	public void setVitalsParameterCondition(
			VitalsParameterCondition vitalsParameterCondition) {
		this.vitalsParameterCondition = vitalsParameterCondition;
	}

	public Integer getVitalsParameterId() {
		return vitalsParameterId;
	}

	public void setVitalsParameterId(Integer vitalsParameterId) {
		this.vitalsParameterId = vitalsParameterId;
	}

	public String getVitalsParameterGwId() {
		return vitalsParameterGwId;
	}

	public void setVitalsParameterGwId(String vitalsParameterGwId) {
		this.vitalsParameterGwId = vitalsParameterGwId;
	}

	public String getVitalsParameterName() {
		return vitalsParameterName;
	}

	public void setVitalsParameterName(String vitalsParameterName) {
		this.vitalsParameterName = vitalsParameterName;
	}

	public Integer getVitalsParameterUnitOfMeasureId() {
		return vitalsParameterUnitOfMeasureId;
	}

	public void setVitalsParameterUnitOfMeasureId(
			Integer vitalsParameterUnitOfMeasureId) {
		this.vitalsParameterUnitOfMeasureId = vitalsParameterUnitOfMeasureId;
	}

	public Short getVitalsParameterGender() {
		return vitalsParameterGender;
	}

	public void setVitalsParameterGender(Short vitalsParameterGender) {
		this.vitalsParameterGender = vitalsParameterGender;
	}

	public Boolean getVitalsParameterGraphNeeded() {
		return vitalsParameterGraphNeeded;
	}

	public void setVitalsParameterGraphNeeded(Boolean vitalsParameterGraphNeeded) {
		this.vitalsParameterGraphNeeded = vitalsParameterGraphNeeded;
	}

	public Boolean getVitalsParameterPreloadFromLastVisit() {
		return vitalsParameterPreloadFromLastVisit;
	}

	public void setVitalsParameterPreloadFromLastVisit(
			Boolean vitalsParameterPreloadFromLastVisit) {
		this.vitalsParameterPreloadFromLastVisit = vitalsParameterPreloadFromLastVisit;
	}

	public Integer getVitalsParameterDisplayOrder() {
		return vitalsParameterDisplayOrder;
	}

	public void setVitalsParameterDisplayOrder(Integer vitalsParameterDisplayOrder) {
		this.vitalsParameterDisplayOrder = vitalsParameterDisplayOrder;
	}

	public Boolean getVitalsParameterIsactive() {
		return vitalsParameterIsactive;
	}

	public void setVitalsParameterIsactive(Boolean vitalsParameterIsactive) {
		this.vitalsParameterIsactive = vitalsParameterIsactive;
	}

	public Integer getVitalsParameterGroup() {
		return vitalsParameterGroup;
	}

	public void setVitalsParameterGroup(Integer vitalsParameterGroup) {
		this.vitalsParameterGroup = vitalsParameterGroup;
	}

	public Integer getVitalsParameterDisplayUnit() {
		return vitalsParameterDisplayUnit;
	}

	public void setVitalsParameterDisplayUnit(Integer vitalsParameterDisplayUnit) {
		this.vitalsParameterDisplayUnit = vitalsParameterDisplayUnit;
	}

	public Integer getVitalsParameterElementOrder() {
		return vitalsParameterElementOrder;
	}

	public void setVitalsParameterElementOrder(Integer vitalsParameterElementOrder) {
		this.vitalsParameterElementOrder = vitalsParameterElementOrder;
	}

	public UnitsOfMeasure getUnitsOfMeasureTable() {
		return unitsOfMeasureTable;
	}

	public void setUnitsOfMeasureTable(UnitsOfMeasure unitsOfMeasureTable) {
		this.unitsOfMeasureTable = unitsOfMeasureTable;
	}
}