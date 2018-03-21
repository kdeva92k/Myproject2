package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.CoreClassHierarchy;
import com.glenwood.glaceemr.server.application.models.FlowsheetType;
import com.glenwood.glaceemr.server.application.models.LabGroups;
import com.glenwood.glaceemr.server.application.models.LabParameters;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;

public class FS_Management {
	Integer flowsheetId;
	Integer flowsheetTypeId;
	Integer loadFromFlowsheet;
	private List<FlowsheetType> flowsheetType;
	private List<VitalsParameter> vitalsParameter;
	private List<LabParameters> labParameters;
	private List<LabGroups> labGroups;
	private List<CoreClassHierarchy> coreClassHierarchy;
	public List<FlowsheetType> getFlowsheetType() {
		return flowsheetType;
	}
	public void setFlowsheetType(List<FlowsheetType> flowsheetType) {
		this.flowsheetType = flowsheetType;
	}
	public List<VitalsParameter> getVitalsParameter() {
		return vitalsParameter;
	}
	public void setVitalsParameter(List<VitalsParameter> vitalsParameter) {
		this.vitalsParameter = vitalsParameter;
	}
	public List<LabParameters> getLabParameters() {
		return labParameters;
	}
	public void setLabParameters(List<LabParameters> labParameters) {
		this.labParameters = labParameters;
	}
	public List<LabGroups> getLabGroups() {
		return labGroups;
	}
	public void setLabGroups(List<LabGroups> labGroups) {
		this.labGroups = labGroups;
	}
	public List<CoreClassHierarchy> getCoreClassHierarchy() {
		return coreClassHierarchy;
	}
	public void setCoreClassHierarchy(List<CoreClassHierarchy> coreClassHierarchy) {
		this.coreClassHierarchy = coreClassHierarchy;
	}
	public Integer getFlowsheetId() {
		return flowsheetId;
	}
	public void setFlowsheetId(Integer flowsheetId) {
		this.flowsheetId = flowsheetId;
	}
	public Integer getFlowsheetTypeId() {
		return flowsheetTypeId;
	}
	public void setFlowsheetTypeId(Integer flowsheetTypeId) {
		this.flowsheetTypeId = flowsheetTypeId;
	}
	public Integer getLoadFromFlowsheet() {
		return loadFromFlowsheet;
	}
	public void setLoadFromFlowsheet(Integer loadFromFlowsheet) {
		this.loadFromFlowsheet = loadFromFlowsheet;
	} 
	
}
