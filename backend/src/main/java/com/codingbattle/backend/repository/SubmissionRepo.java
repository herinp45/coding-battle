package com.codingbattle.backend.repository;

import com.codingbattle.backend.model.Submission;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepo {

    void save(Submission submission);
}
