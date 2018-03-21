package com.glenwood.glaceemr.server.application.services.chart.vitals;

import java.util.ArrayList;
import java.util.List;


public class ElementDetails {
	
	
	List<AssociatedElementDetails> associatedElements=new ArrayList<AssociatedElementDetails>();
	
	/*
	 @Deprecated
	    public ElementDetails() {
	       this.associatedElements = Collections.emptyList();
	    }
	*/
	
	/*ElementDetails(){
		associatedElements=new ArrayList<AssociatedElementDetails>();
	}*/
	
	public List<AssociatedElementDetails> getAssociatedElements() {
		return associatedElements;
	}
	
	public void setAssociatedElements(
			List<AssociatedElementDetails> associatedElements) {
		this.associatedElements = associatedElements;
	}
}
