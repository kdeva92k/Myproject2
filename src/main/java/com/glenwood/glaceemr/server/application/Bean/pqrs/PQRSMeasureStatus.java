package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PQRSMeasureStatus implements Comparable<PQRSMeasureStatus> {
	private Date visitDate = new Date();
	private Map<String,MeasureStatus> measureStatus = new HashMap<String, MeasureStatus>();
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public Map<String, MeasureStatus> getMeasureStatus() {
		return measureStatus;
	}
	public void setMeasureStatus(Map<String, MeasureStatus> measureStatus) {
		this.measureStatus = measureStatus;
	}
	@Override
	public int compareTo(PQRSMeasureStatus o) {
		return this.getVisitDate().compareTo(o.getVisitDate());

	}
}