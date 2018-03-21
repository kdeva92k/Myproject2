/*package com.glenwood.glaceemr;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.models.Patient;
import com.glenwood.glaceemr.server.application.repositories.AddressRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientInsuranceRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRepository;
import com.glenwood.glaceemr.server.application.specifications.PatientSpecification;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:WEB-INF/applicationContext.xml")
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class PatientTableRepositoryTest {
	@Autowired
	PatientRepository patientTableRepository;

	@PersistenceContext
	EntityManager em;

	@Autowired
	AddressRepository addressTableRepository;

	@Autowired
	PatientInsuranceRepository patientInsuranceRepository;

	@Autowired
	EncounterRepository encounterTableRepository;
	
	*//**
	 * Test method using jpql 
	 *//*
	@Test
	public void jpqlTest()
	{

		final String lastname="Smith";
		TypedQuery<Patient> query = em.createQuery("" +
				"select  distinct p from Patient p " +
				"inner join p.encounterTable as e  "+
				"inner join p.pAddressTable as a "
				+"where p.patientLName like :patientByLastname and p.isActive = :isActivePatient and e.isActive = :isActiveEncounter and a.isActive =:isActiveAddress", Patient.class);
		query.setParameter("patientByLastname", "%" + lastname + "%");
		query.setParameter("isActivePatient", true);
		query.setParameter("isActiveEncounter", true);
		query.setParameter("isActiveAddress",true);
		List<Patient> patients = query.getResultList();
		for(Patient patient: patients){
			System.out.println("FirstName from Jpql : "+patient.getPatientFName());
			System.out.println("DOB  from jpql : "+patient.getPatientDob());
		}
	}

	
	*//**
	 * Sample method test  using querydsl
	 *//*
	@SuppressWarnings("unchecked")
	@Test
	public void querdslTest()
	{ 
		List<Patient> patients = (List<Patient>) patientTableRepository.findAll(PatientSpecification.PatientByLastName("Smith"));
		for(Patient patient: patients){
			System.out.println("FirstName from querydsl : "+patient.getPatientFName());
			System.out.println("DOB  from  querydsl: "+patient.getPatientDob());
		}
	}
	
	
	*//**
	 * Method to write to file
	 *//*
	public void WriteToFile(List<Patient> list,String filename)
	{
		ObjectMapper mapper = new ObjectMapper();

		try {

			// convert user object to json string, and save to a file
			mapper.writeValue(new File("/tmp/"+filename+".json"), list);

			// display to console
			System.out.println(mapper.writeValueAsString(list));

		} catch (JsonGenerationException e) {

			e.printStackTrace();

		} catch (JsonMappingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
*/