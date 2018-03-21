package com.glenwood.glaceemr.server.application.specifications;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.glenwood.glaceemr.server.application.models.ClinicalSystem;
import com.glenwood.glaceemr.server.application.models.ClinicalSystemOrder;
import com.glenwood.glaceemr.server.application.models.ClinicalSystemOrder_;
import com.glenwood.glaceemr.server.application.models.ClinicalSystem_;
import com.glenwood.glaceemr.server.application.models.RosElement;
import com.glenwood.glaceemr.server.application.models.RosElement_;

public class ROSSpecification {

	
	/**
	 * Get active ROS Systems
	 * 
	 * @param templateId
	 * @return
	 */
	public static Specification<ClinicalSystem> getActiveROSSystems(final Integer templateId){

		return new Specification<ClinicalSystem>(){

			
			@Override
			public Predicate toPredicate(Root<ClinicalSystem> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Join<ClinicalSystem,ClinicalSystemOrder> paramJoin=root.join(ClinicalSystem_.clinicalSystemOrders,JoinType.LEFT);	
				paramJoin.on(cb.equal(paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderTemplateid),templateId));
				Predicate isActivePred=cb.equal(root.get(ClinicalSystem_.clinicalSystemIsactive),true);
				Predicate gwidNotNull=cb.isNotNull(root.get(ClinicalSystem_.clinicalSystemRosGwid));
				query.orderBy(cb.asc(paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderRos)));
				return cb.and(isActivePred,gwidNotNull);
			}
		};
	}
	
	

	/**
	 * 
	 * Get active ROS Elements for given system
	 * 
	 * @param systemId
	 * @param templateId
	 * @return
	 */
	public static Specification<RosElement> getActiveROSElementBySystem(final List<String> systemId){

		return new Specification<RosElement>(){

			@Override
			public Predicate toPredicate(Root<RosElement> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(RosElement_.clinicalTextMapping, JoinType.LEFT);
				Predicate sysPred=root.get(RosElement_.rosElementSystemId).in(systemId);
				Predicate isActivePred=cb.equal(root.get(RosElement_.rosElementIsactive),true);
				query.orderBy(cb.asc(root.get(RosElement_.rosElementOrderby)));
				return cb.and(sysPred,isActivePred);
			}
		};
	}

	/**
	 * 
	 * Get all active ROS Elements 
	 * 
	 * @param systemId
	 * @param templateId
	 * @return
	 */
	public static Specification<RosElement> getAllActiveROSElements(){

		return new Specification<RosElement>(){

			@Override
			public Predicate toPredicate(Root<RosElement> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(RosElement_.rosElementIsactive),true);
			}
		};
	}

	
}
