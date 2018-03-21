package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import com.google.common.base.Optional;

public class HmrBean {
	Integer Catid;
	String CatName;
	Integer Alertid;
	String AlertName;
	Integer Gender;
	String Cpt;
	String Dx;
	String startdate;
	String enddate;
	Long element_id;
	Integer element_type;
	Integer FromAge;
	Integer ToAge;
	Integer DurType;
	Integer Schedule;
	Integer SchType;
	String dxdesc;
	Integer enablealert;
	Integer isdxbased;
	String comments;
	Integer maxyrs;
	String modifiedby;
	String modifiedon;
	String groupname;
	Integer sortby;
	String initiated_by;
	String initiated_on;
	Integer isactive;
	String desc;
	Integer ruledate;
	Integer AlertDetid;
	Integer groupid;
	String references;
	public HmrBean(Integer Catid,String CatName,Integer Alertid,String AlertName,Integer Gender,String Cpt,String Dx,
			Integer startdate,Integer enddate,Long element_id,Integer element_type,Integer FromAge,Integer ToAge,Integer DurType,
			Integer Schedule,Integer SchType,String dxdesc,Integer enablealert,Integer isdxbased,String comments,Integer maxyrs,String modifiedby,String modifiedon,String groupname,Integer sortby,String initiated_by,
			String initiated_on,Integer isactive,String desc,Integer ruledate){
		this.Catid=Catid;
		this.CatName=CatName;
		this.Alertid=Alertid;
		this.AlertName=AlertName;
		this.Gender=Gender;
		this.Cpt=Cpt;
		this.Dx=Dx;
		this.startdate=Integer.toString(Optional.fromNullable(startdate).or(-1));
		this.enddate=Integer.toString(Optional.fromNullable(enddate).or(-1));
		this.element_id=element_id;
		this.element_type=element_type;
		this.FromAge=FromAge;
		this.ToAge=ToAge;
		this.DurType=DurType;
		this.Schedule=Schedule;
		this.SchType=SchType;
		this.dxdesc=dxdesc;
		this.enablealert=enablealert;
		this.isdxbased=isdxbased;
		this.comments=comments;
		this.maxyrs=maxyrs;
		this.modifiedby=modifiedby;
		this.modifiedon=modifiedon;
		this.groupname=groupname;
		this.sortby=sortby;
		this.initiated_by=initiated_by;
		this.initiated_on=initiated_on;
		this.isactive=isactive;
		this.desc=desc;
		this.ruledate=ruledate;
	}
	public HmrBean(Integer Catid,String CatName,Integer Alertid,String AlertName,Integer Gender,String Cpt,String Dx,
			Integer AlertDetid,Integer FromAge,Integer ToAge,Integer DurType,Integer Schedule,Integer SchType,String dxdesc,
			Integer enablealert,Integer isdxbased,String comments,Integer maxyrs,String modifiedby,String modifiedon,
			String groupname,Integer groupid,Integer sortby,String initiated_by,String initiated_on,Integer isactive,String desc){
		this.Catid=Catid;
		this.CatName=CatName;
		this.Alertid=Alertid;
		this.AlertName=AlertName;
		this.Gender=Gender;
		this.Cpt=Cpt;
		this.Dx=Dx;
		this.AlertDetid=AlertDetid;
		this.FromAge=FromAge;
		this.ToAge=ToAge;
		this.DurType=DurType;
		this.Schedule=Schedule;
		this.SchType=SchType;
		this.dxdesc=dxdesc;
		this.enablealert=enablealert;
		this.isdxbased=isdxbased;
		this.comments=comments;
		this.maxyrs=maxyrs;
		this.modifiedby=modifiedby;
		this.modifiedon=modifiedon;
		this.groupname=groupname;
		this.groupid=groupid;
		this.sortby=sortby;
		this.initiated_by=initiated_by;
		this.initiated_on=initiated_on;
		this.isactive=isactive;
		this.desc=desc;
	}
	public HmrBean(String references){
		this.references=references;
	}
	public HmrBean(Integer FromAge,Integer ToAge,Integer DurType){
		this.FromAge=FromAge;
		this.ToAge=ToAge;
		this.DurType=DurType;
	}
	public String getCatName() {
		return CatName;
	}
	public void setCatName(String catName) {
		CatName = catName;
	}
	public Integer getAlertid() {
		return Alertid;
	}
	public void setAlertid(Integer alertid) {
		Alertid = alertid;
	}
	public String getAlertName() {
		return AlertName;
	}
	public void setAlertName(String alertName) {
		AlertName = alertName;
	}
	public Integer getGender() {
		return Gender;
	}
	public void setGender(Integer gender) {
		Gender = gender;
	}
	public String getCpt() {
		return Cpt;
	}
	public void setCpt(String cpt) {
		Cpt = cpt;
	}
	public String getDx() {
		return Dx;
	}
	public void setDx(String dx) {
		Dx = dx;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Long getElement_id() {
		return element_id;
	}
	public void setElement_id(Long element_id) {
		this.element_id = element_id;
	}
	public Integer getElement_type() {
		return element_type;
	}
	public void setElement_type(Integer element_type) {
		this.element_type = element_type;
	}
	public Integer getFromAge() {
		return FromAge;
	}
	public void setFromAge(Integer fromAge) {
		FromAge = fromAge;
	}
	public Integer getToAge() {
		return ToAge;
	}
	public void setToAge(Integer toAge) {
		ToAge = toAge;
	}
	public Integer getDurType() {
		return DurType;
	}
	public void setDurType(Integer durType) {
		DurType = durType;
	}
	public Integer getSchedule() {
		return Schedule;
	}
	public void setSchedule(Integer schedule) {
		Schedule = schedule;
	}
	public Integer getSchType() {
		return SchType;
	}
	public void setSchType(Integer schType) {
		SchType = schType;
	}
	public String getDxdesc() {
		return dxdesc;
	}
	public void setDxdesc(String dxdesc) {
		this.dxdesc = dxdesc;
	}
	public Integer getEnablealert() {
		return enablealert;
	}
	public void setEnablealert(Integer enablealert) {
		this.enablealert = enablealert;
	}
	public Integer getIsdxbased() {
		return isdxbased;
	}
	public void setIsdxbased(Integer isdxbased) {
		this.isdxbased = isdxbased;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getMaxyrs() {
		return maxyrs;
	}
	public void setMaxyrs(Integer maxyrs) {
		this.maxyrs = maxyrs;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public String getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public Integer getSortby() {
		return sortby;
	}
	public void setSortby(Integer sortby) {
		this.sortby = sortby;
	}
	public String getInitiated_by() {
		return initiated_by;
	}
	public void setInitiated_by(String initiated_by) {
		this.initiated_by = initiated_by;
	}
	public String getInitiated_on() {
		return initiated_on;
	}
	public void setInitiated_on(String initiated_on) {
		this.initiated_on = initiated_on;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getRuledate() {
		return ruledate;
	}
	public void setRuledate(Integer ruledate) {
		this.ruledate = ruledate;
	}
	public Integer getCatid() {
		return Catid;
	}
	public void setCatid(Integer catid) {
		Catid = catid;
	}
	public Integer getAlertDetid() {
		return AlertDetid;
	}
	public void setAlertDetid(Integer alertDetid) {
		AlertDetid = alertDetid;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
}
