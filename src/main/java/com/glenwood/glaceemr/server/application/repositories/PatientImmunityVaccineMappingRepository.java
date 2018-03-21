package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.PatientImmunityVaccineMapping;
@Repository
public interface PatientImmunityVaccineMappingRepository extends JpaRepository<PatientImmunityVaccineMapping,Integer>,JpaSpecificationExecutor<PatientImmunityVaccineMapping> {

}
