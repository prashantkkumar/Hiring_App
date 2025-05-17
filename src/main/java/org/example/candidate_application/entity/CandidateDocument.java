package org.example.candidate_application.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", nullable = false)
    @JsonIgnore
    private Candidate candidate;

    private String documentType;
    private String fileUrl;
    private Boolean verified;
}
