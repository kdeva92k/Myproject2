package com.glenwood.glaceemr.server.application.Bean.pqrs;

import java.util.HashMap;

public class MeasureStatus {


	private int measureId;
	private String cmsId=new String();
	private String reportingYear=new String();
	private int criteria=1;
	private int ipp=0;
	private int denominator=0;
	private int denominatorExclusion=0;
	private int numerator=0;
	private int numeratorExclusion=0;
	private int denominatorException=0;
	private int measurePopulation=0;
	private int measurePopulationExclusion=0;
	private double measureObservation=0;
	private int ruleState=0;
	private HashMap<String,Object> ruleStateObject = new HashMap<String,Object>();
	private int stratum=0;

	public int getMeasureId()
	{
		return measureId;
	}
	public void setMeasureId(int measureId)
	{
		this.measureId = measureId;
	}
	public String getCmsId()
	{
		return cmsId;
	}
	public void setCmsId(String cmsId)
	{
		this.cmsId = cmsId;
	}
	public String getReportingYear()
	{
		return reportingYear;
	}
	public void setReportingYear(String reportingYear)
	{
		this.reportingYear = reportingYear;
	}
	public int getCriteria()
	{
		return criteria;
	}
	public void setCriteria(int criteria)
	{
		this.criteria = criteria;
	}
	public int getIpp()
	{
		return ipp;
	}
	public void setIpp(int ipp)
	{
		this.ipp = ipp;
	}
	public int getDenominator()
	{
		return denominator;
	}
	public void setDenominator(int denominator)
	{
		this.denominator = denominator;
	}
	public int getDenominatorExclusion()
	{
		return denominatorExclusion;
	}
	public void setDenominatorExclusion(int denominatorExclusion)
	{
		this.denominatorExclusion = denominatorExclusion;
	}
	public int getNumerator()
	{
		return numerator;
	}
	public void setNumerator(int numerator)
	{
		this.numerator = numerator;
	}
	public int getNumeratorExclusion()
	{
		return numeratorExclusion;
	}
	public void setNumeratorExclusion(int numeratorExclusion)
	{
		this.numeratorExclusion = numeratorExclusion;
	}
	public int getDenominatorException()
	{
		return denominatorException;
	}
	public void setDenominatorException(int denominatorException)
	{
		this.denominatorException = denominatorException;
	}
	public int getMeasurePopulation()
	{
		return measurePopulation;
	}
	public void setMeasurePopulation(int measurePopulation)
	{
		this.measurePopulation = measurePopulation;
	}
	public int getMeasurePopulationExclusion()
	{
		return measurePopulationExclusion;
	}
	public void setMeasurePopulationExclusion(int measurePopulationExclusion)
	{
		this.measurePopulationExclusion = measurePopulationExclusion;
	}
	public double getMeasureObservation()
	{
		return measureObservation;
	}
	public void setMeasureObservation(double measureObservation)
	{
		this.measureObservation = measureObservation;
	}
	public int getRuleState()
	{
		return ruleState;
	}
	public void setRuleState(int ruleState)
	{
		this.ruleState = ruleState;
	}
	public HashMap<String,Object> getRuleStateObject()
	{
		return ruleStateObject;
	}
	public void setRuleStateObject(HashMap<String,Object> ruleStateObject)
	{
		this.ruleStateObject = ruleStateObject;
	}
	public int getStratum()
	{
		return stratum;
	}
	public void setStratum(int stratum)
	{
		this.stratum = stratum;
	}
	

}
