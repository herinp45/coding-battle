package com.codingbattle.backend.service;

import com.codingbattle.backend.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {

    /**
     * Repository for user data.
     */
    private final UserRepo userRepo;

    /**
     * Password encoder for hashing passwords.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Authentication manager for handling authentication.
     */
    private final AuthenticationManager authenticationManager;
    /**
     * JWT service for generating and validating tokens.
     */
    private final JWTService jwtService;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
}
