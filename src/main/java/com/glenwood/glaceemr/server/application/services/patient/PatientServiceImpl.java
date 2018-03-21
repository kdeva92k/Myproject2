package com.glenwood.glaceemr.server.application.services.patient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.TesttableAdrs;
import com.glenwood.glaceemr.server.application.models.TesttablePtn;
import com.glenwood.glaceemr.server.application.models.TesttablePtn_;
import com.glenwood.glaceemr.server.application.models.Testtableenctr;
import com.glenwood.glaceemr.server.application.models.Testtableenctr_;
import com.glenwood.glaceemr.server.application.repositories.TesttableAdrsRepository;
import com.glenwood.glaceemr.server.application.repositories.TesttableInsMasterRepository;
import com.glenwood.glaceemr.server.application.repositories.TesttablePntInsRepository;
import com.glenwood.glaceemr.server.application.repositories.TesttablePtnRepository;
import com.glenwood.glaceemr.server.application.repositories.TesttableenctrRepository;
import com.glenwood.glaceemr.server.application.specifications.TesttableAdrsSpecfication;
import com.glenwood.glaceemr.server.application.specifications.TesttablePntInsSpecification;
import com.glenwood.glaceemr.server.application.specifications.TesttablePtnSpecification;
import com.glenwood.glaceemr.server.application.specifications.TesttableenctrSpecification;
import com.google.common.collect.ImmutableSet;

@Service
@Transactional
public class PatientServiceImpl implements PatientService{

	@Autowired
	TesttablePtnRepository patientTableRepository; 

	@Autowired
	TesttableAdrsRepository addressTableRepository;

	@Autowired
	TesttableenctrRepository encounterTableRepository; 

	@Autowired
	EntityManagerFactory emf ;

	@Autowired
	TesttableInsMasterRepository insuranceMasterRepository;

	@Autowired
	TesttablePntInsRepository patientInsuranceRepository; 

	@PersistenceContext
	EntityManager em;


	/* (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.patient.PatientService#findPatientByLastName(java.lang.String)
	 * This method returns list of Patients  satisfied by the condition (lastName should match)
	 */
	@Override
	public Iterable<TesttablePtn> findPatientByLastName(String lastName) {

		Iterable<TesttablePtn> patients= patientTableRepository.findAll(TesttablePtnSpecification.PatientByLastName(lastName));
		
		return patients;
	}

	
	/* (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.patient.PatientService#findPatientByLastName(java.lang.String)
	 * This method returns list of Patients  satisfied by the condition (lastName should match)
	 */
	@Override
	public Iterable<TesttablePtn> findPatientByPatientId(Integer patientId) {

		Iterable<TesttablePtn> patients= patientTableRepository.findAll(TesttablePtnSpecification.PatientByPatientId(patientId));
		return patients;
	}




	/* (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.patient.PatientService#findPatientByLastNameAndDob(java.lang.String, java.lang.String)
	 * This method returns list of Patients  satisfied by the condition (lastName and date of birth should match)
	 */
	@Override
	public Iterable<TesttablePtn> findPatientByLastNameAndDob(String lastName, String dob) {
		return  patientTableRepository.findAll(Specifications.where(TesttablePtnSpecification.PatientByLastName(lastName)).and(TesttablePtnSpecification.PatientByDob(dob)));
	}



	@Override
	public Iterable<TesttablePtn> findPatientByinsuranceName(String insuranceName) {

		Iterable<TesttablePtn> patients= patientTableRepository.findAll(TesttablePtnSpecification.PatientByInusranceName(insuranceName),new PageRequest(0, 2,Direction.DESC,"patientFName")).getContent();
		return patients;
	}


	/* (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.patient.PatientService#findPatintByLastNameHavingInsuranceByName(java.lang.String, java.lang.String)
	 * This method returns list of  Patients  satisfied by the condition (lastName and insuranceName should match)
	 */
	@Override
	public Iterable<TesttablePtn> findPatintByLastNameHavingInsuranceByName(String lastName, String insuranceName) {
		Iterable<TesttablePtn> patients=patientTableRepository.findAll(Specifications.where(TesttablePtnSpecification.PatientByLastName(lastName)).and(TesttablePtnSpecification.PatientByInusranceName(insuranceName)));
		return  patients;
	}



	public Iterable<Testtableenctr> findEncounterByPatientId(Integer PatientId) {

		return encounterTableRepository.findAll(TesttableenctrSpecification.EncounterByPatientId(PatientId));

	}


	public Iterable<TesttablePtn> findInsuranceByPatientId(Integer PatientId) {
		
		Iterable<TesttablePtn> patients=patientTableRepository.findAll(TesttablePtnSpecification.PatientByPatientId(PatientId));
		
		for (TesttablePtn patient : patients) {
			patient.setPatientInsuranceTable(ImmutableSet.copyOf(patientInsuranceRepository.findAll(TesttablePntInsSpecification.InsuranceByPatientId(PatientId))));
		}
		
		

		return patients;

	}


	public List<Map<String, Object>> findLatestEncounterDateAndPatientNameByPatientId(Integer PatientId) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Object> cq = builder.createQuery();

