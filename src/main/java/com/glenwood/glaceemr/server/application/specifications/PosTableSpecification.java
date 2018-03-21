package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.PlaceOfService;
import com.glenwood.glaceemr.server.application.models.PlaceOfService_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;

public class PosTableSpecification {
	
	/**
	 * Get All POS details
	 * @return
	 */
	public static Specification<PosTable> getPOSDetails(){
		return new Specification<PosTable>() {

			@SuppressWarnings("unchecked")
			@Override
			public Predicate toPredicate(Root<PosTable> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Join<PosTable, PlaceOfService> posJoin = (Join<PosTable, PlaceOfService>) root.fetch(PosTable_.placeOfService,JoinType.LEFT);
				root.fetch(PosTable_.posType,JoinType.LEFT);
				
				
				Predicate isactivePred = cb.equal(root.get(PosTable_.posTableIsActive), true);
				Predicate poscodePred = cb.not((root.get(PosTable_.posTablePosCode).in(40)));
				Predicate placeIsActivePred = cb.equal(posJoin.get(PlaceOfService_.placeOfServiceIsActive), true);
				
				query.orderBy(cb.asc(root.get(PosTable_.posTableFacilityComments)));
				
				return cb.and(isactivePred, poscodePred, placeIsActivePred);
			
			}
		};
	}
	
	public static Specification<PosTable> getPOSDetailsById(final Integer posId){
		return new Specification<PosTable>() {

			@SuppressWarnings("unchecked")
			@Override
			public Predicate toPredicate(Root<PosTable> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Join<PosTable, PlaceOfService> posJoin = (Join<PosTable, PlaceOfService>) root.fetch(PosTable_.placeOfService,JoinType.LEFT);
				root.fetch(PosTable_.posType,JoinType.LEFT);
				
				
				Predicate isactivePred = cb.equal(root.get(PosTable_.posTableIsActive), true);
				Predicate poscodePred = cb.not((root.get(PosTable_.posTablePosCode).in(40)));
				Predicate placeIsActivePred = cb.equal(posJoin.get(PlaceOfService_.placeOfServiceIsActive), true);
				Predicate posIdpred = cb.equal(root.get(PosTable_.posTableRelationId), posId);
				
				query.orderBy(cb.asc(root.get(PosTable_.posTableFacilityComments)));
				
				return cb.and(posIdpred, isactivePred, poscodePred, placeIsActivePred);
			
			}
		};
	}
}
