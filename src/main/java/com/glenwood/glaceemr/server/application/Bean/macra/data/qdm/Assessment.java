package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;


public class Assessment extends QDM
{
	private String resultCode = new String();
	private String resultCodeSystemOID = new String();
	private String resultCodeSystem = new String();
	private String resultDescription = new String();
	
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

}
