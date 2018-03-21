package com.glenwood.glaceemr.server.application.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "rxnorm")
public class Rxnorm {

	@Id
	@Column(name="rxnorm_id")
	private Integer rxnormId;

	@Column(name="rxnorm_lui")
	private String rxnormLui;

	@Column(name="rxnorm_rxcui")
	private String rxnormRxcui;

	@Column(name="rxnorm_rxaui")
	private String rxnormRxaui;

	@Column(name="rxnorm_sui")
	private String rxnormSui;

	@Column(name="rxnorm_code")
	private String rxnormCode;

	@Column(name="rxnorm_stype")
	private String rxnormStype;

	@Column(name="rxnorm_atui")
	private String rxnormAtui;

	@Column(name="rxnorm_satui")
	private String rxnormSatui;

	@Column(name="rxnorm_atv")
	private String rxnormAtv;

	@Column(name="rxnorm_sab")
	private String rxnormSab;

	@Column(name="rxnorm_suppress")
	private String rxnormSuppress;

	@Column(name="rxnorm_cvf")
	private String rxnormCvf;

	public Integer getRxnormId() {
		return rxnormId;
	}

	public void setRxnormId(Integer rxnormId) {
		this.rxnormId = rxnormId;
	}

	public String getRxnormLui() {
		return rxnormLui;
	}

	public void setRxnormLui(String rxnormLui) {
		this.rxnormLui = rxnormLui;
	}

	public String getRxnormRxcui() {
		return rxnormRxcui;
	}

	public void setRxnormRxcui(String rxnormRxcui) {
		this.rxnormRxcui = rxnormRxcui;
	}

	public String getRxnormRxaui() {
		return rxnormRxaui;
	}

	public void setRxnormRxaui(String rxnormRxaui) {
		this.rxnormRxaui = rxnormRxaui;
	}

	public String getRxnormSui() {
		return rxnormSui;
	}

	public void setRxnormSui(String rxnormSui) {
		this.rxnormSui = rxnormSui;
	}

	public String getRxnormCode() {
		return rxnormCode;
	}

	public void setRxnormCode(String rxnormCode) {
		this.rxnormCode = rxnormCode;
	}

	public String getRxnormStype() {
		return rxnormStype;
	}

	public void setRxnormStype(String rxnormStype) {
		this.rxnormStype = rxnormStype;
	}

	public String getRxnormAtui() {
		return rxnormAtui;
	}

	public void setRxnormAtui(String rxnormAtui) {
		this.rxnormAtui = rxnormAtui;
	}

	public String getRxnormSatui() {
		return rxnormSatui;
	}

	public void setRxnormSatui(String rxnormSatui) {
		this.rxnormSatui = rxnormSatui;
	}

	public String getRxnormAtv() {
		return rxnormAtv;
	}

	public void setRxnormAtv(String rxnormAtv) {
		this.rxnormAtv = rxnormAtv;
	}

	public String getRxnormSab() {
		return rxnormSab;
	}

	public void setRxnormSab(String rxnormSab) {
		this.rxnormSab = rxnormSab;
	}

	public String getRxnormSuppress() {
		return rxnormSuppress;
	}

	public void setRxnormSuppress(String rxnormSuppress) {
		this.rxnormSuppress = rxnormSuppress;
	}

	public String getRxnormCvf() {
		return rxnormCvf;
	}

	public void setRxnormCvf(String rxnormCvf) {
		this.rxnormCvf = rxnormCvf;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="rxnorm_atv",referencedColumnName="ndc_code",insertable=false,updatable=false)
	@JsonManagedReference
	NdcDrugBrandMap ndcDrugBrandMap;

	public NdcDrugBrandMap getNdcDrugBrandMap() {
		return ndcDrugBrandMap;
	}

	public void setNdcDrugBrandMap(NdcDrugBrandMap ndcDrugBrandMap) {
		this.ndcDrugBrandMap = ndcDrugBrandMap;
	}
}