package com.glenwood.glaceemr.server.application.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "letter_header_pos")
public class LetterHeaderPos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="letter_header_pos_id")
	private Integer letterHeaderPosId;

	@Column(name="letter_header_pos_map_id")
	private Integer letterHeaderPosMapId;

	@Column(name="letter_header_pos_posid")
	private Integer letterHeaderPosPosid;

	@Column(name="letter_header_pos_order")
	private Integer letterHeaderPosOrder;
	
	@Column(name="letter_header_pos_variant")
	private Integer letterHeaderPosVariant;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="letter_header_pos_posid", referencedColumnName="pos_table_relation_id" , insertable=false, updatable=false)
	private PosTable posTable;	
	
	public Integer getLetterHeaderPosId() {
		return letterHeaderPosId;
	}

	public void setLetterHeaderPosId(Integer letterHeaderPosId) {
		this.letterHeaderPosId = letterHeaderPosId;
	}

	public Integer getLetterHeaderPosMapId() {
		return letterHeaderPosMapId;
	}

	public void setLetterHeaderPosMapId(Integer letterHeaderPosMapId) {
		this.letterHeaderPosMapId = letterHeaderPosMapId;
	}

	public Integer getLetterHeaderPosPosid() {
		return letterHeaderPosPosid;
	}

	public void setLetterHeaderPosPosid(Integer letterHeaderPosPosid) {
		this.letterHeaderPosPosid = letterHeaderPosPosid;
	}

	public Integer getLetterHeaderPosOrder() {
		return letterHeaderPosOrder;
	}

	public void setLetterHeaderPosOrder(Integer letterHeaderPosOrder) {
		this.letterHeaderPosOrder = letterHeaderPosOrder;
	}

	public PosTable getPosTable() {
		return posTable;
	}

	public void setPosTable(PosTable posTable) {
		this.posTable = posTable;
	}

	public Integer getLetterHeaderPosVariant() {
		return letterHeaderPosVariant;
	}

	public void setLetterHeaderPosVariant(Integer letterHeaderPosVariant) {
		this.letterHeaderPosVariant = letterHeaderPosVariant;
	}

}