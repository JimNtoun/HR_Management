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
import com.datajpa.relationship.model.JobDescription;
import com.datajpa.relationship.repository.JobDescriptionRepository;

@RestController
@RequestMapping("/api/v1")
public class JobDescriptionController {
    @Autowired
    private JobDescriptionRepository jobDescriptionRepository;

    @GetMapping("/descriptions")
    public List<JobDescription> getAllJobDescriptions() {
        return jobDescriptionRepository.findAll();
    }

    @GetMapping("/descriptions/{id}")
    public ResponseEntity<JobDescription> getJobDescriptionById(@PathVariable(value = "id") Long jd_id)
            throws ResourceNotFoundException {
        JobDescription jobDescription = jobDescriptionRepository.findById(jd_id)
                .orElseThrow(() -> new ResourceNotFoundException("JobDescription not found for this id :: " + jd_id));
        return ResponseEntity.ok().body(jobDescription);
    }

    @PostMapping("/descriptions")
    public JobDescription createJobDescription(@Valid @RequestBody JobDescription jobDescription) {
        return jobDescriptionRepository.save(jobDescription);
    }

    @PutMapping("/descriptions/{id}")
    public ResponseEntity<JobDescription> updateJobDescription(@PathVariable(value = "id") Long jd_id,
                                                 @Valid @RequestBody JobDescription jobDescriptionDetails) throws ResourceNotFoundException {
        JobDescription jobDescription = jobDescriptionRepository.findById(jd_id)
                .orElseThrow(() -> new ResourceNotFoundException("JobDescription not found for this id :: " + jd_id));

        jobDescription.setRole(jobDescriptionDetails.getRole());
        final JobDescription updatedJobDescription = jobDescriptionRepository.save(jobDescription);
        return ResponseEntity.ok(updatedJobDescription);
    }

    @DeleteMapping("/descriptions/{id}")
    public Map<String, Boolean> deleteJobDescription(@PathVariable(value = "id") Long jd_id)
            throws ResourceNotFoundException {
        JobDescription jobDescription = jobDescriptionRepository.findById(jd_id)
                .orElseThrow(() -> new ResourceNotFoundException("JobDescription not found for this id :: " + jd_id));

        jobDescriptionRepository.delete(jobDescription);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}