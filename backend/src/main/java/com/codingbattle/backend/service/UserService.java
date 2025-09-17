package com.codingbattle.backend.service;

import com.codingbattle.backend.repository.UserRepo;
import com.codingbattle.backend.dto.UserMapper;
import com.codingbattle.backend.dto.UserRequestDTO;
import com.codingbattle.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    /**
     * User repository
     */
    @Autowired
    private final UserRepo userRepo;
    /**
     * User mapper
     */
    @Autowired
    private final UserMapper userMapper;

    /**
     * User service constructor
     * @param userRepo User repository
     * @param userMapper User mapper
     */
    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }
    
    /**
     * Find user by id
     *
     * @param id User id
     * @return User
     */
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    /**
     * Save user
     * @param user User
     * @return User
     */
    public User save(UserRequestDTO user) {
        User userEntity = UserMapper.toEntity(user);
        return userRepo.save(userEntity);
    }
}