package org.example.candidate_application.service;


import org.example.candidate_application.entity.JobOfferNotification;
import org.example.candidate_application.repository.JobOfferNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobOfferNotificationService {
    @Autowired
    private JobOfferNotificationRepository repository;

    public JobOfferNotification save(Long candidateId, boolean sent, String errorMessage) {
        JobOfferNotification jobOffer = new JobOfferNotification();
        jobOffer.setSent(sent);
        jobOffer.setSentAt(LocalDateTime.now());
        jobOffer.setRetryCount(0);
        jobOffer.setErrorMessage(errorMessage);

        return repository.save(jobOffer);
    }
}
