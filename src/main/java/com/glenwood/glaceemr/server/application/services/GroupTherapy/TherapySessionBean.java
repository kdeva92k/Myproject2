package com.glenwood.glaceemr.server.application.services.GroupTherapy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TherapySessionBean {
	Integer sessionId;
	String sessionStartTime;
	Integer sessionProviderId;
	String sessionProviderName;
	Integer sessionLeaderId;
	String sessionLeaderName;
	Integer sessionSupervisorId;
	String sessionSupervisorName;
	String sessionTopic;	
	Integer sessionPosId;
	String sessionPOSName;
	String sessionDateValue;
	Integer sessionStatus;
	String sessionEndTime;
	Integer groupId;
	String groupName;

	
	
	public TherapySessionBean(Integer sessionId,String sessionStartTime,Integer sessionProviderId,String sessionProviderName,Integer sessionLeaderId,Integer sessionSupervisorId,String sessionTopic,Integer sessionPosId,String sessionPOSName,Date sessionDateValue,Integer sessionStatus,String sessionEndTime){
		super();
		DateFormat timeFormat=new SimpleDateFormat("MM/dd/yyyy");
		if(sessionId!=null){
			this.sessionId = sessionId;
		}
		else{
			this.sessionId = -1;
		}
		if(sessionStartTime!=null){
			this.sessionStartTime=sessionStartTime;
		}
		else{
			this.sessionStartTime = "";
		}
		if(sessionProviderId!=null){
			this.sessionProviderId= sessionProviderId;
		}
		else{
			this.sessionProviderId = -1;
		}
		if(sessionProviderName!=null){
			this.sessionProviderName=sessionProviderName;
		}
		else{
			this.sessionProviderName = "";
		}
		if(sessionLeaderId!=null){
			this.sessionLeaderId= sessionLeaderId;
		}
		else{
			this.sessionLeaderId =-1;
		}
		if(sessionSupervisorId!=null){
			this.sessionSupervisorId= sessionSupervisorId;
		}
		else{
			this.sessionSupervisorId = -1;
		}
		if(sessionTopic!=null){
			this.sessionTopic= sessionTopic;
		}
		else{
			this.sessionTopic = "";
		}
		if(sessionPosId!=null){
			this.sessionPosId = sessionPosId;
		}
		else{
			this.sessionPosId = -1;
		}
		if(sessionPOSName!=null){
			this.sessionPOSName = sessionPOSName;
		}
		else{
			this.sessionPOSName = "";
		}
		if(sessionDateValue!=null){
			this.sessionDateValue = timeFormat.format(sessionDateValue);
		}
		else{
			this.sessionDateValue = "";
		}
		if(sessionStatus!=null){
			this.sessionStatus = sessionStatus;
		}
		else{
			this.sessionStatus = -1;
		}
		if(sessionEndTime!=null){
			this.sessionEndTime = sessionEndTime;
		}
		else{
			this.sessionEndTime = "";
		}
	}
	
	public TherapySessionBean(Integer groupId,Integer sessionId,Date sessionDateValue,String sessionStartTime,String groupName,String sessionTopic,Integer sessionSupervisorId,String sessionSupervisorName,Integer sessionLeaderId,String sessionLeaderName){
		super();
		DateFormat timeFormat=new SimpleDateFormat("MM/dd/yyyy");
		if(groupId!=null) {
			this.groupId=groupId;
		}
		else {
			this.groupId= -1;
		}
		if(sessionId!=null) {
			this.sessionId=sessionId;
		}
		else {
			this.sessionId= -1;
		}
		if(sessionDateValue!=null){
			this.sessionDateValue = timeFormat.format(sessionDateValue);
		}
		else{
			this.sessionDateValue = "";
		}
		if(sessionStartTime!=null){
			this.sessionStartTime = sessionStartTime;
		}
		else{
			this.sessionStartTime = "";
		}
		if(groupName!=null){
			this.groupName = groupName;
		}
		else{
			this.groupName = "";
		}
		if(sessionTopic!=null){
			this.sessionTopic = sessionTopic;
		}
		else{
			this.sessionTopic = "";
		}
		if(sessionSupervisorId!=null){
			this.sessionSupervisorId = sessionSupervisorId;
		}
		else{
			this.sessionSupervisorId = -1;
		}
		if(sessionSupervisorName!=null){
			this.sessionSupervisorName = sessionSupervisorName;
		}
		else{
			this.sessionSupervisorName = "";
		}
		if(sessionLeaderId!=null){
			this.sessionLeaderId = sessionLeaderId;
		}
		else{
			this.sessionLeaderId = -1;
		}
		if(sessionLeaderName!=null){
			this.sessionLeaderName = sessionLeaderName;
		}
		else{
			this.sessionLeaderName = "";
		}
	}
	
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	
	public Integer getSessionProviderId() {
		return sessionProviderId;
	}
	public String getSessionStartTime() {
		return sessionStartTime;
	}
	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	public void setSessionProviderId(Integer sessionProviderId) {
		this.sessionProviderId = sessionProviderId;
	}
	public Integer getSessionPosId() {
		return sessionPosId;
	}
	public void setSessionPosId(Integer sessionPosId) {
		this.sessionPosId = sessionPosId;
	}
	public Integer getSessionStatus() {
		return sessionStatus;
	}
	public void setSessionStatus(Integer sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	public String getSessionDateValue() {
		return sessionDateValue;
	}
	public void setSessionDateValue(String sessionDateValue) {
		this.sessionDateValue = sessionDateValue;
	}
	public String getSessionEndTime() {
		return sessionEndTime;
	}
	public void setSessionEndTime(String sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}
	public String getSessionTopic() {
		return sessionTopic;
	}
	public void setSessionTopic(String sessionTopic) {
		this.sessionTopic = sessionTopic;
	}
	public Integer getSessionLeaderId() {
		return sessionLeaderId;
	}
	public void setSessionLeaderId(Integer sessionLeaderId) {
		this.sessionLeaderId = sessionLeaderId;
	}
	public Integer getSessionSupervisorId() {
		return sessionSupervisorId;
	}
	public void setSessionSupervisorId(Integer sessionSupervisorId) {
		this.sessionSupervisorId = sessionSupervisorId;
	}

	public String getSessionProviderName() {
		return sessionProviderName;
	}

	public void setSessionProviderName(String sessionProviderName) {
		this.sessionProviderName = sessionProviderName;
	}

	public String getSessionPOSName() {
		return sessionPOSName;
	}

	public String getSessionLeaderName() {
		return sessionLeaderName;
	}

	public void setSessionLeaderName(String sessionLeaderName) {
		this.sessionLeaderName = sessionLeaderName;
	}

	public String getSessionSupervisorName() {
		return sessionSupervisorName;
	}

	public void setSessionSupervisorName(String sessionSupervisorName) {
		this.sessionSupervisorName = sessionSupervisorName;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setSessionPOSName(String sessionPOSName) {
		this.sessionPOSName = sessionPOSName;
	}
	
	
	
	
}
