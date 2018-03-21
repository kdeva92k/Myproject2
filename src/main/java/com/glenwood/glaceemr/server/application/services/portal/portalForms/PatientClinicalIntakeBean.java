package com.glenwood.glaceemr.server.application.services.portal.portalForms;
public class PatientClinicalIntakeBean {
	private String clinicalIntakeQuestionGwid;
	private String clinicalIntakeQuestionSaveGwid;
	private String clinicalIntakeQuestionCaption;
	private String clinicalIntakeQuestionDatatype;
	private String clinicalIntakeOptionGwid;
	private String clinicalIntakeOptionSaveGwid;
	private String clinicalIntakeOptionName;
	private String clinicalIntakeOptionValue;
	private String clinicalIntakeOptionSelectBox;
	private String optValue;
	private String groupId;
	private Boolean isBoolean;

	private String patientQuestionGwid;
	private String patientQuestionSaveGwid;   
	private String patientQuestionCaption;
	private String patientQuestionType;
	private String patientOptionLength;
	private String patientOptionGwid;
	private String patientOptionSaveGwid;
	private String patientOptionValue;
	private String patientOptionName;
	private String patientOptionType;
	private String selectBox;
	private String patientOptValue;
	private String PatientElementsQuestionGwid;
	private String PatientElementsOptionGwid;
	private String clinicalElementsType;
	private String patientClinicalElementsType;
	private Boolean patientElemIsBoolean;
	
