package com.glenwood.glaceemr.server.application.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "letter_header_content")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LetterHeaderContent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="letter_header_content_id")
	private Integer letterHeaderContentId;
	
	@Column(name="letter_header_content_style_map_id")
	private Integer letterHeaderContentStyleMapId;
	
	@Column(name="letter_header_content_variant")
	private Integer letterHeaderContentVariant;
	
	@Column(name="letter_header_content_flag")
	private Integer letterHeaderContentFlag;
	
	@Column(name="letter_header_content_custom")
	private String letterHeaderContentCustom;
	
	@Column(name="letter_header_content_style")
	private String letterHeaderContentStyle;

	public Integer getLetterHeaderContentId() {
		return letterHeaderContentId;
	}

	public void setLetterHeaderContentId(Integer letterHeaderContentId) {
		this.letterHeaderContentId = letterHeaderContentId;
	}

	public Integer getLetterHeaderContentStyleMapId() {
		return letterHeaderContentStyleMapId;
	}

	public void setLetterHeaderContentStyleMapId(
			Integer letterHeaderContentStyleMapId) {
		this.letterHeaderContentStyleMapId = letterHeaderContentStyleMapId;
	}

	public Integer getLetterHeaderContentVariant() {
		return letterHeaderContentVariant;
	}

	public void setLetterHeaderContentVariant(Integer letterHeaderContentVariant) {
		this.letterHeaderContentVariant = letterHeaderContentVariant;
	}

	public Integer getLetterHeaderContentFlag() {
		return letterHeaderContentFlag;
	}

	public void setLetterHeaderContentFlag(Integer letterHeaderContentFlag) {
		this.letterHeaderContentFlag = letterHeaderContentFlag;
	}

	public String getLetterHeaderContentCustom() {
		return letterHeaderContentCustom;
	}

	public void setLetterHeaderContentCustom(String letterHeaderContentCustom) {
		this.letterHeaderContentCustom = letterHeaderContentCustom;
	}

	public String getLetterHeaderContentStyle() {
		return letterHeaderContentStyle;
	}

	public void setLetterHeaderContentStyle(String letterHeaderContentStyle) {
		this.letterHeaderContentStyle = letterHeaderContentStyle;
	}
	
	
	
}
