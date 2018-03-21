package com.glenwood.glaceemr.server.application.services.chart.plan;

public class QuickNotesData {

	Integer datalistid;
	String data;
	
	public QuickNotesData(Integer datalistid, String data){
		this.datalistid= datalistid;
		this.data= data;
	}
	
	public Integer getDatalistid() {
		return datalistid;
	}
	public void setDatalistid(Integer datalistid) {
		this.datalistid = datalistid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
