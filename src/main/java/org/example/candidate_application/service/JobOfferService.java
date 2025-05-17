package org.example.candidate_application.service;

import org.example.candidate_application.config.RabbitMQConfig;
import org.example.candidate_application.dto.JobOfferNotificationDTO;
import org.example.candidate_application.entity.Candidate;
import org.example.candidate_application.entity.OnboardStatus;
import org.example.candidate_application.entity.Status;
import org.example.candidate_application.repository.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobOfferService {

    private static final Logger logger = LoggerFactory.getLogger(JobOfferService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CandidateRepository candidateRepository;

    public boolean notifyCandidate(JobOfferNotificationDTO dto) {
        try {
            Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                    .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + dto.getCandidateId()));

            if (candidate.getOnboardStatus() == OnboardStatus.COMPLETE &&
                    candidate.getStatus() == Status.OFFERED) {

                rabbitTemplate.convertAndSend(
                        RabbitMQConfig.EXCHANGE,
                        RabbitMQConfig.ROUTING_KEY,
                        dto
                );
                logger.info("Notification sent successfully for candidate id {}", dto.getCandidateId());
                return true;
            } else {
                logger.warn("Candidate with id {} is not eligible for notification. Current status: {}, onboardStatus: {}",
                        dto.getCandidateId(), candidate.getStatus(), candidate.getOnboardStatus());
                return false;
            }
        } catch (Exception e) {
            logger.error("Error while notifying candidate with id " + dto.getCandidateId(), e);
            return false;
        }
    }
}
