package com.codingbattle.backend.service;

import com.codingbattle.backend.dto.UserMapper;
import com.codingbattle.backend.dto.UserRequestDTO;
import com.codingbattle.backend.dto.UserResponseDTO;
import com.codingbattle.backend.model.User;
import com.codingbattle.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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
     * JWT service for generating and validating tokens.
     */
    private final JWTService jwtService;

    /**
     * User Mapper for converting between User and UserDTO.
     */
    private final UserMapper userMapper;

    /**
     * Constructor for AuthService.
     *
     * @param userRepo              the repository for user data
     * @param passwordEncoder       the password encoder for hashing passwords
     * @param jwtService            the JWT service for generating and validating tokens
     */
    @Autowired
    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }



    /**
     * Registers a new user.
     *
     * @param userRequestDTO contains the user details
     * @return UserResponseDTO containing the registered user's details
     * @throws RuntimeException if the username or email already exists
     */
    public UserResponseDTO register(UserRequestDTO userRequestDTO){
        if (userRepo.existsByUsername(userRequestDTO.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepo.existsByEmail(userRequestDTO.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        User user = userMapper.toEntity(userRequestDTO);
        user.setPasswordHash(passwordEncoder.encode(userRequestDTO.getPassword()));
        userRepo.save(user);
        return userMapper.toResponseDTO(user);
    }

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return a JWT token if authentication is successful
     * @throws RuntimeException if authentication fails
     */
    public String Login(String username, String password){
        User user = userRepo.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwtService.generateToken(user.getUsername());
    }
}
