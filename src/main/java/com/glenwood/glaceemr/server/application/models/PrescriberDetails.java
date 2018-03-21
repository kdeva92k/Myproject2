package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "prescriber_details")
public class PrescriberDetails implements Serializable{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "prescriber_details_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="id")
	private Integer id;

	@Column(name="doctorid")
	private Integer doctorid;

	@Column(name="spiroot")
	private String spiroot;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}

	public String getSpiroot() {
		return spiroot;
	}

	public void setSpiroot(String spiroot) {
		this.spiroot = spiroot;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSpecialityQualifier() {
		return specialityQualifier;
	}

	public void setSpecialityQualifier(String specialityQualifier) {
		this.specialityQualifier = specialityQualifier;
	}

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public String getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}

	public String getPrescriberPrivateKey() {
		return prescriberPrivateKey;
	}

	public void setPrescriberPrivateKey(String prescriberPrivateKey) {
		this.prescriberPrivateKey = prescriberPrivateKey;
	}

	public String getPrescriberPublicKey() {
		return prescriberPublicKey;
	}

	public void setPrescriberPublicKey(String prescriberPublicKey) {
		this.prescriberPublicKey = prescriberPublicKey;
	}

	public String getStateLicenseNumber() {
		return stateLicenseNumber;
	}

	public void setStateLicenseNumber(String stateLicenseNumber) {
		this.stateLicenseNumber = stateLicenseNumber;
	}

	@Column(name="salutation")
	private String salutation;

	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="first_name")
	private String firstName;

	@Column(name="suffix")
	private String suffix;

	@Column(name="speciality_qualifier")
	private String specialityQualifier;

	@Column(name="speciality_code")
	private String specialityCode;

	@Column(name="key_store")
	private String keyStore;

	@Column(name="prescriber_private_key")
	private String prescriberPrivateKey;

	@Column(name="prescriber_public_key")
	private String prescriberPublicKey;

	@Column(name="state_license_number")
	private String stateLicenseNumber;
	
	public List<LocationDetails> getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(List<LocationDetails> locationDetails) {
		this.locationDetails = locationDetails;
	}

	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="prescriberdetails")
	@JsonManagedReference
	List<LocationDetails> locationDetails;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name="doctorid", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	EmployeeProfile employeeProfile;

	public EmployeeProfile getEmployeeProfile() {
		return employeeProfile;
	}

	public void setEmployeeProfile(EmployeeProfile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}
	
	
}