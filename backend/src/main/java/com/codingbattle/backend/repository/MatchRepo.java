package com.codingbattle.backend.repository;

import com.codingbattle.backend.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MatchRepo extends JpaRepository<Match, UUID> {
    @Query("SELECT m FROM Match m WHERE (m.user1.username = :username OR m.user2.username = :username) AND m.status = 'IN_PROGRESS'")
    Optional<Match> findActiveMatch(@Param("username") String username);
}
