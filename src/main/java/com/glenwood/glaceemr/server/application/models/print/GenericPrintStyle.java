package com.glenwood.glaceemr.server.application.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "generic_print_styles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericPrintStyle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="generic_print_styles_id")
	private Integer genericPrintStyleId;
	
	@Column(name="generic_print_styles_name")
	private String genericPrintStyleName;

	@Column(name="generic_print_styles_header_id")
	private Integer genericPrintStyleHeaderId;
	
	@Column(name="generic_print_styles_patient_header_id")
	private Integer genericPrintStylePatientHeaderId;
	
	@Column(name="generic_print_styles_footer_id")
	private Integer genericPrintStyleFooterId;
	
	@Column(name="generic_print_styles_sign_id")
	private Integer genericPrintStyleSignId;

	@Column(name="generic_print_styles_isdefault")
	private Boolean genericPrintStyleIsDefault;
	
	@Column(name="generic_print_styles_isactive")
	private Boolean genericPrintStyleIsActive;
	
	
	public Integer getGenericPrintStyleSignId() {
		return genericPrintStyleSignId;
	}

	public void setGenericPrintStyleSignId(Integer genericPrintStyleSignId) {
		this.genericPrintStyleSignId = genericPrintStyleSignId;
	}

	public Integer getGenericPrintStyleId() {
		return genericPrintStyleId;
	}

	public void setGenericPrintStyleId(Integer genericPrintStyleId) {
		this.genericPrintStyleId = genericPrintStyleId;
	}

	public String getGenericPrintStyleName() {
		return genericPrintStyleName;
	}

	public void setGenericPrintStyleName(String genericPrintStyleName) {
		this.genericPrintStyleName = genericPrintStyleName;
	}

	public Integer getGenericPrintStyleHeaderId() {
		return genericPrintStyleHeaderId;
	}

	public void setGenericPrintStyleHeaderId(Integer genericPrintStyleHeaderId) {
		this.genericPrintStyleHeaderId = genericPrintStyleHeaderId;
	}

	public Integer getGenericPrintStylePatientHeaderId() {
		return genericPrintStylePatientHeaderId;
	}

	public void setGenericPrintStylePatientHeaderId(
			Integer genericPrintStylePatientHeaderId) {
		this.genericPrintStylePatientHeaderId = genericPrintStylePatientHeaderId;
	}

	public Integer getGenericPrintStyleFooterId() {
		return genericPrintStyleFooterId;
	}

	public void setGenericPrintStyleFooterId(Integer genericPrintStyleFooterId) {
		this.genericPrintStyleFooterId = genericPrintStyleFooterId;
	}

	public Boolean getGenericPrintStyleIsDefault() {
		return genericPrintStyleIsDefault;
	}

	public void setGenericPrintStyleIsDefault(Boolean genericPrintStyleIsDefault) {
		this.genericPrintStyleIsDefault = genericPrintStyleIsDefault;
	}

	public Boolean getGenericPrintStyleIsActive() {
		return genericPrintStyleIsActive;
	}

	public void setGenericPrintStyleIsActive(Boolean genericPrintStyleIsActive) {
		this.genericPrintStyleIsActive = genericPrintStyleIsActive;
	}
	
	
	
}
