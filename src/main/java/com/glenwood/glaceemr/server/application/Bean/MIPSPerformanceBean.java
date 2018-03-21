package com.glenwood.glaceemr.server.application.Bean;

public class MIPSPerformanceBean {

	String cmsId;
	String title;
	String measureId;
	int criteria;
	int reportingYear;
	String message;
	long ippCount;
	String ippPatientsList;
	Boolean attestationStatus;
	
	long denominatorCount;
	String denominatorPatientsList;
	
	long denominatorExclusionCount;
	String denominatorExclusionPatientsList;
	
	long numeratorCount;
	String numeratorPatientsList;
	
	long numeratorExclusionCount;
	String numeratorExclusionPatientsList;
	
	long denominatorExceptionCount;
	String denominatorExceptionPatientsList;

	double performanceRate;
	double reportingRate;
	
	public Boolean getAttestationStatus() {
		return attestationStatus;
	}

	public void setAttestationStatus(Boolean attestationStatus) {
		this.attestationStatus = attestationStatus;
	}

	double points;
	
	String npi;
	String tin;
	
	String providerName;
	Boolean highPriority;
	String Outcome;
	
	long notMetPatients;
	//String notMetPatientsList;
	double ecqmPoints;
	Boolean isInverseMeasure;
	String submissionMethod;
	public MIPSPerformanceBean(){
		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public MIPSPerformanceBean(String measureId, boolean attestationStatus) {
		super();
		this.measureId = measureId;
		this.attestationStatus = attestationStatus;
	}
	public MIPSPerformanceBean(String measureId,int denominatorCount,int numeratorCount,int denominatorExceptionCount,int denominatorExclusionCount,double performanceRate){
		this.measureId=measureId;
		this.denominatorCount=denominatorCount;
		this.numeratorCount=numeratorCount;
		this.denominatorExceptionCount=denominatorExceptionCount;
		this.denominatorExclusionCount=denominatorExclusionCount;
		this.performanceRate=performanceRate;
	}
	public MIPSPerformanceBean(String measureId,int denominatorCount,int numeratorCount){
		this.measureId=measureId;
		this.denominatorCount=denominatorCount;
		this.numeratorCount=numeratorCount;
		
	}
	public MIPSPerformanceBean(String tin,String measureId, int criteria, int reportingYear,
			long ippCount, String ippPatientsList, long denominatorCount,
			String denominatorPatientsList, long denominatorExclusionCount,
			String denominatorExclusionPatientsList, long numeratorCount,
			String numeratorPatientsList, long numeratorExclusionCount,
			String numeratorExclusionPatientsList,
			long denominatorExceptionCount,
			String denominatorExceptionPatientsList) {

		this.tin = tin;
		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.ippPatientsList = ippPatientsList;
		this.denominatorCount = denominatorCount;
		this.denominatorPatientsList = denominatorPatientsList;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.denominatorExclusionPatientsList = denominatorExclusionPatientsList;
		this.numeratorCount = numeratorCount;
		this.numeratorPatientsList = numeratorPatientsList;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.numeratorExclusionPatientsList = numeratorExclusionPatientsList;
		this.denominatorExceptionCount = denominatorExceptionCount;
		this.denominatorExceptionPatientsList = denominatorExceptionPatientsList;
		
		
	}
	public MIPSPerformanceBean(String measureId, int criteria,int reportingYear,
			int ippCount, int denominatorCount,
			int denominatorExclusionCount, int numeratorCount,
			int numeratorExclusionCount, int denominatorExceptionCount, double performanceRate,
			double reportingRate, double points, String tin, String providerName) {
		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.denominatorCount = denominatorCount;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.numeratorCount = numeratorCount;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.denominatorExceptionCount = denominatorExceptionCount;
		this.performanceRate = performanceRate;
		this.reportingRate = reportingRate;
		this.points = points;
		this.tin = tin;
		this.providerName = providerName;
		
	}
	
	public MIPSPerformanceBean(String measureId, int criteria, int reportingYear,
			int ippCount, String ippPatientsList, int denominatorCount,
			String denominatorPatientsList, int denominatorExclusionCount,
			String denominatorExclusionPatientsList, int numeratorCount,
			String numeratorPatientsList, int numeratorExclusionCount,
			String numeratorExclusionPatientsList,
			int denominatorExceptionCount,
			String denominatorExceptionPatientsList, double performanceRate,
			double reportingRate, double points) {

		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.ippPatientsList = ippPatientsList;
		this.denominatorCount = denominatorCount;
		this.denominatorPatientsList = denominatorPatientsList;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.denominatorExclusionPatientsList = denominatorExclusionPatientsList;
		this.numeratorCount = numeratorCount;
		this.numeratorPatientsList = numeratorPatientsList;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.numeratorExclusionPatientsList = numeratorExclusionPatientsList;
		this.denominatorExceptionCount = denominatorExceptionCount;
		this.denominatorExceptionPatientsList = denominatorExceptionPatientsList;
		this.performanceRate = performanceRate;
		this.reportingRate = reportingRate;
		this.points = points;
		
	}
	
	public MIPSPerformanceBean(String measureId, int criteria, int reportingYear,
			int ippCount, String ippPatientsList, int denominatorCount,
			String denominatorPatientsList, int denominatorExclusionCount,
			String denominatorExclusionPatientsList, int numeratorCount,
			String numeratorPatientsList, int numeratorExclusionCount,
			String numeratorExclusionPatientsList,
			int denominatorExceptionCount,
			String denominatorExceptionPatientsList, double performanceRate,
			double reportingRate) {

		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.ippPatientsList = ippPatientsList;
		this.denominatorCount = denominatorCount;
		this.denominatorPatientsList = denominatorPatientsList;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.denominatorExclusionPatientsList = denominatorExclusionPatientsList;
		this.numeratorCount = numeratorCount;
		this.numeratorPatientsList = numeratorPatientsList;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.numeratorExclusionPatientsList = numeratorExclusionPatientsList;
		this.denominatorExceptionCount = denominatorExceptionCount;
		this.denominatorExceptionPatientsList = denominatorExceptionPatientsList;
		this.performanceRate = performanceRate;
		this.reportingRate = reportingRate;
		
	}
	
	public MIPSPerformanceBean(String tin,String measureId, int criteria, int reportingYear,
			long ippCount, String ippPatientsList, long denominatorCount,
			String denominatorPatientsList, long denominatorExclusionCount,
			String denominatorExclusionPatientsList, long numeratorCount,
			String numeratorPatientsList, long numeratorExclusionCount,
			String numeratorExclusionPatientsList,
			long denominatorExceptionCount,
			String denominatorExceptionPatientsList,double performanceRate,
			double reportingRate) {

		this.tin = tin;
		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.ippPatientsList = ippPatientsList;
		this.denominatorCount = denominatorCount;
		this.denominatorPatientsList = denominatorPatientsList;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.denominatorExclusionPatientsList = denominatorExclusionPatientsList;
		this.numeratorCount = numeratorCount;
		this.numeratorPatientsList = numeratorPatientsList;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.numeratorExclusionPatientsList = numeratorExclusionPatientsList;
		this.denominatorExceptionCount = denominatorExceptionCount;
		this.denominatorExceptionPatientsList = denominatorExceptionPatientsList;
		this.performanceRate = performanceRate;
		this.reportingRate = reportingRate;
		
	}
	
	public MIPSPerformanceBean(String measureId, int criteria, int reportingYear,
			long ippCount, String ippPatientsList, long denominatorCount,
			String denominatorPatientsList, long denominatorExclusionCount,
			String denominatorExclusionPatientsList, long numeratorCount,
			String numeratorPatientsList, long numeratorExclusionCount,
			String numeratorExclusionPatientsList,
			long denominatorExceptionCount,
			String denominatorExceptionPatientsList) {

		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.ippPatientsList = ippPatientsList;
		this.denominatorCount = denominatorCount;
		this.denominatorPatientsList = denominatorPatientsList;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.denominatorExclusionPatientsList = denominatorExclusionPatientsList;
		this.numeratorCount = numeratorCount;
		this.numeratorPatientsList = numeratorPatientsList;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.numeratorExclusionPatientsList = numeratorExclusionPatientsList;
		this.denominatorExceptionCount = denominatorExceptionCount;
		this.denominatorExceptionPatientsList = denominatorExceptionPatientsList;
		
	}
	
	public MIPSPerformanceBean(String measureId, int criteria,int reportingYear,
			long ippCount, long denominatorCount,
			long denominatorExclusionCount, long numeratorCount,
			long numeratorExclusionCount, long denominatorExceptionCount) {

		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.denominatorCount = denominatorCount;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.numeratorCount = numeratorCount;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.denominatorExceptionCount = denominatorExceptionCount;
		
	}
	
	public MIPSPerformanceBean(String measureId, int criteria,
			int reportingYear, long ippCount, String ippPatientsList,
			long denominatorCount, String denominatorPatientsList,
			long denominatorExclusionCount,
			String denominatorExclusionPatientsList, long numeratorCount,
			String numeratorPatientsList, long numeratorExclusionCount,
			String numeratorExclusionPatientsList,
			long denominatorExceptionCount,
			String denominatorExceptionPatientsList, double performanceRate,
			double reportingRate) {
		super();
		
		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.ippPatientsList = ippPatientsList;
		this.denominatorCount = denominatorCount;
		this.denominatorPatientsList = denominatorPatientsList;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.denominatorExclusionPatientsList = denominatorExclusionPatientsList;
		this.numeratorCount = numeratorCount;
		this.numeratorPatientsList = numeratorPatientsList;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.numeratorExclusionPatientsList = numeratorExclusionPatientsList;
		this.denominatorExceptionCount = denominatorExceptionCount;
		this.denominatorExceptionPatientsList = denominatorExceptionPatientsList;
		this.performanceRate = performanceRate;
		this.reportingRate = reportingRate;
	}
	
	
	public MIPSPerformanceBean(String measureId,int criteria, int reportingYear,
			long denominatorCount, String denominatorPatientsList,
			long numeratorCount, String numeratorPatientsList,
			double performanceRate) {
		super();
		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.denominatorCount = denominatorCount;
		this.denominatorPatientsList = denominatorPatientsList;
		this.numeratorCount = numeratorCount;
		this.numeratorPatientsList = numeratorPatientsList;
		this.performanceRate = performanceRate;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
	
	public Boolean getHighPriority() {
		return highPriority;
	}

	public void setHighPriority(Boolean highPriority) {
		this.highPriority = highPriority;
	}

	public String getOutcome() {
		return Outcome;
	}

	public void setOutcome(String outcome) {
		Outcome = outcome;
	}

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public int getCriteria() {
		return criteria;
	}

	public void setCriteria(int criteria) {
		this.criteria = criteria;
	}

	public double getReportingRate() {
		return reportingRate;
	}

	public void setReportingRate(double reportingRate) {
		this.reportingRate = reportingRate;
	}

	public String getCmsId() {
		return cmsId;
	}

	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMeasureId() {
		return measureId;
	}
	
	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}
	
	public int getReportingYear() {
		return reportingYear;
	}
	
	public void setReportingYear(int reportingYear) {
		this.reportingYear = reportingYear;
	}
	
	public long getNotMetPatients() {
		return notMetPatients;
	}

	public void setNotMetPatients(long notMetPatients) {
		this.notMetPatients = notMetPatients;
	}

	/*public String getNotMetPatientsList() {
		return notMetPatientsList;
	}

	public void setNotMetPatientsList(String notMetPatientsList) {
		this.notMetPatientsList = notMetPatientsList;
	}*/

	public long getIppCount() {
		return ippCount;
	}
	
	public double getPerformanceRate() {
		return performanceRate;
	}

	public void setPerformanceRate(double performanceRate) {
		this.performanceRate = performanceRate;
	}

	public void setIppCount(long ippCount) {
		this.ippCount = ippCount;
	}
	
	public long getDenominatorCount() {
		return denominatorCount;
	}
	
	public void setDenominatorCount(long denominatorCount) {
		this.denominatorCount = denominatorCount;
	}
	
	public long getDenominatorExclusionCount() {
		return denominatorExclusionCount;
	}
	
	public void setDenominatorExclusionCount(long denominatorExclusionCount) {
		this.denominatorExclusionCount = denominatorExclusionCount;
	}
	
	public long getNumeratorCount() {
		return numeratorCount;
	}
	
	public void setNumeratorCount(long numeratorCount) {
		this.numeratorCount = numeratorCount;
	}
	
	public long getNumeratorExclusionCount() {
		return numeratorExclusionCount;
	}
	
	public void setNumeratorExclusionCount(long numeratorExclusionCount) {
		this.numeratorExclusionCount = numeratorExclusionCount;
	}
	
	public long getDenominatorExceptionCount() {
		return denominatorExceptionCount;
	}
	
	public void setDenominatorExceptionCount(long denominatorExceptionCount) {
		this.denominatorExceptionCount = denominatorExceptionCount;
	}

	public String getIppPatientsList() {
		return ippPatientsList;
	}

	public void setIppPatientsList(String ippPatientsList) {
		this.ippPatientsList = ippPatientsList;
	}

	public String getDenominatorPatientsList() {
		return denominatorPatientsList;
	}

	public void setDenominatorPatientsList(String denominatorPatientsList) {
		this.denominatorPatientsList = denominatorPatientsList;
	}

	public String getDenominatorExclusionPatientsList() {
		return denominatorExclusionPatientsList;
	}

	public void setDenominatorExclusionPatientsList(
			String denominatorExclusionPatientsList) {
		this.denominatorExclusionPatientsList = denominatorExclusionPatientsList;
	}

	public String getNumeratorPatientsList() {
		return numeratorPatientsList;
	}

	public void setNumeratorPatientsList(String numeratorPatientsList) {
		this.numeratorPatientsList = numeratorPatientsList;
	}

	public String getNumeratorExclusionPatientsList() {
		return numeratorExclusionPatientsList;
	}

	public void setNumeratorExclusionPatientsList(
			String numeratorExclusionPatientsList) {
		this.numeratorExclusionPatientsList = numeratorExclusionPatientsList;
	}

	public String getDenominatorExceptionPatientsList() {
		return denominatorExceptionPatientsList;
	}

	public void setDenominatorExceptionPatientsList(
			String denominatorExceptionPatientsList) {
		this.denominatorExceptionPatientsList = denominatorExceptionPatientsList;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public double getEcqmPoints() {
		return ecqmPoints;
	}

	public void setEcqmPoints(double ecqmPoints) {
		this.ecqmPoints = ecqmPoints;
	}

	public Boolean getIsInverseMeasure() {
		return isInverseMeasure;
	}

	public void setIsInverseMeasure(Boolean isInverseMeasure) {
		this.isInverseMeasure = isInverseMeasure;
	}

	public String getSubmissionMethod() {
		return submissionMethod;
	}

	public void setSubmissionMethod(String submissionMethod) {
		this.submissionMethod = submissionMethod;
	}
		
	
}