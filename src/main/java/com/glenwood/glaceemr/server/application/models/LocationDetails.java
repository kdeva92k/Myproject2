package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "location_details")
public class LocationDetails implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="location_details_location_id_seq")
//	@SequenceGenerator(name =" location_details_location_id_seq", sequenceName="location_details_location_id_seq", allocationSize=1)
	@Id
	@Column(name="location_id")
	private Integer locationId;

	@Column(name="prescriber_id")
	private Integer prescriberId;

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getPrescriberId() {
		return prescriberId;
	}

	public void setPrescriberId(Integer prescriberId) {
		this.prescriberId = prescriberId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getDea() {
		return dea;
	}

	public void setDea(String dea) {
		this.dea = dea;
	}

	public String getSpiLocationid() {
		return spiLocationid;
	}

	public void setSpiLocationid(String spiLocationid) {
		this.spiLocationid = spiLocationid;
	}

	public String getClinicname() {
		return clinicname;
	}

	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrimaryphone() {
		return primaryphone;
	}

	public void setPrimaryphone(String primaryphone) {
		this.primaryphone = primaryphone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getBeeperphone() {
		return beeperphone;
	}

	public void setBeeperphone(String beeperphone) {
		this.beeperphone = beeperphone;
	}

	public String getCellularphone() {
		return cellularphone;
	}

	public void setCellularphone(String cellularphone) {
		this.cellularphone = cellularphone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getNightphone() {
		return nightphone;
	}

	public void setNightphone(String nightphone) {
		this.nightphone = nightphone;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getPhonealt6() {
		return phonealt6;
	}

	public void setPhonealt6(String phonealt6) {
		this.phonealt6 = phonealt6;
	}

	public Integer getServicelevel() {
		return servicelevel;
	}

	public void setServicelevel(Integer servicelevel) {
		this.servicelevel = servicelevel;
	}

	public Timestamp getActivestartdate() {
		return activestartdate;
	}

	public void setActivestartdate(Timestamp activestartdate) {
		this.activestartdate = activestartdate;
	}

	public Timestamp getActiveenddate() {
		return activeenddate;
	}

	public void setActiveenddate(Timestamp activeenddate) {
		this.activeenddate = activeenddate;
	}

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	@Column(name="location_name")
	private String locationName;

	@Column(name="dea")
	private String dea;

	@Column(name="spi_locationid")
	private String spiLocationid;

	@Column(name="clinicname")
	private String clinicname;

	@Column(name="addressline1")
	private String addressline1;

	@Column(name="addressline2")
	private String addressline2;

	@Column(name="city")
	private String city;

	@Column(name="state")
	private String state;

	@Column(name="zip")
	private String zip;

	@Column(name="email")
	private String email;

	@Column(name="primaryphone")
	private String primaryphone;

	@Column(name="fax")
	private String fax;

	@Column(name="beeperphone")
	private String beeperphone;

	@Column(name="cellularphone")
	private String cellularphone;

	@Column(name="homephone")
	private String homephone;

	@Column(name="nightphone")
	private String nightphone;

	@Column(name="workphone")
	private String workphone;

	@Column(name="phonealt6")
	private String phonealt6;

	@Column(name="servicelevel")
	private Integer servicelevel;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="activestartdate")
	private Timestamp activestartdate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="activeenddate")
	private Timestamp activeenddate;

	@Column(name="npi")
	private String npi;
	
	@Column(name="pos_id")
	private Integer posId;
	
	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="prescriber_id",referencedColumnName="doctorid",insertable=false,updatable=false)
	@JsonBackReference
	PrescriberDetails prescriberdetails;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="prescriber_id",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile empProfileLoc;

	public EmployeeProfile getEmpProfileLoc() {
		return empProfileLoc;
	}

	public void setEmpProfileLoc(EmployeeProfile empProfileLoc) {
		this.empProfileLoc = empProfileLoc;
	}

	public PrescriberDetails getPrescriberdetails() {
		return prescriberdetails;
	}

	public void setPrescriberdetails(PrescriberDetails prescriberdetails) {
		this.prescriberdetails = prescriberdetails;
	}
}