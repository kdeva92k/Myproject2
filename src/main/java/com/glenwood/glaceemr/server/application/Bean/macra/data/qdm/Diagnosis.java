package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;

import java.util.Date;

public class Diagnosis extends QDM
{
	private String severity = new String();
	private Date recordedDate;

	public String getSeverity()
	{
		return severity;
	}
	public void setSeverity(String severity)
	{
		this.severity = severity;
	}
	public Date getRecordedDate()
	{
		return recordedDate;
	}
	public void setRecordedDate(Date recordedDate)
	{
		this.recordedDate = recordedDate;
	}

}
