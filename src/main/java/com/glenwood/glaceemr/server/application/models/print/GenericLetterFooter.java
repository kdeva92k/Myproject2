package com.glenwood.glaceemr.server.application.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "generic_letter_footer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericLetterFooter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="generic_letter_footer_id")
	private Integer genericLetterFooterId;
	
	@Column(name="generic_letter_footer_name")
	private String genericLetterFooterName;

	@Column(name="generic_letter_footer_variant_id")
	private Integer genericLetterFooterVariant;
	
	@Column(name="generic_letter_footer_custom")
	private String genericLetterFooterCustom;
	
	@Column(name="generic_letter_footer_style")
	private String genericLetterFooterStyle;

	@Column(name="generic_letter_footer_isdefault")
	private Boolean genericLetterFooterIsDefault;
	
	@Column(name="generic_letter_footer_isactive")
	private Boolean genericLetterFooterIsActive;

	public Integer getGenericLetterFooterId() {
		return genericLetterFooterId;
	}

	public void setGenericLetterFooterId(Integer genericLetterFooterId) {
		this.genericLetterFooterId = genericLetterFooterId;
	}

	public String getGenericLetterFooterName() {
		return genericLetterFooterName;
	}

	public void setGenericLetterFooterName(String genericLetterFooterName) {
		this.genericLetterFooterName = genericLetterFooterName;
	}

	public Integer getGenericLetterFooterVariant() {
		return genericLetterFooterVariant;
	}

	public void setGenericLetterFooterVariant(Integer genericLetterFooterVariant) {
		this.genericLetterFooterVariant = genericLetterFooterVariant;
	}

	public String getGenericLetterFooterCustom() {
		return genericLetterFooterCustom;
	}

	public void setGenericLetterFooterCustom(String genericLetterFooterCustom) {
		this.genericLetterFooterCustom = genericLetterFooterCustom;
	}

	public String getGenericLetterFooterStyle() {
		return genericLetterFooterStyle;
	}

	public void setGenericLetterFooterStyle(String genericLetterFooterStyle) {
		this.genericLetterFooterStyle = genericLetterFooterStyle;
	}

	public Boolean getGenericLetterFooterIsDefault() {
		return genericLetterFooterIsDefault;
	}

	public void setGenericLetterFooterIsDefault(Boolean genericLetterFooterIsDefault) {
		this.genericLetterFooterIsDefault = genericLetterFooterIsDefault;
	}

	public Boolean getGenericLetterFooterIsActive() {
		return genericLetterFooterIsActive;
	}

	public void setGenericLetterFooterIsActive(Boolean genericLetterFooterIsActive) {
		this.genericLetterFooterIsActive = genericLetterFooterIsActive;
	}
	
}
