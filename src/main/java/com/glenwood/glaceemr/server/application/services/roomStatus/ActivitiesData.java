package com.glenwood.glaceemr.server.application.services.roomStatus;

import java.util.List;

public class ActivitiesData {
 private List<ActivityBean> activityJson;
 private ActivityBean entityData;
 private ActivityBean userData;
 private String patientId;
public List<ActivityBean> getactivityJson() {
	return activityJson;
}
public void setactivityJson(List<ActivityBean> activityJson) {
	this.activityJson = activityJson;
}
public ActivityBean getEntityData() {
	return entityData;
}
public void setEntityData(ActivityBean entityData) {
	this.entityData = entityData;
}
public ActivityBean getUserData() {
	return userData;
}
public void setUserData(ActivityBean userData) {
	this.userData = userData;
}
public String getPatientId() {
	return patientId;
}
public void setPatientId(String patientId) {
	this.patientId = patientId;
}
 
}
