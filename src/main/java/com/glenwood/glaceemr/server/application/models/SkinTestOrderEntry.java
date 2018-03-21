package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.utils.JsonDateSerializer;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_order_entry")
public class SkinTestOrderEntry {

	@Id
	@Column(name="skin_test_order_entry_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_order_entry_id")
	@SequenceGenerator(name="skin_test_order_entry_id",sequenceName="skin_test_order_entry_id",allocationSize=1)
	private Integer skinTestOrderEntryId;

	@Column(name="skin_test_order_entry_skin_test_order_id")
	private Integer skinTestOrderEntrySkinTestOrderId;

	@Column(name="skin_test_order_entry_type_of_test")
	private String skinTestOrderEntryTypeOfTest;

	@Column(name="skin_test_order_entry_concentration")
	private String skinTestOrderEntryConcentration;

	@Column(name="skin_test_order_entry_no_of_units")
	private Integer skinTestOrderEntryNoOfUnits;

	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name="skin_test_order_entry_test_date")
	private Date skinTestOrderEntryTestDate;

	@Column(name="skin_test_order_entry_test_performed_technician")
	private Integer skinTestOrderEntryTestPerformedTechnician;

	@Column(name="skin_test_order_entry_diluent_whealvalue")
	private String skinTestOrderEntryDiluentWhealvalue;

	@Column(name="skin_test_order_entry_diluent_flarevalue")
	private String skinTestOrderEntryDiluentFlarevalue;

	@Column(name="skin_test_order_entry_diluent_grade")
	private String skinTestOrderEntryDiluentGrade;
	
	@Column(name="skin_test_order_entry_diluent_erythema")
	private Boolean skinTestOrderEntryDiluentErythema;

	@Column(name="skin_test_order_entry_diluent_pseudopodia")
	private Boolean skinTestOrderEntryDiluentPseudopodia;

	@Column(name="skin_test_order_entry_histamine_whealvalue")
	private String skinTestOrderEntryHistamineWhealvalue;

	@Column(name="skin_test_order_entry_histamine_flarevalue")
	private String skinTestOrderEntryHistamineFlarevalue;
	
	@Column(name="skin_test_order_entry_histamine_grade")
	private String skinTestOrderEntryHistamineGrade;

	@Column(name="skin_test_order_entry_histamine_erythema")
	private Boolean skinTestOrderEntryHistamineErythema;

	@Column(name="skin_test_order_entry_histamine_pseudopodia")
	private Boolean skinTestOrderEntryHistaminePseudopodia;
	
	@Column(name="skin_test_order_entry_created_by")
	private Integer skinTestOrderEntryCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_entry_created_on")
	private Timestamp skinTestOrderEntryCreatedOn;

	@Column(name="skin_test_order_entry_modified_by")
	private Integer skinTestOrderEntryModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_entry_modified_on")
	private Timestamp skinTestOrderEntryModifiedOn;
	
	@Column(name="skin_test_order_entry_encounter_id")
	private Integer skinTestOrderEntryEncounterId;

	@Column(name="skin_test_order_entry_ordering_physician")
	private Integer skinTestOrderEntryOrderingPhysician;
	
	@Column(name="skin_test_order_entry_ordering_location")
	private Integer skinTestOrderEntryOrderingLocation;
	
	@Column(name="skin_test_order_entry_billed_status")
	private Integer skinTestOrderEntryBilledStatus; // 0-Not billed,1-Billed Modified,2-Billed
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="skin_test_order_entry_skin_test_order_id",referencedColumnName="skin_test_order_id",insertable=false,updatable=false)
	@JsonBackReference
	SkinTestOrder skinTestOrder;
		
	@OneToMany(mappedBy="skinTestOrderEntry")
	@JsonManagedReference
	List<SkinTestOrderDetails> skinTestOrderDetails;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_entry_ordering_physician",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	private EmployeeProfile orderingPhysician;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_entry_ordering_location",referencedColumnName="pos_table_relation_id",insertable=false,updatable=false)
	@JsonManagedReference
	PosTable orderingLocation;

	public Integer getSkinTestOrderEntryId() {
		return skinTestOrderEntryId;
	}

	public void setSkinTestOrderEntryId(Integer skinTestOrderEntryId) {
		this.skinTestOrderEntryId = skinTestOrderEntryId;
	}

	public Integer getSkinTestOrderEntrySkinTestOrderId() {
		return skinTestOrderEntrySkinTestOrderId;
	}

	public void setSkinTestOrderEntrySkinTestOrderId(
			Integer skinTestOrderEntrySkinTestOrderId) {
		this.skinTestOrderEntrySkinTestOrderId = skinTestOrderEntrySkinTestOrderId;
	}

	public String getSkinTestOrderEntryTypeOfTest() {
		return skinTestOrderEntryTypeOfTest;
	}

	public void setSkinTestOrderEntryTypeOfTest(String skinTestOrderEntryTypeOfTest) {
		this.skinTestOrderEntryTypeOfTest = skinTestOrderEntryTypeOfTest;
	}

	public String getSkinTestOrderEntryConcentration() {
		return skinTestOrderEntryConcentration;
	}

	public void setSkinTestOrderEntryConcentration(
			String skinTestOrderEntryConcentration) {
		this.skinTestOrderEntryConcentration = skinTestOrderEntryConcentration;
	}

	public Integer getSkinTestOrderEntryNoOfUnits() {
		return skinTestOrderEntryNoOfUnits;
	}

	public void setSkinTestOrderEntryNoOfUnits(Integer skinTestOrderEntryNoOfUnits) {
		this.skinTestOrderEntryNoOfUnits = skinTestOrderEntryNoOfUnits;
	}

	public Date getSkinTestOrderEntryTestDate() {
		return skinTestOrderEntryTestDate;
	}

	public void setSkinTestOrderEntryTestDate(Date skinTestOrderEntryTestDate) {
		this.skinTestOrderEntryTestDate = skinTestOrderEntryTestDate;
	}

	public Integer getSkinTestOrderEntryTestPerformedTechnician() {
		return skinTestOrderEntryTestPerformedTechnician;
	}

	public void setSkinTestOrderEntryTestPerformedTechnician(
			Integer skinTestOrderEntryTestPerformedTechnician) {
		this.skinTestOrderEntryTestPerformedTechnician = skinTestOrderEntryTestPerformedTechnician;
	}

	public String getSkinTestOrderEntryDiluentWhealvalue() {
		return skinTestOrderEntryDiluentWhealvalue;
	}

	public void setSkinTestOrderEntryDiluentWhealvalue(
			String skinTestOrderEntryDiluentWhealvalue) {
		this.skinTestOrderEntryDiluentWhealvalue = skinTestOrderEntryDiluentWhealvalue;
	}

	public String getSkinTestOrderEntryDiluentFlarevalue() {
		return skinTestOrderEntryDiluentFlarevalue;
	}

	public void setSkinTestOrderEntryDiluentFlarevalue(
			String skinTestOrderEntryDiluentFlarevalue) {
		this.skinTestOrderEntryDiluentFlarevalue = skinTestOrderEntryDiluentFlarevalue;
	}

	public String getSkinTestOrderEntryDiluentGrade() {
		return skinTestOrderEntryDiluentGrade;
	}

	public void setSkinTestOrderEntryDiluentGrade(
			String skinTestOrderEntryDiluentGrade) {
		this.skinTestOrderEntryDiluentGrade = skinTestOrderEntryDiluentGrade;
	}

	public Boolean getSkinTestOrderEntryDiluentErythema() {
		return skinTestOrderEntryDiluentErythema;
	}

	public void setSkinTestOrderEntryDiluentErythema(
			Boolean skinTestOrderEntryDiluentErythema) {
		this.skinTestOrderEntryDiluentErythema = skinTestOrderEntryDiluentErythema;
	}

	public Boolean getSkinTestOrderEntryDiluentPseudopodia() {
		return skinTestOrderEntryDiluentPseudopodia;
	}

	public void setSkinTestOrderEntryDiluentPseudopodia(
			Boolean skinTestOrderEntryDiluentPseudopodia) {
		this.skinTestOrderEntryDiluentPseudopodia = skinTestOrderEntryDiluentPseudopodia;
	}

	public String getSkinTestOrderEntryHistamineWhealvalue() {
		return skinTestOrderEntryHistamineWhealvalue;
	}

	public void setSkinTestOrderEntryHistamineWhealvalue(
			String skinTestOrderEntryHistamineWhealvalue) {
		this.skinTestOrderEntryHistamineWhealvalue = skinTestOrderEntryHistamineWhealvalue;
	}

	public String getSkinTestOrderEntryHistamineFlarevalue() {
		return skinTestOrderEntryHistamineFlarevalue;
	}

	public void setSkinTestOrderEntryHistamineFlarevalue(
			String skinTestOrderEntryHistamineFlarevalue) {
		this.skinTestOrderEntryHistamineFlarevalue = skinTestOrderEntryHistamineFlarevalue;
	}

	public String getSkinTestOrderEntryHistamineGrade() {
		return skinTestOrderEntryHistamineGrade;
	}

	public void setSkinTestOrderEntryHistamineGrade(
			String skinTestOrderEntryHistamineGrade) {
		this.skinTestOrderEntryHistamineGrade = skinTestOrderEntryHistamineGrade;
	}

	public Boolean getSkinTestOrderEntryHistamineErythema() {
		return skinTestOrderEntryHistamineErythema;
	}

	public void setSkinTestOrderEntryHistamineErythema(
			Boolean skinTestOrderEntryHistamineErythema) {
		this.skinTestOrderEntryHistamineErythema = skinTestOrderEntryHistamineErythema;
	}

	public Boolean getSkinTestOrderEntryHistaminePseudopodia() {
		return skinTestOrderEntryHistaminePseudopodia;
	}

	public void setSkinTestOrderEntryHistaminePseudopodia(
			Boolean skinTestOrderEntryHistaminePseudopodia) {
		this.skinTestOrderEntryHistaminePseudopodia = skinTestOrderEntryHistaminePseudopodia;
	}

	public Integer getSkinTestOrderEntryCreatedBy() {
		return skinTestOrderEntryCreatedBy;
	}

	public void setSkinTestOrderEntryCreatedBy(Integer skinTestOrderEntryCreatedBy) {
		this.skinTestOrderEntryCreatedBy = skinTestOrderEntryCreatedBy;
	}

	public Timestamp getSkinTestOrderEntryCreatedOn() {
		return skinTestOrderEntryCreatedOn;
	}

	public void setSkinTestOrderEntryCreatedOn(Timestamp skinTestOrderEntryCreatedOn) {
		this.skinTestOrderEntryCreatedOn = skinTestOrderEntryCreatedOn;
	}

	public Integer getSkinTestOrderEntryModifiedBy() {
		return skinTestOrderEntryModifiedBy;
	}

	public void setSkinTestOrderEntryModifiedBy(Integer skinTestOrderEntryModifiedBy) {
		this.skinTestOrderEntryModifiedBy = skinTestOrderEntryModifiedBy;
	}

	public Timestamp getSkinTestOrderEntryModifiedOn() {
		return skinTestOrderEntryModifiedOn;
	}

	public void setSkinTestOrderEntryModifiedOn(
			Timestamp skinTestOrderEntryModifiedOn) {
		this.skinTestOrderEntryModifiedOn = skinTestOrderEntryModifiedOn;
	}

	public Integer getSkinTestOrderEntryEncounterId() {
		return skinTestOrderEntryEncounterId;
	}

	public void setSkinTestOrderEntryEncounterId(
			Integer skinTestOrderEntryEncounterId) {
		this.skinTestOrderEntryEncounterId = skinTestOrderEntryEncounterId;
	}

	public Integer getSkinTestOrderEntryOrderingPhysician() {
		return skinTestOrderEntryOrderingPhysician;
	}

	public void setSkinTestOrderEntryOrderingPhysician(
			Integer skinTestOrderEntryOrderingPhysician) {
		this.skinTestOrderEntryOrderingPhysician = skinTestOrderEntryOrderingPhysician;
	}

	public Integer getSkinTestOrderEntryOrderingLocation() {
		return skinTestOrderEntryOrderingLocation;
	}

	public void setSkinTestOrderEntryOrderingLocation(
			Integer skinTestOrderEntryOrderingLocation) {
		this.skinTestOrderEntryOrderingLocation = skinTestOrderEntryOrderingLocation;
	}
	
	public Integer getSkinTestOrderEntryBilledStatus() {
		return skinTestOrderEntryBilledStatus;
	}

	public void setSkinTestOrderEntryBilledStatus(Integer skinTestOrderEntryBilledStatus) {
		this.skinTestOrderEntryBilledStatus = skinTestOrderEntryBilledStatus;
	}

	public SkinTestOrder getSkinTestOrder() {
		return skinTestOrder;
	}

	public void setSkinTestOrder(SkinTestOrder skinTestOrder) {
		this.skinTestOrder = skinTestOrder;
	}

	public List<SkinTestOrderDetails> getSkinTestOrderDetails() {
		return skinTestOrderDetails;
	}

	public void setSkinTestOrderDetails(
			List<SkinTestOrderDetails> skinTestOrderDetails) {
		this.skinTestOrderDetails = skinTestOrderDetails;
	}

	public EmployeeProfile getOrderingPhysician() {
		return orderingPhysician;
	}

	public void setOrderingPhysician(EmployeeProfile orderingPhysician) {
		this.orderingPhysician = orderingPhysician;
	}

	public PosTable getOrderingLocation() {
		return orderingLocation;
	}

	public void setOrderingLocation(PosTable orderingLocation) {
		this.orderingLocation = orderingLocation;
	}

}