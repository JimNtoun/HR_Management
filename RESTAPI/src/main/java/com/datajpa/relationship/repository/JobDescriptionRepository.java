package com.datajpa.relationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.datajpa.relationship.model.JobDescription;

@Repository
public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long>{

}
