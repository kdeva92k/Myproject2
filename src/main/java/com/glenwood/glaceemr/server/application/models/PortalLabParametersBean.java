package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalLabParametersBean {

	List<LabEntriesParameter> parameterHistory;
	
	LabEntriesParameter labParameter;

	public List<LabEntriesParameter> getParameterHistory() {
		return parameterHistory;
	}

	public void setParameterHistory(List<LabEntriesParameter> parameterHistory) {
		this.parameterHistory = parameterHistory;
	}

	public LabEntriesParameter getLabParameter() {
		return labParameter;
	}

	public void setLabParameter(LabEntriesParameter labParameter) {
		this.labParameter = labParameter;
	}
	
}
