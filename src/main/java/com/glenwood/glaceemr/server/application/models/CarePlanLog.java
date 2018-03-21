package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="careplan_log")
public class CarePlanLog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="careplan_log_id_seq")
	@SequenceGenerator(name ="careplan_log_id_seq", sequenceName="careplan_log_id_seq", allocationSize=1)
	@Column(name="careplan_log_id")
	private Integer carePlanLogId;
	
	@Column(name="careplan_log_patient_id")
	private Integer carePlanlogPatientId;
	
	@Column(name="careplan_log_reviewed_by")
	private Integer carePlanlogReviewedBy;
	
	@Column(name="careplan_log_reviewed_on")
	private Timestamp carePlanlogReviewedOn;

	@Column(name="careplan_log_reviewed")
	private boolean carePlanlogReviewed;
	
	
	public Integer getCarePlanLogId() {
		return carePlanLogId;
	}

	public void setCarePlanLogId(Integer carePlanLogId) {
		this.carePlanLogId = carePlanLogId;
	}

	public Integer getCarePlanlogPatientId() {
		return carePlanlogPatientId;
	}

	public void setCarePlanlogPatientId(Integer carePlanlogPatientId) {
		this.carePlanlogPatientId = carePlanlogPatientId;
	}

	public Integer getCarePlanlogReviewedBy() {
		return carePlanlogReviewedBy;
	}

	public void setCarePlanlogReviewedBy(Integer carePlanlogReviewedBy) {
		this.carePlanlogReviewedBy = carePlanlogReviewedBy;
	}

	public Timestamp getCarePlanlogReviewedOn() {
		return carePlanlogReviewedOn;
	}

	public void setCarePlanlogReviewedOn(Timestamp carePlanlogReviewedOn) {
		this.carePlanlogReviewedOn = carePlanlogReviewedOn;
	}

	public boolean isCarePlanlogReviewed() {
		return carePlanlogReviewed;
	}

	public void setCarePlanlogReviewed(boolean carePlanlogReviewed) {
		this.carePlanlogReviewed = carePlanlogReviewed;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_log_reviewed_by", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfile;
	
	
}


