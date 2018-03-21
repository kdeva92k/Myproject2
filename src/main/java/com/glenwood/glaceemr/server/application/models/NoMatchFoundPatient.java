package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nomatchfoundpatient")
public class NoMatchFoundPatient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

/*	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="nomatchfoundpatient_id_seq")
	@SequenceGenerator(name =" nomatchfoundpatient_id_seq", sequenceName="nomatchfoundpatient_id_seq", allocationSize=1)*/
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="patient_id")
	private Integer patientid;

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}
}