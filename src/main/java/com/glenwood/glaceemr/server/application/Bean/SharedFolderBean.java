package com.glenwood.glaceemr.server.application.Bean;

import java.util.Map;
/**
 * Bean to hold the shared folder paths across the context.
 * 
 * @author Mohammad Shadab
 *
 */
@SuppressWarnings("rawtypes")
public class SharedFolderBean  
	
{ 
	
	private Map sharedFolderPath;

	public Map getSharedFolderPath() {
		return sharedFolderPath;
	}

	public void setSharedFolderPath(Map sharedFolderPath) {
		this.sharedFolderPath = sharedFolderPath;
	}

	
	
}
