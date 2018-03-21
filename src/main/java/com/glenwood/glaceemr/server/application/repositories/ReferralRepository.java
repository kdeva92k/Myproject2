package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.Referral;

/**
 * Repository for referral module
 * @author chandrahas
 */
@Repository
public interface ReferralRepository extends JpaRepository<Referral, Integer> ,JpaSpecificationExecutor<Referral>{

}
