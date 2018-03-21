package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "bill_status")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillStatus implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_status_bill_status_billid_seq")
	@SequenceGenerator(name = "bill_status_bill_status_billid_seq", sequenceName = "bill_status_bill_status_billid_seq", allocationSize = 1)
	@Column(name="bill_status_billid")
	private Integer bill_status_billid;

	@Column(name="bill_status_nextbillid")
	private Integer bill_status_nextbillid;

	@Column(name="bill_status_status")
	private String bill_status_status;

	@Column(name="bill_status_scpt_id")
	private Integer bill_status_scpt_id;

	@Column(name="bill_status_description")
	private String bill_status_description;

	@Column(name="bill_status_billintervel")
	private Integer bill_status_billintervel;

	public Integer getbill_status_billid() {
		return bill_status_billid;
	}

	public void setbill_status_billid(Integer bill_status_billid) {
		this.bill_status_billid = bill_status_billid;
	}

	public Integer getbill_status_nextbillid() {
		return bill_status_nextbillid;
	}

	public void setbill_status_nextbillid(Integer bill_status_nextbillid) {
		this.bill_status_nextbillid = bill_status_nextbillid;
	}

	public String getbill_status_status() {
		return bill_status_status;
	}

	public void setbill_status_status(String bill_status_status) {
		this.bill_status_status = bill_status_status;
	}

	public Integer getbill_status_scpt_id() {
		return bill_status_scpt_id;
	}

	public void setbill_status_scpt_id(Integer bill_status_scpt_id) {
		this.bill_status_scpt_id = bill_status_scpt_id;
	}

	public String getbill_status_description() {
		return bill_status_description;
	}

	public void setbill_status_description(String bill_status_description) {
		this.bill_status_description = bill_status_description;
	}

	public Integer getbill_status_billintervel() {
		return bill_status_billintervel;
	}

	public void setbill_status_billintervel(Integer bill_status_billintervel) {
		this.bill_status_billintervel = bill_status_billintervel;
	}
	
	
	
}