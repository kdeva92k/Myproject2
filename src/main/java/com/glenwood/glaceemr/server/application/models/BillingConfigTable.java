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
@Table(name = "billing_config_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingConfigTable {

	
	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_config_table_billing_config_table_id_seq")
	@SequenceGenerator(name = "billing_config_table_billing_config_table_id_seq", sequenceName = "billing_config_table_billing_config_table_id_seq", allocationSize = 1)
	@Column(name="billing_config_table_id")
	private Integer billingConfigTableId;

	@Column(name="billing_config_table_config_id")
	private Integer billingConfigTableConfigId;

	@Column(name="billing_config_table_lookup_id")
	private Integer billingConfigTableLookupId;

	@Column(name="billing_config_table_lookup_desc")
	private String billingConfigTableLookupDesc;

	@Column(name="billing_config_table_mapping_desc")
	private String billingConfigTableMappingDesc;
	
	@Column(name="billing_config_table_is_active")
	private Boolean billingConfigTableIsActive;

	public Integer getBillingConfigTableId() {
		return billingConfigTableId;
	}

	public void setBillingConfigTableId(Integer billingConfigTableId) {
		this.billingConfigTableId = billingConfigTableId;
	}

	public Integer getBillingConfigTableConfigId() {
		return billingConfigTableConfigId;
	}

	public void setBillingConfigTableConfigId(Integer billingConfigTableConfigId) {
		this.billingConfigTableConfigId = billingConfigTableConfigId;
	}

	public Integer getBillingConfigTableLookupId() {
		return billingConfigTableLookupId;
	}

	public void setBillingConfigTableLookupId(Integer billingConfigTableLookupId) {
		this.billingConfigTableLookupId = billingConfigTableLookupId;
	}

	public String getBillingConfigTableLookupDesc() {
		return billingConfigTableLookupDesc;
	}

	public void setBillingConfigTableLookupDesc(String billingConfigTableLookupDesc) {
		this.billingConfigTableLookupDesc = billingConfigTableLookupDesc;
	}

	public String getBillingConfigTableMappingDesc() {
		return billingConfigTableMappingDesc;
	}

	public void setBillingConfigTableMappingDesc(
			String billingConfigTableMappingDesc) {
		this.billingConfigTableMappingDesc = billingConfigTableMappingDesc;
	}

	public Boolean getBillingConfigTableIsActive() {
		return billingConfigTableIsActive;
	}

	public void setBillingConfigTableIsActive(Boolean billingConfigTableIsActive) {
		this.billingConfigTableIsActive = billingConfigTableIsActive;
	}
	
}