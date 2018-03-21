package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "allergies_encountermap")
public class AllergiesEncountermap {

	@Id
	@Column(name="allergencmap_id")
	private Integer allergencmapId;

	@Column(name="allergencmap_chartid")
	private Integer allergencmapChartid;

	@Column(name="allergencmap_encounterid")
	private Integer allergencmapEncounterid;

	@Column(name="allergencmap_allergid")
	private String allergencmapAllergid;

	@Column(name="allergencmap_reviewed")
	private Boolean allergencmapReviewed;

	@Column(name="allergencmap_reviewedby")
	private String allergencmapReviewedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="allergencmap_reviewedon")
	private Timestamp allergencmapReviewedon;

	@Column(name="allergencmap_status")
	private Integer allergencmapStatus;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula= @JoinFormula(value="allergencmap_reviewedby::integer" , referencedColumnName="emp_profile_empid"))})
	@JsonManagedReference
	EmployeeProfile empProfile;

	public Integer getAllergencmapId() {
		return allergencmapId;
	}

	public void setAllergencmapId(Integer allergencmapId) {
		this.allergencmapId = allergencmapId;
	}

	public Integer getAllergencmapChartid() {
		return allergencmapChartid;
	}

	public void setAllergencmapChartid(Integer allergencmapChartid) {
		this.allergencmapChartid = allergencmapChartid;
	}

	public Integer getAllergencmapEncounterid() {
		return allergencmapEncounterid;
	}

	public void setAllergencmapEncounterid(Integer allergencmapEncounterid) {
		this.allergencmapEncounterid = allergencmapEncounterid;
	}

	public String getAllergencmapAllergid() {
		return allergencmapAllergid;
	}

	public void setAllergencmapAllergid(String allergencmapAllergid) {
		this.allergencmapAllergid = allergencmapAllergid;
	}

	public Boolean getAllergencmapReviewed() {
		return allergencmapReviewed;
	}

	public void setAllergencmapReviewed(Boolean allergencmapReviewed) {
		this.allergencmapReviewed = allergencmapReviewed;
	}

	public String getAllergencmapReviewedby() {
		return allergencmapReviewedby;
	}

	public void setAllergencmapReviewedby(String allergencmapReviewedby) {
		this.allergencmapReviewedby = allergencmapReviewedby;
	}

	public Timestamp getAllergencmapReviewedon() {
		return allergencmapReviewedon;
	}

	public void setAllergencmapReviewedon(Timestamp allergencmapReviewedon) {
		this.allergencmapReviewedon = allergencmapReviewedon;
	}

	public Integer getAllergencmapStatus() {
		return allergencmapStatus;
	}

	public void setAllergencmapStatus(Integer allergencmapStatus) {
		this.allergencmapStatus = allergencmapStatus;
	}
	
	
}