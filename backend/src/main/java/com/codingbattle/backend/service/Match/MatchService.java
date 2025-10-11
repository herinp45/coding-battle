package com.codingbattle.backend.service.Match;

import com.codingbattle.backend.model.Match;
import com.codingbattle.backend.model.User;
import com.codingbattle.backend.repository.MatchRepo;
import com.codingbattle.backend.repository.ProblemRepo;
import com.codingbattle.backend.repository.UserRepo;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class MatchService {

    /**
     * Repository for match data.
     */
    private final MatchRepo matchRepository;

    /**
     * Queue for users waiting to be matched.
     */
    private final ConcurrentLinkedQueue<UUID> waitingQueue = new ConcurrentLinkedQueue<>();

    /**
     * Repository for user data.
     */
    private final UserRepo userRepo;

    /**
     * Repository for problem data.
     */
    private final ProblemRepo problemRepo;

    /**
     * Constructor for MatchService.
     * @param matchRepository the repository for match data
     * @param userRepo the repository for user data
     * @param problemRepo the repository for problem data
     */
    @Autowired
    public MatchService(MatchRepo matchRepository, UserRepo userRepo, ProblemRepo problemRepo) {
        this.matchRepository = matchRepository;
        this.userRepo = userRepo;
        this.problemRepo = problemRepo;
    }

    /**
     * Adds a user to the waiting queue and attempts to find a match.
     * @param userId the UUID of the user to be added to the queue
     */
    public void joinQueue(UUID userId) {
        waitingQueue.add(userId);
        findMatch();
    }

    /**
     * Attempts to find a match for two users in the waiting queue.
     * If a match is found, creates a Match entity and saves it to the repository.
     * If not enough users are available, they remain in the queue.
     */
    private void findMatch() {
        UUID user1 = waitingQueue.poll();
        UUID user2 = waitingQueue.poll();
        if (user1 == null || user2 == null) {
            // only one user, put back if needed
            if (user1 != null) waitingQueue.add(user1);
            return;
        }

        // Find User and Problem entities from their UUIDs
        User user1A = userRepo.findById(user1).orElse(null);
        User user2A = userRepo.findById(user2).orElse(null);

        // If either user is not found, re-add the other user to the queue if needed
        if (user1A == null) {
            if (user2A != null) waitingQueue.add(user2);
            return;
        }
        if (user2A == null) {
            waitingQueue.add(user1);
            return;
        }
        // TODO: Select a random problem from the database
        var problem = problemRepo.findAll().stream().findAny().orElse(null);
        if (problem == null) {
            // No problem found, re-add users to the queue
            waitingQueue.add(user1);
            waitingQueue.add(user2);
            return;
        }

        Match match = new Match(problem, user1A, user2A, null);
        matchRepository.save(match);
        System.out.println("Created match: " + match.getId() + " for users " + user1 + " and " + user2);

        // TODO: send matchFound via WebSocket to userA and userB
    }
}
