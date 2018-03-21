package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "generic_cpt")
public class GenericCpt {

	@Id
	@Column(name="generic_cpt_uniqueid")
	private Integer genericCptUniqueid;

	@Column(name="generic_cpt_cptcode")
	private String genericCptCptcode;

	@Column(name="generic_cpt_genericcptcode")
	private String genericCptGenericcptcode;

	@Column(name="generic_cpt_insuranceid")
	private String genericCptInsuranceid;

	@Column(name="generic_cpt_ageoperatorval")
	private String genericCptAgeoperatorval;

	@Column(name="generic_cpt_ageval")
	private String genericCptAgeval;

	@Column(name="generic_cpt_genderoptionval")
	private String genericCptGenderoptionval;
	
	public String getGenericCptGenderoptionval() {
		return genericCptGenderoptionval;
	}

	public void setGenericCptGenderoptionval(String genericCptGenderoptionval) {
		this.genericCptGenderoptionval = genericCptGenderoptionval;
	}

	public Integer getGenericCptUniqueid() {
		return genericCptUniqueid;
	}

	public void setGenericCptUniqueid(Integer genericCptUniqueid) {
		this.genericCptUniqueid = genericCptUniqueid;
	}

	public String getGenericCptCptcode() {
		return genericCptCptcode;
	}

	public void setGenericCptCptcode(String genericCptCptcode) {
		this.genericCptCptcode = genericCptCptcode;
	}

	public String getGenericCptGenericcptcode() {
		return genericCptGenericcptcode;
	}

	public void setGenericCptGenericcptcode(String genericCptGenericcptcode) {
		this.genericCptGenericcptcode = genericCptGenericcptcode;
	}

	public String getGenericCptInsuranceid() {
		return genericCptInsuranceid;
	}

	public void setGenericCptInsuranceid(String genericCptInsuranceid) {
		this.genericCptInsuranceid = genericCptInsuranceid;
	}

	public String getGenericCptAgeoperatorval() {
		return genericCptAgeoperatorval;
	}

	public void setGenericCptAgeoperatorval(String genericCptAgeoperatorval) {
		this.genericCptAgeoperatorval = genericCptAgeoperatorval;
	}

	public String getGenericCptAgeval() {
		return genericCptAgeval;
	}

	public void setGenericCptAgeval(String genericCptAgeval) {
		this.genericCptAgeval = genericCptAgeval;
	}
	
	
}