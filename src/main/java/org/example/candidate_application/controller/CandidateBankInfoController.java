package org.example.candidate_application.controller;

import jakarta.validation.Valid;
import org.example.candidate_application.dto.CandidateBankInfoDTO;
import org.example.candidate_application.entity.CandidateBankInfo;
import org.example.candidate_application.service.CandidateBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates/{id}/bank-info")
public class CandidateBankInfoController {
    @Autowired
    private CandidateBankInfoService service;

    @PostMapping("/create")
    public ResponseEntity<CandidateBankInfoDTO> create(@PathVariable Long id, @RequestBody @Valid CandidateBankInfoDTO dto) {
        CandidateBankInfo savedEntity = service.save(id, dto);
        CandidateBankInfoDTO savedDto = convertToDto(savedEntity);
        return ResponseEntity.status(201).body(savedDto);
    }

    @PutMapping("/update")
    public ResponseEntity<CandidateBankInfoDTO> update(@PathVariable Long id, @RequestBody @Valid CandidateBankInfoDTO dto) {
        CandidateBankInfo updatedEntity = service.save(id, dto);
        CandidateBankInfoDTO updatedDto = convertToDto(updatedEntity);
        return ResponseEntity.ok(updatedDto);
    }

    private CandidateBankInfoDTO convertToDto(CandidateBankInfo entity) {
        CandidateBankInfoDTO dto = new CandidateBankInfoDTO();
        dto.setBankName(entity.getBankName());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setIfscCode(entity.getIfscCode());
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