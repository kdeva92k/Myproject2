package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="alert_privilege")
public class AlertPrivilege {

	@Id
	Integer alert_privilege_id;
	Integer alert_privilege_user_id;
	Integer alert_privilege_category_id;
	Boolean alert_privilege_expanded;
	Boolean alert_privilege_date_grouping_needed;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_privilege_category_id",referencedColumnName="alert_category_id",insertable=false,updatable=false)
	AlertCategory alertCategoryTable;
	
	
	public Integer getAlert_privilege_id() {
		return alert_privilege_id;
	}
	public void setAlert_privilege_id(Integer alert_privilege_id) {
		this.alert_privilege_id = alert_privilege_id;
	}
	public Integer getAlert_privilege_user_id() {
		return alert_privilege_user_id;
	}
	public void setAlert_privilege_user_id(Integer alert_privilege_user_id) {
		this.alert_privilege_user_id = alert_privilege_user_id;
	}
	public Integer getAlert_privilege_category_id() {
		return alert_privilege_category_id;
	}
	public void setAlert_privilege_category_id(Integer alert_privilege_category_id) {
		this.alert_privilege_category_id = alert_privilege_category_id;
	}
	public Boolean getAlert_privilege_expanded() {
		return alert_privilege_expanded;
	}
	public void setAlert_privilege_expanded(Boolean alert_privilege_expanded) {
		this.alert_privilege_expanded = alert_privilege_expanded;
	}
	public Boolean getAlert_privilege_date_grouping_needed() {
		return alert_privilege_date_grouping_needed;
	}
	public void setAlert_privilege_date_grouping_needed(
			Boolean alert_privilege_date_grouping_needed) {
		this.alert_privilege_date_grouping_needed = alert_privilege_date_grouping_needed;
	}
}
