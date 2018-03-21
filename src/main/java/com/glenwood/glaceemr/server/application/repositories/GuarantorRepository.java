package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.Guarantor;

/**
 * Repository for Guarantor
 * @author chandrahas
 */
@Repository
public interface GuarantorRepository extends JpaRepository<Guarantor, Integer> ,JpaSpecificationExecutor<Guarantor>{

}
