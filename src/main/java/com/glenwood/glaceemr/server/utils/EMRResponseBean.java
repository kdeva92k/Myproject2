package com.glenwood.glaceemr.server.utils;


import org.springframework.stereotype.Component;

@Component
public class EMRResponseBean {
	public Boolean login;
	public Boolean success;
	public Boolean isAuthorizationPresent;
	public Boolean canUserAccess;
	public String startTime;
	public Object data;
	
	
	
	public Boolean getLogin() {
		return login;
	}
	public void setLogin(Boolean login) {
		this.login = login;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Boolean getIsAuthorizationPresent() {
		return isAuthorizationPresent;
	}
	public void setIsAuthorizationPresent(Boolean isAuthorizationPresent) {
		this.isAuthorizationPresent = isAuthorizationPresent;
	}
	public Boolean getCanUserAccess() {
		return canUserAccess;
	}
	public void setCanUserAccess(Boolean canUserAccess) {
		this.canUserAccess = canUserAccess;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public void restoreDefaults(){
		setSuccess(null);
		setData(null);
		setIsAuthorizationPresent(null);
		setCanUserAccess(null);
	}
	@Override
	public String toString() {
		return "EMRResponseBean [login=" + login + ", success=" + success
				+ ", isAuthorizationPresent=" + isAuthorizationPresent
				+ ", canUserAccess=" + canUserAccess + ", data=" + data + "]";
	}

	
}
