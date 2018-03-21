package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.PatientVisEntries;
@Repository
public interface PatientVisEntriesRepository extends JpaRepository<PatientVisEntries,Integer>,JpaSpecificationExecutor<PatientVisEntries> {

}
