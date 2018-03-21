package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glenwood.glaceemr.server.application.Bean.MeasureStatus;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Response;

public class PqrsResponseBean {
	
	private String accountId = new String();
	private long patientId;
	private Map<String, String> measureInfo = new HashMap<String, String>();
	private Map<String,MeasureStatus> measureStatus = new HashMap<String, MeasureStatus>();
	List<PqrsMeasureBean> pqrsresponse = new ArrayList<PqrsMeasureBean>();
	List<QualityMeasureBean> QualityMeasures = new ArrayList<QualityMeasureBean>();
	
	
	public void setDataFromResponse(Response obj){
		
		setAccountId(obj.getAccountId());
		setPatientId(obj.getPatientId());
		setMeasureStatus(obj.getMeasureStatus());
		
	}
			
	public List<QualityMeasureBean> getQualityMeasures() {
		return QualityMeasures;
	}

	public void setQualityMeasures(List<QualityMeasureBean> qualityMeasures) {
		QualityMeasures = qualityMeasures;
	}

	public List<PqrsMeasureBean> getPqrsresponse() {
		return pqrsresponse;
	}

	public void setPqrsresponse(List<PqrsMeasureBean> pqrsresponse) {
		this.pqrsresponse = pqrsresponse;
	}

	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public long getPatientId() {
		return patientId;
	}
	
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	
	public Map<String, String> getMeasureInfo() {
		return measureInfo;
	}
	
	public void setMeasureInfo(Map<String, String> measureInfo) {
		this.measureInfo = measureInfo;
	}
	
	public Map<String, MeasureStatus> getMeasureStatus() {
		return measureStatus;
	}

	public void setMeasureStatus(Map<String, MeasureStatus> measureStatus) {
		this.measureStatus = measureStatus;
	}
	
}
