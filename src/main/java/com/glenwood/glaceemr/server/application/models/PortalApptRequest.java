package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "portal_appt_request")
public class PortalApptRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="portal_appt_request_portal_appt_request_id_seq")
	@SequenceGenerator(name ="portal_appt_request_portal_appt_request_id_seq", sequenceName="portal_appt_request_portal_appt_request_id_seq", allocationSize=1)
	@Column(name="portal_appt_request_id")
	private Integer portalApptRequestId;

	@Column(name="portal_appt_request_patient_id")
	private Integer portalApptRequestPatientId;

	@Column(name="portal_appt_request_reource_id")
	private Integer portalApptRequestReourceId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="portal_appt_request_request_made_time")
	private Timestamp portalApptRequestRequestMadeTime;

	@Column(name="portal_appt_request_reason")
	private String portalApptRequestReason;

	@Column(name="portal_appt_request_appt_ids")
	private String portalApptRequestApptIds;

	@Column(name="portal_appt_request_current_status")
	private Integer portalApptRequestCurrentStatus;

	@Column(name="portal_appt_request_allotment_summary")
	private String portalApptRequestAllotmentSummary;
	
	@OneToMany(mappedBy="portalApptRequestEntity", fetch=FetchType.LAZY)
	@JsonManagedReference
	List<PortalApptRequestDetail> portalApptRequestDetailsList;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name="portal_appt_request_reource_id", referencedColumnName="sch_resource_id", updatable=false, insertable=false)
	SchedulerResource portalApptRequestResourceEntity;

	public Integer getPortalApptRequestId() {
		return portalApptRequestId;
	}

	public void setPortalApptRequestId(Integer portalApptRequestId) {
		this.portalApptRequestId = portalApptRequestId;
	}

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

	public Timestamp getPortalApptRequestRequestMadeTime() {
		return portalApptRequestRequestMadeTime;
	}

	public void setPortalApptRequestRequestMadeTime(
			Timestamp portalApptRequestRequestMadeTime) {
		this.portalApptRequestRequestMadeTime = portalApptRequestRequestMadeTime;
	}

	public String getPortalApptRequestReason() {
		return portalApptRequestReason;
	}

	public void setPortalApptRequestReason(String portalApptRequestReason) {
		this.portalApptRequestReason = portalApptRequestReason;
	}

	public String getPortalApptRequestApptIds() {
		return portalApptRequestApptIds;
	}

	public void setPortalApptRequestApptIds(String portalApptRequestApptIds) {
		this.portalApptRequestApptIds = portalApptRequestApptIds;
	}

	public Integer getPortalApptRequestCurrentStatus() {
		return portalApptRequestCurrentStatus;
	}

	public void setPortalApptRequestCurrentStatus(
			Integer portalApptRequestCurrentStatus) {
		this.portalApptRequestCurrentStatus = portalApptRequestCurrentStatus;
	}

	public String getPortalApptRequestAllotmentSummary() {
		return portalApptRequestAllotmentSummary;
	}

	public void setPortalApptRequestAllotmentSummary(
			String portalApptRequestAllotmentSummary) {
		this.portalApptRequestAllotmentSummary = portalApptRequestAllotmentSummary;
	}

	public List<PortalApptRequestDetail> getPortalApptRequestDetailsList() {
		return portalApptRequestDetailsList;
	}

	public void setPortalApptRequestDetailsList(
			List<PortalApptRequestDetail> portalApptRequestDetailsList) {
		this.portalApptRequestDetailsList = portalApptRequestDetailsList;
	}

	public SchedulerResource getPortalApptRequestResourceEntity() {
		return portalApptRequestResourceEntity;
	}

	public void setPortalApptRequestResourceEntity(
			SchedulerResource portalApptRequestResourceEntity) {
		this.portalApptRequestResourceEntity = portalApptRequestResourceEntity;
	}
	
}