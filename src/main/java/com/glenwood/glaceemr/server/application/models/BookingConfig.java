package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking_config")
public class BookingConfig {
	

	@Id
	@Column(name="booking_config_id")
	private Integer bookingConfigId;

	@Column(name="booking_config_limit_timeField")
	private String bookingConfigLimitTimefield;

	@Column(name="booking_config_maxFutureDays")
	private Integer bookingConfigMaxFutureDays;
	
	@Column(name="booking_config_isRestricted")
	private boolean bookingConfigIsRestricted;

	
	public Integer getBookingConfigId() {
		return bookingConfigId;
	}

	public void setBookingConfigId(Integer bookingConfigId) {
		this.bookingConfigId = bookingConfigId;
	}

	public String getBookingConfigLimitTimefield() {
		return bookingConfigLimitTimefield;
	}

	public void setBookingConfigLimitTimefield(String bookingConfigLimitTimefield) {
		this.bookingConfigLimitTimefield = bookingConfigLimitTimefield;
	}

	public Integer getBookingConfigMaxFutureDays() {
		return bookingConfigMaxFutureDays;
	}

	public void setBookingConfigMaxFutureDays(Integer bookingConfigMaxFutureDays) {
		this.bookingConfigMaxFutureDays = bookingConfigMaxFutureDays;
	}
	
	public boolean isBookingConfigIsRestricted() {
		return bookingConfigIsRestricted;
	}

	public void setBookingConfigIsRestricted(boolean bookingConfigIsRestricted) {
		this.bookingConfigIsRestricted = bookingConfigIsRestricted;
	}


}

