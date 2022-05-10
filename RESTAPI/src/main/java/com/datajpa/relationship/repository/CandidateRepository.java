package com.datajpa.relationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.datajpa.relationship.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}
