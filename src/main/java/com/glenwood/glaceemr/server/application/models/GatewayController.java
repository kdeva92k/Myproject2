package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "gateway_controller")
public class GatewayController {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="sequence")
	@SequenceGenerator(name ="sequence", sequenceName="gateway_controller_seq")
	@Column(name="gateway_controller_id")
	private Integer gatewayControllerId;

	@Column(name="gateway_controller_module_name")
	private String gatewayControllerModuleName;

	@Column(name="gateway_controller_url")
	private String gatewayControllerUrl;

	public Integer getGatewayControllerId() {
		return gatewayControllerId;
	}

	public void setGatewayControllerId(Integer gatewayControllerId) {
		this.gatewayControllerId = gatewayControllerId;
	}

	public String getGatewayControllerModuleName() {
		return gatewayControllerModuleName;
	}

	public void setGatewayControllerModuleName(String gatewayControllerModuleName) {
		this.gatewayControllerModuleName = gatewayControllerModuleName;
	}

	public String getGatewayControllerUrl() {
		return gatewayControllerUrl;
	}

	public void setGatewayControllerUrl(String gatewayControllerUrl) {
		this.gatewayControllerUrl = gatewayControllerUrl;
	}
	
	
}