package com.glenwood.glaceemr.server.application.services.phonemessages;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;

public class PhoneMessagesSpecification {

	/**
	 * To fetch all the phone message encounter based on patient id
	 * @param patientId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Specification<Encounter> getEncounters(final String patientId, final String startDate, final String endDate){
		return new Specification<Encounter>() {
			@SuppressWarnings("deprecation")
			
			Date dateStarting=null;
			Date dateEnding=null;
			
			@SuppressWarnings("deprecation")
			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<Encounter,Chart> patientIdJoin=root.join(Encounter_.chart,JoinType.INNER);
				Join<Encounter,EmployeeProfile> createdByJoin=root.join(Encounter_.encounterCreatedByEmpProf,JoinType.INNER);
				Join<Encounter,EmployeeProfile> serviceDrJoin=root.join(Encounter_.empProfileEmpId,JoinType.LEFT);
				Predicate[] predicateApUserId;
				
					try{
						if(!startDate.equals("-1"))
							dateStarting=new Date(startDate);
						if(!endDate.equals("-1"))
						dateEnding=new Date(endDate);
					}
					catch(Exception e){
						System.out.println("Date parsing error.\nStartdate "+startDate+"\nEnddate "+endDate+"\nPatientId "+patientId);
					}
					
					if(dateStarting!=null&&dateEnding!=null){
						predicateApUserId= new Predicate[] {
								cb.equal(root.get(Encounter_.encounterType), "2"),
								cb.greaterThanOrEqualTo(root.get(Encounter_.encounterCreatedDate),dateStarting),
								cb.lessThanOrEqualTo(root.get(Encounter_.encounterCreatedDate),dateEnding),
								cb.equal(patientIdJoin.get(Chart_.chartPatientid), patientId)
						};
					}
					else
						if(dateStarting!=null&&dateEnding==null){
							predicateApUserId= new Predicate[] {
									cb.equal(root.get(Encounter_.encounterType), "2"),
									cb.greaterThanOrEqualTo(root.get(Encounter_.encounterCreatedDate),dateStarting),
									cb.equal(patientIdJoin.get(Chart_.chartPatientid), patientId)
							};
						}
					else
						if(dateStarting==null&&dateEnding!=null){
								predicateApUserId= new Predicate[] {
										cb.equal(root.get(Encounter_.encounterType), "2"),
										cb.lessThanOrEqualTo(root.get(Encounter_.encounterCreatedDate),dateEnding),
										cb.equal(patientIdJoin.get(Chart_.chartPatientid), patientId)
								};
							}
					else{
						predicateApUserId= new Predicate[] {
								cb.equal(root.get(Encounter_.encounterType), "2"),
								cb.equal(patientIdJoin.get(Chart_.chartPatientid), patientId)
						};
					}
					
					Predicate predicate=cb.and(predicateApUserId);

					root.join("chart",JoinType.INNER);
					root.join("encounterCreatedByEmpProf",JoinType.INNER);
					root.join("empProfileEmpId",JoinType.LEFT);

					root.fetch(Encounter_.chart,JoinType.INNER);
					root.fetch(Encounter_.encounterCreatedByEmpProf,JoinType.INNER);
					root.fetch(Encounter_.empProfileEmpId,JoinType.LEFT);
					
					cq.orderBy(cb.desc(root.get(Encounter_.encounterCreatedDate)));
				return predicate;
			}
		};
	}

	/**
	 * To fetch all the phone message encounter based on patient id
	 * @param patientId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Specification<Encounter> getEncounterDetails(final String encounterId){
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<Encounter,Chart> patientIdJoin=root.join(Encounter_.chart,JoinType.INNER);
				Join<Encounter,EmployeeProfile> createdByDrJoin=root.join(Encounter_.encounterCreatedByEmpProf,JoinType.INNER);
				Join<Encounter,EmployeeProfile> serviceDrJoin=root.join(Encounter_.empProfileEmpId,JoinType.LEFT);
				


					Predicate predicate=cb.equal(root.get(Encounter_.encounterId), encounterId);

					root.join("chart",JoinType.INNER);
					root.join("encounterCreatedByEmpProf",JoinType.INNER);
					root.join("empProfileEmpId",JoinType.LEFT);			

					root.fetch(Encounter_.chart,JoinType.INNER);
					root.fetch(Encounter_.encounterCreatedByEmpProf,JoinType.INNER);
					root.fetch(Encounter_.empProfileEmpId,JoinType.LEFT);
				
				return predicate;
			}
		};
	}

	/**
	 * To fetch all the phone message encounter based on patient id
	 * @param patientId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Specification<Encounter> getEncounter(final String encounterId){
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

					Predicate predicate=cb.equal(root.get(Encounter_.encounterId), encounterId);

				return predicate;
			}
		};
	}
}