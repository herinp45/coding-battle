package com.codingbattle.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codingbattle.backend.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    /**
     * Find user by email
     */
    User findByEmail(String email);
    /**
     * Find user by username and email
     */
    User findByUsernameAndEmail(String username, String email);
    
    /**
     * Check if user exists by username
     */ 
    Boolean existsByUsername(String username);
    /**
     * Check if user exists by email
     */
    Boolean existsByEmail(String email);

}