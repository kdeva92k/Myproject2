package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;


public class ImmunizationQDM {
	public int getRefusalReason() {
		return refusalReason;
	}
	public void setRefusalReason(int refusalReason) {
		this.refusalReason = refusalReason;
	}
	int vaccId;
	String vaccName;
	Date performedDate;
	String cvx;
	String groupCVX;
	int status;
	int refusalReason;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getVaccId() {
		return vaccId;
	}
	public void setVaccId(int vaccId) {
		this.vaccId = vaccId;
	}
	public String getVaccName() {
		return vaccName;
	}
	public void setVaccName(String vaccName) {
		this.vaccName = vaccName;
	}
	public Date getPerformedDate() {
		return performedDate;
	}
	public void setPerformedDate(Date performedDate) {
		this.performedDate = performedDate;
	}
	public String getCvx() {
		return cvx;
	}
	public void setCvx(String cvx) {
		this.cvx = cvx;
	}
	public String getGroupCVX() {
		return groupCVX;
	}
	public void setGroupCVX(String groupCVX) {
		this.groupCVX = groupCVX;
	}
	public ImmunizationQDM(Integer vaccId,String vaccName,Date performedDate,String cvx,String groupCVX,int status,int refusalReason){
		this.vaccId=vaccId;
		this.vaccName=vaccName;
		this.performedDate=performedDate;
		this.cvx=cvx;
		this.groupCVX=groupCVX;
		this.status=status;
		this.refusalReason=refusalReason;
	}
	
}