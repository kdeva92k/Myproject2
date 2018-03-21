package com.glenwood.glaceemr.server.application.specifications;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Flowsheet;
import com.glenwood.glaceemr.server.application.models.FlowsheetClinicalParam;
import com.glenwood.glaceemr.server.application.models.FlowsheetClinicalParam_;
import com.glenwood.glaceemr.server.application.models.FlowsheetDrug;
import com.glenwood.glaceemr.server.application.models.FlowsheetDrug_;
import com.glenwood.glaceemr.server.application.models.FlowsheetDx;
import com.glenwood.glaceemr.server.application.models.FlowsheetDx_;
import com.glenwood.glaceemr.server.application.models.FlowsheetLab;
import com.glenwood.glaceemr.server.application.models.FlowsheetLab_;
import com.glenwood.glaceemr.server.application.models.FlowsheetParam;
import com.glenwood.glaceemr.server.application.models.FlowsheetParam_;
import com.glenwood.glaceemr.server.application.models.FlowsheetType;
import com.glenwood.glaceemr.server.application.models.FlowsheetType_;
import com.glenwood.glaceemr.server.application.models.Flowsheet_;
import com.glenwood.glaceemr.server.application.models.LabStandardCode;
import com.glenwood.glaceemr.server.application.models.LabStandardCode_;
import com.glenwood.glaceemr.server.application.models.LabStandardGroup;
import com.glenwood.glaceemr.server.application.models.LabStandardGroup_;
import com.glenwood.glaceemr.server.application.models.ParamStandardCode;
import com.glenwood.glaceemr.server.application.models.ParamStandardCode_;
import com.glenwood.glaceemr.server.application.models.ParamStandardGroup;
import com.glenwood.glaceemr.server.application.models.ParamStandardGroup_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;


public class FlowsheetSpecification {
	/**
	 * Specification to get the list of flowsheets which are active
	 * @param isActive
	 * @return Specification<Flowsheet>
	 */
	public static Specification<Flowsheet> flowsheetIsactive(final Boolean isActive)
	{
		return new Specification<Flowsheet>() {

			@Override
			public Predicate toPredicate(Root<Flowsheet> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetIsactive = cb.equal(root.get(Flowsheet_.flowsheetIsactive),isActive);
				return flowsheetIsactive;
			}
		};
	}

	/**
	 * Specification to get the list of flowsheets which belong to a specific flowsheet type
	 * @param flowsheetType
	 * @return Specification<Flowsheet>
	 */
	public static Specification<Flowsheet> flowsheetType(final Integer typeId)
	{
		return new Specification<Flowsheet>() {

			@Override
			public Predicate toPredicate(Root<Flowsheet> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetType = cb.equal(root.get(Flowsheet_.flowsheetType),typeId);
				return flowsheetType;
			}
		};
	}

	/**
	 * Specification to get the list of flowsheets with details based on flowsheet type along with flowsheet_type table details
	 * @param flowsheetType
	 * @return Specification<Flowsheet>
	 */
	public static Specification<Flowsheet> flowsheetList(final Integer flowsheetType)
	{
		return new Specification<Flowsheet>() {

			@Override
			public Predicate toPredicate(Root<Flowsheet> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.join("flowsheetTypeTable",JoinType.INNER);
				Predicate[] restrictions = new Predicate[] {
						flowsheetType(flowsheetType).toPredicate(root, query, cb),
						flowsheetIsactive(true).toPredicate(root, query, cb)
				};
				Predicate flowsheetList = cb.and(restrictions);
				Order[] orderBy = new Order[] {
						cb.asc(root.get( Flowsheet_.flowsheetType )),
						cb.asc(root.get( Flowsheet_.flowsheetId ))
				};
				query.orderBy(orderBy);
				return flowsheetList;
			}
		};
	}
	
	/**
	 * Specification to get the flowsheet having a flowsheet Id
	 * @param flowsheetId
	 * @return Specification<Flowsheet>
	 */
	public static Specification<Flowsheet> flowsheetId(final Integer flowsheetId)
	{
		return new Specification<Flowsheet>() {

			@Override
			public Predicate toPredicate(Root<Flowsheet> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetType = cb.equal(root.get(Flowsheet_.flowsheetId),flowsheetId);
				return flowsheetType;
			}
		};
	}
	

