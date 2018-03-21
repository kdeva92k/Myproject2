package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.PortalMessage;
import com.glenwood.glaceemr.server.application.models.PortalMessage_;

public class PortalMessagesSpecification {
	
	
	
	/**
	 * @param patientId	: used to get total messages list of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PortalMessage> getTotalMessagesByPatientId(final int patientId)
	   {
		   return new Specification<PortalMessage>() {

			@Override
			public Predicate toPredicate(Root<PortalMessage> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				root.join(PortalMessage_.patientRegistrationTable,JoinType.INNER);
				
				Predicate patientTotalMessages=cq.where(cb.equal(root.get(PortalMessage_.patientid), patientId)).getRestriction();
				
				return patientTotalMessages;
			}
			   
		};
	   }
	
	/**
	 * @param patientId	: used to get total messages list of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PortalMessage> getMessagesThreadByParentId(final int parentId)
	   {
		   return new Specification<PortalMessage>() {

			@Override
			public Predicate toPredicate(Root<PortalMessage> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				root.join(PortalMessage_.patientRegistrationTable,JoinType.INNER);
				
				Predicate patientThreadMessages=cq.where(cb.equal(root.get(PortalMessage_.parentid), parentId))
						.orderBy(cb.desc(root.get(PortalMessage_.id))).distinct(true)
						.getRestriction();
				
				return patientThreadMessages;
			}
			   
		};
	   }
	

	/**
	 * @param patientId	: used to get inbox messages list of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PortalMessage> getInboxMessagesListByPatientId(final int patientId)
	   {
		   return new Specification<PortalMessage>() {

			@Override
			public Predicate toPredicate(Root<PortalMessage> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				root.join(PortalMessage_.patientRegistrationTable,JoinType.INNER);
				
				Predicate patientMessageList=cq.where(cb.equal(root.get(PortalMessage_.patientid), patientId),cb.notEqual(root.get(PortalMessage_.messageBy), patientId)).getRestriction();
				return patientMessageList;
			}
			   
		};
	   }

	
	/**
	 * @param patientId	: used to get sent messages list of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PortalMessage> getSentMessagesListByPatientId(final int patientId)
	   {
		   return new Specification<PortalMessage>() {

			@Override
			public Predicate toPredicate(Root<PortalMessage> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				root.join(PortalMessage_.patientRegistrationTable,JoinType.INNER);
				
				Predicate patientMessageList=cq.where(cb.equal(root.get(PortalMessage_.patientid), patientId),cb.equal(root.get(PortalMessage_.messageBy), patientId)).getRestriction();
				return patientMessageList;
			}
			   
		};
	   }
	
	/**
	 * @param patientId	: used to get sent messages list of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PortalMessage> deleteMessageById(final int messageId)
	   {
		   return new Specification<PortalMessage>() {

			@Override
			public Predicate toPredicate(Root<PortalMessage> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
								
				return cb.equal(root.get(PortalMessage_.id), messageId);
				
			}
			   
		};
	   }
	
	/**
	 * @param patientId	: used to get sent messages list of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PortalMessage> deleteMessageThreadById(final int patientId, final int threadId)
	   {
		   return new Specification<PortalMessage>() {

			@Override
			public Predicate toPredicate(Root<PortalMessage> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
								
				return cb.and(cb.equal(root.get(PortalMessage_.patientid), patientId), cb.equal(root.get(PortalMessage_.parentid), threadId));
				
			}
			   
		};
	   }
	
	

	
	public static Pageable createPortalMessagesPageRequestByDescDate(int pageIndex, int offset) {
		
	    return new PageRequest(pageIndex, offset, Sort.Direction.DESC,"mdate");
	}
	

}
