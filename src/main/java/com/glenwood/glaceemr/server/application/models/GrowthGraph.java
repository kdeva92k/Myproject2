package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "growthgraph")
public class GrowthGraph {

	@Id
	@Column(name="growthgraph_id")
	private Integer growthgraph_id;

	@Column(name="growthgraph_category")
	private String growthgraph_category;

	
	@Column(name="growthgraph_gender")
	private String growthgraph_gender;

	@Column(name="growthgraph_age_range_from")
	private Integer growthgraph_age_range_from;

	@Column(name="growthgraph_age_range_to")
	private Integer growthgraph_age_range_to;

	@Column(name="growthgraph_graph_image_name")
	private String growthgraph_graph_image_name;

	@Column(name="growthgraph_order_by")
	private Integer growthgraph_order_by;

	@Column(name="growthgraph_bgimg_opacity")
	private Double growthgraph_bgimg_opacity;

	@Column(name="growthgraph_top_graph_cirrad")
	private Integer growthgraph_top_graph_cirrad;

	@Column(name="growthgraph_bottom_graph_cirrad")
	private Integer growthgraph_bottom_graph_cirrad;

	@Column(name="growthgraph_top_graph_circolor")
	private String growthgraph_top_graph_circolor;

	@Column(name="growthgraph_bottom_graph_circolor")
	private String growthgraph_bottom_graph_circolor;

	@Column(name="growthgraph_top_graph_linewidth")
	private Integer growthgraph_top_graph_linewidth;

	@Column(name="growthgraph_bottom_graph_linewidth")
	private Integer growthgraph_bottom_graph_linewidth;

	@Column(name="growthgraph_top_graph_linecolor")
	private String growthgraph_top_graph_linecolor;

	@Column(name="growthgraph_bottom_graph_linecolor")
	private String growthgraph_bottom_graph_linecolor;

	@Column(name="growthgraph_graph_is_active")
	private Boolean growthgraph_graph_is_active;

	@Column(name="growthgraph_total_vitals_row")
	private Integer growthgraph_total_vitals_row;

	public Integer getgrowthgraph_id() {
		return growthgraph_id;
	}

	public void setgrowthgraph_id(Integer growthgraph_id) {
		this.growthgraph_id = growthgraph_id;
	}

	public String getgrowthgraph_category() {
		return growthgraph_category;
	}

	public void setgrowthgraph_category(String growthgraph_category) {
		this.growthgraph_category = growthgraph_category;
	}

	public String getgrowthgraph_gender() {
		return growthgraph_gender;
	}

	public void setgrowthgraph_gender(String growthgraph_gender) {
		this.growthgraph_gender = growthgraph_gender;
	}

	public Integer getgrowthgraph_age_range_from() {
		return growthgraph_age_range_from;
	}

	public void setgrowthgraph_age_range_from(Integer growthgraph_age_range_from) {
		this.growthgraph_age_range_from = growthgraph_age_range_from;
	}

	public Integer getgrowthgraph_age_range_to() {
		return growthgraph_age_range_to;
	}

	public void setgrowthgraph_age_range_to(Integer growthgraph_age_range_to) {
		this.growthgraph_age_range_to = growthgraph_age_range_to;
	}

	public String getgrowthgraph_graph_image_name() {
		return growthgraph_graph_image_name;
	}

	public void setgrowthgraph_graph_image_name(String growthgraph_graph_image_name) {
		this.growthgraph_graph_image_name = growthgraph_graph_image_name;
	}

	public Integer getgrowthgraph_order_by() {
		return growthgraph_order_by;
	}

	public void setgrowthgraph_order_by(Integer growthgraph_order_by) {
		this.growthgraph_order_by = growthgraph_order_by;
	}

	public Double getgrowthgraph_bgimg_opacity() {
		return growthgraph_bgimg_opacity;
	}

	public void setgrowthgraph_bgimg_opacity(Double growthgraph_bgimg_opacity) {
		this.growthgraph_bgimg_opacity = growthgraph_bgimg_opacity;
	}

	public Integer getgrowthgraph_top_graph_cirrad() {
		return growthgraph_top_graph_cirrad;
	}

	public void setgrowthgraph_top_graph_cirrad(Integer growthgraph_top_graph_cirrad) {
		this.growthgraph_top_graph_cirrad = growthgraph_top_graph_cirrad;
	}

	public Integer getgrowthgraph_bottom_graph_cirrad() {
		return growthgraph_bottom_graph_cirrad;
	}

	public void setgrowthgraph_bottom_graph_cirrad(Integer growthgraph_bottom_graph_cirrad) {
		this.growthgraph_bottom_graph_cirrad = growthgraph_bottom_graph_cirrad;
	}

	public String getgrowthgraph_top_graph_circolor() {
		return growthgraph_top_graph_circolor;
	}

	public void setgrowthgraph_top_graph_circolor(String growthgraph_top_graph_circolor) {
		this.growthgraph_top_graph_circolor = growthgraph_top_graph_circolor;
	}

	public String getgrowthgraph_bottom_graph_circolor() {
		return growthgraph_bottom_graph_circolor;
	}

	public void setgrowthgraph_bottom_graph_circolor(String growthgraph_bottom_graph_circolor) {
		this.growthgraph_bottom_graph_circolor = growthgraph_bottom_graph_circolor;
	}

	public Integer getgrowthgraph_top_graph_linewidth() {
		return growthgraph_top_graph_linewidth;
	}

	public void setgrowthgraph_top_graph_linewidth(Integer growthgraph_top_graph_linewidth) {
		this.growthgraph_top_graph_linewidth = growthgraph_top_graph_linewidth;
	}

	public Integer getgrowthgraph_bottom_graph_linewidth() {
		return growthgraph_bottom_graph_linewidth;
	}

	public void setgrowthgraph_bottom_graph_linewidth(Integer growthgraph_bottom_graph_linewidth) {
		this.growthgraph_bottom_graph_linewidth = growthgraph_bottom_graph_linewidth;
	}

	public String getgrowthgraph_top_graph_linecolor() {
		return growthgraph_top_graph_linecolor;
	}

	public void setgrowthgraph_top_graph_linecolor(String growthgraph_top_graph_linecolor) {
		this.growthgraph_top_graph_linecolor = growthgraph_top_graph_linecolor;
	}

	public String getgrowthgraph_bottom_graph_linecolor() {
		return growthgraph_bottom_graph_linecolor;
	}

	public void setgrowthgraph_bottom_graph_linecolor(String growthgraph_bottom_graph_linecolor) {
		this.growthgraph_bottom_graph_linecolor = growthgraph_bottom_graph_linecolor;
	}

	public Boolean getgrowthgraph_graph_is_active() {
		return growthgraph_graph_is_active;
	}

	public void setgrowthgraph_graph_is_active(Boolean growthgraph_graph_is_active) {
		this.growthgraph_graph_is_active = growthgraph_graph_is_active;
	}

	public Integer getgrowthgraph_total_vitals_row() {
		return growthgraph_total_vitals_row;
	}

	public void setgrowthgraph_total_vitals_row(Integer growthgraph_total_vitals_row) {
		this.growthgraph_total_vitals_row = growthgraph_total_vitals_row;
	}
}