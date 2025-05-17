package org.example.candidate_application.controller;

import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidateEducationDTO;
import org.example.candidate_application.entity.CandidateEducation;
import org.example.candidate_application.service.CandidateEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates/{id}/educational-info")
public class CandidateEducationController {
    @Autowired
    private CandidateEducationService service;

    @PostMapping("/create")
    public ResponseEntity<CandidateEducationDTO> create(@PathVariable Long id, @RequestBody @Valid CandidateEducationDTO dto) {
        CandidateEducation savedEntity = service.save(id, dto);
        CandidateEducationDTO savedDto = convertToDto(savedEntity);
        return ResponseEntity.status(201).body(savedDto);
    }

    @PutMapping("/update")
    public ResponseEntity<CandidateEducationDTO> update(@PathVariable Long id, @RequestBody @Valid CandidateEducationDTO dto) {
        CandidateEducation updatedEntity = service.save(id, dto);
        CandidateEducationDTO updatedDto = convertToDto(updatedEntity);
        return ResponseEntity.ok(updatedDto);
    }

    private CandidateEducationDTO convertToDto(CandidateEducation entity) {
        CandidateEducationDTO dto = new CandidateEducationDTO();
        dto.setDegree(entity.getDegree());
        dto.setInstitution(entity.getInstitution());
        dto.setYearOfPassing(entity.getYearOfPassing());
        return dto;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }
}