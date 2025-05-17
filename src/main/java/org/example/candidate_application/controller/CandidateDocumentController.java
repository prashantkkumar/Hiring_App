package org.example.candidate_application.controller;

import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidateDocumentDTO;
import org.example.candidate_application.service.CandidateDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates/{id}/documents")
public class CandidateDocumentController {

    @Autowired
    private CandidateDocumentService candidateDocumentService;

    @PutMapping
    public ResponseEntity<?> addDocument(@PathVariable("id") Long id,
                                         @Valid @RequestBody CandidateDocumentDTO dto) {
        candidateDocumentService.addDocument(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Document added successfully");
    }
}
