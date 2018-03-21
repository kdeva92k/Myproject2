package com.glenwood.glaceemr.server.application.specifications.print;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.LetterHeaderEmp;
import com.glenwood.glaceemr.server.application.models.LetterHeaderEmp_;
import com.glenwood.glaceemr.server.application.models.LetterHeaderPos;
import com.glenwood.glaceemr.server.application.models.LetterHeaderPos_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.print.LetterHeaderContent;
import com.glenwood.glaceemr.server.application.models.print.LetterHeaderContent_;


public class LetterHeaderSpecification {
	
	public static Specification<LetterHeaderContent> getLetterHeaderContent(final Integer headerId){

		return new Specification<LetterHeaderContent>(){

			@Override
			public Predicate toPredicate(Root<LetterHeaderContent> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate headerCondition=cb.equal(root.get(LetterHeaderContent_.letterHeaderContentStyleMapId),headerId);
				
				return headerCondition;
			}
		};
	}

	public static Specification<LetterHeaderContent> getLetterHeaderContent(final Integer headerId, final List<Integer> variantId){

		return new Specification<LetterHeaderContent>(){

			@Override
			public Predicate toPredicate(Root<LetterHeaderContent> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(LetterHeaderContent_.letterHeaderContentStyleMapId),headerId), 
					root.get(LetterHeaderContent_.letterHeaderContentVariant).in(variantId),
					root.get(LetterHeaderContent_.letterHeaderContentFlag).isNotNull(),
					cb.notEqual(root.get(LetterHeaderContent_.letterHeaderContentFlag),-1));
				return query.getRestriction();
			}
		};
	}
	
	public static Specification<LetterHeaderEmp> getEmpDetails(final Integer headerId, final Integer variantId){

		return new Specification<LetterHeaderEmp>(){

			@Override
			public Predicate toPredicate(Root<LetterHeaderEmp> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate pred=cb.equal(root.get(LetterHeaderEmp_.letterHeaderEmpMapId),headerId);
				Predicate varPred=cb.equal(root.get(LetterHeaderEmp_.letterHeaderEmpVariant),variantId);
				query.where(pred,varPred).orderBy(cb.asc(root.get(LetterHeaderEmp_.letterHeaderEmpOrder)));
				
				return query.getRestriction();
			}
		};
	}
	
	public static Specification<LetterHeaderPos> getPOSDetails(final Integer headerId, final Integer variantId){

		return new Specification<LetterHeaderPos>(){

			@Override
			public Predicate toPredicate(Root<LetterHeaderPos> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate pred=cb.equal(root.get(LetterHeaderPos_.letterHeaderPosMapId),headerId);
				Predicate varPred=cb.equal(root.get(LetterHeaderPos_.letterHeaderPosVariant),variantId);
				query.where(pred,varPred).orderBy(cb.asc(root.get(LetterHeaderPos_.letterHeaderPosOrder)));
				
				return query.getRestriction();
			}
		};
	}
	
	public static Specification<LetterHeaderEmp> fetchEmpDetails(final Integer headerId, final Integer variantId){

		return new Specification<LetterHeaderEmp>(){

			@Override
			public Predicate toPredicate(Root<LetterHeaderEmp> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate pred=cb.equal(root.get(LetterHeaderEmp_.letterHeaderEmpMapId),headerId);
				Predicate varPred=cb.equal(root.get(LetterHeaderEmp_.letterHeaderEmpVariant),variantId);
				Fetch<LetterHeaderEmp, EmployeeProfile> empJoin = root.fetch(LetterHeaderEmp_.empProfile, JoinType.LEFT);
				empJoin.fetch(EmployeeProfile_.specialityTable, JoinType.LEFT);
				
				query.where(pred,varPred).orderBy(cb.asc(root.get(LetterHeaderEmp_.letterHeaderEmpOrder)));
				
				return query.getRestriction();
			}
		};
	}
	
	public static Specification<LetterHeaderPos> fetchPOSDetails(final Integer headerId, final Integer variantId){

		return new Specification<LetterHeaderPos>(){

			@Override
			public Predicate toPredicate(Root<LetterHeaderPos> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate pred=cb.equal(root.get(LetterHeaderPos_.letterHeaderPosMapId),headerId);
				Predicate varPred=cb.equal(root.get(LetterHeaderPos_.letterHeaderPosVariant),variantId);
				Fetch<LetterHeaderPos, PosTable> posJoin = root.fetch(LetterHeaderPos_.posTable, JoinType.LEFT);
				posJoin.fetch(PosTable_.placeOfService, JoinType.LEFT);
				posJoin.fetch(PosTable_.posType, JoinType.LEFT);
				query.where(pred,varPred).orderBy(cb.asc(root.get(LetterHeaderPos_.letterHeaderPosOrder)));
				
				return query.getRestriction();
			}
		};
	}
}