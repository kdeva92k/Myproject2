package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.CarePlanSummary;


@Repository
public interface CarePlanSummaryRepository extends JpaRepository<CarePlanSummary, Integer>,JpaSpecificationExecutor<CarePlanSummary> 
{
	CarePlanSummary findByCarePlanSummaryPatientIdAndCarePlanSummaryGoalIdAndCarePlanSummaryEpisodeId(Integer patientId,Integer goalId,Integer EpisodeId);
}