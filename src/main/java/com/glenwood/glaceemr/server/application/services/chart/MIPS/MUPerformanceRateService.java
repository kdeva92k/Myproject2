package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.util.Date;
import java.util.List;

import com.glenwood.glaceemr.server.application.Bean.MIPSPerformanceBean;

public interface MUPerformanceRateService{

	List<Integer> getPatientsSeen(int providerId, Date startDate, Date endDate);

	void addToMacraMeasuresRate(Integer providerId, List<MIPSPerformanceBean> providerPerformance, int reportingYear, 
			Date startDate, Date endDate, boolean isACI);

	String getLastUpdatedDate();
	
}
