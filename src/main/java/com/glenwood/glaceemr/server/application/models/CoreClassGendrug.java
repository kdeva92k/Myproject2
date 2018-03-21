package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "core_class_gendrug")
public class CoreClassGendrug implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Column(name="id", length=5)
	private String id;

	@Column(name="class_id", length=200)
	private String classId;

	@Id
	@Column(name="drug_id", length=200)
	private String drugId;

	@Column(name="is_direct_link", length=200)
	private String isDirectLink;

	@Column(name="class_system_id", length=200)
	private String classSystemId;

	@Column(name="create_date", length=200)
	private String createDate;
	
	@Column(name="update_date", length=200)
	private String updateDate;

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="class_id", referencedColumnName="class_id", insertable=false, updatable=false)
	@JsonManagedReference
    CoreClassHierarchy coreClassHierarchyTable;
	
	@OneToOne
	@JoinColumn(name="drug_id", referencedColumnName="drug_id", insertable=false, updatable=false)
	@JsonManagedReference
    CoreGendrug coreGendrugTable;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getIsDirectLink() {
		return isDirectLink;
	}

	public void setIsDirectLink(String isDirectLink) {
		this.isDirectLink = isDirectLink;
	}

	public String getClassSystemId() {
		return classSystemId;
	}

	public void setClassSystemId(String classSystemId) {
		this.classSystemId = classSystemId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}