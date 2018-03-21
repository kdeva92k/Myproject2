package com.glenwood.glaceemr.server.application.services.portal.portalSettings;

public class SSODetailsBean {

	String gwt_lan_url;
	
	String accountID;
	
	String Glace_spring_URL;
	
	String Glace_tomcat_URL;
	
	String Glace_apache_URL;

	public String getGwt_lan_url() {
		return gwt_lan_url;
	}

	public void setGwt_lan_url(String gwt_lan_url) {
		this.gwt_lan_url = gwt_lan_url;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getGlace_spring_URL() {
		return Glace_spring_URL;
	}

	public void setGlace_spring_URL(String glace_spring_URL) {
		Glace_spring_URL = glace_spring_URL;
	}

	public String getGlace_tomcat_URL() {
		return Glace_tomcat_URL;
	}

	public void setGlace_tomcat_URL(String glace_tomcat_URL) {
		Glace_tomcat_URL = glace_tomcat_URL;
	}

	public String getGlace_apache_URL() {
		return Glace_apache_URL;
	}

	public void setGlace_apache_URL(String glace_apache_URL) {
		Glace_apache_URL = glace_apache_URL;
	}
	
}
