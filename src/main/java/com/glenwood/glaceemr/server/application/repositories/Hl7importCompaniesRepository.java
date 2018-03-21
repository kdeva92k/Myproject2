package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.glenwood.glaceemr.server.application.models.Hl7importCompanies;

@Repository
public interface Hl7importCompaniesRepository extends JpaRepository<Hl7importCompanies ,Integer>,JpaSpecificationExecutor<Hl7importCompanies>{

}