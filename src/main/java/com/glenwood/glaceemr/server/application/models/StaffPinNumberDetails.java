package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "staff_pin_number_details")
public class StaffPinNumberDetails {
	@Id
	@Column(name="staff_pin_number_details_profileid")
	private Integer staff_pin_number_details_profileid;

	@Column(name="staff_pin_number_details_upin_number")
	private String staff_pin_number_details_upin_number;

	@Column(name="staff_pin_number_details_mpin_number")
	private String staff_pin_number_details_mpin_number;

	@Column(name="staff_pin_number_details_bcpin_number")
	private String staff_pin_number_details_bcpin_number;

	@Column(name="staff_pin_number_details_licno")
	private String staff_pin_number_details_licno;

	@Column(name="staff_pin_number_details_cctpin_number")
	private String staff_pin_number_details_cctpin_number;

	public Integer getstaff_pin_number_details_profileid() {
		return staff_pin_number_details_profileid;
	}

	public void setstaff_pin_number_details_profileid(Integer staff_pin_number_details_profileid) {
		this.staff_pin_number_details_profileid = staff_pin_number_details_profileid;
	}

	public String getstaff_pin_number_details_upin_number() {
		return staff_pin_number_details_upin_number;
	}

	public void setstaff_pin_number_details_upin_number(String staff_pin_number_details_upin_number) {
		this.staff_pin_number_details_upin_number = staff_pin_number_details_upin_number;
	}

	public String getstaff_pin_number_details_mpin_number() {
		return staff_pin_number_details_mpin_number;
	}

	public void setstaff_pin_number_details_mpin_number(String staff_pin_number_details_mpin_number) {
		this.staff_pin_number_details_mpin_number = staff_pin_number_details_mpin_number;
	}

	public String getstaff_pin_number_details_bcpin_number() {
		return staff_pin_number_details_bcpin_number;
	}

	public void setstaff_pin_number_details_bcpin_number(String staff_pin_number_details_bcpin_number) {
		this.staff_pin_number_details_bcpin_number = staff_pin_number_details_bcpin_number;
	}

	public String getstaff_pin_number_details_licno() {
		return staff_pin_number_details_licno;
	}

	public void setstaff_pin_number_details_licno(String staff_pin_number_details_licno) {
		this.staff_pin_number_details_licno = staff_pin_number_details_licno;
	}

	public String getstaff_pin_number_details_cctpin_number() {
		return staff_pin_number_details_cctpin_number;
	}

	public void setstaff_pin_number_details_cctpin_number(String staff_pin_number_details_cctpin_number) {
		this.staff_pin_number_details_cctpin_number = staff_pin_number_details_cctpin_number;
	}

	public String getstaff_pin_number_details_cliapin_number() {
		return staff_pin_number_details_cliapin_number;
	}

	public void setstaff_pin_number_details_cliapin_number(String staff_pin_number_details_cliapin_number) {
		this.staff_pin_number_details_cliapin_number = staff_pin_number_details_cliapin_number;
	}

	public String getstaff_pin_number_details_gnpino() {
		return staff_pin_number_details_gnpino;
	}

	public void setstaff_pin_number_details_gnpino(String staff_pin_number_details_gnpino) {
		this.staff_pin_number_details_gnpino = staff_pin_number_details_gnpino;
	}

	public String getstaff_pin_number_details_smedino() {
		return staff_pin_number_details_smedino;
	}

	public void setstaff_pin_number_details_smedino(String staff_pin_number_details_smedino) {
		this.staff_pin_number_details_smedino = staff_pin_number_details_smedino;
	}

	@Column(name="staff_pin_number_details_cliapin_number")
	private String staff_pin_number_details_cliapin_number;

	@Column(name="staff_pin_number_details_gnpino")
	private String staff_pin_number_details_gnpino;

	@Column(name="staff_pin_number_details_smedino")
	private String staff_pin_number_details_smedino;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="staffPinNumberDetails")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	List<MacraProviderConfiguration> macraProviderConfiguration;
	
}