package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.FlowsheetDrug;
import com.glenwood.glaceemr.server.application.models.FlowsheetDx;
import com.glenwood.glaceemr.server.application.models.LabStandardGroup;
import com.glenwood.glaceemr.server.application.models.ParamStandardGroup;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;

public class FS_ConfiguredDetails {
	private List<VitalsParameter> vitalsParameter;
	private List<FlowsheetDrug> flowsheetDrug;
	private List<FS_LabParameterBean> labParameters;
	private List<FS_LabBean> labGroups;
	private List<ClinicalElements> clinicalElements;
	private List<FlowsheetDx> flowsheetDx;
	private List<LabStandardGroup> labStandardGroup;
	private List<ParamStandardGroup> paramStandardGroup;
	private String messageAfterSave;
	private Integer currentGroupIdAfterSave;
	private String currentGroupNameAfterSave;
	public List<VitalsParameter> getVitalsParameter() {
		return vitalsParameter;
	}
	public void setVitalsParameter(List<VitalsParameter> vitalsParameter) {
		this.vitalsParameter = vitalsParameter;
	}
	public List<FlowsheetDrug> getFlowsheetDrug() {
		return flowsheetDrug;
	}
	public void setFlowsheetDrug(List<FlowsheetDrug> flowsheetDrug) {
		this.flowsheetDrug = flowsheetDrug;
	}
	public List<FS_LabParameterBean> getLabParameters() {
		return labParameters;
	}
	public void setLabParameters(List<FS_LabParameterBean> labParameters) {
		this.labParameters = labParameters;
	}
	public List<FS_LabBean> getLabGroups() {
		return labGroups;
	}
	public void setLabGroups(List<FS_LabBean> labGroups) {
		this.labGroups = labGroups;
	}
	public List<ClinicalElements> getClinicalElements() {
		return clinicalElements;
	}
	public void setClinicalElements(List<ClinicalElements> clinicalElements) {
		this.clinicalElements = clinicalElements;
	}
	public List<FlowsheetDx> getFlowsheetDx() {
		return flowsheetDx;
	}
	public void setFlowsheetDx(List<FlowsheetDx> flowsheetDx) {
		this.flowsheetDx = flowsheetDx;
	}
	public List<LabStandardGroup> getLabStandardGroup() {
		return labStandardGroup;
	}
	public void setLabStandardGroup(List<LabStandardGroup> labStandardGroup) {
		this.labStandardGroup = labStandardGroup;
	}
	public List<ParamStandardGroup> getParamStandardGroup() {
		return paramStandardGroup;
	}
	public void setParamStandardGroup(List<ParamStandardGroup> paramStandardGroup) {
		this.paramStandardGroup = paramStandardGroup;
	}
	public String getMessageAfterSave() {
		return messageAfterSave;
	}
	public void setMessageAfterSave(String messageAfterSave) {
		this.messageAfterSave = messageAfterSave;
	}
	public Integer getCurrentGroupIdAfterSave() {
		return currentGroupIdAfterSave;
	}
	public void setCurrentGroupIdAfterSave(Integer currentGroupIdAfterSave) {
		this.currentGroupIdAfterSave = currentGroupIdAfterSave;
	}
	public String getCurrentGroupNameAfterSave() {
		return currentGroupNameAfterSave;
	}
	public void setCurrentGroupNameAfterSave(String currentGroupNameAfterSave) {
		this.currentGroupNameAfterSave = currentGroupNameAfterSave;
	}
}
