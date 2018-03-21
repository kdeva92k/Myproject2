package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;

import java.util.Date;

public class MedicationOrder extends QDM
{
	private int CMD;
	private String dose = new String();
	private String frequency = new String();
	private int refills;
	private String route = new String();
	private Date orderDate;
	
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
	public Date getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(Date orderDate)
	{
		this.orderDate = orderDate;
	}

	
}
