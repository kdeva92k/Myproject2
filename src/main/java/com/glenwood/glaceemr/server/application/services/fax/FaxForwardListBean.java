package com.glenwood.glaceemr.server.application.services.fax;

public class FaxForwardListBean {

	Integer hsp001;
	String hsp002;

	public FaxForwardListBean() {
		super();
	}
	public FaxForwardListBean(Integer hsp001, String hsp002) {
		super();
		this.hsp001 = hsp001;
		this.hsp002 = hsp002;
	}
	public Integer getHsp001() {
		return hsp001;
	}
	public void setHsp001(Integer hsp001) {
		this.hsp001 = hsp001;
	}
	public String getHsp002() {
		return hsp002;
	}
	public void setHsp002(String hsp002) {
		this.hsp002 = hsp002;
	}

}