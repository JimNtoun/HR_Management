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
import com.datajpa.relationship.model.Recruiter;
import com.datajpa.relationship.repository.RecruiterRepository;

@RestController
@RequestMapping("/api/v1")
public class RecruiterController {
    @Autowired
    private RecruiterRepository recruiterRepository;

    @GetMapping("/recruiters")
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    @GetMapping("/recruiters/{id}")
    public ResponseEntity<Recruiter> getRecruiterById(@PathVariable(value = "id") Long recruiterId)
            throws ResourceNotFoundException {
        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found for this id :: " + recruiterId));
        return ResponseEntity.ok().body(recruiter);
    }

    @PostMapping("/recruiters")
    public Recruiter createRecruiter(@Valid @RequestBody Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    @PutMapping("/recruiters/{id}")
    public ResponseEntity<Recruiter> updatedRecruiter(@PathVariable(value = "id") Long recruiterId,
                                                   @Valid @RequestBody Recruiter recruiterDetails) throws ResourceNotFoundException {
        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + recruiterId));

        recruiter.setEmailId(recruiterDetails.getEmailId());
        recruiter.setLastName(recruiterDetails.getLastName());
        recruiter.setFirstName(recruiterDetails.getFirstName());
        recruiter.setSalary(recruiterDetails.getSalary());
        final Recruiter updatedRecruiter = recruiterRepository.save(recruiter);
        return ResponseEntity.ok(updatedRecruiter);
    }

    @DeleteMapping("/recruiters/{id}")
    public Map<String, Boolean> deleteRecruiter(@PathVariable(value = "id") Long recruiterId)
            throws ResourceNotFoundException {
        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter not found for this id :: " + recruiterId));

        recruiterRepository.delete(recruiter);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}