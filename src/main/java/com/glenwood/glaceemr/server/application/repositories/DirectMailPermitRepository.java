package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.DirectMailPermit;


@Repository
public interface DirectMailPermitRepository extends JpaRepository<DirectMailPermit, Integer>,JpaSpecificationExecutor<DirectMailPermit>{

}
