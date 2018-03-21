package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.PatientCahpsSurvey;
import com.glenwood.glaceemr.server.application.models.PatientSurveyAnswers;

public interface PatientCahpsSurveyRepository extends JpaRepository<PatientCahpsSurvey,Integer>,JpaSpecificationExecutor<PatientCahpsSurvey> {

}
