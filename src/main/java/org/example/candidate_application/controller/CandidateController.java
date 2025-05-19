package org.example.candidate_application.controller;

import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidateDTO;
import org.example.candidate_application.entity.Candidate;
import org.example.candidate_application.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody @Valid CandidateDTO dto) {
        return new ResponseEntity<>(candidateService.createCandidate(dto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        return ResponseEntity.ok(candidateService.getCandidateById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody @Valid CandidateDTO dto) {
        Candidate updatedCandidate = candidateService.updateCandidate(id, dto);
        return ResponseEntity.ok(updatedCandidate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        boolean deleted = candidateService.deleteCandidateById(id);
        if (deleted) {
            return ResponseEntity.ok("Candidate deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
