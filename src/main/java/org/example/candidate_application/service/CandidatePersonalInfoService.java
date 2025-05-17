package org.example.candidate_application.service;

import org.example.candidate_application.dto.CandidatePersonalInfoDTO;
import org.example.candidate_application.entity.Candidate;
import org.example.candidate_application.entity.CandidatePersonalInfo;
import org.example.candidate_application.repository.CandidatePersonalInfoRepository;
import org.example.candidate_application.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatePersonalInfoService {
    @Autowired
    private CandidatePersonalInfoRepository repository;

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidatePersonalInfo save(Long candidateId, CandidatePersonalInfoDTO dto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate with ID " + candidateId + " not found"));

        CandidatePersonalInfo info = new CandidatePersonalInfo();
        info.setCandidate(candidate);
        info.setDob(dto.getDob());
        info.setGender(dto.getGender());
        info.setAddress(dto.getAddress());
        info.setNationality(dto.getNationality());

        return repository.save(info);
    }
}