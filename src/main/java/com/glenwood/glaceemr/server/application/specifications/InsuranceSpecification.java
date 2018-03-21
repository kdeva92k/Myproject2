package com.glenwood.glaceemr.server.application.specifications;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.InsCompAddr;
import com.glenwood.glaceemr.server.application.models.InsCompAddr_;
import com.glenwood.glaceemr.server.application.models.InsCompany;
import com.glenwood.glaceemr.server.application.models.InsCompany_;
import com.glenwood.glaceemr.server.application.models.InsuranceFilterBean;

public class InsuranceSpecification {
	
	/**
	 * @return list of filtered insurances
	 */	
	public static Specification<InsCompAddr> getInsurancesBy(final InsuranceFilterBean filterBean)
	   {
		   return new Specification<InsCompAddr>() {

				@Override
				public Predicate toPredicate(Root<InsCompAddr> root,
						CriteriaQuery<?> cq, CriteriaBuilder cb) {
					
					Join<InsCompAddr, InsCompany> insComJoin=root.join(InsCompAddr_.insCompany);
					
					/*Final Predicate Build*/
					
					Predicate insShortIdPredicate=null;
					if(filterBean.getInsShortId()!=null)
						insShortIdPredicate=cb.and(cb.equal(root.get(InsCompAddr_.insCompAddrInscompanyId), Integer.parseInt(filterBean.getInsShortId())));
					
					Predicate insNamePredicate=null;
					if(filterBean.getInsName()!=null&&!filterBean.getInsName().equalsIgnoreCase("-1")&&filterBean.getInsName().length()>0)
						insNamePredicate=cb.and(cb.equal(cb.upper(insComJoin.get(InsCompany_.insCompanyName)), filterBean.getInsName()));
					
					Predicate insAddressPredicate=null;
					if(filterBean.getInsAddress()!=null&&!filterBean.getInsAddress().equalsIgnoreCase("-1")&&filterBean.getInsAddress().length()>0)
						insAddressPredicate=cb.and(cb.equal(cb.upper(root.get(InsCompAddr_.insCompAddrAddress)), filterBean.getInsAddress()));
					
					Predicate insStatePredicate=null;
					if(filterBean.getInsState()!=null&&!filterBean.getInsState().equalsIgnoreCase("-1")&&filterBean.getInsState().length()>0)
						insStatePredicate=cb.and(cb.equal(cb.upper(root.get(InsCompAddr_.insCompAddrState)), filterBean.getInsState().toUpperCase()));
					
					Predicate insIsActivePredicate=null;
					if(filterBean.getIsInsActive()!=null){
					if(!filterBean.getIsInsActive())
						insIsActivePredicate=cb.equal(insComJoin.get(InsCompany_.insCompanyIsactive), false);
					else
						insIsActivePredicate=cb.equal(insComJoin.get(InsCompany_.insCompanyIsactive), true);
					}else
						insIsActivePredicate=cb.equal(insComJoin.get(InsCompany_.insCompanyIsactive), true);
					
					List<Predicate> predicateList = new LinkedList<Predicate>();
					if(insShortIdPredicate!=null)
						predicateList.add(insShortIdPredicate);
					if(insNamePredicate!=null)
						predicateList.add(insNamePredicate);
					if(insAddressPredicate!=null)
						predicateList.add(insAddressPredicate);
					if(insStatePredicate!=null)
						predicateList.add(insStatePredicate);
					if(insIsActivePredicate!=null)
						predicateList.add(insIsActivePredicate);
					
					Predicate[] predicateArray = new Predicate[predicateList.size()];
					predicateList.toArray(predicateArray);
					
					if(Long.class!=cq.getResultType()){
						root.fetch(InsCompAddr_.insCompany);
					}
					
					Predicate filterPredicate=cq.where(predicateArray).getRestriction();
					
					return filterPredicate;
				}
				   
			};
	   }
	
	
	public static Pageable createInsurancePaginationRequest(int pageIndex, int offset){
		
		return new PageRequest(pageIndex, offset,  Sort.Direction.ASC, "insCompAddrInsName");
	}
	
}
