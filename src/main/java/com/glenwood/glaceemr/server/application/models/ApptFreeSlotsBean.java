package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

public class ApptFreeSlotsBean {

	int schTemplateDetailId;
	
	Timestamp schTemplateDetailStartTime;
	
	Timestamp schTemplateDetailEndTime;
	
	int schTemplateDetailInterval;
	
	int schTemplateDetailApptTypeId;
	
	String schTemplateDetailTypeName;
	
	int schTemplateDetailLocationId;
	
	String schTemplateDetailLocationName;
	
	String schTemplateDetailLocationColor;
	
	int schTemplateDetailStatusId;
	
	int schTemplateDetailStatusName;
	
	int schTemplateDetailStatusGroupId;
	
	int schTemplateDetailNoOfSlots;
	
	String schTemplateDetailCellColor;
	
	int schTemplateDetailOwnerId;
	
	String schTemplateDetailOwnername;
	
	String schTemplateDetailDoctorColor;
	
	int schTemplateDetailSameDayIsCheck;
	
	String schTemplateDetailDoctorNotes;
	
	
	public ApptFreeSlotsBean(){
		
	}

	public ApptFreeSlotsBean(int schTemplateDetailId,
			Timestamp schTemplateDetailStartTime, Timestamp schTemplateDetailEndTime,
			int schTemplateDetailInterval, int schTemplateDetailApptTypeId,
			String schTemplateDetailTypeName, int schTemplateDetailLocationId,
			String schTemplateDetailLocationName,
			String schTemplateDetailLocationColor,
			int schTemplateDetailStatusId, int schTemplateDetailStatusName,
			int schTemplateDetailStatusGroupId, int schTemplateDetailNoOfSlots,
			String schTemplateDetailCellColor, int schTemplateDetailOwnerId,
			String schTemplateDetailOwnername,
			String schTemplateDetailDoctorColor,
			int schTemplateDetailSameDayIsCheck,
			String schTemplateDetailDoctorNotes) {
		
		super();
		
		this.schTemplateDetailId = schTemplateDetailId;
		this.schTemplateDetailStartTime = schTemplateDetailStartTime;
		this.schTemplateDetailEndTime = schTemplateDetailEndTime;
		this.schTemplateDetailInterval = schTemplateDetailInterval;
		this.schTemplateDetailApptTypeId = schTemplateDetailApptTypeId;
		this.schTemplateDetailTypeName = schTemplateDetailTypeName;
		this.schTemplateDetailLocationId = schTemplateDetailLocationId;
		this.schTemplateDetailLocationName = schTemplateDetailLocationName;
		this.schTemplateDetailLocationColor = schTemplateDetailLocationColor;
		this.schTemplateDetailStatusId = schTemplateDetailStatusId;
		this.schTemplateDetailStatusName = schTemplateDetailStatusName;
		this.schTemplateDetailStatusGroupId = schTemplateDetailStatusGroupId;
		this.schTemplateDetailNoOfSlots = schTemplateDetailNoOfSlots;
		this.schTemplateDetailCellColor = schTemplateDetailCellColor;
		this.schTemplateDetailOwnerId = schTemplateDetailOwnerId;
		this.schTemplateDetailOwnername = schTemplateDetailOwnername;
		this.schTemplateDetailDoctorColor = schTemplateDetailDoctorColor;
		this.schTemplateDetailSameDayIsCheck = schTemplateDetailSameDayIsCheck;
		this.schTemplateDetailDoctorNotes = schTemplateDetailDoctorNotes;
	}

	public int getSchTemplateDetailId() {
		return schTemplateDetailId;
	}

	public void setSchTemplateDetailId(int schTemplateDetailId) {
		this.schTemplateDetailId = schTemplateDetailId;
	}

	public Timestamp getSchTemplateDetailStartTime() {
		return schTemplateDetailStartTime;
	}

	public void setSchTemplateDetailStartTime(Timestamp timestamp) {
		this.schTemplateDetailStartTime = timestamp;
	}

	public Timestamp getSchTemplateDetailEndTime() {
		return schTemplateDetailEndTime;
	}

	public void setSchTemplateDetailEndTime(Timestamp schTemplateDetailEndTime) {
		this.schTemplateDetailEndTime = schTemplateDetailEndTime;
	}

	public int getSchTemplateDetailInterval() {
		return schTemplateDetailInterval;
	}

