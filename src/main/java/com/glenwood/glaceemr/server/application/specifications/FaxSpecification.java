package com.glenwood.glaceemr.server.application.specifications;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.Trimspec;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.DoctorSign;
import com.glenwood.glaceemr.server.application.models.DoctorSign_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.FaxFolder;
import com.glenwood.glaceemr.server.application.models.FaxInbox;
import com.glenwood.glaceemr.server.application.models.FaxInbox_;
import com.glenwood.glaceemr.server.application.models.FaxOutbox;
import com.glenwood.glaceemr.server.application.models.FaxOutbox_;
import com.glenwood.glaceemr.server.application.models.FaxStatus;
import com.glenwood.glaceemr.server.application.models.FaxStatus_;
import com.glenwood.glaceemr.server.application.models.FaxType;
import com.glenwood.glaceemr.server.application.models.InFaxStatus;
import com.glenwood.glaceemr.server.application.models.InFaxStatus_;
/**
 * Specification for Fax module
 * @author Anil Kumar
 *
 */
public class FaxSpecification {
	@Autowired
	EntityManager entityManager;

	public static Specification<FaxOutbox> getOutboxDetails(final Integer fax_outbox_folderid, final Integer userId) {
		return new Specification<FaxOutbox>() {
			@SuppressWarnings("unused")
			@Override
			public Predicate toPredicate(Root<FaxOutbox> root,
				   CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Join<FaxOutbox, FaxFolder> h496join= root.join(FaxOutbox_.faxFolder,JoinType.INNER);
				Join<FaxOutbox, FaxStatus> statusjoin= root.join(FaxOutbox_.faxStatus,JoinType.INNER);
				Join<FaxOutbox, EmployeeProfile> empProfile1join= root.join(FaxOutbox_.chart_users_1,JoinType.LEFT);
				Join<FaxOutbox, EmployeeProfile> empProfile2join= root.join(FaxOutbox_.chart_users_2,JoinType.LEFT);
				Join<FaxOutbox, EmployeeProfile> empProfile3join= root.join(FaxOutbox_.chart_users_3,JoinType.LEFT);
				Predicate condition = null;
				if(userId==-1){
					condition = cb.and(cb.equal(root.get(FaxOutbox_.fax_outbox_folderid),fax_outbox_folderid),cb.equal(statusjoin.get(FaxStatus_.groupid),7),cb.equal(root.get(FaxOutbox_.fax_outbox023Faxbox),1));
				}else{
					condition = cb.and(cb.equal(root.get(FaxOutbox_.fax_outbox_folderid),fax_outbox_folderid),cb.equal(statusjoin.get(FaxStatus_.groupid),7),cb.equal(root.get(FaxOutbox_.fax_outbox023Faxbox),1),cb.equal(root.get(FaxOutbox_.fax_outbox_forwardedto),userId),cb.equal(root.get(FaxOutbox_.fax_outbox_createdby),userId));
				}
				return cq.where(condition).getRestriction();
			}			
		};
	}
	
	@SuppressWarnings("unused")
	public static Predicate getFaxOutboxDetails(final Integer fax_outbox_folderid, final Integer userId, Root<FaxOutbox> root,
			   CriteriaQuery<?> cq, CriteriaBuilder cb){
		
		Join<FaxOutbox, FaxFolder> h496join= root.join(FaxOutbox_.faxFolder,JoinType.INNER);
		Join<FaxOutbox, FaxStatus> statusjoin= root.join(FaxOutbox_.faxStatus,JoinType.INNER);
		Join<FaxOutbox, EmployeeProfile> empProfile1join= root.join(FaxOutbox_.chart_users_1,JoinType.LEFT);
		Join<FaxOutbox, EmployeeProfile> empProfile2join= root.join(FaxOutbox_.chart_users_2,JoinType.LEFT);
		Join<FaxOutbox, EmployeeProfile> empProfile3join= root.join(FaxOutbox_.chart_users_3,JoinType.LEFT);
		Predicate condition = null;
		if(userId==-1){
			condition = cb.and(cb.equal(root.get(FaxOutbox_.fax_outbox_folderid),fax_outbox_folderid),cb.equal(statusjoin.get(FaxStatus_.groupid),7),cb.equal(root.get(FaxOutbox_.fax_outbox023Faxbox),1));
		}else{
			condition = cb.and(cb.equal(root.get(FaxOutbox_.fax_outbox_folderid),fax_outbox_folderid),cb.equal(statusjoin.get(FaxStatus_.groupid),7),cb.equal(root.get(FaxOutbox_.fax_outbox023Faxbox),1),cb.equal(root.get(FaxOutbox_.fax_outbox_forwardedto),userId),cb.equal(root.get(FaxOutbox_.fax_outbox_createdby),userId));
		}
		
		return cq.where(condition).getRestriction();
		
	}

