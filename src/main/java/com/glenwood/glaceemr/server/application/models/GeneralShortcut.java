package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "general_shortcut")
public class GeneralShortcut {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="public.general_shortcut_general_shortcut_id_seq")
	@SequenceGenerator(name ="public.general_shortcut_general_shortcut_id_seq", sequenceName="public.general_shortcut_general_shortcut_id_seq", allocationSize=1)
	@Column(name="general_shortcut_id")
	private Integer generalShortcutId;

	@Column(name="general_shortcut_code")
	private String generalShortcutCode;

	@Column(name="general_shortcut_description")
	private String generalShortcutDescription;

	@Column(name="general_shortcut_map_group_id")
	private Integer generalShortcutMapGroupId;

	@Column(name="general_shortcut_isactive")
	private Boolean generalShortcutIsactive;

	/*@Column(name="general_shortcut_parent_id")
	private Integer generalShortcutParentId;*/

	public Integer getGeneralShortcutId() {
		return generalShortcutId;
	}

	public void setGeneralShortcutId(Integer generalShortcutId) {
		this.generalShortcutId = generalShortcutId;
	}

	public String getGeneralShortcutCode() {
		return generalShortcutCode;
	}

	public void setGeneralShortcutCode(String generalShortcutCode) {
		this.generalShortcutCode = generalShortcutCode;
	}

	public String getGeneralShortcutDescription() {
		return generalShortcutDescription;
	}

	public void setGeneralShortcutDescription(String generalShortcutDescription) {
		this.generalShortcutDescription = generalShortcutDescription;
	}

	public Integer getGeneralShortcutMapGroupId() {
		return generalShortcutMapGroupId;
	}

	public void setGeneralShortcutMapGroupId(Integer generalShortcutMapGroupId) {
		this.generalShortcutMapGroupId = generalShortcutMapGroupId;
	}

	public Boolean getGeneralShortcutIsactive() {
		return generalShortcutIsactive;
	}

	public void setGeneralShortcutIsactive(Boolean generalShortcutIsactive) {
		this.generalShortcutIsactive = generalShortcutIsactive;
	}

/*	public Integer getGeneralShortcutParentId() {
		return generalShortcutParentId;
	}

	public void setGeneralShortcutParentId(Integer generalShortcutParentId) {
		this.generalShortcutParentId = generalShortcutParentId;
	}
	*/
	
}