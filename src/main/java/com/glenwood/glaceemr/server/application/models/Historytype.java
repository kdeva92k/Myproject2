package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "history_type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Historytype {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="history_type_history_type_id_seq")
	@SequenceGenerator(name ="history_type_history_type_id_seq", sequenceName="history_type_history_type_id_seq", allocationSize=1)
	@Column(name="history_type_id")
	private Integer historyTypeId;

	@Column(name="history_type_name")
	private String historyTypeName;
	
	
	@Column(name="history_type_isactive")
	private boolean historyTypeIsactive;
	
	@Column(name="history_type_orderby")
	private Integer historyTypeOrderby;

/*	@OneToMany(mappedBy="historyType")
	@JsonManagedReference
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping;*/
	
	public Integer getHistoryTypeId() {
		
		return historyTypeId;
	}

	public void setHistoryTypeId(Integer historyTypeId) {
		this.historyTypeId = historyTypeId;
	}

	public String getHistoryTypeName() {
		return historyTypeName;
	}

	public void setHistoryTypeName(String historyTypeName) {
		this.historyTypeName = historyTypeName;
	}

	public boolean isHistoryTypeIsactive() {
		return historyTypeIsactive;
	}

	public void setHistoryTypeIsactive(boolean historyTypeIsactive) {
		this.historyTypeIsactive = historyTypeIsactive;
	}

	public Integer getHistoryTypeOrderby() {
		return historyTypeOrderby;
	}

	public void setHistoryTypeOrderby(Integer historyTypeOrderby) {
		this.historyTypeOrderby = historyTypeOrderby;
	}
	
	
	
	
}