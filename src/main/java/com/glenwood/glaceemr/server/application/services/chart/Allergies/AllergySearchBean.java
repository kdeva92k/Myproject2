package com.glenwood.glaceemr.server.application.services.chart.Allergies;

public class AllergySearchBean {
	private String Code;
	private String Substance;
	private String codesystemid;
	
	public AllergySearchBean(){

	}
	
	public AllergySearchBean(String Code,String Substance,String codesystemid){
		super();
		if(Code!=null){
			this.Code=Code;
		}

		if(Substance!=null){
			this.Substance=Substance;
		}
		if(codesystemid!= null){
			this.codesystemid=codesystemid;
		}

	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getSubstance() {
		return Substance;
	}

	public void setSubstance(String substance) {
		Substance = substance;
	}

	public String getCodesystemid() {
		return codesystemid;
	}

	public void setCodesystemid(String codesystemid) {
		this.codesystemid = codesystemid;
	}

		
}
