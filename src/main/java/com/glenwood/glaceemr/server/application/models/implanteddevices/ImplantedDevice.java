package com.glenwood.glaceemr.server.application.models.implanteddevices;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "implanted_device")
public class ImplantedDevice{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="implanted_device_id")
	private Integer implantedDeviceId;
	
	@Column(name="implanted_device_udi")
	private String implantedDeviceUDI;
	
	@Column(name="implanted_device_di")
	private String implantedDeviceDI;
	
	@Column(name="implanted_device_implant_date")
	private Date implantedDeviceImplantDate;
	
	@Column(name="implanted_device_status")//To include deleted data, have used byte instead of boolean
	private Byte implantedDeviceStatus;
	
	@Column(name="implanted_device_reason_deactivation")
	private String implantedDeviceReasonDeactivation;
	
	@Column(name="implanted_device_patient_id")
	private Integer implantedDevicePatientId;
	
	@Column(name="implanted_device_agency_name")
	private String implantedDeviceAgencyName;
	
	@Column(name="implanted_device_lot_number")
	private String implantedDeviceLotNumber;
	
	@Column(name="implanted_device_serial_number")
	private String implantedDeviceSerialNumber;
	
	@Column(name="implanted_device_expiration_date")
	private Date implantedDeviceExpirationDate;
	
	@Column(name="implanted_device_manufacturing_date")
	private Date implantedDeviceManufacturingDate;
	
	@Column(name="implanted_device_mpho_lot_number")
	private String implantedDeviceMPHOLotNumber;
	
	@Column(name="implanted_device_donation_id")
	private String implantedDeviceDonationId;
	
	@Column(name="implanted_device_gmdnptname")
	private String implantedDeviceGMDNPTname;
	
	@Column(name="implanted_device_snomedctname")
	private String implantedDeviceSNOMEDCTName;
	
	@Column(name="implanted_device_brand_name")
	private String implantedDeviceBrandName;
	
	@Column(name="implanted_device_versionmodelnumber")
	private String implantedDeviceVersionModelNumber;
	
	@Column(name="implanted_device_company_name")
	private String implantedDeviceCompanyName;
	
	@Column(name="implanted_device_mrisafetystatus")
	private String implantedDeviceMRISafetyStatus;
	
	@Column(name="implanted_device_labeledcontainsnrl")
	private Boolean implantedDeviceLabeledContainsNRL;
	
	@Column(name="implanted_device_labelednonrl")
	private Boolean implantedDeviceLabeledNoNRL;
	
	@Column(name="implanted_device_snomedid")
	private String implantedDeviceSNOMEDId;
	
	@Column(name="implanted_device_json")
	private String implantedDeviceJSON;
	
	@Column(name="implanted_device_createdby")
	private Integer implantedDeviceCreatedby;
	
	@Column(name="implanted_device_createdon")
	private Date implantedDeviceCreatedon;
	
	@Column(name="implanted_device_modifiedby")
	private Integer implantedDeviceModifiedby;
	
	@Column(name="implanted_device_modifiedon")
	private Date implantedDeviceModifiedon;

	public Integer getImplantedDeviceId() {
		return implantedDeviceId;
	}

	public void setImplantedDeviceId(Integer implantedDeviceId) {
		this.implantedDeviceId = implantedDeviceId;
	}

	public String getImplantedDeviceUDI() {
		return implantedDeviceUDI;
	}

	public void setImplantedDeviceUDI(String implantedDeviceUDI) {
		this.implantedDeviceUDI = implantedDeviceUDI;
	}

	public String getImplantedDeviceDI() {
		return implantedDeviceDI;
	}

	public void setImplantedDeviceDI(String implantedDeviceDI) {
		this.implantedDeviceDI = implantedDeviceDI;
	}

	public Date getImplantedDeviceImplantDate() {
		return implantedDeviceImplantDate;
	}

	public void setImplantedDeviceImplantDate(Date implantedDeviceImplantDate) {
		this.implantedDeviceImplantDate = implantedDeviceImplantDate;
	}

	public Byte getImplantedDeviceStatus() {
		return implantedDeviceStatus;
	}

	public void setImplantedDeviceStatus(Byte implantedDeviceStatus) {
		this.implantedDeviceStatus = implantedDeviceStatus;
	}

	public String getImplantedDeviceReasonDeactivation() {
		return implantedDeviceReasonDeactivation;
	}

	public void setImplantedDeviceReasonDeactivation(
			String implantedDeviceReasonDeactivation) {
		this.implantedDeviceReasonDeactivation = implantedDeviceReasonDeactivation;
	}

	public Integer getImplantedDevicePatientId() {
		return implantedDevicePatientId;
	}

	public void setImplantedDevicePatientId(Integer implantedDevicePatientId) {
		this.implantedDevicePatientId = implantedDevicePatientId;
	}

	public String getImplantedDeviceAgencyName() {
		return implantedDeviceAgencyName;
	}

	public void setImplantedDeviceAgencyName(String implantedDeviceAgencyName) {
		this.implantedDeviceAgencyName = implantedDeviceAgencyName;
	}

	public String getImplantedDeviceLotNumber() {
		return implantedDeviceLotNumber;
	}

	public void setImplantedDeviceLotNumber(String implantedDeviceLotNumber) {
		this.implantedDeviceLotNumber = implantedDeviceLotNumber;
	}

	public String getImplantedDeviceSerialNumber() {
		return implantedDeviceSerialNumber;
	}

	public void setImplantedDeviceSerialNumber(String implantedDeviceSerialNumber) {
		this.implantedDeviceSerialNumber = implantedDeviceSerialNumber;
	}

	public Date getImplantedDeviceExpirationDate() {
		return implantedDeviceExpirationDate;
	}

	public void setImplantedDeviceExpirationDate(Date implantedDeviceExpirationDate) {
		this.implantedDeviceExpirationDate = implantedDeviceExpirationDate;
	}

	public Date getImplantedDeviceManufacturingDate() {
		return implantedDeviceManufacturingDate;
	}

	public void setImplantedDeviceManufacturingDate(
			Date implantedDeviceManufacturingDate) {
		this.implantedDeviceManufacturingDate = implantedDeviceManufacturingDate;
	}

	public String getImplantedDeviceMPHOLotNumber() {
		return implantedDeviceMPHOLotNumber;
	}

	public void setImplantedDeviceMPHOLotNumber(String implantedDeviceMPHOLotNumber) {
		this.implantedDeviceMPHOLotNumber = implantedDeviceMPHOLotNumber;
	}

	public String getImplantedDeviceDonationId() {
		return implantedDeviceDonationId;
	}

	public void setImplantedDeviceDonationId(String implantedDeviceDonationId) {
		this.implantedDeviceDonationId = implantedDeviceDonationId;
	}

	public String getImplantedDeviceGMDNPTname() {
		return implantedDeviceGMDNPTname;
	}

	public void setImplantedDeviceGMDNPTname(String implantedDeviceGMDNPTname) {
		this.implantedDeviceGMDNPTname = implantedDeviceGMDNPTname;
	}

	public String getImplantedDeviceSNOMEDCTName() {
		return implantedDeviceSNOMEDCTName;
	}

	public void setImplantedDeviceSNOMEDCTName(String implantedDeviceSNOMEDCTName) {
		this.implantedDeviceSNOMEDCTName = implantedDeviceSNOMEDCTName;
	}

	public String getImplantedDeviceBrandName() {
		return implantedDeviceBrandName;
	}

	public void setImplantedDeviceBrandName(String implantedDeviceBrandName) {
		this.implantedDeviceBrandName = implantedDeviceBrandName;
	}

	public String getImplantedDeviceVersionModelNumber() {
		return implantedDeviceVersionModelNumber;
	}

	public void setImplantedDeviceVersionModelNumber(
			String implantedDeviceVersionModelNumber) {
		this.implantedDeviceVersionModelNumber = implantedDeviceVersionModelNumber;
	}

	public String getImplantedDeviceCompanyName() {
		return implantedDeviceCompanyName;
	}

	public void setImplantedDeviceCompanyName(String implantedDeviceCompanyName) {
		this.implantedDeviceCompanyName = implantedDeviceCompanyName;
	}

	public String getImplantedDeviceMRISafetyStatus() {
		return implantedDeviceMRISafetyStatus;
	}

	public void setImplantedDeviceMRISafetyStatus(
			String implantedDeviceMRISafetyStatus) {
		this.implantedDeviceMRISafetyStatus = implantedDeviceMRISafetyStatus;
	}

	public Boolean getImplantedDeviceLabeledContainsNRL() {
		return implantedDeviceLabeledContainsNRL;
	}

	public void setImplantedDeviceLabeledContainsNRL(
			Boolean implantedDeviceLabeledContainsNRL) {
		this.implantedDeviceLabeledContainsNRL = implantedDeviceLabeledContainsNRL;
	}

	public Boolean getImplantedDeviceLabeledNoNRL() {
		return implantedDeviceLabeledNoNRL;
	}

	public void setImplantedDeviceLabeledNoNRL(Boolean implantedDeviceLabeledNoNRL) {
		this.implantedDeviceLabeledNoNRL = implantedDeviceLabeledNoNRL;
	}

	public String getImplantedDeviceSNOMEDId() {
		return implantedDeviceSNOMEDId;
	}

	public void setImplantedDeviceSNOMEDId(String implantedDeviceSNOMEDId) {
		this.implantedDeviceSNOMEDId = implantedDeviceSNOMEDId;
	}

	public String getImplantedDeviceJSON() {
		return implantedDeviceJSON;
	}

	public void setImplantedDeviceJSON(String implantedDeviceJSON) {
		this.implantedDeviceJSON = implantedDeviceJSON;
	}

	public Integer getImplantedDeviceCreatedby() {
		return implantedDeviceCreatedby;
	}

	public void setImplantedDeviceCreatedby(Integer implantedDeviceCreatedby) {
		this.implantedDeviceCreatedby = implantedDeviceCreatedby;
	}

	public Date getImplantedDeviceCreatedon() {
		return implantedDeviceCreatedon;
	}

	public void setImplantedDeviceCreatedon(Date implantedDeviceCreatedon) {
		this.implantedDeviceCreatedon = implantedDeviceCreatedon;
	}

	public Integer getImplantedDeviceModifiedby() {
		return implantedDeviceModifiedby;
	}

	public void setImplantedDeviceModifiedby(Integer implantedDeviceModifiedby) {
		this.implantedDeviceModifiedby = implantedDeviceModifiedby;
	}

	public Date getImplantedDeviceModifiedon() {
		return implantedDeviceModifiedon;
	}

	public void setImplantedDeviceModifiedon(Date implantedDeviceModifiedon) {
		this.implantedDeviceModifiedon = implantedDeviceModifiedon;
	}

	
	
			
}