package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;

public class Communication extends QDM
{
	private int type;
	private String fulFillmentId = new String();
	
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getFulFillmentId()
	{
		return fulFillmentId;
	}

	public void setFulFillmentId(String fulFillmentId)
	{
		this.fulFillmentId = fulFillmentId;
	}
	
}
