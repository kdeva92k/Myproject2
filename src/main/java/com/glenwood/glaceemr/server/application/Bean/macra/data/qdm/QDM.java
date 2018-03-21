package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;

import java.util.Date;

public class QDM implements Comparable<QDM>
{
	private String code = new String();
	private String codeSystemOID = new String();
	private String codeSystem = new String();
	private String description = new String();
	private Date startDate = new Date();
	private Date endDate = null;
	private int status;
	private Negation negation;
	
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getCodeSystemOID()
	{
		return codeSystemOID;
	}
	public void setCodeSystemOID(String codeSystemOID)
	{
		this.codeSystemOID = codeSystemOID;
	}
	public String getCodeSystem()
	{
		return codeSystem;
	}
	public void setCodeSystem(String codeSystem)
	{
		this.codeSystem = codeSystem;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public Negation getNegation()
	{
		return negation;
	}
	public void setNegation(Negation negation)
	{
		this.negation = negation;
	}
	@Override
	public int compareTo(QDM o)
	{
		return o.getStartDate().compareTo(this.getStartDate());
	}	
}
