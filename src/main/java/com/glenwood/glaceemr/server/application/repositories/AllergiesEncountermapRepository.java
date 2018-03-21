package com.glenwood.glaceemr.server.application.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.glenwood.glaceemr.server.application.models.AllergiesEncountermap;

public interface AllergiesEncountermapRepository extends JpaRepository<AllergiesEncountermap, Integer>, JpaSpecificationExecutor<AllergiesEncountermap>{
	@Query("select current_timestamp() from Users pb where 1=1")
	   Timestamp findCurrentTimeStamp();
}
