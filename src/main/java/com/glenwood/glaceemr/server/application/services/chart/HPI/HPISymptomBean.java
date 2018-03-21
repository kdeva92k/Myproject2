package com.glenwood.glaceemr.server.application.services.chart.HPI;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalSystem;

public class HPISymptomBean{

	
	private Integer hpiSymptomId;

	private String hpiSymptomGwid;

	private Integer hpiSymptomSystemId;

	private String hpiSymptomName;

	private String hpiSymptomComments;

	private Integer hpiSymptomType;

	private String hpiSymptomPrinttext;

	private Integer hpiSymptomHitcount;

	private Integer hpiSymptomOrderby;

	private Boolean hpiSymptomIsactive;

	private Boolean hpiSymptomRetaincase;
	
	private String limitOrder;
	
	private ClinicalSystem clinicalSystem;
	
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping;
	
	public Integer getHpiSymptomId() {
		return hpiSymptomId;
	}

	public void setHpiSymptomId(Integer hpiSymptomId) {
		this.hpiSymptomId = hpiSymptomId;
	}

	public String getHpiSymptomGwid() {
		return hpiSymptomGwid;
	}

	public void setHpiSymptomGwid(String hpiSymptomGwid) {
		this.hpiSymptomGwid = hpiSymptomGwid;
	}

	public Integer getHpiSymptomSystemId() {
		return hpiSymptomSystemId;
	}

	public void setHpiSymptomSystemId(Integer hpiSymptomSystemId) {
		this.hpiSymptomSystemId = hpiSymptomSystemId;
	}

	public String getHpiSymptomName() {
		return hpiSymptomName;
	}

	public void setHpiSymptomName(String hpiSymptomName) {
		this.hpiSymptomName = hpiSymptomName;
	}

	public String getHpiSymptomComments() {
		return hpiSymptomComments;
	}

	public void setHpiSymptomComments(String hpiSymptomComments) {
		this.hpiSymptomComments = hpiSymptomComments;
	}

	public Integer getHpiSymptomType() {
		return hpiSymptomType;
	}

	public void setHpiSymptomType(Integer hpiSymptomType) {
		this.hpiSymptomType = hpiSymptomType;
	}

	public String getHpiSymptomPrinttext() {
		return hpiSymptomPrinttext;
	}

	public void setHpiSymptomPrinttext(String hpiSymptomPrinttext) {
		this.hpiSymptomPrinttext = hpiSymptomPrinttext;
	}

	public Integer getHpiSymptomHitcount() {
		return hpiSymptomHitcount;
	}

	public void setHpiSymptomHitcount(Integer hpiSymptomHitcount) {
		this.hpiSymptomHitcount = hpiSymptomHitcount;
	}

	public Integer getHpiSymptomOrderby() {
		return hpiSymptomOrderby;
	}

	public void setHpiSymptomOrderby(Integer hpiSymptomOrderby) {
		this.hpiSymptomOrderby = hpiSymptomOrderby;
	}

	public Boolean getHpiSymptomIsactive() {
		return hpiSymptomIsactive;
	}

	public void setHpiSymptomIsactive(Boolean hpiSymptomIsactive) {
		this.hpiSymptomIsactive = hpiSymptomIsactive;
	}

	public Boolean getHpiSymptomRetaincase() {
		return hpiSymptomRetaincase;
	}

	public void setHpiSymptomRetaincase(Boolean hpiSymptomRetaincase) {
		this.hpiSymptomRetaincase = hpiSymptomRetaincase;
	}

	public ClinicalSystem getClinicalSystem() {
		return clinicalSystem;
	}

	public void setClinicalSystem(ClinicalSystem clinicalSystem) {
		this.clinicalSystem = clinicalSystem;
	}

	public String getLimitOrder() {
		return limitOrder;
	}

	public void setLimitOrder(String limitOrder) {
		this.limitOrder = limitOrder;
	}

	public List<ClinicalElementTemplateMapping> getClinicalElementTemplateMapping() {
		return clinicalElementTemplateMapping;
	}

	public void setClinicalElementTemplateMapping(
			List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping) {
		this.clinicalElementTemplateMapping = clinicalElementTemplateMapping;
	}

}