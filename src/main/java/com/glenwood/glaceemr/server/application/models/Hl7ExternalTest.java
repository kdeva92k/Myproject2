package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "hl7_external_test")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hl7ExternalTest {

	@Id
	@Column(name="hl7_external_test_id")
	private Long hl7ExternalTestId;

	@Column(name="hl7_external_test_labcompanyid", nullable=false)
	private Integer hl7ExternalTestLabcompanyid;

	@Column(name="hl7_external_test_isactive", columnDefinition="Boolean default true")
	private Boolean hl7ExternalTestIsactive;

	@Column(name="hl7_external_test_code", length=80)
	private String hl7ExternalTestCode;

	@Column(name="hl7_external_test_name", length=300)
	private String hl7ExternalTestName;

	@Column(name="hl7_external_test_state", length=50)
	private String hl7ExternalTestState;

	@Column(name="hl7_external_test_flag", length=50)
	private String hl7ExternalTestFlag;

	@Column(name="hl7_external_test_specimenttype", length=255)
	private String hl7ExternalTestSpecimenttype;

	@Column(name="hl7_external_test_suffix", length=255)
	private String hl7ExternalTestSuffix;

	@Column(name="hl7_external_test_class", length=50, columnDefinition="String default 'OT'")
	private String hl7ExternalTestClass;

	@Column(name="hl7_external_test_aoe_type", length=50, columnDefinition="String default ''")
	private String hl7ExternalTestAoeType;
	
	@Column(name="hl7_external_test_specimen_soource", length=50, columnDefinition="String default ''")
	private String hl7ExternalTestSpecimenSoource;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="hl7ExternalTestTable")
	public List<Hl7ExternalTestmapping> hl7ExternalTestmappingTable;
	
	
	public Long getHl7ExternalTestId() {
		return hl7ExternalTestId;
	}

	public void setHl7ExternalTestId(Long hl7ExternalTestId) {
		this.hl7ExternalTestId = hl7ExternalTestId;
	}

	public Integer getHl7ExternalTestLabcompanyid() {
		return hl7ExternalTestLabcompanyid;
	}

	public void setHl7ExternalTestLabcompanyid(Integer hl7ExternalTestLabcompanyid) {
		this.hl7ExternalTestLabcompanyid = hl7ExternalTestLabcompanyid;
	}

	public Boolean getHl7ExternalTestIsactive() {
		return hl7ExternalTestIsactive;
	}

	public void setHl7ExternalTestIsactive(Boolean hl7ExternalTestIsactive) {
		this.hl7ExternalTestIsactive = hl7ExternalTestIsactive;
	}

	public String getHl7ExternalTestCode() {
		return hl7ExternalTestCode;
	}

	public void setHl7ExternalTestCode(String hl7ExternalTestCode) {
		this.hl7ExternalTestCode = hl7ExternalTestCode;
	}

	public String getHl7ExternalTestName() {
		return hl7ExternalTestName;
	}

	public void setHl7ExternalTestName(String hl7ExternalTestName) {
		this.hl7ExternalTestName = hl7ExternalTestName;
	}

	public String getHl7ExternalTestState() {
		return hl7ExternalTestState;
	}

	public void setHl7ExternalTestState(String hl7ExternalTestState) {
		this.hl7ExternalTestState = hl7ExternalTestState;
	}

	public String getHl7ExternalTestFlag() {
		return hl7ExternalTestFlag;
	}

	public void setHl7ExternalTestFlag(String hl7ExternalTestFlag) {
		this.hl7ExternalTestFlag = hl7ExternalTestFlag;
	}

	public String getHl7ExternalTestSpecimenttype() {
		return hl7ExternalTestSpecimenttype;
	}

	public void setHl7ExternalTestSpecimenttype(String hl7ExternalTestSpecimenttype) {
		this.hl7ExternalTestSpecimenttype = hl7ExternalTestSpecimenttype;
	}

	public String getHl7ExternalTestSuffix() {
		return hl7ExternalTestSuffix;
	}

	public void setHl7ExternalTestSuffix(String hl7ExternalTestSuffix) {
		this.hl7ExternalTestSuffix = hl7ExternalTestSuffix;
	}

	public String getHl7ExternalTestClass() {
		return hl7ExternalTestClass;
	}

	public void setHl7ExternalTestClass(String hl7ExternalTestClass) {
		this.hl7ExternalTestClass = hl7ExternalTestClass;
	}

	public String getHl7ExternalTestAoeType() {
		return hl7ExternalTestAoeType;
	}

	public void setHl7ExternalTestAoeType(String hl7ExternalTestAoeType) {
		this.hl7ExternalTestAoeType = hl7ExternalTestAoeType;
	}

	public String getHl7ExternalTestSpecimenSoource() {
		return hl7ExternalTestSpecimenSoource;
	}

	public void setHl7ExternalTestSpecimenSoource(
			String hl7ExternalTestSpecimenSoource) {
		this.hl7ExternalTestSpecimenSoource = hl7ExternalTestSpecimenSoource;
	}
}