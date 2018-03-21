package com.glenwood.glaceemr.server.application.services.fax;

import java.util.List;

public class FaxUsersBean {

	String name;
	List<FaxForwardListBean> usersList;
	
	public FaxUsersBean(String name, List<FaxForwardListBean> usersList) {
		super();
		this.name = name;
		this.usersList = usersList;
	}
	
	public FaxUsersBean() {
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<FaxForwardListBean> getUsersList() {
		return usersList;
	}
	
	public void setUsersList(List<FaxForwardListBean> usersList) {
		this.usersList = usersList;
	}
	
}