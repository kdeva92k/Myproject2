package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "macra_configuration")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MacraConfiguration {

	@Id
	@Column(name="macra_configuration_year")
	private Integer macraConfigurationYear;

	@Column(name="macra_configuration_type")
	private Integer macraConfigurationType;

	@Column(name="macra_configuration_mips")
	private Boolean macraConfigurationMips;

	@Column(name="macra_configuration_apm")
	private Boolean macraConfigurationApm;

	public Integer getMacraConfigurationYear() {
		return macraConfigurationYear;
	}

	public void setMacraConfigurationYear(Integer macraConfigurationYear) {
		this.macraConfigurationYear = macraConfigurationYear;
	}

	public Integer getMacraConfigurationType() {
		return macraConfigurationType;
	}

	public void setMacraConfigurationType(Integer macraConfigurationType) {
		this.macraConfigurationType = macraConfigurationType;
	}

	public Boolean getMacraConfigurationMips() {
		return macraConfigurationMips;
	}

	public void setMacraConfigurationMips(Boolean macraConfigurationMips) {
		this.macraConfigurationMips = macraConfigurationMips;
	}

	public Boolean getMacraConfigurationApm() {
		return macraConfigurationApm;
	}

	public void setMacraConfigurationApm(Boolean macraConfigurationApm) {
		this.macraConfigurationApm = macraConfigurationApm;
	}
	@OneToMany(mappedBy="macraConf")
	@JsonManagedReference
	private List<MacraProviderConfiguration> macraProviderConf;
	
}