package com.glenwood.glaceemr.server.application.Bean;

public class DocumentsCategoryBean {

	private Integer patientDocCategoryId;
	private String patientDocCategoryName;
	private long totalCount;

	public DocumentsCategoryBean(Integer patientDocCategoryId,String patientDocCategoryName,long totalCount){

		super();
		if(patientDocCategoryId!=null){
			this.patientDocCategoryId=patientDocCategoryId;
		}

		if(patientDocCategoryName!=null){
			this.patientDocCategoryName=patientDocCategoryName;
		}
		if(totalCount!= -1){
			this.totalCount=totalCount;
		}
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Integer getPatientDocCategoryId() {
		return patientDocCategoryId;
	}

	public void setPatientDocCategoryId(Integer patientDocCategoryId) {
		this.patientDocCategoryId = patientDocCategoryId;
	}

	public String getPatientDocCategoryName() {
		return patientDocCategoryName;
	}

	public void setPatientDocCategoryName(String patientDocCategoryName) {
		this.patientDocCategoryName = patientDocCategoryName;
	}
}