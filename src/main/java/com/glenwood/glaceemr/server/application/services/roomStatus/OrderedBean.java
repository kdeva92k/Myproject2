package com.glenwood.glaceemr.server.application.services.roomStatus;

public class OrderedBean {
	private String orderon;
	private String labname;
	private Integer status;
	private String orderby;
	private String notes;
	private String loinc;
	private String cpt;
	private Integer labtestdetailid;
	private Integer testId;
	private Integer labgroupid;
	private String labgroupname;
	
	public OrderedBean(String orderon, String labname,Integer status,String orderby,String notes,String loinc, String cpt,Integer labtestdetailid,Integer testId,Integer labgroupid,String labgroupname) {
        super();
		this.orderon = orderon;
		this.labname = labname;
		this.status = status;
		this.orderby = orderby;
		this.notes = notes;
		this.loinc = loinc;
		this.cpt = cpt;
		this.labtestdetailid = labtestdetailid;
		this.testId = testId;
		this.labgroupid = labgroupid;
		this.labgroupname = labgroupname;

	}
	
	public String getOrderon() {
		return orderon;
	}

	public void setOrderon(String orderon) {
		this.orderon = orderon;
	}

	public String getLabname() {
		return labname;
	}

	public void setLabname(String labname) {
		this.labname = labname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getLoinc() {
		return loinc;
	}

	public void setLoinc(String loinc) {
		this.loinc = loinc;
	}

	public String getCpt() {
		return cpt;
	}

	public void setCpt(String cpt) {
		this.cpt = cpt;
	}

	public Integer getLabtestdetailid() {
		return labtestdetailid;
	}

	public void setLabtestdetailid(Integer labtestdetailid) {
		this.labtestdetailid = labtestdetailid;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getLabgroupid() {
		return labgroupid;
	}

	public void setLabgroupid(Integer labgroupid) {
		this.labgroupid = labgroupid;
	}

	public String getLabgroupname() {
		return labgroupname;
	}

	public void setLabgroupname(String labgroupname) {
		this.labgroupname = labgroupname;
	}

}
