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

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "focal_shortcut_elements")
public class FocalShortcutElements {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="focal_shortcut_elements_focal_shortcut_elements_id_seq")
	@SequenceGenerator(name ="focal_shortcut_elements_focal_shortcut_elements_id_seq", sequenceName="focal_shortcut_elements_focal_shortcut_elements_id_seq", allocationSize=1)
	@Column(name="focal_shortcut_elements_id")
	private Integer focalShortcutElementsId;

	@Column(name="focal_shortcut_elements_gwid")
	private String focalShortcutElementsGwid;

	@Column(name="focal_shortcut_elements_value")
	private String focalShortcutElementsValue;

	@Column(name="focal_shortcut_elements_isactive")
	private Boolean focalShortcutElementsIsactive;

	@Column(name="focal_shortcut_elements_mapid")
	private Integer focalShortcutElementsMapid;
	
	@Formula("concat('CC', focal_shortcut_elements_value)")
	 private String concated;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="focal_shortcut_elements_mapid",referencedColumnName="focal_shortcut_id",insertable=false,updatable=false)
    FocalShortcut focalShortcut;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="focal_shortcut_elements_gwid",referencedColumnName="clinical_elements_gwid",insertable=false,updatable=false)
    ClinicalElements clinicalElements;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula = @JoinFormula(value = "substr(focal_shortcut_elements_gwid,6,3)", referencedColumnName = "plan_type_gwid")) })
    PlanType planType;

	public Integer getFocalShortcutElementsId() {
		return focalShortcutElementsId;
	}

	public void setFocalShortcutElementsId(Integer focalShortcutElementsId) {
		this.focalShortcutElementsId = focalShortcutElementsId;
	}

	public String getFocalShortcutElementsGwid() {
		return focalShortcutElementsGwid;
	}

	public void setFocalShortcutElementsGwid(String focalShortcutElementsGwid) {
		this.focalShortcutElementsGwid = focalShortcutElementsGwid;
	}

	public String getFocalShortcutElementsValue() {
		return focalShortcutElementsValue;
	}

	public void setFocalShortcutElementsValue(String focalShortcutElementsValue) {
		this.focalShortcutElementsValue = focalShortcutElementsValue;
	}

	public Boolean getFocalShortcutElementsIsactive() {
		return focalShortcutElementsIsactive;
	}

	public void setFocalShortcutElementsIsactive(
			Boolean focalShortcutElementsIsactive) {
		this.focalShortcutElementsIsactive = focalShortcutElementsIsactive;
	}

	public Integer getFocalShortcutElementsMapid() {
		return focalShortcutElementsMapid;
	}

	public void setFocalShortcutElementsMapid(Integer focalShortcutElementsMapid) {
		this.focalShortcutElementsMapid = focalShortcutElementsMapid;
	}

	public FocalShortcut getFocalShortcut() {
		return focalShortcut;
	}

	public void setFocalShortcut(FocalShortcut focalShortcut) {
		this.focalShortcut = focalShortcut;
	}

	public ClinicalElements getClinicalElements() {
		return clinicalElements;
	}

	public void setClinicalElements(ClinicalElements clinicalElements) {
		this.clinicalElements = clinicalElements;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}
	
}