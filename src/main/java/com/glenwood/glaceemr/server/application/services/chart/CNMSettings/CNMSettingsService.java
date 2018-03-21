package com.glenwood.glaceemr.server.application.services.chart.CNMSettings;


public interface CNMSettingsService {

	public boolean getCNMSettingsIsActiveById(Integer id);
	
	public boolean getCNMSettingsIsActiveByName(String name);
	
	public boolean getCNMSettingsIsActiveByIdAndName(Integer id,String name);
}
