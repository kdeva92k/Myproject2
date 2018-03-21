package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "body_site")
public class BodySite {

	@Id
	@Column(name="body_site_code")
	private String bodySiteCode;

	@Column(name="body_site_codesysemoid")
	private String bodySiteCodesysemoid;

	@Column(name="body_site_description")
	private String bodySiteDescription;

	@Column(name="body_site_alternatecode")
	private String bodySiteAlternatecode;
	
	public String getBodySiteCode() {
		return bodySiteCode;
	}

	public void setBodySiteCode(String bodySiteCode) {
		this.bodySiteCode = bodySiteCode;
	}

	public String getBodySiteCodesysemoid() {
		return bodySiteCodesysemoid;
	}

	public void setBodySiteCodesysemoid(String bodySiteCodesysemoid) {
		this.bodySiteCodesysemoid = bodySiteCodesysemoid;
	}

	public String getBodySiteDescription() {
		return bodySiteDescription;
	}

	public void setBodySiteDescription(String bodySiteDescription) {
		this.bodySiteDescription = bodySiteDescription;
	}

	public String getBodySiteAlternatecode() {
		return bodySiteAlternatecode;
	}

	public void setBodySiteAlternatecode(String bodySiteAlternatecode) {
		this.bodySiteAlternatecode = bodySiteAlternatecode;
	}
}