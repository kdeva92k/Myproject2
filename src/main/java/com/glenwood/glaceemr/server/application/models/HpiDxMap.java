package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "hpi_dx_map")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HpiDxMap {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hpi_dx_map_hpi_dx_map_id_seq")
	@SequenceGenerator(name ="hpi_dx_map_hpi_dx_map_id_seq", sequenceName="hpi_dx_map_hpi_dx_map_id_seq", allocationSize=1)
	@Column(name="hpi_dx_map_id")
	private Integer hpiDxMapId;

	@Column(name="hpi_dx_map_symptomid")
	private Integer hpiDxMapSymptomid;

	@Column(name="hpi_dx_map_dx")
	private String hpiDxMapDx;

	@Column(name="hpi_dx_map_isactive")
	private Boolean hpiDxMapIsactive;

	public Integer getHpiDxMapId() {
		return hpiDxMapId;
	}

	public void setHpiDxMapId(Integer hpiDxMapId) {
		this.hpiDxMapId = hpiDxMapId;
	}

	public Integer getHpiDxMapSymptomid() {
		return hpiDxMapSymptomid;
	}

	public void setHpiDxMapSymptomid(Integer hpiDxMapSymptomid) {
		this.hpiDxMapSymptomid = hpiDxMapSymptomid;
	}

	public String getHpiDxMapDx() {
		return hpiDxMapDx;
	}

	public void setHpiDxMapDx(String hpiDxMapDx) {
		this.hpiDxMapDx = hpiDxMapDx;
	}

	public Boolean getHpiDxMapIsactive() {
		return hpiDxMapIsactive;
	}

	public void setHpiDxMapIsactive(Boolean hpiDxMapIsactive) {
		this.hpiDxMapIsactive = hpiDxMapIsactive;
	}
	
}