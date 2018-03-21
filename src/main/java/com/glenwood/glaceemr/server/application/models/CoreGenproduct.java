package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "core_genproduct")
public class CoreGenproduct {

	
	@Column(name="id")
	private Integer id;
	
	@Id
	@Column(name="genproduct_id")
	private String genproductId;
	
	@OneToOne
	@JoinColumn(name="drug_id", referencedColumnName="drug_id", insertable=false, updatable=false)
	@JsonManagedReference
    CoreClassGendrug coreclassgendrug;
	
	@Column(name="generic_product_name")
	private String genericProductName;

	@Column(name="drug_id")
	private String drugId;

	@Column(name="route_id")
	private String routeId;

	@Column(name="doseform_id")
	private String doseformId;

	@Column(name="strength_id")
	private String strengthId;

	@Column(name="csa_code")
	private String csaCode;

	@Column(name="rx_otc_status")
	private String rxOtcStatus;

	@Column(name="is_active")
	private String isActive;

	@Column(name="obsolete_date")
	private String obsoleteDate;

	@Column(name="jcode")
	private String jcode;

	@Column(name="jcode_description")
	private String jcodeDescription;

	@Column(name="manufact_generic_product_name")
	private String manufactGenericProductName;

	@Column(name="create_date")
	private String createDate;
}