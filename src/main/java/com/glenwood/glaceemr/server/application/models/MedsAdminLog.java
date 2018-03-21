package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "meds_admin_log")
public class MedsAdminLog {

	@Id
	@Column(name="meds_admin_log_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="meds_admin_log_id")
	@SequenceGenerator(name="meds_admin_log_id",sequenceName="meds_admin_log_id",allocationSize=1)
	private Integer medsAdminLogId;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="meds_admin_log_administered_by",referencedColumnName="emp_profile_empid",insertable=false, updatable=false)
	@JsonManagedReference
	EmployeeProfile empprofile;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="meds_admin_log_plan_id",referencedColumnName="meds_admin_plan_id",insertable=false, updatable=false)
	@JsonBackReference
	MedsAdminPlan medsAdminPlan;
	
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="meds_admin_log_admin_category",referencedColumnName="meds_admin_category_id",insertable=false, updatable=false)
	@JsonManagedReference
	MedsAdminCategory medsAdminCategory;
	
	@Column(name="meds_admin_log_route_id")
	private Integer medsAdminLogRouteId;
	
	@Column(name="meds_admin_log_patient_id")
	private Integer medsAdminLogPatientId;

	@Column(name="meds_admin_log_actual_plan_date")
	private String medsAdminLogActualPlanDate;
	
	@Column(name="meds_admin_log_administration_date")
	private String medsAdminLogAdministrationDate;

	@Column(name="meds_admin_log_administered_by")
	private Integer medsAdminLogAdministeredBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="meds_admin_log_modified_date")
	private Timestamp medsAdminLogModifiedDate;

	@Column(name="meds_admin_log_modified_by")
	private Integer medsAdminLogModifiedBy;

	@Column(name="meds_admin_log_plan_id")
	private Integer medsAdminLogPlanId;

	@Column(name="meds_admin_log_is_deleted")
	private Boolean medsAdminLogIsDeleted;

	@Column(name="meds_admin_log_deleted_by")
	private Integer medsAdminLogDeletedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="meds_admin_log_deleted_on")
	private Timestamp medsAdminLogDeletedOn;
	
	@Column(name="meds_admin_log_admin_category")
	private Integer medsAdminLogAdminCategory;
	
	@Column(name="meds_admin_log_admin_notes")
	private String medsAdminLogAdminNotes;
	
	public Integer getMedsAdminLogId() {
		return medsAdminLogId;
	}

	public void setMedsAdminLogId(Integer medsAdminLogId) {
		this.medsAdminLogId = medsAdminLogId;
	}

	public Integer getMedsAdminLogRouteId() {
		return medsAdminLogRouteId;
	}

	public void setMedsAdminLogRouteId(Integer medsAdminLogRouteId) {
		this.medsAdminLogRouteId = medsAdminLogRouteId;
	}

	public Integer getMedsAdminLogPatientId() {
		return medsAdminLogPatientId;
	}

	public void setMedsAdminLogPatientId(Integer medsAdminLogPatientId) {
		this.medsAdminLogPatientId = medsAdminLogPatientId;
	}

	public String getMedsAdminLogActualPlanDate() {
		return medsAdminLogActualPlanDate;
	}

	public void setMedsAdminLogActualPlanDate(String medsAdminLogActualPlanDate) {
		this.medsAdminLogActualPlanDate = medsAdminLogActualPlanDate;
	}

	public String getMedsAdminLogAdministrationDate() {
		return medsAdminLogAdministrationDate;
	}

	public void setMedsAdminLogAdministrationDate(
			String medsAdminLogAdministrationDate) {
		this.medsAdminLogAdministrationDate = medsAdminLogAdministrationDate;
	}

	public Integer getMedsAdminLogAdministeredBy() {
		return medsAdminLogAdministeredBy;
	}

	public void setMedsAdminLogAdministeredBy(Integer medsAdminLogAdministeredBy) {
		this.medsAdminLogAdministeredBy = medsAdminLogAdministeredBy;
	}

	public Timestamp getMedsAdminLogModifiedDate() {
		return medsAdminLogModifiedDate;
	}

	public void setMedsAdminLogModifiedDate(Timestamp medsAdminLogModifiedDate) {
		this.medsAdminLogModifiedDate = medsAdminLogModifiedDate;
	}

	public Integer getMedsAdminLogModifiedBy() {
		return medsAdminLogModifiedBy;
	}

	public void setMedsAdminLogModifiedBy(Integer medsAdminLogModifiedBy) {
		this.medsAdminLogModifiedBy = medsAdminLogModifiedBy;
	}

	public Integer getMedsAdminLogPlanId() {
		return medsAdminLogPlanId;
	}

	public void setMedsAdminLogPlanId(Integer medsAdminLogPlanId) {
		this.medsAdminLogPlanId = medsAdminLogPlanId;
	}

	public Boolean getMedsAdminLogIsDeleted() {
		return medsAdminLogIsDeleted;
	}

	public void setMedsAdminLogIsDeleted(Boolean medsAdminLogIsDeleted) {
		this.medsAdminLogIsDeleted = medsAdminLogIsDeleted;
	}

	public Integer getMedsAdminLogDeletedBy() {
		return medsAdminLogDeletedBy;
	}

	public void setMedsAdminLogDeletedBy(Integer medsAdminLogDeletedBy) {
		this.medsAdminLogDeletedBy = medsAdminLogDeletedBy;
	}

	public Timestamp getMedsAdminLogDeletedOn() {
		return medsAdminLogDeletedOn;
	}

	public void setMedsAdminLogDeletedOn(Timestamp medsAdminLogDeletedOn) {
		this.medsAdminLogDeletedOn = medsAdminLogDeletedOn;
	}

	public Integer getMedsAdminLogAdminCategory() {
		return medsAdminLogAdminCategory;
	}

	public void setMedsAdminLogAdminCategory(Integer medsAdminLogAdminCategory) {
		this.medsAdminLogAdminCategory = medsAdminLogAdminCategory;
	}

	public EmployeeProfile getEmpprofile() {
		return empprofile;
	}

	public void setEmpprofile(EmployeeProfile empprofile) {
		this.empprofile = empprofile;
	}

	public MedsAdminPlan getMedsAdminPlan() {
		return medsAdminPlan;
	}

	public void setMedsAdminPlan(MedsAdminPlan medsAdminPlan) {
		this.medsAdminPlan = medsAdminPlan;
	}

	public MedsAdminCategory getMedsAdminCategory() {
		return medsAdminCategory;
	}

	public void setMedsAdminCategory(MedsAdminCategory medsAdminCategory) {
		this.medsAdminCategory = medsAdminCategory;
	}

	public String getMedsAdminLogAdminNotes() {
		return medsAdminLogAdminNotes;
	}

	public void setMedsAdminLogAdminNotes(String medsAdminLogAdminNotes) {
		this.medsAdminLogAdminNotes = medsAdminLogAdminNotes;
	}
	
}