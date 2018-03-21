package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "admission_room")
public class AdmissionRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admission_room_admission_room_id_seq")
	@SequenceGenerator(name ="admission_room_admission_room_id_seq", sequenceName="admission_room_admission_room_id_seq", allocationSize=1)
	@Column(name="admission_room_id")
	private Integer admissionRoomId;

	@Column(name="admission_room_block_id")
	private Integer admissionRoomBlockId;

	@Column(name="admission_room_name")
	private String admissionRoomName;

	@Column(name="admission_room_order")
	private Integer admissionRoomOrder;

	public Integer getAdmissionRoomId() {
		return admissionRoomId;
	}

	public void setAdmissionRoomId(Integer admissionRoomId) {
		this.admissionRoomId = admissionRoomId;
	}

	public Integer getAdmissionRoomBlockId() {
		return admissionRoomBlockId;
	}

	public void setAdmissionRoomBlockId(Integer admissionRoomBlockId) {
		this.admissionRoomBlockId = admissionRoomBlockId;
	}

	public String getAdmissionRoomName() {
		return admissionRoomName;
	}

	public void setAdmissionRoomName(String admissionRoomName) {
		this.admissionRoomName = admissionRoomName;
	}

	public Integer getAdmissionRoomOrder() {
		return admissionRoomOrder;
	}

	public void setAdmissionRoomOrder(Integer admissionRoomOrder) {
		this.admissionRoomOrder = admissionRoomOrder;
	}
	
	/*public AdmissionBlock getAdmissionBlock() {
		return admissionBlock;
	}

	public void setAdmissionBlock(AdmissionBlock admissionBlock) {
		this.admissionBlock = admissionBlock;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="admission_room_block_id", referencedColumnName="admission_block_id", insertable=false,updatable=false)
	AdmissionBlock admissionBlock;*/
}