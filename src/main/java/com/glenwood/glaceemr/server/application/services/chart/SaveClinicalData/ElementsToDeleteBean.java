package com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData;
public class ElementsToDeleteBean{
	private long elemenetsToDeleteGWId;
	private int elemenetsToDeleteType;
	private String elemenetsToDeleteText;
	private boolean elemenetsToDeleteBoolean;
	private int elemenetsToDeleteOption;
	private int elemenetsToDeleteNumber;
	private String valueToDelete;
	public ElementsToDeleteBean(){
		elemenetsToDeleteGWId=-1;
		elemenetsToDeleteType=-1;
		elemenetsToDeleteText="";
		elemenetsToDeleteBoolean=false;
		elemenetsToDeleteOption=-1;
		elemenetsToDeleteNumber=-1;
		valueToDelete="";
	}
	public long getElemenetsToDeleteGWId() {
		return elemenetsToDeleteGWId;
	}
	public void setElemenetsToDeleteGWId(long elemenetsToDeleteGWId) {
		this.elemenetsToDeleteGWId = elemenetsToDeleteGWId;
	}
	public String getElemenetsToDeleteText() {
		return elemenetsToDeleteText;
	}
	public void setElemenetsToDeleteText(String elemenetsToDeleteText) {
		this.elemenetsToDeleteText = elemenetsToDeleteText;
	}
	public boolean isElemenetsToDeleteBoolean() {
		return elemenetsToDeleteBoolean;
	}
	public void setElemenetsToDeleteBoolean(boolean elemenetsToDeleteBoolean) {
		this.elemenetsToDeleteBoolean = elemenetsToDeleteBoolean;
	}
	public int getElemenetsToDeleteOption() {
		return elemenetsToDeleteOption;
	}
	public void setElemenetsToDeleteOption(int elemenetsToDeleteOption) {
		this.elemenetsToDeleteOption = elemenetsToDeleteOption;
	}
	public int getElemenetsToDeleteNumber() {
		return elemenetsToDeleteNumber;
	}
	public void setElemenetsToDeleteNumber(int elemenetsToDeleteNumber) {
		this.elemenetsToDeleteNumber = elemenetsToDeleteNumber;
	}
	public String getValueToDelete() {
		return valueToDelete;
	}
	public void setValueToDelete(String valueToDelete) {
		this.valueToDelete = valueToDelete;
	}
	public int getElemenetsToDeleteType() {
	    return elemenetsToDeleteType;
	}
	public void setElemenetsToDeleteType(int elemenetsToDeleteType) {
	    this.elemenetsToDeleteType = elemenetsToDeleteType;
	}
}