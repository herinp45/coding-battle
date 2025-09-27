package com.codingbattle.backend.controller;

import com.codingbattle.backend.dto.UserDTO.LoginRequestDTO;
import com.codingbattle.backend.dto.UserDTO.UserResponseDTO;
import com.codingbattle.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.codingbattle.backend.dto.UserDTO.UserRequestDTO;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    /**
     * User service
     */
    private final AuthService userService;

    /**
     * User controller constructor
     * @param userService User service
     */
    @Autowired
    public UserController(AuthService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public UserResponseDTO registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.register(userRequestDTO);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequestDTO userRequestDTO) {
        return userService.login(userRequestDTO);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.register(userRequestDTO);
    }
}