package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "admission")
public class Admission implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admission_admission_id_seq")
	@SequenceGenerator(name ="admission_admission_id_seq", sequenceName="admission_admission_id_seq", allocationSize=1)
	@Column(name="admission_id")
	private Integer admissionId;

	@Column(name="admission_patient_id")
	private Integer admissionPatientId;

	@Column(name="admission_doctor_id")
	private Integer admissionDoctorId;

	@Column(name="admission_rdoctor_id")
	private Integer admissionRdoctorId;

	@Column(name="admission_pos_id")
	private Integer admissionPosId;

	@Column(name="admission_admitted_date")
	@Type(type="date")
	private Date admissionDate;

	@Column(name="admission_discharged_date",updatable=true)
	@Type(type="date")
	private Date admissionDischargeDate;

	@Column(name="admission_episode")
	private Integer admissionEpisode;

	
	

	@Column(name="admission_unknown1")
	@Type(type="date")
	private Date admissionUnknown1;

	@Column(name="admission_unknown2")
	@Type(type="date")
	private Date admissionUnknown2;

	/*@Column(name="admission_unknown3")
	private Boolean admissionUnknown3;*/

	@Column(name="admission_admitted_to")
	private String admissionAdmittedTo;

	@Column(name="admission_referral_upin")
	private String admissionReferralUpin;

	@Column(name="admission_dx1")
	private String admissionDx1;

	@Column(name="admission_dx2")
	private String admissionDx2;

	@Column(name="admission_dx3")
	private String admissionDx3;

	@Column(name="admission_dx4")
	private String admissionDx4;

	@Column(name="admission_dx5")
	private String admissionDx5;

	@Column(name="admission_dx6")
	private String admissionDx6;

	@Column(name="admission_dx7")
	private String admissionDx7;

	@Column(name="admission_dx8")
	private String admissionDx8;

	@Column(name="admission_unknown4")
	private String admissionUnknown4;

	@Column(name="admission_unknown5")
	private String admissionUnknown5;

	@Column(name="admission_unknown6")
	private String admissionUnknown6;

	@Column(name="admission_unknown7")
	private String admissionUnknown7;

	@Column(name="admission_unknown8")
	private String admissionUnknown8;

	@Column(name="admission_unknown9")
	private String admissionUnknown9;

	@Column(name="admission_unknown10")
	private String admissionUnknown10;

	@Column(name="admission_prevadmissionid")
	private Integer admissionPrevadmissionid;

	@Column(name="admission_isadmitafterdischarge")
	private Integer admissionIsadmitafterdischarge;

	@Column(name="admission_admitted_time")
	private String admissionAdmittedTime;

	@Column(name="admission_discharged_time")
	private String admissionDischargedTime;

	@Column(name="admission_dx1desc")
	private String admissionDx1desc;

	@Column(name="admission_dx2desc")
	private String admissionDx2desc;

	@Column(name="admission_dx3desc")
	private String admissionDx3desc;

	@Column(name="admission_dx4desc")
	private String admissionDx4desc;

	@Column(name="admission_dx5desc")
	private String admissionDx5desc;

	@Column(name="admission_dx6desc")
	private String admissionDx6desc;

	@Column(name="admission_dx7desc")
	private String admissionDx7desc;

	@Column(name="admission_dx8desc")
	private String admissionDx8desc;

	@Column(name="admission_dx9")
	private String admissionDx9;

	@Column(name="admission_dx9desc")
	private String admissionDx9desc;

	@Column(name="admission_dx10desc")
	private String admissionDx10desc;

	@Column(name="admission_dx11desc")
	private String admissionDx11desc;

	@Column(name="admission_dx12desc")
	private String admissionDx12desc;

	@Column(name="admission_dx10")
	private String admissionDx10;

	@Column(name="admission_dx11")
	private String admissionDx11;

	@Column(name="admission_dx12")
	private String admissionDx12;

	@Column(name="admission_status")
	private Integer admissionStatus;
	
	@Column(name="admission_room")
	private Integer admissionRoom;
	
	@Column(name="admission_notes")
	private String admissionNotes;
	
	@Column(name="admission_discharge_doctor_id")
	private Integer admissionDischargeDocId;
	
	@Column(name="admission_block")
	private Integer admissionBlock;
	
	@Column(name="admission_time")
	private String admissionTime;
	
	@Column(name="admission_discharge_disposition")
	private String admissionDischargeDisposition;

	@Column(name="admission_discharge_disposition_other")
	private String admissionDischargeDispositionOther;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "admission_doctor_id", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfile;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "admission_pos_id", referencedColumnName = "pos_table_relation_id", insertable = false, updatable = false)
	private PosTable posTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "admission_block", referencedColumnName = "admission_block_id", insertable = false, updatable = false)
	private AdmissionBlock admissionBlockTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "admission_room", referencedColumnName = "admission_room_id", insertable = false, updatable = false)
	private AdmissionRoom admissionRoomTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "admission_patient_id", referencedColumnName = "patient_registration_id", insertable = false, updatable = false)
	private PatientRegistration  patientRegistration;
	
	@OneToMany(mappedBy="admission")
	@JsonManagedReference
	List<PatientAdmission> patientAdmission;
	
	public List<PatientAdmission> getPatientAdmission() {
		return patientAdmission;
	}

	public void setPatientAdmission(List<PatientAdmission> patientAdmission) {
		this.patientAdmission = patientAdmission;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	@OneToMany(mappedBy="admission")
	private List<Encounter> encounter;
	

	public List<Encounter> getEncounter() {
		return encounter;
	}

	public void setEncounter(List<Encounter> encounter) {
		this.encounter = encounter;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Date getAdmissionDischargeDate() {
		return admissionDischargeDate;
	}

	public void setAdmissionDischargeDate(Date admissionDischargeDate) {
		this.admissionDischargeDate = admissionDischargeDate;
	}

	

	/*public List<Encounter> getEncounter() {
		return encounter;
	}

	public void setEncounter(List<Encounter> encounter) {
		this.encounter = encounter;
	}*/

	public void setAdmissionEpisode(Integer admissionEpisode) {
		this.admissionEpisode = admissionEpisode;
	}

	public Integer getAdmissionEpisode() {
		return admissionEpisode;
	}
	
	
	public Integer getAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(Integer admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	public Integer getAdmissionDischargeDocId() {
		return admissionDischargeDocId;
	}

	public void setAdmissionDischargeDocId(Integer admissionDischargeDocId) {
		this.admissionDischargeDocId = admissionDischargeDocId;
	}
	
	
	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}

	public PosTable getPosTable() {
		return posTable;
	}

	public void setPosTable(PosTable posTable) {
		this.posTable = posTable;
	}

	public Integer getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	public Integer getAdmissionPatientId() {
		return admissionPatientId;
	}

	public void setAdmissionPatientId(Integer admissionPatientId) {
		this.admissionPatientId = admissionPatientId;
	}

	public Integer getAdmissionDoctorId() {
		return admissionDoctorId;
	}

	public void setAdmissionDoctorId(Integer admissionDoctorId) {
		this.admissionDoctorId = admissionDoctorId;
	}

	public Integer getAdmissionRdoctorId() {
		return admissionRdoctorId;
	}

	public void setAdmissionRdoctorId(Integer admissionRdoctorId) {
		this.admissionRdoctorId = admissionRdoctorId;
	}

	public Integer getAdmissionPosId() {
		return admissionPosId;
	}

	public void setAdmissionPosId(Integer admissionPosId) {
		this.admissionPosId = admissionPosId;
	}

	public Date getAdmissionAdmittedDate() {
		return admissionDate;
	}

	public void setAdmissionAdmittedDate(Date admissionAdmittedDate) {
		this.admissionDate = admissionAdmittedDate;
	}

	public Date getAdmissionDischargedDate() {
		return admissionDischargeDate;
	}

	public void setAdmissionDischargedDate(Date admissionDischargedDate) {
		this.admissionDischargeDate = admissionDischargedDate;
	}

	public Date getAdmissionUnknown1() {
		return admissionUnknown1;
	}

	public void setAdmissionUnknown1(Date admissionUnknown1) {
		this.admissionUnknown1 = admissionUnknown1;
	}

	public Date getAdmissionUnknown2() {
		return admissionUnknown2;
	}

	public void setAdmissionUnknown2(Date admissionUnknown2) {
		this.admissionUnknown2 = admissionUnknown2;
	}

	/*public Boolean getAdmissionUnknown3() {
		return admissionUnknown3;
	}

	public void setAdmissionUnknown3(Boolean admissionUnknown3) {
		this.admissionUnknown3 = admissionUnknown3;
	}*/

	public String getAdmissionAdmittedTo() {
		return admissionAdmittedTo;
	}

	public void setAdmissionAdmittedTo(String admissionAdmittedTo) {
		this.admissionAdmittedTo = admissionAdmittedTo;
	}

	public String getAdmissionReferralUpin() {
		return admissionReferralUpin;
	}

	public void setAdmissionReferralUpin(String admissionReferralUpin) {
		this.admissionReferralUpin = admissionReferralUpin;
	}

	public String getAdmissionDx1() {
		return admissionDx1;
	}

	public void setAdmissionDx1(String admissionDx1) {
		this.admissionDx1 = admissionDx1;
	}

	public String getAdmissionDx2() {
		return admissionDx2;
	}

	public void setAdmissionDx2(String admissionDx2) {
		this.admissionDx2 = admissionDx2;
	}

	public String getAdmissionDx3() {
		return admissionDx3;
	}

	public void setAdmissionDx3(String admissionDx3) {
		this.admissionDx3 = admissionDx3;
	}

	public String getAdmissionDx4() {
		return admissionDx4;
	}

	public void setAdmissionDx4(String admissionDx4) {
		this.admissionDx4 = admissionDx4;
	}

	public String getAdmissionDx5() {
		return admissionDx5;
	}

	public void setAdmissionDx5(String admissionDx5) {
		this.admissionDx5 = admissionDx5;
	}

	public String getAdmissionDx6() {
		return admissionDx6;
	}

	public void setAdmissionDx6(String admissionDx6) {
		this.admissionDx6 = admissionDx6;
	}

	public String getAdmissionDx7() {
		return admissionDx7;
	}

	public void setAdmissionDx7(String admissionDx7) {
		this.admissionDx7 = admissionDx7;
	}

	public String getAdmissionDx8() {
		return admissionDx8;
	}

	public void setAdmissionDx8(String admissionDx8) {
		this.admissionDx8 = admissionDx8;
	}

	public String getAdmissionUnknown4() {
		return admissionUnknown4;
	}

	public void setAdmissionUnknown4(String admissionUnknown4) {
		this.admissionUnknown4 = admissionUnknown4;
	}

	public String getAdmissionUnknown5() {
		return admissionUnknown5;
	}

	public void setAdmissionUnknown5(String admissionUnknown5) {
		this.admissionUnknown5 = admissionUnknown5;
	}

	public String getAdmissionUnknown6() {
		return admissionUnknown6;
	}

	public void setAdmissionUnknown6(String admissionUnknown6) {
		this.admissionUnknown6 = admissionUnknown6;
	}

	public String getAdmissionUnknown7() {
		return admissionUnknown7;
	}

	public void setAdmissionUnknown7(String admissionUnknown7) {
		this.admissionUnknown7 = admissionUnknown7;
	}

	public String getAdmissionUnknown8() {
		return admissionUnknown8;
	}

	public void setAdmissionUnknown8(String admissionUnknown8) {
		this.admissionUnknown8 = admissionUnknown8;
	}

	public String getAdmissionUnknown9() {
		return admissionUnknown9;
	}

	public void setAdmissionUnknown9(String admissionUnknown9) {
		this.admissionUnknown9 = admissionUnknown9;
	}

	public String getAdmissionUnknown10() {
		return admissionUnknown10;
	}

	public void setAdmissionUnknown10(String admissionUnknown10) {
		this.admissionUnknown10 = admissionUnknown10;
	}

	public Integer getAdmissionPrevadmissionid() {
		return admissionPrevadmissionid;
	}

	public void setAdmissionPrevadmissionid(Integer admissionPrevadmissionid) {
		this.admissionPrevadmissionid = admissionPrevadmissionid;
	}

	public Integer getAdmissionIsadmitafterdischarge() {
		return admissionIsadmitafterdischarge;
	}

	public void setAdmissionIsadmitafterdischarge(
			Integer admissionIsadmitafterdischarge) {
		this.admissionIsadmitafterdischarge = admissionIsadmitafterdischarge;
	}

	public String getAdmissionAdmittedTime() {
		return admissionAdmittedTime;
	}

	public void setAdmissionAdmittedTime(String admissionAdmittedTime) {
		this.admissionAdmittedTime = admissionAdmittedTime;
	}

	public String getAdmissionDischargedTime() {
		return admissionDischargedTime;
	}

	public void setAdmissionDischargedTime(String admissionDischargedTime) {
		this.admissionDischargedTime = admissionDischargedTime;
	}

	public String getAdmissionDx1desc() {
		return admissionDx1desc;
	}

	public void setAdmissionDx1desc(String admissionDx1desc) {
		this.admissionDx1desc = admissionDx1desc;
	}

	public String getAdmissionDx2desc() {
		return admissionDx2desc;
	}

	public void setAdmissionDx2desc(String admissionDx2desc) {
		this.admissionDx2desc = admissionDx2desc;
	}

	public String getAdmissionDx3desc() {
		return admissionDx3desc;
	}

	public void setAdmissionDx3desc(String admissionDx3desc) {
		this.admissionDx3desc = admissionDx3desc;
	}

	public String getAdmissionDx4desc() {
		return admissionDx4desc;
	}

	public void setAdmissionDx4desc(String admissionDx4desc) {
		this.admissionDx4desc = admissionDx4desc;
	}

	public String getAdmissionDx5desc() {
		return admissionDx5desc;
	}

	public void setAdmissionDx5desc(String admissionDx5desc) {
		this.admissionDx5desc = admissionDx5desc;
	}

	public String getAdmissionDx6desc() {
		return admissionDx6desc;
	}

	public void setAdmissionDx6desc(String admissionDx6desc) {
		this.admissionDx6desc = admissionDx6desc;
	}

	public String getAdmissionDx7desc() {
		return admissionDx7desc;
	}

	public void setAdmissionDx7desc(String admissionDx7desc) {
		this.admissionDx7desc = admissionDx7desc;
	}

	public String getAdmissionDx8desc() {
		return admissionDx8desc;
	}

	public void setAdmissionDx8desc(String admissionDx8desc) {
		this.admissionDx8desc = admissionDx8desc;
	}

	public String getAdmissionDx9() {
		return admissionDx9;
	}

	public void setAdmissionDx9(String admissionDx9) {
		this.admissionDx9 = admissionDx9;
	}

	public String getAdmissionDx9desc() {
		return admissionDx9desc;
	}

	public void setAdmissionDx9desc(String admissionDx9desc) {
		this.admissionDx9desc = admissionDx9desc;
	}

	public String getAdmissionDx10desc() {
		return admissionDx10desc;
	}

	public void setAdmissionDx10desc(String admissionDx10desc) {
		this.admissionDx10desc = admissionDx10desc;
	}

	public String getAdmissionDx11desc() {
		return admissionDx11desc;
	}

	public void setAdmissionDx11desc(String admissionDx11desc) {
		this.admissionDx11desc = admissionDx11desc;
	}

	public String getAdmissionDx12desc() {
		return admissionDx12desc;
	}

	public void setAdmissionDx12desc(String admissionDx12desc) {
		this.admissionDx12desc = admissionDx12desc;
	}

	public String getAdmissionDx10() {
		return admissionDx10;
	}

	public void setAdmissionDx10(String admissionDx10) {
		this.admissionDx10 = admissionDx10;
	}

	public String getAdmissionDx11() {
		return admissionDx11;
	}

	public void setAdmissionDx11(String admissionDx11) {
		this.admissionDx11 = admissionDx11;
	}

	public String getAdmissionDx12() {
		return admissionDx12;
	}

	public void setAdmissionDx12(String admissionDx12) {
		this.admissionDx12 = admissionDx12;
	}

	public Integer getAdmissionRoom() {
		return admissionRoom;
	}

	public void setAdmissionRoom(Integer admissionRoom) {
		this.admissionRoom = admissionRoom;
	}

	public String getAdmissionNotes() {
		return admissionNotes;
	}

	public void setAdmissionNotes(String admissionNotes) {
		this.admissionNotes = admissionNotes;
	}

	public Integer getAdmissionBlock() {
		return admissionBlock;
	}

	public void setAdmissionBlock(Integer admissionBlock) {
		this.admissionBlock = admissionBlock;
	}

	public AdmissionBlock getAdmissionBlockTable() {
		return admissionBlockTable;
	}

	public void setAdmissionBlockTable(AdmissionBlock admissionBlockTable) {
		this.admissionBlockTable = admissionBlockTable;
	}

	public AdmissionRoom getAdmissionRoomTable() {
		return admissionRoomTable;
	}

	public void setAdmissionRoomTable(AdmissionRoom admissionRoomTable) {
		this.admissionRoomTable = admissionRoomTable;
	}

	public String getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(String admissionTime) {
		this.admissionTime = admissionTime;
	}	
	public void setadmissionDischargeDisposition(String admissionDischargeDisposition) {
		this.admissionDischargeDisposition = admissionDischargeDisposition;
	}	
	public String getadmissionDischargeDisposition() {
		return admissionDischargeDisposition;
	}

	public void setadmissionDischargeDispositionOther(String admissionDischargeDispositionOther) {
		this.admissionDischargeDispositionOther = admissionDischargeDispositionOther;
	}	
	public String getaadmissionDischargeDispositionOther() {
		return admissionDischargeDispositionOther;
	}
	
	
}