package com.glenwood.glaceemr.server.application.services.pos;

/**
 * Place of service details bean
 * @author software
 *
 */
public class PosDataBean {
	
	private Integer posRelationId;
	private Integer posPlaceId;
	private String posName;
	private String posComments;
	private String posAddress;
	private String posState;
	private String posCity;
	private String posZip;
	private String posPhNum;
	private String posFaxNum;
	private String posType;
	
	public PosDataBean(Integer posRelationId, Integer posPlaceId,
			String posName, String posComments, String posAddress, String posState, String posCity, String posZip, String posPhNum, String posFaxNum, String posType) {
		this.posRelationId = posRelationId;
		this.posPlaceId = posPlaceId;
		this.posName = posName;
		this.posAddress = posAddress;
		this.posState = posState;
		this.posCity = posCity;
		this.posZip = posZip;
		this.posPhNum = posPhNum;
		this.posFaxNum = posFaxNum;
		this.posType = posType;
		this.posComments = posComments;
	}

	public PosDataBean(Integer posRelationId, Integer posPlaceId,
			String posName, String posAddress, String posState, String posCity, String posZip, String posPhNum, String posFaxNum, String posType) {
		this.posRelationId = posRelationId;
		this.posPlaceId = posPlaceId;
		this.posName = posName;
		this.posAddress = posAddress;
		this.posState = posState;
		this.posCity = posCity;
		this.posZip = posZip;
		this.posPhNum = posPhNum;
		this.posFaxNum = posFaxNum;
		this.posType = posType;
	}
	
	public Integer getPosRelationId() {
		return posRelationId;
	}

	public void setPosRelationId(Integer posRelationId) {
		this.posRelationId = posRelationId;
	}

	public Integer getPosPlaceId() {
		return posPlaceId;
	}

	public void setPosPlaceId(Integer posPlaceId) {
		this.posPlaceId = posPlaceId;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getPosComments() {
		return posComments;
	}

	public void setPosComments(String posComments) {
		this.posComments = posComments;
	}

	public String getPosAddress() {
		return posAddress;
	}

	public void setPosAddress(String posAddress) {
		this.posAddress = posAddress;
	}

	public String getPosState() {
		return posState;
	}

	public void setPosState(String posState) {
		this.posState = posState;
	}

	public String getPosCity() {
		return posCity;
	}

	public void setPosCity(String posCity) {
		this.posCity = posCity;
	}

	public String getPosZip() {
		return posZip;
	}

	public void setPosZip(String posZip) {
		this.posZip = posZip;
	}

	public String getPosPhNum() {
		return posPhNum;
	}

	public void setPosPhNum(String posPhNum) {
		this.posPhNum = posPhNum;
	}

	public String getPosFaxNum() {
		return posFaxNum;
	}

	public void setPosFaxNum(String posFaxNum) {
		this.posFaxNum = posFaxNum;
	}

	public String getPosType() {
		return posType;
	}

	public void setPosType(String posType) {
		this.posType = posType;
	}

	
}