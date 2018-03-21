package com.glenwood.glaceemr.server.application.repositories.print;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.print.PatientHeaderDetails;

@Repository
public interface PatientHeaderDetailsRepository extends JpaRepository<PatientHeaderDetails, Integer>,JpaSpecificationExecutor<PatientHeaderDetails>{
	//Count of attributes based on header id and page id
	@Query("select count(*) from PatientHeaderDetails p where p.patientHeaderId = ?1 and p.pageId=?2 ")
	long countByPatientHeaderIdAndPageId(int patientHeaderId, int pageId);
}

