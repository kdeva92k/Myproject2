package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class FlowsheetManagementSaveBean {
	Integer sheetId;
	Integer sheetTypeId;
	String sheetName;
	String dataToSave="{}";
	public Integer getSheetId() {
		return sheetId;
	}
	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}
	public String getDataToSave() {
		return dataToSave;
	}
	public void setDataToSave(String dataToSave) {
		this.dataToSave = dataToSave;
	}
	public Integer getSheetTypeId() {
		return sheetTypeId;
	}
	public void setSheetTypeId(Integer sheetTypeId) {
		this.sheetTypeId = sheetTypeId;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
