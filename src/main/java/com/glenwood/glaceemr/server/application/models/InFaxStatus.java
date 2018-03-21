package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "infaxstatus")
public class InFaxStatus {
	@Column(name="id")
	private Integer id;

	@Id
	@Column(name="statusid")
	private Integer statusid;

	@Column(name="displayname")
	private String displayname;

	@Column(name="description")
	private String description;

	@Column(name="groupid")
	private Integer groupid;

	@Column(name="color")
	private String color;

	@Column(name="isfromchart")
	private Integer isfromchart;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getIsfromchart() {
		return isfromchart;
	}

	public void setIsfromchart(Integer isfromchart) {
		this.isfromchart = isfromchart;
	}
}