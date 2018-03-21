package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "aftercare_ins")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AftercareIns {

	@Id
	@Column(name="aftercare_ins_id")
	private Integer aftercareInsId;

	@Column(name="aftercare_ins_unknown1")
	private Integer aftercareInsUnknown1;

	@Column(name="aftercare_ins_unknown5")
	private Integer aftercareInsUnknown5;

	@Column(name="aftercare_ins_unknown2")
	private Integer aftercareInsUnknown2;

	@Column(name="aftercare_ins_unknown3")
	private Integer aftercareInsUnknown3;

	@Column(name="aftercare_ins_name")
	private String aftercareInsName;

	@Column(name="aftercare_ins_description")
	private String aftercareInsDescription;

	@Column(name="aftercare_ins_filename")
	private String aftercareInsFilename;

	@Column(name="aftercare_ins_language")
	private String aftercareInsLanguage;

	@Column(name="aftercare_ins_isactive")
	private Boolean aftercareInsIsactive;

	@Column(name="aftercare_ins_source")
	private String aftercareInsSource;

	@Column(name="aftercare_ins_unknown6")
	private String aftercareInsUnknown6;

	public Integer getAftercareInsId() {
		return aftercareInsId;
	}

	public void setAftercareInsId(Integer aftercareInsId) {
		this.aftercareInsId = aftercareInsId;
	}

	public Integer getAftercareInsUnknown1() {
		return aftercareInsUnknown1;
	}

	public void setAftercareInsUnknown1(Integer aftercareInsUnknown1) {
		this.aftercareInsUnknown1 = aftercareInsUnknown1;
	}

	public Integer getAftercareInsUnknown5() {
		return aftercareInsUnknown5;
	}

	public void setAftercareInsUnknown5(Integer aftercareInsUnknown5) {
		this.aftercareInsUnknown5 = aftercareInsUnknown5;
	}

	public Integer getAftercareInsUnknown2() {
		return aftercareInsUnknown2;
	}

	public void setAftercareInsUnknown2(Integer aftercareInsUnknown2) {
		this.aftercareInsUnknown2 = aftercareInsUnknown2;
	}

	public Integer getAftercareInsUnknown3() {
		return aftercareInsUnknown3;
	}

	public void setAftercareInsUnknown3(Integer aftercareInsUnknown3) {
		this.aftercareInsUnknown3 = aftercareInsUnknown3;
	}

	public String getAftercareInsName() {
		return aftercareInsName;
	}

	public void setAftercareInsName(String aftercareInsName) {
		this.aftercareInsName = aftercareInsName;
	}

	public String getAftercareInsDescription() {
		return aftercareInsDescription;
	}

	public void setAftercareInsDescription(String aftercareInsDescription) {
		this.aftercareInsDescription = aftercareInsDescription;
	}

	public String getAftercareInsFilename() {
		return aftercareInsFilename;
	}

	public void setAftercareInsFilename(String aftercareInsFilename) {
		this.aftercareInsFilename = aftercareInsFilename;
	}

	public String getAftercareInsLanguage() {
		return aftercareInsLanguage;
	}

	public void setAftercareInsLanguage(String aftercareInsLanguage) {
		this.aftercareInsLanguage = aftercareInsLanguage;
	}

	public Boolean getAftercareInsIsactive() {
		return aftercareInsIsactive;
	}

	public void setAftercareInsIsactive(Boolean aftercareInsIsactive) {
		this.aftercareInsIsactive = aftercareInsIsactive;
	}

	public String getAftercareInsSource() {
		return aftercareInsSource;
	}

	public void setAftercareInsSource(String aftercareInsSource) {
		this.aftercareInsSource = aftercareInsSource;
	}

	public String getAftercareInsUnknown6() {
		return aftercareInsUnknown6;
	}

	public void setAftercareInsUnknown6(String aftercareInsUnknown6) {
		this.aftercareInsUnknown6 = aftercareInsUnknown6;
	}
}