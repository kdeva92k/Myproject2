package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class LabEntriesParameterBean {

	private Integer labEntriesParameterId;
	private Integer labEntriesParameterTestdetailid;
	private Integer labEntriesParameterMapid;
	private String labEntriesParameterValue;
	private String labEntriesParameterStatus;
	private String labEntriesParameterNotes;
	private Integer labEntriesParameterChartid;
	private Date labEntriesParameterDate;
	private String labEntriesParameterResultstatus;
	private Integer labEntriesParameterSortorder;
	private Boolean labEntriesParameterIsactive;
	private String labEntriesParameterNormalrange;
	private Integer labEntriesParameterLabcompDetailid;
	private Integer labEntriesParameterIspdf;
	private Integer labEntriesParameterFilenameId;
	private Integer labEntriesParameterFilenameScanid;
	private String labEntriesParameterName;
	private String labEntriesParameterCode;
	private String labEntriesParameterCodeSystem;
	
	public LabEntriesParameterBean(){
		
	}
	public LabEntriesParameterBean(Integer labEntriesParameterIspdf,Integer labEntriesParameterFilenameScanid,Integer labEntriesParameterId){
		
		this.labEntriesParameterIspdf=labEntriesParameterIspdf;
		this.labEntriesParameterFilenameScanid=labEntriesParameterFilenameScanid;
		this.labEntriesParameterId=labEntriesParameterId;
	}
	public Integer getLabEntriesParameterId() {
		return labEntriesParameterId;
	}
	public void setLabEntriesParameterId(Integer labEntriesParameterId) {
		this.labEntriesParameterId = labEntriesParameterId;
	}
	public Integer getLabEntriesParameterTestdetailid() {
		return labEntriesParameterTestdetailid;
	}
	public void setLabEntriesParameterTestdetailid(
			Integer labEntriesParameterTestdetailid) {
		this.labEntriesParameterTestdetailid = labEntriesParameterTestdetailid;
	}
	public Integer getLabEntriesParameterMapid() {
		return labEntriesParameterMapid;
	}
	public void setLabEntriesParameterMapid(Integer labEntriesParameterMapid) {
		this.labEntriesParameterMapid = labEntriesParameterMapid;
	}
	public String getLabEntriesParameterValue() {
		return labEntriesParameterValue;
	}
	public void setLabEntriesParameterValue(String labEntriesParameterValue) {
		this.labEntriesParameterValue = labEntriesParameterValue;
	}
	public String getLabEntriesParameterStatus() {
		return labEntriesParameterStatus;
	}
	public void setLabEntriesParameterStatus(String labEntriesParameterStatus) {
		this.labEntriesParameterStatus = labEntriesParameterStatus;
	}
	public String getLabEntriesParameterNotes() {
		return labEntriesParameterNotes;
	}
	public void setLabEntriesParameterNotes(String labEntriesParameterNotes) {
		this.labEntriesParameterNotes = labEntriesParameterNotes;
	}
	public Integer getLabEntriesParameterChartid() {
		return labEntriesParameterChartid;
	}
	public void setLabEntriesParameterChartid(Integer labEntriesParameterChartid) {
		this.labEntriesParameterChartid = labEntriesParameterChartid;
	}
	public Date getLabEntriesParameterDate() {
		return labEntriesParameterDate;
	}
	public void setLabEntriesParameterDate(Date labEntriesParameterDate) {
		this.labEntriesParameterDate = labEntriesParameterDate;
	}
	public String getLabEntriesParameterResultstatus() {
		return labEntriesParameterResultstatus;
	}
	public void setLabEntriesParameterResultstatus(
			String labEntriesParameterResultstatus) {
		this.labEntriesParameterResultstatus = labEntriesParameterResultstatus;
	}
	public Integer getLabEntriesParameterSortorder() {
		return labEntriesParameterSortorder;
	}
	public void setLabEntriesParameterSortorder(Integer labEntriesParameterSortorder) {
		this.labEntriesParameterSortorder = labEntriesParameterSortorder;
	}
	public Boolean getLabEntriesParameterIsactive() {
		return labEntriesParameterIsactive;
	}
	public void setLabEntriesParameterIsactive(Boolean labEntriesParameterIsactive) {
		this.labEntriesParameterIsactive = labEntriesParameterIsactive;
	}
	public String getLabEntriesParameterNormalrange() {
		return labEntriesParameterNormalrange;
	}
	public void setLabEntriesParameterNormalrange(
			String labEntriesParameterNormalrange) {
		this.labEntriesParameterNormalrange = labEntriesParameterNormalrange;
	}
	public Integer getLabEntriesParameterLabcompDetailid() {
		return labEntriesParameterLabcompDetailid;
	}
	public void setLabEntriesParameterLabcompDetailid(
			Integer labEntriesParameterLabcompDetailid) {
		this.labEntriesParameterLabcompDetailid = labEntriesParameterLabcompDetailid;
	}
	public Integer getLabEntriesParameterIspdf() {
		return labEntriesParameterIspdf;
	}
	public void setLabEntriesParameterIspdf(Integer labEntriesParameterIspdf) {
		this.labEntriesParameterIspdf = labEntriesParameterIspdf;
	}
	public Integer getLabEntriesParameterFilenameId() {
		return labEntriesParameterFilenameId;
	}
	public void setLabEntriesParameterFilenameId(
			Integer labEntriesParameterFilenameId) {
		this.labEntriesParameterFilenameId = labEntriesParameterFilenameId;
	}
	public Integer getLabEntriesParameterFilenameScanid() {
		return labEntriesParameterFilenameScanid;
	}
	public void setLabEntriesParameterFilenameScanid(
			Integer labEntriesParameterFilenameScanid) {
		this.labEntriesParameterFilenameScanid = labEntriesParameterFilenameScanid;
	}
	public String getLabEntriesParameterName() {
		return labEntriesParameterName;
	}
	public void setLabEntriesParameterName(String labEntriesParameterName) {
		this.labEntriesParameterName = labEntriesParameterName;
	}
	public String getLabEntriesParameterCode() {
		return labEntriesParameterCode;
	}
	public void setLabEntriesParameterCode(String labEntriesParameterCode) {
		this.labEntriesParameterCode = labEntriesParameterCode;
	}
	public String getLabEntriesParameterCodeSystem() {
		return labEntriesParameterCodeSystem;
	}
	public void setLabEntriesParameterCodeSystem(
			String labEntriesParameterCodeSystem) {
		this.labEntriesParameterCodeSystem = labEntriesParameterCodeSystem;
	}
}
