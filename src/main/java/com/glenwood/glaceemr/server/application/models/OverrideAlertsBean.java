package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

public class OverrideAlertsBean {
	
	private Date overriddenOn;
	private String reason;
	private String overridealertsFlowsheetElementId;
	

	public OverrideAlertsBean(String overridealertsFlowsheetElementId,String reason, Date overriddenOn) {
		this.overriddenOn=overriddenOn;
		this.overridealertsFlowsheetElementId=overridealertsFlowsheetElementId;
	}
	
	public String getOverridealertsFlowsheetElementId() {
		return overridealertsFlowsheetElementId;
	}

	public void setOverridealertsFlowsheetElementId(String overridealertsFlowsheetElementId) {
		this.overridealertsFlowsheetElementId = overridealertsFlowsheetElementId;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason; 
	}
	
	public Date getOverriddenOn() {
		return overriddenOn;
	}

	public void setOverriddenOn(Date overriddenOn) {
		this.overriddenOn = overriddenOn;
	}
	
}