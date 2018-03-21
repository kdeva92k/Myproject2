package com.glenwood.glaceemr.server.application.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "generic_letter_header")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericLetterHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="generic_letter_header_id")
	private Integer genericLetterHeaderId;
	
	@Column(name="generic_letter_header_name")
	private String genericLetterHeaderName;
	
	@Column(name="generic_letter_header_type")
	private Integer genericLetterHeaderType;
	
	@Column(name="generic_letter_header_left")
	private Integer genericLetterHeaderLeft;
	
	@Column(name="generic_letter_header_address")
	private Integer genericLetterHeaderAddress;
	
	@Column(name="generic_letter_header_isdefault")
	private Boolean genericLetterHeaderIsDefault;
	
	@Column(name="generic_letter_header_isactive")
	private Boolean genericLetterHeaderIsActive;

	@Column(name="generic_letter_header_left_address")
	private Integer genericLetterHeaderLeftAddress;
	
	public Integer getGenericLetterHeaderId() {
		return genericLetterHeaderId;
	}

	public void setGenericLetterHeaderId(Integer genericLetterHeaderId) {
		this.genericLetterHeaderId = genericLetterHeaderId;
	}

	public String getGenericLetterHeaderName() {
		return genericLetterHeaderName;
	}

	public void setGenericLetterHeaderName(String genericLetterHeaderName) {
		this.genericLetterHeaderName = genericLetterHeaderName;
	}

	public Integer getGenericLetterHeaderType() {
		return genericLetterHeaderType;
	}

	public void setGenericLetterHeaderType(Integer genericLetterHeaderType) {
		this.genericLetterHeaderType = genericLetterHeaderType;
	}

	public Integer getGenericLetterHeaderLeft() {
		return genericLetterHeaderLeft;
	}

	public void setGenericLetterHeaderLeft(Integer genericLetterHeaderLeft) {
		this.genericLetterHeaderLeft = genericLetterHeaderLeft;
	}

	public Integer getGenericLetterHeaderAddress() {
		return genericLetterHeaderAddress;
	}

	public void setGenericLetterHeaderAddress(Integer genericLetterHeaderAddress) {
		this.genericLetterHeaderAddress = genericLetterHeaderAddress;
	}

	public Boolean getGenericLetterHeaderIsDefault() {
		return genericLetterHeaderIsDefault;
	}

	public void setGenericLetterHeaderIsDefault(Boolean genericLetterHeaderIsDefault) {
		this.genericLetterHeaderIsDefault = genericLetterHeaderIsDefault;
	}

	public Boolean getGenericLetterHeaderIsActive() {
		return genericLetterHeaderIsActive;
	}

	public void setGenericLetterHeaderIsActive(Boolean genericLetterHeaderIsActive) {
		this.genericLetterHeaderIsActive = genericLetterHeaderIsActive;
	}

	public Integer getGenericLetterHeaderLeftAddress() {
		return genericLetterHeaderLeftAddress;
	}

	public void setGenericLetterHeaderLeftAddress(
			Integer genericLetterHeaderLeftAddress) {
		this.genericLetterHeaderLeftAddress = genericLetterHeaderLeftAddress;
	}

			
}
