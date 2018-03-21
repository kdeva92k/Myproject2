package com.glenwood.glaceemr.server.application.services.chart.vitals;


/**
 * used for getting the element bean of vital
 * @author CNM
 *
 */
public class VitalElementBean {
	private String vitalGWId;
	private String vitalName;
	private int vitalUnit;
	private int vitalGender;
	private String isvitalGraphNeeded;
	private boolean isvitalLastVisit;
	private String vitalCondition;
	private int vitalConditionType;
	private String unitsOfMeasureCode;
	
	public VitalElementBean() {
		
	}
	
	/**
	 * used for getting the vital GlenWood Id
	 * @return the value of vitalGlenwoodId
	 */	
	
	public String getVitalGWId(){
		return vitalGWId;
	}
	
	/**
	 * used for setting the vital GlenWood Id
	 * @param vitalGWId it represents vitalGlenwoodId
	 */
	
	public void setVitalGWId(String vitalGWId){
		this.vitalGWId = vitalGWId;
	}
	
	/**
	 * used for getting the vital name
	 * @returns the value stored in the vitalName
	 */
	
	public String getVitalName(){
		return vitalName;
	}
	
	/**
	 * used to set the vital name
	 * @param vitalName it represents the name of the vital
	 */
	
	public void setVitalName(String vitalName){
		this.vitalName = vitalName;
	}
	
	/**
	 * used to get the vital unit
	 * @returns the value of vitalUnit
	 */
	
	public int getVitalUnit(){
		return vitalUnit;
	}
	
	/**
	 * used to set the vital unit
	 * @param vitalUnit
	 */
	
	public void setVitalUnit(int vitalUnit){
		this.vitalUnit = vitalUnit;
	}
	
	/**
	 * used to get the vital gender
	 * @return the value of patientGender
	 */
	
	public int getVitalGender(){
		return vitalGender;
	}
	
	/**
	 * used to set the vital gender
	 * @param vitalGender it represents the gender of a patient
	 */
	
	public void setVitalGender(int vitalGender){
		this.vitalGender = vitalGender;
	}
	
	/**
	 * used to get the vital is vital graph needed or not
	 * @returns whether the vitalGraphNeeded or not
	 */
	
	public String getVitalIsvitalGraphNeeded() {
		return isvitalGraphNeeded;
	}
	
	/**
	 * used to set the vital is vital needed or not
	 * @param isvitalGraphNeededit represents the vitalGraph
	 */
	
	public void setVitalIsvitalGraphNeeded(String isvitalGraphNeeded) {
		this.isvitalGraphNeeded = isvitalGraphNeeded;
	}
	
	/**
	 * used to get the vital is vital last visit 
	 * @returns the value it is lastVitalVisit
	 */
	
	public boolean getVitalIsvitalLastVisit() {
		return isvitalLastVisit;
	}
	
	/**
	 * used to set the vital is vital last visit
	 * @param isvitalLastVisit
	 */
	
	public void setVitalIsvitalLastVisit(boolean isvitalLastVisit) {
		this.isvitalLastVisit = isvitalLastVisit;
	}
	
	/**
	 * 
	 * @returns the value of a vitalCondition
	 */
	
	public String getVitalCondition() {
		return vitalCondition;
	}
	
	/**
	 * used to set the vital condition
	 * @param vitalCondition
	 */
	
	public void setVitalCondition(String vitalCondition) {
		this.vitalCondition = vitalCondition;
	}
	
	/**
	 * used to get the vital condition
	 * @return the value of vitalConditionType
	 */
	
	public int getVitalConditionType() {
		return vitalConditionType;
	}
	
	/**
	 * used to set the vital condition type
	 * @param vitalConditionType
	 */
	
	public void setVitalConditionType(int vitalConditionType) {
		this.vitalConditionType = vitalConditionType;
	}

	public String getIsvitalGraphNeeded() {
		return isvitalGraphNeeded;
	}

	public void setIsvitalGraphNeeded(String isvitalGraphNeeded) {
		this.isvitalGraphNeeded = isvitalGraphNeeded;
	}

	public boolean isIsvitalLastVisit() {
		return isvitalLastVisit;
	}

	public void setIsvitalLastVisit(boolean isvitalLastVisit) {
		this.isvitalLastVisit = isvitalLastVisit;
	}

	public String getUnitsOfMeasureCode() {
		return unitsOfMeasureCode;
	}

	public void setUnitsOfMeasureCode(String unitsOfMeasureCode) {
		this.unitsOfMeasureCode = unitsOfMeasureCode;
	}
	
	
}