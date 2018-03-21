package com.glenwood.glaceemr.server.application.Bean;


public class MIPSPatientInformation {

	int patientId;
	String accountNo;
	String lastName;
	String firstName;
	String dob;
	String gender;
	String race;
	String ethnicity;
	String address1;
	String address2;
	String city;
	String state;
	String zip;
	String phoneNo;
	String status;
	int ipp;
	int denominator;
	int denominatorExclusion;
	int numerator;
	int numeratorExclusion;
	int denominatorException;
	
	public MIPSPatientInformation(int patientId, String accountNo,
			String lastName, String firstName, String dob, String gender,
			String race, String ethnicity, String address1, String address2,
			String city, String state, String zip, String phoneNo,
			String status, int ipp, int denominator, int denominatorExclusion,
			int numerator, int numeratorExclusion, int denominatorException) {
		super();
		this.patientId = patientId;
		this.accountNo = accountNo;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dob = dob;
		this.gender = gender;
		this.race = race;
		this.ethnicity = ethnicity;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNo = phoneNo;
		this.status = status;
		this.ipp = ipp;
		this.denominator = denominator;
		this.denominatorExclusion = denominatorExclusion;
		this.numerator = numerator;
		this.numeratorExclusion = numeratorExclusion;
		this.denominatorException = denominatorException;
	}


	public MIPSPatientInformation(int patientId, String accountNo,String lastName, String firstName, String dob, String gender,String race, String ethnicity, String address1, String address2,String city, String state, String zip,String phoneNo,int ipp,int denominator,int denominatorExclusion,int numerator,int numeratorExclusion,int denominatorException){
		super();
		this.patientId = patientId;
		this.accountNo = accountNo;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dob = dob;
		this.gender = gender;
		this.race = race;
		this.ethnicity = ethnicity;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNo = phoneNo;
		this.ipp=ipp;
		this.denominator=denominator;
		this.denominatorExclusion=denominatorExclusion;
		this.numerator=numerator;
		this.numeratorExclusion=numeratorExclusion;
		this.denominatorException=denominatorException;
	}
	public MIPSPatientInformation(int patientId, String accountNo,
			String lastName, String firstName, String dob, String gender,
			String race, String ethnicity,String phoneNo, String address1, String address2,
			String city, String state, String zip) {
		super();
		this.patientId = patientId;
		this.accountNo = accountNo;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dob = dob;
		this.gender = gender;
		this.race = race;
		this.ethnicity = ethnicity;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNo = phoneNo;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getIpp() {
		return ipp;
	}

	public void setIpp(int ipp) {
		this.ipp = ipp;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	public int getDenominatorExclusion() {
		return denominatorExclusion;
	}

	public void setDenominatorExclusion(int denominatorExclusion) {
		this.denominatorExclusion = denominatorExclusion;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public int getNumeratorExclusion() {
		return numeratorExclusion;
	}

	public void setNumeratorExclusion(int numeratorExclusion) {
		this.numeratorExclusion = numeratorExclusion;
	}

	public int getDenominatorException() {
		return denominatorException;
	}

	public void setDenominatorException(int denominatorException) {
		this.denominatorException = denominatorException;
	}

}