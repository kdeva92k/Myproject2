package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ss_status_error_codes")
public class SSStatusErrorCodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getSsStatusErrorCodesId() {
		return ssStatusErrorCodesId;
	}

	public void setSsStatusErrorCodesId(Integer ssStatusErrorCodesId) {
		this.ssStatusErrorCodesId = ssStatusErrorCodesId;
	}

	public Integer getSsStatusErrorCodesTypeId() {
		return ssStatusErrorCodesTypeId;
	}

	public void setSsStatusErrorCodesTypeId(Integer ssStatusErrorCodesTypeId) {
		this.ssStatusErrorCodesTypeId = ssStatusErrorCodesTypeId;
	}

	public String getSsStatusErrorCodesName() {
		return ssStatusErrorCodesName;
	}

	public void setSsStatusErrorCodesName(String ssStatusErrorCodesName) {
		this.ssStatusErrorCodesName = ssStatusErrorCodesName;
	}

	@Id
	@Column(name="ss_status_error_codes_id")
	private Integer ssStatusErrorCodesId;

	@Column(name="ss_status_error_codes_type_id")
	private Integer ssStatusErrorCodesTypeId;

	@Column(name="ss_status_error_codes_name")
	private String ssStatusErrorCodesName;
	
	@Column(name="ss_status_error_codes_description")
	private String ssStatusErrorCodesDescription;

	public String getSsStatusErrorCodesDescription() {
		return ssStatusErrorCodesDescription;
	}

	public void setSsStatusErrorCodesDescription(String ssStatusErrorCodesDescription) {
		this.ssStatusErrorCodesDescription = ssStatusErrorCodesDescription;
	}
}