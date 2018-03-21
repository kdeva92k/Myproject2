package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pos_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PosTable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pos_table_pos_table_relation_id_seq")
	@SequenceGenerator(name = "pos_table_pos_table_relation_id_seq", sequenceName = "pos_table_pos_table_relation_id_seq", allocationSize = 1)
	@Column(name="pos_table_relation_id")
	private Integer posTableRelationId;

	@Column(name="pos_table_place_id")
	private Integer posTablePlaceId;

	@Column(name="pos_table_pos_code")
	private Integer posTablePosCode;

	@Column(name="pos_table_place_of_service")
	private String posTablePlaceOfService;

	@Column(name="pos_table_facility_comments")
	private String posTableFacilityComments;

	@Column(name="pos_table_is_active")
	private Boolean posTableIsActive;

	@Column(name="h555555",columnDefinition="integer default 1")
	private Integer h555555;

	@Column(name="pos_table_practice")
	private String posTablePractice;

	@Column(name="pos_table_isdme",columnDefinition="boolean default true")
	private Boolean posTableIsdme;
	
	@Column(name="pos_table_istransfirst_configured",columnDefinition="boolean default false")
	private Boolean posTableIstransfirstConfigured;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="pos_table_pos_code", referencedColumnName="pos_type_type_id", insertable=false, updatable=false)
	private PosType posType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="pos_table_place_id", referencedColumnName="place_of_service_placeid", insertable=false, updatable=false)
	private PlaceOfService placeOfService;
	
	
	@OneToMany(mappedBy="posTable", fetch=FetchType.LAZY)
	@JsonManagedReference
	List<Encounter> encounter;
	
	@OneToMany(mappedBy="posTable")
	@JsonBackReference
	List<Admission> admission;

	public PlaceOfService getPlaceOfService() {
		return placeOfService;
	}

	public void setPlaceOfService(PlaceOfService placeOfService) {
		this.placeOfService = placeOfService;
	}

	public PosType getPosType() {
		return posType;
	}

	public void setPosType(PosType posType) {
		this.posType = posType;
	}
	
	public Boolean getPosTableIstransfirstConfigured() {
		return posTableIstransfirstConfigured;
	}

	public void setPosTableIstransfirstConfigured(
			Boolean posTableIstransfirstConfigured) {
		this.posTableIstransfirstConfigured = posTableIstransfirstConfigured;
	}

	public Integer getPosTableRelationId() {
		return posTableRelationId;
	}

	public void setPosTableRelationId(Integer posTableRelationId) {
		this.posTableRelationId = posTableRelationId;
	}

	public Integer getPosTablePlaceId() {
		return posTablePlaceId;
	}

	public void setPosTablePlaceId(Integer posTablePlaceId) {
		this.posTablePlaceId = posTablePlaceId;
	}

	public Integer getPosTablePosCode() {
		return posTablePosCode;
	}

	public void setPosTablePosCode(Integer posTablePosCode) {
		this.posTablePosCode = posTablePosCode;
	}

	public String getPosTablePlaceOfService() {
		return posTablePlaceOfService;
	}

	public void setPosTablePlaceOfService(String posTablePlaceOfService) {
		this.posTablePlaceOfService = posTablePlaceOfService;
	}

	public String getPosTableFacilityComments() {
		return posTableFacilityComments;
	}

	public void setPosTableFacilityComments(String posTableFacilityComments) {
		this.posTableFacilityComments = posTableFacilityComments;
	}

	public Boolean getPosTableIsActive() {
		return posTableIsActive;
	}

	public void setPosTableIsActive(Boolean posTableIsActive) {
		this.posTableIsActive = posTableIsActive;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public String getPosTablePractice() {
		return posTablePractice;
	}

	public void setPosTablePractice(String posTablePractice) {
		this.posTablePractice = posTablePractice;
	}

	public Boolean getPosTableIsdme() {
		return posTableIsdme;
	}

	public void setPosTableIsdme(Boolean posTableIsdme) {
		this.posTableIsdme = posTableIsdme;
	}

		
}