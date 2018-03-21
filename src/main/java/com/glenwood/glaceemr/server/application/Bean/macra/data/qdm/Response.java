package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;

import java.util.HashMap;
import java.util.Map;

import com.glenwood.glaceemr.server.application.Bean.MeasureStatus;


public class Response
{
	private String accountId = new String();
	private long patientId;
	private Map<String,MeasureStatus> measureStatus = new HashMap<String, MeasureStatus>();
	public String getAccountId()
	{
		return accountId;
	}
	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}
	public long getPatientId()
	{
		return patientId;
	}
	public void setPatientId(long patientId)
	{
		this.patientId = patientId;
	}
	public Map<String,MeasureStatus> getMeasureStatus()
	{
		return measureStatus;
	}
	public void setMeasureStatus(Map<String,MeasureStatus> measureStatus)
	{
		this.measureStatus = measureStatus;
	}
	
}