package com.glenwood.glaceemr.server.application.repositories.print;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.print.GenericLetterFooter;

@Repository
public interface GenericFooterRepository extends JpaRepository<GenericLetterFooter, Integer>,JpaSpecificationExecutor<GenericLetterFooter>{

}

