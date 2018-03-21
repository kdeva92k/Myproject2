package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalApptRequestBean {

	private Integer portalApptRequestPatientId;

	private Integer portalApptRequestReourceId;

	private String portalApptRequestRequestMadeTime;

	private String portalApptRequestReason;

	private Integer portalApptRequestCurrentStatus;
	
	List<PortalApptRequestDetailBean> portalApptRequestDetailsList;
	
	public Integer getPortalApptRequestPatientId() {
		return portalApptRequestPatientId;
	}

	public void setPortalApptRequestPatientId(Integer portalApptRequestPatientId) {
		this.portalApptRequestPatientId = portalApptRequestPatientId;
	}

	public Integer getPortalApptRequestReourceId() {
		return portalApptRequestReourceId;
	}

	public void setPortalApptRequestReourceId(Integer portalApptRequestReourceId) {
		this.portalApptRequestReourceId = portalApptRequestReourceId;
	}

	public String getPortalApptRequestRequestMadeTime() {
		return portalApptRequestRequestMadeTime;
	}

	public void setPortalApptRequestRequestMadeTime(
			String portalApptRequestRequestMadeTime) {
		this.portalApptRequestRequestMadeTime = portalApptRequestRequestMadeTime;
	}

	public String getPortalApptRequestReason() {
		return portalApptRequestReason;
	}

	public void setPortalApptRequestReason(String portalApptRequestReason) {
		this.portalApptRequestReason = portalApptRequestReason;
	}

	public Integer getPortalApptRequestCurrentStatus() {
		return portalApptRequestCurrentStatus;
	}

	public void setPortalApptRequestCurrentStatus(
			Integer portalApptRequestCurrentStatus) {
		this.portalApptRequestCurrentStatus = portalApptRequestCurrentStatus;
	}

	public List<PortalApptRequestDetailBean> getPortalApptRequestDetailsList() {
		return portalApptRequestDetailsList;
	}

	public void setPortalApptRequestDetailsList(
			List<PortalApptRequestDetailBean> portalApptRequestDetailsList) {
		this.portalApptRequestDetailsList = portalApptRequestDetailsList;
	}

}
