package com.glenwood.glaceemr.server.application.services.chart.Allergies;

import java.util.ArrayList;
import java.util.List;

public class AllergyBean {
	private Integer countValue;
	private List<AllergySearchBean> allergySearchBean;
	private List<String> reactionName;
	public  AllergyBean(){
		countValue=-1;
		allergySearchBean=new ArrayList<AllergySearchBean>();
		reactionName = new ArrayList<String>();
	}
	public Integer getCountValue() {
		return countValue;
	}
	public void setCountValue(Integer countValue) {
		this.countValue = countValue;
	}
	
	public List<AllergySearchBean> getAllergySearchBean() {
		return allergySearchBean;
	}
	public void setAllergySearchBean(List<AllergySearchBean> allergySearchBean) {
		this.allergySearchBean = allergySearchBean;
	}
	public List<String> getReactionName() {
		return reactionName;
	}
	public void setReactionName(List<String> reactionName) {
		this.reactionName = reactionName;
	}
	
}
