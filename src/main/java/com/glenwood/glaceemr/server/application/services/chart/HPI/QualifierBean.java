package com.glenwood.glaceemr.server.application.services.chart.HPI;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class QualifierBean{
	private String qualifierName;
	private int qualifierId;
	private String qualifierGWId;
	private String qualifierEandM;
	private String qualifierPrintText;
	private LinkedHashMap<String, HPIElementBean> qualifierElements;
	public QualifierBean(){
		qualifierName="";
		qualifierId=-1;
		qualifierElements=new LinkedHashMap<String, HPIElementBean>();
		qualifierGWId="";
		qualifierEandM="";
		qualifierPrintText="";
	}
	public String getQualifierGWId() {
		return qualifierGWId;
	}
	public void setQualifierGWId(String qualifierGWId) {
		this.qualifierGWId = qualifierGWId;
	}
	public String getQualifierName() {
		return qualifierName;
	}
	public void setQualifierName(String qualifierName) {
		this.qualifierName = qualifierName;
	}
	public LinkedHashMap<String, HPIElementBean> getQualifierElements() {
		return qualifierElements;
	}
	public void setQualifierElements(
			LinkedHashMap<String, HPIElementBean> qualifierElements) {
		this.qualifierElements = qualifierElements;
	}
	public int getQualifierId() {
		return qualifierId;
	}
	public void setQualifierId(int qualifierId) {
		this.qualifierId = qualifierId;
	}
	public String getQualifierEandM() {
		return qualifierEandM;
	}
	public void setQualifierEandM(String qualifierEandM) {
		this.qualifierEandM = qualifierEandM;
	}
	
	public String getqualifierPrintText() {
		return qualifierPrintText;
	}
	public void setqualifierPrintText(String qualifierPrintText) {
		this.qualifierPrintText = qualifierPrintText;
	}
	
}