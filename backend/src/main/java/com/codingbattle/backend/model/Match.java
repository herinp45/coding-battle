package com.codingbattle.backend.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private User winner;

    @Column(name = "started_at", nullable = false, updatable = false)
    private Instant startedAt = Instant.now();



    // ===== Constructors =====
    public Match() {}

    public Match(UUID id, Problem problem, User user1, User user2, User winner, Instant startedAt) {
        this.id = id;
        this.problem = problem;
        this.user1 = user1;
        this.user2 = user2;
        this.winner = winner;
        this.startedAt = startedAt;
    }

    public Match(Problem problem, User user1, User user2, Instant startedAt) {
        this.problem = problem;
        this.user1 = user1;
        this.user2 = user2;
        this.startedAt = startedAt != null ? startedAt : Instant.now();
    }

    // ===== Getters & Setters =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Problem getProblem() { return problem; }
    public void setProblem(Problem problem) { this.problem = problem; }

    public User getUser1() { return user1; }
    public void setUser1(User user1) { this.user1 = user1; }

    public User getUser2() { return user2; }
    public void setUser2(User user2) { this.user2 = user2; }

    public User getWinner() { return winner; }
    public void setWinner(User winner) { this.winner = winner; }

    public Instant getStartedAt() { return startedAt; }
    public void setStartedAt(Instant startedAt) { this.startedAt = startedAt; }

}
