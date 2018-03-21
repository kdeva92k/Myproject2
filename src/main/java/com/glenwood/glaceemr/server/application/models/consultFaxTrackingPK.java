package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

public class consultFaxTrackingPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer encounterid;
	private Integer leaf_id;
	private Integer fax_id;
	
	public consultFaxTrackingPK(){
		
	}
	
	
	@Override
	public boolean equals(Object obj) {

		if(obj instanceof consultFaxTrackingPK){

			consultFaxTrackingPK consultFaxTrackObj= (consultFaxTrackingPK) obj;
			
			if(! (consultFaxTrackObj.getEncounterid() == encounterid )){
				return false;
			}
			if(! (consultFaxTrackObj.getLeafid() == leaf_id )){
				return false;
			}
			if(! (consultFaxTrackObj.getFaxid() == fax_id )){
				return false;
			}
			return true;
		}
		return false;

	}


	@Override
	public int hashCode() {
		return encounterid.hashCode() + leaf_id.hashCode() + fax_id.hashCode();
	}
	
	public Integer getLeafid() {
		return leaf_id;
	}
	public void setLeafid(Integer leaf_id) {
		this.leaf_id = leaf_id;
	}
	
	public Integer getFaxid() {
		return fax_id;
	}
	public void setFaxid(Integer fax_id) {
		this.fax_id = fax_id;
	}
	
	public Integer getEncounterid() {
		return encounterid;
	}
	public void setEncounterid(Integer encounterid) {
		this.encounterid = encounterid;
	}
	
	
	
	
	

}
