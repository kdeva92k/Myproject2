package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MeasureGroup {
	
	
	
	private String groupNumber = new String();
	private String groupDescription = new String();
	private String groupOperator = new String();
	private List<GroupDetails> groupDetails = new ArrayList<GroupDetails>();
	
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	public String getGroupOperator() {
		return groupOperator;
	}
	public void setGroupOperator(String groupOperator) {
		this.groupOperator = groupOperator;
	}
	
	public List<GroupDetails> getGroupDetails() {
		return groupDetails;
	}
	public void setGroupDetails(List<GroupDetails> groupDetails) {
		this.groupDetails = groupDetails;
	}
	
	
	
}
