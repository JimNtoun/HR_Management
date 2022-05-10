package com.datajpa.relationship.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datajpa.relationship.exception.ResourceNotFoundException;
import com.datajpa.relationship.model.Candidate;
import com.datajpa.relationship.repository.CandidateRepository;

@RestController
@RequestMapping("/api/v1")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/candidates")
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @GetMapping("/candidates/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable(value = "id") Long candidateId)
            throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for this id :: " + candidateId));
        return ResponseEntity.ok().body(candidate);
    }

    @PostMapping("/candidates")
    public Candidate createCompany(@Valid @RequestBody Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @PutMapping("/candidates/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable(value = "id") Long candidateId,
                                                 @Valid @RequestBody Candidate candidateDetails) throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for this id :: " + candidateId));

        candidate.setFirstName(candidateDetails.getFirstName());
        candidate.setLastName(candidateDetails.getLastName());
        final Candidate updatedCandidate = candidateRepository.save(candidate);
        return ResponseEntity.ok(updatedCandidate);
    }

    @DeleteMapping("/candidates/{id}")
    public Map<String, Boolean> deleteCandidate(@PathVariable(value = "id") Long candidateId)
            throws ResourceNotFoundException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for this id :: " + candidateId));

        candidateRepository.delete(candidate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}