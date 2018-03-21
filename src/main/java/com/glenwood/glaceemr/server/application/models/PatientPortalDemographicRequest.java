package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;


@Entity
@Table(name = "demographic_request")
public class PatientPortalDemographicRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="public.demographic_request_demographic_request_id_seq")
	@SequenceGenerator(name ="public.demographic_request_demographic_request_id_seq", sequenceName="public.demographic_request_demographic_request_id_seq", allocationSize=1)
	@Column(name="demographic_request_id")
	private Integer demographic_request_id;

	@Column(name="demographic_request_first_name")
	private String demographic_request_first_name;

	@Column(name="demographic_request_last_name")
	private String demographic_request_last_name;

	@Column(name="demographic_request_mid_initial")
	private String demographic_request_mid_initial;

	@Column(name="demographic_request_work_phone")
	private String demographic_request_work_phone;

	@Column(name="demographic_request_date_of_birth")
	private String demographic_request_date_of_birth;

	@Column(name="demographic_request_gender")
	private Integer demographic_request_gender;

	@Column(name="demographic_request_marital_status")
	private Integer demographic_request_marital_status;

	@Column(name="demographic_request_home_phone")
	private String demographic_request_home_phone;

	@Column(name="demographic_request_address")
	private String demographic_request_address;

	@Column(name="demographic_request_city")
	private String demographic_request_city;

	@Column(name="demographic_request_state")
	private String demographic_request_state;

	@Column(name="demographic_request_zip")
	private String demographic_request_zip;

	@Column(name="demographic_request_email")
	private String demographic_request_email;

	@Column(name="demographic_request_employer")
	private String demographic_request_employer;

	@Column(name="demographic_request_cell_no")
	private String demographic_request_cell_no;

	@Column(name="demographic_request_driver_license")
	private String demographic_request_driver_license;

	@Column(name="demographic_request_refer_physician")
	private String demographic_request_refer_physician;

	@Column(name="demographic_request_how_intro")
	private Integer demographic_request_how_intro;

	@Column(name="demographic_request_contact_person")
	private String demographic_request_contact_person;

	@Column(name="demographic_request_contact_person_relation")
	private Integer demographic_request_contact_person_relation;

	@Column(name="demographic_request_contact_person_address")
	private String demographic_request_contact_person_address;

	@Column(name="demographic_request_contact_person_city")
	private String demographic_request_contact_person_city;

	@Column(name="demographic_request_contact_person_state")
	private String demographic_request_contact_person_state;

	@Column(name="demographic_request_contact_person_zip")
	private String demographic_request_contact_person_zip;

	@Column(name="demographic_request_primary_ins_company")
	private String demographic_request_primary_ins_company;

	@Column(name="demographic_request_primary_ins_company_id")
	private String demographic_request_primary_ins_company_id;

	@Column(name="demographic_request_primary_ins_patient_id")
	private String demographic_request_primary_ins_patient_id;

	@Column(name="demographic_request_primary_ins_valid_from")
	private String demographic_request_primary_ins_valid_from;

	@Column(name="demographic_request_primary_ins_valid_to")
	private String demographic_request_primary_ins_valid_to;

	@Column(name="demographic_request_primary_ins_group_number")
	private String demographic_request_primary_ins_group_number;

	@Column(name="demographic_request_primary_ins_group_name")
	private String demographic_request_primary_ins_group_name;

	@Column(name="demographic_request_primary_ins_relation")
	private Integer demographic_request_primary_ins_relation;

	@Column(name="demographic_request_primary_ins_subscriber_name")
	private String demographic_request_primary_ins_subscriber_name;

	@Column(name="demographic_request_secondary_ins_company")
	private String demographic_request_secondary_ins_company;

	@Column(name="demographic_request_secondary_ins_company_id")
	private String demographic_request_secondary_ins_company_id;

	@Column(name="demographic_request_secondary_ins_patient_id")
	private String demographic_request_secondary_ins_patient_id;

	@Column(name="demographic_request_secondary_ins_valid_from")
	private String demographic_request_secondary_ins_valid_from;

	@Column(name="demographic_request_secondary_ins_valid_to")
	private String demographic_request_secondary_ins_valid_to;

	@Column(name="demographic_request_secondary_ins_group_number")
	private String demographic_request_secondary_ins_group_number;

	@Column(name="demographic_request_secondary_ins_group_name")
	private String demographic_request_secondary_ins_group_name;

	@Column(name="demographic_request_secondary_ins_relation")
	private Integer demographic_request_secondary_ins_relation;

	@Column(name="demographic_request_secondary_ins_subscriber_name")
	private String demographic_request_secondary_ins_subscriber_name;

	@Column(name="demographic_request_user_name")
	private String demographic_request_user_name;

	@Column(name="demographic_request_patient_id")
	private Integer demographic_request_patient_id;

	@Column(name="demographic_request_email_reminder")
	private Boolean demographic_request_email_reminder;

	@Column(name="demographic_request_phone_reminder")
	private Boolean demographic_request_phone_reminder;

	@Column(name="demographic_request_primary_ins_copay")
	private Double demographic_request_primary_ins_copay;

	public Integer getdemographic_request_id() {
		return demographic_request_id;
	}

	public void setdemographic_request_id(Integer demographic_request_id) {
		this.demographic_request_id = demographic_request_id;
	}

	public String getdemographic_request_first_name() {
		return demographic_request_first_name;
	}

	public void setdemographic_request_first_name(String demographic_request_first_name) {
		this.demographic_request_first_name = demographic_request_first_name;
	}

	public String getdemographic_request_last_name() {
		return demographic_request_last_name;
	}

	public void setdemographic_request_last_name(String demographic_request_last_name) {
		this.demographic_request_last_name = demographic_request_last_name;
	}

	public String getdemographic_request_mid_initial() {
		return demographic_request_mid_initial;
	}

	public void setdemographic_request_mid_initial(String demographic_request_mid_initial) {
		this.demographic_request_mid_initial = demographic_request_mid_initial;
	}

	public String getdemographic_request_work_phone() {
		return demographic_request_work_phone;
	}

	public void setdemographic_request_work_phone(String demographic_request_work_phone) {
		this.demographic_request_work_phone = demographic_request_work_phone;
	}

	public String getdemographic_request_date_of_birth() {
		return demographic_request_date_of_birth;
	}

	public void setdemographic_request_date_of_birth(String demographic_request_date_of_birth) {
		this.demographic_request_date_of_birth = demographic_request_date_of_birth;
	}

	public Integer getdemographic_request_gender() {
		return demographic_request_gender;
	}

	public void setdemographic_request_gender(Integer demographic_request_gender) {
		this.demographic_request_gender = demographic_request_gender;
	}

	public Integer getdemographic_request_marital_status() {
		return demographic_request_marital_status;
	}

	public void setdemographic_request_marital_status(Integer demographic_request_marital_status) {
		this.demographic_request_marital_status = demographic_request_marital_status;
	}

	public String getdemographic_request_home_phone() {
		return demographic_request_home_phone;
	}

	public void setdemographic_request_home_phone(String demographic_request_home_phone) {
		this.demographic_request_home_phone = demographic_request_home_phone;
	}

	public String getdemographic_request_address() {
		return demographic_request_address;
	}

	public void setdemographic_request_address(String demographic_request_address) {
		this.demographic_request_address = demographic_request_address;
	}

	public String getdemographic_request_city() {
		return demographic_request_city;
	}

	public void setdemographic_request_city(String demographic_request_city) {
		this.demographic_request_city = demographic_request_city;
	}

	public String getdemographic_request_state() {
		return demographic_request_state;
	}

	public void setdemographic_request_state(String demographic_request_state) {
		this.demographic_request_state = demographic_request_state;
	}

	public String getdemographic_request_zip() {
		return demographic_request_zip;
	}

	public void setdemographic_request_zip(String demographic_request_zip) {
		this.demographic_request_zip = demographic_request_zip;
	}

	public String getdemographic_request_email() {
		return demographic_request_email;
	}

	public void setdemographic_request_email(String demographic_request_email) {
		this.demographic_request_email = demographic_request_email;
	}

	public String getdemographic_request_employer() {
		return demographic_request_employer;
	}

	public void setdemographic_request_employer(String demographic_request_employer) {
		this.demographic_request_employer = demographic_request_employer;
	}

	public String getdemographic_request_cell_no() {
		return demographic_request_cell_no;
	}

	public void setdemographic_request_cell_no(String demographic_request_cell_no) {
		this.demographic_request_cell_no = demographic_request_cell_no;
	}

	public String getdemographic_request_driver_license() {
		return demographic_request_driver_license;
	}

	public void setdemographic_request_driver_license(String demographic_request_driver_license) {
		this.demographic_request_driver_license = demographic_request_driver_license;
	}

	public String getdemographic_request_refer_physician() {
		return demographic_request_refer_physician;
	}

	public void setdemographic_request_refer_physician(String demographic_request_refer_physician) {
		this.demographic_request_refer_physician = demographic_request_refer_physician;
	}

	public Integer getdemographic_request_how_intro() {
		return demographic_request_how_intro;
	}

	public void setdemographic_request_how_intro(Integer demographic_request_how_intro) {
		this.demographic_request_how_intro = demographic_request_how_intro;
	}

	public String getdemographic_request_contact_person() {
		return demographic_request_contact_person;
	}

	public void setdemographic_request_contact_person(String demographic_request_contact_person) {
		this.demographic_request_contact_person = demographic_request_contact_person;
	}

	public Integer getdemographic_request_contact_person_relation() {
		return demographic_request_contact_person_relation;
	}

	public void setdemographic_request_contact_person_relation(Integer demographic_request_contact_person_relation) {
		this.demographic_request_contact_person_relation = demographic_request_contact_person_relation;
	}

	public String getdemographic_request_contact_person_address() {
		return demographic_request_contact_person_address;
	}

	public void setdemographic_request_contact_person_address(String demographic_request_contact_person_address) {
		this.demographic_request_contact_person_address = demographic_request_contact_person_address;
	}

	public String getdemographic_request_contact_person_city() {
		return demographic_request_contact_person_city;
	}

	public void setdemographic_request_contact_person_city(String demographic_request_contact_person_city) {
		this.demographic_request_contact_person_city = demographic_request_contact_person_city;
	}

	public String getdemographic_request_contact_person_state() {
		return demographic_request_contact_person_state;
	}

	public void setdemographic_request_contact_person_state(String demographic_request_contact_person_state) {
		this.demographic_request_contact_person_state = demographic_request_contact_person_state;
	}

	public String getdemographic_request_contact_person_zip() {
		return demographic_request_contact_person_zip;
	}

	public void setdemographic_request_contact_person_zip(String demographic_request_contact_person_zip) {
		this.demographic_request_contact_person_zip = demographic_request_contact_person_zip;
	}

	public String getdemographic_request_primary_ins_company() {
		return demographic_request_primary_ins_company;
	}

	public void setdemographic_request_primary_ins_company(String demographic_request_primary_ins_company) {
		this.demographic_request_primary_ins_company = demographic_request_primary_ins_company;
	}

	public String getdemographic_request_primary_ins_company_id() {
		return demographic_request_primary_ins_company_id;
	}

	public void setdemographic_request_primary_ins_company_id(String demographic_request_primary_ins_company_id) {
		this.demographic_request_primary_ins_company_id = demographic_request_primary_ins_company_id;
	}

	public String getdemographic_request_primary_ins_patient_id() {
		return demographic_request_primary_ins_patient_id;
	}

	public void setdemographic_request_primary_ins_patient_id(String demographic_request_primary_ins_patient_id) {
		this.demographic_request_primary_ins_patient_id = demographic_request_primary_ins_patient_id;
	}

	public String getdemographic_request_primary_ins_valid_from() {
		return demographic_request_primary_ins_valid_from;
	}

	public void setdemographic_request_primary_ins_valid_from(String demographic_request_primary_ins_valid_from) {
		this.demographic_request_primary_ins_valid_from = demographic_request_primary_ins_valid_from;
	}

	public String getdemographic_request_primary_ins_valid_to() {
		return demographic_request_primary_ins_valid_to;
	}

	public void setdemographic_request_primary_ins_valid_to(String demographic_request_primary_ins_valid_to) {
		this.demographic_request_primary_ins_valid_to = demographic_request_primary_ins_valid_to;
	}

	public String getdemographic_request_primary_ins_group_number() {
		return demographic_request_primary_ins_group_number;
	}

	public void setdemographic_request_primary_ins_group_number(String demographic_request_primary_ins_group_number) {
		this.demographic_request_primary_ins_group_number = demographic_request_primary_ins_group_number;
	}

	public String getdemographic_request_primary_ins_group_name() {
		return demographic_request_primary_ins_group_name;
	}

	public void setdemographic_request_primary_ins_group_name(String demographic_request_primary_ins_group_name) {
		this.demographic_request_primary_ins_group_name = demographic_request_primary_ins_group_name;
	}

	public Integer getdemographic_request_primary_ins_relation() {
		return demographic_request_primary_ins_relation;
	}

	public void setdemographic_request_primary_ins_relation(Integer demographic_request_primary_ins_relation) {
		this.demographic_request_primary_ins_relation = demographic_request_primary_ins_relation;
	}

	public String getdemographic_request_primary_ins_subscriber_name() {
		return demographic_request_primary_ins_subscriber_name;
	}

	public void setdemographic_request_primary_ins_subscriber_name(String demographic_request_primary_ins_subscriber_name) {
		this.demographic_request_primary_ins_subscriber_name = demographic_request_primary_ins_subscriber_name;
	}

	public String getdemographic_request_secondary_ins_company() {
		return demographic_request_secondary_ins_company;
	}

	public void setdemographic_request_secondary_ins_company(String demographic_request_secondary_ins_company) {
		this.demographic_request_secondary_ins_company = demographic_request_secondary_ins_company;
	}

	public String getdemographic_request_secondary_ins_company_id() {
		return demographic_request_secondary_ins_company_id;
	}

	public void setdemographic_request_secondary_ins_company_id(String demographic_request_secondary_ins_company_id) {
		this.demographic_request_secondary_ins_company_id = demographic_request_secondary_ins_company_id;
	}

	public String getdemographic_request_secondary_ins_patient_id() {
		return demographic_request_secondary_ins_patient_id;
	}

	public void setdemographic_request_secondary_ins_patient_id(String demographic_request_secondary_ins_patient_id) {
		this.demographic_request_secondary_ins_patient_id = demographic_request_secondary_ins_patient_id;
	}

	public String getdemographic_request_secondary_ins_valid_from() {
		return demographic_request_secondary_ins_valid_from;
	}

	public void setdemographic_request_secondary_ins_valid_from(String demographic_request_secondary_ins_valid_from) {
		this.demographic_request_secondary_ins_valid_from = demographic_request_secondary_ins_valid_from;
	}

	public String getdemographic_request_secondary_ins_valid_to() {
		return demographic_request_secondary_ins_valid_to;
	}

	public void setdemographic_request_secondary_ins_valid_to(String demographic_request_secondary_ins_valid_to) {
		this.demographic_request_secondary_ins_valid_to = demographic_request_secondary_ins_valid_to;
	}

	public String getdemographic_request_secondary_ins_group_number() {
		return demographic_request_secondary_ins_group_number;
	}

	public void setdemographic_request_secondary_ins_group_number(String demographic_request_secondary_ins_group_number) {
		this.demographic_request_secondary_ins_group_number = demographic_request_secondary_ins_group_number;
	}

	public String getdemographic_request_secondary_ins_group_name() {
		return demographic_request_secondary_ins_group_name;
	}

	public void setdemographic_request_secondary_ins_group_name(String demographic_request_secondary_ins_group_name) {
		this.demographic_request_secondary_ins_group_name = demographic_request_secondary_ins_group_name;
	}

	public Integer getdemographic_request_secondary_ins_relation() {
		return demographic_request_secondary_ins_relation;
	}

	public void setdemographic_request_secondary_ins_relation(Integer demographic_request_secondary_ins_relation) {
		this.demographic_request_secondary_ins_relation = demographic_request_secondary_ins_relation;
	}

	public String getdemographic_request_secondary_ins_subscriber_name() {
		return demographic_request_secondary_ins_subscriber_name;
	}

	public void setdemographic_request_secondary_ins_subscriber_name(String demographic_request_secondary_ins_subscriber_name) {
		this.demographic_request_secondary_ins_subscriber_name = demographic_request_secondary_ins_subscriber_name;
	}

	public String getdemographic_request_user_name() {
		return demographic_request_user_name;
	}

	public void setdemographic_request_user_name(String demographic_request_user_name) {
		this.demographic_request_user_name = demographic_request_user_name;
	}

	public Integer getdemographic_request_patient_id() {
		return demographic_request_patient_id;
	}

	public void setdemographic_request_patient_id(Integer demographic_request_patient_id) {
		this.demographic_request_patient_id = demographic_request_patient_id;
	}

	public Boolean getdemographic_request_email_reminder() {
		return demographic_request_email_reminder;
	}

	public void setdemographic_request_email_reminder(Boolean demographic_request_email_reminder) {
		this.demographic_request_email_reminder = demographic_request_email_reminder;
	}

	public Boolean getdemographic_request_phone_reminder() {
		return demographic_request_phone_reminder;
	}

	public void setdemographic_request_phone_reminder(Boolean demographic_request_phone_reminder) {
		this.demographic_request_phone_reminder = demographic_request_phone_reminder;
	}

	public Double getdemographic_request_primary_ins_copay() {
		return demographic_request_primary_ins_copay;
	}

	public void setdemographic_request_primary_ins_copay(Double demographic_request_primary_ins_copay) {
		this.demographic_request_primary_ins_copay = demographic_request_primary_ins_copay;
	}
	
}