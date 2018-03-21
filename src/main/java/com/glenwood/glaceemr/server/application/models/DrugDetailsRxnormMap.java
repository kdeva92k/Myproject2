package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "drug_details_rxnorm_map")
public class DrugDetailsRxnormMap {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="drug_details_rxnorm_map_drug_details_rxnorm_map_id_seq")
	@SequenceGenerator(name =" drug_details_rxnorm_map_drug_details_rxnorm_map_id_seq", sequenceName="drug_details_rxnorm_map_drug_details_rxnorm_map_id_seq", allocationSize=1)
	@Column(name="drug_details_rxnorm_map_id")
	private Integer drugDetailsRxnormMapId;

	@Id
	@Column(name="drug_details_rxnorm_map_drug_id")
	private Integer drugDetailsRxnormMapDrugId;

	public Integer getDrugDetailsRxnormMapId() {
		return drugDetailsRxnormMapId;
	}

	public void setDrugDetailsRxnormMapId(Integer drugDetailsRxnormMapId) {
		this.drugDetailsRxnormMapId = drugDetailsRxnormMapId;
	}

	public Integer getDrugDetailsRxnormMapDrugId() {
		return drugDetailsRxnormMapDrugId;
	}

	public void setDrugDetailsRxnormMapDrugId(Integer drugDetailsRxnormMapDrugId) {
		this.drugDetailsRxnormMapDrugId = drugDetailsRxnormMapDrugId;
	}

	public String getDrugDetailsRxnormMapRxnormCode() {
		return drugDetailsRxnormMapRxnormCode;
	}

	public void setDrugDetailsRxnormMapRxnormCode(
			String drugDetailsRxnormMapRxnormCode) {
		this.drugDetailsRxnormMapRxnormCode = drugDetailsRxnormMapRxnormCode;
	}

	@Column(name="drug_details_rxnorm_map_rxnorm_code")
	private String drugDetailsRxnormMapRxnormCode;
}