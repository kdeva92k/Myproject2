package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "chief_complaints")
public class ChiefComplaints {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chief_complaints_chief_complaints_id_seq")
	@SequenceGenerator(name = "chief_complaints_chief_complaints_id_seq", sequenceName = "chief_complaints_chief_complaints_id_seq", allocationSize = 1)
	@Column(name="chief_complaints_id")
	private Integer chiefComplaintsId;

	@Column(name="chief_complaints_name")
	private String chiefComplaintsName;

	@Column(name="chief_complaints_isactive")
	private Boolean chiefComplaintsIsactive;

	@Column(name="chief_complaints_default1")
	private Boolean chiefComplaintsDefault1;

	@Column(name="chief_complaints_default2")
	private Boolean chiefComplaintsDefault2;

	@Column(name="h555555")
	private String h555555;

	@Column(name="chief_complaints_hitcount")
	private Integer chiefComplaintsHitcount;

	public Integer getChiefComplaintsId() {
		return chiefComplaintsId;
	}

	public void setChiefComplaintsId(Integer chiefComplaintsId) {
		this.chiefComplaintsId = chiefComplaintsId;
	}

	public String getChiefComplaintsName() {
		return chiefComplaintsName;
	}

	public void setChiefComplaintsName(String chiefComplaintsName) {
		this.chiefComplaintsName = chiefComplaintsName;
	}

	public Boolean getChiefComplaintsIsactive() {
		return chiefComplaintsIsactive;
	}

	public void setChiefComplaintsIsactive(Boolean chiefComplaintsIsactive) {
		this.chiefComplaintsIsactive = chiefComplaintsIsactive;
	}

	public Boolean getChiefComplaintsDefault1() {
		return chiefComplaintsDefault1;
	}

	public void setChiefComplaintsDefault1(Boolean chiefComplaintsDefault1) {
		this.chiefComplaintsDefault1 = chiefComplaintsDefault1;
	}

	public Boolean getChiefComplaintsDefault2() {
		return chiefComplaintsDefault2;
	}

	public void setChiefComplaintsDefault2(Boolean chiefComplaintsDefault2) {
		this.chiefComplaintsDefault2 = chiefComplaintsDefault2;
	}

	public String getH555555() {
		return h555555;
	}

	public void setH555555(String h555555) {
		this.h555555 = h555555;
	}

	public Integer getChiefComplaintsHitcount() {
		return chiefComplaintsHitcount;
	}

	public void setChiefComplaintsHitcount(Integer chiefComplaintsHitcount) {
		this.chiefComplaintsHitcount = chiefComplaintsHitcount;
	}
	
	
	
}