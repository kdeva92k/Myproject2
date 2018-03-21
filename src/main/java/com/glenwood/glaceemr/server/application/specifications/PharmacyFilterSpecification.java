package com.glenwood.glaceemr.server.application.specifications;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.PharmDetails;
import com.glenwood.glaceemr.server.application.models.PharmDetails_;
import com.glenwood.glaceemr.server.application.models.PharmacyFilterBean;

public class PharmacyFilterSpecification {
	
	/**
	 * @return list of filtered pharmacies
	 */	
	public static Specification<PharmDetails> getPharmaciesBy(final PharmacyFilterBean filterBean)
	   {
		   return new Specification<PharmDetails>() {

				@Override
				public Predicate toPredicate(Root<PharmDetails> root,
						CriteriaQuery<?> cq, CriteriaBuilder cb) {
					
					/*Final Predicate Build*/
					
					Predicate storeNamePredicate=null;
					if(filterBean.getPharmacyName()!=null&&!filterBean.getPharmacyName().equalsIgnoreCase("-1")&&filterBean.getPharmacyName().length()>0)
						storeNamePredicate=cb.and(cb.like(cb.upper(root.get(PharmDetails_.pharmDetailsStoreName)), "%"+filterBean.getPharmacyName().toUpperCase()+"%"));
					
					Predicate pharmacyNCPDPIDPredicate=null;
					if(filterBean.getPharmacyNCPDPID()!=null&&!filterBean.getPharmacyNCPDPID().equalsIgnoreCase("-1")&&filterBean.getPharmacyNCPDPID().length()>0)
						pharmacyNCPDPIDPredicate=cb.and(cb.equal(cb.upper(root.get(PharmDetails_.pharmDetailsNcpdpid)), filterBean.getPharmacyNCPDPID()));
					
					Predicate pharmacyStoreNoPredicate=null;
					if(filterBean.getPharmacyStoreNo()!=null&&!filterBean.getPharmacyStoreNo().equalsIgnoreCase("-1")&&filterBean.getPharmacyStoreNo().length()>0)
						pharmacyStoreNoPredicate=cb.and(cb.equal(cb.upper(root.get(PharmDetails_.pharmDetailsStoreNumber)), filterBean.getPharmacyStoreNo()));
					
					Predicate pharmacyAddressPredicate=null;
					if(filterBean.getPharmacyAddress()!=null&&!filterBean.getPharmacyAddress().equalsIgnoreCase("-1")&&filterBean.getPharmacyAddress().length()>0)
						pharmacyAddressPredicate=cb.and(cb.like(cb.upper(root.get(PharmDetails_.pharmDetailsAddressLine1)), "%"+filterBean.getPharmacyAddress().toUpperCase()+"%"),
								cb.like(cb.upper(root.get(PharmDetails_.pharmDetailsAddressLine2)), "%"+filterBean.getPharmacyAddress().toUpperCase()+"%"));
					
					Predicate pharmacyCityPredicate=null;
					if(filterBean.getPharmacyCity()!=null&&!filterBean.getPharmacyCity().equalsIgnoreCase("-1")&&filterBean.getPharmacyCity().length()>0)
						pharmacyCityPredicate=cb.and(cb.equal(cb.upper(root.get(PharmDetails_.pharmDetailsCity)), filterBean.getPharmacyCity().toUpperCase()));
					
					Predicate pharmacyStatePredicate=null;
					if(filterBean.getPharmacyState()!=null&&!filterBean.getPharmacyState().equalsIgnoreCase("-1")&&filterBean.getPharmacyState().length()>0)
						pharmacyStatePredicate=cb.and(cb.equal(cb.upper(root.get(PharmDetails_.pharmDetailsState)), filterBean.getPharmacyState().toUpperCase()));
					
					Predicate pharmacyZipPredicate=null;
					if(filterBean.getPharmacyZip()!=null&&!filterBean.getPharmacyZip().equalsIgnoreCase("-1")&&filterBean.getPharmacyZip().length()>0)
						pharmacyZipPredicate=cb.and(cb.equal(cb.upper(root.get(PharmDetails_.pharmDetailsZip)), filterBean.getPharmacyZip().toUpperCase()));
					
					Predicate pharmacyPhoneNoPredicate=null;
					if(filterBean.getPharmacyPhoneNo()!=null&&!filterBean.getPharmacyPhoneNo().equalsIgnoreCase("-1")&&filterBean.getPharmacyPhoneNo().length()>0)
						pharmacyPhoneNoPredicate=cb.and(cb.equal(cb.upper(root.get(PharmDetails_.pharmDetailsPrimaryPhone)), filterBean.getPharmacyPhoneNo().toUpperCase()));
					
					
					Predicate pharmacyIs24HoursPredicate=null;
					if(filterBean.getPharmacyIs24Hours()!=null)
					if(filterBean.getPharmacyIs24Hours())
						pharmacyIs24HoursPredicate=cb.equal(cb.upper(root.get(PharmDetails_.pharmDetailsIs24hr)), "1");
					else
						pharmacyIs24HoursPredicate=cb.equal(cb.upper(root.get(PharmDetails_.pharmDetailsIs24hr)), "0");
								
					Predicate pharmacyIsECPSPredicate=null;
					if(filterBean.getPharmacyIsECPS()!=null)
					if(filterBean.getPharmacyIsECPS())
						pharmacyIsECPSPredicate=cb.equal(root.get(PharmDetails_.pharmDetailsServiceLevel), 1);
					else
						pharmacyIsECPSPredicate=cb.equal(root.get(PharmDetails_.pharmDetailsServiceLevel), 0);
					
					List<Predicate> predicateList = new LinkedList<Predicate>();
					if(storeNamePredicate!=null)
						predicateList.add(storeNamePredicate);
					if(pharmacyNCPDPIDPredicate!=null)
						predicateList.add(pharmacyNCPDPIDPredicate);
					if(pharmacyStoreNoPredicate!=null)
						predicateList.add(pharmacyStoreNoPredicate);
					if(pharmacyAddressPredicate!=null)
						predicateList.add(pharmacyAddressPredicate);
					if(pharmacyCityPredicate!=null)
						predicateList.add(pharmacyCityPredicate);
					if(pharmacyStatePredicate!=null)
						predicateList.add(pharmacyStatePredicate);
					if(pharmacyZipPredicate!=null)
						predicateList.add(pharmacyZipPredicate);
					if(pharmacyPhoneNoPredicate!=null)
						predicateList.add(pharmacyPhoneNoPredicate);
					if(pharmacyIs24HoursPredicate!=null)
						predicateList.add(pharmacyIs24HoursPredicate);
					if(pharmacyIsECPSPredicate!=null)
						predicateList.add(pharmacyIsECPSPredicate);
					
					Predicate[] predicateArray = new Predicate[predicateList.size()];
					predicateList.toArray(predicateArray);
					
					Predicate filterPredicate=cq.where(predicateArray).getRestriction();
					
					return filterPredicate;
				}
				   
			};
	   }
	
	
	public static Pageable createPharmacyPaginationRequest(int pageIndex, int offset){
		
		return new PageRequest(pageIndex, offset);
	}
	
}
