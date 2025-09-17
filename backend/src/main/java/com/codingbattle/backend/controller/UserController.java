package com.codingbattle.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Optional;

import com.codingbattle.backend.model.User;
import com.codingbattle.backend.dto.UserRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {
    /**
     * User service
     */
    @Autowired
    private final UserService userService;

    /**
     * User controller constructor
     * @param userService User service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Get all users
     * @return List of users
     */
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Get user by id
     *
     * @param id User id
     * @return User
     */
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    /**
     * Save user
     * @param user User
     * @return User
     */
    @PostMapping("/save")
    public User saveUser(@RequestBody UserRequestDTO user) {
        return userService.save(user);
    }
}