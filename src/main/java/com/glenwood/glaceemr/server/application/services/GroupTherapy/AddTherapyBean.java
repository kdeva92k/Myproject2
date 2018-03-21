package com.glenwood.glaceemr.server.application.services.GroupTherapy;


public class AddTherapyBean {
	private String gwid;
	private String name;
	private Integer Type;
	private String optionsName;
	private String optionsValue;
	private String result = "";
	private Integer therapySessionPatientDetailsId;
	public AddTherapyBean(String gwid,String name,Integer Type,String optionsName,String optionsValue,Integer therapySessionPatientDetailsId){
		super();
		if(gwid!=null){
			this.gwid = gwid;
		}else{
			this.gwid = "";
		}
		if(name!=null){
			this.name = name;
		}else{
			this.name = "";
		}
		if(Type!=null){
			this.Type = Type;
		}else{
			this.Type = -1;
		}
		if(optionsName!=null){
			this.optionsName = optionsName;
		}else{
			this.optionsName = "";
		}
		if(optionsValue!=null){
			this.optionsValue = optionsValue;
		}else{
			this.optionsValue = "";
		}
		if(therapySessionPatientDetailsId!=null){
			this.therapySessionPatientDetailsId = therapySessionPatientDetailsId;
		}else{
			this.therapySessionPatientDetailsId = -1;
		}
	}
	
	public AddTherapyBean(String gwid,String name,Integer Type,String optionsName,String optionsValue,String result,Integer therapySessionPatientDetailsId){
		super();
		if(gwid!=null){
			this.gwid = gwid;
		}else{
			this.gwid = "";
		}
		if(name!=null){
			this.name = name;
		}else{
			this.name = "";
		}
		if(Type!=null){
			this.Type = Type;
		}else{
			this.Type =-1;
		}
		if(optionsName!=null){
			this.optionsName = optionsName;
		}else{
			this.optionsName = "";
		}
		if(optionsValue!=null){
			this.optionsValue = optionsValue;
		}else{
			this.optionsValue = "";
		}
		if(result!=null){
			this.result=result;
		}else{
			this.result="";
		}
		if(therapySessionPatientDetailsId!=null){
			this.therapySessionPatientDetailsId = therapySessionPatientDetailsId;
		}else{
			this.therapySessionPatientDetailsId = -1;
		}
	}
	
	
	public AddTherapyBean(String gwid,String name,Integer Type,String result,Integer therapySessionPatientDetailsId){
		super();
		if(gwid!=null){
			this.gwid = gwid;
		}else{
			this.gwid = "";
		}
		if(name!=null){
			this.name = name;
		}else{
			this.name = "";
		}
		if(Type!=null){
			this.Type = Type;
		}else{
			this.Type = -1;
		}
		if(result!=null){
			this.result=result;
		}else{
			this.result="";
		}
		if(therapySessionPatientDetailsId!=null){
			this.therapySessionPatientDetailsId = therapySessionPatientDetailsId;
		}else{
			this.therapySessionPatientDetailsId = -1;
		} 
	}
	
	public AddTherapyBean(String gwid,String name,Integer Type,Integer therapySessionPatientDetailsId){
		super();
		if(gwid!=null){
			this.gwid = gwid;
		}else{
			this.gwid = "";
		}
		if(name!=null){
			this.name = name;
		}else{
			this.name = "";
		}
		if(Type!=null){
			this.Type = Type;
		}else{
			this.Type = -1;
		}
		if(therapySessionPatientDetailsId!=null){
			this.therapySessionPatientDetailsId = therapySessionPatientDetailsId;
		}else{
			this.therapySessionPatientDetailsId = -1;
		}
	}
	public AddTherapyBean(String gwid,String name,Integer Type,String result){
		super();
		if(gwid!=null){
			this.gwid = gwid;
		}
		else{
			this.gwid = "";
		}
		if(name!=null){
			this.name = name;
		}
		else{
			this.name = "";
		}
		if(Type!=null){
			this.Type = Type;
		}else{
			this.Type = -1;
		}
		if(result!=null){
			this.result = result;
		}else{
			this.result = "";
		}
	}
	public AddTherapyBean(String gwid,String name,Integer Type,String optionsName,String optionsValue,String result){
		if(gwid!=null){
			this.gwid = gwid;
		}else{
			this.gwid = "";
		}
		if(name!=null){
			this.name = name;
		}else{
			this.name = "";
		}
		if(Type!=null){
			this.Type = Type;
		}else{
			this.Type = -1;
		}
		if(optionsName!=null){
			this.optionsName = optionsName;
		}else{
			this.optionsName = "";
		}
		if(optionsValue!=null){
			this.optionsValue = optionsValue;
		}else{
			this.optionsValue = "";
		}
		if(result!=null){
			this.result = result;
		}else{
			this.result = "";
		}
	}
	
	
	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

	public String getOptionsName() {
		return optionsName;
	}

	public void setOptionsName(String optionsName) {
		this.optionsName = optionsName;
	}

	public String getOptionsValue() {
		return optionsValue;
	}

	public void setOptionsValue(String optionsValue) {
		this.optionsValue = optionsValue;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public Integer getTherapySessionPatientDetailsId() {
		return therapySessionPatientDetailsId;
	}

	public void setTherapySessionPatientDetailsId(Integer therapySessionPatientDetailsId) {
		this.therapySessionPatientDetailsId = therapySessionPatientDetailsId;
	}

}
