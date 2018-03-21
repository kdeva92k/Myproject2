package com.glenwood.glaceemr.server.application.services.alertinbox;

import java.util.Date;

public class AlertInboxBean {
	
	Integer alertid;
	String roomname;
	Integer alertgroup;
	Integer parentid;
	Integer modifiedbyid;
	Integer readbyid;
	String redirecturl;
	Integer chartid;
	Integer fromid;
	String fromName;
	Boolean read;
	String modifiedbyname;
	Integer encounterid;
	Integer toid;
	String toName;
	String alert_category_url_caption;
	String forwardedby;
	Integer alerttype;
	Integer subpage;
	String modifiedon;
	String createddate;
	String msg;
	Integer categoryid;
	String readbyname;
	String patientname;
	Integer patientid;
	String receivedto;
	Boolean highprivilage;
	String patientphnum;
	Integer refid;
	Integer alertsection;
	String patientaccnum;
	Integer qrflag;
	String displayname;
	Integer status;
	Boolean expanded;
	Integer actionmap;
	Integer categoryorder;
	String categoryname;
	Boolean needdatewisegrouping;
	String qrurl;
	String servicedoctor;
	String readon;
	Date currentmilliseconds;
	String createdtime;
	String createddatetime;
	
	public AlertInboxBean(){
		
	}
	
	public AlertInboxBean(Integer alertid, Integer categoryid, Integer chartid, String patientaccnum, String patientname, String roomname, String categoryname,
			String redirecturl, String displayname, Integer alertgroup, Integer alerttype, Integer fromid, Integer toid, Integer refid, Integer patientid, Integer parentid,
			Integer encounterid, String msg, Integer modifiedbyid, String createddate, String forwardedby, String receivedto,String readbyname, String modifiedbyname,
			boolean read, String alert_category_url_caption, Integer subpage, String modifiedon, boolean highprivilage, Integer alertsection, String qrurl, 
			Integer status, boolean expanded, Integer actionmap, Integer categoryorder, boolean needdatewisegrouping, Integer qrflag, String servicedoctor, String readon, 
			Date currentmilliseconds, String createdtime, String patientphnum, Integer readbyid) {
		this.alertid = alertid;
		this.roomname = roomname;
		this.alertgroup = alertgroup;
		this.parentid = parentid;
		this.modifiedbyid = modifiedbyid;
		this.readbyid = readbyid;
		this.redirecturl = redirecturl;
		this.chartid = chartid;
		this.fromid = fromid;
		this.read = read;
		this.modifiedbyname = modifiedbyname;
		this.encounterid = encounterid;
		this.toid = toid;
		this.alert_category_url_caption = alert_category_url_caption;
		this.forwardedby = forwardedby;
		this.alerttype = alerttype;
		this.subpage = subpage;
		this.modifiedon = modifiedon;
		this.createddate = createddate;
		this.msg = msg;
		this.categoryid = categoryid;
		this.readbyname = readbyname;
		this.patientname = patientname;
		this.patientid = patientid;
		this.receivedto = receivedto;
		this.highprivilage = highprivilage;
		this.patientphnum = patientphnum;
		this.refid = refid;
		this.alertsection = alertsection;
		this.patientaccnum = patientaccnum;
		this.qrflag = qrflag;
		this.displayname = displayname;
		this.status = status;
		this.expanded = expanded;
		this.actionmap = actionmap;
		this.categoryorder = categoryorder;
		this.categoryname = categoryname;
		this.needdatewisegrouping = needdatewisegrouping;
		this.qrurl = qrurl;
		this.servicedoctor = servicedoctor;
		this.readon = readon;
		this.currentmilliseconds = currentmilliseconds;
		this.createdtime = createdtime;
	}
	
