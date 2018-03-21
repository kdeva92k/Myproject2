package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_reviewed_details")
public class PatientReviewedDetails {

	@Id
	@SequenceGenerator(name="patient_reviewed_details_patient_reviewed_details_id_seq", sequenceName="patient_reviewed_details_patient_reviewed_details_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="patient_reviewed_details_patient_reviewed_details_id_seq")
	@Column(name="patient_reviewed_details_id")
	private Integer patientReviewedDetailsId;

	@Column(name="patient_reviewed_details_patientid")
	private Integer patientReviewedDetailsPatientid;

	@Column(name="patient_reviewed_details_chartid")
	private Integer patientReviewedDetailsChartid;

	@Column(name="patient_reviewed_details_encounterid")
	private Integer patientReviewedDetailsEncounterid;

	@Column(name="patient_reviewed_details_by")
	private Integer patientReviewedDetailsBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_reviewed_details_on")
	private Timestamp patientReviewedDetailsOn;

	public Integer getPatientReviewedDetailsId() {
		return patientReviewedDetailsId;
	}

	public void setPatientReviewedDetailsId(Integer patientReviewedDetailsId) {
		this.patientReviewedDetailsId = patientReviewedDetailsId;
	}

	public Integer getPatientReviewedDetailsPatientid() {
		return patientReviewedDetailsPatientid;
	}

	public void setPatientReviewedDetailsPatientid(
			Integer patientReviewedDetailsPatientid) {
		this.patientReviewedDetailsPatientid = patientReviewedDetailsPatientid;
	}

	public Integer getPatientReviewedDetailsChartid() {
		return patientReviewedDetailsChartid;
	}

	public void setPatientReviewedDetailsChartid(
			Integer patientReviewedDetailsChartid) {
		this.patientReviewedDetailsChartid = patientReviewedDetailsChartid;
	}

	public Integer getPatientReviewedDetailsEncounterid() {
		return patientReviewedDetailsEncounterid;
	}

	public void setPatientReviewedDetailsEncounterid(
			Integer patientReviewedDetailsEncounterid) {
		this.patientReviewedDetailsEncounterid = patientReviewedDetailsEncounterid;
	}

	public Integer getPatientReviewedDetailsBy() {
		return patientReviewedDetailsBy;
	}

	public void setPatientReviewedDetailsBy(Integer patientReviewedDetailsBy) {
		this.patientReviewedDetailsBy = patientReviewedDetailsBy;
	}

	public Timestamp getPatientReviewedDetailsOn() {
		return patientReviewedDetailsOn;
	}

	public void setPatientReviewedDetailsOn(Timestamp patientReviewedDetailsOn) {
		this.patientReviewedDetailsOn = patientReviewedDetailsOn;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_reviewed_details_by",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	private EmployeeProfile empProfile;

	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}
	
}