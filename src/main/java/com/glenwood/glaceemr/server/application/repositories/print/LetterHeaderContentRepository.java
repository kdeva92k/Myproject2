package com.glenwood.glaceemr.server.application.repositories.print;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.print.LetterHeaderContent;


@Repository
public interface LetterHeaderContentRepository extends JpaRepository<LetterHeaderContent, Integer>,JpaSpecificationExecutor<LetterHeaderContent>{

}