		Root<Testtableenctr> root = cq.from(Testtableenctr.class);
		Join<Testtableenctr,TesttablePtn> patient=root.join(Testtableenctr_.patientTable,JoinType.INNER);
		Predicate Bypatientid=builder.equal(patient.get(TesttablePtn_.patientId), PatientId);
		cq.multiselect(builder.max(root.get(Testtableenctr_.encounterId))).where(Bypatientid);
		
		Integer id=(Integer) em.createQuery(cq).getSingleResult();
		Predicate Bymaxencoutnerid=builder.equal(root.get(Testtableenctr_.encounterId), id);
		
		cq.multiselect(root.get(Testtableenctr_.encounterId),root.get(Testtableenctr_.encounterDate),patient.get(TesttablePtn_.patientLName),patient.get(TesttablePtn_.patientId)).where(Bymaxencoutnerid).distinct(true);  //using metamodel
		
		List<String> listofproperties=new ArrayList<String>();
		listofproperties.add("encounterId");
		listofproperties.add("encoutnerDate");
		listofproperties.add("patientLastName");
		listofproperties.add("patientId");	
		List<Map<String, Object>> listofobj=CustomQueryResult(listofproperties, cq);
		for (int i = 0; i < listofobj.size(); i++) {
			if(listofobj.get(i).containsKey("encoutnerDate")){
				String test=listofobj.get(i).get("encoutnerDate").toString();
//				listofobj.get(i).replace("encoutnerDate",test );
			}
		}
		return listofobj;

	}

	/* Method to execute the custom queries */
	public List<Map<String, Object>> CustomQueryResult(List<String> listinorder,CriteriaQuery<Object> cq) 
	{

		try{

			Iterator iter= em.createQuery(cq).getResultList().iterator();
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();

			while ( iter.hasNext() ) {
				Object[] row = (Object[]) iter.next();
				Map<String,Object> newMap=  new HashMap<String,Object>();
				for(int i=0;i<row.length;i++)
				{
					newMap.put(listinorder.get(i),(Object)row[i]);
				}
				resultList.add(newMap);

			}
			return resultList;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public Iterable<Testtableenctr> findEncountersbyPatientID(List<Integer> patientId) {
		
		return encounterTableRepository.findAll(TesttableenctrSpecification.EncounterByPatientIds(patientId));
	
	}

	
	public List<Iterable<Object>> findLatestEncountersbyPatientIDs(List<Integer> PatientId) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Testtableenctr> root = cq.from(Testtableenctr.class);
		Join<Testtableenctr,TesttablePtn> patient=root.join(Testtableenctr_.patientTable,JoinType.INNER);
		List<Iterable<Object>> newlist= new ArrayList<Iterable<Object>>();
		for(int i=0;i<PatientId.size();i++){
			Predicate Bypatientid=builder.equal(patient.get(TesttablePtn_.patientId), PatientId.get(i));
			cq.multiselect(builder.max(root.get(Testtableenctr_.encounterId))).where(Bypatientid);
			Integer id=(Integer) em.createQuery(cq).getSingleResult();
			Predicate Bymaxencoutnerid=builder.equal(root.get(Testtableenctr_.encounterId), id);
			cq.multiselect(root).where(Bymaxencoutnerid).distinct(true);  //using metamodel
			Iterable<Object> test=em.createQuery(cq).getResultList();
			newlist.add(test);

		}
		return newlist;

	}
	
	
	/*
	 * For inserting the data into the accounts
	 */

	public void insertorupdaterecord()
	{
		TesttablePtn testinsert=new TesttablePtn();
		testinsert.setIsActive(false);
//		Timestamp patientDob = Timestamp.valueOf("12-12-1999 00:00:00");
		testinsert.setPatientDob("12-12-1999");
		testinsert.setPatientFName("rakesh");
		testinsert.setPatientLName("sai");
		testinsert.setPatientGender(1);
		testinsert.setPatientId(10);
		patientTableRepository.saveAndFlush(testinsert);
	}
	
	@Override
	public TesttablePtn updateByPatient(TesttablePtn patient) {
		
		return patientTableRepository.saveAndFlush(patient);
	}
	
	
	public void deleteById(Integer patientId)
	{
		patientTableRepository.delete(patientTableRepository.findOne(TesttablePtnSpecification.PatientByPatientId(patientId)));
		
		
	}


	@Override
	public void deleteByLastname(String lastName) {
		patientTableRepository.delete(patientTableRepository.findAll(TesttablePtnSpecification.PatientByLastName(lastName)));
		
	}


	@Override
	public void deleteAllAddress() {
		addressTableRepository.deleteAll();
		
	}
	
	


	@Override
	public Iterable<TesttableAdrs> updateByAddress(String address) {
		
		Iterable<TesttableAdrs> addresses=addressTableRepository.findAll(TesttableAdrsSpecfication.byAddress(address));
		for (TesttableAdrs address2 : addresses) {
			address2.setAddress(address2.getAddress()+" USA");
			address2=addressTableRepository.saveAndFlush(address2);	
		}
		 return addresses;
	}


	@Override
	public TesttablePtn insertByPatient(TesttablePtn patient) {
		return patientTableRepository.save(patient);
	}
	
	

	@Override
	public TesttablePtn updateByAddressId(Integer addressId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteByPatient(TesttablePtn patient) {
		patientTableRepository.delete(patient);
		
	}
	
}
