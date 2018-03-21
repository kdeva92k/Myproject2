package com.glenwood.glaceemr.server.application.services.chart.HPI;

public class ChiefComplaintsBean {
	
	public ChiefComplaintsBean(Integer shortcutId,String shortcutName,String shortcutValue){
		super();
		if(shortcutId!=null){
			this.shortcutId=shortcutId;
		}

		if(shortcutName!=null){
			this.shortcutName=shortcutName;
		}
		if(shortcutValue!= null){
			this.shortcutValue=shortcutValue;
		}

	}

	private Integer shortcutId;
	
	private String shortcutName;
	
	private String shortcutValue;

	public Integer getShortcutId() {
		return shortcutId;
	}

	public void setShortcutId(Integer shortcutId) {
		this.shortcutId = shortcutId;
	}

	public String getShortcutName() {
		return shortcutName;
	}

	public void setShortcutName(String shortcutName) {
		this.shortcutName = shortcutName;
	}
	public String getShortcutValue() {
		return shortcutValue;
	}

	public void setShortcutValue(String shortcutValue) {
		this.shortcutValue = shortcutValue;
	}


}