	public void setSchTemplateDetailInterval(int schTemplateDetailInterval) {
		this.schTemplateDetailInterval = schTemplateDetailInterval;
	}

	public int getSchTemplateDetailApptTypeId() {
		return schTemplateDetailApptTypeId;
	}

	public void setSchTemplateDetailApptTypeId(int schTemplateDetailApptTypeId) {
		this.schTemplateDetailApptTypeId = schTemplateDetailApptTypeId;
	}

	public String getSchTemplateDetailTypeName() {
		return schTemplateDetailTypeName;
	}

	public void setSchTemplateDetailTypeName(String schTemplateDetailTypeName) {
		this.schTemplateDetailTypeName = schTemplateDetailTypeName;
	}

	public int getSchTemplateDetailLocationId() {
		return schTemplateDetailLocationId;
	}

	public void setSchTemplateDetailLocationId(int schTemplateDetailLocationId) {
		this.schTemplateDetailLocationId = schTemplateDetailLocationId;
	}

	public String getSchTemplateDetailLocationName() {
		return schTemplateDetailLocationName;
	}

	public void setSchTemplateDetailLocationName(
			String schTemplateDetailLocationName) {
		this.schTemplateDetailLocationName = schTemplateDetailLocationName;
	}

	public String getSchTemplateDetailLocationColor() {
		return schTemplateDetailLocationColor;
	}

	public void setSchTemplateDetailLocationColor(
			String schTemplateDetailLocationColor) {
		this.schTemplateDetailLocationColor = schTemplateDetailLocationColor;
	}

	public int getSchTemplateDetailStatusId() {
		return schTemplateDetailStatusId;
	}

	public void setSchTemplateDetailStatusId(int schTemplateDetailStatusId) {
		this.schTemplateDetailStatusId = schTemplateDetailStatusId;
	}

	public int getSchTemplateDetailStatusName() {
		return schTemplateDetailStatusName;
	}

	public void setSchTemplateDetailStatusName(int schTemplateDetailStatusName) {
		this.schTemplateDetailStatusName = schTemplateDetailStatusName;
	}

	public int getSchTemplateDetailStatusGroupId() {
		return schTemplateDetailStatusGroupId;
	}

	public void setSchTemplateDetailStatusGroupId(int schTemplateDetailStatusGroupId) {
		this.schTemplateDetailStatusGroupId = schTemplateDetailStatusGroupId;
	}

	public int getSchTemplateDetailNoOfSlots() {
		return schTemplateDetailNoOfSlots;
	}

	public void setSchTemplateDetailNoOfSlots(int schTemplateDetailNoOfSlots) {
		this.schTemplateDetailNoOfSlots = schTemplateDetailNoOfSlots;
	}

	public String getSchTemplateDetailCellColor() {
		return schTemplateDetailCellColor;
	}

	public void setSchTemplateDetailCellColor(String schTemplateDetailCellColor) {
		this.schTemplateDetailCellColor = schTemplateDetailCellColor;
	}

	public int getSchTemplateDetailOwnerId() {
		return schTemplateDetailOwnerId;
	}

	public void setSchTemplateDetailOwnerId(int schTemplateDetailOwnerId) {
		this.schTemplateDetailOwnerId = schTemplateDetailOwnerId;
	}

	public String getSchTemplateDetailOwnername() {
		return schTemplateDetailOwnername;
	}

	public void setSchTemplateDetailOwnername(String schTemplateDetailOwnername) {
		this.schTemplateDetailOwnername = schTemplateDetailOwnername;
	}

	public String getSchTemplateDetailDoctorColor() {
		return schTemplateDetailDoctorColor;
	}

	public void setSchTemplateDetailDoctorColor(String schTemplateDetailDoctorColor) {
		this.schTemplateDetailDoctorColor = schTemplateDetailDoctorColor;
	}

	public int getSchTemplateDetailSameDayIsCheck() {
		return schTemplateDetailSameDayIsCheck;
	}

	public void setSchTemplateDetailSameDayIsCheck(
			int schTemplateDetailSameDayIsCheck) {
		this.schTemplateDetailSameDayIsCheck = schTemplateDetailSameDayIsCheck;
	}

	public String getSchTemplateDetailDoctorNotes() {
		return schTemplateDetailDoctorNotes;
	}

	public void setSchTemplateDetailDoctorNotes(String schTemplateDetailDoctorNotes) {
		this.schTemplateDetailDoctorNotes = schTemplateDetailDoctorNotes;
	}
	
}
