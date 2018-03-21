package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest;

public interface Hl7ExternalTestRepository  extends JpaRepository<Hl7ExternalTest, Integer>,JpaSpecificationExecutor<Hl7ExternalTest> {

}