	public static Specification<FaxInbox> getInboxDetails(final Integer fax_inbox_folderid, final Integer fax_inbox_statusid, final Integer fax_inbox_forwardeduserid, final Integer h491017_faxbox) {
		return new Specification<FaxInbox>() {
			@SuppressWarnings("unused")
			@Override
			public Predicate toPredicate(Root<FaxInbox> root,
				   CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Join<FaxInbox, FaxFolder> faxFolderjoin = root.join(FaxInbox_.faxFolder,JoinType.INNER);
				Join<FaxInbox, InFaxStatus> statusjoin = root.join(FaxInbox_.faxStatus,JoinType.INNER);
				Join<FaxInbox, FaxType> faxTypejoin = root.join(FaxInbox_.faxType,JoinType.INNER);
				Join<FaxInbox, EmployeeProfile> empProfile1join = root.join(FaxInbox_.emplopyeeProfile,JoinType.LEFT);
				Join<FaxInbox, EmployeeProfile> empProfile2join = root.join(FaxInbox_.emplopyeeProfile1,JoinType.LEFT);
				Predicate condition = null;
				if(fax_inbox_statusid == -1){
					condition = cb.and(cb.equal(statusjoin.get(InFaxStatus_.groupid),6),cb.equal(root.get(FaxInbox_.fax_inbox_folderid),fax_inbox_folderid),cb.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid),fax_inbox_forwardeduserid),cb.equal(root.get(FaxInbox_.fax_inbox017Faxbox),h491017_faxbox),cb.equal(root.get(FaxInbox_.fax_inbox018Isenabled),true));
				}else{
					condition = cb.and(cb.equal(statusjoin.get(InFaxStatus_.groupid),6),cb.equal(root.get(FaxInbox_.fax_inbox_folderid),fax_inbox_folderid),cb.equal(root.get(FaxInbox_.fax_inbox_statusid),0),cb.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid),fax_inbox_forwardeduserid),cb.equal(root.get(FaxInbox_.fax_inbox017Faxbox),h491017_faxbox),cb.equal(root.get(FaxInbox_.fax_inbox018Isenabled),true));
				}
				return cq.where(condition).getRestriction();
			}			
		};
	}
	@SuppressWarnings("unused")
	public static Predicate getFaxInboxDetails(final Integer fax_inbox_folderid, final Integer fax_inbox_statusid, final Integer fax_inbox_forwardeduserid, final Integer h491017_faxbox,Root<FaxInbox> root,CriteriaQuery<?> cq, CriteriaBuilder cb) {
		
				Join<FaxInbox, FaxFolder> faxFolderjoin = root.join(FaxInbox_.faxFolder,JoinType.INNER);
				Join<FaxInbox, InFaxStatus> statusjoin = root.join(FaxInbox_.faxStatus,JoinType.INNER);
				Join<FaxInbox, FaxType> faxTypejoin = root.join(FaxInbox_.faxType,JoinType.INNER);
				Join<FaxInbox, EmployeeProfile> empProfile1join = root.join(FaxInbox_.emplopyeeProfile,JoinType.LEFT);
				Join<FaxInbox, EmployeeProfile> empProfile2join = root.join(FaxInbox_.emplopyeeProfile1,JoinType.LEFT);
				Predicate condition = null;
				if(fax_inbox_statusid == -1){
					condition = cb.and(cb.equal(statusjoin.get(InFaxStatus_.groupid),6),cb.equal(root.get(FaxInbox_.fax_inbox_folderid),fax_inbox_folderid),cb.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid),fax_inbox_forwardeduserid),cb.equal(root.get(FaxInbox_.fax_inbox017Faxbox),h491017_faxbox),cb.equal(root.get(FaxInbox_.fax_inbox018Isenabled),true));
				}else{
					condition = cb.and(cb.equal(statusjoin.get(InFaxStatus_.groupid),6),cb.equal(root.get(FaxInbox_.fax_inbox_folderid),fax_inbox_folderid),cb.equal(root.get(FaxInbox_.fax_inbox_statusid),0),cb.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid),fax_inbox_forwardeduserid),cb.equal(root.get(FaxInbox_.fax_inbox017Faxbox),h491017_faxbox),cb.equal(root.get(FaxInbox_.fax_inbox018Isenabled),true));
				}
				return cq.where(condition).getRestriction();
			}			

	
	public static Specification<FaxInbox>faxForward(final String faxId[]){
		return new Specification<FaxInbox>() {
			@Override
			public Predicate toPredicate(Root<FaxInbox> root,
				   CriteriaQuery<?> cq, CriteriaBuilder cb) {				
				Predicate predicate=cq.where(root.get(FaxInbox_.fax_inbox_id).in(Arrays.asList(faxId))).getRestriction();
				return predicate;
			}
		};
	}

	public static Specification<FaxInbox> getInFaxDetails(final Integer faxId, final Integer userId, final Integer faxTab, final Integer faxFolder){
			return new Specification<FaxInbox>() {
			@Override
			public Predicate toPredicate(Root<FaxInbox> root,
				   CriteriaQuery<?> cq, CriteriaBuilder cb) {
				   Predicate condition = null;
				   if(faxTab == 1){
					   condition = cb.and(cb.equal(root.get(FaxInbox_.fax_inbox_id),faxId), cb.equal(root.get(FaxInbox_.fax_inbox_folderid),faxFolder), cb.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid),0));
				   }else{
					   condition = cb.and(cb.equal(root.get(FaxInbox_.fax_inbox_id),faxId), cb.equal(root.get(FaxInbox_.fax_inbox_folderid),faxFolder), cb.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid),userId));
				   }
				return cq.where(condition).getRestriction();
			}
		};
	}
	 
	public static Specification<FaxInbox> lastFaxReceivedTime(final Integer faxId){
		return new Specification<FaxInbox>() {
		@Override
		public Predicate toPredicate(Root<FaxInbox> root,
			   CriteriaQuery<?> cq, CriteriaBuilder cb) {
			   Predicate condition = null;
			   condition = cb.and(cb.equal(root.get(FaxInbox_.fax_inbox_id), faxId));
			return cq.where(condition).getRestriction();
		}
	};
}

	public static Specification<FaxOutbox> getOutFaxDetails(final Integer userId,final Integer faxId,final Integer faxTab, final Integer faxFolder){
		return new Specification<FaxOutbox>() {
			@Override
			public Predicate toPredicate(Root<FaxOutbox> root,
				   CriteriaQuery<?> cq, CriteriaBuilder cb) {
			       Predicate condition = null;
			    		   if(faxTab == 1){
							   condition = cb.and(cb.equal(root.get(FaxOutbox_.fax_outbox_id),faxId), cb.equal(root.get(FaxOutbox_.fax_outbox_recipientname),String.valueOf(faxFolder))/*, cb.equal(root.get(FaxOutbox_.fax_outbox_createddate),Date.valueOf(String.valueOf(0)))*/);
						   }else{
							   condition = cb.and(cb.equal(root.get(FaxOutbox_.fax_outbox_id),faxId), cb.equal(root.get(FaxOutbox_.fax_outbox_recipientname),String.valueOf(faxFolder))/* cb.equal(root.get(FaxOutbox_.fax_outbox_createddate),Date.valueOf(String.valueOf(userId))*/);
						   }  
			return cq.where(condition).getRestriction();
			}
		};
}

	public static Specification<DoctorSign> getSignatureDetails( final Integer empId ,final boolean accessType) {
		return new Specification<DoctorSign>() {
			@SuppressWarnings("unused")
			@Override
			public Predicate toPredicate(Root<DoctorSign> root,
				   CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Integer[] groupId = new Integer[]{-1,-7,-10};
				Predicate condition = null;
				Join<DoctorSign, EmployeeProfile> join1 = root.join(DoctorSign_.empLoginId,JoinType.INNER);
				if(!accessType){
					condition = cb.and(cb.equal(root.get(DoctorSign_.loginid),empId), cb.notEqual(root.get(DoctorSign_.filename), "-1"), cb.equal(join1.get(EmployeeProfile_.empProfileIsActive), true));
				}else{
					condition = cb.and(cb.notEqual(root.get(DoctorSign_.filename), "-1"), cb.equal(join1.get(EmployeeProfile_.empProfileIsActive), true), join1.get(EmployeeProfile_.empProfileGroupid).in(-1,-10), cb.equal(root.get(DoctorSign_.signaccess), true));
				}
					cq.orderBy(cb.desc(join1.get(EmployeeProfile_.empProfileGroupid)), cb.asc(join1.get(EmployeeProfile_.empProfileFullname)));
				return cq.where(condition).getRestriction();
			}
		};
	}
	
	/**
	 * code to return employee login id
	 */
	
	public static Specification<EmployeeProfile> getEmployeeLoginId(final int empId){
		
		return new Specification<EmployeeProfile>() {
		
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> cq, CriteriaBuilder cb){
				
				Predicate condition = null;
				condition = cb.and(cb.equal(root.get(EmployeeProfile_.empProfileLoginid),empId));
				return cq.where(condition).getRestriction();
				
			}
			
		};
		
	}
	
	/**
	 * code to get sign access type 
	 */
	
	public static Specification<DoctorSign> getSignAccessType(final int loginId){
		
		return new Specification<DoctorSign>() {
		
			@Override
			public Predicate toPredicate(Root<DoctorSign> root, CriteriaQuery<?> cq, CriteriaBuilder cb){
				
				Predicate condition = null;
				condition = cb.and(cb.equal(root.get(DoctorSign_.loginid), loginId));
				return cq.where(condition).getRestriction();
				
			}
			
		};
		
	}
	
	/**
	 * code to return fax details of selected fax Id's
	 */
	
	public static Specification<FaxInbox> getSelectedFaxDetails(final List<Integer> faxId){
		
		return new Specification<FaxInbox>() {
			
			@Override
			public Predicate toPredicate(Root<FaxInbox> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Expression<Integer> expr=root.get(FaxInbox_.fax_inbox_id);
				Predicate predicate=query.where(expr.in(faxId)).getRestriction();
				return predicate;
				
			}
			
		};
		
	}
