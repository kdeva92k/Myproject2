package com.glenwood.glaceemr.server.application.models;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "leaf_library")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeafLibrary {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaf_library_leaf_library_id_seq")
	@SequenceGenerator(name = "leaf_library_leaf_library_id_seq", sequenceName = "leaf_library_leaf_library_id_seq", allocationSize = 1)
	@Column(name="leaf_library_id")
	private Integer leafLibraryId;

	@Column(name="leaf_library_name")
	private String leafLibraryName;

	@Column(name="leaf_library_description")
	private String leafLibraryDescription;

	@Column(name="leaf_library_group_id")
	private Integer leafLibraryGroupId;

	@Column(name="leaf_library_order")
	private Integer leafLibraryOrder;

	@Column(name="leaf_library_type")
	private Integer leafLibraryType;

	@Column(name="leaf_library_tablename")
	private String leafLibraryTablename;

	@Column(name="leaf_library_printurl")
	private String leafLibraryPrinturl;

	@Column(name="leaf_library_speciality_id")
	private Integer leafLibrarySpecialityId;

	@Column(name="leaf_library_isactive")
	private Boolean leafLibraryIsactive;

	@Column(name="leaf_library_isreport_needed")
	private Boolean leafLibraryIsreportNeeded;

	@Column(name="leaf_library_isfax_needed")
	private Boolean leafLibraryIsfaxNeeded;

	@Column(name="leaf_library_summary")
	private Boolean leafLibrarySummary;

	@Column(name="leaf_library_ref_url")
	private String leafLibraryRefUrl;

	@Column(name="leaf_library_eandm_needed")
	private Boolean leafLibraryEandmNeeded;

	@Column(name="leaf_library_word_needed")
	private Boolean leafLibraryWordNeeded;

	@Column(name="leaf_library_report_name")
	private String leafLibraryReportName;

	@Column(name="leaf_library_header")
	private Integer leafLibraryHeader;

	@Column(name="leaf_library_soaptemplate_id")
	private Integer leafLibrarySoaptemplateId;

	@Column(name="leaf_library_isprocedure")
	private Boolean leafLibraryIsprocedure;

	@Column(name="leaf_library_template_category")
	private Integer leafLibraryTemplateCategory;

	@Column(name="leaf_library_age_category")
	private Integer leafLibraryAgeCategory;

	@Column(name="leaf_library_start_age")
	private Integer leafLibraryStartAge;

	@Column(name="leaf_library_end_age")
	private Integer leafLibraryEndAge;

	@Column(name="leaf_library_header_id")
	private Integer leafLibraryHeaderId;

	@Column(name="leaf_library_return_jsp_id")
	private String leafLibraryReturnJspId;

	@Column(name="leaf_library_load_previous")
	private Boolean leafLibraryLoadPrevious;

	@Column(name="leaf_library_isconsultreport")
	private Integer leafLibraryIsconsultreport;

	@Column(name="leaf_library_display_type")
	private Integer leafLibraryDisplayType;

	@Column(name="leaf_library_notesviewneeded")
	private Integer leafLibraryNotesviewneeded;

	@Column(name="leaf_library_associatedx")
	private String leafLibraryAssociatedx;

	@Column(name="leaf_library_isagebased")
	private Boolean leafLibraryIsagebased;

	@Column(name="leaf_library_print_style_id")
	private Integer leafLibraryPrintStyleId;

	public Integer getLeafLibraryPrintStyleId() {
		return leafLibraryPrintStyleId;
	}

	public void setLeafLibraryPrintStyleId(Integer leafLibraryPrintStyleId) {
		this.leafLibraryPrintStyleId = leafLibraryPrintStyleId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "leaf_library_group_id", referencedColumnName = "leaf_group_id", insertable = false, updatable = false)
	private LeafGroup leafGroup;
	
	@OneToMany(mappedBy="leafLibrary")
	List<ProviderLeafMapping> providerLeafMappings;
	
	@OneToMany(mappedBy = "leafLibraryTable")
	List<LeafPatient> leafPatients;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "leaf_library_name", referencedColumnName = "template_details_name", insertable = false, updatable = false)
	private TemplateDetails template_details;
	
	
	@OneToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="leaf_library_id", referencedColumnName="leaf_conf_data_leafid" , insertable=false, updatable=false)
	private LeafConfData leaf_conf_data;
	
	public List<LeafPatient> getLeafPatients() {
		return leafPatients;
	}

	public void setLeafPatients(List<LeafPatient> leafPatients) {
		this.leafPatients = leafPatients;
	}

	public LeafConfData getleaf_conf_data() {
		return leaf_conf_data;
	}

	public void setleaf_conf_data(LeafConfData leaf_conf_data) {
		this.leaf_conf_data = leaf_conf_data;
	}

	public LeafGroup getLeafGroup() {
		return leafGroup;
	}

	public void setLeafGroup(LeafGroup leafGroup) {
		this.leafGroup = leafGroup;
	}

	public List<ProviderLeafMapping> getProviderLeafMappings() {
		return providerLeafMappings;
	}

	public void setProviderLeafMappings(
			List<ProviderLeafMapping> providerLeafMappings) {
		this.providerLeafMappings = providerLeafMappings;
	}

	public TemplateDetails gettemplate_details() {
		return template_details;
	}

	public void settemplate_details(TemplateDetails template_details) {
		this.template_details = template_details;
	}
	
	public Integer getLeafLibraryId() {
		return leafLibraryId;
	}

	public void setLeafLibraryId(Integer leafLibraryId) {
		this.leafLibraryId = leafLibraryId;
	}

	public String getLeafLibraryName() {
		return leafLibraryName;
	}

	public void setLeafLibraryName(String leafLibraryName) {
		this.leafLibraryName = leafLibraryName;
	}

	public String getLeafLibraryDescription() {
		return leafLibraryDescription;
	}

	public void setLeafLibraryDescription(String leafLibraryDescription) {
		this.leafLibraryDescription = leafLibraryDescription;
	}

	public Integer getLeafLibraryGroupId() {
		return leafLibraryGroupId;
	}

	public void setLeafLibraryGroupId(Integer leafLibraryGroupId) {
		this.leafLibraryGroupId = leafLibraryGroupId;
	}

	public Integer getLeafLibraryOrder() {
		return leafLibraryOrder;
	}

	public void setLeafLibraryOrder(Integer leafLibraryOrder) {
		this.leafLibraryOrder = leafLibraryOrder;
	}

	public Integer getLeafLibraryType() {
		return leafLibraryType;
	}

	public void setLeafLibraryType(Integer leafLibraryType) {
		this.leafLibraryType = leafLibraryType;
	}

	public String getLeafLibraryTablename() {
		return leafLibraryTablename;
	}

	public void setLeafLibraryTablename(String leafLibraryTablename) {
		this.leafLibraryTablename = leafLibraryTablename;
	}

	public String getLeafLibraryPrinturl() {
		return leafLibraryPrinturl;
	}

	public void setLeafLibraryPrinturl(String leafLibraryPrinturl) {
		this.leafLibraryPrinturl = leafLibraryPrinturl;
	}

	public Integer getLeafLibrarySpecialityId() {
		return leafLibrarySpecialityId;
	}

	public void setLeafLibrarySpecialityId(Integer leafLibrarySpecialityId) {
		this.leafLibrarySpecialityId = leafLibrarySpecialityId;
	}

	public Boolean getLeafLibraryIsactive() {
		return leafLibraryIsactive;
	}

	public void setLeafLibraryIsactive(Boolean leafLibraryIsactive) {
		this.leafLibraryIsactive = leafLibraryIsactive;
	}

	public Boolean getLeafLibraryIsreportNeeded() {
		return leafLibraryIsreportNeeded;
	}

	public void setLeafLibraryIsreportNeeded(Boolean leafLibraryIsreportNeeded) {
		this.leafLibraryIsreportNeeded = leafLibraryIsreportNeeded;
	}

	public Boolean getLeafLibraryIsfaxNeeded() {
		return leafLibraryIsfaxNeeded;
	}

	public void setLeafLibraryIsfaxNeeded(Boolean leafLibraryIsfaxNeeded) {
		this.leafLibraryIsfaxNeeded = leafLibraryIsfaxNeeded;
	}

	public Boolean getLeafLibrarySummary() {
		return leafLibrarySummary;
	}

	public void setLeafLibrarySummary(Boolean leafLibrarySummary) {
		this.leafLibrarySummary = leafLibrarySummary;
	}

	public String getLeafLibraryRefUrl() {
		return leafLibraryRefUrl;
	}

	public void setLeafLibraryRefUrl(String leafLibraryRefUrl) {
		this.leafLibraryRefUrl = leafLibraryRefUrl;
	}

	public Boolean getLeafLibraryEandmNeeded() {
		return leafLibraryEandmNeeded;
	}

	public void setLeafLibraryEandmNeeded(Boolean leafLibraryEandmNeeded) {
		this.leafLibraryEandmNeeded = leafLibraryEandmNeeded;
	}

	public Boolean getLeafLibraryWordNeeded() {
		return leafLibraryWordNeeded;
	}

	public void setLeafLibraryWordNeeded(Boolean leafLibraryWordNeeded) {
		this.leafLibraryWordNeeded = leafLibraryWordNeeded;
	}

	public String getLeafLibraryReportName() {
		return leafLibraryReportName;
	}

	public void setLeafLibraryReportName(String leafLibraryReportName) {
		this.leafLibraryReportName = leafLibraryReportName;
	}

	public Integer getLeafLibraryHeader() {
		return leafLibraryHeader;
	}

	public void setLeafLibraryHeader(Integer leafLibraryHeader) {
		this.leafLibraryHeader = leafLibraryHeader;
	}

	public Integer getLeafLibrarySoaptemplateId() {
		return leafLibrarySoaptemplateId;
	}

	public void setLeafLibrarySoaptemplateId(Integer leafLibrarySoaptemplateId) {
		this.leafLibrarySoaptemplateId = leafLibrarySoaptemplateId;
	}

	public Boolean getLeafLibraryIsprocedure() {
		return leafLibraryIsprocedure;
	}

	public void setLeafLibraryIsprocedure(Boolean leafLibraryIsprocedure) {
		this.leafLibraryIsprocedure = leafLibraryIsprocedure;
	}

	public Integer getLeafLibraryTemplateCategory() {
		return leafLibraryTemplateCategory;
	}

	public void setLeafLibraryTemplateCategory(Integer leafLibraryTemplateCategory) {
		this.leafLibraryTemplateCategory = leafLibraryTemplateCategory;
	}

	public Integer getLeafLibraryAgeCategory() {
		return leafLibraryAgeCategory;
	}

	public void setLeafLibraryAgeCategory(Integer leafLibraryAgeCategory) {
		this.leafLibraryAgeCategory = leafLibraryAgeCategory;
	}

	public Integer getLeafLibraryStartAge() {
		return leafLibraryStartAge;
	}

	public void setLeafLibraryStartAge(Integer leafLibraryStartAge) {
		this.leafLibraryStartAge = leafLibraryStartAge;
	}

	public Integer getLeafLibraryEndAge() {
		return leafLibraryEndAge;
	}

	public void setLeafLibraryEndAge(Integer leafLibraryEndAge) {
		this.leafLibraryEndAge = leafLibraryEndAge;
	}

	public Integer getLeafLibraryHeaderId() {
		return leafLibraryHeaderId;
	}

	public void setLeafLibraryHeaderId(Integer leafLibraryHeaderId) {
		this.leafLibraryHeaderId = leafLibraryHeaderId;
	}

	public String getLeafLibraryReturnJspId() {
		return leafLibraryReturnJspId;
	}

	public void setLeafLibraryReturnJspId(String leafLibraryReturnJspId) {
		this.leafLibraryReturnJspId = leafLibraryReturnJspId;
	}

	public Boolean getLeafLibraryLoadPrevious() {
		return leafLibraryLoadPrevious;
	}

	public void setLeafLibraryLoadPrevious(Boolean leafLibraryLoadPrevious) {
		this.leafLibraryLoadPrevious = leafLibraryLoadPrevious;
	}

	public Integer getLeafLibraryIsconsultreport() {
		return leafLibraryIsconsultreport;
	}

	public void setLeafLibraryIsconsultreport(Integer leafLibraryIsconsultreport) {
		this.leafLibraryIsconsultreport = leafLibraryIsconsultreport;
	}

	public Integer getLeafLibraryDisplayType() {
		return leafLibraryDisplayType;
	}

	public void setLeafLibraryDisplayType(Integer leafLibraryDisplayType) {
		this.leafLibraryDisplayType = leafLibraryDisplayType;
	}

	public Integer getLeafLibraryNotesviewneeded() {
		return leafLibraryNotesviewneeded;
	}

	public void setLeafLibraryNotesviewneeded(Integer leafLibraryNotesviewneeded) {
		this.leafLibraryNotesviewneeded = leafLibraryNotesviewneeded;
	}

	public String getLeafLibraryAssociatedx() {
		return leafLibraryAssociatedx;
	}

	public void setLeafLibraryAssociatedx(String leafLibraryAssociatedx) {
		this.leafLibraryAssociatedx = leafLibraryAssociatedx;
	}

	public Boolean getLeafLibraryIsagebased() {
		return leafLibraryIsagebased;
	}

	public void setLeafLibraryIsagebased(Boolean leafLibraryIsagebased) {
		this.leafLibraryIsagebased = leafLibraryIsagebased;
	}
	
	
}