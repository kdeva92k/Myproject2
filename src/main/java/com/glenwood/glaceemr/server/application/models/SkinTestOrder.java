package com.glenwood.glaceemr.server.application.models;

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
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderStatus;
import com.glenwood.glaceemr.server.utils.JsonDateSerializer;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "skin_test_order")
public class SkinTestOrder {

	@Id
	@Column(name="skin_test_order_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="skin_test_order_id")
	@SequenceGenerator(name="skin_test_order_id",sequenceName="skin_test_order_id",allocationSize=1)
	private Integer skinTestOrderId;

	@Column(name="skin_test_order_patient_id")
	private Integer skinTestOrderPatientId;
	
	@Column(name="skin_test_order_skin_test_form_shortcut_id")
	private Integer skinTestOrderSkinTestFormShortcutId;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name="skin_test_order_start_date")
	private Date skinTestOrderStartDate;

	@Column(name="skin_test_order_status")
	private Integer skinTestOrderStatus;

	@Column(name="skin_test_order_notes")
	private String skinTestOrderNotes;
	
	@Column(name="skin_test_order_technician")
	private Integer skinTestOrderTechnician;
	
	@Column(name="skin_test_order_encounter_id")
	private Integer skinTestOrderEncounterId;
	
	@Column(name="skin_test_order_dx_code1")
	private String skinTestOrderDxCode1;
	
	@Column(name="skin_test_order_dx_desc1")
	private String skinTestOrderDxDesc1;
	
	@Column(name="skin_test_order_dx_code2")
	private String skinTestOrderDxCode2;
	
	@Column(name="skin_test_order_dx_desc2")
	private String skinTestOrderDxDesc2;
	
	@Column(name="skin_test_order_dx_code3")
	private String skinTestOrderDxCode3;
	
	@Column(name="skin_test_order_dx_desc3")
	private String skinTestOrderDxDesc3;
	
	@Column(name="skin_test_order_dx_code4")
	private String skinTestOrderDxCode4;
	
	@Column(name="skin_test_order_dx_desc4")
	private String skinTestOrderDxDesc4;
	
	@Column(name="skin_test_order_dx_code5")
	private String skinTestOrderDxCode5;

	@Column(name="skin_test_order_dx_desc5")
	private String skinTestOrderDxDesc5;

	@Column(name="skin_test_order_dx_code6")
	private String skinTestOrderDxCode6;

	@Column(name="skin_test_order_dx_desc6")
	private String skinTestOrderDxDesc6;

	@Column(name="skin_test_order_dx_code7")
	private String skinTestOrderDxCode7;

	@Column(name="skin_test_order_dx_desc7")
	private String skinTestOrderDxDesc7;

	@Column(name="skin_test_order_dx_code8")
	private String skinTestOrderDxCode8;

	@Column(name="skin_test_order_dx_desc8")
	private String skinTestOrderDxDesc8;

	@Column(name="skin_test_order_dx_code9")
	private String skinTestOrderDxCode9;

	@Column(name="skin_test_order_dx_desc9")
	private String skinTestOrderDxDesc9;

	@Column(name="skin_test_order_dx_code10")
	private String skinTestOrderDxCode10;

	@Column(name="skin_test_order_dx_desc10")
	private String skinTestOrderDxDesc10;

	@Column(name="skin_test_order_dx_code11")
	private String skinTestOrderDxCode11;

	@Column(name="skin_test_order_dx_desc11")
	private String skinTestOrderDxDesc11;

	@Column(name="skin_test_order_dx_code12")
	private String skinTestOrderDxCode12;

	@Column(name="skin_test_order_dx_desc12")
	private String skinTestOrderDxDesc12;
	
	@Column(name="skin_test_order_dx_code_system_id")
	private String skinTestOrderDxCodeSystemId;
	
	@Column(name="skin_test_order_mapping_test_detail_id")
	private Integer skinTestOrderMappingTestDetailId;
	
	@Column(name="skin_test_order_ordering_physician")
	private Integer skinTestOrderOrderingPhysician;