public static Specification<FaxOutbox> getSelectedFaxDetails1(final List<Integer> faxId){
		
		return new Specification<FaxOutbox>() {
			
			@Override
			public Predicate toPredicate(Root<FaxOutbox> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Expression<Integer> expr=root.get(FaxOutbox_.fax_outbox_id);
				Predicate predicate=query.where(expr.in(faxId)).getRestriction();
				return predicate;
				
			}
			
		};
		
	}

public static Specification<FaxInbox> readFax(final Integer faxId){
	return new Specification<FaxInbox>() {
		@Override
		public Predicate toPredicate(Root<FaxInbox> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
			Predicate predicate=cq.where(cb.equal(root.get(FaxInbox_.fax_inbox_id),faxId)).getRestriction();
			return predicate;
		}
	};
}

@SuppressWarnings("unused")
public static Predicate getReadFax(final Integer faxId,Root<FaxInbox> root,CriteriaQuery<?> cq, CriteriaBuilder cb){
	
	Join<FaxInbox, FaxFolder> faxFolderjoin = root.join(FaxInbox_.faxFolder,JoinType.INNER);
	Join<FaxInbox, InFaxStatus> statusjoin = root.join(FaxInbox_.faxStatus,JoinType.INNER);
	Join<FaxInbox, FaxType> faxTypejoin = root.join(FaxInbox_.faxType,JoinType.INNER);
	Join<FaxInbox, EmployeeProfile> empProfile1join = root.join(FaxInbox_.emplopyeeProfile,JoinType.LEFT);
	Join<FaxInbox, EmployeeProfile> empProfile2join = root.join(FaxInbox_.emplopyeeProfile1,JoinType.LEFT);
	Predicate condition = null;
	condition = cb.and(cb.equal(root.get(FaxInbox_.fax_inbox_id),faxId));
	return cq.where(condition).getRestriction();
	
}

