package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.LeafLibrary_;


public class LeafLibrarySpecification {

	
	
	public static Specification<LeafLibrary> getLeafDetailsByTemplateId(final Integer templateId){

		return new Specification<LeafLibrary>(){

			@Override
			public Predicate toPredicate(Root<LeafLibrary> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(LeafLibrary_.leafLibrarySoaptemplateId),templateId);
			}

		};
	}

	public static Specification<LeafLibrary> getAllTemplates(){

		return new Specification<LeafLibrary>(){

			@Override
			public Predicate toPredicate(Root<LeafLibrary> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate pred = cb.equal(root.get(LeafLibrary_.leafLibraryIsactive),true);
				query.orderBy(cb.asc(root.get(LeafLibrary_.leafLibraryName)));
				
				return pred;
			}

		};
	}
	public static Specification<LeafLibrary> getByPrintStyleId(final Integer styleId){

		return new Specification<LeafLibrary>(){

			@Override
			public Predicate toPredicate(Root<LeafLibrary> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate pred = cb.equal(root.get(LeafLibrary_.leafLibraryIsactive),true);
				Predicate stylePred = cb.equal(root.get(LeafLibrary_.leafLibraryPrintStyleId),styleId);
				query.orderBy(cb.asc(root.get(LeafLibrary_.leafLibraryName)));
				
				
				return cb.and(pred,stylePred);
			}

		};
	}

	public static Specification<LeafLibrary> getLeafDetailsById(final Integer templateId){

		return new Specification<LeafLibrary>(){

			@Override
			public Predicate toPredicate(Root<LeafLibrary> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(LeafLibrary_.leafLibraryId),templateId);
			}

		};
	}
	
}
