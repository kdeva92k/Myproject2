package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import java.util.ArrayList;
import java.util.List;
import com.glenwood.glaceemr.server.application.models.Icdm;

public class FlowsheetBean {
	private Boolean chartBased;
	private List<FlowsheetNameBean> flowsheetList;
	private String flowsheetName;
	private int flowsheetId;
	private int flowsheetType;
	private List<Icdm> dxData;
	private ArrayList<FS_LabBean> labData;
	private ArrayList<FS_LabParameterBean> paramData;
	private ArrayList<String> paramDateArr;
	private ArrayList<FS_DrugBean> drugData;
//	private ArrayList<FS_ClinicalNotes> notesData;
	private ArrayList<FS_ClinicalElementBean> clinicalPlanData;
	private ArrayList<FS_ClinicalElementBean> clinicalVitalsData;
	private ArrayList<String> vitalDateArr;
//	private String onSetDate;	
	public String getFlowsheetName() {
		return flowsheetName;
	}
	public void setFlowsheetName(String flowsheetName) {
		this.flowsheetName = flowsheetName;
	}
	public int getFlowsheetId() {
		return flowsheetId;
	}
	public void setFlowsheetId(int flowsheetId) {
		this.flowsheetId = flowsheetId;
	}
	public int getFlowsheetType() {
		return flowsheetType;
	}
	public void setFlowsheetType(int flowsheetType) {
		this.flowsheetType = flowsheetType;
	}
	public List<Icdm> getDxData() {
		return dxData;
	}
	public void setDxData(List<Icdm> dxData) {
		this.dxData = dxData;
	}
	public ArrayList<FS_LabBean> getLabData() {
		return labData;
	}
	public void setLabData(ArrayList<FS_LabBean> labData) {
		this.labData = labData;
	}
	public ArrayList<FS_LabParameterBean> getParamData() {
		return paramData;
	}
	public void setParamData(ArrayList<FS_LabParameterBean> paramData) {
		this.paramData = paramData;
	}
	public ArrayList<String> getParamDateArr() {
		return paramDateArr;
	}
	public void setParamDateArr(ArrayList<String> paramDateArr) {
		this.paramDateArr = paramDateArr;
	}
	public List<FlowsheetNameBean> getFlowsheetList() {
		return flowsheetList;
	}
	public void setFlowsheetList(List<FlowsheetNameBean> flowsheetList) {
		this.flowsheetList = flowsheetList;
	}
	public ArrayList<FS_ClinicalElementBean> getClinicalVitalsData() {
		return clinicalVitalsData;
	}
	public void setClinicalVitalsData(
			ArrayList<FS_ClinicalElementBean> clinicalVitalsData) {
		this.clinicalVitalsData = clinicalVitalsData;
	}
	public ArrayList<String> getVitalDateArr() {
		return vitalDateArr;
	}
	public void setVitalDateArr(ArrayList<String> vitalDateArr) {
		this.vitalDateArr = vitalDateArr;
	}
	public ArrayList<FS_ClinicalElementBean> getClinicalPlanData() {
		return clinicalPlanData;
	}
	public void setClinicalPlanData(
			ArrayList<FS_ClinicalElementBean> clinicalPlanData) {
		this.clinicalPlanData = clinicalPlanData;
	}
	public ArrayList<FS_DrugBean> getDrugData() {
		return drugData;
	}
	public void setDrugData(ArrayList<FS_DrugBean> drugData) {
		this.drugData = drugData;
	}
	public Boolean getChartBased() {
		return chartBased;
	}
	public void setChartBased(Boolean chartBased) {
		this.chartBased = chartBased;
	}
}
