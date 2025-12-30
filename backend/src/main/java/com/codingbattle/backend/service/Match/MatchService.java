package com.codingbattle.backend.service.Match;

import com.codingbattle.backend.dto.MatchDTO.MatchMapper;
import com.codingbattle.backend.dto.MatchDTO.MatchResponseDTO;
import com.codingbattle.backend.model.Match;
import com.codingbattle.backend.model.User;
import com.codingbattle.backend.repository.MatchRepo;
import com.codingbattle.backend.repository.ProblemRepo;
import com.codingbattle.backend.repository.UserRepo;
import com.codingbattle.backend.dto.MatchDTO.MatchProblemResponseDTO;
import com.codingbattle.backend.dto.Problem.ProblemMapper;

import com.codingbattle.backend.model.Problem;
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
    public MatchService(MatchRepo matchRepository, UserRepo userRepo, ProblemRepo problemRepo, MatchMapper matchMapper, ProblemMapper problemMapper) {
        this.matchRepository = matchRepository;
        this.userRepo = userRepo;
        this.problemRepo = problemRepo;
        this.matchMapper = matchMapper;
        this.problemMapper = problemMapper;
    }

    /**
     * Join the matchmaking queue (toggle: join if not in queue, cancel if already in queue)
     */
    public synchronized MatchResponseDTO joinQueue(String username) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username cannot be null or blank");

        if (waitingQueue.contains(username)) {
            // Already in queue → remove
            waitingQueue.remove(username);
            return null; // indicate cancelled
        }

        // Add to queue
        waitingQueue.add(username);
        return findMatch();
    }

    /**
     * Explicitly leave the queue (for reload/unmount)
     */
    public synchronized void leaveQueue(String username) {
        waitingQueue.remove(username);
    }

    /**
     * Try to match two users from the queue
     */
    private MatchResponseDTO findMatch() {
        String username1 = waitingQueue.poll();
        String username2 = waitingQueue.poll();

        if (username1 == null || username2 == null) {
            if (username1 != null) waitingQueue.add(username1);
            return null;
        }

        User user1 = userRepo.findByUsername(username1);
        User user2 = userRepo.findByUsername(username2);

        if (user1 == null) {
            if (user2 != null) waitingQueue.add(username2);
            return null;
        }
        if (user2 == null) {
            waitingQueue.add(username1);
            return null;
        }

        var problem = problemRepo.findAll().stream().findAny().orElse(null);
        if (problem == null) {
            waitingQueue.add(username1);
            waitingQueue.add(username2);
            return null;
        }

        Match match = new Match(problem, user1, user2, null);
        matchRepository.save(match);

        System.out.println("Created match: " + match.getId() + " for users " + username1 + " and " + username2);
        return matchMapper.toDTO(match);
    }

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
