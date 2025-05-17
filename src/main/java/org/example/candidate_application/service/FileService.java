package org.example.candidate_application.service;


import org.example.candidate_application.entity.Candidate;
import org.example.candidate_application.entity.FileEntity;
import org.example.candidate_application.repository.CandidateRepository;
import org.example.candidate_application.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public FileEntity storeFile(MultipartFile file , Long candidateId) throws IOException {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFiletype(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setCandidate(candidate);

        return fileRepository.save(fileEntity);
    }
}
