package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "core_class_hierarchy")
public class CoreClassHierarchy implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=5)
	private String id;

	@Column(name="class_id", length=200)
	private String classId;

	@Column(name="display_name", length=200)
	private String displayName;

	@Column(name="search_name", length=200)
	private String searchName;

	@Column(name="metaphone_1", length=200)
	private String metaphone1;

	@Column(name="metaphone_2", length=200)
	private String metaphone2;

	@Column(name="parent_class_id", length=200)
	private String parentClassId;

	@Column(name="has_children", length=200)
	private String hasChildren;

	@Column(name="is_active", length=200)
	private String isActive;

	@Column(name="class_system_id", length=200)
	private String classSystemId;

	@Column(name="create_date", length=200)
	private String createDate;

	@Column(name="update_date", length=200)
	private String updateDate;

	@OneToMany(mappedBy="classId")
	@JsonManagedReference
    List<CoreClassGendrug> coreClassGendrugTable;
	
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getMetaphone1() {
		return metaphone1;
	}

	public void setMetaphone1(String metaphone1) {
		this.metaphone1 = metaphone1;
	}

	public String getMetaphone2() {
		return metaphone2;
	}

	public void setMetaphone2(String metaphone2) {
		this.metaphone2 = metaphone2;
	}

	public String getParentClassId() {
		return parentClassId;
	}

	public void setParentClassId(String parentClassId) {
		this.parentClassId = parentClassId;
	}

	public String getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
}