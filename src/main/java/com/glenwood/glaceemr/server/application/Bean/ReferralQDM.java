package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class ReferralQDM {
	
	Integer refId;
	Date orderedDate;
	Date reviewedDate;
	Integer status;
	
	public ReferralQDM(Integer refId, Date orderedDate,Date reviewedDate, Integer status) {
		super();
		this.refId = refId;
		this.orderedDate = orderedDate;
		this.reviewedDate=reviewedDate;
		this.status = status;
	}	

	public Integer getRefId() {
		return refId;
	}
	
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	
	
	
	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Date getReviewedDate() {
		return reviewedDate;
	}

	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}

	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

}
