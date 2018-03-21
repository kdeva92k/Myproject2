package com.glenwood.glaceemr.server.application.services.chart.clinicalElements;

import java.util.ArrayList;
import java.util.List;

import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;


public class ClinicalElementBean {
	private String clinicalElementName;
	private String clinicalElementGWID;
	private String clinicalElementNotes;
	private Integer clinicalElementDataType;
	private String clinicalElementCPT;
	private String clinicalElementICD9;
	private String clinicalElementSNOMED;
	private Boolean clinicalElementIsActive;
	private Boolean clinicalElementIsGlobal;
	private Boolean clinicalElementIsHistory;
	private Boolean clinicalElementIsEpisode;
	private String clinicalElementTextDimension;
	private Integer clinicalElementGender;
	private Integer clinicalElementIsSelect;
	private List<ClinicalTextMapping> clinicalTextMappings;
	
	public ClinicalElementBean(String clinicalElementName,
			String clinicalElementGWID, String clinicalElementNotes,
			Integer clinicalElementDataType, String clinicalElementCPT,
			String clinicalElementICD9, String clinicalElementSNOMED,
			Boolean clinicalElementIsActive, Boolean clinicalElementIsGlobal,
			Boolean clinicalElementIsHistory, Boolean clinicalElementIsEpisode,
			String clinicalElementTextDimension, Integer clinicalElementGender,
			Integer clinicalElementIsSelect,
			List<ClinicalTextMapping> clinicalTextMappings) {
		super();
		this.clinicalElementName = clinicalElementName;
		this.clinicalElementGWID = clinicalElementGWID;
		this.clinicalElementNotes = clinicalElementNotes;
		this.clinicalElementDataType = clinicalElementDataType;
		this.clinicalElementCPT = clinicalElementCPT;
		this.clinicalElementICD9 = clinicalElementICD9;
		this.clinicalElementSNOMED = clinicalElementSNOMED;
		this.clinicalElementIsActive = clinicalElementIsActive;
		this.clinicalElementIsGlobal = clinicalElementIsGlobal;
		this.clinicalElementIsHistory = clinicalElementIsHistory;
		this.clinicalElementIsEpisode = clinicalElementIsEpisode;
		this.clinicalElementTextDimension = clinicalElementTextDimension;
		this.clinicalElementGender = clinicalElementGender;
		this.clinicalElementIsSelect = clinicalElementIsSelect;
		this.clinicalTextMappings = clinicalTextMappings;
	}

	
	public ClinicalElementBean(String clinicalElementName,
			String clinicalElementGWID, String clinicalElementNotes,
			Integer clinicalElementDataType, String clinicalElementCPT,
			String clinicalElementICD9, String clinicalElementSNOMED,
			Boolean clinicalElementIsActive, Boolean clinicalElementIsGlobal,
			Boolean clinicalElementIsHistory, Boolean clinicalElementIsEpisode,
			String clinicalElementTextDimension, Integer clinicalElementGender,
			Integer clinicalElementIsSelect) {
		this.clinicalElementName = clinicalElementName;
		this.clinicalElementGWID = clinicalElementGWID;
		this.clinicalElementNotes = clinicalElementNotes;
		this.clinicalElementDataType = clinicalElementDataType;
		this.clinicalElementCPT = clinicalElementCPT;
		this.clinicalElementICD9 = clinicalElementICD9;
		this.clinicalElementSNOMED = clinicalElementSNOMED;
		this.clinicalElementIsActive = clinicalElementIsActive;
		this.clinicalElementIsGlobal = clinicalElementIsGlobal;
		this.clinicalElementIsHistory = clinicalElementIsHistory;
		this.clinicalElementIsEpisode = clinicalElementIsEpisode;
		this.clinicalElementTextDimension = clinicalElementTextDimension;
		this.clinicalElementGender = clinicalElementGender;
		this.clinicalElementIsSelect = clinicalElementIsSelect;
	}

