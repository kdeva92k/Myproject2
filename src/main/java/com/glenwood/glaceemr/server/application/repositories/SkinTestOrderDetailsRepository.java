package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.SkinTestOrderDetails;

public interface SkinTestOrderDetailsRepository extends JpaRepository<SkinTestOrderDetails, Integer> ,JpaSpecificationExecutor<SkinTestOrderDetails> {
}
