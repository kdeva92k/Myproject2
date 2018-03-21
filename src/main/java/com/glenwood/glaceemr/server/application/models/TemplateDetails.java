package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "template_details")
public class TemplateDetails {

	@Column(name="template_details_id")
	private Integer template_details_id;

	@Id
	@Column(name="template_details_name")
	private String template_details_name;

	@Column(name="template_details_isActive")
	private Boolean template_details_isActive;
	
	
	@OneToMany(mappedBy="template_details")
	List<LeafLibrary> leafLibraries;
	
	
	public List<LeafLibrary> getLeafLibraries() {
		return leafLibraries;
	}

	public void setLeafLibraries(List<LeafLibrary> leafLibraries) {
		this.leafLibraries = leafLibraries;
	}

	public Integer gettemplate_details_id() {
		return template_details_id;
	}

	public void settemplate_details_id(Integer template_details_id) {
		this.template_details_id = template_details_id;
	}

	public String gettemplate_details_name() {
		return template_details_name;
	}

	public void settemplate_details_name(String template_details_name) {
		this.template_details_name = template_details_name;
	}

	public Boolean gettemplate_details_isActive() {
		return template_details_isActive;
	}

	public void settemplate_details_isActive(Boolean template_details_isActive) {
		this.template_details_isActive = template_details_isActive;
	}
	
	
}