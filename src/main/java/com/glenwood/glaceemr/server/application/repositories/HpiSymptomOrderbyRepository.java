package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.HpiSymptomOrderby;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory;

@Repository
public interface HpiSymptomOrderbyRepository extends JpaRepository<HpiSymptomOrderby, Integer>, JpaSpecificationExecutor<HpiSymptomOrderby>{

}
