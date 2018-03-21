package com.glenwood.glaceemr.server.application.services.chart.plan;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

@Component
public class PlanBean {

	LinkedHashMap<Integer, String> PlanTypes= new LinkedHashMap<Integer, String>();
	LinkedHashMap<String, PlanElementBean> PlanInstructions= new LinkedHashMap<String, PlanElementBean>();
	
	public LinkedHashMap<Integer, String> getPlanTypes() {
		return PlanTypes;
	}
	public void setPlanTypes(LinkedHashMap<Integer, String> planTypes) {
		PlanTypes = planTypes;
	}
	public LinkedHashMap<String, PlanElementBean> getPlanInstructions() {
		return PlanInstructions;
	}
	public void setPlanInstructions(
			LinkedHashMap<String, PlanElementBean> planInstructions) {
		PlanInstructions = planInstructions;
	}
	
	
}
