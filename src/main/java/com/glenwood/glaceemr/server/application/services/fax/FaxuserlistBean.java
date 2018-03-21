package com.glenwood.glaceemr.server.application.services.fax;

public class FaxuserlistBean {
	
	Integer empProfileEmpid;
	String empProfileFullname;
	Integer empProfileLoginid;
	String fileName;
	
	public Integer getEmpProfileLoginid() {
		return empProfileLoginid;
	}

	public void setEmpProfileLoginid(Integer empProfileLoginid) {
		this.empProfileLoginid = empProfileLoginid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FaxuserlistBean(String empProfileFullname,
			Integer empProfileLoginid, String fileName) {
		super();
		this.empProfileFullname = empProfileFullname;
		this.empProfileLoginid = empProfileLoginid;
		this.fileName = fileName;
	}
	
	public FaxuserlistBean(Integer empProfileEmpid, String empProfileFullname) {
		super();
		this.empProfileEmpid = empProfileEmpid;
		this.empProfileFullname = empProfileFullname;
	}

	public Integer getEmpProfileEmpid() {
		return empProfileEmpid;
	}
	
	public void setEmpProfileEmpid(Integer empProfileEmpid) {
		this.empProfileEmpid = empProfileEmpid;
	}
	
	public String getEmpProfileFullname() {
		return empProfileFullname;
	}
	
	public void setEmpProfileFullname(String empProfileFullname) {
		this.empProfileFullname = empProfileFullname;
	}
}	