	/**
	 * Specification to get the list of flowsheets types which are active
	 * @param isActive
	 * @return Specification<FlowsheetType>
	 */
	public static Specification<FlowsheetType> flowsheetTypeIsactive(final Boolean isActive)
	{
		return new Specification<FlowsheetType>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetType> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetIsactive = cb.equal(root.get(FlowsheetType_.flowsheetTypeIsactive),isActive);
				return flowsheetIsactive;
			}
		};
	}
	

	/**
	 * Specification to get the list of configured flowsheet labs based on map Id
	 * @param map id
	 * @return Specification<FlowsheetLab>
	 */
	public static Specification<FlowsheetLab> flowsheetLabMapId(final Integer mapId)
	{
		return new Specification<FlowsheetLab>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetLab> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetLabsMapId = cb.equal(root.get(FlowsheetLab_.flowsheetLabMapid),mapId);
				return flowsheetLabsMapId;
			}
		};
	}
	
	/**
	 * Specification to get the list of configured flowsheet labs based on gender and is active
	 * @param map id
	 * @return Specification<FlowsheetLab>
	 */
	public static Specification<FlowsheetLab> flowsheetLabGenderAndIsActive(final List<Integer> gender,final Integer patientId)
	{
		return new Specification<FlowsheetLab>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetLab> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Subquery<Integer> subquery = query.subquery(Integer.class);
				Root<PatientRegistration> subqueryFrom = subquery.from(PatientRegistration.class);
				Expression<Integer> subQExp=subqueryFrom.get(PatientRegistration_.patientRegistrationSex);
				subquery.select(subQExp);
				subquery.where(cb.equal(subqueryFrom.get(PatientRegistration_.patientRegistrationId), patientId));
				
				Join<FlowsheetLab,LabStandardGroup> join=root.join("labStandardGroupTable", JoinType.INNER);
				Predicate flowsheetLabsGenderIsActive = cb.and(cb.or(join.get(LabStandardGroup_.labStandardGroupGender).in(gender),cb.in(join.get(LabStandardGroup_.labStandardGroupGender)).value(subquery)),cb.equal(join.get(LabStandardGroup_.labStandardGroupIsactive),true));
				return flowsheetLabsGenderIsActive;
			}
		};
	}

	
	/**
	 * Specification to get the list of configured flowsheet params based on map Id
	 * @param map id
	 * @return Specification<FlowsheetParam>
	 */
	public static Specification<FlowsheetParam> flowsheetParamMapId(final Integer mapId)
	{
		return new Specification<FlowsheetParam>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetParam> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetParamMapId = cb.equal(root.get(FlowsheetParam_.flowsheetParamMapid),mapId);
				return flowsheetParamMapId;
			}
		};
	}
	
	/**
	 * Specification to get the list of configured flowsheet params based on gender and is active
	 * @param map id
	 * @return Specification<FlowsheetParam>
	 */
	public static Specification<FlowsheetParam> flowsheetParamGenderAndIsActive(final List<Integer> gender,final Integer patientId)
	{
		return new Specification<FlowsheetParam>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetParam> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Subquery<Integer> subquery = query.subquery(Integer.class);
				Root<PatientRegistration> subqueryFrom = subquery.from(PatientRegistration.class);
				Expression<Integer> subQExp=subqueryFrom.get(PatientRegistration_.patientRegistrationSex);
				subquery.select(subQExp);
				subquery.where(cb.equal(subqueryFrom.get(PatientRegistration_.patientRegistrationId), patientId));
				
				Join<FlowsheetParam,ParamStandardGroup> join=root.join("paramStandardGroupTable", JoinType.INNER);
				Predicate FlowsheetParamGenderIsActive = cb.and(cb.or(join.get(ParamStandardGroup_.paramStandardGroupGender).in(gender),cb.in(join.get(ParamStandardGroup_.paramStandardGroupGender)).value(subquery)),cb.equal(join.get(ParamStandardGroup_.paramStandardGroupIsactive),true));
				return FlowsheetParamGenderIsActive;
			}
		};
	}


	/**
	 * Specification to get the list of FlowsheetDx's for a particular flowsheet Id
	 * @param flowsheetDxMapid
	 * @return Specification<FlowsheetDx>
	 */
	public static Specification<FlowsheetDx> flowsheetDxMapid(final Integer flowsheetDxMapid)
	{
		return new Specification<FlowsheetDx>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetDx> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetDxMapidPred = cb.equal(root.get(FlowsheetDx_.flowsheetDxMapid),flowsheetDxMapid);
				return flowsheetDxMapidPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of FlowsheetDx's for a particular code
	 * @param flowsheetDxMapid
	 * @return Specification<FlowsheetDx>
	 */
	public static Specification<FlowsheetDx> flowsheetDxCode(final String flowsheetDxCode)
	{
		return new Specification<FlowsheetDx>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetDx> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetDxMapidPred = cb.equal(root.get(FlowsheetDx_.flowsheetDxCode),flowsheetDxCode);
				return flowsheetDxMapidPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of FlowsheetDx's based on flowsheet Id
	 * @param flowsheetType
	 * @param flowsheetId
	 * @return Specification<FlowsheetDx>
	 */
	public static Specification<FlowsheetDx> flowsheetDxCodeBasedOnFlowsheetTypeId(final Integer flowsheetType,final Integer flowsheetId)
	{
		return new Specification<FlowsheetDx>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetDx> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				Join<FlowsheetDx,Flowsheet> join=root.join("flowsheetTable", JoinType.INNER);
				Predicate[] restrictions = new Predicate[] {
						cb.equal(join.get("flowsheetType"),2),
						cb.equal(join.get("flowsheetIsactive"),true)
				};
				join.on(restrictions);
				join.join("flowsheetTypeTable",JoinType.INNER);
				Predicate flowsheetPred=cb.equal(join.get("flowsheetId"),flowsheetId);
				return flowsheetPred;
			}
		};
	}


	/**
	 * Specification to get the list of configured flowsheet labs based on map Id
	 * @param map id
	 * @return Specification<LabStandardCode>
	 */
	public static Specification<LabStandardCode> labStandardCodeGroupIds(final List<Integer> GroupId)
	{
		return new Specification<LabStandardCode>() {

			@Override
			public Predicate toPredicate(Root<LabStandardCode> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labStandardCodeGroupId = root.get(LabStandardCode_.labStandardCodeGroupId).in(GroupId);
				return labStandardCodeGroupId;
			}
		};
	}
	
	/**
	 * Specification to get the list of configured flowsheet code based on map Id
	 * @param map id
	 * @return Specification<LabStandardCode>
	 */
	public static Specification<LabStandardCode> labStandardCodeGroupId(final Integer GroupId)
	{
		return new Specification<LabStandardCode>() {

			@Override
			public Predicate toPredicate(Root<LabStandardCode> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labStandardCodeGroupId = cb.equal(root.get(LabStandardCode_.labStandardCodeGroupId),GroupId);
				return labStandardCodeGroupId;
			}
		};
	}


	/**
	 * Specification to get the list of distinct lab standard groups in group id
	 * @param group id
	 * @return Specification<LabStandardGroup>
	 */
	public static Specification<LabStandardGroup> labStandardGroupIdIsActive(final List<Integer> groupId)
	{
		return new Specification<LabStandardGroup>() {

			@Override
			public Predicate toPredicate(Root<LabStandardGroup> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.distinct(true);
				query.orderBy(cb.asc(root.get(LabStandardGroup_.labStandardGroupName)));
				Predicate labStandardGroupId = cb.and(root.get(LabStandardGroup_.labStandardGroupId).in(groupId),cb.equal(root.get(LabStandardGroup_.labStandardGroupIsactive),true));
				return labStandardGroupId;
			}
		};
	}
	
	/**
	 * Specification to get the list of lab standard group for group id
	 * @param group id
	 * @return Specification<LabStandardGroup>
	 */
	public static Specification<LabStandardGroup> labStandardGroup(final Integer groupId)
	{
		return new Specification<LabStandardGroup>() {

			@Override
			public Predicate toPredicate(Root<LabStandardGroup> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labStandardGroup = cb.equal(root.get(LabStandardGroup_.labStandardGroupId),groupId);
				return labStandardGroup;
			}
		};
	}

	/**
	 * Specification to get the list of lab standard group which are active/inactive
	 * @param group id
	 * @return Specification<LabStandardGroup>
	 */
	public static Specification<LabStandardGroup> labStandardGroupIsActive(final Boolean isActive)
	{
		return new Specification<LabStandardGroup>() {

			@Override
			public Predicate toPredicate(Root<LabStandardGroup> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labStandardGroupIsActive = cb.equal(root.get(LabStandardGroup_.labStandardGroupIsactive),isActive);
				return labStandardGroupIsActive;
			}
		};
	}

	/**
	 * Specification to get the list of configured flowsheet params based on map Id
	 * @param map id
	 * @return Specification<ParamStandardCode>
	 */
	public static Specification<ParamStandardCode> paramStandardCodeGroupIds(final List<Integer> GroupId)
	{
		return new Specification<ParamStandardCode>() {

			@Override
			public Predicate toPredicate(Root<ParamStandardCode> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate paramStandardCodeGroupId = root.get(ParamStandardCode_.paramStandardCodeGroupId).in(GroupId);
				return paramStandardCodeGroupId;
			}
		};
	}
	
	/**
	 * Specification to get the list of configured flowsheet params based on map Id
	 * @param map id
	 * @return Specification<ParamStandardCode>
	 */
	public static Specification<ParamStandardCode> paramStandardCodeGroupId(final Integer GroupId)
	{
		return new Specification<ParamStandardCode>() {

			@Override
			public Predicate toPredicate(Root<ParamStandardCode> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate paramStandardCodeGroupId = cb.equal(root.get(ParamStandardCode_.paramStandardCodeGroupId),GroupId);
				return paramStandardCodeGroupId;
			}
		};
	}

	/**
	 * Specification to get the list of distinct param standard groups in group id
	 * @param group id
	 * @return Specification<ParamStandardGroup>
	 */
	public static Specification<ParamStandardGroup> paramStandardGroupIdIsActive(final List<Integer> groupId)
	{
		return new Specification<ParamStandardGroup>() {

			@Override
			public Predicate toPredicate(Root<ParamStandardGroup> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.distinct(true);
				query.orderBy(cb.asc(root.get(ParamStandardGroup_.paramStandardGroupName)));
				Predicate labStandardGroupId = cb.and(root.get(ParamStandardGroup_.paramStandardGroupId).in(groupId),cb.equal(root.get(ParamStandardGroup_.paramStandardGroupIsactive),true));
				return labStandardGroupId;
			}
		};
	}
	
	/**
	 * Specification to get the list of param standard group for group id
	 * @param group id
	 * @return Specification<LabStandardGroup>
	 */
	public static Specification<ParamStandardGroup> paramStandardGroup(final Integer groupId)
	{
		return new Specification<ParamStandardGroup>() {

			@Override
			public Predicate toPredicate(Root<ParamStandardGroup> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate paramStandardGroup = cb.equal(root.get(ParamStandardGroup_.paramStandardGroupId),groupId);
				return paramStandardGroup;
			}
		};
	}
	
	/**
	 * Specification to get the list of param standard group which are active/inactive
	 * @param group id
	 * @return Specification<ParamStandardGroup>
	 */
	public static Specification<ParamStandardGroup> paramStandardGroupIsActive(final Boolean isActive)
	{
		return new Specification<ParamStandardGroup>() {

			@Override
			public Predicate toPredicate(Root<ParamStandardGroup> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labStandardGroup = cb.equal(root.get(ParamStandardGroup_.paramStandardGroupIsactive),isActive);
				return labStandardGroup;
			}
		};
	}
	
	/**
	 * Specification to get the list of flowsheet clinical elements configured based on element type(i.e. vitals/clinical elements)
	 * @param element type
	 * @param flowsheet Id
	 * @return Specification<FlowsheetClinicalParam>
	 */
	public static Specification<FlowsheetClinicalParam> flowsheetClinicalParam(final Integer elementType,final Integer flowsheetId)
	{
		return new Specification<FlowsheetClinicalParam>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetClinicalParam> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate elementTypePred=cb.equal(root.get(FlowsheetClinicalParam_.flowsheetClinicalParamMapElementtype),elementType);
				Predicate flowsheetIdPred=cb.equal(root.get(FlowsheetClinicalParam_.flowsheetClinicalParamMapid),flowsheetId);
				Predicate flowsheetClinicalParam = cb.and(elementTypePred,flowsheetIdPred);
				return flowsheetClinicalParam;
			}
		};
	}
	
	/**
	 * Specification to get the list of flowsheet classes configured based on flowsheet Id
	 * @param flowsheet Id
	 * @return Specification<FlowsheetDrug>
	 */
	public static Specification<FlowsheetDrug> flowsheetDrugsMapId(final Integer flowsheetId)
	{
		return new Specification<FlowsheetDrug>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetDrug> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate flowsheetIdPred=cb.equal(root.get(FlowsheetDrug_.flowsheetDrugMapid),flowsheetId);
				return flowsheetIdPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of flowsheet classes configured based class Id
	 * @param flowsheet Id
	 * @return Specification<FlowsheetDrug>
	 */
	public static Specification<FlowsheetDrug> flowsheetDrugsClass(final String classId)
	{
		return new Specification<FlowsheetDrug>() {

			@Override
			public Predicate toPredicate(Root<FlowsheetDrug> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate classIdPred=cb.equal(root.get(FlowsheetDrug_.flowsheetDrugClassId),classId);
				return classIdPred;
			}
		};
	}

}
