package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "provider_leaf_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
@IdClass(value = ProviderLeafMappingPK.class)
public class ProviderLeafMapping {

	
	@Id
	private Integer userid;

	@Id
	private Integer leafid;

	@Column(name="frequentlused")
	private Boolean frequentlused;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "leafid", referencedColumnName = "leaf_library_id", insertable = false, updatable = false)
	private LeafLibrary leafLibrary;
	
	
	
	public LeafLibrary getLeafLibrary() {
		return leafLibrary;
	}

	public void setLeafLibrary(LeafLibrary leafLibrary) {
		this.leafLibrary = leafLibrary;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getLeafid() {
		return leafid;
	}

	public void setLeafid(Integer leafid) {
		this.leafid = leafid;
	}

	public Boolean getFrequentlused() {
		return frequentlused;
	}

	public void setFrequentlused(Boolean frequentlused) {
		this.frequentlused = frequentlused;
	}
	
	
}