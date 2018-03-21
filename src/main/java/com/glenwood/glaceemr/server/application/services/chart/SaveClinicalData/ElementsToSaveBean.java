package com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData;
public class ElementsToSaveBean{
	private long elemenetsToSaveGWId;
	private int elemenetsToSaveType;
	private String elemenetsToSaveText;
	private boolean elemenetsToSaveBoolean;
	private int elemenetsToSaveOption;
	private int elemenetsToSaveNumber;
	private String valueToSave;
	public ElementsToSaveBean(){

		elemenetsToSaveGWId=-1;
		elemenetsToSaveType=-1;
		elemenetsToSaveText="";
		elemenetsToSaveBoolean=false;
		elemenetsToSaveOption=-1;
		elemenetsToSaveNumber=-1;
		valueToSave="";
	}
	public long getElemenetsToSaveGWId() {
		return elemenetsToSaveGWId;
	}
	public void setElemenetsToSaveGWId(long elemenetsToSaveGWId) {
		this.elemenetsToSaveGWId = elemenetsToSaveGWId;
	}
	public String getElemenetsToSaveText() {
		return elemenetsToSaveText;
	}
	public void setElemenetsToSaveText(String elemenetsToSaveText) {
		this.elemenetsToSaveText = elemenetsToSaveText;
	}
	public Boolean getElemenetsToSaveBoolean() {
		return elemenetsToSaveBoolean;
	}
	public void setElemenetsToSaveBoolean(Boolean elemenetsToSaveBoolean) {
		this.elemenetsToSaveBoolean = elemenetsToSaveBoolean;
	}
	public int getElemenetsToSaveOption() {
		return elemenetsToSaveOption;
	}
	public void setElemenetsToSaveOption(int elemenetsToSaveOption) {
		this.elemenetsToSaveOption = elemenetsToSaveOption;
	}
	public int getElemenetsToSaveNumber() {
		return elemenetsToSaveNumber;
	}
	public void setElemenetsToSaveNumber(int elemenetsToSaveNumber) {
		this.elemenetsToSaveNumber = elemenetsToSaveNumber;
	}
	public String getValueToSave() {
		return valueToSave;
	}
	public void setValueToSave(String valueToSave) {
		this.valueToSave = valueToSave;
	}
	public int getElemenetsToSaveType() {
	    return elemenetsToSaveType;
	}
	public void setElemenetsToSaveType(int elemenetsToSaveType) {
	    this.elemenetsToSaveType = elemenetsToSaveType;
	}
}