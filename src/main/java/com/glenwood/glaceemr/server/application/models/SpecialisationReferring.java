package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialisation_referring")
public class SpecialisationReferring {

	@Id
	@Column(name="specialisation_referring_id")
	private Integer specialisation_referring_id;

	@Column(name="specialisation_referring_name")
	private String specialisation_referring_name;

	@Column(name="specialisation_referring_description")
	private String specialisation_referring_description;

	@Column(name="nucc_code")
	private String nuccCode;

	public Integer getspecialisation_referring_id() {
		return specialisation_referring_id;
	}

	public void setspecialisation_referring_id(Integer specialisation_referring_id) {
		this.specialisation_referring_id = specialisation_referring_id;
	}

	public String getspecialisation_referring_name() {
		return specialisation_referring_name;
	}

	public void setspecialisation_referring_name(String specialisation_referring_name) {
		this.specialisation_referring_name = specialisation_referring_name;
	}

	public String getspecialisation_referring_description() {
		return specialisation_referring_description;
	}

	public void setspecialisation_referring_description(String specialisation_referring_description) {
		this.specialisation_referring_description = specialisation_referring_description;
	}

	public String getNuccCode() {
		return nuccCode;
	}

	public void setNuccCode(String nuccCode) {
		this.nuccCode = nuccCode;
	}
	
}