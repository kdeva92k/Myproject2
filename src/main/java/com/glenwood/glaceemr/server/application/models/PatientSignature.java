package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_signature")
public class PatientSignature {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_signature_id_seq")
	@SequenceGenerator(name ="patient_signature_id_seq", sequenceName="patient_signature_id_seq", allocationSize=1)
	@Column(name="id")
	private Integer id;

	@Column(name="patientid")
	private Integer patientid;

	@Column(name="chartid")
	private Integer chartid;

	@Column(name="encounterid")
	private Integer encounterid;

	@Column(name="signaturefilename")
	private String signaturefilename;

	@Column(name="binaryfilename")
	private String binaryfilename;

	@Column(name="signature_entity_type")
	private Integer signatureEntityType;

	@Column(name="createdby")
	private Integer createdby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="createdon")
	private Timestamp createdon;

	@Column(name="encryptionkeytext")
	private String encryptionkeytext;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "patientid", referencedColumnName = "patient_registration_id", insertable = false, updatable = false)
	private PatientRegistration  patientregistration;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public Integer getChartid() {
		return chartid;
	}

	public void setChartid(Integer chartid) {
		this.chartid = chartid;
	}

	public Integer getEncounterid() {
		return encounterid;
	}

	public void setEncounterid(Integer encounterid) {
		this.encounterid = encounterid;
	}

	public String getSignaturefilename() {
		return signaturefilename;
	}

	public void setSignaturefilename(String signaturefilename) {
		this.signaturefilename = signaturefilename;
	}

	public String getBinaryfilename() {
		return binaryfilename;
	}

	public void setBinaryfilename(String binaryfilename) {
		this.binaryfilename = binaryfilename;
	}

	public Integer getSignatureEntityType() {
		return signatureEntityType;
	}

	public void setSignatureEntityType(Integer signatureEntityType) {
		this.signatureEntityType = signatureEntityType;
	}

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	public String getEncryptionkeytext() {
		return encryptionkeytext;
	}

	public void setEncryptionkeytext(String encryptionkeytext) {
		this.encryptionkeytext = encryptionkeytext;
	}
}