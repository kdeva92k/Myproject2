package com.glenwood.glaceemr.server.application.services.chartcenter;

import java.util.List;

import org.springframework.data.domain.Page;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistrationSearchBean;

public interface ChartcenterService {
	
	/**
	 * @param toSearchData:json object with lastname,firstname,pagesize,searchmode
	 * @return
	 * @throws Exception
	 */
	public Page<PatientRegistration>  getPatientSearchResult(String toSearchData,String searchTypeParam)throws Exception;

	public List<PatientRegistrationSearchBean> getPatientNameBySearch(String toSearchData) throws Exception;
	
	public int getChartIdByPatientId(Integer patientId) throws Exception;
}
