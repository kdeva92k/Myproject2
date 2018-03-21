package com.glenwood.glaceemr.server.application.services.chart.HPI;
public class HPIElementBean{
	private String elementGWId;
	private String elementname;
	private String elementPrintText;
	private String clinicalElementName;
	private int clinicalElementDataType;
	private boolean clinicalElementIsActive;
	private boolean clinicalElementIsHistory;
	private boolean clinicalElementIsEpisode;
	private String clinicalElementTextDimension;
	private int clinicalElementGender;
	private int clinicalElementIsSelect;
	private String value;
	
	public HPIElementBean(){
		elementGWId="";
		elementname="";
		elementPrintText="";
		clinicalElementName="";
		clinicalElementDataType=-1;
		clinicalElementIsActive=false;
		clinicalElementIsHistory=false;
		clinicalElementIsEpisode=false;
		clinicalElementGender=0;
		clinicalElementTextDimension="10";
		clinicalElementIsSelect = -1;
		value="";
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getClinicalElementName() {
		return clinicalElementName;
	}
	public void setClinicalElementName(String clinicalElementName) {
		this.clinicalElementName = clinicalElementName;
	}
	public int getClinicalElementDataType() {
		return clinicalElementDataType;
	}
	public void setClinicalElementDataType(int clinicalElementDataType) {
		this.clinicalElementDataType = clinicalElementDataType;
	}
	public Boolean getClinicalElementIsActive() {
		return clinicalElementIsActive;
	}
	public void setClinicalElementIsActive(boolean clinicalElementIsActive) {
		this.clinicalElementIsActive = clinicalElementIsActive;
	}
	
	public boolean getClinicalElementIsHistory() {
		return clinicalElementIsHistory;
	}
	public void setClinicalElementIsHistory(boolean clinicalElementIsHistory) {
		this.clinicalElementIsHistory = clinicalElementIsHistory;
	}
	public boolean getClinicalElementIsEpisode() {
		return clinicalElementIsEpisode;
	}
	public void setClinicalElementIsEpisode(boolean clinicalElementIsEpisode) {
		this.clinicalElementIsEpisode = clinicalElementIsEpisode;
	}
	public String getClinicalElementTextDimension() {
		return clinicalElementTextDimension;
	}
	public void setClinicalElementTextDimension(String clinicalElementTextDimension) {
		this.clinicalElementTextDimension = clinicalElementTextDimension;
	}
	public int getClinicalElementGender() {
		return clinicalElementGender;
	}
	public void setClinicalElementGender(int clinicalElementGender) {
		this.clinicalElementGender = clinicalElementGender;
	}
	public int getClinicalElementIsSelect() {
		return clinicalElementIsSelect;
	}
	public void setClinicalElementIsSelect(int clinicalElementIsSelect) {
		this.clinicalElementIsSelect = clinicalElementIsSelect;
	}
	public String getElementGWId() {
		return elementGWId;
	}
	public void setElementGWId(String elementGWId) {
		this.elementGWId = elementGWId;
	}
	public String getElementname() {
		return elementname;
	}
	public void setElementname(String elementname) {
		this.elementname = elementname;
	}
	public String getElementPrintText() {
		return elementPrintText;
	}
	public void setElementPrintText(String elementPrintText) {
		this.elementPrintText = elementPrintText;
	}
	
}