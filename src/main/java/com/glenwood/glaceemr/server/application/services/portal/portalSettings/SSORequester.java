package com.glenwood.glaceemr.server.application.services.portal.portalSettings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SSORequester {
	/*
	 * This method establishes a direct connection to SSO and gets the required information
	 * URL takes accountID and returns the TOMCAT(Glace URL)
	 * and APACHE (GWT Glace URL)
	 */
	    public StringBuffer getUrlFromSSO(String accountID) throws Exception{
	    	String connectionpath="https://sso.glaceemr.com/TestSSOAccess?accountId="+accountID;
		    String connectionUrl=connectionpath;		
			URL url = new URL(connectionUrl);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("content_type","text/plain");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
            BufferedReader br= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String statusvalue;
    		StringBuffer strval = new StringBuffer();
    		while (( statusvalue= br.readLine()) != null) {
    			strval.append(statusvalue);
    		}
    		br.close();
            return strval;	
    	}
}
