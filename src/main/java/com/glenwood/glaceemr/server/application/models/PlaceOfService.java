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
@Table(name = "place_of_service")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceOfService {

	
	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_of_service_place_of_service_placeid_seq")
	@SequenceGenerator(name = "place_of_service_place_of_service_placeid_seq", sequenceName = "place_of_service_place_of_service_placeid_seq", allocationSize = 1)
	@Column(name="place_of_service_placeid")
	private Integer placeOfServicePlaceid;

	@Column(name="place_of_service_place_name")
	private String placeOfServicePlaceName;

	@Column(name="place_of_service_address")
	private String placeOfServiceAddress;

	@Column(name="place_of_service_city")
	private String placeOfServiceCity;

	@Column(name="place_of_service_state")
	private String placeOfServiceState;

	@Column(name="place_of_service_zip")
	private String placeOfServiceZip;

	@Column(name="place_of_service_phone")
	private String placeOfServicePhone;

	@Column(name="place_of_service_fax")
	private String placeOfServiceFax;

	@Column(name="place_of_service_bcbs_code")
	private String placeOfServiceBcbsCode;

	@Column(name="place_of_service_medicare_code")
	private String placeOfServiceMedicareCode;

	@Column(name="place_of_service_is_active")
	private Boolean placeOfServiceIsActive;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="place_of_service_isdme")
	private Boolean placeOfServiceIsdme;

	public Integer getPlaceOfServicePlaceid() {
		return placeOfServicePlaceid;
	}

	public void setPlaceOfServicePlaceid(Integer placeOfServicePlaceid) {
		this.placeOfServicePlaceid = placeOfServicePlaceid;
	}

	public String getPlaceOfServicePlaceName() {
		return placeOfServicePlaceName;
	}

	public void setPlaceOfServicePlaceName(String placeOfServicePlaceName) {
		this.placeOfServicePlaceName = placeOfServicePlaceName;
	}

	public String getPlaceOfServiceAddress() {
		return placeOfServiceAddress;
	}

	public void setPlaceOfServiceAddress(String placeOfServiceAddress) {
		this.placeOfServiceAddress = placeOfServiceAddress;
	}

	public String getPlaceOfServiceCity() {
		return placeOfServiceCity;
	}

	public void setPlaceOfServiceCity(String placeOfServiceCity) {
		this.placeOfServiceCity = placeOfServiceCity;
	}

	public String getPlaceOfServiceState() {
		return placeOfServiceState;
	}

	public void setPlaceOfServiceState(String placeOfServiceState) {
		this.placeOfServiceState = placeOfServiceState;
	}

	public String getPlaceOfServiceZip() {
		return placeOfServiceZip;
	}

	public void setPlaceOfServiceZip(String placeOfServiceZip) {
		this.placeOfServiceZip = placeOfServiceZip;
	}

	public String getPlaceOfServicePhone() {
		return placeOfServicePhone;
	}

	public void setPlaceOfServicePhone(String placeOfServicePhone) {
		this.placeOfServicePhone = placeOfServicePhone;
	}

	public String getPlaceOfServiceFax() {
		return placeOfServiceFax;
	}

	public void setPlaceOfServiceFax(String placeOfServiceFax) {
		this.placeOfServiceFax = placeOfServiceFax;
	}

	public String getPlaceOfServiceBcbsCode() {
		return placeOfServiceBcbsCode;
	}

	public void setPlaceOfServiceBcbsCode(String placeOfServiceBcbsCode) {
		this.placeOfServiceBcbsCode = placeOfServiceBcbsCode;
	}

	public String getPlaceOfServiceMedicareCode() {
		return placeOfServiceMedicareCode;
	}

	public void setPlaceOfServiceMedicareCode(String placeOfServiceMedicareCode) {
		this.placeOfServiceMedicareCode = placeOfServiceMedicareCode;
	}

	public Boolean getPlaceOfServiceIsActive() {
		return placeOfServiceIsActive;
	}

	public void setPlaceOfServiceIsActive(Boolean placeOfServiceIsActive) {
		this.placeOfServiceIsActive = placeOfServiceIsActive;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Boolean getPlaceOfServiceIsdme() {
		return placeOfServiceIsdme;
	}

	public void setPlaceOfServiceIsdme(Boolean placeOfServiceIsdme) {
		this.placeOfServiceIsdme = placeOfServiceIsdme;
	}
	
}