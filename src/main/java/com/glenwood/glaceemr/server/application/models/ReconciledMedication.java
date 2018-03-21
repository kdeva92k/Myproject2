package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "reconciled_medication")
public class ReconciledMedication {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="reconciled_medication_reconciled_medication_id_seq")
	@SequenceGenerator(name = "reconciled_medication_reconciled_medication_id_seq", sequenceName="reconciled_medication_reconciled_medication_id_seq", allocationSize=1)
	@Column(name="reconciled_medication_id")
	private Integer reconciledMedicationId;

	@Column(name="reconciled_medication_map_id")
	private Integer reconciledMedicationMapId;

	@Column(name="reconciled_medication_map_internal_root_source")
	private String reconciledMedicationMapInternalRootSource;

	@Column(name="reconciled_medication_by")
	private Integer reconciledMedicationBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="reconciled_medication_on")
	private Timestamp reconciledMedicationOn;

	@Column(name="reconciled_medication_encounterid")
	private Integer reconciledMedicationEncounterid;

	public Integer getReconciledMedicationId() {
		return reconciledMedicationId;
	}

	public void setReconciledMedicationId(Integer reconciledMedicationId) {
		this.reconciledMedicationId = reconciledMedicationId;
	}

	public Integer getReconciledMedicationMapId() {
		return reconciledMedicationMapId;
	}

	public void setReconciledMedicationMapId(Integer reconciledMedicationMapId) {
		this.reconciledMedicationMapId = reconciledMedicationMapId;
	}

	public String getReconciledMedicationMapInternalRootSource() {
		return reconciledMedicationMapInternalRootSource;
	}

	public void setReconciledMedicationMapInternalRootSource(
			String reconciledMedicationMapInternalRootSource) {
		this.reconciledMedicationMapInternalRootSource = reconciledMedicationMapInternalRootSource;
	}

	public Integer getReconciledMedicationBy() {
		return reconciledMedicationBy;
	}

	public void setReconciledMedicationBy(Integer reconciledMedicationBy) {
		this.reconciledMedicationBy = reconciledMedicationBy;
	}

	public Timestamp getReconciledMedicationOn() {
		return reconciledMedicationOn;
	}

	public void setReconciledMedicationOn(Timestamp reconciledMedicationOn) {
		this.reconciledMedicationOn = reconciledMedicationOn;
	}

	public Integer getReconciledMedicationEncounterid() {
		return reconciledMedicationEncounterid;
	}

	public void setReconciledMedicationEncounterid(
			Integer reconciledMedicationEncounterid) {
		this.reconciledMedicationEncounterid = reconciledMedicationEncounterid;
	}
	
}