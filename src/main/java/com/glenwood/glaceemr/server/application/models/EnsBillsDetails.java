package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
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
@Table(name = "ens_bills_details")
public class EnsBillsDetails implements Serializable{

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="batchno")
	private Integer batchno;

	@Column(name="stmnt_type")
	private String stmntType;

	@Column(name="stmnt_path")
	private String stmntPath;

	@Column(name="ens_batch_no")
	private Integer ensBatchNo;

	@Column(name="uploaded")
	private Integer uploaded;

	@Column(name="report_received")
	private Integer reportReceived;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="reports_date")
	private Timestamp reportsDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lastrundate")
	private Timestamp lastrundate;

	@Column(name="preview_flag")
	private Integer previewFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBatchno() {
		return batchno;
	}

	public void setBatchno(Integer batchno) {
		this.batchno = batchno;
	}

	public String getStmntType() {
		return stmntType;
	}

	public void setStmntType(String stmntType) {
		this.stmntType = stmntType;
	}

	public String getStmntPath() {
		return stmntPath;
	}

	public void setStmntPath(String stmntPath) {
		this.stmntPath = stmntPath;
	}

	public Integer getEnsBatchNo() {
		return ensBatchNo;
	}

	public void setEnsBatchNo(Integer ensBatchNo) {
		this.ensBatchNo = ensBatchNo;
	}

	public Integer getUploaded() {
		return uploaded;
	}

	public void setUploaded(Integer uploaded) {
		this.uploaded = uploaded;
	}

	public Integer getReportReceived() {
		return reportReceived;
	}

	public void setReportReceived(Integer reportReceived) {
		this.reportReceived = reportReceived;
	}

	public Timestamp getReportsDate() {
		return reportsDate;
	}

	public void setReportsDate(Timestamp reportsDate) {
		this.reportsDate = reportsDate;
	}

	public Timestamp getLastrundate() {
		return lastrundate;
	}

	public void setLastrundate(Timestamp lastrundate) {
		this.lastrundate = lastrundate;
	}

	public Integer getPreviewFlag() {
		return previewFlag;
	}

	public void setPreviewFlag(Integer previewFlag) {
		this.previewFlag = previewFlag;
	}
	
	
}