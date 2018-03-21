package com.glenwood.glaceemr.server.application.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.IAMeasures;


@Repository
	public interface IAMeasuresRepository extends JpaRepository<IAMeasures, Integer> ,JpaSpecificationExecutor<IAMeasures> {


}
