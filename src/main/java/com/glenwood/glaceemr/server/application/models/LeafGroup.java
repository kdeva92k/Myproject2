package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "leaf_group")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeafGroup {

	@Id
	@Column(name="leaf_group_id")
	private Integer leafGroupId;

	@Column(name="leaf_group_name")
	private String leafGroupName;

	@Column(name="leaf_group_description")
	private String leafGroupDescription;

	@Column(name="leaf_group_order")
	private Integer leafGroupOrder;

	@Column(name="leaf_group_isactive")
	private Boolean leafGroupIsactive;

	@OneToMany(mappedBy="leafGroup")
	List<LeafLibrary> leafLibraries;
	
	public Integer getLeafGroupId() {
		return leafGroupId;
	}

	public void setLeafGroupId(Integer leafGroupId) {
		this.leafGroupId = leafGroupId;
	}

	public String getLeafGroupName() {
		return leafGroupName;
	}

	public void setLeafGroupName(String leafGroupName) {
		this.leafGroupName = leafGroupName;
	}

	public String getLeafGroupDescription() {
		return leafGroupDescription;
	}

	public void setLeafGroupDescription(String leafGroupDescription) {
		this.leafGroupDescription = leafGroupDescription;
	}

	public Integer getLeafGroupOrder() {
		return leafGroupOrder;
	}

	public void setLeafGroupOrder(Integer leafGroupOrder) {
		this.leafGroupOrder = leafGroupOrder;
	}

	public Boolean getLeafGroupIsactive() {
		return leafGroupIsactive;
	}

	public void setLeafGroupIsactive(Boolean leafGroupIsactive) {
		this.leafGroupIsactive = leafGroupIsactive;
	}
	
	
}