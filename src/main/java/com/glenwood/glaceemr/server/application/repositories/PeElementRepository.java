package com.glenwood.glaceemr.server.application.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.glenwood.glaceemr.server.application.models.PeElement;


@Repository
public interface PeElementRepository extends JpaRepository<PeElement, Integer>,JpaSpecificationExecutor<PeElement>{

}
