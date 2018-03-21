
package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_user_default")
public class SchedulerUserDefault {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name ="sequence", sequenceName="sch_user_default_sch_user_default_id_seq", allocationSize=1)
	@Column(name="sch_user_default_id")
	private Integer schUserDefaultId;

	@Column(name="sch_user_default_user_id")
	private Integer schUserDefaultUserId;

	@Column(name="sch_user_default_resource_id")
	private Integer schUserDefaultResourceId;

	@Column(name="sch_user_default_todisplay")
	private Boolean schUserDefaultTodisplay;

	public Integer getSchUserDefaultId() {
		return schUserDefaultId;
	}

	public void setSchUserDefaultId(Integer schUserDefaultId) {
		this.schUserDefaultId = schUserDefaultId;
	}

	public Integer getSchUserDefaultUserId() {
		return schUserDefaultUserId;
	}

	public void setSchUserDefaultUserId(Integer schUserDefaultUserId) {
		this.schUserDefaultUserId = schUserDefaultUserId;
	}

	public Integer getSchUserDefaultResourceId() {
		return schUserDefaultResourceId;
	}

	public void setSchUserDefaultResourceId(Integer schUserDefaultResourceId) {
		this.schUserDefaultResourceId = schUserDefaultResourceId;
	}

	public Boolean getSchUserDefaultTodisplay() {
		return schUserDefaultTodisplay;
	}

	public void setSchUserDefaultTodisplay(Boolean schUserDefaultTodisplay) {
		this.schUserDefaultTodisplay = schUserDefaultTodisplay;
	}
	
	
}