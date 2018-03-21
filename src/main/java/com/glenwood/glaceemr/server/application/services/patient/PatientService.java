package com.glenwood.glaceemr.server.application.services.patient;

import java.util.List;
import java.util.Map;

import com.glenwood.glaceemr.server.application.models.TesttableAdrs;
import com.glenwood.glaceemr.server.application.models.Testtableenctr;
import com.glenwood.glaceemr.server.application.models.TesttableIns;
import com.glenwood.glaceemr.server.application.models.TesttablePtn;
import com.glenwood.glaceemr.server.application.models.TesttablePntIns;

public interface PatientService {


	/**
	 * @param lastName		: required patients lastName
	 * @return List of Patients  satisfied by the condition (lastName should match)
	 */
	public Iterable<TesttablePtn> findPatientByLastName(String lastName);

	public Iterable<TesttablePtn> findPatientByinsuranceName(String insuranceName);
	
	/**
	 * @param lastname		: required patients lastName 
	 * @param Dob			: required patients date of birth	 
	 * @return List of Patients  satisfied by the condition (lastName and date of birth should match)
	 */
	public Iterable<TesttablePtn> findPatientByLastNameAndDob(String lastname,String Dob);

	/**
	 * @param lastname		: required patients lastName
	 * @param insuranceName	: required patients insuranceName
	 * @return List of Patients  satisfied by the condition (lastName and insuranceName should match)
	 */
	public Iterable<TesttablePtn> findPatintByLastNameHavingInsuranceByName(String lastname,String insuranceName);
	
	
	
	public Iterable<Testtableenctr> findEncounterByPatientId(Integer PatientId) ;
	
	
	
	public Iterable<TesttablePtn> findInsuranceByPatientId(Integer PatientId) ;
	
	public List<Map<String, Object>> findLatestEncounterDateAndPatientNameByPatientId(Integer PatientId);
	
	public Iterable<Testtableenctr> findEncountersbyPatientID(List<Integer> patientId);
	public List<Iterable<Object>>findLatestEncountersbyPatientIDs(List<Integer> PatientId);
	
	
	public Iterable<TesttablePtn> findPatientByPatientId(Integer patientId);
	
	public void deleteById(Integer patientId);
	
	public void deleteByLastname(String  lastName);
	
	public void deleteAllAddress();
	
	public void deleteByPatient(TesttablePtn patient);
	
	public TesttablePtn updateByPatient(TesttablePtn patient);
	
	public TesttablePtn insertByPatient(TesttablePtn patient);
	
	
	public Iterable<TesttableAdrs> updateByAddress(String address);
	
	public TesttablePtn updateByAddressId(Integer addressId);
	
	public void insertorupdaterecord();
	
	
	
	
}
