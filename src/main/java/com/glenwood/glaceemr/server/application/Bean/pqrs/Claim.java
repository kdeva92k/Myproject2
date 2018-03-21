package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Claim implements Comparable<Claim>{
	private Date startDate = new Date();
	private Date endDate = new Date();
	private int providerId;
	private String pos = new String();
	private List<Service> services = new ArrayList<Service>();


	public Claim(Date startDate, Date endDate, int providerId, String pos, List<Service> services) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.providerId = providerId;
		this.pos = pos;
		this.services = services;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getProviderId() {
		return providerId;
	}
	
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	
	public String getPos() {
		return pos;
	}
	
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public List<Service> getServices() {
		return services;
	}
	
	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	@Override
	public int compareTo(Claim o) {
		return o.getStartDate().compareTo(this.getStartDate());
	}	
}

