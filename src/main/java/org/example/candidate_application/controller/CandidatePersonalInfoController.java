package org.example.candidate_application.controller;

import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidatePersonalInfoDTO;
import org.example.candidate_application.entity.CandidatePersonalInfo;
import org.example.candidate_application.service.CandidatePersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates/{id}/personal-info")
public class CandidatePersonalInfoController {
    @Autowired
    private CandidatePersonalInfoService service;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidatePersonalInfoDTO> create(@PathVariable Long id, @RequestBody @Valid CandidatePersonalInfoDTO dto) {
        CandidatePersonalInfo savedEntity = service.save(id, dto);
        CandidatePersonalInfoDTO savedDto = convertToDto(savedEntity);
        return ResponseEntity.status(201).body(savedDto);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidatePersonalInfoDTO> update(@PathVariable Long id, @RequestBody @Valid CandidatePersonalInfoDTO dto) {
        CandidatePersonalInfo updatedEntity = service.save(id, dto);
        CandidatePersonalInfoDTO updatedDto = convertToDto(updatedEntity);
        return ResponseEntity.ok(updatedDto);
    }

    private CandidatePersonalInfoDTO convertToDto(CandidatePersonalInfo entity) {
        CandidatePersonalInfoDTO dto = new CandidatePersonalInfoDTO();
        dto.setDob(entity.getDob());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setNationality(entity.getNationality());
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

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Unsupported media type: " + ex.getContentType() + ". Expected 'application/json'.");
        return ResponseEntity.status(415).body(error);
    }
}