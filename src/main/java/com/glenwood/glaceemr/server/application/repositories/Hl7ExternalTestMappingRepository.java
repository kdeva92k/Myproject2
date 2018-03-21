package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping;;

public interface Hl7ExternalTestMappingRepository   extends JpaRepository<Hl7ExternalTestmapping, Integer>,JpaSpecificationExecutor<Hl7ExternalTestmapping> {

}
