package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PqrsHubSpecification {
	
	private List<MeasureCriteria> measureCriteria = new ArrayList<MeasureCriteria>();

	public List<MeasureCriteria> getMeasureCriteria() {
		return measureCriteria;
	}

	public void setMeasureCriteria(List<MeasureCriteria> measureCriteria) {
		this.measureCriteria = measureCriteria;
	}


	
}
