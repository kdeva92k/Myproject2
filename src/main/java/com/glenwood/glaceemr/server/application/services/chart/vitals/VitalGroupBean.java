package com.glenwood.glaceemr.server.application.services.chart.vitals;

import java.util.LinkedHashMap;
/**
 * 
 * @author CNM
 */

public class VitalGroupBean {
	
	private int groupId;
	private String groupName;
	private LinkedHashMap<String, VitalElementBean> vitalElementBean;
	/**
	 * used to get the group id
	 * @return the value of groupId
	 */
	public int getGroupId(){
		return groupId;
	}
	/**
	 * used to set the group id
	 * @param groupId it is id of a group
	 */
	public void setGroupId(int groupId){
		this.groupId = groupId;
	}
	/**
	 * used to get the group name
	 * @return the name of group
	 */
	public String getGroupName(){
		return groupName;
	}
	/**
	 * used to set the group name
	 * @param groupName is for name of a group
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	/**
	 * used for getting vital elements
	 * @returns the vitalElementBean object
	 */
	public LinkedHashMap<String, VitalElementBean> getVitalElements(){
		return vitalElementBean;
	}
	/**
	 * calling the vital element bean file
	 * @param vitalElementBean
	 */
	public void setVitalElements(LinkedHashMap<String, VitalElementBean> vitalElementBean){
		this.vitalElementBean = vitalElementBean;
	}
}
