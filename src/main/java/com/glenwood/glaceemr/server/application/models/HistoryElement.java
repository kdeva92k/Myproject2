package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalSystem;

/**
 * @author Software
 *
 */
@Entity
@Table(name = "history_element")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryElement implements Serializable{
	
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="historyElementSeqGenerator")
	@SequenceGenerator(name ="historyElementSeqGenerator", sequenceName="history_element_history_element_id_seq", allocationSize=1)
	@Column(name="history_element_id")
	private Integer historyElementId;

	@Column(name="history_element_name")
	private String historyElementName;

	@Column(name="history_element_display_name")
	private String historyElementDisplayName;

	@Column(name="history_element_history_type_id")
	private Integer historyElementHistoryTypeId;
	
	@Id
	@Column(name="history_element_gwid")
	private String historyElementGwid;

	@Column(name="history_element_isactive")
	private Boolean historyElementIsactive;
	
	
	@Column(name="history_element_orderby")
	private Integer historyElementOrderBy;
	
	
	@Column(name="history_element_printtext")
	private String historyElementPrinttext;
	
	
	@Column(name="history_element_system_id")
	private Integer historyElementSystemId;

	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="history_element_system_id", referencedColumnName="clinical_system_hx_gwid", insertable=false, updatable=false)
	@JsonManagedReference
	ClinicalSystem clinicalSystem;
	
	@OneToMany(mappedBy="historyElement2",fetch=FetchType.LAZY)
	@JsonManagedReference
	List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping;
	
	/*@OneToMany(mappedBy="historyElement1")
	private ClinicalTextMapping clinicalTextMapping;


	public ClinicalTextMapping getClinicalTextMapping() {
		return clinicalTextMapping;
	}


	public void setClinicalTextMapping(ClinicalTextMapping clinicalTextMapping) {
		this.clinicalTextMapping = clinicalTextMapping;
	}
*/

	

	public ClinicalSystem getClinicalSystem() {
		return clinicalSystem;
	}


	public void setClinicalSystem(ClinicalSystem clinicalSystem) {
		this.clinicalSystem = clinicalSystem;
	}


	public List<ClinicalElementTemplateMapping> getClinicalElementTemplateMapping() {
		return clinicalElementTemplateMapping;
	}


	public void setClinicalElementTemplateMapping(
			List<ClinicalElementTemplateMapping> clinicalElementTemplateMapping) {
		this.clinicalElementTemplateMapping = clinicalElementTemplateMapping;
	}

	
	public Integer getHistoryElementId() {
		return historyElementId;
	}


	public void setHistoryElementId(Integer historyElementId) {
		this.historyElementId = historyElementId;
	}


	public String getHistoryElementName() {
		return historyElementName;
	}


	public void setHistoryElementName(String historyElementName) {
		this.historyElementName = historyElementName;
	}


	public String getHistoryElementDisplayName() {
		return historyElementDisplayName;
	}


	public void setHistoryElementDisplayName(String historyElementDisplayName) {
		this.historyElementDisplayName = historyElementDisplayName;
	}


	public Integer getHistoryElementHistoryTypeId() {
		return historyElementHistoryTypeId;
	}


	public void setHistoryElementHistoryTypeId(Integer historyElementHistoryTypeId) {
		this.historyElementHistoryTypeId = historyElementHistoryTypeId;
	}


	public String getHistoryElementGwid() {
		return historyElementGwid;
	}


	public void setHistoryElementGwid(String historyElementGwid) {
		this.historyElementGwid = historyElementGwid;
	}


	public Boolean getHistoryElementIsactive() {
		return historyElementIsactive;
	}


	public void setHistoryElementIsactive(Boolean historyElementIsactive) {
		this.historyElementIsactive = historyElementIsactive;
	}


	public Integer getHistoryElementOrderBy() {
		return historyElementOrderBy;
	}


	public void setHistoryElementOrderBy(Integer historyElementOrderBy) {
		this.historyElementOrderBy = historyElementOrderBy;
	}


	public String getHistoryElementPrinttext() {
		return historyElementPrinttext;
	}


	public void setHistoryElementPrinttext(String historyElementPrinttext) {
		this.historyElementPrinttext = historyElementPrinttext;
	}


	public Integer getHistoryElementSystemId() {
		return historyElementSystemId;
	}


	public void setHistoryElementSystemId(Integer historyElementSystemId) {
		this.historyElementSystemId = historyElementSystemId;
	}
	
	
	
	
}
