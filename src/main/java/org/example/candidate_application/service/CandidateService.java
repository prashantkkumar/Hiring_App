package org.example.candidate_application.service;

import org.example.candidate_application.dto.CandidateDTO;
import org.example.candidate_application.entity.Candidate;
import org.example.candidate_application.repository.CandidateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate createCandidate(CandidateDTO dto) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(dto, candidate);
        candidate.setCreatedAt(LocalDateTime.now());
        candidate.setUpdatedAt(LocalDateTime.now());
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate with ID " + id + " not found"));
    }
}
