package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.CodingSystems;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.ProblemList_;

/**
 * Contains set of predicates corresponds to the problem list
 * @author software
 *
 */
public class ProblemListSpecification {

	/**
	 * Specification to retrieve patient active problems
	 * @param patientid
	 * @return
	 */
	public static Specification<ProblemList> getproblemlist(final int patientid) {
		return new Specification<ProblemList>() {

			@Override
			public Predicate toPredicate(Root<ProblemList> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {			
				Predicate predicate=cb.equal(root.get(ProblemList_.problemListPatientId), patientid);
				Predicate predicate1=cb.equal(root.get(ProblemList_.problemListIsactive), true);
				Predicate predicate2=cb.equal(root.get(ProblemList_.problemListIsresolved), false);
				Predicate status = cb.and(predicate1, predicate2);
				Predicate result=cb.and(predicate,status);
				
				return result;

			}
		};

	}
	
	/**
	 * Specification to retrieve all patient problems
	 * @param patientid
	 * @return
	 */
	public static Specification<ProblemList> getAllproblemlist(final int patientid) {
		return new Specification<ProblemList>() {

			@Override
			public Predicate toPredicate(Root<ProblemList> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {			

				Predicate predicate=cb.equal(root.get(ProblemList_.problemListPatientId), patientid);
				Predicate predicate1=cb.notEqual(root.get(ProblemList_.problemListIsactive), false);
				Predicate result = cb.and(predicate, predicate1);
				return result;

			}
		};

	}
	
	/**
	 * Specification to retrieve patient inactive problems
	 * @param patientid
	 * @return
	 */
	public static Specification<ProblemList> getInactiveResolved(final int patientid) {
		return new Specification<ProblemList>() {

			@Override
			public Predicate toPredicate(Root<ProblemList> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {			
				Predicate predicate=cb.equal(root.get(ProblemList_.problemListPatientId), patientid);
				Predicate predicate1=cb.equal(root.get(ProblemList_.problemListIsactive), false);
				Predicate predicate2=cb.equal(root.get(ProblemList_.problemListIsresolved), true);
				Predicate status = cb.or(predicate1, predicate2);
				Predicate result=cb.and(predicate,status);
				return result;

			}
		};

	}
	
	/**
	 * Specification to retrieve data corresponding to edit page by considering problem ID
	 * @param patientid
	 * @param problemid
	 * @return
	 */
	public static Specification<ProblemList> getDataToEdit(final int patientid,final int problemid) {
		return new Specification<ProblemList>() {

			@Override
			public Predicate toPredicate(Root<ProblemList> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {			
				Predicate predicate=cb.equal(root.get(ProblemList_.problemListPatientId), patientid);
				Predicate predicate1=cb.equal(root.get(ProblemList_.problemListId), problemid);
				Predicate result=cb.and(predicate,predicate1);
				return result;

			}
		};

	}
	/**
	 * Specification to retrieve data corresponding to problem list page by considering dxCode
	 * @param dxCode
	 * @param patienId
	 * @return
	 */
	public static Specification<ProblemList> getByDxcode(final String dxCode,final int patienId) {
		return new Specification<ProblemList>() {

			@Override
			public Predicate toPredicate(Root<ProblemList> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {			
				Predicate dxcodePred=cb.equal(root.get(ProblemList_.problemListDxCode),dxCode);
				Predicate patientPred=cb.equal(root.get(ProblemList_.problemListPatientId),patienId);
				Predicate activePred=cb.equal(root.get(ProblemList_.problemListIsactive),true);
				Predicate result=cb.and(dxcodePred,patientPred,activePred);
				return result;

			}
		};

	}	
	
	/**
	 * Get leaf details of patient
	 * @param patientId
	 * @return
	 */
	public static Specification<ProblemList> getPatientProblems(final Integer patientId)
	{
		return new Specification<ProblemList>() {
			
			@Override
			public Predicate toPredicate(Root<ProblemList> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<ProblemList,CodingSystems> problemCodingJoin= root.join(ProblemList_.codingSystems,JoinType.INNER);
				Predicate pred = cb.equal(root.get(ProblemList_.problemListPatientId),patientId);
				query.where(pred).orderBy(cb.asc(root.get(ProblemList_.problemListDxCode)));
				return pred;
			}
		};
	}
	
	/**
	 * Check active status
	 * @param active
	 * @return
	 */
	public static Specification<ProblemList> isActive(final Boolean active)
	{
		return new Specification<ProblemList>() {
			
			@Override
			public Predicate toPredicate(Root<ProblemList> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(ProblemList_.problemListIsactive),active);
				return pred;
			}
		};
	}

}
