package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "chart_status")
public class ChartStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="chart_status_id")
	private Integer chart_status_id;

	@Column(name="chart_status_status_id")
	private Integer chart_status_status_id;

	@Column(name="chart_status_status_name")
	private String chart_status_status_name;

	@Column(name="chart_status_description")
	private String chart_status_description;

	@Column(name="chart_status_reference_id")
	private Integer chart_status_reference_id;

	@Column(name="chart_status_color")
	private String chart_status_color;

	@Column(name="chart_status_display_order")
	private Integer chart_status_display_order;
	
	public Integer getchart_status_id() {
		return chart_status_id;
	}

	public void setchart_status_id(Integer chart_status_id) {
		this.chart_status_id = chart_status_id;
	}

	public Integer getchart_status_status_id() {
		return chart_status_status_id;
	}

	public void setchart_status_status_id(Integer chart_status_status_id) {
		this.chart_status_status_id = chart_status_status_id;
	}

	public String getchart_status_status_name() {
		return chart_status_status_name;
	}

	public void setchart_status_status_name(String chart_status_status_name) {
		this.chart_status_status_name = chart_status_status_name;
	}

	public String getchart_status_description() {
		return chart_status_description;
	}

	public void setchart_status_description(String chart_status_description) {
		this.chart_status_description = chart_status_description;
	}

	public Integer getchart_status_reference_id() {
		return chart_status_reference_id;
	}

	public void setchart_status_reference_id(Integer chart_status_reference_id) {
		this.chart_status_reference_id = chart_status_reference_id;
	}

	public String getchart_status_color() {
		return chart_status_color;
	}

	public void setchart_status_color(String chart_status_color) {
		this.chart_status_color = chart_status_color;
	}

	public Integer getchart_status_display_order() {
		return chart_status_display_order;
	}

	public void setchart_status_display_order(Integer chart_status_display_order) {
		this.chart_status_display_order = chart_status_display_order;
	}
}