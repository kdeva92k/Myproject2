package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_portal_shareddocs")
public class PatientPortalSharedDocs {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_portal_shareddocs_patient_portal_shareddocs_id_seq")
	@SequenceGenerator(name ="patient_portal_shareddocs_patient_portal_shareddocs_id_seq", sequenceName="patient_portal_shareddocs_patient_portal_shareddocs_id_seq", allocationSize=1)
	@Column(name="patient_portal_shareddocs_id")
	private Integer patientPortalShareddocsId;

	@Column(name="patient_portal_shareddocs_filedetid")
	private Integer patientPortalShareddocsFiledetid;

	@Column(name="patient_portal_shareddocs_sharedby")
	private Integer patientPortalShareddocsSharedby;

	@Column(name="patient_portal_shareddocs_patientid")
	private Integer patientPortalShareddocsPatientid;
	
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="patient_portal_shareddocs_sharedon")
	private Timestamp patientPortalShareddocsSharedon;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER )
	@JoinColumn(name="patient_portal_shareddocs_filedetid", referencedColumnName="filedetails_id", insertable=false, updatable=false)
	@JsonManagedReference
	FileDetails fileDetails;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="patient_portal_shareddocs_sharedby", referencedColumnName="emp_profile_empid", insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile empProfileSharedUser;
	
	public Timestamp getPatientPortalShareddocsSharedon() {
		return patientPortalShareddocsSharedon;
	}

	public FileDetails getFileDetails() {
		return fileDetails;
	}

	public EmployeeProfile getEmpProfileSharedUser() {
		return empProfileSharedUser;
	}

	public void setPatientPortalShareddocsSharedon(
			Timestamp patientPortalShareddocsSharedon) {
		this.patientPortalShareddocsSharedon = patientPortalShareddocsSharedon;
	}

	public void setFileDetails(FileDetails fileDetails) {
		this.fileDetails = fileDetails;
	}

	public void setEmpProfileSharedUser(EmployeeProfile empProfileSharedUser) {
		this.empProfileSharedUser = empProfileSharedUser;
	}

	public Integer getPatientPortalShareddocsId() {
		return patientPortalShareddocsId;
	}

	public Integer getPatientPortalShareddocsFiledetid() {
		return patientPortalShareddocsFiledetid;
	}

	public Integer getPatientPortalShareddocsSharedby() {
		return patientPortalShareddocsSharedby;
	}

	public Integer getPatientPortalShareddocsPatientid() {
		return patientPortalShareddocsPatientid;
	}

	public void setPatientPortalShareddocsId(Integer patientPortalShareddocsId) {
		this.patientPortalShareddocsId = patientPortalShareddocsId;
	}

	public void setPatientPortalShareddocsFiledetid(
			Integer patientPortalShareddocsFiledetid) {
		this.patientPortalShareddocsFiledetid = patientPortalShareddocsFiledetid;
	}

	public void setPatientPortalShareddocsSharedby(
			Integer patientPortalShareddocsSharedby) {
		this.patientPortalShareddocsSharedby = patientPortalShareddocsSharedby;
	}

	public void setPatientPortalShareddocsPatientid(
			Integer patientPortalShareddocsPatientid) {
		this.patientPortalShareddocsPatientid = patientPortalShareddocsPatientid;
	}

	
	
}