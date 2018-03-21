package com.glenwood.glaceemr.server.application.services.chart.plan;

public class ReturnVisitData {

	String gwid;	
	String mgwid;
	Integer type;
	Boolean isactive;
	Boolean format;
	String name;
	String value;	
	Integer order;
	
	public ReturnVisitData(String gwid, String mgwid, Integer type,
			Boolean isactive, String name, String value,
			Integer order) {
		this.gwid = gwid;
		this.mgwid = mgwid;
		this.type = type;
		this.isactive = isactive;
		this.name = name;
		this.value = value;
		this.order = order;
	}
	
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getMgwid() {
		return mgwid;
	}
	public void setMgwid(String mgwid) {
		this.mgwid = mgwid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	public Boolean getFormat() {
		return format;
	}
	public void setFormat(Boolean format) {
		this.format = format;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