	public Integer getAlertid() {
		return alertid;
	}
	public void setAlertid(Integer alertid) {
		this.alertid = alertid;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public Integer getAlertgroup() {
		return alertgroup;
	}
	public void setAlertgroup(Integer alertgroup) {
		this.alertgroup = alertgroup;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public Integer getModifiedbyid() {
		return modifiedbyid;
	}
	public void setModifiedbyid(Integer modifiedbyid) {
		this.modifiedbyid = modifiedbyid;
	}
	public Integer getReadbyid() {
		return readbyid;
	}
	public void setReadbyid(Integer readbyid) {
		this.readbyid = readbyid;
	}
	public String getRedirecturl() {
		return redirecturl;
	}
	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}
	public Integer getChartid() {
		return chartid;
	}
	public void setChartid(Integer chartid) {
		this.chartid = chartid;
	}
	public Integer getFromid() {
		return fromid;
	}
	public void setFromid(Integer fromid) {
		this.fromid = fromid;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	public String getModifiedbyname() {
		return modifiedbyname;
	}
	public void setModifiedbyname(String modifiedbyname) {
		this.modifiedbyname = modifiedbyname;
	}
	public Integer getEncounterid() {
		return encounterid;
	}
	public void setEncounterid(Integer encounterid) {
		this.encounterid = encounterid;
	}
	public Integer getToid() {
		return toid;
	}
	public void setToid(Integer toid) {
		this.toid = toid;
	}
	public String getAlert_category_url_caption() {
		return alert_category_url_caption;
	}
	public void setAlert_category_url_caption(String alert_category_url_caption) {
		this.alert_category_url_caption = alert_category_url_caption;
	}
	public String getForwardedby() {
		return forwardedby;
	}
	public void setForwardedby(String forwardedby) {
		this.forwardedby = forwardedby;
	}
	public Integer getAlerttype() {
		return alerttype;
	}
	public void setAlerttype(Integer alerttype) {
		this.alerttype = alerttype;
	}
	public Integer getSubpage() {
		return subpage;
	}
	public void setSubpage(Integer subpage) {
		this.subpage = subpage;
	}
	public String getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getReadbyname() {
		return readbyname;
	}
	public void setReadbyname(String readbyname) {
		this.readbyname = readbyname;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public Integer getPatientid() {
		return patientid;
	}
	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}
	public String getReceivedto() {
		return receivedto;
	}
	public void setReceivedto(String receivedto) {
		this.receivedto = receivedto;
	}
	public Boolean getHighprivilage() {
		return highprivilage;
	}
	public void setHighprivilage(Boolean highprivilage) {
		this.highprivilage = highprivilage;
	}
	public String getPatientphnum() {
		return patientphnum;
	}
	public void setPatientphnum(String patientphnum) {
		this.patientphnum = patientphnum;
	}
	public Integer getRefid() {
		return refid;
	}
	public void setRefid(Integer refid) {
		this.refid = refid;
	}
	public Integer getAlertsection() {
		return alertsection;
	}
	public void setAlertsection(Integer alertsection) {
		this.alertsection = alertsection;
	}
	public String getPatientaccnum() {
		return patientaccnum;
	}
	public void setPatientaccnum(String patientaccnum) {
		this.patientaccnum = patientaccnum;
	}
	public Integer getQrflag() {
		return qrflag;
	}
	public void setQrflag(Integer qrflag) {
		this.qrflag = qrflag;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public Integer getActionmap() {
		return actionmap;
	}
	public void setActionmap(Integer actionmap) {
		this.actionmap = actionmap;
	}
	public Integer getCategoryorder() {
		return categoryorder;
	}
	public void setCategoryorder(Integer categoryorder) {
		this.categoryorder = categoryorder;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public Boolean getNeeddatewisegrouping() {
		return needdatewisegrouping;
	}
	public void setNeeddatewisegrouping(Boolean needdatewisegrouping) {
		this.needdatewisegrouping = needdatewisegrouping;
	}
	public String getQrurl() {
		return qrurl;
	}
	public void setQrurl(String qrurl) {
		this.qrurl = qrurl;
	}
	public String getServicedoctor() {
		return servicedoctor;
	}
	public void setServicedoctor(String servicedoctor) {
		this.servicedoctor = servicedoctor;
	}
	public String getReadon() {
		return readon;
	}
	public void setReadon(String readon) {
		this.readon = readon;
	}
	public Date getCurrentmilliseconds() {
		return currentmilliseconds;
	}
	public void setCurrentmilliseconds(Date currentmilliseconds) {
		this.currentmilliseconds = currentmilliseconds;
	}
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getCreateddatetime() {
		return createddatetime;
	}
	public void setCreateddatetime(String createddatetime) {
		this.createddatetime = createddatetime;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}
	
}