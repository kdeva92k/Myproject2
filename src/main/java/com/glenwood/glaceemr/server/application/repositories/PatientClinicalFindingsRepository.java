package com.glenwood.glaceemr.server.application.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.glenwood.glaceemr.server.application.models.PatientClinicalFindings;

public interface PatientClinicalFindingsRepository extends JpaRepository<PatientClinicalFindings, Integer>,JpaSpecificationExecutor<PatientClinicalFindings>{
	@Query("select current_timestamp() from Users pb where pb.userId=1")
	   Timestamp findCurrentTimeStamp();
}
