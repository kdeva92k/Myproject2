package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;



public class ProviderLeafMappingPK implements Serializable{


	private static final long serialVersionUID = 1L;

	private Integer userid;
	private Integer leafid;

	public ProviderLeafMappingPK(){

	}

	@Override
	public boolean equals(Object obj) {

		if(obj instanceof ProviderLeafMappingPK){

			ProviderLeafMappingPK provLeafObj= (ProviderLeafMappingPK) obj;
			if(! (provLeafObj.getUserid() == userid )){
				return false;
			}
			if(! (provLeafObj.getLeafid() == leafid )){
				return false;
			}
			return true;
		}
		return false;

	}


	@Override
	public int hashCode() {
		return userid.hashCode() + leafid.hashCode();
	}

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getLeafid() {
		return leafid;
	}
	public void setLeafid(Integer leafid) {
		this.leafid = leafid;
	}




}
