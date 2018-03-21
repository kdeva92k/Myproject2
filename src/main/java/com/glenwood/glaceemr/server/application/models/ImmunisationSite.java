package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "immunisation_site")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImmunisationSite {

	@Id
	@Column(name="immunisation_site_id")
	private Integer immunisationSiteId;

	@Column(name="immunisation_site_siteinfo")
	private String immunisationSiteSiteinfo;

	@Column(name="immunisation_site_sortorder")
	private Integer immunisationSiteSortorder;

	@Column(name="immunisation_site_isactive")
	private Boolean immunisationSiteIsactive;

	@Column(name="immunisation_site_sitekeycode")
	private String immunisationSiteSitekeycode;

	public Integer getImmunisationSiteId() {
		return immunisationSiteId;
	}

	public void setImmunisationSiteId(Integer immunisationSiteId) {
		this.immunisationSiteId = immunisationSiteId;
	}

	public String getImmunisationSiteSiteinfo() {
		return immunisationSiteSiteinfo;
	}

	public void setImmunisationSiteSiteinfo(String immunisationSiteSiteinfo) {
		this.immunisationSiteSiteinfo = immunisationSiteSiteinfo;
	}

	public Integer getImmunisationSiteSortorder() {
		return immunisationSiteSortorder;
	}

	public void setImmunisationSiteSortorder(Integer immunisationSiteSortorder) {
		this.immunisationSiteSortorder = immunisationSiteSortorder;
	}

	public Boolean getImmunisationSiteIsactive() {
		return immunisationSiteIsactive;
	}

	public void setImmunisationSiteIsactive(Boolean immunisationSiteIsactive) {
		this.immunisationSiteIsactive = immunisationSiteIsactive;
	}

	public String getImmunisationSiteSitekeycode() {
		return immunisationSiteSitekeycode;
	}

	public void setImmunisationSiteSitekeycode(String immunisationSiteSitekeycode) {
		this.immunisationSiteSitekeycode = immunisationSiteSitekeycode;
	}
}