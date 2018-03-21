package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults;

public interface Hl7UnmappedResultsRepository extends JpaRepository<Hl7Unmappedresults, Integer>,JpaSpecificationExecutor<Hl7Unmappedresults>{

}
