package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.PatientPortalSharedDocs;

@Repository
public interface PatientPortalSharedDocsRepository extends JpaRepository<PatientPortalSharedDocs, Integer>, JpaSpecificationExecutor<PatientPortalSharedDocs> {

}
