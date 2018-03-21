package com.glenwood.glaceemr.server.application.specifications.print;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.InitialSettings_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;

public class GenericPrintSpecification {

	public static Specification<PatientRegistration> getPatientDetails(final int patientId){
		return new Specification<PatientRegistration>() {

			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Fetch<PatientRegistration, ReferringDoctor> patRefJoin = root.fetch(PatientRegistration_.referringPhyTable,JoinType.LEFT);
//				Join<PatientRegistration, PatientInsDetail> patInsAddrJoin = root.join(PatientRegistration_.patientInsuranceTable, JoinType.LEFT);
//				patInsAddrJoin.on(cb.equal(patInsAddrJoin.get(PatientInsDetail_.patientInsDetailIsactive), true));
//				patInsAddrJoin.on(patInsAddrJoin.get(PatientInsDetail_.patientInsDetailInstype).in(2));
//				root.fetch(PatientRegistration_.patientInsuranceTable, JoinType.LEFT);
//				Fetch<PatientInsDetail, InsCompAddr> compJoin = patInsAddrJoin.fetch("insCompAddr",JoinType.LEFT);
//				Fetch<InsCompAddr, InsCompany> insComp = compJoin.fetch("insCompany", JoinType.LEFT);
				root.fetch(PatientRegistration_.empProfile, JoinType.LEFT);
				
				/*if (Long.class != query.getResultType()) {
					root.fetch(PatientRegistration_.referringPhyTable,JoinType.LEFT);
					root.fetch(PatientRegistration_.patientInsuranceTable, JoinType.LEFT);
				}*/
				
				Predicate patientIdPred= cb.equal(root.get(PatientRegistration_.patientRegistrationId), patientId);
//				Predicate insTypePred = cb.equal(patInsAddrJoin.get(PatientInsDetail_.patientInsDetailInstype), 2);
//				Predicate insIsactivePred = cb.equal(patInsAddrJoin.get(PatientInsDetail_.patientInsDetailIsactive), true);
				
				
//				Predicate finalPred = cb.and(patientIdPred, insTypePred, insIsactivePred);
				Predicate finalPred = cb.and(patientIdPred);
				return finalPred;
			}

		};
	}

	public static Specification<PatientRegistration> getPatientDetails(final int patientId,final List<Integer> insTypeList){
		return new Specification<PatientRegistration>() {

			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				root.fetch(PatientRegistration_.empProfile, JoinType.LEFT);
				Fetch<PatientRegistration, ReferringDoctor> patRefJoin = root.fetch(PatientRegistration_.referringPhyTable,JoinType.LEFT);
//				Join<PatientRegistration, PatientInsDetail> patInsAddrJoin = root.join(PatientRegistration_.patientInsuranceTable, JoinType.LEFT);
//				patInsAddrJoin.on(cb.equal(patInsAddrJoin.get(PatientInsDetail_.patientInsDetailIsactive), true));
//				patInsAddrJoin.on(patInsAddrJoin.get(PatientInsDetail_.patientInsDetailInstype).in(insTypeList));
//				root.fetch(PatientRegistration_.patientInsuranceTable, JoinType.LEFT);
//				Fetch<PatientInsDetail, InsCompAddr> compJoin = patInsAddrJoin.fetch("insCompAddr",JoinType.LEFT);
//				Fetch<InsCompAddr, InsCompany> insComp = compJoin.fetch("insCompany", JoinType.LEFT);
				
				
				/*if (Long.class != query.getResultType()) {
					root.fetch(PatientRegistration_.referringPhyTable,JoinType.LEFT);
					root.fetch(PatientRegistration_.patientInsuranceTable, JoinType.LEFT);
				}*/
				
				Predicate patientIdPred= cb.equal(root.get(PatientRegistration_.patientRegistrationId), patientId);
//				Predicate insTypePred = cb.equal(patInsAddrJoin.get(PatientInsDetail_.patientInsDetailInstype), 2);
//				Predicate insIsactivePred = cb.equal(patInsAddrJoin.get(PatientInsDetail_.patientInsDetailIsactive), true);
				
				
//				Predicate finalPred = cb.and(patientIdPred, insTypePred, insIsactivePred);
				Predicate finalPred = cb.and(patientIdPred);
				return finalPred;
			}

		};
	}
	
	/**To get list of practice details
     * @return specification to fetch list of practice details
     */    
    public static Specification<InitialSettings> getPracticeDetails()
       {
           return new Specification<InitialSettings>() {

            @Override
            public Predicate toPredicate(Root<InitialSettings> root,
                    CriteriaQuery<?> cq, CriteriaBuilder cb) {
                
                Predicate practicePredicate=cq.where(cb.equal(root.get(InitialSettings_.initialSettingsOptionType), 4)).getRestriction();
                return practicePredicate;
            }
               
        };
       }
}
