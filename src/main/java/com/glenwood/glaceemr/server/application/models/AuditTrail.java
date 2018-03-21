package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

/**
 * @author Aparna
 *	Last Modified by Jagadeeswar
 */

@Entity
@Table(name = "audittrail_log")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AuditTrail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audittrail_log_audittrail_log_id_seq")
	@SequenceGenerator(name = "audittrail_log_audittrail_log_id_seq", sequenceName = "audittrail_log_audittrail_log_id_seq", allocationSize = 1)
	@Column(name = "audittrail_log_id")
	private Integer logId;

	@Column(name = "audittrail_log_parent_id")
	private Integer parentID;

	@Column(name = "audittrail_log_type")
	private Integer logType;

	@NotFound(action = NotFoundAction.IGNORE)
	@Column(name = "audittrail_log_user_id")
	private Integer userId;

	@NotFound(action = NotFoundAction.IGNORE)
	@Column(name = "audittrail_log_patient_id")
	private Integer patientId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name = "audittrail_log_on", columnDefinition = "timestamp with time zone")
	private Timestamp logOn;

	@Column(name = "audittrail_log_module")
	private Integer module;

	@Column(name = "audittrail_log_action")
	private Integer action;

	@Column(name = "audittrail_log_outcome")
	private String outcome;

	@Column(name = "audittrail_log_client_ip")
	private String clientIp;

	@Column(name = "audittrail_log_server_ip")
	private String serverIp;

	@Column(name = "audittrail_log_server_hostname")
	private String serverHostname;

	@Column(name = "audittrail_log_session_id")
	private String sessionId;

	@Column(name = "audittrail_log_relevant_ids")
	private String relevantIds;

	@Column(name = "audittrail_log_description")
	public String desc;

	@Column(name = "audittrail_log_logintype")
	private Integer loginType;

	@Column(name = "audittrail_log_request_url")
	private String requestedUrl;

	@Column(name = "audittrail_log_referrer_url")
	private String referenceUrl;

	@Column(name = "audittrail_log_phi_description")
	private String phiDescription;

	@Column(name = "audittrail_log_data_backup")
	private String backUpData;

	@Column(name = "audittrail_log_raw_data")
	private String rawData;

	@Column(name = "audittrail_log_checksum")
	private String checkSum;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "audittrail_log_user_id", referencedColumnName = "login_users_id", insertable = false, updatable = false)
	/*
	 * @ManyToOne
	 * 
	 * @NotFound(action = NotFoundAction.IGNORE)
	 * 
	 * @JoinColumn(name = "audittrail_log_patient_id", referencedColumnName =
	 * "login_users_id", insertable = false, updatable = false) private Users
	 * auditLogUser;
	 */
	@Transient
	private Boolean verifyCheckSum;
	@Transient
	private Integer accountNo;
	@Transient
	private String patientName;
	@Transient
	private Date dob;
	@Transient
	private Integer logParentModule;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Timestamp getLogOn() {
		return logOn;
	}

	public void setLogOn(Timestamp logOn) {
		this.logOn = logOn;
	}

	public Integer getModule() {
		return module;
	}

	public void setModule(Integer module) {
		this.module = module;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerHostname() {
		return serverHostname;
	}

	public void setServerHostname(String serverHostname) {
		this.serverHostname = serverHostname;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRelevantIds() {
		return relevantIds;
	}

	public void setRelevantIds(String relevantIds) {
		this.relevantIds = relevantIds;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getRequestedUrl() {
		return requestedUrl;
	}

	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;
	}

	public String getReferenceUrl() {
		return referenceUrl;
	}

	public void setReferenceUrl(String referenceUrl) {
		this.referenceUrl = referenceUrl;
	}

	public String getPhiDescription() {
		return phiDescription;
	}

	public void setPhiDescription(String phiDescription) {
		this.phiDescription = phiDescription;
	}

	public String getBackUpData() {
		return backUpData;
	}

	public void setBackUpData(String backUpData) {
		this.backUpData = backUpData;
	}

	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	

	@JsonProperty
	public Boolean getVerifyCheckSum() {
		return verifyCheckSum;
	}

	@JsonProperty
	public void setVerifyCheckSum(Boolean verifyCheckSum) {
		this.verifyCheckSum = verifyCheckSum;
	}
	
	@JsonProperty
	public Integer getAccountNo() {
		return accountNo;
	}

	@JsonProperty
	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	@JsonProperty
	public String getPatientName() {
		return patientName;
	}

	@JsonProperty
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@JsonProperty
	public Date getDob() {
		return dob;
	}

	@JsonProperty
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@JsonProperty
	public Integer getLogParentModule() {
		return logParentModule;
	}

	@JsonProperty
	public void setLogParentModule(Integer logParentModule) {
		this.logParentModule = logParentModule;
	}

	@Override
	public String toString() {
		return "AuditTrail [logId=" + logId + ", parentID=" + parentID + ", logType=" + logType + ", userId=" + userId
				+ ", patientId=" + patientId + ", logOn=" + logOn + ", module=" + module + ", action=" + action
				+ ", outcome=" + outcome + ", clientIp=" + clientIp + ", serverIp=" + serverIp + ", serverHostname="
				+ serverHostname + ", sessionId=" + sessionId + ", relevantIds=" + relevantIds + ", desc=" + desc
				+ ", loginType=" + loginType + ", requestedUrl=" + requestedUrl + ", referenceUrl=" + referenceUrl
				+ ", phiDescription=" + phiDescription + ", backUpData=" + backUpData + ", rawData=" + rawData
				+ ", checkSum=" + checkSum + ", verifyCheckSum=" + verifyCheckSum + ", accountNo=" + accountNo
				+ ", patientName=" + patientName + ", dob=" + dob + ", logParentModule=" + logParentModule + "]";
	}
	
	

}
