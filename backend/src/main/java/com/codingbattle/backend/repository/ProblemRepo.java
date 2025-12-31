package com.codingbattle.backend.repository;

import com.codingbattle.backend.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemRepo extends JpaRepository<Problem, UUID> {
    @Query(value = "SELECT * FROM problems ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Problem findRandomProblem();
}
