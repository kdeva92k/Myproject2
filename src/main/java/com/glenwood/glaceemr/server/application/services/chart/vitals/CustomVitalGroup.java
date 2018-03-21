package com.glenwood.glaceemr.server.application.services.chart.vitals;

import java.util.ArrayList;
import java.util.List;

/*@Component
@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS)*/
public class CustomVitalGroup {

	Integer age;
	short sex;
	Integer groupId;
	String vitalGroupName;
	String vitalType;
	List<ElementDetails> unitgroupdetails=new ArrayList<ElementDetails>();
	
	
	
	public short getSex() {
		return sex;
	}
	public void setSex(short sex) {
		this.sex = sex;
	}
	
	
	public void setUnitgroupdetails(List<ElementDetails> unitgroupdetails) {
		this.unitgroupdetails = unitgroupdetails;
	}
	public String getVitalGroupName() {
		return vitalGroupName;
	}
	public void setVitalGroupName(String vitalGroupName) {
		this.vitalGroupName = vitalGroupName;
	}
	public String getVitalType() {
		return vitalType;
	}
	public void setVitalType(String vitalType) {
		this.vitalType = vitalType;
	}
	public List<ElementDetails> getUnitgroupdetails() {
		return unitgroupdetails;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getAge() {
		return age;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	
	
	
}
