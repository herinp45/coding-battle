package com.codingbattle.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.codingbattle.backend.model.User;
import com.codingbattle.backend.security.UserService;

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
     * @param id User id
     * @return User
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
}