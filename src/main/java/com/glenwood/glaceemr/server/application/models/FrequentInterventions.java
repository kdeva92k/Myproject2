package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "frequent_interventions")
public class FrequentInterventions{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="frequent_interventions_id_seq")
	@SequenceGenerator(name ="frequent_interventions_id_seq", sequenceName="frequent_interventions_id_seq", allocationSize=1)
	
	@Column(name="frequent_interventions_id")
	private Integer frequentinterventionsid;
	
	@Column(name="frequent_interventions_description")
	private String frequentinterventionsdescription;

	@Column(name="frequent_interventions_code")
	private String frequentinterventionscode;
	
	@Column(name="frequent_interventions_code_system_oid")
	private String frequentinterventionscodesystemoid;
	
	@Column(name="frequent_interventions_status")
	private Integer frequentinterventionsstatus;
	
	@Column(name="frequent_interventions_user_id")
	private Integer frequentinterventionsuserid;
	
	@Column(name="frequent_interventions_group")
	private String frequentinterventionsgroup;
	
	@Column(name="frequent_interventions_created_by")
	private Integer frequentinterventionscreatedby;
		
	@Column(name="frequent_interventions_created_on")
	private Timestamp frequentinterventionscreatedon;
	
	@Column(name="frequent_interventions_category")
	private String frequentinterventionscategory;

	public Integer getFrequentinterventionsid() {
		return frequentinterventionsid;
	}

	public String getFrequentinterventionsdescription() {
		return frequentinterventionsdescription;
	}

	public String getFrequentinterventionscode() {
		return frequentinterventionscode;
	}

	public String getFrequentinterventionscodesystemoid() {
		return frequentinterventionscodesystemoid;
	}

	public Integer getFrequentinterventionsstatus() {
		return frequentinterventionsstatus;
	}

	public Integer getFrequentinterventionsuserid() {
		return frequentinterventionsuserid;
	}

	public String getFrequentinterventionsgroup() {
		return frequentinterventionsgroup;
	}

	public Integer getFrequentinterventionscreatedby() {
		return frequentinterventionscreatedby;
	}

	public Timestamp getFrequentinterventionscreatedon() {
		return frequentinterventionscreatedon;
	}

	public void setFrequentinterventionsid(Integer frequentinterventionsid) {
		this.frequentinterventionsid = frequentinterventionsid;
	}

	public void setFrequentinterventionsdescription(
			String frequentinterventionsdescription) {
		this.frequentinterventionsdescription = frequentinterventionsdescription;
	}

	public void setFrequentinterventionscode(String frequentinterventionscode) {
		this.frequentinterventionscode = frequentinterventionscode;
	}

	public void setFrequentinterventionscodesystemoid(
			String frequentinterventionscodesystemoid) {
		this.frequentinterventionscodesystemoid = frequentinterventionscodesystemoid;
	}

	public void setFrequentinterventionsstatus(Integer frequentinterventionsstatus) {
		this.frequentinterventionsstatus = frequentinterventionsstatus;
	}

	public void setFrequentinterventionsuserid(Integer frequentinterventionsuserid) {
		this.frequentinterventionsuserid = frequentinterventionsuserid;
	}

	public void setFrequentinterventionsgroup(String frequentinterventionsgroup) {
		this.frequentinterventionsgroup = frequentinterventionsgroup;
	}

	public void setFrequentinterventionscreatedby(
			Integer frequentinterventionscreatedby) {
		this.frequentinterventionscreatedby = frequentinterventionscreatedby;
	}

	public void setFrequentinterventionscreatedon(
			Timestamp frequentinterventionscreatedon) {
		this.frequentinterventionscreatedon = frequentinterventionscreatedon;
	}

	public String getFrequentinterventionscategory() {
		return frequentinterventionscategory;
	}

	public void setFrequentinterventionscategory(
			String frequentinterventionscategory) {
		this.frequentinterventionscategory = frequentinterventionscategory;
	}

	
}