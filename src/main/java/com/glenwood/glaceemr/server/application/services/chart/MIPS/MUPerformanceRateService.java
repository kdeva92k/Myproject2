package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.util.Date;
import java.util.List;

import com.glenwood.glaceemr.server.application.Bean.MIPSPerformanceBean;
import com.glenwood.glaceemr.server.application.Bean.MacraProviderQDM;

public interface MUPerformanceRateService{

	List<Integer> getPatientsSeen(int providerId, Date startDate, Date endDate);

	void addToMacraMeasuresRate(Integer providerId, List<MIPSPerformanceBean> providerPerformance, int reportingYear, 
			Date startDate, Date endDate, boolean isACI);

	String getLastUpdatedDate();
	
	String getCompleteQPPJSON(int reportingYear, int providerId, List<MacraProviderQDM> providerInfo,List<MIPSPerformanceBean> attestationMeasures,String sharedPath);

	String getQualityJSON(int reportingYear, int providerId,List<MacraProviderQDM> providerInfo, String sharedPath);

	String getACIJSON(int reportingYear, int providerId,List<MacraProviderQDM> providerInfo,List<MIPSPerformanceBean> attestationMeasures, String sharedPath);
	
}
