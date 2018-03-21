package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;

public class ActiveMedication extends QDM
{

	private int CMD;
	private String dose = new String();
	private String frequency = new String();
	private int refills;
	private String route = new String();

	
	
	public int getCMD()
	{
		return CMD;
	}



	public void setCMD(int cMD)
	{
		CMD = cMD;
	}



	public String getDose()
	{
		return dose;
	}



	public void setDose(String dose)
	{
		this.dose = dose;
	}



	public String getFrequency()
	{
		return frequency;
	}



	public void setFrequency(String frequency)
	{
		this.frequency = frequency;
	}



	public int getRefills()
	{
		return refills;
	}



	public void setRefills(int refills)
	{
		this.refills = refills;
	}



	public String getRoute()
	{
		return route;
	}



	public void setRoute(String route)
	{
		this.route = route;
	}


}
