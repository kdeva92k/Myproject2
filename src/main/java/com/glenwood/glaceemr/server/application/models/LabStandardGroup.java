package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "lab_standard_group")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabStandardGroup {

	@Id
	@Column(name="lab_standard_group_id", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lab_standard_group_lab_standard_group_id_seq")
	@SequenceGenerator(name ="lab_standard_group_lab_standard_group_id_seq", sequenceName="lab_standard_group_lab_standard_group_id_seq", allocationSize=1)
	private Integer labStandardGroupId;

	@Column(name="lab_standard_group_name", length=100)
	private String labStandardGroupName;
	
	@Column(name="lab_standard_group_gender", columnDefinition="Integer default 5")
	private Integer labStandardGroupGender;
	
	@Column(name="lab_standard_group_isactive", columnDefinition="Boolean default true")
	private Boolean labStandardGroupIsactive;

	@OneToMany(mappedBy="labStandardCodeGroupId")
	@JsonManagedReference
	List<LabStandardCode> labStandardCodeTable;
	
	public String getLabStandardGroupName() {
		return labStandardGroupName;
	}

	public void setLabStandardGroupName(String labStandardGroupName) {
		this.labStandardGroupName = labStandardGroupName;
	}

	public Integer getLabStandardGroupId() {
		return labStandardGroupId;
	}

	public void setLabStandardGroupId(Integer labStandardGroupId) {
		this.labStandardGroupId = labStandardGroupId;
	}

	public Integer getLabStandardGroupGender() {
		return labStandardGroupGender;
	}

	public void setLabStandardGroupGender(Integer labStandardGroupGender) {
		this.labStandardGroupGender = labStandardGroupGender;
	}

	public Boolean getLabStandardGroupIsactive() {
		return labStandardGroupIsactive;
	}

	public void setLabStandardGroupIsactive(Boolean labStandardGroupIsactive) {
		this.labStandardGroupIsactive = labStandardGroupIsactive;
	}

	public List<LabStandardCode> getLabStandardCodeTable() {
		return labStandardCodeTable;
	}

	public void setLabStandardCodeTable(List<LabStandardCode> labCodeTable) {
		this.labStandardCodeTable = labCodeTable;
	}
}