package org.example.candidate_application.controller;

import jakarta.validation.Valid;
import org.example.candidate_application.dto.JobOfferNotificationDTO;
import org.example.candidate_application.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-offer")
public class JobOfferNotificationController {

    @Autowired
    private JobOfferService jobOfferService;

    @PostMapping("/notify")
    public ResponseEntity<String> notifyCandidate(@RequestBody @Valid JobOfferNotificationDTO dto) {
        jobOfferService.sendJobOfferNotification(dto);
        return ResponseEntity.ok("Job offer notification sent via RabbitMQ.");
    }
}

