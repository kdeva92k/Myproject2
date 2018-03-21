package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "associated_order_pblm")
public class AssociatedOrderPblm {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="associated_order_pblm_associated_order_pblm_map_id_seq")
	@SequenceGenerator(name ="associated_order_pblm_associated_order_pblm_map_id_seq", sequenceName="associated_order_pblm_associated_order_pblm_map_id_seq", allocationSize=1)
	@Column(name="associated_order_pblm_map_id")
	private Integer associatedOrderPblmMapId;

	@Column(name="associated_order_pblm_id")
	private Integer associatedOrderPblmId;

	@Column(name="associated_order_pblm_entity_id")
	private Integer associatedOrderPblmEntityId;

	@Column(name="associated_order_pblm_entity_type")
	private Integer associatedOrderPblmEntityType;

	@Column(name="associated_order_pblm_assoc_by")
	private Integer associatedOrderPblmAssocBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="associated_order_pblm_assoc_on")
	private Timestamp associatedOrderPblmAssocOn;

	@Column(name="associated_order_pblm_flag")
	private Boolean associatedOrderPblmFlag;

	@Column(name="associated_order_pblm_dxcode")
	private String associatedOrderPblmDxcode;

	public Integer getAssociatedOrderPblmMapId() {
		return associatedOrderPblmMapId;
	}

	public void setAssociatedOrderPblmMapId(Integer associatedOrderPblmMapId) {
		this.associatedOrderPblmMapId = associatedOrderPblmMapId;
	}

	public Integer getAssociatedOrderPblmId() {
		return associatedOrderPblmId;
	}

	public void setAssociatedOrderPblmId(Integer associatedOrderPblmId) {
		this.associatedOrderPblmId = associatedOrderPblmId;
	}

	public Integer getAssociatedOrderPblmEntityId() {
		return associatedOrderPblmEntityId;
	}

	public void setAssociatedOrderPblmEntityId(Integer associatedOrderPblmEntityId) {
		this.associatedOrderPblmEntityId = associatedOrderPblmEntityId;
	}

	public Integer getAssociatedOrderPblmEntityType() {
		return associatedOrderPblmEntityType;
	}

	public void setAssociatedOrderPblmEntityType(
			Integer associatedOrderPblmEntityType) {
		this.associatedOrderPblmEntityType = associatedOrderPblmEntityType;
	}

	public Integer getAssociatedOrderPblmAssocBy() {
		return associatedOrderPblmAssocBy;
	}

	public void setAssociatedOrderPblmAssocBy(Integer associatedOrderPblmAssocBy) {
		this.associatedOrderPblmAssocBy = associatedOrderPblmAssocBy;
	}

	public Timestamp getAssociatedOrderPblmAssocOn() {
		return associatedOrderPblmAssocOn;
	}

	public void setAssociatedOrderPblmAssocOn(Timestamp associatedOrderPblmAssocOn) {
		this.associatedOrderPblmAssocOn = associatedOrderPblmAssocOn;
	}

	public Boolean getAssociatedOrderPblmFlag() {
		return associatedOrderPblmFlag;
	}

	public void setAssociatedOrderPblmFlag(Boolean associatedOrderPblmFlag) {
		this.associatedOrderPblmFlag = associatedOrderPblmFlag;
	}

	public String getAssociatedOrderPblmDxcode() {
		return associatedOrderPblmDxcode;
	}

	public void setAssociatedOrderPblmDxcode(String associatedOrderPblmDxcode) {
		this.associatedOrderPblmDxcode = associatedOrderPblmDxcode;
	}
}