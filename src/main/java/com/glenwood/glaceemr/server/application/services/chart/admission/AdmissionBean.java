package com.glenwood.glaceemr.server.application.services.chart.admission;

public class AdmissionBean {
	
	Integer admissionId;
	Integer patientId;
	Integer chartId;
	String admissionDate;
	String admissionTime;
	Integer admssProvider;
	Integer pos;
	String selectedDx;
	String dischargeDx;	
	Integer userId;
	Integer loginId;
	Integer admissionEpisode;
	Integer roomNo;
	Integer blockNo;
	String notes;
	String dischargeDate;
	String dispositionvalue; 
	String dispositionText;
	
	public Integer getAdmissionId() {
		return admissionId;
	}
	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}
	public Integer getAdmissionEpisode() {
		return admissionEpisode;
	}
	public void setAdmissionEpisode(Integer admissionEpisode) {
		this.admissionEpisode = admissionEpisode;
	}
	public Integer getChartId() {
		return chartId;
	}
	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public Integer getAdmssProvider() {
		return admssProvider;
	}
	public void setAdmssProvider(Integer admssProvider) {
		this.admssProvider = admssProvider;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public String getSelectedDx() {
		return selectedDx;
	}
	public void setSelectedDx(String selectedDx) {
		this.selectedDx = selectedDx;
	}
	public String getDischargeDx() {
		return dischargeDx;
	}
	public void setDischargeDx(String dischargeDx) {
		this.dischargeDx = dischargeDx;
	}
	public Integer getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(Integer blockNo) {
		this.blockNo = blockNo;
	}
	public String getAdmissionTime() {
		return admissionTime;
	}
	public void setAdmissionTime(String admissionTime) {
		this.admissionTime = admissionTime;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getDispositionvalue() {
		return dispositionvalue;
		
	}
	public void setDispositionvalue(String dispositionvalue) {
		this.dispositionvalue = dispositionvalue;
		
	}
	public String getDispositionText() {
		return dispositionText;
		
	}
	public void setDispositionText(String dispositionText) {
		this.dispositionText = dispositionText;
		
	}
	
}
