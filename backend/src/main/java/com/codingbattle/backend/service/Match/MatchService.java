package com.codingbattle.backend.service.Match;

import com.codingbattle.backend.dto.MatchDTO.MatchMapper;
import com.codingbattle.backend.dto.MatchDTO.MatchResponseDTO;
import com.codingbattle.backend.model.Match;
import com.codingbattle.backend.model.User;
import com.codingbattle.backend.repository.MatchRepo;
import com.codingbattle.backend.repository.ProblemRepo;
import com.codingbattle.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class MatchService {

    private final MatchRepo matchRepository;
    private final UserRepo userRepo;
    private final ProblemRepo problemRepo;
    private final MatchMapper matchMapper;

    private final ConcurrentLinkedQueue<UUID> waitingQueue = new ConcurrentLinkedQueue<>();

    @Autowired
    public MatchService(MatchRepo matchRepository, UserRepo userRepo, ProblemRepo problemRepo, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.userRepo = userRepo;
        this.problemRepo = problemRepo;
        this.matchMapper = matchMapper;
    }

    /**
     * Adds a user to the waiting queue and attempts to find a match.
     * @param userId the UUID of the user to be added to the queue
     */
    public synchronized MatchResponseDTO joinQueue(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if (waitingQueue.contains(userId)) {
            throw new IllegalStateException("User is already in the queue");
        }

        waitingQueue.add(userId);
        return findMatch();
    }

    /**
     * Attempts to find a match for two users in the waiting queue.
     * If a match is found, creates a Match entity and saves it to the repository.
     */
    private MatchResponseDTO findMatch() {
        UUID user1 = waitingQueue.poll();
        UUID user2 = waitingQueue.poll();

        if (user1 == null || user2 == null) {
            // only one user, requeue if needed
            if (user1 != null) waitingQueue.add(user1);
            return null;
        }

        User user1A = userRepo.findById(user1).orElse(null);
        User user2A = userRepo.findById(user2).orElse(null);

        if (user1A == null) {
            if (user2A != null) waitingQueue.add(user2);
            return null;
        }
        if (user2A == null) {
            waitingQueue.add(user1);
            return null;
        }

        // Select a random problem
        var problem = problemRepo.findAll().stream().findAny().orElse(null);
        if (problem == null) {
            waitingQueue.add(user1);
            waitingQueue.add(user2);
            return null;
        }

        // Create and persist match
        Match match = new Match(problem, user1A, user2A, null);
        matchRepository.save(match);

        System.out.println("Created match: " + match.getId() + " for users " + user1 + " and " + user2);

        return matchMapper.toDTO(match);
    }
}
