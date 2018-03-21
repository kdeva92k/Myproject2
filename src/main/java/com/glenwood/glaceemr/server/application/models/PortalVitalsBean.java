package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.Date;


public class PortalVitalsBean {
	
	String gwId;
	String value;
	String date;
	String unit;
	
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGwId() {
		return gwId;
	}
	public void setGwId(String gwId) {
		this.gwId = gwId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public PortalVitalsBean(String gwId, String value, Date date) {
		super();
		this.gwId = gwId;
		this.value = value;
		if(date!=null)
		this.date = date+"";
	}
	public PortalVitalsBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PortalVitalsBean(String gwId, String value, Date date, String unit) {
		super();
		this.gwId = gwId;
		this.value = value;
		if(date!=null)
			this.date = date+"";
		this.unit = unit;
	}
	
	

}
