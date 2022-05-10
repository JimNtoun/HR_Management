package com.datajpa.relationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.datajpa.relationship.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{

}

