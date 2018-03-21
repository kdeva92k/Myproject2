package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PQRSResponse {
	private String accountId = new String();
	private long patientId;
	private Map<Date,PQRSMeasureStatus> measureStatus = new HashMap<Date,PQRSMeasureStatus>();
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public Map<Date,PQRSMeasureStatus> getMeasureStatus() {
		return measureStatus;
	}
	public void setMeasureStatus(Map<Date,PQRSMeasureStatus> measureStatus) {
		this.measureStatus = measureStatus;
	}

}
