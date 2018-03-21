package com.glenwood.glaceemr.server.application.services.chart.dischargeVitals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glenwood.glaceemr.server.application.services.chart.vitals.CustomVitalGroup;

public class DischargeVitalBean {
	
	public List<CustomVitalGroup> vitals ;
	public Map<String, HashMap<String, String>> vitalData ;
	
	public DischargeVitalBean() {
		vitals=new ArrayList<CustomVitalGroup>();
		vitalData =new HashMap<String, HashMap<String, String>>();
		
	}
	
	public List<CustomVitalGroup> getVitals() {
		return vitals;
	}
	public void setVitals(List<CustomVitalGroup> vitals) {
		this.vitals = vitals;
	}
	public Map<String, HashMap<String, String>> getVitalData() {
		return vitalData;
	}
	public void setVitalData(Map<String, HashMap<String, String>> vitalData) {
		this.vitalData = vitalData;
	} 
	
	
	
}