	@Column(name="skin_test_order_ordering_location")
	private Integer skinTestOrderOrderingLocation;
	
	@Column(name="skin_test_order_created_by")
	private Integer skinTestOrderCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_created_on")
	private Timestamp skinTestOrderCreatedOn;

	@Column(name="skin_test_order_last_modified_by")
	private Integer skinTestOrderLastModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_last_modified_on")
	private Timestamp skinTestOrderLastModifiedOn;
	
	@Column(name="skin_test_order_completed_by")
	private Integer skinTestOrderCompletedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_completed_on")
	private Timestamp skinTestOrderCompletedOn;

	@Column(name="skin_test_order_reviewed_by")
	private Integer skinTestOrderReviewedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="skin_test_order_reviewed_on")
	private Timestamp skinTestOrderReviewedOn;
	
	@Column(name="skin_test_order_evaluation_text")
	private String skinTestOrderEvaluationText;
	
	@Column(name="skin_test_order_result_comments")
	private String skinTestOrderResultComments;
	
	@ManyToOne
	@JoinColumn(name="skin_test_order_status",referencedColumnName="skin_test_order_status_id",insertable=false,updatable=false)
	@JsonManagedReference
	SkinTestOrderStatus orderStatus;
	
	@OneToMany(mappedBy="skinTestOrder")
	@JsonManagedReference
	List<SkinTestOrderEntry> skinTestOrderEntries;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_skin_test_form_shortcut_id",referencedColumnName="skin_test_form_shortcut_id",insertable=false,updatable=false)
	@JsonManagedReference
	SkinTestFormShortcut skinTestFormShortcut;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_technician",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile technician;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_ordering_physician",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile orderingPhysician;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_reviewed_by",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile reviewedBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skin_test_order_completed_by",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile completedBy;

	public Integer getSkinTestOrderId() {
		return skinTestOrderId;
	}

	public void setSkinTestOrderId(Integer skinTestOrderId) {
		this.skinTestOrderId = skinTestOrderId;
	}

	public Integer getSkinTestOrderPatientId() {
		return skinTestOrderPatientId;
	}

	public void setSkinTestOrderPatientId(Integer skinTestOrderPatientId) {
		this.skinTestOrderPatientId = skinTestOrderPatientId;
	}

	public Integer getSkinTestOrderSkinTestFormShortcutId() {
		return skinTestOrderSkinTestFormShortcutId;
	}

	public void setSkinTestOrderSkinTestFormShortcutId(
			Integer skinTestOrderSkinTestFormShortcutId) {
		this.skinTestOrderSkinTestFormShortcutId = skinTestOrderSkinTestFormShortcutId;
	}

	public Date getSkinTestOrderStartDate() {
		return skinTestOrderStartDate;
	}

	public void setSkinTestOrderStartDate(Date skinTestOrderStartDate) {
		this.skinTestOrderStartDate = skinTestOrderStartDate;
	}

	public Integer getSkinTestOrderStatus() {
		return skinTestOrderStatus;
	}

	public void setSkinTestOrderStatus(Integer skinTestOrderStatus) {
		this.skinTestOrderStatus = skinTestOrderStatus;
	}

	public String getSkinTestOrderNotes() {
		return skinTestOrderNotes;
	}

	public void setSkinTestOrderNotes(String skinTestOrderNotes) {
		this.skinTestOrderNotes = skinTestOrderNotes;
	}

	public Integer getSkinTestOrderTechnician() {
		return skinTestOrderTechnician;
	}

	public void setSkinTestOrderTechnician(Integer skinTestOrderTechnician) {
		this.skinTestOrderTechnician = skinTestOrderTechnician;
	}

	public Integer getSkinTestOrderEncounterId() {
		return skinTestOrderEncounterId;
	}

	public void setSkinTestOrderEncounterId(Integer skinTestOrderEncounterId) {
		this.skinTestOrderEncounterId = skinTestOrderEncounterId;
	}

	public String getSkinTestOrderDxCode1() {
		return skinTestOrderDxCode1;
	}

	public void setSkinTestOrderDxCode1(String skinTestOrderDxCode1) {
		this.skinTestOrderDxCode1 = skinTestOrderDxCode1;
	}

	public String getSkinTestOrderDxDesc1() {
		return skinTestOrderDxDesc1;
	}

	public void setSkinTestOrderDxDesc1(String skinTestOrderDxDesc1) {
		this.skinTestOrderDxDesc1 = skinTestOrderDxDesc1;
	}

	public String getSkinTestOrderDxCode2() {
		return skinTestOrderDxCode2;
	}

	public void setSkinTestOrderDxCode2(String skinTestOrderDxCode2) {
		this.skinTestOrderDxCode2 = skinTestOrderDxCode2;
	}

	public String getSkinTestOrderDxDesc2() {
		return skinTestOrderDxDesc2;
	}

	public void setSkinTestOrderDxDesc2(String skinTestOrderDxDesc2) {
		this.skinTestOrderDxDesc2 = skinTestOrderDxDesc2;
	}

	public String getSkinTestOrderDxCode3() {
		return skinTestOrderDxCode3;
	}

	public void setSkinTestOrderDxCode3(String skinTestOrderDxCode3) {
		this.skinTestOrderDxCode3 = skinTestOrderDxCode3;
	}

	public String getSkinTestOrderDxDesc3() {
		return skinTestOrderDxDesc3;
	}

	public void setSkinTestOrderDxDesc3(String skinTestOrderDxDesc3) {
		this.skinTestOrderDxDesc3 = skinTestOrderDxDesc3;
	}

	public String getSkinTestOrderDxCode4() {
		return skinTestOrderDxCode4;
	}

	public void setSkinTestOrderDxCode4(String skinTestOrderDxCode4) {
		this.skinTestOrderDxCode4 = skinTestOrderDxCode4;
	}

	public String getSkinTestOrderDxDesc4() {
		return skinTestOrderDxDesc4;
	}

	public void setSkinTestOrderDxDesc4(String skinTestOrderDxDesc4) {
		this.skinTestOrderDxDesc4 = skinTestOrderDxDesc4;
	}

	public String getSkinTestOrderDxCode5() {
		return skinTestOrderDxCode5;
	}

	public void setSkinTestOrderDxCode5(String skinTestOrderDxCode5) {
		this.skinTestOrderDxCode5 = skinTestOrderDxCode5;
	}

	public String getSkinTestOrderDxDesc5() {
		return skinTestOrderDxDesc5;
	}

	public void setSkinTestOrderDxDesc5(String skinTestOrderDxDesc5) {
		this.skinTestOrderDxDesc5 = skinTestOrderDxDesc5;
	}

	public String getSkinTestOrderDxCode6() {
		return skinTestOrderDxCode6;
	}

	public void setSkinTestOrderDxCode6(String skinTestOrderDxCode6) {
		this.skinTestOrderDxCode6 = skinTestOrderDxCode6;
	}

	public String getSkinTestOrderDxDesc6() {
		return skinTestOrderDxDesc6;
	}

	public void setSkinTestOrderDxDesc6(String skinTestOrderDxDesc6) {
		this.skinTestOrderDxDesc6 = skinTestOrderDxDesc6;
	}

	public String getSkinTestOrderDxCode7() {
		return skinTestOrderDxCode7;
	}

	public void setSkinTestOrderDxCode7(String skinTestOrderDxCode7) {
		this.skinTestOrderDxCode7 = skinTestOrderDxCode7;
	}

	public String getSkinTestOrderDxDesc7() {
		return skinTestOrderDxDesc7;
	}

	public void setSkinTestOrderDxDesc7(String skinTestOrderDxDesc7) {
		this.skinTestOrderDxDesc7 = skinTestOrderDxDesc7;
	}

	public String getSkinTestOrderDxCode8() {
		return skinTestOrderDxCode8;
	}

	public void setSkinTestOrderDxCode8(String skinTestOrderDxCode8) {
		this.skinTestOrderDxCode8 = skinTestOrderDxCode8;
	}

	public String getSkinTestOrderDxDesc8() {
		return skinTestOrderDxDesc8;
	}

	public void setSkinTestOrderDxDesc8(String skinTestOrderDxDesc8) {
		this.skinTestOrderDxDesc8 = skinTestOrderDxDesc8;
	}

	public String getSkinTestOrderDxCode9() {
		return skinTestOrderDxCode9;
	}

	public void setSkinTestOrderDxCode9(String skinTestOrderDxCode9) {
		this.skinTestOrderDxCode9 = skinTestOrderDxCode9;
	}

	public String getSkinTestOrderDxDesc9() {
		return skinTestOrderDxDesc9;
	}

	public void setSkinTestOrderDxDesc9(String skinTestOrderDxDesc9) {
		this.skinTestOrderDxDesc9 = skinTestOrderDxDesc9;
	}

	public String getSkinTestOrderDxCode10() {
		return skinTestOrderDxCode10;
	}

	public void setSkinTestOrderDxCode10(String skinTestOrderDxCode10) {
		this.skinTestOrderDxCode10 = skinTestOrderDxCode10;
	}

	public String getSkinTestOrderDxDesc10() {
		return skinTestOrderDxDesc10;
	}

	public void setSkinTestOrderDxDesc10(String skinTestOrderDxDesc10) {
		this.skinTestOrderDxDesc10 = skinTestOrderDxDesc10;
	}

	public String getSkinTestOrderDxCode11() {
		return skinTestOrderDxCode11;
	}

	public void setSkinTestOrderDxCode11(String skinTestOrderDxCode11) {
		this.skinTestOrderDxCode11 = skinTestOrderDxCode11;
	}

	public String getSkinTestOrderDxDesc11() {
		return skinTestOrderDxDesc11;
	}

	public void setSkinTestOrderDxDesc11(String skinTestOrderDxDesc11) {
		this.skinTestOrderDxDesc11 = skinTestOrderDxDesc11;
	}

	public String getSkinTestOrderDxCode12() {
		return skinTestOrderDxCode12;
	}

	public void setSkinTestOrderDxCode12(String skinTestOrderDxCode12) {
		this.skinTestOrderDxCode12 = skinTestOrderDxCode12;
	}

	public String getSkinTestOrderDxDesc12() {
		return skinTestOrderDxDesc12;
	}

	public void setSkinTestOrderDxDesc12(String skinTestOrderDxDesc12) {
		this.skinTestOrderDxDesc12 = skinTestOrderDxDesc12;
	}
	
	public String getSkinTestOrderDxCodeSystemId() {
		return skinTestOrderDxCodeSystemId;
	}

	public void setSkinTestOrderDxCodeSystemId(String skinTestOrderDxCodeSystemId) {
		this.skinTestOrderDxCodeSystemId = skinTestOrderDxCodeSystemId;
	}

	public Integer getSkinTestOrderMappingTestDetailId() {
		return skinTestOrderMappingTestDetailId;
	}

	public void setSkinTestOrderMappingTestDetailId(
			Integer skinTestOrderMappingTestDetailId) {
		this.skinTestOrderMappingTestDetailId = skinTestOrderMappingTestDetailId;
	}

	public Integer getSkinTestOrderOrderingPhysician() {
		return skinTestOrderOrderingPhysician;
	}

	public void setSkinTestOrderOrderingPhysician(
			Integer skinTestOrderOrderingPhysician) {
		this.skinTestOrderOrderingPhysician = skinTestOrderOrderingPhysician;
	}

	public Integer getSkinTestOrderOrderingLocation() {
		return skinTestOrderOrderingLocation;
	}

	public void setSkinTestOrderOrderingLocation(
			Integer skinTestOrderOrderingLocation) {
		this.skinTestOrderOrderingLocation = skinTestOrderOrderingLocation;
	}

	public Integer getSkinTestOrderCreatedBy() {
		return skinTestOrderCreatedBy;
	}

	public void setSkinTestOrderCreatedBy(Integer skinTestOrderCreatedBy) {
		this.skinTestOrderCreatedBy = skinTestOrderCreatedBy;
	}

	public Timestamp getSkinTestOrderCreatedOn() {
		return skinTestOrderCreatedOn;
	}

	public void setSkinTestOrderCreatedOn(Timestamp skinTestOrderCreatedOn) {
		this.skinTestOrderCreatedOn = skinTestOrderCreatedOn;
	}

	public Integer getSkinTestOrderLastModifiedBy() {
		return skinTestOrderLastModifiedBy;
	}

	public void setSkinTestOrderLastModifiedBy(Integer skinTestOrderLastModifiedBy) {
		this.skinTestOrderLastModifiedBy = skinTestOrderLastModifiedBy;
	}

	public Timestamp getSkinTestOrderLastModifiedOn() {
		return skinTestOrderLastModifiedOn;
	}

	public void setSkinTestOrderLastModifiedOn(Timestamp skinTestOrderLastModifiedOn) {
		this.skinTestOrderLastModifiedOn = skinTestOrderLastModifiedOn;
	}
	
	public Integer getSkinTestOrderCompletedBy() {
		return skinTestOrderCreatedBy;
	}

	public void setSkinTestOrderCompletedBy(Integer skinTestOrderCompletedBy) {
		this.skinTestOrderCompletedBy = skinTestOrderCompletedBy;
	}

	public Timestamp getSkinTestOrderCompletedOn() {
		return skinTestOrderCompletedOn;
	}

	public void setSkinTestOrderCompletedOn(Timestamp skinTestOrderCompletedOn) {
		this.skinTestOrderCompletedOn = skinTestOrderCompletedOn;
	}

	public Integer getSkinTestOrderReviewedBy() {
		return skinTestOrderReviewedBy;
	}

	public void setSkinTestOrderReviewedBy(Integer skinTestOrderReviewedBy) {
		this.skinTestOrderReviewedBy = skinTestOrderReviewedBy;
	}

	public Timestamp getSkinTestOrderReviewedOn() {
		return skinTestOrderReviewedOn;
	}
	
	public String getSkinTestOrderEvaluationText() {
		return skinTestOrderEvaluationText;
	}

	public void setSkinTestOrderEvaluationText(String skinTestOrderEvaluationText) {
		this.skinTestOrderEvaluationText = skinTestOrderEvaluationText;
	}

	public String getSkinTestOrderResultComments() {
		return skinTestOrderResultComments;
	}

	public void setSkinTestOrderResultComments(String skinTestOrderResultComments) {
		this.skinTestOrderResultComments = skinTestOrderResultComments;
	}
	
	public void setSkinTestOrderReviewedOn(Timestamp skinTestOrderReviewedOn) {
		this.skinTestOrderReviewedOn = skinTestOrderReviewedOn;
	}
	
	public SkinTestOrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(SkinTestOrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<SkinTestOrderEntry> getSkinTestOrderEntries() {
		return skinTestOrderEntries;
	}

	public void setSkinTestOrderEntries(
			List<SkinTestOrderEntry> skinTestOrderEntries) {
		this.skinTestOrderEntries = skinTestOrderEntries;
	}

	public SkinTestFormShortcut getSkinTestFormShortcut() {
		return skinTestFormShortcut;
	}

	public void setSkinTestFormShortcut(SkinTestFormShortcut skinTestFormShortcut) {
		this.skinTestFormShortcut = skinTestFormShortcut;
	}

	public EmployeeProfile getTechnician() {
		return technician;
	}

	public void setTechnician(EmployeeProfile technician) {
		this.technician = technician;
	}

	public EmployeeProfile getOrderingPhysician() {
		return orderingPhysician;
	}

	public void setOrderingPhysician(EmployeeProfile orderingPhysician) {
		this.orderingPhysician = orderingPhysician;
	}

	public EmployeeProfile getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(EmployeeProfile reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public EmployeeProfile getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(EmployeeProfile completedBy) {
		this.completedBy = completedBy;
	}

}