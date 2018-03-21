package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "vaccine_order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VaccineOrder {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vaccine_order_vaccine_order_id_seq")
	@SequenceGenerator(name = "vaccine_order_vaccine_order_id_seq", sequenceName = "vaccine_order_vaccine_order_id_seq", allocationSize = 1)
	@Column(name="vaccine_order_id")
	private Integer vaccineOrderId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="vaccine_order_date")
	private Timestamp vaccineOrderDate;

	@Column(name="vaccine_order_man_details_id")
	private Integer vaccineOrderManDetailsId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name="vaccine_order_man_details_id", referencedColumnName="vaccine_man_details_id" , insertable=false, updatable=false)
	private VaccineManDetails vaccineManDetails;

	public VaccineManDetails getVaccineManDetails() {
		return vaccineManDetails;
	}

	public void setVaccineManDetails(VaccineManDetails vaccineManDetails) {
		this.vaccineManDetails = vaccineManDetails;
	}

	public Integer getVaccineOrderId() {
		return vaccineOrderId;
	}

	public void setVaccineOrderId(Integer vaccineOrderId) {
		this.vaccineOrderId = vaccineOrderId;
	}

	public Timestamp getVaccineOrderDate() {
		return vaccineOrderDate;
	}

	public void setVaccineOrderDate(Timestamp vaccineOrderDate) {
		this.vaccineOrderDate = vaccineOrderDate;
	}

	public Integer getVaccineOrderManDetailsId() {
		return vaccineOrderManDetailsId;
	}

	public void setVaccineOrderManDetailsId(Integer vaccineOrderManDetailsId) {
		this.vaccineOrderManDetailsId = vaccineOrderManDetailsId;
	}
	
}