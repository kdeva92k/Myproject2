package com.glenwood.glaceemr.server.application.specifications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.GrowthGraph;
import com.glenwood.glaceemr.server.application.models.GrowthGraph_;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;

public class GrowthGraphSpecification {

	/**
	 * Specification to fetch all graph list based on ageInMonth and sex
	 * @param ageInMonth
	 * @param sex (1 is male 0 is female)
	 * @return
	 */
	public static Specification<GrowthGraph> byAgeandSex(final Integer ageInMonth, final Integer sex)
	{
		return new Specification<GrowthGraph>() {

			@Override
			public Predicate toPredicate(Root<GrowthGraph> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {


				Predicate fromId=cb.greaterThanOrEqualTo(root.get(GrowthGraph_.growthgraph_id), 1);
				Predicate toId=cb.lessThanOrEqualTo(root.get(GrowthGraph_.growthgraph_id), 10);
				Predicate cond=cb.equal(root.get(GrowthGraph_.growthgraph_graph_is_active), true);
				Predicate ageInMonthLeast=cb.greaterThanOrEqualTo(root.get(GrowthGraph_.growthgraph_age_range_from), 0);
				Predicate ageInMonthHighest=cb.lessThanOrEqualTo(root.get(GrowthGraph_.growthgraph_age_range_from), ageInMonth);
				Predicate sexPredicate=null;
				if(sex==1)
					sexPredicate=cb.equal(root.get(GrowthGraph_.growthgraph_gender), "M");
				else
					sexPredicate=cb.equal(root.get(GrowthGraph_.growthgraph_gender), "F");
				Predicate predicate=cb.and(fromId,toId,cond,ageInMonthLeast,ageInMonthHighest,sexPredicate);
				cq.orderBy(cb.asc(root.get(GrowthGraph_.growthgraph_order_by)));
				return predicate;
			}

		};
	}
	public static Specification<Encounter> getWellVisitDetails(final int chartId)
	{
		return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Join<Encounter,Chart> chart=root.join(Encounter_.chart,JoinType.INNER);
				Join<Chart, ServiceDetail> serviceDetail = chart.join(Chart_.serviceDetail,JoinType.INNER);
				Join<ServiceDetail,Cpt> cpt = serviceDetail.join(ServiceDetail_.cpt,JoinType.INNER);
				
				List<String> cptCode = new ArrayList<String>(Arrays.asList("99381","99382","99383","99384","99391","99392","99393","99394"));
				cq.where(cb.and(
						cb.equal(cpt.get(Cpt_.cptIsactive), true),
						cpt.get(Cpt_.cptCptcode).in(cptCode),
						cb.equal(cb.function("date", Date.class, root.get(Encounter_.encounterDate)),cb.function("date", Date.class, serviceDetail.get(ServiceDetail_.serviceDetailDos))),
						cb.equal(root.get(Encounter_.encounterChartid), chartId)));
				
				cq.orderBy(cb.desc(root.get(Encounter_.encounterDate)));
				return null;

			}

		};
	}
}