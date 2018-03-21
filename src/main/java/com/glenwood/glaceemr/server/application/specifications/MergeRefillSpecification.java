package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AlertEvent_;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox_;

public class MergeRefillSpecification {

	public static Specification<AlertEvent> getAlertEvent(final int userId,final String AlertIds ){
		return new Specification<AlertEvent>() {

			@Override
			public Predicate toPredicate(Root<AlertEvent> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String[] ids=AlertIds.split(",");
				List<Integer> idlist=new ArrayList<Integer>(); 
				for(int i=0;i<ids.length;i++){
					idlist.add(Integer.parseInt(ids[i]));
				}
				Predicate modified=cb.equal(root.get(AlertEvent_.alertEventModifiedby), userId);
				query.where(cb.and(modified, root.get(AlertEvent_.alertEventId).in(idlist)));
				
				return query.getRestriction();
			}
			
		};
		
	}
	
	public static Specification<SSMessageInbox> getCloseAlert(final String alertId){
		return new Specification<SSMessageInbox>() {

			@Override
			public Predicate toPredicate(Root<SSMessageInbox> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String[] ids=alertId.split(",");
				List<Integer> idlist=new ArrayList<Integer>(); 
				for(int i=0;i<ids.length;i++){
					idlist.add(Integer.parseInt(ids[i]));
				}
				
				return query.where(root.get(SSMessageInbox_.ssMessageInboxId).in(idlist)).getRestriction();
			}
			
		};
	}
	
	public static Specification<SSMessageInbox> getPatientName(final String alertId){
		return new Specification<SSMessageInbox>() {

			@Override
			public Predicate toPredicate(Root<SSMessageInbox> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return query.where(cb.equal(root.get(SSMessageInbox_.ssMessageInboxId),alertId)).getRestriction();
			}
			
		};
	}
	
	public static Specification<SSMessageInbox> getPatientList(final String lname,final String fname,final Timestamp dob,final String gender){
		return new Specification<SSMessageInbox>() {

			@Override
			public Predicate toPredicate(Root<SSMessageInbox> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate plname=cb.equal(root.get(SSMessageInbox_.ssMessageInboxReceivedPatientLastname), lname);
				Predicate pfname=cb.equal(root.get(SSMessageInbox_.ssMessageInboxReceivedPatientFirstname), fname);
				Predicate pgender=cb.equal(root.get(SSMessageInbox_.ssMessageInboxReceivedPatientGender), gender);
				Predicate pdob=cb.equal(root.get(SSMessageInbox_.ssMessageInboxReceivedPatientDob), dob);
				return query.where(cb.and(plname,pfname,pgender,pdob)).getRestriction();
			}
			
		};
	}
	
}
