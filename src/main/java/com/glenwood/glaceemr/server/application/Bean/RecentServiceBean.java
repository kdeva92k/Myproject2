package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class RecentServiceBean {
	
	public RecentServiceBean(Integer serviceDetailPatientid,
			Integer serviceDetailId, Date serviceDetailDos) {
		this.serviceDetailPatientid = serviceDetailPatientid;
		this.serviceDetailId = serviceDetailId;
		this.serviceDetailDos = serviceDetailDos;
	}
	private Integer serviceDetailPatientid;
	private Integer serviceDetailId;
	private Date serviceDetailDos;
  
	public Integer getServiceDetailPatientid() {
		return serviceDetailPatientid;
	}
	public void setServiceDetailPatientid(Integer serviceDetailPatientid) {
		this.serviceDetailPatientid = serviceDetailPatientid;
	}
	public Integer getServiceDetailId() {
		return serviceDetailId;
	}
	public void setServiceDetailId(Integer serviceDetailId) {
		this.serviceDetailId = serviceDetailId;
	}
	public Date getServiceDetailDos() {
		return serviceDetailDos;
	}
	public void setServiceDetailDos(Date serviceDetailDos) {
		this.serviceDetailDos = serviceDetailDos;
	}

}
