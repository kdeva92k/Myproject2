package com.glenwood.glaceemr.server.application.services.fax;

public class FaxSignListBean {

	String empProfileFullname;
	Integer empProfileLoginid;
	String loginUsersUsername;
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

	public String getLoginUsersUsername() {
		return loginUsersUsername;
	}

	public void setLoginUsersUsername(String loginUsersUsername) {
		this.loginUsersUsername = loginUsersUsername;
	}

	public FaxSignListBean(String empProfileFullname,
			Integer empProfileLoginid, String loginUsersUsername, String fileName) {
		super();
		this.empProfileFullname = empProfileFullname;
		this.empProfileLoginid = empProfileLoginid;
		this.loginUsersUsername = loginUsersUsername;
		this.fileName = fileName;

	}

	public String getEmpProfileFullname() {
		return empProfileFullname;
	}

	public void setEmpProfileFullname(String empProfileFullname) {
		this.empProfileFullname = empProfileFullname;
	}
}