	public ClinicalElementBean(){
		clinicalElementName="";
		clinicalElementGWID="-1";
		clinicalElementNotes="";
		clinicalElementDataType=-1;
		clinicalElementCPT="";
		clinicalElementICD9="";
		clinicalElementSNOMED="";
		clinicalElementIsActive=false;
		clinicalElementIsGlobal=false;
		clinicalElementIsHistory=false;
		clinicalElementIsEpisode=false;
		clinicalElementGender=0;
		clinicalElementTextDimension="10";
		clinicalElementIsSelect = -1;
		clinicalTextMappings=new ArrayList<ClinicalTextMapping>();
	}
	
	
	public List<ClinicalTextMapping> getClinicalTextMappings() {
		return clinicalTextMappings;
	}


	public void setClinicalTextMappings(List<ClinicalTextMapping> clinicalTextMappings) {
		this.clinicalTextMappings = clinicalTextMappings;
	}

	
	
	public String getClinicalElementName() {
		return clinicalElementName;
	}
	public void setClinicalElementName(String clinicalElementName) {
		this.clinicalElementName = clinicalElementName;
	}
	public String getClinicalElementNotes() {
		return clinicalElementNotes;
	}
	public void setClinicalElementNotes(String clinicalElementNotes) {
		this.clinicalElementNotes = clinicalElementNotes;
	}
	public Integer getClinicalElementDataType() {
		return clinicalElementDataType;
	}
	public void setClinicalElementDataType(Integer clinicalElementDataType) {
		this.clinicalElementDataType = clinicalElementDataType;
	}
	public String getClinicalElementCPT() {
		return clinicalElementCPT;
	}
	public void setClinicalElementCPT(String clinicalElementCPT) {
		this.clinicalElementCPT = clinicalElementCPT;
	}
	public String getClinicalElementICD9() {
		return clinicalElementICD9;
	}
	public void setClinicalElementICD9(String clinicalElementICD9) {
		this.clinicalElementICD9 = clinicalElementICD9;
	}
	public String getClinicalElementSNOMED() {
		return clinicalElementSNOMED;
	}
	public void setClinicalElementSNOMED(String clinicalElementSNOMED) {
		this.clinicalElementSNOMED = clinicalElementSNOMED;
	}
	public Boolean getClinicalElementIsActive() {
		return clinicalElementIsActive;
	}
	public void setClinicalElementIsActive(Boolean clinicalElementIsActive) {
		this.clinicalElementIsActive = clinicalElementIsActive;
	}
	public Boolean getClinicalElementIsGlobal() {
		return clinicalElementIsGlobal;
	}
	public void setClinicalElementIsGlobal(Boolean clinicalElementIsGlobal) {
		this.clinicalElementIsGlobal = clinicalElementIsGlobal;
	}
	public Boolean getClinicalElementIsHistory() {
		return clinicalElementIsHistory;
	}
	public void setClinicalElementIsHistory(Boolean clinicalElementIsHistory) {
		this.clinicalElementIsHistory = clinicalElementIsHistory;
	}
	public Boolean getClinicalElementIsEpisode() {
		return clinicalElementIsEpisode;
	}
	public void setClinicalElementIsEpisode(Boolean clinicalElementIsEpisode) {
		this.clinicalElementIsEpisode = clinicalElementIsEpisode;
	}
	public Integer getClinicalElementGender() {
		return clinicalElementGender;
	}
	public void setClinicalElementGender(Integer clinicalElementGender) {
		this.clinicalElementGender = clinicalElementGender;
	}
	public String getClinicalElementTextDimension() {
		return clinicalElementTextDimension;
	}
	public void setClinicalElementTextDimension(String clinicalElementTextDimension) {
		this.clinicalElementTextDimension = clinicalElementTextDimension;
	}
	public Integer getClinicalElementIsSelect() {
		return clinicalElementIsSelect;
	}
	public void setClinicalElementIsSelect(Integer clinicalElementIsSelect) {
		this.clinicalElementIsSelect = clinicalElementIsSelect;
	}
	public String getClinicalElementGWID() {
		return clinicalElementGWID;
	}
	public void setClinicalElementGWID(String clinicalElementGWID) {
		this.clinicalElementGWID = clinicalElementGWID;
	}
	
}