package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;

public class ChartSpecification {
	/**
	 * Specification to get the chart having patient Id
	 * @param map id
	 * @return Specification<Chart>
	 */
	public static Specification<Chart> patientId(final Integer patientId)
	{
		return new Specification<Chart>() {

			@Override
			public Predicate toPredicate(Root<Chart> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetLabsMapId = cb.equal(root.get(Chart_.chartPatientid),patientId);
				return flowsheetLabsMapId;
			}
		};
	}
	
	
	
	/**
	 * Specification to get the chart id of patient having patient Id
	 * @param patientId
	 * @return Specification<Chart>
	 */
	public static Specification<Chart> getChartId(final int patientId)
	{
		return new Specification<Chart>() {
			
			@Override
			public Predicate toPredicate(Root<Chart> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate chartIdPredicate=cb.equal(root.get(Chart_.chartPatientid),patientId);
				return chartIdPredicate;
			}
		};
	}
	
	/**
	 * Specification to get the chart having chart Id
	 * @param map id
	 * @return Specification<Chart>
	 */
	public static Specification<Chart> findByChartId(final Integer chartId)
	{
		return new Specification<Chart>() {

			@Override
			public Predicate toPredicate(Root<Chart> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(Chart_.chartId),chartId);
				return pred;
			}
		};
	}
	
	
	
	
	
	
	
	
	
	
	
		
		
	
}
