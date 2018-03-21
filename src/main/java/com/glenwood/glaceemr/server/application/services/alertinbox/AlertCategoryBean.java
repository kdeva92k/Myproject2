package com.glenwood.glaceemr.server.application.services.alertinbox;

import java.util.List;


public class AlertCategoryBean {

	public AlertCategoryBean(int alerttype, int qrflag, int subpage,
			int status, int alertgroup, int categoryid, String displayname,
			Boolean expanded, int alertsection, int actionmap,
			int categoryorder, String categoryname,
			Boolean needdatewisegrouping, String qrurl) {
		super();
		this.alerttype = alerttype;
		this.qrflag = qrflag;
		this.subpage = subpage;
		this.status = status;
		this.alertgroup = alertgroup;
		this.categoryid = categoryid;
		this.displayname = displayname;
		this.expanded = expanded;
		this.alertsection = alertsection;
		this.actionmap = actionmap;
		this.categoryorder = categoryorder;
		this.categoryname = categoryname;
		this.needdatewisegrouping = needdatewisegrouping;
		this.qrurl = qrurl;
	}
	
	public AlertCategoryBean(){
		
	}
	
	List<AlertInboxBean> alertInboxBean;
	int alerttype;
	int qrflag;
	int subpage;
	int status;
	int alertgroup;
	int categoryid;
	String displayname;
	Boolean expanded;
	int alertsection;
	int actionmap;
	int categoryorder;
	String categoryname;
	Boolean needdatewisegrouping;
	String qrurl;
	int unReadCount;
	int totalCount;
	
	
	public List<AlertInboxBean> getAlertInboxBean() {
		return alertInboxBean;
	}
	public void setAlertInboxBean(List<AlertInboxBean> alertInboxBean) {
		this.alertInboxBean = alertInboxBean;
	}
	public int getAlerttype() {
		return alerttype;
	}
	public void setAlerttype(int alerttype) {
		this.alerttype = alerttype;
	}
	public int getQrflag() {
		return qrflag;
	}
	public void setQrflag(int qrflag) {
		this.qrflag = qrflag;
	}
	public int getSubpage() {
		return subpage;
	}
	public void setSubpage(int subpage) {
		this.subpage = subpage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAlertgroup() {
		return alertgroup;
	}
	public void setAlertgroup(int alertgroup) {
		this.alertgroup = alertgroup;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public int getAlertsection() {
		return alertsection;
	}
	public void setAlertsection(int alertsection) {
		this.alertsection = alertsection;
	}
	public int getActionmap() {
		return actionmap;
	}
	public void setActionmap(int actionmap) {
		this.actionmap = actionmap;
	}
	public int getCategoryorder() {
		return categoryorder;
	}
	public void setCategoryorder(int categoryorder) {
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

	public int getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(int unReadCount) {
		this.unReadCount = unReadCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}