package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "pharm_details")
public class PharmDetails implements java.io.Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pharm_details_pharm_details_id_seq")
	@SequenceGenerator(name ="pharm_details_pharm_details_id_seq", sequenceName="pharm_details_pharm_details_id_seq", allocationSize=1)
	@Column(name="pharm_details_id")
	private Integer pharmDetailsId;
	
	@Column(name="pharm_details_store_name")
	private String pharmDetailsStoreName;

	@Column(name="pharm_details_address_line_1")
	private String pharmDetailsAddressLine1;

	@Column(name="pharm_details_city")
	private String pharmDetailsCity;

	@Column(name="pharm_details_state")
	private String pharmDetailsState;

	@Column(name="pharm_details_zip")
	private String pharmDetailsZip;

	@Column(name="pharm_details_physician_line")
	private String pharmDetailsPhysicianLine;

	@Column(name="pharm_details_fax")
	private String pharmDetailsFax;

	@Column(name="pharm_details_primary_phone")
	private String pharmDetailsPrimaryPhone;

	@Column(name="pharm_details_is_24hr")
	private String pharmDetailsIs24hr;

	@Column(name="pharm_details_ncpdpid")
	private String pharmDetailsNcpdpid;

	@Column(name="pharm_details_email")
	private String pharmDetailsEmail;

	@Column(name="pharm_details_store_number")
	private String pharmDetailsStoreNumber;

	@Column(name="pharm_details_ref_num_alt1")
	private String pharmDetailsRefNumAlt1;

	@Column(name="pharm_details_ref_num_alt1_qualifier")
	private String pharmDetailsRefNumAlt1Qualifier;

	@Column(name="pharm_details_address_line_2")
	private String pharmDetailsAddressLine2;

	@Column(name="pharm_details_ph_alt1_and_qualifer")
	private String pharmDetailsPhAlt1AndQualifer;

	@Column(name="pharm_details_ph_alt2_and_qualifer")
	private String pharmDetailsPhAlt2AndQualifer;

	@Column(name="pharm_details_ph_alt3_and_qualifer")
	private String pharmDetailsPhAlt3AndQualifer;

	@Column(name="pharm_details_ph_alt4_and_qualifer")
	private String pharmDetailsPhAlt4AndQualifer;

	@Column(name="pharm_details_ph_alt5_and_qualifer")
	private String pharmDetailsPhAlt5AndQualifer;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="pharm_details_active_start_time")
	private Timestamp pharmDetailsActiveStartTime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="pharm_details_active_end_time")
	private Timestamp pharmDetailsActiveEndTime;

	@Column(name="pharm_details_service_level")
	private Integer pharmDetailsServiceLevel;

	@Column(name="pharm_details_partner_account")
	private String pharmDetailsPartnerAccount;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="pharm_details_last_modify_date")
	private Timestamp pharmDetailsLastModifyDate;

	@Column(name="pharm_details_24_hr_flag")
	private Integer pharmDetails24HrFlag;

	@Column(name="pharm_details_cross_street")
	private String pharmDetailsCrossStreet;

	@Column(name="pharm_details_record_change")
	private String pharmDetailsRecordChange;

	@Column(name="pharm_details_old_service_level")
	private String pharmDetailsOldServiceLevel;

	@Column(name="pharm_details_text_service_level")
	private String pharmDetailsTextServiceLevel;

	@Column(name="pharm_details_text_service_level_change")
	private String pharmDetailsTextServiceLevelChange;

	@Column(name="pharm_details_version")
	private String pharmDetailsVersion;

	@Column(name="pharm_details_ss_email")
	private String pharmDetailsSsEmail;

	@Column(name="pharm_details_is_ss_pharm")
	private Integer pharmDetailsIsSsPharm;

	@Column(name="pharm_details_pharmacist_lname")
	private String pharmDetailsPharmacistLname;

	@Column(name="pharm_details_pharmacist_fname")
	private String pharmDetailsPharmacistFname;

	@Column(name="pharm_details_pharmacist_midname")
	private String pharmDetailsPharmacistMidname;

	@Column(name="pharm_details_pharmacist_prefix")
	private String pharmDetailsPharmacistPrefix;

	@Column(name="pharm_details_pharmacist_suffix")
	private String pharmDetailsPharmacistSuffix;

	@Column(name="pharm_details_ph_beeper")
	private String pharmDetailsPhBeeper;

	@Column(name="pharm_details_ph_cell")
	private String pharmDetailsPhCell;

	@Column(name="pharm_details_ph_night")
	private String pharmDetailsPhNight;

	@Column(name="pharm_details_specialtytype1")
	private String pharmDetailsSpecialtytype1;

	@Column(name="pharm_details_specialtytype2")
	private String pharmDetailsSpecialtytype2;

	@Column(name="pharm_details_specialtytype3")
	private String pharmDetailsSpecialtytype3;

	@Column(name="pharm_details_specialtytype4")
	private String pharmDetailsSpecialtytype4;
	
	@Column(name="pharm_details_npi")
	private String pharmDetailsnpi;

	public Integer getPharmDetailsId() {
		return pharmDetailsId;
	}

	public void setPharmDetailsId(Integer pharmDetailsId) {
		this.pharmDetailsId = pharmDetailsId;
	}

	public String getPharmDetailsStoreName() {
		return pharmDetailsStoreName;
	}

	public void setPharmDetailsStoreName(String pharmDetailsStoreName) {
		this.pharmDetailsStoreName = pharmDetailsStoreName;
	}

	public String getPharmDetailsAddressLine1() {
		return pharmDetailsAddressLine1;
	}

	public void setPharmDetailsAddressLine1(String pharmDetailsAddressLine1) {
		this.pharmDetailsAddressLine1 = pharmDetailsAddressLine1;
	}

	public String getPharmDetailsCity() {
		return pharmDetailsCity;
	}

	public void setPharmDetailsCity(String pharmDetailsCity) {
		this.pharmDetailsCity = pharmDetailsCity;
	}

	public String getPharmDetailsState() {
		return pharmDetailsState;
	}

	public void setPharmDetailsState(String pharmDetailsState) {
		this.pharmDetailsState = pharmDetailsState;
	}

	public String getPharmDetailsZip() {
		return pharmDetailsZip;
	}

	public void setPharmDetailsZip(String pharmDetailsZip) {
		this.pharmDetailsZip = pharmDetailsZip;
	}

	public String getPharmDetailsPhysicianLine() {
		return pharmDetailsPhysicianLine;
	}

	public void setPharmDetailsPhysicianLine(String pharmDetailsPhysicianLine) {
		this.pharmDetailsPhysicianLine = pharmDetailsPhysicianLine;
	}

	public String getPharmDetailsFax() {
		return pharmDetailsFax;
	}

	public void setPharmDetailsFax(String pharmDetailsFax) {
		this.pharmDetailsFax = pharmDetailsFax;
	}

	public String getPharmDetailsPrimaryPhone() {
		return pharmDetailsPrimaryPhone;
	}

	public void setPharmDetailsPrimaryPhone(String pharmDetailsPrimaryPhone) {
		this.pharmDetailsPrimaryPhone = pharmDetailsPrimaryPhone;
	}

	public String getPharmDetailsIs24hr() {
		return pharmDetailsIs24hr;
	}

	public void setPharmDetailsIs24hr(String pharmDetailsIs24hr) {
		this.pharmDetailsIs24hr = pharmDetailsIs24hr;
	}

	public String getPharmDetailsNcpdpid() {
		return pharmDetailsNcpdpid;
	}

	public void setPharmDetailsNcpdpid(String pharmDetailsNcpdpid) {
		this.pharmDetailsNcpdpid = pharmDetailsNcpdpid;
	}

	public String getPharmDetailsEmail() {
		return pharmDetailsEmail;
	}

	public void setPharmDetailsEmail(String pharmDetailsEmail) {
		this.pharmDetailsEmail = pharmDetailsEmail;
	}

	public String getPharmDetailsStoreNumber() {
		return pharmDetailsStoreNumber;
	}

	public void setPharmDetailsStoreNumber(String pharmDetailsStoreNumber) {
		this.pharmDetailsStoreNumber = pharmDetailsStoreNumber;
	}

	public String getPharmDetailsRefNumAlt1() {
		return pharmDetailsRefNumAlt1;
	}

	public void setPharmDetailsRefNumAlt1(String pharmDetailsRefNumAlt1) {
		this.pharmDetailsRefNumAlt1 = pharmDetailsRefNumAlt1;
	}

	public String getPharmDetailsRefNumAlt1Qualifier() {
		return pharmDetailsRefNumAlt1Qualifier;
	}

	public void setPharmDetailsRefNumAlt1Qualifier(
			String pharmDetailsRefNumAlt1Qualifier) {
		this.pharmDetailsRefNumAlt1Qualifier = pharmDetailsRefNumAlt1Qualifier;
	}

	public String getPharmDetailsAddressLine2() {
		return pharmDetailsAddressLine2;
	}

	public void setPharmDetailsAddressLine2(String pharmDetailsAddressLine2) {
		this.pharmDetailsAddressLine2 = pharmDetailsAddressLine2;
	}

	public String getPharmDetailsPhAlt1AndQualifer() {
		return pharmDetailsPhAlt1AndQualifer;
	}

	public void setPharmDetailsPhAlt1AndQualifer(
			String pharmDetailsPhAlt1AndQualifer) {
		this.pharmDetailsPhAlt1AndQualifer = pharmDetailsPhAlt1AndQualifer;
	}

	public String getPharmDetailsPhAlt2AndQualifer() {
		return pharmDetailsPhAlt2AndQualifer;
	}

	public void setPharmDetailsPhAlt2AndQualifer(
			String pharmDetailsPhAlt2AndQualifer) {
		this.pharmDetailsPhAlt2AndQualifer = pharmDetailsPhAlt2AndQualifer;
	}

	public String getPharmDetailsPhAlt3AndQualifer() {
		return pharmDetailsPhAlt3AndQualifer;
	}

	public void setPharmDetailsPhAlt3AndQualifer(
			String pharmDetailsPhAlt3AndQualifer) {
		this.pharmDetailsPhAlt3AndQualifer = pharmDetailsPhAlt3AndQualifer;
	}

	public String getPharmDetailsPhAlt4AndQualifer() {
		return pharmDetailsPhAlt4AndQualifer;
	}

	public void setPharmDetailsPhAlt4AndQualifer(
			String pharmDetailsPhAlt4AndQualifer) {
		this.pharmDetailsPhAlt4AndQualifer = pharmDetailsPhAlt4AndQualifer;
	}

	public String getPharmDetailsPhAlt5AndQualifer() {
		return pharmDetailsPhAlt5AndQualifer;
	}

	public void setPharmDetailsPhAlt5AndQualifer(
			String pharmDetailsPhAlt5AndQualifer) {
		this.pharmDetailsPhAlt5AndQualifer = pharmDetailsPhAlt5AndQualifer;
	}

	public Timestamp getPharmDetailsActiveStartTime() {
		return pharmDetailsActiveStartTime;
	}

	public void setPharmDetailsActiveStartTime(Timestamp pharmDetailsActiveStartTime) {
		this.pharmDetailsActiveStartTime = pharmDetailsActiveStartTime;
	}

	public Timestamp getPharmDetailsActiveEndTime() {
		return pharmDetailsActiveEndTime;
	}

	public void setPharmDetailsActiveEndTime(Timestamp pharmDetailsActiveEndTime) {
		this.pharmDetailsActiveEndTime = pharmDetailsActiveEndTime;
	}

	public Integer getPharmDetailsServiceLevel() {
		return pharmDetailsServiceLevel;
	}

	public void setPharmDetailsServiceLevel(Integer pharmDetailsServiceLevel) {
		this.pharmDetailsServiceLevel = pharmDetailsServiceLevel;
	}

	public String getPharmDetailsPartnerAccount() {
		return pharmDetailsPartnerAccount;
	}

	public void setPharmDetailsPartnerAccount(String pharmDetailsPartnerAccount) {
		this.pharmDetailsPartnerAccount = pharmDetailsPartnerAccount;
	}

	public Timestamp getPharmDetailsLastModifyDate() {
		return pharmDetailsLastModifyDate;
	}

	public void setPharmDetailsLastModifyDate(Timestamp pharmDetailsLastModifyDate) {
		this.pharmDetailsLastModifyDate = pharmDetailsLastModifyDate;
	}

	public Integer getPharmDetails24HrFlag() {
		return pharmDetails24HrFlag;
	}

	public void setPharmDetails24HrFlag(Integer pharmDetails24HrFlag) {
		this.pharmDetails24HrFlag = pharmDetails24HrFlag;
	}

	public String getPharmDetailsCrossStreet() {
		return pharmDetailsCrossStreet;
	}

	public void setPharmDetailsCrossStreet(String pharmDetailsCrossStreet) {
		this.pharmDetailsCrossStreet = pharmDetailsCrossStreet;
	}

	public String getPharmDetailsRecordChange() {
		return pharmDetailsRecordChange;
	}

	public void setPharmDetailsRecordChange(String pharmDetailsRecordChange) {
		this.pharmDetailsRecordChange = pharmDetailsRecordChange;
	}

	public String getPharmDetailsOldServiceLevel() {
		return pharmDetailsOldServiceLevel;
	}

	public void setPharmDetailsOldServiceLevel(String pharmDetailsOldServiceLevel) {
		this.pharmDetailsOldServiceLevel = pharmDetailsOldServiceLevel;
	}

	public String getPharmDetailsTextServiceLevel() {
		return pharmDetailsTextServiceLevel;
	}

	public void setPharmDetailsTextServiceLevel(String pharmDetailsTextServiceLevel) {
		this.pharmDetailsTextServiceLevel = pharmDetailsTextServiceLevel;
	}

	public String getPharmDetailsTextServiceLevelChange() {
		return pharmDetailsTextServiceLevelChange;
	}

	public void setPharmDetailsTextServiceLevelChange(
			String pharmDetailsTextServiceLevelChange) {
		this.pharmDetailsTextServiceLevelChange = pharmDetailsTextServiceLevelChange;
	}

	public String getPharmDetailsVersion() {
		return pharmDetailsVersion;
	}

	public void setPharmDetailsVersion(String pharmDetailsVersion) {
		this.pharmDetailsVersion = pharmDetailsVersion;
	}

	public String getPharmDetailsSsEmail() {
		return pharmDetailsSsEmail;
	}

	public void setPharmDetailsSsEmail(String pharmDetailsSsEmail) {
		this.pharmDetailsSsEmail = pharmDetailsSsEmail;
	}

	public Integer getPharmDetailsIsSsPharm() {
		return pharmDetailsIsSsPharm;
	}

	public void setPharmDetailsIsSsPharm(Integer pharmDetailsIsSsPharm) {
		this.pharmDetailsIsSsPharm = pharmDetailsIsSsPharm;
	}

	public String getPharmDetailsPharmacistLname() {
		return pharmDetailsPharmacistLname;
	}

	public void setPharmDetailsPharmacistLname(String pharmDetailsPharmacistLname) {
		this.pharmDetailsPharmacistLname = pharmDetailsPharmacistLname;
	}

	public String getPharmDetailsPharmacistFname() {
		return pharmDetailsPharmacistFname;
	}

	public void setPharmDetailsPharmacistFname(String pharmDetailsPharmacistFname) {
		this.pharmDetailsPharmacistFname = pharmDetailsPharmacistFname;
	}

	public String getPharmDetailsPharmacistMidname() {
		return pharmDetailsPharmacistMidname;
	}

	public void setPharmDetailsPharmacistMidname(
			String pharmDetailsPharmacistMidname) {
		this.pharmDetailsPharmacistMidname = pharmDetailsPharmacistMidname;
	}

	public String getPharmDetailsPharmacistPrefix() {
		return pharmDetailsPharmacistPrefix;
	}

	public void setPharmDetailsPharmacistPrefix(String pharmDetailsPharmacistPrefix) {
		this.pharmDetailsPharmacistPrefix = pharmDetailsPharmacistPrefix;
	}

	public String getPharmDetailsPharmacistSuffix() {
		return pharmDetailsPharmacistSuffix;
	}

	public void setPharmDetailsPharmacistSuffix(String pharmDetailsPharmacistSuffix) {
		this.pharmDetailsPharmacistSuffix = pharmDetailsPharmacistSuffix;
	}

	public String getPharmDetailsPhBeeper() {
		return pharmDetailsPhBeeper;
	}

	public void setPharmDetailsPhBeeper(String pharmDetailsPhBeeper) {
		this.pharmDetailsPhBeeper = pharmDetailsPhBeeper;
	}

	public String getPharmDetailsPhCell() {
		return pharmDetailsPhCell;
	}

	public void setPharmDetailsPhCell(String pharmDetailsPhCell) {
		this.pharmDetailsPhCell = pharmDetailsPhCell;
	}

	public String getPharmDetailsPhNight() {
		return pharmDetailsPhNight;
	}

	public void setPharmDetailsPhNight(String pharmDetailsPhNight) {
		this.pharmDetailsPhNight = pharmDetailsPhNight;
	}

	public String getPharmDetailsSpecialtytype1() {
		return pharmDetailsSpecialtytype1;
	}

	public void setPharmDetailsSpecialtytype1(String pharmDetailsSpecialtytype1) {
		this.pharmDetailsSpecialtytype1 = pharmDetailsSpecialtytype1;
	}

	public String getPharmDetailsSpecialtytype2() {
		return pharmDetailsSpecialtytype2;
	}

	public void setPharmDetailsSpecialtytype2(String pharmDetailsSpecialtytype2) {
		this.pharmDetailsSpecialtytype2 = pharmDetailsSpecialtytype2;
	}

	public String getPharmDetailsSpecialtytype3() {
		return pharmDetailsSpecialtytype3;
	}

	public void setPharmDetailsSpecialtytype3(String pharmDetailsSpecialtytype3) {
		this.pharmDetailsSpecialtytype3 = pharmDetailsSpecialtytype3;
	}

	public String getPharmDetailsSpecialtytype4() {
		return pharmDetailsSpecialtytype4;
	}

	public void setPharmDetailsSpecialtytype4(String pharmDetailsSpecialtytype4) {
		this.pharmDetailsSpecialtytype4 = pharmDetailsSpecialtytype4;
	}
	public String getPharmDetailsnpi() {
		return pharmDetailsnpi;
	}

	public void setPharmDetailsnpi(String pharmDetailsnpi) {
		this.pharmDetailsnpi = pharmDetailsnpi;
	}
}