package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.ArrayList;
import java.util.List;

public class Service {
	
	private String cpt = new String();
	private List<String> modifierList = new ArrayList<String>();
	private List<String> dxList = new ArrayList<String>();
	
	public String getCpt() {
		return cpt;
	}
	
	public void setCpt(String cpt) {
		this.cpt = cpt;
	}
	
	public List<String> getModifierList() {
		return modifierList;
	}
	
	public void setModifierList(List<String> modifierList) {
		this.modifierList = modifierList;
	}
	
	public List<String> getDxList() {
		return dxList;
	}
	
	public void setDxList(List<String> dxList) {
		this.dxList = dxList;
	}
	
	
	public Service(String cpt, List<String> modifierList, List<String> dxList) {
		super();
		this.cpt = cpt;
		this.modifierList = modifierList;
		this.dxList = dxList;
	}
	
	public Service() {
		super();
	}
	
	
}
