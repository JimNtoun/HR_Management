package com.datajpa.relationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.datajpa.relationship.model.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{

}