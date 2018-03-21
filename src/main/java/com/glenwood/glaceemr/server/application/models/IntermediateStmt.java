package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "intermediate_stmt")
public class IntermediateStmt {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="intermediate_stmt_intermediate_stmt_id_seq")
	@SequenceGenerator(name ="intermediate_stmt_intermediate_stmt_id_seq", sequenceName="intermediate_stmt_intermediate_stmt_id_seq", allocationSize=1)
	@Column(name="intermediate_stmt_id")
	private Integer intermediateStmtId;

	@Column(name="intermediate_stmt_patientid")
	private Integer intermediateStmtPatientid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="intermediate_stmt_date")
	private Timestamp intermediateStmtDate;

	@Column(name="intermediate_stmt_billamt")
	private Double intermediateStmtBillamt;

	@Column(name="intermediate_stmt_primaryins")
	private String intermediateStmtPrimaryins;

	@Column(name="intermediate_stmt_mindos")
	private Date intermediateStmtMindos;

	@Column(name="intermediate_stmt_maxdos")
	private Date intermediateStmtMaxdos;

	@Column(name="intermediate_stmt_primaryaddress")
	private String intermediateStmtPrimaryaddress;

	@Column(name="intermediate_stmt_primarycity")
	private String intermediateStmtPrimarycity;

	@Column(name="intermediate_stmt_primarystate")
	private String intermediateStmtPrimarystate;

	@Column(name="intermediate_stmt_primaryzip")
	private String intermediateStmtPrimaryzip;

	@Column(name="intermediate_stmt_primarypolicy")
	private String intermediateStmtPrimarypolicy;

	@Column(name="intermediate_stmt_guarantorname")
	private String intermediateStmtGuarantorname;

	@Column(name="intermediate_stmt_guarantoraddress")
	private String intermediateStmtGuarantoraddress;

	@Column(name="intermediate_stmt_guarantorcity")
	private String intermediateStmtGuarantorcity;

	@Column(name="intermediate_stmt_guarantorstate")
	private String intermediateStmtGuarantorstate;

	@Column(name="intermediate_stmt_guarantorzip")
	private String intermediateStmtGuarantorzip;

	@Column(name="intermediate_stmt_guarantorrel")
	private Integer intermediateStmtGuarantorrel;

	@Column(name="intermediate_stmt_trackerid")
	private Integer intermediateStmtTrackerid;

	public Integer getIntermediateStmtId() {
		return intermediateStmtId;
	}

	public void setIntermediateStmtId(Integer intermediateStmtId) {
		this.intermediateStmtId = intermediateStmtId;
	}

	public Integer getIntermediateStmtPatientid() {
		return intermediateStmtPatientid;
	}

	public void setIntermediateStmtPatientid(Integer intermediateStmtPatientid) {
		this.intermediateStmtPatientid = intermediateStmtPatientid;
	}

	public Timestamp getIntermediateStmtDate() {
		return intermediateStmtDate;
	}

	public void setIntermediateStmtDate(Timestamp intermediateStmtDate) {
		this.intermediateStmtDate = intermediateStmtDate;
	}

	public Double getIntermediateStmtBillamt() {
		return intermediateStmtBillamt;
	}

	public void setIntermediateStmtBillamt(Double intermediateStmtBillamt) {
		this.intermediateStmtBillamt = intermediateStmtBillamt;
	}

	public String getIntermediateStmtPrimaryins() {
		return intermediateStmtPrimaryins;
	}

	public void setIntermediateStmtPrimaryins(String intermediateStmtPrimaryins) {
		this.intermediateStmtPrimaryins = intermediateStmtPrimaryins;
	}

	public Date getIntermediateStmtMindos() {
		return intermediateStmtMindos;
	}

	public void setIntermediateStmtMindos(Date intermediateStmtMindos) {
		this.intermediateStmtMindos = intermediateStmtMindos;
	}

	public Date getIntermediateStmtMaxdos() {
		return intermediateStmtMaxdos;
	}

	public void setIntermediateStmtMaxdos(Date intermediateStmtMaxdos) {
		this.intermediateStmtMaxdos = intermediateStmtMaxdos;
	}

	public String getIntermediateStmtPrimaryaddress() {
		return intermediateStmtPrimaryaddress;
	}

	public void setIntermediateStmtPrimaryaddress(
			String intermediateStmtPrimaryaddress) {
		this.intermediateStmtPrimaryaddress = intermediateStmtPrimaryaddress;
	}

	public String getIntermediateStmtPrimarycity() {
		return intermediateStmtPrimarycity;
	}

	public void setIntermediateStmtPrimarycity(String intermediateStmtPrimarycity) {
		this.intermediateStmtPrimarycity = intermediateStmtPrimarycity;
	}

	public String getIntermediateStmtPrimarystate() {
		return intermediateStmtPrimarystate;
	}

	public void setIntermediateStmtPrimarystate(String intermediateStmtPrimarystate) {
		this.intermediateStmtPrimarystate = intermediateStmtPrimarystate;
	}

	public String getIntermediateStmtPrimaryzip() {
		return intermediateStmtPrimaryzip;
	}

	public void setIntermediateStmtPrimaryzip(String intermediateStmtPrimaryzip) {
		this.intermediateStmtPrimaryzip = intermediateStmtPrimaryzip;
	}

	public String getIntermediateStmtPrimarypolicy() {
		return intermediateStmtPrimarypolicy;
	}

	public void setIntermediateStmtPrimarypolicy(
			String intermediateStmtPrimarypolicy) {
		this.intermediateStmtPrimarypolicy = intermediateStmtPrimarypolicy;
	}

	public String getIntermediateStmtGuarantorname() {
		return intermediateStmtGuarantorname;
	}

	public void setIntermediateStmtGuarantorname(
			String intermediateStmtGuarantorname) {
		this.intermediateStmtGuarantorname = intermediateStmtGuarantorname;
	}

	public String getIntermediateStmtGuarantoraddress() {
		return intermediateStmtGuarantoraddress;
	}

	public void setIntermediateStmtGuarantoraddress(
			String intermediateStmtGuarantoraddress) {
		this.intermediateStmtGuarantoraddress = intermediateStmtGuarantoraddress;
	}

	public String getIntermediateStmtGuarantorcity() {
		return intermediateStmtGuarantorcity;
	}

	public void setIntermediateStmtGuarantorcity(
			String intermediateStmtGuarantorcity) {
		this.intermediateStmtGuarantorcity = intermediateStmtGuarantorcity;
	}

	public String getIntermediateStmtGuarantorstate() {
		return intermediateStmtGuarantorstate;
	}

	public void setIntermediateStmtGuarantorstate(
			String intermediateStmtGuarantorstate) {
		this.intermediateStmtGuarantorstate = intermediateStmtGuarantorstate;
	}

	public String getIntermediateStmtGuarantorzip() {
		return intermediateStmtGuarantorzip;
	}

	public void setIntermediateStmtGuarantorzip(String intermediateStmtGuarantorzip) {
		this.intermediateStmtGuarantorzip = intermediateStmtGuarantorzip;
	}

	public Integer getIntermediateStmtGuarantorrel() {
		return intermediateStmtGuarantorrel;
	}

	public void setIntermediateStmtGuarantorrel(Integer intermediateStmtGuarantorrel) {
		this.intermediateStmtGuarantorrel = intermediateStmtGuarantorrel;
	}

	public Integer getIntermediateStmtTrackerid() {
		return intermediateStmtTrackerid;
	}

	public void setIntermediateStmtTrackerid(Integer intermediateStmtTrackerid) {
		this.intermediateStmtTrackerid = intermediateStmtTrackerid;
	}
	
	
}