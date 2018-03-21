package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "hl7_laborders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hl7Laborders {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hl7_laborders_hl7_laborders_order_id_seq")
	@SequenceGenerator(name ="hl7_laborders_hl7_laborders_order_id_seq", sequenceName="hl7_laborders_hl7_laborders_order_id_seq", allocationSize=1)
	@Column(name="hl7_laborders_order_id")
	private Integer hl7LabordersOrderId;

	@Column(name="hl7_laborders_patient_id")
	private Integer hl7LabordersPatientId;

	@Column(name="hl7_laborders_enc_id")
	private Integer hl7LabordersEncId;

	@Column(name="hl7_laborders_test_id")
	private Integer hl7LabordersTestId;

	@Column(name="hl7_laborders_ord_by")
	private Integer hl7LabordersOrdBy;

	@Column(name="hl7_laborders_ord_to")
	private Integer hl7LabordersOrdTo;

	@Column(name="hl7_laborders_ord_date")
	private Date hl7LabordersOrdDate;

	@Column(name="hl7_laborders_status")
	private Integer hl7LabordersStatus;

	@Column(name="hl7_laborders_filename")
	private String hl7LabordersFilename;

	@Column(name="hl7_laborders_reqid")
	private String hl7LabordersReqid;

	public Integer getHl7LabordersOrderId() {
		return hl7LabordersOrderId;
	}

	public void setHl7LabordersOrderId(Integer hl7LabordersOrderId) {
		this.hl7LabordersOrderId = hl7LabordersOrderId;
	}

	public Integer getHl7LabordersPatientId() {
		return hl7LabordersPatientId;
	}

	public void setHl7LabordersPatientId(Integer hl7LabordersPatientId) {
		this.hl7LabordersPatientId = hl7LabordersPatientId;
	}

	public Integer getHl7LabordersEncId() {
		return hl7LabordersEncId;
	}

	public void setHl7LabordersEncId(Integer hl7LabordersEncId) {
		this.hl7LabordersEncId = hl7LabordersEncId;
	}

	public Integer getHl7LabordersTestId() {
		return hl7LabordersTestId;
	}

	public void setHl7LabordersTestId(Integer hl7LabordersTestId) {
		this.hl7LabordersTestId = hl7LabordersTestId;
	}

	public Integer getHl7LabordersOrdBy() {
		return hl7LabordersOrdBy;
	}

	public void setHl7LabordersOrdBy(Integer hl7LabordersOrdBy) {
		this.hl7LabordersOrdBy = hl7LabordersOrdBy;
	}

	public Integer getHl7LabordersOrdTo() {
		return hl7LabordersOrdTo;
	}

	public void setHl7LabordersOrdTo(Integer hl7LabordersOrdTo) {
		this.hl7LabordersOrdTo = hl7LabordersOrdTo;
	}

	public Date getHl7LabordersOrdDate() {
		return hl7LabordersOrdDate;
	}

	public void setHl7LabordersOrdDate(Date hl7LabordersOrdDate) {
		this.hl7LabordersOrdDate = hl7LabordersOrdDate;
	}

	public Integer getHl7LabordersStatus() {
		return hl7LabordersStatus;
	}

	public void setHl7LabordersStatus(Integer hl7LabordersStatus) {
		this.hl7LabordersStatus = hl7LabordersStatus;
	}

	public String getHl7LabordersFilename() {
		return hl7LabordersFilename;
	}

	public void setHl7LabordersFilename(String hl7LabordersFilename) {
		this.hl7LabordersFilename = hl7LabordersFilename;
	}

	public String getHl7LabordersReqid() {
		return hl7LabordersReqid;
	}

	public void setHl7LabordersReqid(String hl7LabordersReqid) {
		this.hl7LabordersReqid = hl7LabordersReqid;
	}
	
}