	public Boolean getPatientElemIsBoolean() {
		return patientElemIsBoolean;
	}
	public void setPatientElemIsBoolean(Boolean patientElemIsBoolean) {
		this.patientElemIsBoolean = patientElemIsBoolean;
	}
	public String getPatientClinicalElementsType() {
		return patientClinicalElementsType;
	}
	public void setPatientClinicalElementsType(String patientClinicalElementsType) {
		this.patientClinicalElementsType = patientClinicalElementsType;
	}
	private String elementGwid;
	private String elementsaveGwid;
	public String getElementsaveGwid() {
		return elementsaveGwid;
	}
	public void setElementsaveGwid(String elementsaveGwid) {
		this.elementsaveGwid = elementsaveGwid;
	}
	private String elementisActive;
	private String elementType;
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public String getElementisActive() {
		return elementisActive;
	}
	public void setElementisActive(String elementisActive) {
		this.elementisActive = elementisActive;
	}
	public String getElementGwid() {
		return elementGwid;
	}
	public void setElementGwid(String elementGwid) {
		this.elementGwid = elementGwid;
	}
	public String getElementValue() {
		return elementValue;
	}
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}
	private String elementValue;
	
	public String getOptValue() {
		return optValue;
	}
	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}
	public String getPatientElementsQuestionGwid() {
		return PatientElementsQuestionGwid;
	}
	public void setPatientElementsQuestionGwid(String patientElementsQuestionGwid) {
		PatientElementsQuestionGwid = patientElementsQuestionGwid;
	}
	public String getPatientElementsOptionGwid() {
		return PatientElementsOptionGwid;
	}
	public void setPatientElementsOptionGwid(String patientElementsOptionGwid) {
		PatientElementsOptionGwid = patientElementsOptionGwid;
	}
	public PatientClinicalIntakeBean(){
		 
	 }
	public String getClinicalIntakeQuestionGwid() {
		return clinicalIntakeQuestionGwid;
	}
	public void setClinicalIntakeQuestionGwid(String clinicalIntakeQuestionGwid) {
		this.clinicalIntakeQuestionGwid = clinicalIntakeQuestionGwid;
	}
	public String getClinicalIntakeQuestionCaption() {
		return clinicalIntakeQuestionCaption;
	}
	public void setClinicalIntakeQuestionCaption(
			String clinicalIntakeQuestionCaption) {
		this.clinicalIntakeQuestionCaption = clinicalIntakeQuestionCaption;
	}
	public String getClinicalIntakeQuestionDatatype() {
		return clinicalIntakeQuestionDatatype;
	}
	public void setClinicalIntakeQuestionDatatype(
			String clinicalIntakeQuestionDatatype) {
		this.clinicalIntakeQuestionDatatype = clinicalIntakeQuestionDatatype;
	}
	public String getClinicalIntakeOptionGwid() {
		return clinicalIntakeOptionGwid;
	}
	public void setClinicalIntakeOptionGwid(String clinicalIntakeOptionGwid) {
		this.clinicalIntakeOptionGwid = clinicalIntakeOptionGwid;
	}
	public String getClinicalIntakeOptionName() {
		return clinicalIntakeOptionName;
	}
	public void setClinicalIntakeOptionName(String clinicalIntakeOptionName) {
		this.clinicalIntakeOptionName = clinicalIntakeOptionName;
	}
	public String getClinicalIntakeOptionValue() {
		return clinicalIntakeOptionValue;
	}
	public void setClinicalIntakeOptionValue(String clinicalIntakeOptionValue) {
		this.clinicalIntakeOptionValue = clinicalIntakeOptionValue;
	}
	public String getClinicalIntakeOptionSelectBox() {
		return clinicalIntakeOptionSelectBox;
	}
	public void setClinicalIntakeOptionSelectBox(String clinicalIntakeOptionSelectBox) {
		this.clinicalIntakeOptionSelectBox = clinicalIntakeOptionSelectBox;
	}
	public String getPatientQuestionGwid() {
		return patientQuestionGwid;
	}
	public void setPatientQuestionGwid(String patientQuestionGwid) {
		this.patientQuestionGwid = patientQuestionGwid;
	}
	public String getPatientQuestionCaption() {
		return patientQuestionCaption;
	}
	public void setPatientQuestionCaption(String patientQuestionCaption) {
		this.patientQuestionCaption = patientQuestionCaption;
	}
	public String getPatientQuestionType() {
		return patientQuestionType;
	}
	public void setPatientQuestionType(String patientQuestionType) {
		this.patientQuestionType = patientQuestionType;
	}
	public String getPatientOptionGwid() {
		return patientOptionGwid;
	}
	public void setPatientOptionGwid(String patientOptionGwid) {
		this.patientOptionGwid = patientOptionGwid;
	}
	public String getPatientOptionValue() {
		return patientOptionValue;
	}
	public void setPatientOptionValue(String patientOptionValue) {
		this.patientOptionValue = patientOptionValue;
	}
	public String getPatientOptionName() {
		return patientOptionName;
	}
	public void setPatientOptionName(String patientOptionName) {
		this.patientOptionName = patientOptionName;
	}
	public String getSelectBox() {
		return selectBox;
	}
	public void setSelectBox(String selectBox) {
		this.selectBox = selectBox;
	}
	public String getPatientOptionLength() {
		return patientOptionLength;
	}
	public void setPatientOptionLength(String patientOptionLength) {
		this.patientOptionLength = patientOptionLength;
	}
	public String getPatientOptionType() {
		return patientOptionType;
	}
	public void setPatientOptionType(String patientOptionType) {
		this.patientOptionType = patientOptionType;
	}
	public String getPatientOptValue() {
		return patientOptValue;
	}
	public void setPatientOptValue(String patientOptValue) {
		this.patientOptValue = patientOptValue;
	}
	public String getClinicalIntakeQuestionSaveGwid() {
		return clinicalIntakeQuestionSaveGwid;
	}
	public void setClinicalIntakeQuestionSaveGwid(
			String clinicalIntakeQuestionSaveGwid) {
		this.clinicalIntakeQuestionSaveGwid = clinicalIntakeQuestionSaveGwid;
	}
	public String getClinicalIntakeOptionSaveGwid() {
		return clinicalIntakeOptionSaveGwid;
	}
	public void setClinicalIntakeOptionSaveGwid(String clinicalIntakeOptionSaveGwid) {
		this.clinicalIntakeOptionSaveGwid = clinicalIntakeOptionSaveGwid;
	}
	public String getPatientQuestionSaveGwid() {
		return patientQuestionSaveGwid;
	}
	public void setPatientQuestionSaveGwid(String patientQuestionSaveGwid) {
		this.patientQuestionSaveGwid = patientQuestionSaveGwid;
	}
	public String getPatientOptionSaveGwid() {
		return patientOptionSaveGwid;
	}
	public void setPatientOptionSaveGwid(String patientOptionSaveGwid) {
		this.patientOptionSaveGwid = patientOptionSaveGwid;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getClinicalElementsType() {
		return clinicalElementsType;
	}
	public void setClinicalElementsType(String clinicalElementsType) {
		this.clinicalElementsType = clinicalElementsType;
	}
	public Boolean getIsBoolean() {
		return isBoolean;
	}
	public void setIsBoolean(Boolean isBoolean) {
		this.isBoolean = isBoolean;
	}
}