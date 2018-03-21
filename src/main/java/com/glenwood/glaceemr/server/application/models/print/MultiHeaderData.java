package com.glenwood.glaceemr.server.application.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "multi_header_data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MultiHeaderData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="multi_header_data_id")
	private Integer multiHeaderDataId;

	@Column(name="multi_header_data_map_id")
	private Integer multiHeaderDataMapId;

	@Column(name="multi_header_data_custom")
	private String multiHeaderDataCustom;

	@Column(name="multi_header_data_tagline")
	private String multiHeaderDataTagline;

	@Column(name="multi_header_data_order")
	private Integer multiHeaderDataOrder;

	public Integer getMultiHeaderDataId() {
		return multiHeaderDataId;
	}

	public void setMultiHeaderDataId(Integer multiHeaderDataId) {
		this.multiHeaderDataId = multiHeaderDataId;
	}

	public Integer getMultiHeaderDataMapId() {
		return multiHeaderDataMapId;
	}

	public void setMultiHeaderDataMapId(Integer multiHeaderDataMapId) {
		this.multiHeaderDataMapId = multiHeaderDataMapId;
	}

	public String getMultiHeaderDataCustom() {
		return multiHeaderDataCustom;
	}

	public void setMultiHeaderDataCustom(String multiHeaderDataCustom) {
		this.multiHeaderDataCustom = multiHeaderDataCustom;
	}

	public String getMultiHeaderDataTagline() {
		return multiHeaderDataTagline;
	}

	public void setMultiHeaderDataTagline(String multiHeaderDataTagline) {
		this.multiHeaderDataTagline = multiHeaderDataTagline;
	}

	public Integer getMultiHeaderDataOrder() {
		return multiHeaderDataOrder;
	}

	public void setMultiHeaderDataOrder(Integer multiHeaderDataOrder) {
		this.multiHeaderDataOrder = multiHeaderDataOrder;
	}
	
	

}
