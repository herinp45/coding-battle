package com.codingbattle.backend.repository;

import com.codingbattle.backend.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemRepo extends JpaRepository<Problem, UUID> {
}
