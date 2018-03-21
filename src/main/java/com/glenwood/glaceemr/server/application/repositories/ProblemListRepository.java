package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.CodingSystems;
import com.glenwood.glaceemr.server.application.models.ProblemList;

@Repository
public interface ProblemListRepository extends JpaRepository<ProblemList, Integer> ,JpaSpecificationExecutor<ProblemList> {

}
