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
@Table(name = "pos_type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PosType {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pos_type_pos_type_unique_id_seq")
	@SequenceGenerator(name = "pos_type_pos_type_unique_id_seq", sequenceName = "pos_type_pos_type_unique_id_seq", allocationSize = 1)
	@Column(name="pos_type_type_id")
	private Integer posTypeTypeId;
	
	@Column(name="pos_type_unique_id")
	private Integer posTypeUniqueId;
	
	@Column(name="pos_type_type_name")
	private String posTypeTypeName;
	
	@Column(name="pos_type_isactive")
	private boolean posTypeIsactive;

	@Column(name="h555555",columnDefinition="integer default 1")
	private Integer h555555;
	
	/*
	@OneToMany(mappedBy="PosType")
	@JsonManagedReference
	List<PosTable> PosTable;*/
	

	public Integer getPosTypeUniqueId() {
		return posTypeUniqueId;
	}

	public void setPosTypeUniqueId(Integer posTypeUniqueId) {
		this.posTypeUniqueId = posTypeUniqueId;
	}

	public Integer getPosTypeTypeId() {
		return posTypeTypeId;
	}

	public void setPosTypeTypeId(Integer posTypeTypeId) {
		this.posTypeTypeId = posTypeTypeId;
	}

	public String getPosTypeTypeName() {
		return posTypeTypeName;
	}

	public void setPosTypeTypeName(String posTypeTypeName) {
		this.posTypeTypeName = posTypeTypeName;
	}

	public boolean isPosTypeIsactive() {
		return posTypeIsactive;
	}

	public void setPosTypeIsactive(boolean posTypeIsactive) {
		this.posTypeIsactive = posTypeIsactive;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

}
