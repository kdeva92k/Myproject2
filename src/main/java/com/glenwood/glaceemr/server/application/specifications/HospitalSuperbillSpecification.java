package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.Admission;
import com.glenwood.glaceemr.server.application.models.Admission_;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;

@Component
@Transactional
public class HospitalSuperbillSpecification {

	/**to get admitted patient list
	 * @param selectedPosId
	 * @return
	 */
	public static Specification<Admission> getAdmittedPatientsList(final Integer selectedPosId) {
        return new Specification<Admission>() {
            @Override
            public Predicate toPredicate(Root<Admission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
               Join<Admission,PatientRegistration> patRegJoin=root.join(Admission_.patientRegistration,JoinType.INNER);
               Join<Admission,PosTable> posTableJoin=root.join(Admission_.posTable,JoinType.INNER);
               Join<Admission,EmployeeProfile> empJoin=root.join(Admission_.empProfile,JoinType.INNER);
               Join<PatientRegistration,EmployeeProfile> primPhysician=patRegJoin.join(PatientRegistration_.empProfile,JoinType.LEFT);
               Join<PatientRegistration,ReferringDoctor> refPhysician=patRegJoin.join(PatientRegistration_.referringPhyTable,JoinType.LEFT);
               Predicate dischargeDate=root.get(Admission_.admissionDischargeDate).isNull();
               Predicate admissionPosType=cb.equal(root.get(Admission_.admissionPosId),selectedPosId);
               Predicate patientActive=cb.equal(patRegJoin.get(PatientRegistration_.patientRegistrationActive),true);
               Predicate typeofPOS=posTableJoin.get(PosTable_.posTablePosCode).in(21,22,23,31,32,33);   
               Predicate posActive=cb.equal(posTableJoin.get(PosTable_.posTableIsActive),true);
               Predicate finalPredicate=cb.and(dischargeDate,admissionPosType,patientActive,typeofPOS,posActive);
               if (Long.class != query.getResultType()) {
                   root.fetch(Admission_.patientRegistration);
                   root.fetch(Admission_.posTable);
                   root.fetch(Admission_.empProfile);
               }
              return query.where(finalPredicate).getRestriction();
            }
        };
    }
	
	public static Specification<ServiceDetail> getserviceDetails(final List<Integer> serviceDetailIdList,final List<java.util.Date> maxDos) {
		 return new Specification<ServiceDetail>() {
			 @Override
				public Predicate toPredicate(Root<ServiceDetail> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
	                Join<ServiceDetail,Cpt> cptJoin=root.join(ServiceDetail_.cpt,JoinType.INNER);
	                Join<ServiceDetail,EmployeeProfile> serDoctor=root.join(ServiceDetail_.sdoctors,JoinType.LEFT);
                    Join<ServiceDetail,EmployeeProfile> billingDoctor=root.join(ServiceDetail_.bdoctors,JoinType.LEFT);
	                Predicate serId=root.get(ServiceDetail_.serviceDetailId).in(serviceDetailIdList);
	                Predicate dos=root.get(ServiceDetail_.serviceDetailDos).in(maxDos);
	                Predicate finalPredicate=cb.and(serId,dos);
	              return query.where(finalPredicate).getRestriction();
			 }
		 };
	}
	
	/**
	 * Get frequently used cpt codes depend upon place of service id 
	 * @param posTypeId
	 * @return
	 */
	public static Specification<Cpt> getCptcodes(final String posTypeId){
		return new Specification<Cpt>() {
			@Override
			public Predicate toPredicate(Root<Cpt> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate frequentPredicate = null;
				if(posTypeId.equals("0")){
					frequentPredicate=root.get(Cpt_.cptCptcode).in("99221","99222","99223","99231","99232",
							"99233","99291","99292","99238","99239","99251","99252","99253","99254","99255","99234",
							"99235","99236","99217","99218","99219","99220","99281","99282","99283","99284","99285");
				}
				else if(posTypeId.equals("1")){
					frequentPredicate=root.get(Cpt_.cptCptcode).in("99304","99305","99306","99307","99308","99309","99315","99316");
				}
				query.where(frequentPredicate).orderBy(cb.asc(root.get(Cpt_.cptCptcode)));
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Discharging the patient
	 * @param patientId
	 * @param admissionId
	 * @return
	 */
	public static Specification<Admission> getUpdateRowData(final Integer patientId, final int admissionId) {
		return new Specification<Admission>(){
			@Override
			public Predicate toPredicate(Root<Admission> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate updateDisDate=cb.and(cb.equal(root.get(Admission_.admissionPatientId),patientId),cb.equal(root.get(Admission_.admissionId),admissionId));
				return updateDisDate;
			}
		};
	}
	
	/**
	 * to get previous Visit Dx codes
	 * @param patientId
	 * @return
	 */
	public static Specification<ServiceDetail> getpreviousVisitDxCodes(final Integer patientId) {
		return new Specification<ServiceDetail>() {
			@Override
			public Predicate toPredicate(Root<ServiceDetail> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate prevDxCodes=cb.equal(root.get(ServiceDetail_.serviceDetailPatientid), patientId);
				return prevDxCodes;
			}
		};
	}
	
	/**
     * Predicate to get all provider details in a particular group
	 * @return
	*/
	public static Specification<EmployeeProfile> getListOfProviders() {
		return new Specification<EmployeeProfile>() {
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate groupId=root.get(EmployeeProfile_.empProfileGroupid).in(-1,-2);
				Predicate active=cb.equal(root.get(EmployeeProfile_.empProfileIsActive), true);
				Predicate finalPredicate=cb.and(groupId,active);
				return finalPredicate;
			}
		};
	}
	
	/**
	 * To get services information
	 * @param patientid
	 * @param admissiondate
	 * @return
	 */
	public static Specification<ServiceDetail> getServicesList(final Integer patientid,final Date admissionDate) {
		return new Specification<ServiceDetail>() {
			@Override
			public Predicate toPredicate(Root<ServiceDetail> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<ServiceDetail,Cpt> cptJoin=root.join(ServiceDetail_.cpt,JoinType.INNER);
				Predicate patientId=cb.equal(root.get(ServiceDetail_.serviceDetailPatientid),patientid);
				Predicate dos=cb.greaterThanOrEqualTo(root.get(ServiceDetail_.serviceDetailDos),admissionDate);
				Predicate cptTypeCheck=cb.or(cb.equal(cptJoin.get(Cpt_.cptCpttype), 1),cb.like(cptJoin.get(Cpt_.cptCptcode),"%novisit%" ));
				query.where(dos,patientId,cptTypeCheck).orderBy(cb.desc(root.get(ServiceDetail_.serviceDetailDos)),cb.desc(root.get(ServiceDetail_.serviceDetailId)));			
			return query.getRestriction();
			}

		};
	}
	
}