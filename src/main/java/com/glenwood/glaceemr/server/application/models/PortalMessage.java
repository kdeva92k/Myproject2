package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "portal_message")
public class PortalMessage implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="portal_message_id_seq")
	@SequenceGenerator(name ="portal_message_id_seq", sequenceName="portal_message_id_seq", allocationSize=1)
	@Column(name="id")
	private Integer id;

	@Column(name="patientid")
	private Integer patientid;

	@Column(name="message")
	private String message;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="mdate")
	private Timestamp mdate;

	@Column(name="status")
	private Integer status;

	@Column(name="portal_message_reply")
	private String portalMessageReply;

	@Column(name="portal_message_alertid")
	private Integer portalMessageAlertid;

	@Column(name="message_by")
	private Integer messageBy;

	@Column(name="isactive")
	private Boolean isactive;

	@Column(name="message_to")
	private Integer messageTo;

	@Column(name="parentid")
	private Integer parentid;

	@Column(name="portal_message_sender")
	private String portalMessageSender;

	@Column(name="portal_message_receiver")
	private String portalMessageReceiver;

	@Column(name="portal_message_mapid")
	private Integer portalMessageMapid;

	@Column(name="portal_message_hasreplied")
	private Boolean portalMessageHasreplied;

	@Column(name="portal_message_type")
	private Integer portalMessageType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="patientid", referencedColumnName="patient_registration_id", insertable=false, updatable=false)
	private PatientRegistration patientRegistrationTable;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="portalMessage")
	@JsonManagedReference
	private List<AlertEvent> alertEventList;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getMdate() {
		return mdate;
	}

	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPortalMessageReply() {
		return portalMessageReply;
	}

	public void setPortalMessageReply(String portalMessageReply) {
		this.portalMessageReply = portalMessageReply;
	}

	public Integer getPortalMessageAlertid() {
		return portalMessageAlertid;
	}

	public void setPortalMessageAlertid(Integer portalMessageAlertid) {
		this.portalMessageAlertid = portalMessageAlertid;
	}

	public Integer getMessageBy() {
		return messageBy;
	}

	public void setMessageBy(Integer messageBy) {
		this.messageBy = messageBy;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Integer getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(Integer messageTo) {
		this.messageTo = messageTo;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getPortalMessageSender() {
		return portalMessageSender;
	}

	public void setPortalMessageSender(String portalMessageSender) {
		this.portalMessageSender = portalMessageSender;
	}

	public String getPortalMessageReceiver() {
		return portalMessageReceiver;
	}

	public void setPortalMessageReceiver(String portalMessageReceiver) {
		this.portalMessageReceiver = portalMessageReceiver;
	}

	public Integer getPortalMessageMapid() {
		return portalMessageMapid;
	}

	public void setPortalMessageMapid(Integer portalMessageMapid) {
		this.portalMessageMapid = portalMessageMapid;
	}

	public Boolean getPortalMessageHasreplied() {
		return portalMessageHasreplied;
	}

	public void setPortalMessageHasreplied(Boolean portalMessageHasreplied) {
		this.portalMessageHasreplied = portalMessageHasreplied;
	}

	public Integer getPortalMessageType() {
		return portalMessageType;
	}

	public void setPortalMessageType(Integer portalMessageType) {
		this.portalMessageType = portalMessageType;
	}

	public PatientRegistration getPatientRegistrationTable() {
		return patientRegistrationTable;
	}

	public void setPatientRegistrationTable(
			PatientRegistration patientRegistrationTable) {
		this.patientRegistrationTable = patientRegistrationTable;
	}

	public List<AlertEvent> getAlertEventList() {
		return alertEventList;
	}

	public void setAlertEventList(List<AlertEvent> alertEventList) {
		this.alertEventList = alertEventList;
	}
	
}