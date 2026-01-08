package com.codingbattle.backend.service.Match;

import com.codingbattle.backend.dto.MatchDTO.MatchMapper;
import com.codingbattle.backend.dto.MatchDTO.MatchResponseDTO;
import com.codingbattle.backend.dto.MatchDTO.MatchProblemResponseDTO;
import com.codingbattle.backend.dto.Problem.ProblemMapper;
import com.codingbattle.backend.model.Match;
import com.codingbattle.backend.model.MatchStatus;
import com.codingbattle.backend.model.Problem;
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
    private final ProblemMapper problemMapper;
    private final ConcurrentLinkedQueue<String> waitingQueue = new ConcurrentLinkedQueue<>();

    @Autowired
    public MatchService(MatchRepo matchRepository, UserRepo userRepo, ProblemRepo problemRepo,
                        MatchMapper matchMapper, ProblemMapper problemMapper) {
        this.matchRepository = matchRepository;
        this.userRepo = userRepo;
        this.problemRepo = problemRepo;
        this.matchMapper = matchMapper;
        this.problemMapper = problemMapper;
    }

    /**
     * Join the matchmaking queue.
     * If two users are available, create a match immediately.
     */
    public synchronized MatchResponseDTO joinQueue(String username) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username cannot be null or blank");

        // If already in queue → remove (cancel)
        if (waitingQueue.contains(username)) {
            waitingQueue.remove(username);
            return null;
        }

        // Add to queue
        waitingQueue.add(username);

        // Check if a match can be created
        if (waitingQueue.size() >= 2) {
            String user1Name = waitingQueue.poll();
            String user2Name = waitingQueue.poll();

            User user1 = userRepo.findByUsername(user1Name);
            User user2 = userRepo.findByUsername(user2Name);

            if (user1 == null) { waitingQueue.add(user2Name); return null; }
            if (user2 == null) { waitingQueue.add(user1Name); return null; }

            Problem problem = problemRepo.findRandomProblem();
            if (problem == null) { waitingQueue.add(user1Name); waitingQueue.add(user2Name); return null; }

            Match match = new Match(problem, user1, user2, null);
            match.setStatus(MatchStatus.IN_PROGRESS);
            System.out.println(match);
            matchRepository.save(match);

            System.out.println("Created match: " + match.getId() + " for users " + user1Name + " and " + user2Name);
            return matchMapper.toDTO(match);
        }

        // Still waiting for an opponent
        return null;
    }

    /**
     * Explicitly leave the queue
     */
    public synchronized void leaveQueue(String username) {
        waitingQueue.remove(username);
    }

    /**
     * Get an active match by username
     */
    public MatchResponseDTO getMatchStatus(String username) {
        Match match = matchRepository.findActiveMatch(username).orElse(null);
        if (match == null) return null;
        return matchMapper.toDTO(match);
    }

    /**
     * Get a match and its problem by match ID
     */
    public MatchProblemResponseDTO getMatchById(UUID matchID) {
        Match match = matchRepository.findById(matchID).orElse(null);
        if (match == null) return null;

        Problem problem = match.getProblem();
        MatchProblemResponseDTO dto = new MatchProblemResponseDTO();
        dto.setMatchId(match.getId());
        dto.setPlayer1Id(match.getUser1().getId());
        dto.setPlayer2Id(match.getUser2().getId());
        dto.setProblem(problemMapper.toDTO(problem));
        return dto;
    }
}
