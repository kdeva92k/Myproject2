package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "portal_appt_request_detail")
public class PortalApptRequestDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="portal_appt_request_detail_portal_appt_request_detail_id_seq")
	@SequenceGenerator(name ="portal_appt_request_detail_portal_appt_request_detail_id_seq", sequenceName="portal_appt_request_detail_portal_appt_request_detail_id_seq", allocationSize=1)
	@Column(name="portal_appt_request_detail_id")
	private Integer portalApptRequestDetailId;

	@Column(name="portal_appt_request_detail_request_id")
	private Integer portalApptRequestDetailRequestId;

	@Column(name="portal_appt_request_detail_request_rank")
	private Integer portalApptRequestDetailRequestRank;

	@Column(name="portal_appt_request_detail_request_date")
	private Date portalApptRequestDetailRequestDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="portal_appt_request_detail_time_start")
	private Timestamp portalApptRequestDetailTimeStart;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="portal_appt_request_detail_time_end")
	private Timestamp portalApptRequestDetailTimeEnd;

	@Column(name="portal_appt_request_detail_current_status")
	private Integer portalApptRequestDetailCurrentStatus;
	
	@ManyToOne
	@JoinColumn(name="portal_appt_request_detail_request_id", referencedColumnName="portal_appt_request_id", updatable=false, insertable=false)
	@JsonBackReference
	PortalApptRequest portalApptRequestEntity;

	public Integer getPortalApptRequestDetailId() {
		return portalApptRequestDetailId;
	}

	public void setPortalApptRequestDetailId(Integer portalApptRequestDetailId) {
		this.portalApptRequestDetailId = portalApptRequestDetailId;
	}

	public Integer getPortalApptRequestDetailRequestId() {
		return portalApptRequestDetailRequestId;
	}

	public void setPortalApptRequestDetailRequestId(
			Integer portalApptRequestDetailRequestId) {
		this.portalApptRequestDetailRequestId = portalApptRequestDetailRequestId;
	}

	public Integer getPortalApptRequestDetailRequestRank() {
		return portalApptRequestDetailRequestRank;
	}

	public void setPortalApptRequestDetailRequestRank(
			Integer portalApptRequestDetailRequestRank) {
		this.portalApptRequestDetailRequestRank = portalApptRequestDetailRequestRank;
	}

	public Date getPortalApptRequestDetailRequestDate() {
		return portalApptRequestDetailRequestDate;
	}

	public void setPortalApptRequestDetailRequestDate(
			Date portalApptRequestDetailRequestDate) {
		this.portalApptRequestDetailRequestDate = portalApptRequestDetailRequestDate;
	}

	public Timestamp getPortalApptRequestDetailTimeStart() {
		return portalApptRequestDetailTimeStart;
	}

	public void setPortalApptRequestDetailTimeStart(
			Timestamp portalApptRequestDetailTimeStart) {
		this.portalApptRequestDetailTimeStart = portalApptRequestDetailTimeStart;
	}

	public Timestamp getPortalApptRequestDetailTimeEnd() {
		return portalApptRequestDetailTimeEnd;
	}

	public void setPortalApptRequestDetailTimeEnd(
			Timestamp portalApptRequestDetailTimeEnd) {
		this.portalApptRequestDetailTimeEnd = portalApptRequestDetailTimeEnd;
	}

	public Integer getPortalApptRequestDetailCurrentStatus() {
		return portalApptRequestDetailCurrentStatus;
	}

	public void setPortalApptRequestDetailCurrentStatus(
			Integer portalApptRequestDetailCurrentStatus) {
		this.portalApptRequestDetailCurrentStatus = portalApptRequestDetailCurrentStatus;
	}

	public PortalApptRequest getPortalApptRequestEntity() {
		return portalApptRequestEntity;
	}

	public void setPortalApptRequestEntity(PortalApptRequest portalApptRequestEntity) {
		this.portalApptRequestEntity = portalApptRequestEntity;
	}
	
}