@SuppressWarnings("unused")
public static Expression<Boolean> getSearchFax(String nameString,int faxFolder, int faxTab, int faxLocation,int forwardUserId, Root<FaxInbox> root, CriteriaQuery<Object> cq,CriteriaBuilder cb) {

	Join<FaxInbox, FaxFolder> faxFolderjoin = root.join(FaxInbox_.faxFolder,JoinType.INNER);
	Join<FaxInbox, InFaxStatus> statusjoin = root.join(FaxInbox_.faxStatus,JoinType.INNER);
	Join<FaxInbox, FaxType> faxTypejoin = root.join(FaxInbox_.faxType,JoinType.INNER);
	Join<FaxInbox, EmployeeProfile> empProfile1join = root.join(FaxInbox_.emplopyeeProfile,JoinType.LEFT);
	Join<FaxInbox, EmployeeProfile> empProfile2join = root.join(FaxInbox_.emplopyeeProfile1,JoinType.LEFT);
	Predicate condition = null;
	if(faxTab == 1){
		condition = cb.and(cb.like(cb.lower(cb.trim(Trimspec.BOTH,root.get(FaxInbox_.fax_inbox_tsid))),
				nameString.trim().toLowerCase()+"%"), cb.equal(root.get(FaxInbox_.fax_inbox_folderid),faxFolder),cb.equal(root.get(FaxInbox_.fax_inbox017Faxbox),faxLocation),cb.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid),0));
	}else{
		condition = cb.and(cb.like(cb.lower(cb.trim(Trimspec.BOTH,root.get(FaxInbox_.fax_inbox_tsid))),
				nameString.trim().toLowerCase()+"%"), cb.equal(root.get(FaxInbox_.fax_inbox_folderid),faxFolder),cb.equal(root.get(FaxInbox_.fax_inbox017Faxbox),faxLocation),cb.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid),forwardUserId));
	}
	;
	return cq.where(condition).getRestriction();
}

