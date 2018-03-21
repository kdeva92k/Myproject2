package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ros_element")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RosElement implements Serializable{

	
	private static final long serialVersionUID = -4620663540752914167L;

	@Id
	@Column(name="ros_element_id")
	private Integer rosElementId;

	@Column(name="ros_element_name")
	private String rosElementName;

	@Column(name="ros_element_printtext")
	private String rosElementPrinttext;

	@Column(name="ros_element_system_id")
	private Integer rosElementSystemId;

	@Column(name="ros_element_orderby")
	private Integer rosElementOrderby;

	@Column(name="ros_element_gwid")
	private String rosElementGwid;

	@Column(name="ros_element_isactive")
	private Boolean rosElementIsactive;

	
	/*@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name = "ros_element_system_id", referencedColumnName = "clinical_system_ros_gwid", insertable = false, updatable = false)
	private ClinicalSystem clinicalSystem;*/
	
	
	public Integer getRosElementId() {
		return rosElementId;
	}

	public void setRosElementId(Integer rosElementId) {
		this.rosElementId = rosElementId;
	}

	public String getRosElementName() {
		return rosElementName;
	}

	public void setRosElementName(String rosElementName) {
		this.rosElementName = rosElementName;
	}

	public String getRosElementPrinttext() {
		return rosElementPrinttext;
	}

	public void setRosElementPrinttext(String rosElementPrinttext) {
		this.rosElementPrinttext = rosElementPrinttext;
	}

	public Integer getRosElementSystemId() {
		return rosElementSystemId;
	}

	public void setRosElementSystemId(Integer rosElementSystemId) {
		this.rosElementSystemId = rosElementSystemId;
	}

	public Integer getRosElementOrderby() {
		return rosElementOrderby;
	}

	public void setRosElementOrderby(Integer rosElementOrderby) {
		this.rosElementOrderby = rosElementOrderby;
	}

	public String getRosElementGwid() {
		return rosElementGwid;
	}

	public void setRosElementGwid(String rosElementGwid) {
		this.rosElementGwid = rosElementGwid;
	}

	public Boolean getRosElementIsactive() {
		return rosElementIsactive;
	}

	public void setRosElementIsactive(Boolean rosElementIsactive) {
		this.rosElementIsactive = rosElementIsactive;
	}
	
	@OneToMany(mappedBy="rosElement")
	List<ClinicalTextMapping> clinicalTextMapping;

	public List<ClinicalTextMapping> getClinicalTextMapping() {
		return clinicalTextMapping;
	}

	public void setClinicalTextMapping(List<ClinicalTextMapping> clinicalTextMapping) {
		this.clinicalTextMapping = clinicalTextMapping;
	}
}