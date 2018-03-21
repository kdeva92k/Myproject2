package com.glenwood.glaceemr.server.application.services.chart.plan;

public class Data {

	Integer shortcutid;
	String shortcutname;
	String shortcutvalue;
	
	public Integer getShortcutid() {
		return shortcutid;
	}
	public void setShortcutid(Integer shortcutid) {
		this.shortcutid = shortcutid;
	}
	public String getShortcutname() {
		return shortcutname;
	}
	public void setShortcutname(String shortcutname) {
		this.shortcutname = shortcutname;
	}
	public String getShortcutvalue() {
		return shortcutvalue;
	}
	public void setShortcutvalue(String shortcutvalue) {
		this.shortcutvalue = shortcutvalue;
	}
	public Data(Integer shortcutid, String shortcutname, String shortcutvalue) {
		this.shortcutid = shortcutid;
		this.shortcutname = shortcutname;
		this.shortcutvalue = shortcutvalue;
	}
	
}
