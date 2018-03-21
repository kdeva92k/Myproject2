package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ros_system")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RosSystem {

	@Id
	@Column(name="ros_system_id")
	private Integer rosSystemId;

	@Column(name="ros_system_name")
	private String rosSystemName;

	@Column(name="ros_system_orderby")
	private Integer rosSystemOrderby;

	@Column(name="ros_system_isactive")
	private Boolean rosSystemIsactive;

	@Column(name="ros_system_eandmtype")
	private String rosSystemEandmtype;

	@Column(name="ros_system_deferred_gwid")
	private String rosSystemDeferredGwid;
}