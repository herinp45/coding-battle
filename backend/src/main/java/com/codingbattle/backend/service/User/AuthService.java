package com.codingbattle.backend.service.User;

import com.codingbattle.backend.dto.UserDTO.LoginRequestDTO;
import com.codingbattle.backend.dto.UserDTO.UserMapper;
import com.codingbattle.backend.dto.UserDTO.UserRequestDTO;
import com.codingbattle.backend.dto.UserDTO.UserResponseDTO;
import com.codingbattle.backend.model.User;
import com.codingbattle.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

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
    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder, JWTService jwtService, UserMapper userMapper) {
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
     * @param userRequestDTO contains the username and password
     * @return a JWT token if authentication is successful
     * @throws RuntimeException if authentication fails
     */
    public String login(LoginRequestDTO userRequestDTO){
        String identifier = userRequestDTO.getIdentifier();
        String password = userRequestDTO.getPassword();
        User user = userRepo.findByUsernameOrEmail(identifier, identifier);
        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwtService.generateToken(user.getUsername(), String.valueOf(user.getRole()));
    }

    /**
     * Gets the current user based on the provided JWT token.
     *
     * @param token the JWT token
     * @return UserResponseDTO containing the current user's details
     * @throws RuntimeException if the user is not found
     */
    public UserResponseDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return userMapper.toResponseDTO(user);
    }

    /**
     * Gets all users.
     *
     * @return List of UserResponseDTO containing all users
     */
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }
}
