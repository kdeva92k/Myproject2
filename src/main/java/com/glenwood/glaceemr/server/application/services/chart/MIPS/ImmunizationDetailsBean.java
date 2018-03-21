package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.util.Date;

public class ImmunizationDetailsBean {

	public ImmunizationDetailsBean(int labEntriesTestId, String labDescriptionTestDesc,
			Date labEntriesPerfOn, String labDescriptionCvx,int status,int refusalReason) {
		super();
		this.labEntriesTestId=labEntriesTestId;
		this.labDescriptionTestDesc=labDescriptionTestDesc;
		this.labEntriesPerfOn=labEntriesPerfOn;
		this.labDescriptionCvx=labDescriptionCvx;
		this.status=status;
		this.refusalReason=refusalReason;
		
	}
public int getLabEntriesTestId() {
		return labEntriesTestId;
	}
	public void setLabEntriesTestId(int labEntriesTestId) {
		this.labEntriesTestId = labEntriesTestId;
	}
	public String getLabDescriptionTestDesc() {
		return labDescriptionTestDesc;
	}
	public void setLabDescriptionTestDesc(String labDescriptionTestDesc) {
		this.labDescriptionTestDesc = labDescriptionTestDesc;
	}
	public Date getLabEntriesPerfOn() {
		return labEntriesPerfOn;
	}
	public void setLabEntriesPerfOn(Date labEntriesPerfOn) {
		this.labEntriesPerfOn = labEntriesPerfOn;
	}
	public String getLabDescriptionCvx() {
		return labDescriptionCvx;
	}
	public void setLabDescriptionCvx(String labDescriptionCvx) {
		this.labDescriptionCvx = labDescriptionCvx;
	}
	int labEntriesTestId;
String labDescriptionTestDesc;
Date labEntriesPerfOn;
String labDescriptionCvx;
int status;
int refusalReason;
public int getRefusalReason() {
	return refusalReason;
}
public void setRefusalReason(int refusalReason) {
	this.refusalReason = refusalReason;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}


}