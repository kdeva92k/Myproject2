package com.glenwood.glaceemr.server.application.services.chart.plan;

import java.util.List;

public class InstructionHighLevelDetailsData {

	String displayname;
	String typeid;		
	List<InstructionDetailsData> planelements=null;
	
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public List<InstructionDetailsData> getPlanelements() {
		return planelements;
	}
	public void setPlanelements(List<InstructionDetailsData> planelements) {
		this.planelements = planelements;
	}
	
}
