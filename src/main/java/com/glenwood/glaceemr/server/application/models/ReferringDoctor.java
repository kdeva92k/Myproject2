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
@Table(name = "referring_doctor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferringDoctor {

	public ReferringDoctor(){
		
	}
	
	public ReferringDoctor(Integer referring_doctor_uniqueid, String referring_doctor_lastname, String referring_doctor_midinitial, String referring_doctor_firstname, String referring_doctor_credential){
		this.referring_doctor_uniqueid= referring_doctor_uniqueid;
		this.referring_doctor_lastname= referring_doctor_lastname;
		this.referring_doctor_midinitial= referring_doctor_midinitial;
		this.referring_doctor_firstname= referring_doctor_firstname;
		this.referring_doctor_credential= referring_doctor_credential;
	}
	public ReferringDoctor(Integer referring_doctor_uniqueid, String referring_doctor_lastname, String referring_doctor_firstname){
		this.referring_doctor_uniqueid= referring_doctor_uniqueid;
		this.referring_doctor_lastname= referring_doctor_lastname;
		this.referring_doctor_firstname= referring_doctor_firstname;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "referring_doctor_referring_doctor_uniqueid_seq")
	@SequenceGenerator(name = "referring_doctor_referring_doctor_uniqueid_seq", sequenceName = "referring_doctor_referring_doctor_uniqueid_seq", allocationSize = 1)
	@Column(name="referring_doctor_uniqueid")
	private Integer referring_doctor_uniqueid;

	@Column(name="referring_doctor_rdoctorid")
	private String referring_doctor_rdoctorid;

	@Column(name="referring_doctor_lastname")
	private String referring_doctor_lastname;

	@Column(name="referring_doctor_midinitial")
	private String referring_doctor_midinitial;

	@Column(name="referring_doctor_firstname")
	private String referring_doctor_firstname;

	@Column(name="referring_doctor_address")
	private String referring_doctor_address;

	@Column(name="referring_doctor_city")
	private String referring_doctor_city;

	@Column(name="referring_doctor_state")
	private String referring_doctor_state;

	@Column(name="referring_doctor_zip")
	private String referring_doctor_zip;

	@Column(name="referring_doctor_phoneno")
	private String referring_doctor_phoneno;

	@Column(name="referring_doctor_fax_number")
	private String referring_doctor_fax_number;

	@Column(name="referring_doctor_upin_no")
	private String referring_doctor_upin_no;

	@Column(name="referring_doctor_medicare_pin")
	private String referring_doctor_medicare_pin;

	@Column(name="referring_doctor_npi")
	private String referring_doctor_npi;

	@Column(name="referring_doctor_isactive")
	private Boolean referring_doctor_isactive;

	@Column(name="referring_doctor_type")
	private Short referring_doctor_type;

	@Column(name="referring_doctor_hitcount")
	private Integer referring_doctor_hitcount;

	@Column(name="referring_doctor_iserphysician")
	private Boolean referring_doctor_iserphysician;

	@Column(name="referring_doctor_speciality_id")
	private Integer referring_doctor_speciality_id;

	@Column(name="referring_doctor_referringdoctor")
	private String referring_doctor_referringdoctor;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="referring_doctor_credential")
	private String referring_doctor_credential;

	@Column(name="referring_doctor_mailid")
	private String referring_doctor_mailid;

	@Column(name="referring_doctor_prefix")
	private String referring_doctor_prefix;

	@Column(name="taxonomy_code")
	private String taxonomyCode;

	@Column(name="direct_email_address")
	private String directEmailAddress;

	@Column(name="practice_name")
	private String practiceName;

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public Integer getreferring_doctor_uniqueid() {
		return referring_doctor_uniqueid;
	}

	public String getreferring_doctor_rdoctorid() {
		return referring_doctor_rdoctorid;
	}

	public String getreferring_doctor_lastname() {
		return referring_doctor_lastname;
	}

	public String getreferring_doctor_midinitial() {
		return referring_doctor_midinitial;
	}

	public String getreferring_doctor_firstname() {
		return referring_doctor_firstname;
	}

	public String getreferring_doctor_address() {
		return referring_doctor_address;
	}

	public String getreferring_doctor_city() {
		return referring_doctor_city;
	}

	public String getreferring_doctor_state() {
		return referring_doctor_state;
	}

	public String getreferring_doctor_zip() {
		return referring_doctor_zip;
	}

	public String getreferring_doctor_phoneno() {
		return referring_doctor_phoneno;
	}

	public String getreferring_doctor_fax_number() {
		return referring_doctor_fax_number;
	}

	public String getreferring_doctor_upin_no() {
		return referring_doctor_upin_no;
	}

	public String getreferring_doctor_medicare_pin() {
		return referring_doctor_medicare_pin;
	}

	public String getreferring_doctor_npi() {
		return referring_doctor_npi;
	}

	public Boolean getreferring_doctor_isactive() {
		return referring_doctor_isactive;
	}

	public Short getreferring_doctor_type() {
		return referring_doctor_type;
	}

	public Integer getreferring_doctor_hitcount() {
		return referring_doctor_hitcount;
	}

	public Boolean getreferring_doctor_iserphysician() {
		return referring_doctor_iserphysician;
	}

	public Integer getreferring_doctor_speciality_id() {
		return referring_doctor_speciality_id;
	}

	public String getreferring_doctor_referringdoctor() {
		return referring_doctor_referringdoctor;
	}

	public Integer getH555555() {
		return h555555;
	}

	public String getreferring_doctor_credential() {
		return referring_doctor_credential;
	}

	public String getreferring_doctor_mailid() {
		return referring_doctor_mailid;
	}

	public String getreferring_doctor_prefix() {
		return referring_doctor_prefix;
	}

	public String getTaxonomyCode() {
		return taxonomyCode;
	}

	public String getDirectEmailAddress() {
		return directEmailAddress;
	}

	public void setreferring_doctor_uniqueid(Integer referring_doctor_uniqueid) {
		this.referring_doctor_uniqueid = referring_doctor_uniqueid;
	}

	public void setreferring_doctor_rdoctorid(String referring_doctor_rdoctorid) {
		this.referring_doctor_rdoctorid = referring_doctor_rdoctorid;
	}

	public void setreferring_doctor_lastname(String referring_doctor_lastname) {
		this.referring_doctor_lastname = referring_doctor_lastname;
	}

	public void setreferring_doctor_midinitial(String referring_doctor_midinitial) {
		this.referring_doctor_midinitial = referring_doctor_midinitial;
	}

	public void setreferring_doctor_firstname(String referring_doctor_firstname) {
		this.referring_doctor_firstname = referring_doctor_firstname;
	}

	public void setreferring_doctor_address(String referring_doctor_address) {
		this.referring_doctor_address = referring_doctor_address;
	}

	public void setreferring_doctor_city(String referring_doctor_city) {
		this.referring_doctor_city = referring_doctor_city;
	}

	public void setreferring_doctor_state(String referring_doctor_state) {
		this.referring_doctor_state = referring_doctor_state;
	}

	public void setreferring_doctor_zip(String referring_doctor_zip) {
		this.referring_doctor_zip = referring_doctor_zip;
	}

	public void setreferring_doctor_phoneno(String referring_doctor_phoneno) {
		this.referring_doctor_phoneno = referring_doctor_phoneno;
	}

	public void setreferring_doctor_fax_number(String referring_doctor_fax_number) {
		this.referring_doctor_fax_number = referring_doctor_fax_number;
	}

	public void setreferring_doctor_upin_no(String referring_doctor_upin_no) {
		this.referring_doctor_upin_no = referring_doctor_upin_no;
	}

	public void setreferring_doctor_medicare_pin(String referring_doctor_medicare_pin) {
		this.referring_doctor_medicare_pin = referring_doctor_medicare_pin;
	}

	public void setreferring_doctor_npi(String referring_doctor_npi) {
		this.referring_doctor_npi = referring_doctor_npi;
	}

	public void setreferring_doctor_isactive(Boolean referring_doctor_isactive) {
		this.referring_doctor_isactive = referring_doctor_isactive;
	}

	public void setreferring_doctor_type(Short referring_doctor_type) {
		this.referring_doctor_type = referring_doctor_type;
	}

	public void setreferring_doctor_hitcount(Integer referring_doctor_hitcount) {
		this.referring_doctor_hitcount = referring_doctor_hitcount;
	}

	public void setreferring_doctor_iserphysician(Boolean referring_doctor_iserphysician) {
		this.referring_doctor_iserphysician = referring_doctor_iserphysician;
	}

	public void setreferring_doctor_speciality_id(Integer referring_doctor_speciality_id) {
		this.referring_doctor_speciality_id = referring_doctor_speciality_id;
	}

	public void setreferring_doctor_referringdoctor(String referring_doctor_referringdoctor) {
		this.referring_doctor_referringdoctor = referring_doctor_referringdoctor;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public void setreferring_doctor_credential(String referring_doctor_credential) {
		this.referring_doctor_credential = referring_doctor_credential;
	}

	public void setreferring_doctor_mailid(String referring_doctor_mailid) {
		this.referring_doctor_mailid = referring_doctor_mailid;
	}

	public void setreferring_doctor_prefix(String referring_doctor_prefix) {
		this.referring_doctor_prefix = referring_doctor_prefix;
	}

	public void setTaxonomyCode(String taxonomyCode) {
		this.taxonomyCode = taxonomyCode;
	}

	public void setDirectEmailAddress(String directEmailAddress) {
		this.directEmailAddress = directEmailAddress;
	}
	
}