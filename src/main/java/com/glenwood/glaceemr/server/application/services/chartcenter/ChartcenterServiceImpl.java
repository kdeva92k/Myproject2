package com.glenwood.glaceemr.server.application.services.chartcenter;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistrationSearchBean;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.specifications.PatientRegistrationSpecification;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

@Service
public class ChartcenterServiceImpl implements ChartcenterService {

	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;
	
	@Autowired
	EntityManager em;
	
	String firstName="";
	String lastName="";
	int currentPage = 0;
	int pageSize = 0;
	int searchMode = 0;
	int searchType = 0;
	@Override
	public 	Page<PatientRegistration>  getPatientSearchResult(String toSearchData,String searchTypeParam)throws Exception {

		JSONObject data = new JSONObject(toSearchData);
		lastName=Optional.fromNullable(Strings.emptyToNull(data.get("txtLName").toString())).or("");
		firstName=Optional.fromNullable(Strings.emptyToNull(data.get("txtFName").toString())).or("");
		currentPage =Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(data.get("currentPage").toString())).or("20"));
		pageSize =Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(data.get("pageSize").toString())).or("20"));
		searchMode=Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(data.get("searchMode").toString())).or("1"));
		int keyDownFlag= Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(data.get("keyDownFlag").toString())).or("1"));
		searchType=Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(searchTypeParam)).or("1"));
		int offset = pageSize * ( currentPage - 1 );
		Page<PatientRegistration> patientList = null;
		
		/* query choosing based on searchmode and lastname/firstname */
		if (searchMode == 0) {
			if (lastName.length() > 0) {
				 patientList =patientRegistrationRepository.findAll(Specifications.where(PatientRegistrationSpecification.PatientByLastName(lastName)).and(PatientRegistrationSpecification.PatientIsactive(true)),new PageRequest(offset, pageSize));
				
			}
			if(firstName.length() > 0){
				 patientList =patientRegistrationRepository.findAll(Specifications.where(PatientRegistrationSpecification.PatientByFirstName(firstName)).and(PatientRegistrationSpecification.PatientIsactive(true)),new PageRequest(offset, pageSize));
			}
			
		}
		else {
			 patientList =patientRegistrationRepository.findAll(Specifications.where(PatientRegistrationSpecification.PatientByAccountNumber(lastName)).and(PatientRegistrationSpecification.PatientIsactive(true)),new PageRequest(offset, pageSize));

		}
		
		return patientList;
	}
	@Override
	public List<PatientRegistrationSearchBean> getPatientNameBySearch(String toSearchData) throws Exception{
		int offset,limit;
		JSONObject searchData = new JSONObject(toSearchData);
		lastName=Optional.fromNullable(Strings.emptyToNull(searchData.get("txtLName").toString())).or("");
		firstName=Optional.fromNullable(Strings.emptyToNull(searchData.get("txtFName").toString())).or("");
		offset =Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(searchData.get("offset").toString())).or("20"));
		limit =Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(searchData.get("limit").toString())).or("20"));
		searchMode=Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(searchData.get("searchMode").toString())).or("1"));
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		cq.multiselect(builder.construct(PatientRegistrationSearchBean.class, root.get(PatientRegistration_.patientRegistrationId),
				root.get(PatientRegistration_.patientRegistrationLastName),
				root.get(PatientRegistration_.patientRegistrationFirstName),
				root.get(PatientRegistration_.patientRegistrationAccountno),
				root.get(PatientRegistration_.patientRegistrationPreferredcontact)));
		List<Predicate> predicateList = new ArrayList<Predicate>();
		if (searchMode == 0) {
			if (lastName.length() > 0) {
				Predicate PatientByLastName = builder.like(builder.upper(root.get(PatientRegistration_.patientRegistrationLastName)),lastName.toUpperCase()+"%");
				predicateList.add(PatientByLastName);
			}
			if(firstName.length() > 0){
				Predicate PatientByFirstName = builder.like(builder.upper(root.get(PatientRegistration_.patientRegistrationFirstName)),firstName.toUpperCase()+"%");
				predicateList.add(PatientByFirstName);
			}
		}
		else {
			Predicate PatientByAccountNumber = builder.like(builder.upper(root.get(PatientRegistration_.patientRegistrationAccountno)),lastName.toUpperCase()+"%");
			predicateList.add(PatientByAccountNumber);
		}
		Predicate PatientIsActive = builder.equal(root.get(PatientRegistration_.patientRegistrationActive),true);
		predicateList.add(PatientIsActive);
		cq.where(predicateList.toArray(new Predicate[] {}));
		List<Object> ptReg = em.createQuery(cq).setFirstResult(offset)
											.setMaxResults(limit).getResultList();
		
		long totalCount = getTotalCount(predicateList);
		
		List<PatientRegistrationSearchBean> ptRegBean = new ArrayList<PatientRegistrationSearchBean>();
		for(int i=0;i<ptReg.size();i++)
		{
			PatientRegistrationSearchBean ptRegSingleBean = (PatientRegistrationSearchBean) ptReg.get(i);
			ptRegSingleBean.setTotalcount(totalCount);
			ptRegBean.add(ptRegSingleBean);
		}
		return ptRegBean;
	}
	
	public long getTotalCount(List<Predicate> predicateList) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		cq.select(builder.count(root.get(PatientRegistration_.patientRegistrationId)));
		cq.where(predicateList.toArray(new Predicate[] {}));
		return (long) em.createQuery(cq).getSingleResult();
	}
	@Override
	public int getChartIdByPatientId(Integer patientId) throws Exception {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<Chart> root = cq.from(Chart.class);
		
		cq.select(root.get(Chart_.chartId));
		cq.where(builder.equal(root.get(Chart_.chartPatientid), patientId));
		
		
		return (int) em.createQuery(cq).getSingleResult();
	}

}
