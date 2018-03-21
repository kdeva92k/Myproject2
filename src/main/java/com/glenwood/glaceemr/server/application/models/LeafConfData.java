package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leaf_conf_data")
public class LeafConfData {

	
	@Column(name="leaf_conf_data_id")
	private Integer leaf_conf_data_id;

	@Column(name="leaf_conf_data_xmldata")
	private String leaf_conf_data_xmldata;

	@Id
	@Column(name="leaf_conf_data_leafid")
	private Integer leaf_conf_data_leafid;

	

	public Integer getleaf_conf_data_id() {
		return leaf_conf_data_id;
	}

	public void setleaf_conf_data_id(Integer leaf_conf_data_id) {
		this.leaf_conf_data_id = leaf_conf_data_id;
	}

	public String getleaf_conf_data_xmldata() {
		return leaf_conf_data_xmldata;
	}

	public void setleaf_conf_data_xmldata(String leaf_conf_data_xmldata) {
		this.leaf_conf_data_xmldata = leaf_conf_data_xmldata;
	}

	public Integer getleaf_conf_data_leafid() {
		return leaf_conf_data_leafid;
	}

	public void setleaf_conf_data_leafid(Integer leaf_conf_data_leafid) {
		this.leaf_conf_data_leafid = leaf_conf_data_leafid;
	}
	
	
}