@SuppressWarnings("unused")
public static Expression<Boolean> getOutBoxSearchFax(String nameString,int faxFolder, int faxTab, int faxLocation,int forwardUserId, Root<FaxOutbox> root, CriteriaQuery<Object> cq,CriteriaBuilder cb) {

	Join<FaxOutbox, FaxFolder> h496join= root.join(FaxOutbox_.faxFolder,JoinType.INNER);
	Join<FaxOutbox, FaxStatus> statusjoin= root.join(FaxOutbox_.faxStatus,JoinType.INNER);
	Join<FaxOutbox, EmployeeProfile> empProfile1join= root.join(FaxOutbox_.chart_users_1,JoinType.LEFT);
	Join<FaxOutbox, EmployeeProfile> empProfile2join= root.join(FaxOutbox_.chart_users_2,JoinType.LEFT);
	Join<FaxOutbox, EmployeeProfile> empProfile3join= root.join(FaxOutbox_.chart_users_3,JoinType.LEFT);
	Predicate condition = null;
	if(faxTab == 1){
		condition = cb.and(cb.like(cb.lower(cb.trim(Trimspec.BOTH,root.get(FaxOutbox_.fax_outbox_recipientname))),
				nameString.trim().toLowerCase()+"%"),cb.equal(root.get(FaxOutbox_.fax_outbox_folderid),faxFolder),cb.equal(root.get(FaxOutbox_.fax_outbox023Faxbox),faxLocation));
	}else{

		condition = cb.and(cb.like(cb.lower(cb.trim(Trimspec.BOTH,root.get(FaxOutbox_.fax_outbox_recipientname))),
				nameString.trim().toLowerCase()+"%"),cb.equal(root.get(FaxOutbox_.fax_outbox_folderid),faxFolder),cb.equal(root.get(FaxOutbox_.fax_outbox023Faxbox),faxLocation),cb.equal(root.get(FaxOutbox_.fax_outbox_forwardedto),forwardUserId),cb.equal(root.get(FaxOutbox_.fax_outbox_createdby),forwardUserId));
	}
	
	return cq.where(condition).getRestriction();
}


}
