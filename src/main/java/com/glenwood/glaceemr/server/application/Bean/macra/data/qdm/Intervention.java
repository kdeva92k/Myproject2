package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;

public class Intervention extends QDM
{
	private String id = new String();
	private String resultCode = new String();
	private String resultCodeSystemOID = new String();
	private String resultCodeSystem = new String();
	private String resultDescription = new String();
	private String resultValue = new String();
	private String resultUnit = new String();

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(String resultCode)
	{
		this.resultCode = resultCode;
	}

	public String getResultCodeSystemOID()
	{
		return resultCodeSystemOID;
	}

	public void setResultCodeSystemOID(String resultCodeSystemOID)
	{
		this.resultCodeSystemOID = resultCodeSystemOID;
	}

	public String getResultCodeSystem()
	{
		return resultCodeSystem;
	}

	public void setResultCodeSystem(String resultCodeSystem)
	{
		this.resultCodeSystem = resultCodeSystem;
	}

	public String getResultDescription()
	{
		return resultDescription;
	}

	public void setResultDescription(String resultDescription)
	{
		this.resultDescription = resultDescription;
	}

	public String getResultValue()
	{
		return resultValue;
	}

	public void setResultValue(String resultValue)
	{
		this.resultValue = resultValue;
	}

	public String getResultUnit()
	{
		return resultUnit;
	}

	public void setResultUnit(String resultUnit)
	{
		this.resultUnit = resultUnit;
	}


	
}
