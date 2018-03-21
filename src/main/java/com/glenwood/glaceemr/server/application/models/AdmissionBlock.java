package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "admission_block")
public class AdmissionBlock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admission_block_admission_block_id_seq")
	@SequenceGenerator(name ="admission_block_admission_block_id_seq", sequenceName="admission_block_admission_block_id_seq", allocationSize=1)
	@Column(name="admission_block_id")
	private Integer admissionBlockId;

	@Column(name="admission_block_name")
	private String admissionBlockName;

	@Column(name="admission_block_order")
	private Integer admissionBlockOrder;

	public Integer getAdmissionBlockId() {
		return admissionBlockId;
	}

	public void setAdmissionBlockId(Integer admissionBlockId) {
		this.admissionBlockId = admissionBlockId;
	}

	public String getAdmissionBlockName() {
		return admissionBlockName;
	}

	public void setAdmissionBlockName(String admissionBlockName) {
		this.admissionBlockName = admissionBlockName;
	}

	public Integer getAdmissionBlockOrder() {
		return admissionBlockOrder;
	}

	public void setAdmissionBlockOrder(Integer admissionBlockOrder) {
		this.admissionBlockOrder = admissionBlockOrder;
	}
	
}