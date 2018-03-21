package com.glenwood.glaceemr.server.application.specifications;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure_;
import com.glenwood.glaceemr.server.application.models.VitalGroup;
import com.glenwood.glaceemr.server.application.models.VitalGroup_;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.models.VitalsParameter_;



public class VitalsSpecification {
	
	public static Specification<VitalGroup> getVitalActiveGrps(final Integer patientId,final Integer groupId,final short patientSex){

		return new Specification<VitalGroup>(){

			@Override
			public Predicate toPredicate(Root<VitalGroup> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<VitalGroup,VitalsParameter> paramJoin=root.join(VitalGroup_.vitalsParameter,JoinType.INNER);			
				Predicate isTrue=cb.equal(paramJoin.get(VitalsParameter_.vitalsParameterIsactive),true);
				Predicate patgender=cb.equal(paramJoin.get(VitalsParameter_.vitalsParameterGender),patientSex);
				Predicate patDefaultGender=cb.equal(paramJoin.get(VitalsParameter_.vitalsParameterGender),0);
				Predicate grpPred=null;
				if(groupId!=-1)
					grpPred=cb.equal(root.get(VitalGroup_.vitalGroupId),groupId);
				Predicate compPredicate=cb.or(patgender,patDefaultGender);
				query.orderBy(cb.asc(root.get(VitalGroup_.vitalGroupOrderby)));
				query.distinct(true);
				Predicate finalPred=null;
				if(groupId!=-1)
					finalPred=cb.and(isTrue,compPredicate,grpPred);
				else
					finalPred=cb.and(isTrue,compPredicate);
				return finalPred;
			}

		};
	}

	
	public static Specification<VitalsParameter> vitalsActiveGwId(final List<String> gwid){
		return new Specification<VitalsParameter>() {
			@Override
			public Predicate toPredicate(Root<VitalsParameter> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isGwid=root.get(VitalsParameter_.vitalsParameterGwId).in(gwid);
				Predicate isActive=cb.equal(root.get(VitalsParameter_.vitalsParameterIsactive),true);
				return cb.and(isGwid,isActive);
			}
		};
	}

	public static Specification<VitalsParameter> getUnitsForVitals(final List<String> gwids){
		return new Specification<VitalsParameter>() {
			@Override
			public Predicate toPredicate(Root<VitalsParameter> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(VitalsParameter_.unitsOfMeasureTable,JoinType.INNER);
				Predicate elementPred=root.get(VitalsParameter_.vitalsParameterGwId).in(gwids);
				return elementPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of distinct vital parameters which are active
	 * @param isActive
	 * @return Specification<VitalsParameter>
	 */
	public static Specification<VitalsParameter> vitalParametersIsActiveDistinct(final Boolean isActive)
	{
		return new Specification<VitalsParameter>() {

			@Override
			public Predicate toPredicate(Root<VitalsParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.distinct(true);
				query.orderBy(cb.asc(root.get(VitalsParameter_.vitalsParameterName)));
				Predicate vitalsIsactive = cb.equal(root.get(VitalsParameter_.vitalsParameterIsactive),isActive);
				return vitalsIsactive;
			}
		};
	}

	
	public static Specification<VitalsParameter> isVitalActive(final String gwid){
		return new Specification<VitalsParameter>() {
			@Override
			public Predicate toPredicate(Root<VitalsParameter> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isGwid=cb.equal(root.get(VitalsParameter_.vitalsParameterGwId),gwid);
				Predicate isActive=cb.equal(root.get(VitalsParameter_.vitalsParameterIsactive),true);
				return cb.and(isGwid,isActive);
			}
		};
	}
	
	public static Specification<UnitsOfMeasure> getUnit(final Integer unitId){
		return new Specification<UnitsOfMeasure>() {
			@Override
			public Predicate toPredicate(Root<UnitsOfMeasure> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(UnitsOfMeasure_.unitsOfMeasureId),unitId);
			}
		};
	}
	
	public static Specification<VitalsParameter> getVitalElements(final Short patientSex,final Integer groupId){
		return new Specification<VitalsParameter>() {
			@Override
			public Predicate toPredicate(Root<VitalsParameter> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(VitalsParameter_.vitalsParameterCondition,JoinType.INNER);
				root.fetch(VitalsParameter_.unitsOfMeasureTable,JoinType.LEFT);
				Predicate isTrue = cb.equal(root.get(VitalsParameter_.vitalsParameterIsactive),true);
				Predicate isGroup = cb.equal(root.get(VitalsParameter_.vitalsParameterGroup),groupId);
				Predicate gender = root.get(VitalsParameter_.vitalsParameterGender).in(patientSex,0);
				Predicate finalPred = cb.and(isTrue,isGroup,gender);
				query.orderBy(cb.asc(root.get(VitalsParameter_.vitalsParameterDisplayOrder)));
				return finalPred;
			}
		};
	}
	
}
