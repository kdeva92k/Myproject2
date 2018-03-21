package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.ChartStatus;
import com.glenwood.glaceemr.server.application.models.ChartStatus_;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.InitialSettings_;

public class InitialSettingsSpecification {
	/**
	 * Specification to get the list based on id
	 * @param id
	 * @return Specification<InitialSettings>
	 */
	public static Specification<InitialSettings> optionId(final Integer id)
	{
		return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate optionId = cb.equal(root.get(InitialSettings_.initialSettingsOptionId),id);
				return optionId;
			}
		};
	}
	
	/**
	 * Specification to get the list of status
	 * @param id
	 * @return Specification<H068>
	 */
	public static Specification<ChartStatus> getIdType(final Integer type)
	{
		return new Specification<ChartStatus>() {

			@Override
			public Predicate toPredicate(Root<ChartStatus> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate typeId = cb.equal(root.get(ChartStatus_.chart_status_reference_id),type);
				return typeId;
			}
		};
	}
	
	/**
	 * Specification to get the list based on option name
	 * @param optionName
	 * @return Specification<InitialSettings>
	 */
	public static Specification<InitialSettings> optionName(final String optionName)
	{
		return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicate = cb.like(root.get(InitialSettings_.initialSettingsOptionName),"%"+optionName+"%");
				return predicate;
			}
		};
	}
	
	/**
	 * Specification to get the list based on type
	 * @param id
	 * @return Specification<InitialSettings>
	 */
	public static Specification<InitialSettings> optionType(final Integer type)
	{
		return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate optionId = cb.equal(root.get(InitialSettings_.initialSettingsOptionType),type);
				return optionId;
			}
		};
	}
	
	/**
	 * Specification to get the list based on visible
	 * @param id
	 * @return Specification<InitialSettings>
	 */
	public static Specification<InitialSettings> optionVisible(final Boolean visible)
	{
		return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate optionId = cb.equal(root.get(InitialSettings_.initialSettingsVisible),visible);
				return optionId;
			}
		};
	}
}
