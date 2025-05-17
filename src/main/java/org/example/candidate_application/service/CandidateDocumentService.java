package org.example.candidate_application.service;

import org.example.candidate_application.dto.CandidateDocumentDTO;
import org.example.candidate_application.entity.Candidate;
import org.example.candidate_application.entity.CandidateDocument;
import org.example.candidate_application.repository.CandidateDocumentRepository;
import org.example.candidate_application.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateDocumentService {

    @Autowired
    private CandidateDocumentRepository candidateDocumentRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public void addDocument(Long candidateId, CandidateDocumentDTO candidateDocumentDto) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));

        CandidateDocument candidateDocument = new CandidateDocument();
        candidateDocument.setFileUrl(candidateDocumentDto.getFileUrl());
        candidateDocument.setDocumentType(candidateDocumentDto.getDocumentType());
        candidateDocument.setVerified(candidateDocumentDto.isVerified());
        candidateDocument.setCandidate(candidate);

        candidateDocumentRepository.save(candidateDocument);
    }
}
